<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Track27 김견강</title>
	<link rel="stylesheet" href="css/main.css" />
	<!-- 폰트어썸 import -->
	<script src="https://kit.fontawesome.com/7565764b78.js" crossorigin="anonymous"></script>
	<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript">
	function goMemberServlet(gb) {
		gubun.t_gubun.value= gb;
		gubun.method="post";
		gubun.action="Member";
		gubun.submit();
	}
	function goSearch() {
		search.method="post";
		search.action="Board";
		search.submit();
	}
	function goListPage(pageNo) {
		search.t_nowPage.value = pageNo;
		search.method="post";
		search.action="Board";
		search.submit();
	}
</script>
</head>
<body>
<form name="gubun">
	<input type="hidden" name="t_gubun" />
	<input type="hidden" name="t_no" />
</form>
<header>
	<section class="header-content">
		<h1><a href="Index">MY ANILOG</a></h1>
		<section class="header-right">
			<nav>
				<ul>
					<li><a href="Index">Home</a></li>
					<c:if test="${not empty sessionName}">
						<li><a href="javascript:goMemberServlet('logout')">Logout</a></li>
						<li><a href="javascript:goMemberServlet('mypage')">Mypage</a></li>
					</c:if>
					<c:if test="${empty sessionName}">
						<li><a href="javascript:goMemberServlet('login')">Login</a></li>
						<li><a href="javascript:goMemberServlet('join')">Join</a></li>
					</c:if>
				</ul>			
			</nav>
			<form name="search" method="post">
			<input type="hidden" name="t_nowPage" />
				<section class="search">
					<select name="t_select">
						<option value="title" <c:if test="${select eq 'title'}">selected</c:if>>Title</option> 
						<option value="content" <c:if test="${select eq 'content'}">selected</c:if>>Content</option>
					</select>
					<input type="text" name="t_search" value="${search}" size="10" />
					<button type="submit" onclick="javascript:search.action='Board'">Search</button>
				</section>
			</form>
		</section>
	</section>
<hr class="divider">
</header>