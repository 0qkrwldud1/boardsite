package com.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class boardDTO {
	private int num; // bd_num
	private String title; // bd_title
	private String content; // bd_content
	private String user_ID; // user_ID
	private String filename; // bd_filename
	private int viewCnt; // bd_viewcnt
	private LocalDateTime regDate; // bd_regdate
	
	
	public boardDTO( ) {
		super();
	}

	

	public boardDTO(int num, String title, String content, String user_ID, String filename, int viewCnt, LocalDateTime regDate) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.user_ID = user_ID;
		this.filename = filename;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
		
	}

	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



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
