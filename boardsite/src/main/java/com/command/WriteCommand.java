package com.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.boardDAO;
import com.beans.boardDTO;

public class WriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String user_ID = request.getParameter("user_ID");
		
		boardDTO dto = new boardDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setUser_ID(user_ID);
		
		if (title != null && title.trim().length() > 0) {
			try {
				cnt = new boardDAO().insert(dto);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("result", cnt);
		request.setAttribute("dto", dto); // auto-generated key (num)
	}

}