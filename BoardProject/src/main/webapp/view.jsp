<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="bbs.BbsDAO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- Bootstrap5 -->
    <link rel="stylesheet" href="css/bootstrap.css">
    
	<title>JSP</title>
</head>

<body>
	<%
	// 메인 페이지로 이동했을 때 세션에 값이 있는지 확인하기
	String userId = null;
	if(session.getAttribute("userId") != null){
		userId = (String)session.getAttribute("userId");
	}
	
	// bbsId 초기화 후 'bbsId'라는 데이터 값이 넘어온다면 캐스팅하여 변수에 담기
	int bbsId = 0;
	if(request.getParameter("bbsId") != null){
		bbsId = Integer.parseInt(request.getParameter("bbsId"));
	}
	if(bbsId == 0){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 글입니다.')");
		script.println("location.href='bbs.jsp'");
		script.println("</script>");
	}
	
	Bbs bbs = new BbsDAO().detail(bbsId);
	%>
	

	<jsp:include page="navbar.jsp"></jsp:include>
	
	<!-- 게시판 글 읽기 영역 시작 -->
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시글 상세조회</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width:20%">글 제목</td>
						<td colspan="2"><%=bbs.getBbsTitle() %></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%=bbs.getUserId() %></td>
					</tr>
					<tr>
						<td>작성일자</td>
						<td colspan="2"><%=bbs.getBbsDate() %></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="height: 200px; text-align:left"><%=bbs.getBbsContent() %></td>
					</tr>
				</tbody>
			</table>
			<a href="bbs.jsp" class="btn btn-primary">목록</a>
			
			<%
				if(userId != null && userId.equals(bbs.getUserId())){
			%>
				<a href="update.jsp?bbsId=<%=bbsId %>" class="btn btn-primary">수정</a>
				<a href="deleteAction.jsp?bbsId=<%=bbsId %>" class="btn btn-primary">삭제</a>
			<%
				}
			%>
		</div>
	</div>
	<!-- 게시판 상세조회 영역 끝 -->
	
	<!-- Bootstrap5 + JQuery -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>

</html>