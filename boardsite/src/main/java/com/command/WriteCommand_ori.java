package com.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.boardDAO;
import com.beans.boardDTO;

public class WriteCommand_ori implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
			
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		 content = content.replaceAll("\r\n","<br>");
		String user_ID = request.getParameter("user_ID");
		
		
		// write.jsp에서 입력한 title과 summary, price 값을 가져와 변수에 저장.
		// boardDTO 객체를 생성하여 가져온 값들을 dto에 set 합니다.
		// 만약 title 값이 null이 아닌 경우 boardDAO() 인스턴스의 insert() 메서드를 실행시키고 반환된 값을 cnt 변수에 저장.
		boardDTO dto = new boardDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setUser_ID(user_ID);
	
		if (title != null && title.trim().length() > 0) {
			try {
				cnt = new boardDAO().insert(dto);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("result", cnt);
		request.setAttribute("dto", dto); // auto-generated key (num)
	}	
	
}
		//cnt 값이 1이라면 데이터베이스에 데이터가 제대로 insert가 되었다는 뜻 
		// 또한 게시글이 올바르게 등록되었다면 해당 게시글을 view 하는 창으로 바로 넘어갈 것이므로 해당 글의 uid가 필요함. 
		// dto에 담긴 uid를 사용할 것이므로 dto도 attribute에 set해서 넘겨준다.
		
		
		

		
		

