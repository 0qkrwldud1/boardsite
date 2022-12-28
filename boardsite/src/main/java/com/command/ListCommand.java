package com.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.boardDAO;
import com.beans.boardDTO;

public class ListCommand implements Command {
	
	// 리스트를 만듦 -> d에 select를 사용.

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			List<boardDTO> boardList = null;
			try {
				boardList = new boardDAO().select();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			request.setAttribute("boardList", boardList);
		}	
	
	}
