<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.beans.*"%>
<%@ page import="java.util.*"%>
<%
List<boardDTO> list = (List<boardDTO>) request.getAttribute("list");
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
	<button class= "btn" onclick="location.href='write.do'">Publish</button>
   	
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