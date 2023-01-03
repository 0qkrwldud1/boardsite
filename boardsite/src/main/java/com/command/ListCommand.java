package com.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.boardDAO;
import com.beans.boardDTO;
import com.controller.boardcontroller;

public class ListCommand implements Command {
	
	// List<boardDTO>로 list 변수를 선언.
	// 데이터베이스에 저장된 데이터를 읽어와 boardlist에 저장.
	// 트랜잭션 전담은 DAO에서 하기로 했으므로 BookDAO 객체를 생성. 
	// 리스트만드는 코맨드
	// get() 메서드는 ListCommand 클래스를 만들 때와 똑같은 방법으로 만듦.
	// get() 메서드에서 데이터베이스에 저장된 모든 정보를 불러와 ArrayList에 저장하는 기능을 구현.

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int LISTCOUNT = boardcontroller.LISTCOUNT;
		boardDAO dao = new boardDAO();
		List<boardDTO> list = new ArrayList<boardDTO>();
		
		int pageNum=1;
		int limit=LISTCOUNT;
		
		if(request.getParameter("pageNum")!=null)
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
				// 만약 request에 담겨진 페이지 정보가 널이 아니면
				// parseInt -> 해당문자열을 정수 값으로 변환해서 담는다.
		String items = request.getParameter("items");
				// items 검색할 때 선택할 조건 제목, 글쓴이, 글
		String text = request.getParameter("text");
				// 검색할 때 텍스트 사용.
				// 해당 게시글의 모든 갯수를 가져오는 역할.
				int total_record = dao.getboardCount(items, text);
				list = dao.getboardList(pageNum, limit, items, text);
				
				int total_page;
				
				// 11/5 -> 2.2
				// Math.floor -> 2
				// total_page는 3
				if (total_record % limit == 0){     
			     	total_page =total_record/limit;
			     	Math.floor(total_page);  
				}
				else{
				   total_page =total_record/limit;
				   double total_page_test =  Math.floor(total_page);
				   System.out.println("total_page_test의 값:"+ total_page_test);
				   Math.floor(total_page); 
				   total_page =  total_page + 1; 
				}			
				request.setAttribute("LISTCOUNT", LISTCOUNT);
		   		request.setAttribute("pageNum", pageNum);		  
		   		request.setAttribute("total_page", total_page);   
				request.setAttribute("total_record",total_record); 
				request.setAttribute("list", list);
			}
	}
