package com.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import common.D;

public class boardDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	public boardDAO() { 
		try { 
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws SQLException {
		if (rs != null) 
				rs.close();
		
		if (pstmt != null) 
				pstmt.close();
		
		if (conn != null) 
				conn.close();
	}
	
	// 새로 추가한 메서드 페이징처리 
	
	public int getboardCount() throws SQLException{
		

		int cnt = 0;
		
		try {
			
			pstmt = conn.prepareStatement(D.SQL_BOARD_COUNT);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt(1);
				System.out.println("게시판 총 글 개수  : "+cnt);
			}								
			
		} catch (Exception e) {
				e.printStackTrace();
			}	finally {
				close();	
		}		
		return cnt;
	}
	// db에 있는 글 개수 확인하는 메서드 
	
	/*
	private List<boardDTO> buildList(ResultSet rs) throws SQLException {
			List<boardDTO> list = new ArrayList<>();
			
			// 리스트 만들기
			// list라는 새로운 arraylist를 만듦
			
			
			while (rs.next()) {

				int num = rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				if (content == null)
					content = "";
				String user_ID = rs.getString("user_ID");
				int viewCnt = rs.getInt("viewcnt");
				LocalDateTime regDate = rs.getObject("regdate", LocalDateTime.class);
	
				boardDTO dto = new boardDTO(num, title, content, user_ID, viewCnt, regDate);
				list.add(dto);

			}
			return list;
	}
	 */
	
