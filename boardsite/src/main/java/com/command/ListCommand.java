package com.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.boardDAO;
import com.beans.boardDTO;

public class ListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			List<boardDTO> list = null;
			try {
				list = new boardDAO().select();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			request.setAttribute("list", list);
		}	
	
	}
