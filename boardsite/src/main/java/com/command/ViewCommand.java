package com.command;


import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.boardDAO;
import com.beans.boardDTO;

public class ViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<boardDTO> list = null;

		int num = Integer.parseInt(request.getParameter("num"));

		try {
			list = new boardDAO().readByNum(num);
			request.setAttribute("list", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}