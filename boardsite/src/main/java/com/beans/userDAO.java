package com.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.D;

public class userDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public userDAO() {
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String user_ID, String user_PW) {
		String sql = "select user_PW from user where user_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
			if(rs.getString(1).equals(user_PW)) {
					return 1; //로그인 성공
			} else 
					return 0; //비밀번호 없음
			}
			return -1; //아이디 없음
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //오류
	}
	
	public int join(userDTO userDTO) {
		String sql = "insert into user values(?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDTO.getUser_ID());
			pstmt.setString(2, userDTO.getUser_PW());
			pstmt.setString(3, userDTO.getUser_Name());
			pstmt.setString(4, userDTO.getUser_Gender());
			pstmt.setString(5, userDTO.getUser_Email());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}return -1;
	}
	
	
	
	
	
	
	
}
