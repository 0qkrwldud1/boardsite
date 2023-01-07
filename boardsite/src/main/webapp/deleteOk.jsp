<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = (Integer)request.getAttribute("num");
	int nowpage = ((Integer) request.getAttribute("page"));
%>
<% 	if (num == 0 ) { %>
	
	<script>
		alert("삭제 실패");
		history.back();
	</script>

<% } else { %>
	<script>
		alert("삭제 성공");
		location.href = "list.do";
	</script>
<% } %>