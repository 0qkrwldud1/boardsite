<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.beans.*"%>
<%@ page import="java.util.*"%>
<%
List<boardDTO> list = (List<boardDTO>) request.getAttribute("list");
%>
<%

String items = request.getParameter("items");
String text = request.getParameter("text");

boardDAO bdao = new boardDAO();

int cnt  = bdao.getboardCount(items, text);

int pageSize = 3;

String pageNum = request.getParameter("pageNum");

if (pageNum == null) {
		pageNum = "1";
}

int currentPage = Integer.parseInt(pageNum);
int startRow = (currentPage - 1) * pageSize + 1;
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
		if (list != null) {
			for (boardDTO dto : list) {
		%>
		<tr>
			<td><%=dto.getNum()%></td>
			<td class = "tdd"><a href="view.do?num=<%=dto.getNum()%>"><%=dto.getTitle()%></a></td>
			<td><%=dto.getUser_ID()%></td>
			<td><%=dto.getViewCnt()%></td>
			<td><%=dto.getRegDateTime()%></td>
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
   		<%
   		if(cnt != 0) {
   			// 페이징처리
   			//전체 페이지 수 계산
   			int pageCount = cnt / pageSize + (cnt%pageSize==0?0:1);
   			
   			//한 페이지에 보여줄 페이지 블럭
   			int pageBlock = 10;
   			
   			//한 페이지에 보여줄 페이지 블럭 시작번호 계산
   			int startPage = ((currentPage-1)/ pageBlock)*pageBlock+1;
   			
   			//한 페이지에 보여줄 페이지 블럭 끝 번호 계산
   			int endPage = startPage + pageBlock-1;
   			if(endPage > pageCount) {
   				endPage = pageCount;
   			}
   		
   		%>
   		
   		<%if (startPage > pageBlock) { %>
   			<a href = "list.jsp?pageNum=<%=startPage-pageBlock%>"> prev</a>
   			
	   	<%} %>
	   	
	   	<% for (int i =startPage; i<= endPage; i++) { %>
	   			<a href = "list.jsp?pageNum=<%=i %>"> <%=i %></a>
	   	<%} %>
	   	
	   	<% if (endPage<pageCount) { %>
	   			<a href = "list.jsp?pageNum=<%=startPage+pageBlock %>"> next</a>
	   			
	   	<%} %>
	   	
	   	<%} %>
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