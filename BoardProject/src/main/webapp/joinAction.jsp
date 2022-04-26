<%@page import="java.io.PrintWriter"%>
<%@page import="user.UserDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="user" class="user.User" scope="page"></jsp:useBean>
<jsp:setProperty property="userId" name="user"/>
<jsp:setProperty property="userPw" name="user"/>
<jsp:setProperty property="userName" name="user"/>
<jsp:setProperty property="userGender" name="user"/>
<jsp:setProperty property="userEmail" name="user"/>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>JSP 게시판 로그인 결과</title>
</head>
<body>
	
	<%
		if(user.getUserId() == null || user.getUserPw() == null || 
			user.getUserName() == null || user.getUserGender() == null || user.getUserEmail() == null){
			
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('모든 정보를 입력해주세요.')");
			script.println("history.back()");
			script.println("</script>");
			
		}else{
			UserDAO userDAO = new UserDAO();
			int result = userDAO.join(user);
			if(result == -1){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 존재하는 아이디입니다')");
				script.println("history.back()");
				script.println("</script>");
			}else{
				session.setAttribute("userId", user.getUserId());
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('회원가입 성공')");
				script.println("location.href='main.jsp'");
				script.println("</script>");
			}
		}
	%>

</body>
</html>