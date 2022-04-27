<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Board</title>
</head>
<body>


	<button type="button" onclick="location.href='write'">글쓰기</button>
	
	<table>
		<thead>
			<tr>
				<th>글 번호</th>
				<th>글 제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="post" items="${postList}">
			<tr onclick="detail(${post.boardNo});" style="cursor: pointer;" >
					<td>${post.boardNo}</td>
					<td>${post.boardTitle}</td>
					<td>${post.boardWriter}</td>
					<td>${post.readCount}</td>
					<td>${post.createDate}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="hidden" name="page">
	
	
	<c:if test="${!empty sessionScope.message}">
		<script type="text/javascript">
			document.addEventListener( "DOMContentLoaded", function () {
				alert("${message}");
			});	
		</script>
		<c:remove var="message" scope="session"/>
	</c:if>
	
	<script type="text/javascript">
			function detail(post_num){
				location.href= "detail?no=" + post_num;
			}
	</script>

</body>
</html>