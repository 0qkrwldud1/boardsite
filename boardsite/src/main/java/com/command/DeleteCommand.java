package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.boardDAO;

public class DeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// int cnt = 0;
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		/*
		try {
				cnt = new boardDAO().deleteByNum(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", cnt);
		
		*/
		
		boardDAO dao = boardDAO.getInstance();
		
		dao.deleteByNum(num);
		
		request.setAttribute("num", num);
		request.setAttribute("page", pageNum);
		
		
	}	

}
