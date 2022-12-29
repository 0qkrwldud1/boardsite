<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.beans.*"%>
<%@ page import="java.util.*"%>
<%
List<boardDTO> boardList = (List<boardDTO>) request.getAttribute("boardList");
%>

<%
// 디비에서 전체 글목록을 읽어서 가져오기
// boardDAO 객체생성
boardDAO bdao = new boardDAO();

// 디비에 글이 있는지 확인 후 있으면 글 모두 가져오기,없으면 가져오지않기 : getboardCount()
int cnt = bdao.getboardCount();

// 페이징처리
// 한 페이지에서 보여줄 글의 개수 설정
int pageSize = 10;

// 지금 내가 몇페이지에 있는 확인
String pageNum = request.getParameter("pageNum");

// 페이지번호정보가 없을 경우 내가 보는 페이지가 첫페이지가 되도록
if (pageNum == null) {
		pageNum = "1";
}

// 시작행번호계산
// 10개씩 컬럼 나누고 2페이지에서 시작행이 11이되고 3페이지에서 시작행이 21이 되게끔 만들기
int currentPage = Integer.parseInt(pageNum); // String을 integer로 변환
int startRow = (currentPage - 1) * pageSize + 1;
//currentPage가 2인경우, (2-1)x10+1 = 11
//currentPage가 3인경우, (3-1)x10+1 = 21

// 끝행번호계산



int endRow= currentPage * pageSize; 
//currentPage가 2인경우, 2*10 = 20
//currentPage가 3인경우, 3*10 = 30

// 게시판 글의 수를 화면에 데이터 출력
// 게시판 총 글의 수 : cnt개

// getBoardList() 메서드생성

/*
System.out.println(bdao.getboardList());
boardList = null;

if(cnt != 0) {
	boardList = bdao.getboardList(startRow, pageSize);
}
*/

%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>list</title>
    <link rel="stylesheet" href="css/post.css" >
  </head>
  
  <body>
    
      <header>
        <div id="grid_header">
          <nav class="header_top" >
              <ul class="top_member_box">
                  <li><a href="signUp.jsp">Sign Up</a></li>
                  <li><a href="signIn.jsp">Sign In</a></li>
                  <li><a href="write.jsp">Post</a></li>
                  <li><a href="list.do">Board List</a></li>
                  <li><a href="main.jsp">Home</a></li>
                  <li><a href="signOutAction.jsp">Sign Out</a></li>
              </ul>
           </nav>
        </div>
          <div class="a">
            <span class="banddy"> banddy </span><span class="logo_text" style="font-style: italic; color: black; font-size: 15px;"> _ is a space for the group members.</span>
          </div>
     </header>
   		
   	<main>
   	
   	
    <hr><br>
	<div id = "list_main">
	<table>
		<tr>
			<th>no.</th>
			<th>Title</th>
			<th>User ID</th>
			<th>View</th>
			<th>Post</th>
		</tr>
		
		<%
		if (boardList != null) {
		for(boardDTO bdto : boardList){
			
			
		%>
		<tr>
			<td><%=bdto.getNum()%></td>
			<td class = "tdd"><a href="view.do?num=<%=bdto.getNum()%>&pageNum=<%=pageNum%>">
			<%=bdto.getTitle()%></a></td>
			<td><%=bdto.getUser_ID()%></td>
			<td><%=bdto.getViewCnt()%></td>
			<td><%=bdto.getRegDateTime()%></td>
		</tr>
		<%
			}
		}
		%>
	</table>
	<br>
	</div> 
	<div class = "btn_area">
	<button class= "btn" onclick="location.href='write.do'">Publish</button>
   	</div>
   	
   	
   	<div id = "page_control">
   		<% if(cnt != 0) { 
   			// cnt는 전체 글 갯수
   			// 페이지갯수처리
   			//전체 페이지 수 계산
   			int pageCount = cnt / pageSize + (cnt%pageSize == 0? 0: 1);
   			
   			//화면에 보여줄 페이지번호의 갯수 (페이지블럭)
   			int pageBlock = 3; //페이지에 10개 페이지갯수 보여줌
   			
   			//한 페이지에 보여줄 페이지 블럭 시작번호 계산
   			int startPage = ((currentPage-1)/ pageBlock) * pageBlock + 1;
   			
   			//한 페이지에 보여줄 페이지 블럭 끝 번호 계산
   			int endPage = startPage + pageBlock - 1;
   			if(endPage > pageCount) {  
   					endPage = pageCount;
   		 	} 
   		// 이전 페이지
   		%>
   		
   		<% if (startPage > pageBlock) { %>
   				
   				<a href = "list.do?pageNum=<%=startPage-pageBlock%>"> prev </a>
   				
	   <% } %>
   		
	   <% 	 for (int i=startPage; i<= endPage; i++) { %>
	   		 	
	   			<a href = "list.do?pageNum=<%=i %>"> <%=i %> </a>
	   			
	  	<% } %>
	   	
	  	<% 	 if (endPage<pageCount) { %>
	  		 	
	   			<a href = "list.do?pageNum=<%=startPage+pageBlock %>"> next </a>
	   	<% } %>
	   	
	
	<%
	} 
	
	%>
	   	
		</div>   	
	   	
	   	
	   	
   
   	</main>		
   		
     <footer>
        <div id="foot">
          <nav>
            <a href="https://band.us/band/89389707?referrer=" target="_blank">band </a> |
            <a href="https://github.com/0qkrwldud1/web_wldud.git" target="_blank">github</a> 
          </nav>
          <p>
            <span>박지영</span><br/>
            <span>e-mail : 0qkrwldud1@naver.com</span><br/>
            <span>© 2022 Copyright : 0qkrwldud1. All Rights Reserved.</span><br/>
          </p>
        </div>
      </footer>
    
  </body>
  
  </html>