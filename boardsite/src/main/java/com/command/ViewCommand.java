package com.command;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.FimageDTO;
import com.beans.boardDAO;
import com.beans.boardDTO;

public class ViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//List<boardDTO> list = null;
		
		boardDAO dao = boardDAO.getInstance();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		// pageNum : 페이징 처리에서 해당 페이지 번호.
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		// 임시로 해당 게시글을 담은 객체(DTO)
		boardDTO dto = new boardDTO();
		
		// 이미지 처리할 임시 객체들.
		ArrayList<FimageDTO> fileLists = new ArrayList<FimageDTO>();
		
		
		// dao 에서 해당 글번호의 내용을 가져오는 메서드.
		// 이 메서드 안에 조회수를 증가하는 메서드가 포함되어 있다.
		dto = dao.getBoardByNum(num, pageNum);
		System.out.println("getBoardByNum 메서드 출력후" + dto);
		System.out.println("num : " + num);
		
		fileLists = dao.getBoardImageByNum(num);
		System.out.println("getBoardImageByNum 메서드 출력후" + fileLists);
		
		//콘솔확인.
		
			for (int i = 0; i < fileLists.size(); i++) { 
			 
			 FimageDTO ex = fileLists.get(i);
			 String ex2 = ex.getFileName(); 
			 System.out.println("ex2 밖에 반복문 테스트" + ex2); 
			 
		 }
		 

		// 내장객체에 , 선택된 하나의 게시글의 번호인 num
		// board : 하나의 선택된 게시글의 객체.
		request.setAttribute("num", num);
		request.setAttribute("page", pageNum);
		request.setAttribute("dto", dto);
		// 해당 뷰에서 파일 이미지들의 목록을 확인하기 위해서 설정. 
		request.setAttribute("fileLists", fileLists);
	}

		
	}
