package com.command;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.boardDAO;
import com.beans.boardDTO;

public class ViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<boardDTO> boardList = null;

		int num = Integer.parseInt(request.getParameter("num"));

		try {
			boardList = new boardDAO().readByNum(num);
			request.setAttribute("boardList", boardList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}