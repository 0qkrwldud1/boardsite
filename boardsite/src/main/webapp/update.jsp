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
	String regDate = dto.getRegDateTime(); 
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><%=title %> update</title>
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

	<h1><%=title %></h1>
	<hr>
	
	<form name="frm" action="updateOk.do" method="post" onsubmit="return chkSubmit()">
	
		<input type="hidden" name="num" value="<%=num %>"/>
		<div class = "container">
			<div class = "ti_list">
			<span>
			<strong>Title</strong> 
			</span>
			<span>
		    <input type="text" name="title" value="<%=title %>"/><br>
			</span>
			</div>
			<div class = "ti_list">
			<span>
			<strong>ID</strong>
			</span>
			<sapn>
			<input type="text" name="user_ID" value="<%=user_ID %>"/><br>
			</sapn>
			</div>
			<div class = "ti_list">
			<strong>Post</strong> <%=regDate %><br><br>
			</div>
		</div>
	<div class = "con">
		
		<textarea cols="100" rows="15" name="text"><%=content %></textarea>
		
	</div>	
	<input class = "btn" type="submit" value="수정하기"/>
	</form>
	
	<br><hr><br>
	<button class = "btn" onclick="histroy.back()">뒤로가기</button>
	<button class = "btn" onclick="location.href='list.do'">목록으로</button>
	
	<script>
	function chkSubmit() {
		frm = document.forms['frm'];

		let title = frm['title'].value.trim();
		let user_ID = frm['user_ID'].value.trim();

		if (title == '') {
			alert("제목은 반드시 입력해야 합니다");
			frm['title'].focus();
			return false;
		}
		
		if (user_ID == '') {
			alert("아이디는 반드시 입력해야 합니다.");
			frm['user_ID'].focus();
			return false;
		}

		return true;
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