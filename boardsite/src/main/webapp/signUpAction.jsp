<%@page import="com.beans.userDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "com.beans.userDAO" %>
<jsp:useBean id ="userDTO" class = "com.beans.userDTO" scope = "page" />
<jsp:setProperty name = "userDTO" property = "user_ID" />
<jsp:setProperty name = "userDTO" property = "user_PW" />
<jsp:setProperty name = "userDTO" property = "user_Name" />
<jsp:setProperty name = "userDTO" property = "user_Gender" />
<jsp:setProperty name = "userDTO" property = "user_Email" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SignUpAction</title>
    <link rel="stylesheet" href="css/post.css" >
</head>
<body>
  <%
  
	  	String user_ID = null; // 현재 세션 상태를 체크함.
		if(session.getAttribute("user_ID") != null) {
			user_ID = (String)session.getAttribute("user_ID");
		}
		
		if (user_ID != null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인 되어 있습니다.')");
			script.println("location.href = 'main.jsp'");
			script.println("</script>");
		}
  
	  	if(userDTO.getUser_ID() == null || userDTO.getUser_PW() == null || 
	  	userDTO.getUser_Name() == null || userDTO.getUser_Gender() == null ||
	  	userDTO.getUser_Email() == null) {
  		PrintWriter script = response.getWriter();
  		script.println("<script>");
  		script.println("alert('입력이 안된 사항이 있습니다.')");
  		script.println("history.back()");
  		script.println("</script>");
  		
  		} else {
  			userDAO loguser = new userDAO();
  			int result = loguser.join(userDTO);
  			if (result == -1) {
  				PrintWriter script = response.getWriter();
  		  		script.println("<script>");
  		  		script.println("alert('이미 존재하는 아이디 입니다.')");
  		  		script.println("history.back()");
  		  		script.println("</script>");
  		} else {
  				session.setAttribute("user_ID", userDTO.getUser_ID());
	  			PrintWriter script = response.getWriter();
	  	  		script.println("<script>");
	  	  		script.println("alert('회원가입 성공')");
	  	  		script.println("location.href = 'main.jsp'");
	  	  		script.println("</script>");
  		}
  	}
  	
  %>
   
</body>   
</html> 

