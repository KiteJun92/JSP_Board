<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Board1</title>
</head>
<body>

	메인페이지
	
	<a href="board/list">게시판</a>
	
	<c:if test="${!empty sessionScope.message}">
		<script type="text/javascript">
			document.addEventListener( "DOMContentLoaded", function () {
				alert("${message}");
			});	
		</script>
		<c:remove var="message" scope="session"/>
	</c:if>

</body>
</html>