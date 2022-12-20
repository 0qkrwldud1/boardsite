package com.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class boardDTO {
	private int num; // bd_num
	private String title; // bd_title
	private String content; // bd_content
	private String user_ID; // user_ID
	private int viewCnt; // bd_viewcnt
	private LocalDateTime regDate; // bd_regdate
	//private int available; //bd_available
	
	public boardDTO( ) {
		super();
	}

	

	public boardDTO(int num, String title, String content, String user_ID, int viewCnt, LocalDateTime regDate) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.user_ID = user_ID;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
		//this.available = available;
	}
//
//	public boardDTO(int num, String title, String content, String user_ID, int viewCnt, LocalDateTime regDate) {
//		super();
//		this.num = num;
//		this.title = title;
//		this.content = content;
//		this.user_ID = user_ID;
//		this.viewCnt = viewCnt;
//		this.regDate = regDate;
//	}
	
//	public int getAvailable() {
//			return available;
//		}
//	
//		public void setAvailable(int available) {
//			this.available = available;
//		}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	
	public String getRegDateTime() {
		if (this.regDate == null) return "";
		return this.regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
	}
	
	
}
