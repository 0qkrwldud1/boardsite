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
	
	public int getboardCount(String items, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;

		String sql;
		
		if (items == null && text == null)
			sql = "select  count(*) from board";
		else
			sql = "select   count(*) from board where " + items + " like '%" + text + "%'";
		
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) 
				x = rs.getInt(1);
			
		} catch (Exception ex) {
			System.out.println("getboardCount() 에러 : " + ex);
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
		return x;
	}
	// db에 있는 글 개수 확인하는 메서드 
	
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

	public List<boardDTO> select() throws SQLException {
			List<boardDTO> list = null;
			
			// 보드 테이블에서 레코드 가져오기

		try {
				pstmt = conn.prepareStatement(D.SQL_BOARD_SELECT);
				rs = pstmt.executeQuery();
				list = buildList(rs);
		} finally {
				close();
		}
		return list;
	}
	
	
	
	public int insert(boardDTO dto) throws SQLException {
		int cnt = 0;
		
		
		String title = dto.getTitle();
		String content = dto.getContent();
		String user_ID = dto.getUser_ID();
		
		
		int num;
		String[] generatedCols = {"bd_num"};

		try {
			pstmt = conn.prepareStatement(D.SQL_BOADR_INSERT, generatedCols);
		
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, user_ID);
			
			
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



	public List<boardDTO> readByNum(int num) throws SQLException {
		List<boardDTO> list = null;
		
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(D.SQL_BOARD_INC_VIEWCNT);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = conn.prepareStatement(D.SQL_BOARD_SELECT_BY_NUM);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			list = buildList(rs);
			
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			close();
		}	
	    
		return list;
	}
	
	// getboardlist랑 바꾸기 
	public List<boardDTO> selectByNum(int num) throws SQLException {
		List<boardDTO> list = null;
			
		try {
			pstmt = conn.prepareStatement(D.SQL_GET_BOARD_LIST);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			list = buildList(rs);
		} finally {
			close();
		}
			
		return list;
	}
	
// 페이징 처리하는 글 목록 만들기.
	public List getboardList(int startRow, int pageSize) throws SQLException {
		
		List boardList = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(D.SQL_GET_BOARD_LIST);
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
			
			} catch (SQLException e) {
				conn.rollback();
				throw e;
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