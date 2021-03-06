<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default"> <!-- 네비게이션 -->
	<div class="navbar-header"> 	<!-- 네비게이션 상단 부분 -->
		<!-- 네비게이션 상단 박스 영역 -->
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<!-- 이 삼줄 버튼은 화면이 좁아지면 우측에 나타난다 -->
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<!-- 상단 바에 제목이 나타나고 클릭하면 main 페이지로 이동한다 -->
		<a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
	</div>
	<!-- 게시판 제목 이름 옆에 나타나는 메뉴 영역 -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<c:if test="${!empty sessionScope.userId}">
				<li><a href="main.jsp">메인</a></li>
			</c:if>
			<li><a href="bbs.jsp">게시판</a></li>
		</ul>
		<!-- 헤더 우측에 나타나는 드랍다운 영역 -->
		<ul class="nav navbar-nav navbar-right">
			
			<c:choose>
				<c:when test="${empty sessionScope.userId}">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">접속하기<span class="caret"></span></a>
						<!-- 드랍다운 아이템 영역 -->	
						<ul class="dropdown-menu">
							<li class="active"><a href="login.jsp">로그인</a></li>
							<li><a href="join.jsp">회원가입</a></li>
						</ul>
					</li>
				</c:when>
				
				<c:otherwise>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">회원관리<span class="caret"></span></a>
						<!-- 드랍다운 아이템 영역 -->	
						<ul class="dropdown-menu">
							<li class="active"><a href="logoutAction.jsp">로그아웃</a></li>
						</ul>
					</li>
				</c:otherwise>
			</c:choose>
			
		</ul>
	</div>
</nav>
