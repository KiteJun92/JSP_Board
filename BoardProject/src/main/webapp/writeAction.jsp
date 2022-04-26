<%@page import="java.io.PrintWriter"%>
<%@page import="bbs.BbsDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="bbs" class="bbs.Bbs" scope="page"></jsp:useBean>
<jsp:setProperty property="bbsTitle" name="bbs"/>
<jsp:setProperty property="bbsContent" name="bbs"/>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>JSP 게시판</title>
</head>
<body>
	
	<%
		// 현재 세션 상태 파악
		String userId = null;
		if(session.getAttribute("userId") != null){
			userId = (String)session.getAttribute("userId");
		}
	
	
		if(bbs.getBbsTitle() == null || bbs.getBbsContent() == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('모든 정보를 입력해주세요.')");
			script.println("history.back()");
			script.println("</script>");
		}else{
			BbsDAO bbsDAO = new BbsDAO();
			int result = bbsDAO.write(bbs.getBbsTitle(), userId, bbs.getBbsContent());
			// DB 오류
			if(result == -1){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글 작성 실패')");
				script.println("history.back()");
				script.println("</script>");
			}else{
				// 정상 입력 시 로직 수행
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글 작성 성공')");
				script.println("location.href='bbs.jsp'");
				script.println("</script>");
			}
		}
	%>

</body>
</html>