	public List<boardDTO> get() throws SQLException {
		List<boardDTO> boardList = null;			
			// 데이터베이스에 저장된 모든 정보를 불러와 ArrayList에 저장하는 기능을 구현.

		try {
				pstmt = conn.prepareStatement(D.SQL_BOARD_GET_LIST);
				rs = pstmt.executeQuery();
				boardList = getboardList(rs);
		} finally {
				close();
		}
		return boardList;
	}		// 데이터베이스에서 가져온 ResultSet에 담긴 정보들을 BookDTO 타입인 List로 저장하는 메서드.
	
	
	// 글 쓰기
	public int insert(boardDTO dto) throws SQLException {
		int cnt = 0;
		
		// 사용자가 입력한 값인 title, content, user_ID, filename 값을 가져온다.
		String title = dto.getTitle();
		String content = dto.getContent();
		String user_ID = dto.getUser_ID();
		String filename = dto.getFilename();
		
		int num;
		String[] generatedCols = {"bd_num"};
		
		// auto_incremenet 값인 num 값을 dto 객체에 담아야 합니다. 
		// num 값은 auto_increment 값이므로 prepareStatement() 메서드 사용 시 매개변수를 두 개로 준다.
		
		try {
			pstmt = conn.prepareStatement(D.SQL_BOADR_INSERT, generatedCols);
		
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, user_ID);
			pstmt.setString(4, filename);
			
			cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					num = rs.getInt(1);
					dto.setNum(num);
				}
			}
		} finally {
			close();
		}
		
		return cnt;
	}


	// 조회수 증가시키고 선택한 글 상세 읽기
	public List<boardDTO> readByNum(int num) throws SQLException {
		List<boardDTO> boardList = null;
		
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(D.SQL_BOARD_INC_VIEWCNT);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = conn.prepareStatement(D.SQL_BOARD_GET_BY_NUM);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			boardList = getboardList(rs);
			
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			close();
		}	
	    
		return boardList;
	}
	
	// 수정할때 불러옴 선택한 글 상세 읽기
	public List<boardDTO> getByNum(int num) throws SQLException {
		List<boardDTO> boardList = null;
			
		try {
			pstmt = conn.prepareStatement(D.SQL_BOARD_GET_BY_NUM);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			boardList = getboardList(rs);
		} finally {
			close();
		}
			
		return boardList;
	}
	
	// 매개변수x
	public List<boardDTO> getboardList() throws SQLException{
		//가변길이 배열 생성
		List<boardDTO> boardList = new ArrayList<>(); 

		/*
		try {
			pstmt = conn.prepareStatement(D.SQL_BOARD_LIST);
			rs = pstmt.executeQuery(); */
			
			//정보의 갯수가 몇개인지 모르기때문에 while 반복문을 사용.
			while(rs.next()){
				//bdto 객체생성해서 그 안에 rs데이터 저장.
				boardDTO bdto = new boardDTO();
				
				bdto.setNum(rs.getInt("num"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				
				bdto.setUser_ID(rs.getString("user_ID"));
				bdto.setViewCnt(rs.getInt("viewcnt"));
				bdto.setRegDate(rs.getObject("regdate", LocalDateTime.class));
				// 여기까지가 한 행의 데이터를 저장한 것임. while로 모든 행을 반복한다.
				
				//가변배열(ArrayList)에 위의 데이터 저장
				//즉 배열 한칸에 회원 1명의 정보를 저장함.
				boardList.add(bdto); //업캐스팅
			}
		
		
		
		/*
		 * finally { close(); }
		 */
		
		return boardList;
	}
	
	// DB에서 게시글 전체 가져오는 메서드 구현
	// 매개변수 ResultSet rs
	public List<boardDTO> getboardList(ResultSet rs) throws SQLException{
			//가변길이 배열 생성
			List<boardDTO> boardList = new ArrayList<>(); 

			/*
			try {
				pstmt = conn.prepareStatement(D.SQL_BOARD_LIST);
				rs = pstmt.executeQuery(); */
				
				//정보의 갯수가 몇개인지 모르기때문에 while 반복문을 사용.
				while(rs.next()){
					//bdto 객체생성해서 그 안에 rs데이터 저장.
					boardDTO bdto = new boardDTO();
					
					bdto.setNum(rs.getInt("num"));
					bdto.setTitle(rs.getString("title"));
					bdto.setContent(rs.getString("content"));
					bdto.setUser_ID(rs.getString("user_ID"));
					bdto.setViewCnt(rs.getInt("viewcnt"));
					bdto.setRegDate(rs.getObject("regdate", LocalDateTime.class));
					// 여기까지가 한 행의 데이터를 저장한 것임. while로 모든 행을 반복한다.
					
					//가변배열(ArrayList)에 위의 데이터 저장
					//즉 배열 한칸에 회원 1명의 정보를 저장함.
					boardList.add(bdto); //업캐스팅
				}
			
			
			
			/*
			 * finally { close(); }
			 */
			
			return boardList;
		}//getBoardcontent 닫기
	
// getBoardList(int startRow, int pageSize) 오버로딩 메서드만들고 sql구문 변경하기.
	public List<boardDTO> getboardList(int startRow, int pageSize) throws SQLException {
		
		List<boardDTO> boardList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(D.SQL_BOARD_LIST);
			pstmt.setInt(1, startRow -1);
			pstmt.setInt(2, pageSize);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			boardDTO bdto = new boardDTO();
			
			bdto.setNum(rs.getInt("num"));
			bdto.setTitle(rs.getString("title"));
			bdto.setContent(rs.getString("content"));
			bdto.setUser_ID(rs.getString("user_ID"));
			bdto.setViewCnt(rs.getInt("viewcnt"));
			bdto.setRegDate(rs.getObject("regdate", LocalDateTime.class));
			
			
			boardList.add(bdto);
			
			
			}
			
			System.out.println("DAO: 글 정보 저장 완료" +boardList.size());
			
			} 
		finally {
			close();
		}
		return boardList;
	}

// getboardlist 수정중
	
	public int update(int num, String title, String content, String user_ID) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_BOARD_UPDATE);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, user_ID);
			pstmt.setInt(4, num);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}
			
		return cnt;
	}
	
	public int deleteByNum(int num) throws SQLException {
		int cnt = 0;
			
		try {
			pstmt = conn.prepareStatement(D.SQL_BOARD_DELETE);
			pstmt.setInt(1, num);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}
		
		return cnt;
	}
	
}