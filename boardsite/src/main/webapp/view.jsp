<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.beans.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	boardDTO dto = (boardDTO) request.getAttribute("dto");
	
	// 해당 뷰에 작업 하기 위해서, 컨트롤러에서 설정한 파일 이미지들 전체를 담는 컬렉션을 가져오는 역할. 
	
	ArrayList<FimageDTO> fileLists = (ArrayList<FimageDTO>)request.getAttribute("fileLists");
	
	int num = ((Integer) request.getAttribute("num")).intValue();
	int nowpage = ((Integer) request.getAttribute("page")).intValue();
	
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>view</title>
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
	
	<h1><%=dto.getTitle()%></h1>
	<hr>
	<div class = "container">
	<div class = "ti_list">
	<strong>Title </strong> <%=dto.getTitle()%><br>
	</div>
	<div class = "ti_list">
	<strong>ID </strong> <%=dto.getUser_ID()%><br>
	</div>
	<div class = "ti_list">
	<strong>View </strong> <%=dto.getViewCnt()%><br>
	</div>
	<div class = "ti_list">
	<strong>Post</strong> <%=dto.getRegDate()%><br><br>
	</div>
	<div class = "ti_list">
	<strong>category</strong> <%=dto.getCategory()%><br><br>
	</div>
	
	</div>
	
	<div class = "view_content">
	
	<%=dto.getContent()%>
	<% for (int i = 0; i < fileLists.size(); i++) {
				FimageDTO fimageDTO = new FimageDTO();
				fimageDTO = fileLists.get(i);
				
				String image = fimageDTO.getFileName();
				
				%>
				
				<img src="C:/Users/박지영/git/boardsite/boardsite/src/main/webapp/board_images/
			
				<%= fimageDTO.getFileName()%>" style="width: 20%">
				
				<%
				}
				%>
	
	</div>
	<div id="d_file"></div>
	<script>
	var cnt=1;
	
  	function addFile(){
	  
  		$("#d_file").append("<br>"+"<input type='file' name='fileName"+cnt+"' />");
	  	cnt++;
  	}  
	</script>
	<!-- 반복문으로 컬렉션에 있는 파일 이미지 객체를 하나씩 꺼내서 가져오는 작업. -->
	
	
	<br><hr><br>
	
	<div class = "btn_area">
	<c:set var="user_id" value="<%=dto.getUser_ID()%>" />
	<c:if test="${user_ID == user_id}">			
		<button class = "btn" type="button" onclick="chkDelete(<%=dto.getNum()%>)">Delete</button>
		<button class = "btn" type="button" onclick="location.href='update.do?num=<%=dto.getNum()%>&pageNum=<%=nowpage%>'">Update</button>
		<button class = "btn" type="button" onclick="location.href='write.do'">New Post</button>
	</c:if>
	<button class = "btn" type="button" onclick="location.href='list.do'">Board List</button>
    </div>
    <script>
    function chkDelete(num) {
    	let r = confirm("삭제하시겠습니까?");
        if (r) {
        	location.href = "deleteOk.do?num=" + num ;
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