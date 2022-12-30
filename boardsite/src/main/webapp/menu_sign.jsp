<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String user_ID = (String) session.getAttribute("user_ID");
%>

<div id="grid_header">
    <nav class="header_top" >
        <ul class="top_member_box">
	        <c:choose>
				<c:when test="${empty user_ID}">   
		            <li><a href="signUp.jsp">Sign Up</a></li>
		            <li><a href="signIn.jsp">Sign In</a></li>
	         	</c:when>
				<c:otherwise>  
		            <li>[<%=user_ID%>님]</li>
		            <li><a href="signOutAction.jsp">Sign Out</a></li>
	            </c:otherwise>
			</c:choose>
			         <li><a href="write.jsp">Post</a></li>
			         <li><a href="list.do">Board List</a></li>
			         <li><a href="main.jsp">Home</a></li>
            
        </ul>
      </nav>
    </div>
  <div id="up_a">
             <div class="up_b">
               <span class="banddy"> banddy </span><span class="logo_text" style="font-style: italic; color: rgb(195, 209, 189); font-size: 15px;"> _ is a space for the group members.</span>
            </div>
          </div>