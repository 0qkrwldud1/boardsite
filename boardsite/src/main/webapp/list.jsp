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
    
    <div class = "top_list">
    			<div >
					<form action="<c:url value="./list.do"/>" method="post"
							style= "margin-left: 15%;">
		 				<table id = "search_category">
							<tr id = "search_category">
								<td id = "search_category">&nbsp;&nbsp; 
									<select name="items" class="txt" value="${searchVO.items}">
									<option value="">Please choose a category</option>
										<option value="art"${param.items eq 'art' ? "selected" : ""}>문화/예술</option>
						                <option value="pet"${param.items eq 'pet' ? "selected" : ""}>반려동물</option>
						                <option value="trip"${param.items eq 'trip' ? "selected" : ""}>여행/캠핑</option>
						                <option value="life"${param.items eq 'life' ? "selected" : ""}>일상/이야기</option>
						                <option value="study"${param.items eq 'study' ? "selected" : ""}>교육/공부</option>
						                <option value="meet"${param.items eq 'meet' ? "selected" : ""}>친목/모임</option>
						                <option value="sport"${param.items eq 'sport' ? "selected" : ""}>스포츠/레저</option>
						                <option value="IT"${param.items eq 'IT' ? "selected" : ""}>IT/컴퓨터</option>
						                <option value="job"${param.items eq 'job' ? "selected" : ""}>취업/자격증</option>
									</select> 
									<input type="submit" class= "btn" value="Search " />
								</td>	
							</tr>
						</table>
					</form>
			   	</div>
			   	
			   	<div class = "total">
					<button class= "btn" ><b>total <%=total_record%></b></button>
				</div>
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
			<td><%=board.getRegDate()%></td>
			
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
							<select name="items" class="txt" value="${searchVO.items}">
								
								<option value="bd_title"${param.items eq 'bd_title' ? "selected" : ""}>Title</option>
								<option value="bd_content"${param.items eq 'bd_content' ? "selected" : ""}>Content</option>
								<option value="user_ID"${param.items eq 'user_ID' ? "selected" : ""}>User ID</option>

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
			
	   	  
   
   			
   	<h4>유저 관리자 1:1 채팅 - 관리자</h4>
   	<!-- 유저가 접속할 때마다 이 템플릿으로 채팅창을 생성한다. 
   	보내는 쪽이 관리자, 호스트네임 -> admin 
   	로그인 했을때, 아이콘을 누르면 대화창이 뜨게 만든다
   	문의같은 형식으로 채팅상담-->
  	
  	<div class="template" style="display:none">
	    
	    <form>
	      <!-- 메시지 텍스트 박스 -->
	      <input type="text" class="message" onkeydown="if(event.keyCode === 13) return false;">
	      <!-- 전송 버튼 -->
	      <input value="Send" type="button" class="sendBtn">
	    
	    </form>
    <br />
    
    <!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->
    
    	<textarea rows="10" cols="50" class="console" disabled="disabled"></textarea>
  	</div>
  	
  <!-- 소스를 간단하게 하기 위하 Jquery를 사용했습니다. -->
  
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  
  <script type="text/javascript">
    
  	// 서버의 admin의 서블릿으로 웹 소켓을 한다.
    var webSocket = new WebSocket("ws://localhost:8090/boardsite/admin");
    
  	// 운영자에서의 open, close, error는 의미가 없어서 형태만 선언
    webSocket.onopen = function(message) { };
    webSocket.onclose = function(message) { };
    webSocket.onerror = function(message) { };
    
    // 서버로 부터 메시지가 오면
    webSocket.onmessage = function(message) {
      
    // 메시지의 구조는 JSON 형태로 만들었다.
    let node = JSON.parse(message.data);
    
    // 메시지의 status는 유저의 접속 형태이다.
    // visit은 유저가 접속했을 때 알리는 메시지다.
    if(node.status === "visit") {
        
    	// 위 템플릿 div를 취득한다.
        let form = $(".template").html();
        
    	// div를 감싸고 속성 data-key에 unique키를 넣는다.
        form = $("<div class='float-left'></div>").attr("data-key",node.key).append(form); 
        
    	// body에 추가한다.
        $("body").append(form);
        
    	// message는 유저가 메시지를 보낼 때 알려주는 메시지이다.
      	
    	} else if(node.status === "message") {
       
    	// key로 해당 div영역을 찾는다.
        let $div = $("[data-key='"+node.key+"']");
       
    	// console영역을 찾는다.
        let log = $div.find(".console").val();
        
    	// 아래에 메시지를 추가한다.
        $div.find(".console").val(log + "(user) => " +node.message + "\n");
      
    	// bye는 유저가 접속을 끊었을 때 알려주는 메시지이다.
     
    	} else if(node.status === "bye") {
       
    	// 해당 키로 div를 찾아서 dom을 제거한다.
        $("[data-key='"+node.key+"']").remove();
      }
    };
    
    // 전송 버튼을 클릭하면 발생하는 이벤트
    $(document).on("click", ".sendBtn", function(){
      
    	// div 태그를 찾는다.
     	 let $div = $(this).closest(".float-left");
      
    	// 메시지 텍스트 박스를 찾아서 값을 취득한다.
      	let message = $div.find(".message").val();
      
    	// 유저 key를 취득한다.
      	let key = $div.data("key");
      
    	// console영역을 찾는다.
      	let log = $div.find(".console").val();
      
    	// 아래에 메시지를 추가한다.
     	$div.find(".console").val(log + "(me) => " + message + "\n");
      
    	// 텍스트 박스의 값을 초기화 한다.
      	$div.find(".message").val("");
      
    	// 웹소켓으로 메시지를 보낸다.
      	webSocket.send(key+"#####"+message);
    });
    
    	// 텍스트 박스에서 엔터키를 누르면
    	$(document).on("keydown", ".message", function(){
      
    	// keyCode 13은 엔터이다.
      	if(event.keyCode === 13) {
        
      	// 버튼을 클릭하는 트리거를 발생한다.
        $(this).closest(".float-left").find(".sendBtn").trigger("click");
        
      	// form에 의해 자동 submit을 막는다.
        return false;
      }
      return true;
    });
  </script>
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