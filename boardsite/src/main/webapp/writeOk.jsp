<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.beans.*" %>
<%@ page import="java.util.*"%>
<%
	boardDTO dto = (boardDTO) request.getAttribute("dto");
	
	// 해당 뷰에 작업 하기 위해서, 컨트롤러에서 설정한 파일 이미지들 전체를 담는 컬렉션을 가져오는 역할. 
	
	int num = ((Integer) request.getAttribute("num")).intValue();
	int nowpage = ((Integer) request.getAttribute("page")).intValue();
	
%>

	<script>
		alert("등록 성공");
		location.href = "view.do?num=<%=dto.getNum()%>&pageNum=<%=nowpage%>";
	</script>
