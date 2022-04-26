<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- Bootstrap5 -->
    <link rel="stylesheet" href="css/bootstrap.css">
    
	<title>Main</title>
</head>

<body>
	<%
	// 메인 페이지로 이동했을 때 세션에 값이 있는지 확인하기
	String userId = null;
	if(session.getAttribute("userId") != null){
		userId = (String)session.getAttribute("userId");
	}
	%>
	

	<jsp:include page="navbar.jsp"></jsp:include>
	
	
	
	
	<!-- Bootstrap5 + JQuery -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>

</html>