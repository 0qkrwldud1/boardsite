<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.beans.*" %>
<%@ page import="java.util.*"%>
<%
	boardDTO dto = (boardDTO) request.getAttribute("dto");
	
	int boardNum = ((Integer) request.getAttribute("boardNum")).intValue();


%>
<% if (boardNum == 0) { %>
	<script>
		alert("등록 실패");
		history.back();
	</script>
<% } else { %>
	<script>
		alert("등록 성공");
		location.href = "list.do";
	</script>
<% } %>