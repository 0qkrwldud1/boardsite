<%@page import="com.beans.userDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "com.beans.userDAO" %>

<jsp:useBean id ="userDTO" class = "com.beans.userDTO" scope = "page" />
<jsp:setProperty name = "userDTO" property = "user_ID" />
<jsp:setProperty name = "userDTO" property = "user_PW" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>signInAction</title>
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
  	
  	userDAO loguser = new userDAO();
  	int result = loguser.login(userDTO.getUser_ID(), userDTO.getUser_PW());
  	if (result ==1 ) {
  		session.setAttribute("user_ID", userDTO.getUser_ID());
  		PrintWriter script = response.getWriter();
  		script.println("<script>");
  		script.println("alert('로그인 성공')");
  		script.println("location.href = 'list.jsp'");
  		script.println("</script>");
  		
  	}else if (result == 0) {
  		PrintWriter script = response.getWriter();
  		script.println("<script>");
  		script.println("alert('비밀번호가 틀립니다.')");
  		script.println("history.back()");
  		script.println("</script>");
  		
  	}else if (result == -1) {
  		PrintWriter script = response.getWriter();
  		script.println("<script>");
  		script.println("alert('존재하지 않는 아이디 입니다.')");
  		script.println("history.back()");
  		script.println("</script>");
  		
  	}else if (result == -2) {
  		PrintWriter script = response.getWriter();
  		script.println("<script>");
  		script.println("alert('데이터 베이스 오류입니다.')");
  		script.println("history.back()");
  		script.println("</script>");
  	}
  	
  %>
   
</body>   
</html> 

