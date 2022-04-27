<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSP Board1</title>

<!-- Bootstrap4 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

</head>
<body>

    <div class="container my-5">

			<h3>게시글 상세</h3>
			<hr>
				  
			<div class="form-inline mb-2">
				<label class="input-group-addon mr-3 insert-label">제목</label> 
				<p>${post.boardTitle }</p>
			</div>

			<div class="form-inline mb-2">
				<label class="input-group-addon mr-3 insert-label">작성자</label>
				<p>${post.boardWriter}</p>
			</div>

			<hr>

			<div class="form-group">
				<div>
					<label for="content">내용</label>
				</div>
				<textarea readonly class="form-control" id="boardContent" name="boardContent" rows="15" style="resize: none; outline: none">
					${post.boardContent}
				</textarea>
			</div>


			<hr class="mb-4">

			<div class="text-center">
				<button type="button" class="btn btn-primary">수정</button>
				<button id="deletBtn" type="button" class="btn btn-primary">삭제</button>
				<button type="button" class="btn btn-primary" onclick="location.href='list'">목록으로</button>
			</div>

    </div>

		<form id="deleteForm" action='delete' method="POST">
			<input type="hidden" id="boardNum" name="no" value="${post.boardNo}">
			<input type="hidden" id="boardPass" name="boardPw" value="${post.boardPw}">
		</form>

	<!-- Bootstrap 4 JS -->
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
	
	<script>
		document.querySelector("#deletBtn").addEventListener("click", () => {
			const boardNo = ${post.boardNo};
			let checkPw = prompt("비밀번호를 입력해주세요");
			
			document.querySelector("#boardPass").value = checkPw;
			document.querySelector("#boardNum").value = boardNo;

			document.querySelector("#deleteForm").submit();
		});
	</script>
	
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