package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.Command;
import com.command.DeleteCommand;
import com.command.ListCommand;
import com.command.SelectCommand;
import com.command.UpdateCommand;
import com.command.ViewCommand;
import com.command.WriteCommand;


@WebServlet("*.do")
public class boardcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public boardcontroller() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response); 
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Command command = null;	// 어떠한 로직을 수행할지
		String viewPage = null;	// 어떠한 페이지를 보여줄지
		
		// URL로부터 command 분리
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		switch(com) {
		case "/list.do":
			command = new ListCommand();
			command.execute(request, response);
			viewPage = "list.jsp";
			break;
		case "/view.do":
			command = new ViewCommand();
			command.execute(request, response);
			viewPage = "view.jsp";
			break;
		case "/write.do":
			viewPage = "write.jsp";
			break;
		case "/writeOk.do":
			command =  new WriteCommand();
			command.execute(request, response);
			viewPage = "writeOk.jsp";
			break;
		case "/update.do":
			command = new SelectCommand();
			command.execute(request, response);
			viewPage = "update.jsp";
			break;
		case "/updateOk.do":
			command = new UpdateCommand();
			command.execute(request, response);
			viewPage = "updateOk.jsp";
			break;
		case "/deleteOk.do":
			command = new DeleteCommand();
			command.execute(request, response);
			viewPage = "deleteOk.jsp";
			break;
		}
		
		// view page로 forward
		if (viewPage != null) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response); // "/WEB-INF/views/board/"
		}
	}

}


