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

public class boardDAO_ori {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public boardDAO_ori() { 
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
				String category = rs.getString("category");
				int viewCnt = rs.getInt("viewcnt");
				String regDate = rs.getString("regdate");
	
				boardDTO dto = new boardDTO(num, title, content, user_ID, category,  viewCnt, regDate);
				list.add(dto);

			}
			return list;
	}

	public List<boardDTO> select() throws SQLException {
			List<boardDTO> list = null;
			
			// 보드 테이블에서 레코드 가져오기

		try {
				pstmt = conn.prepareStatement(D.SQL_BOARD_GET);
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
			
			pstmt = conn.prepareStatement(D.SQL_BOARD_GET_BY_NUM);
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
	
	public List<boardDTO> selectByNum(int num) throws SQLException {
		List<boardDTO> list = null;
			
		try {
			pstmt = conn.prepareStatement(D.SQL_BOARD_GET_BY_NUM);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			list = buildList(rs);
		} finally {
			close();
		}
			
		return list;
	}
	
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