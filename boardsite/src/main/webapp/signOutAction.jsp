<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>list</title>
<link rel="stylesheet" href="css/post.css" >
</head>
<body>

	<%
		session.invalidate();
	%>
	<script >
		alert('로그아웃 되었습니다.')
		location.href = "main.jsp";
	</script>

</body>
</html>