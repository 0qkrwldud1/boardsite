package com.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.boardDAO;
import com.beans.boardDTO;

public class UpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		/*
		int cnt = 0;

		int num = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
			content = content.replaceAll("\r\n","<br>"); // 엔터키 db에 <br>태그로 변환해서 저장
		String user_ID = request.getParameter("user_ID");

		if ((title != null && title.trim().length() > 0) ) {
			try {
				cnt = new boardDAO().update(num, title, content, user_ID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("result", cnt);
		
		*/

		// 문자열 형식의 게시글 번호를 int 형으로 변환하는 작업. parse
		int num = Integer.parseInt(request.getParameter("num"));
		//int pageNum = Integer.parseInt(request.getParameter("pageNum"));

		// dao 디비 연결를 위한 객체 및 다수의 디비 연결 메소드.
		boardDAO dao = boardDAO.getInstance();

		// 임시로 해당 게시글을 담은 객체(DTO)
		boardDTO dto = new boardDTO();

		dto.setNum(num);
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setUser_ID(request.getParameter("user_ID"));

		// 날짜 형식 지정하는 포맷을 잘 정리.
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String regdate = formatter.format(new java.util.Date());

		// 게시글을 수정시 조회수를 0으로 초기화함.
		
		dto.setRegDate(regdate);
		

		dao.update(dto);
		
	}
}
