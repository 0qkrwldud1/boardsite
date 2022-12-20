<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.beans.*" %>
<%
	int cnt = (Integer)request.getAttribute("result");
	boardDTO dto = (boardDTO)request.getAttribute("dto");
%>
<% if (cnt == 0) { %>
	<script>
		alert("등록 실패");
		history.back();
	</script>

<% } else { %>
	<script>
		alert("등록 성공");
		location.href = "view.do?num=<%=dto.getNum()%>";
	</script>
<% } %>