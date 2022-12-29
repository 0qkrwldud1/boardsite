package com.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.boardDAO;
import com.beans.boardDTO;

public class SelectCommand implements Command {

	// 수정시 불러오는 코맨드
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		List<boardDTO> boardList = null;

		try {
			boardList = new boardDAO().getByNum(num);
								// selectByNum(num); 바꾸기전 -> get
								// getboardList();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("boardList", boardList);
	}
}
