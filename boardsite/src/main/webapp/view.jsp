<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.beans.*"%>
<%@ page import="java.util.*"%>

<%
List<boardDTO> list = (List<boardDTO>) request.getAttribute("list");

if (list == null || list.size() == 0) {
%>
<script>
	
	alert("해당 정보가 삭제되었거나 존재하지 않습니다.");
	history.back();
	
	
</script>
<%
	return;
}
%>

<%
	boardDTO dto = list.get(0);
	int num = dto.getNum();
	String title = dto.getTitle();
	String content = dto.getContent();
	String user_ID = dto.getUser_ID();
	int viewCnt = dto.getViewCnt();
	String regDate = dto.getRegDateTime(); 
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>view</title>
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

	<h1><%=title%></h1>
	<hr>
	<div class = "container">
	<div class = "ti_list">
	<strong>Title </strong> <%=title%><br>
	</div>
	<div class = "ti_list">
	<strong>ID </strong> <%=user_ID%><br>
	</div>
	<div class = "ti_list">
	<strong>View </strong> <%=viewCnt%><br>
	</div>
	<div class = "ti_list">
	<strong>Post</strong> <%=regDate%><br><br>
	</div>
	
	</div>
	
	<div class = "con">
	<strong>Content</strong><br>
	<%=content%>
	</div>
	<br><hr><br>
	<button class = "btn" type="button" onclick="chkDelete(<%=num%>)">Delete</button>
	<button class = "btn" type="button" onclick="location.href='list.do'">Board List</button>
	<button class = "btn" type="button" onclick="location.href='update.do?num=<%=num%>'">Update</button>
	<button class = "btn" type="button" onclick="location.href='write.do'">New Post</button>
    
    <script>
    function chkDelete(num) {
    	let r = confirm("삭제하시겠습니까?");
        if (r) {
        	location.href = "deleteOk.do?num=" + num;
        }
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