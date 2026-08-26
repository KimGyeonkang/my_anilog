<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script>
	function goWriteForm() {
		gubun.t_gubun.value = "writeForm";
		gubun.method="post";
		gubun.action="Board";
		gubun.submit();
	}
	function goView(no) {
		gubun.t_gubun.value = "view";
		gubun.t_no.value = no;
		gubun.method="post";
		gubun.action="Board";
		gubun.submit();
	}
</script>
<section id="container">
	<main class="main">
		<section class="board">
		<fieldset>
		<h1>BOARD</h1>
		<!-- font-weight: bold; -> 굵은 글씨로 -->
		<p style="margin-bottom: 10px;">지금까지 <span style="color: red; font-weight: bold;">${totalCount}</span> 건의 소중한 기록을 보관 중입니다!</p>
			<table class="board-table" style="margin-bottom: 10px; width: 50%">
				<colgroup>
					<col width="10%" />
					<col width="*" />
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="7%" />
				</colgroup>
				<tr>
					<td class="th-td">번호</td>
					<td class="th-td">제목</td>
					<td class="th-td">첨부</td>
					<td class="th-td">글쓴이</td>
					<td class="th-td">작성일</td>
					<td class="th-td">조회수</td>
				</tr>
				<!-- 별도의 상태 변수 만들지 않고 order만 사용해 게시글 번호 생성 -->
				<!-- varStatus="status"는 현재 반복 상태 저장 -->
				<!-- status.index는 0부터 시작 -->
				<!-- status.count는 1부터 시작 -->
				<c:forEach items="${list}" var="board" varStatus="status">
					<tr>
						<td style="text-align: center;">${order - status.index}</td>
						<td><a href="javascript:goView('${board.getNo()}')">${board.getTitle()}</a></td>
						<td style="text-align: center;">
							<c:if test="${not empty board.getAttach()}">O</c:if>
						</td>
						<td style="text-align: center;">${board.getReg_name()}</td>
						<td>${board.getReg_date()}</td>
						<td style="text-align: right;">${board.getHit()}</td>
					</tr>
				</c:forEach>

			</table>
			<div class="paging">
			<!--  
				<a href=""><i class="fa fa-angle-double-left"></i></a>
				<a href=""><i class="fa fa-angle-left"></i></a>
				<a href="" class="active">1</a>
				<a href="">2</a>
				<a href="">3</a>
				<a href="">4</a>
				<a href="">5</a>
				<a href=""><i class="fa fa-angle-right"></i></a>
				<a href=""><i class="fa fa-angle-double-right"></i></a>
			-->
			${paging}
			</div>
			<c:if test="${not empty sessionId}">
				<section class="button">
					<a href="javascript:goWriteForm()">WRITE</a>
				</section>
			</c:if>
			<fieldset>
		</section>
	</main>

</section>
<%@ include file="../common_footer.jsp" %>
</body>
</html>