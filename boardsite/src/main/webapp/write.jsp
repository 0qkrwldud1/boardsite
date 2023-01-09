<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.beans.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>write</title>
<link rel="stylesheet" href="css/post.css" >
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>
<script >
/* 파일첨부 추가하는 함수 */
	var cnt=1;

	function addFile(){

	$("#d_file").append("<br>"+"<input type='file' name='fileName" + cnt + "' />");
	cnt++;
}  
	
</script>
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
	<!-- 이미지 업로드 form에 추가 enctype="multipart/form-data" -->
	<hr>
	
	<form name="frm" action="writeOk.do" method="post" 
		onsubmit="return chkSubmit()" enctype="multipart/form-data">
		<div id = "write_main">
			<div class = "container">
				<div class = "ti_list">
				Title <input class = "title" type="text" name="title"/><br>
				</div>
			
				<div class = "ti_list">
				ID <input type="text" name="user_ID"/><br><br>
				</div>
			
				
				<div class= "dropdown">
			
					<select name="category" >
		                <option value="art">문화/예술</option>
		                <option value="pet">반려동물</option>
		                <option value="trip">여행/캠핑</option>
		                <option value="life">일상/이야기</option>
		                <option value="study">교육/공부</option>
		                <option value="meet">친목/모임</option>
		                <option value="sport">스포츠/레저</option>
		                <option value="IT">IT/컴퓨터</option>
		                <option value="job">취업/자격증</option>
		            </select>
			
				</div>
			</div>
			
			<div>
			
				<textarea id= "con" name="content" ></textarea>
				
			</div>
			
			<!-- input file -> css 적용 / 엔터시 onkeypress="onTestChange();" 
			 <input type="file" id="file" name="fileName" onClick="addFile()"/>
			-->
			<!-- 자바스크립트 함수를 이용해 버튼 클릭 시, 아래에 HTML append 기능으로 뷰에 추가하는 방법. 
			  <script>
			   		// 파일 첨부시 파일 이름을 보여줌.
			    	
			   		$("#uploads").on('change',function(){
			    	  var fileName = $("#uploads").val();
			    	  $(".upload-name").val(fileName);
			    	});
			    </script>-->
			
			
			<div class="filebox">
			    <input class="upload-name" value="첨부파일" placeholder="filename" >
			    <label for="uploads">Upload</label> 
			    <input type="file" id="uploads" value="파일 추가" onClick="addFile()" name= "fileName"/>
			   <!--  <div id="d_file"></div> onClick="addFile()"--> 
			    <script>
			   		// 파일 첨부시 파일 이름을 보여줌.
			    	
			   		$("#uploads").on('change',function(){
			    	  var fileName = $("#uploads").val();
			    	  $(".upload-name").val(fileName);
			    	});
			    </script>
			  
			</div>
			 
		</div>
		
			<!-- 파일 하나만 추가 
			<input type="file"  id="uploads" name="fileName" multiple onClick="addFile()" >
			-->
			<!-- 
				자바스크립트 함수를 이용해 버튼 클릭 시, 아래에 HTML append 기능으로 뷰에 추가하는 방법. 
				<input type="button" value="파일 추가" onClick="addFile()"/>
				<div id="d_file"></div>
			-->
			
			
			
			<!-- 파일 선택 화면에서 이미지를 여러개 선택하는 방법. -->
			<!-- 이미지 파일 추가: <input type="file"  id="uploads" name="uploads" multiple> -->
		
		<hr>
		<div class = "btn_area">
			<span>
				<button class = "btn" type="submit" value="Publish" 
				>Publish</button>
			
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
	
	//엔터키 눌렀을 때 화면에 적용
	//두칸씩 늘어나서 x
	
		function onTestChange() {
		    var key = window.event.keyCode;
	
		    // If the user has pressed enter
		    if (key === 13) {
		        document.getElementById("con").value = 
		        	document.getElementById("con").value + "\n";
		        return false;
		    }
		    else {
		        return true;
		    }
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