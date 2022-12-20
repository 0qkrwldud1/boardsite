<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>write</title>
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
	
	
	<hr>
	
	<form name="frm" action="writeOk.do" method="post" onsubmit="return chkSubmit()">
		<div id = "write_main">
			<div class = "container">
				<div class = "ti_list">
				Title <input class = "title" type="text" name="title"/><br>
				</div>
			
				<div class = "ti_list">
				ID <input type="text" name="user_ID"/><br><br>
				</div>
			</div>
		
			
			<div class = "con">
			
				<textarea cols="150" rows="20"  name="content"></textarea>
				<br><br>
			</div>
		
		</div>
		<hr>
		<div class = "wir_btn">
			<span>
				<input class = "btn" type="submit" value="Publish"/>
			
			</span>
			<span>
				<button class = "btn" type="button" onclick="location.href='list.do'">Board List</button>
			</span>
		</div>
	</form>
	
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
			
			else if (user_ID == '') {
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
            <a href="https://band.us/band/89389707?referrer=" target="_blank">band </a> ㅣ
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