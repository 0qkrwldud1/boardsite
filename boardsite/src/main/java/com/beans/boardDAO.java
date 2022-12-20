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
	
	private List<boardDTO> buildList(ResultSet rs) throws SQLException {
			List<boardDTO> list = new ArrayList<>();
			
//			String sql = "select * from board bd_num < ? and available = 1 order by bd_num"
//					+ "desc limit 10 ";
//			try {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setInt(1, getNext() - (pageNumber -1)*10);
//				rs = pstmt.executeQuery();
//			
			while (rs.next()) {
//				boardDTO dto = new boardDTO();
//				dto.setNum(rs.getInt(1));
//				dto.setTitle(rs.getString(2));
//				dto.setUser_ID(rs.getString(3));
//				dto.setContent(rs.getString(4));
//				dto.setViewCnt(rs.getInt(5));
//				dto.setAvailable(rs.getInt(6));
//			}
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

	
//	private String getDate() {
//		String sql = "select now()";
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				return rs.getString(1);
//			}
//		} 	catch (Exception e) {
//			e.printStackTrace();
//		}
//			return null;
//	}
//
//	private int getNext() {
//		String sql = "select user_ID from board order by user_ID desc";
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//					rs = pstmt.executeQuery();
//		if (rs.next()) {
//			return rs.getInt(1) + 1;
//		}
//			return 1;
//		} 	catch (Exception e) {
//			e.printStackTrace();
//	
//		}
//			return -1;
//	}

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
	
	public List<boardDTO> selectByNum(int num) throws SQLException {
		List<boardDTO> list = null;
			
		try {
			pstmt = conn.prepareStatement(D.SQL_BOARD_SELECT_BY_NUM);
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