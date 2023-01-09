package com.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.boardDAO;
import com.beans.boardDTO;

public class ListCommand_ori implements Command {
	
	// List<boardDTO>로 list 변수를 선언.
	// 데이터베이스에 저장된 데이터를 읽어와 boardlist에 저장.
	// 트랜잭션 전담은 DAO에서 하기로 했으므로 BookDAO 객체를 생성. 
	// 리스트만드는 코맨드
	// get() 메서드는 ListCommand 클래스를 만들 때와 똑같은 방법으로 만듦.
	// get() 메서드에서 데이터베이스에 저장된 모든 정보를 불러와 ArrayList에 저장하는 기능을 구현.

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			List<boardDTO> boardList = null;
			try {
				boardList =  new boardDAO().get();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			request.setAttribute("boardList", boardList);
		}	// boardList 인스턴스를 "boardList"라는 이름으로 request의 attribute에 담는다. 
			// list.jsp에서 request를 사용할 때 getAttribute() 메서드를 사용해 "list"라는 이름의 Object를 불러올 수 있다.
	}
