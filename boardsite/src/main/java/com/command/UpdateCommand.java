package com.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.boardDAO;

public class UpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;

		int num = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String user_ID = request.getParameter("user_ID");

		if ((title != null && title.trim().length() > 0) ) {
			try {
				cnt = new boardDAO().update(num, title, content, user_ID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("result", cnt);
	}
}
