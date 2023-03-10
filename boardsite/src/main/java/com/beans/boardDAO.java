package com.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.D;

public class boardDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private static boardDAO instance;
	
	public static boardDAO getInstance() {
		if (instance == null)
			instance = new boardDAO();
		return instance;
	}
	
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
	
	// 총 게시글 수 카운트 
	// 매개변수: items, text
	public int getboardCount(String items, String text) {
		

		int cnt = 0;
		
		String sql;
		
		if (items == null && text == null) 
			sql = "select  count(*) from board";
		
		else if (text == null)
			sql = "select count(*) from board where bd_category = '" + items + "' order by bd_num; ";
	
		else 
			sql =  "select   count(*) from board where " + items + " like '%" + text + "%'";
		try {	
	
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();

			if (rs.next()) 
				cnt = rs.getInt(1);
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {			
			try {				
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();												
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}		
		return cnt;
	}
	
	/*
	 * 리스트만 만드는 메서드
	 * 매개변수 ResultSet rs
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
	
		//board  테이블의 레코드 리스트 가져오기 -> 페이징처리
		public ArrayList<boardDTO> getboardList(int page, int limit, 
				String items, String text)  {
			
			// page-> 현재 페이지
			// limit -> 초기에 설정한 현재 페이지에 불러올 갯수.
			// db에서 불러올 게시글의 총 개수.
			int total_record = getboardCount(items, text );
			// start = (1-1)*5 = 0
			int start = (page - 1) * limit;
			// index = 1
			int index = start + 1;

			String sql;

			if (items == null && text == null)
				sql = 	" SELECT  * FROM board ORDER BY bd_num DESC ";
			
				// 보드 테이블을 num 기준으로 내림차순으로 정렬.
			else if (text == null)
				sql = "select * from board where bd_category = '" + items + "' order by bd_num; ";
			
			else
				sql = "SELECT  * FROM board where " + items + " like '%" + text + "%' ORDER BY bd_num DESC ";
				// 보드 테이블을 조건내에서 text에 검색한 글자를 가진 text가 있는 칼럼을 내림차순으로 정렬.
				
			//arraylist를 만듦.
			ArrayList<boardDTO> list = new ArrayList<boardDTO>();

			try {
				Class.forName(D.DRIVER);
				conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				rs = pstmt.executeQuery();
				
				while (rs.absolute(index)) {
					boardDTO board = new boardDTO();
					board.setNum(rs.getInt("bd_num"));
					board.setTitle(rs.getString("bd_title"));
					board.setContent(rs.getString("bd_content"));
					board.setUser_ID(rs.getString("user_ID"));
					board.setViewCnt(rs.getInt("bd_viewcnt"));
					board.setRegDate(rs.getString("bd_regdate"));
					list.add(board);

					// 인덱스 = 1, 5보다 작고 동시에 인덱스가 게시글의 총 개수보다 작거나 같다면,
					if (index < (start + limit) && index <= total_record)
						// 인덱스가 1씩 증가;
						index++;
					else
						break;
				}
				return list;
			} catch (Exception ex) {
				System.out.println("getBoardList() 에러 : " + ex);
			} finally {
				try {
					if (rs != null) 
						rs.close();							
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}			
			}
			return null;
		}
		
		// 보드 레코드 가져오기
		public List<boardDTO> get() throws SQLException {
			List<boardDTO> list = null;			
		

		try {
				pstmt = conn.prepareStatement(D.SQL_BOARD_GET_LIST);
				rs = pstmt.executeQuery();
				list = getboardList();
		} finally {
				close();
		}
		return list;
		}	// 데이터베이스에서 가져온 ResultSet에 담긴 정보들을 BookDTO 타입인 List로 저장하는 메서드.
	
	
	// 글 쓰기 디비에 insert
	//boardDTO 타입 매개변수 dto
	public int insert(boardDTO dto) throws SQLException {
		int cnt = 0;
		
		// 사용자가 입력한 값인 title, content, user_ID, filename 값을 가져온다.
		String title = dto.getTitle();
		String content = dto.getContent();
		String user_ID = dto.getUser_ID();
		
		
		int num;
		String[] generatedCols = {"bd_num"};
		
		// auto_incremenet 값인 num 값을 dto 객체에 담는다.
		// num 값은 auto_increment 값이므로 prepareStatement() 메서드 사용 시 매개변수를 두 개로 준다.
		
		try {
			pstmt = conn.prepareStatement(D.SQL_BOADR_INSERT, generatedCols);
		
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, user_ID);
			
			
			cnt = pstmt.executeUpdate();
			
			// cnt가 보다 크면 
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
	
		// board insert -> 
		// cnt 안쓰고 글등록 
		public void insertBoard(boardDTO dto)  {
	
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName(D.DRIVER);
				conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);		
				conn.setAutoCommit( false );
				String sql = "insert into board values(?, ?, ?, ?, ?, ?, ?)";
			
				//동적 쿼리에 쿼리 담기.
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				
				// 동적 쿼리 객체를 이용해서, 해당 디비에 각 항목의 값을 넣는 과정. 
				pstmt.setInt(1, dto.getNum());
				pstmt.setString(2, dto.getTitle());
				pstmt.setString(3, dto.getContent());
				pstmt.setString(4, dto.getUser_ID());
				pstmt.setString(5, dto.getCategory());
				pstmt.setInt(6, dto.getViewCnt());
				pstmt.setString(7, dto.getRegDate());
		
				
				// 해당 담은 동적 쿼리 객체를 실행하는 메서드. 
				// executeUpdate() 호출해서 디비에 저장. 
				pstmt.executeUpdate();
				conn.commit();
				conn.setAutoCommit(true);
			} catch (Exception ex) {
				System.out.println("insertBoard() 에러 : " + ex);
			} finally {
				try {
					// 역순으로 디비에 연결할 때 사용 했던 객체를 반납함. 
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}		
				}		
			} 
	
		//이미지를 등록하는 메서드. 매개변수: 파일 이미지들 객체를 담은 컬렉션이 필요함. 
		public void insertImage(ArrayList<FimageDTO> fileLists)  {

			// FimageDTO 타입의 ArrayList : fileLists
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			
			try {
				Class.forName(D.DRIVER);
				conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
				
				for(int i=0; i<fileLists.size(); i++) {
					FimageDTO fimageDTO = fileLists.get(i);

					String sql = "insert into board_images values(?, ?, ?, ?)";
					
					//동적 쿼리에 쿼리 담기.
					pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					
					// 동적 쿼리 객체를 이용해서, 해당 디비에 각 항목의 값을 넣는 과정. 
					//fnum , fileName, regDate, num
					// 해당 멀티 이미지를 반복문으로 여러개를 입력 하는 로직 필요.
					
					//getFnum() 자동으로 숫자 증가.
					pstmt.setInt(1, fimageDTO.getFnum());
					
					// 반복문으로 해당 목록에 들어가 있는 파일이름을 하나씩 가져올 계획. 
					pstmt.setString(2, fimageDTO.getFileName());
					
					// 등록하는 날짜 형식은 시스템 날짜및 시간을 사용할 예정. 
					pstmt.setString(3, fimageDTO.getRegDate());
					
					// 부모 게시글을 입력할 예정. 
					pstmt.setInt(4, fimageDTO.getNum());

					// 해당 담은 동적 쿼리 객체를 실행하는 메서드. 
					// executeUpdate() 호출해서 디비에 저장. 
					pstmt.executeUpdate();
				}
				
				} catch (Exception ex) {
					System.out.println("insertImage() 에러: " + ex);
				} finally {
				try {
					// 역순으로 디비에 연결할 때 사용 했던 객체를 반납함. 
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
					} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
					}		
				}		
			} 	
		
		// 상세글에서 해당 이미지 불러오는 메서드.	
		public ArrayList<FimageDTO> getBoardImageByNum(int num) {
				
				// 디비 연결를 위한 세트.
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				ArrayList<FimageDTO> fileLists = new ArrayList<FimageDTO>();				
				// 해당 게시글 번호로 다시 디비에서 조회하는 작업. 
				String sql = "select * from board_images where bd_num = ? ";

				try {
					// 임시 담을 객체. 게시글 번호로 인한 하나의 게시글을 담을 임시 객체.
					
				
					Class.forName(D.DRIVER);
					conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					
					// 선택된 게시글 번호로 조회된 이미지 여러개를 가져와서. 
					// 임시 객체에 역할로 담는 작업. 
					rs = pstmt.executeQuery();

					while (rs.next()) {
						FimageDTO fileDTO = new FimageDTO();
						fileDTO.setFnum(rs.getInt("fnum"));
						fileDTO.setFileName(rs.getString("fileName"));
						fileDTO.setRegDate("regDate");
						fileDTO.setNum(rs.getInt("bd_num"));
						
						fileLists.add(fileDTO);
					}
					System.out.println("fileLists의 갯수 : " + fileLists.size());
					return fileLists;
				
					} catch (Exception ex) {
						System.out.println("getBoardByNum() 예외 : " + ex);
					} finally {
						try {
						
						if (rs != null) 
							rs.close();							
						if (pstmt != null) 
							pstmt.close();				
						if (conn != null) 
							conn.close();
					} catch (Exception ex) {
						throw new RuntimeException(ex.getMessage());
					}		
				}
					return null;
				}
		
		public boardDTO getBoardByNum(int num, int page) {
			
			// 디비 연결를 위한 세트.
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
					
			// 임시 담을 객체. 게시글 번호로 인한 하나의 게시글을 담을 임시 객체.
			boardDTO dto = null;

			// 해당 게시글 클릭시, 조회수 증가하는 메서드. 
			updateHit(num);
			// 해당 게시글 번호로 다시 디비에서 조회하는 작업. 
			String sql = "select * from board where bd_num = ? ";

			try {
				Class.forName(D.DRIVER);
				conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				// 선택된 게시글 번호로 조회된 게시글 하나를 가져와서. 
				// 임시 객체에 역으로 담는 작업. 
				rs = pstmt.executeQuery();

				if (rs.next()) {
					dto = new boardDTO();
					dto.setNum(rs.getInt("bd_num"));
					dto.setTitle(rs.getString("bd_title"));
					dto.setContent(rs.getString("bd_content"));
					dto.setUser_ID(rs.getString("user_ID"));
					dto.setCategory(rs.getString("bd_category"));
					dto.setViewCnt(rs.getInt("bd_viewcnt"));
					dto.setRegDate(rs.getString("bd_regdate"));
				}
				
				return dto;
				} catch (Exception ex) {
				System.out.println("getBoardByNum() 에러 : " + ex);
				} finally {
					try {
					if (rs != null) 
						rs.close();							
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}		
			}
				return null;
			}
		
		//해당 게시글을 조회 했을 경우, 조회수를 증가하는 메서드. 
		public void updateHit(int num) {

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				Class.forName(D.DRIVER);
				conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);

				// 해당 게시글의 번호에 해당하는 히트(조회수) 조회.
				String sql = "select bd_viewcnt from board where bd_num = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				// 기본값.
				int viewcnt = 0;

				//해당 결과값(디비에서 불러온 값)에서 해당 히트의 값을 불러와서 + 1 증가.
				if (rs.next())
					viewcnt = rs.getInt("bd_viewcnt") + 1;
			
				// 조회된 조회수 숫자에 1카운트한 값을 다시 디비에 업데이트 수정하는 작업. 
				sql = "update board set bd_viewcnt=? where bd_num=?";
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);		
				pstmt.setInt(1, viewcnt);
				pstmt.setInt(2, num);
				
				pstmt.executeUpdate();
				
				} catch (Exception ex) {
					System.out.println("updateHit() 에러 : " + ex);
				} finally {
					try { // 디비연결에 사용했던 자원 반납. 역순. 
					if (rs != null) 
						rs.close();							
					if (pstmt != null) 
						pstmt.close();				
					if (conn != null) 
						conn.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}			
			}
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
		List<boardDTO> list = null;
			
		try {
			pstmt = conn.prepareStatement(D.SQL_BOARD_GET_BY_NUM);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			list = getboardList();
		} finally {
			close();
		}
			
		return list;
	}
	
	// 매개변수없는 리스트 만드는 메서드
	public List<boardDTO> getboardList() throws SQLException{
		//가변길이 배열 생성
		List<boardDTO> list = new ArrayList<>(); 

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
				bdto.setRegDate(rs.getString("regdate"));
				// 여기까지가 한 행의 데이터를 저장한 것임. while로 모든 행을 반복한다.
				
				//가변배열(ArrayList)에 위의 데이터 저장
				//즉 배열 한칸에 회원 1명의 정보를 저장함.
				list.add(bdto); //업캐스팅
			}
		
		
		
		/*
		 * finally { close(); }
		 */
		
		return list;
	}
	
	// x
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
					bdto.setRegDate(rs.getString("regdate"));
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
	
	// x
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
			bdto.setRegDate(rs.getString("regdate"));
			
			
			boardList.add(bdto);
			
			
			}
			
			System.out.println("DAO: 글 정보 저장 완료" +boardList.size());
			
			} 
		finally {
			close();
		}
		return boardList;
	}	// getboardlist 수정중
	
	
	// 글 수정하기
	// 수정전 매개변수 -> int num, String title, String content, String user_ID
	public void update(boardDTO dto)  {
		// int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update board set bd_title=?, bd_content=?, user_ID=? where bd_num=?";
			
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			pstmt = conn.prepareStatement(sql);
			
			conn.setAutoCommit(false);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getUser_ID());
			pstmt.setInt(4, dto.getNum());
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (Exception ex) {
			System.out.println("updateBoard() 에러 : " + ex);
		} finally {
			try {										
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}
	} 
	
	// 글 삭제하기
	public void deleteByNum(int num)  {
		// int cnt = 0;
		
		String sql = "delete from board where bd_num=?";	
			
		try {
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
		
			pstmt.executeUpdate();
		
		} catch (Exception ex) {
			System.out.println("deleteBoard() 에러 : " + ex);
		
		} finally {
			try {										
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}
	}	
}
	
