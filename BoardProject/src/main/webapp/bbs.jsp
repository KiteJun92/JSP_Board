<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="java.util.ArrayList" %>

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
	
	int pageNumber = 1;	// 기본 페이지 지정
	// 만약 매개변수 오브젝트 타입 pageNumber가 존재하면 int로 캐스팅해 저장 
	if(request.getParameter("pageNumber") != null){
		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	}
	%>
	

	<jsp:include page="navbar.jsp"></jsp:include>
	
	<!-- 게시판 메인 페이지 영역 시작 -->
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center; user-select: none;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<tbody>
				<%
					BbsDAO bbsDAO = new BbsDAO();
					ArrayList<Bbs> list = bbsDAO.getList(pageNumber);
					for(int i = 0; i < list.size(); i++){
				%>
					<tr>
						<!-- 테스트 코드 -->
						<td><%= list.get(i).getBbsId() %></td>
						<td>
						<a href="view.jsp?bbsId=<%= list.get(i).getBbsId() %>">
							<%= list.get(i).getBbsTitle() %>
						</a>
						</td>
						<td><%= list.get(i).getUserId() %></td>
						<td><%= list.get(i).getBbsDate() %></td>
					</tr>
				<%
					}
				%>
				</tbody>
			</table>
			
			<!-- 페이징 처리 영역 -->
			<%
				if(pageNumber != 1){
			%>
					<a href="bbs.jsp?pageNumber=<%=pageNumber - 1 %>" class="btn btn-success btn-arrow-left">이전</a>					
			<%
				}
				if(bbsDAO.nextPage(pageNumber + 1)){
			%>
				<a href="bbs.jsp?pageNumber=<%=pageNumber + 1 %>" class="btn btn-success btn-arrow-left">다음</a>
			<%
				}
			%>	
			
			
			
			<c:if test="${!empty sessionScope.userId }">
				<!-- 글쓰기 버튼 생성 -->
				<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
			</c:if>
		</div>
	</div>
	<!-- 게시판 메인 페이지 영역 끝 -->
	
	
	
	
	<!-- Bootstrap5 + JQuery -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>

</html>