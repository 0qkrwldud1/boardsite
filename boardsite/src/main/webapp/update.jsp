<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<%@ page import="com.beans.*"%>
<%@ page import="java.util.*"%>

<%
List<boardDTO> list = (List<boardDTO>) request.getAttribute("list");
%>
<%
	boardDTO board = list.get(0);
	int num = board.getNum();
	String title = board.getTitle();
	String content = board.getContent();
	String user_ID = board.getUser_ID();
	int viewCnt = board.getViewCnt();
	String regDate = board.getRegDate(); 
	String category =board.getCategory();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><%=title %> update</title>
<link rel="stylesheet" href="css/post.css" >
<script src="js/upload.js"></script> 
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
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
     enctype="multipart/form-data"
     -->
     

	<h1><%=title %></h1>
	<hr>
	
	<form name="frm" action="updateOk.do" method="post" 
		onsubmit="return chkSubmit()" >
	
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
				<span> <!-- ID??? ??????x -->
					<input type="text" name="user_ID" value="<%=user_ID %>" readOnly/><br>
				</span>
				</div>
				<div class = "ti_list">
				<strong>Post</strong> <%=regDate %><br><br>
				</div>
				<div class = "ti_list">
				<strong>category</strong> <%=category%><br><br>
				</div>
			</div>
			
		<div>
			<textarea id= "con"  name="content"><%=content %></textarea>
			<!-- name??? ????????? ????????? ????????? ??????x -> content??? ??????. -->	
			
			<div class="filebox">
			    <input class="upload-name" value="????????????" placeholder="filename">
			   	<label for="file">Upload</label> 
			    <!-- <input type="file" id="file" > ????????? -->
			    <!-- ???????????? ??? ?????? ????????? ?????? -->
			    <input type="file" id="uploads" value="?????? ??????" onClick="addFile()"
			    	name= "fileName" required = false />
			    
			    <input class = "up_btn" type="submit" value="Update"/>
			    
			    <script> $("#file").on('change',function(){
				   var fileName = $("#file").val();
				   $(".upload-name").val(fileName);
				 });
			    </script>
			    
			</div>
		</div>	
		
	</form>
	
	<br><hr><br>
	
	<div class = "btn_area">
		<button class = "btn" onclick="histroy.back()">Back</button>
		<button class = "btn" onclick="location.href='list.do'">Board List</button>
	</div>
	
	<script>
	function chkSubmit() {
		frm = document.forms['frm'];

		let title = frm['title'].value.trim();
		let user_ID = frm['user_ID'].value.trim();

		if (title == '') {
			alert("????????? ????????? ???????????? ?????????");
			frm['title'].focus();
			return false;
		}
		
		if (user_ID == '') {
			alert("???????????? ????????? ???????????? ?????????.");
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
            <span>?????????</span><br/>
            <span>e-mail : 0qkrwldud1@naver.com</span><br/>
            <span>?? 2022 Copyright : 0qkrwldud1. All Rights Reserved.</span><br/>
          </p>
        </div>
      </footer>
	
</body>
</html>