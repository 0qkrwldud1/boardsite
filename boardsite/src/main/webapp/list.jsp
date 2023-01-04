<%@ page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.beans.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

List<boardDTO> list = (List<boardDTO>) request.getAttribute("list");
String user_ID = (String) session.getAttribute("user_ID");
int total_record = ((Integer) request.getAttribute("total_record")).intValue();
int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
int total_page = ((Integer) request.getAttribute("total_page")).intValue();
int LISTCOUNT = ((Integer) request.getAttribute("LISTCOUNT")).intValue();

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
    <jsp:include page="menu_post.jsp" />
    <!--   
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
   	-->	
   	<main>
   	
   	
    <hr><br>
    
    <div class = "total">
		<button class= "btn" ><b>total <%=total_record%></b></button>
   	</div>
   
	<div id = "list_main">
	
	<table>
		<tr>
			<th>no.</th>
			<th>Title</th>
			<th>User ID</th>
			<th>View</th>
			<th>Post</th>
		</tr>
		<!--  for(boardDTO bdto : boardList){ 
			  boardDTO board = (boardDTO) list.get(j);
		-->
		
		<%
		if (list != null) {
			for (boardDTO board : list) {
				
		%>
		<tr>
			<td><%=board.getNum()%></td>
			<td class = "tdd"><a href="view.do?num=<%=board.getNum()%>&pageNum=<%=pageNum%>">
			<%=board.getTitle()%></a></td>
			<td><%=board.getUser_ID()%></td>
			<td><%=board.getViewCnt()%></td>
			<td><%=board.getRegDateTime()%></td>
			
		</tr>
		<%
			}
			}			
		%>
	</table>
	<br>
	</div> 
	
	<div class = "list_container">
		<div class = "search_area">
			<form action="<c:url value="./list.do"/>" method="post">
 				<table id = "search_table">
					<tr id = "search_table">
						<td id = "search_table">&nbsp;&nbsp; 
							<select name="items" class="txt" >
								<option value="bd_title">Title</option>
								<option value="bd_content">Content</option>
								<option value="user_ID">User ID</option>
							</select> 
							<input name="text" type="text" id = "search_input"/> 
							<input type="submit" class= "btn" value="Search " />
						</td>	
					</tr>
				</table>
			</form>
	   		</div>
	
			<div class = "btn_area">
				<button class= "btn" onclick="location.href='write.do'" 
					onclick="checkForm(); return false;">Publish</button>
		   	</div>
   		
	  </div>
   	
   	<div align="center">
		<c:set var="pageNum" value="<%=pageNum%>" />
			<c:forEach var="i" begin="1" end="<%=total_page%>">
				<a href="<c:url value="./list.do?pageNum=${i}" /> ">
					<c:choose>
						<c:when test="${pageNum==i}">
							<font color='white'><b> [${i}]</b></font>
						</c:when>
						<c:otherwise>
							<font color='white'> [${i}]</font>
						</c:otherwise>
					</c:choose>
				</a>
			</c:forEach>
	</div>
			
	   	  
   
   	</main>		
   
   	<script >
		function checkForm() {	
			if (${user_ID==null}) {
				alert("로그인 해주세요.");
				return false;
			}
	
			location.href = "./BoardWriteForm.do?id=<%=user_ID%>"
		}
	</script>

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