<%@page import="dao.BoardDao"%>
<%@page import="dto.BoardDto"%>
<%@page import="common.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script>
	function goUpdateForm() {
		board.t_gubun.value = "updateForm";
		board.method="post";
		board.action="Board";
		board.submit();
	}
	function goDelete() {
		if (confirm("용사님의 소중한 기록이에요. 정말 삭제하시려고요?")) {
			board.t_gubun.value = "delete";
			board.method="post";
			board.action="Board";
			board.submit();
		}
	}
</script>
<form name="board">
	<input type="hidden" name="t_no" value="${board.getNo()}" />
	<input type="hidden" name="t_gubun" />
	<input type="hidden" name="t_original_attach" value="${board.getAttach()}" />
</form>
<section id="container">
	<main class="main">
	<fieldset>
		<section class="board">
		<h1>BOARD</h1>
			<table class="board-table" style="margin-bottom: 10px; width: 40%;">
				<colgroup>
					<col width="15%" />
					<col width="50%" />
					<col width="15%" />
					<col width="*" />
				</colgroup>
				<tr>
					<td class="th-td">제목</td>
					<td>
						${board.getTitle()}
					</td>
					<td class="th-td">조회수</td>
					<td>
						${board.getHit()}
					</td>
				</tr>
				<tr>
					<td class="th-td">내용</td>
					<td colspan="3">
						<c:if test="${not empty board.getAttach()}">
							<img src="attach/board/${board.getAttach()}" width="450" height="500" />
						</c:if>
						<textarea rows="30" cols="70" readonly style="width: 90%; resize: none; border: none; outline: none;">${board.getContent()}</textarea>
					</td>
				</tr>
				<tr>
					<td class="th-td">첨부</td>
					<td colspan="3">
					<c:choose>
						<c:when test="${not empty board.getAttach()}">
						<a href="FileDownServlet?t_fileDir=board&t_fileName=${board.getAttach()}">
							${board.getAttach()}
						</a>
						</c:when>
						<c:otherwise>현재는 첨부파일이 없습니다 용사님! 적절한 첨부는 기록을 보다 레벨업 시킵니다!</c:otherwise>
					</c:choose>
					</td>
				</tr>
				<tr>
					<td class="th-td">글쓴이</td>
					<td colspan="3">${board.getReg_name()}</td>
				</tr>
				<tr>
					<td class="th-td">등록일자</td>
					<td colspan="3">${board.getReg_date()}</td>
				</tr>

			</table>
			<section class="button">
				<a href="Board">LIST</a>
				<!-- 작성자와 관리자만 수정 삭제 가능 -->
				<c:if test="${sessionName eq board.getReg_name() or sessionLevel eq 'admin'}">
					&nbsp;&nbsp; / &nbsp;&nbsp;
					<a href="javascript:goUpdateForm()">UPDATE</a>
					&nbsp;&nbsp; / &nbsp;&nbsp;
					<a href="javascript:goDelete()">DELETE</a>
				</c:if>
			</section>
		</section>
	</fieldset>
	</main>
</section>
<%@ include file="../common_footer.jsp" %>
</body>
</html>