<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script>
	function goMemberUpdateForm() {
		gubun.t_gubun.value = "memberUpdateForm";
		gubun.method="post";
		gubun.action="Member";
		gubun.submit();
	}
	
	function goExit() {
		if(confirm("정말 저희를 떠나시겠어요 용사님?")) {
			gubun.t_gubun.value = "exit";
			gubun.method="post";
			gubun.action="Member";
			gubun.submit();
		}
	}
	
	function goUpdatePassword() {
		gubun.t_gubun.value = "updatePasswordForm";
		gubun.method="post";
		gubun.action="Member";
		gubun.submit();
	}
</script>
<section id="container">
	<main class="main">
		<section class="member-form">
		<fieldset>
			<h1>MY PAGE</h1>
			<h4>모험은 즐거우신가요 용사님?</h4>
			<section class="join">
				<table class="member-table">
					<colgroup>
						<col width="30%" />
						<col width="*" />
					</colgroup>
					<tr>
						<th>
							<i class="fa-solid fa-id-badge"></i>
							아이디
						</th>
						<td>
							${t_member.getId()}
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-key"></i>
							비밀번호
						</th>
						<td>
							<c:forEach begin="1" end="${t_member.getPassword_length()}">
								*
							</c:forEach>
							&nbsp;&nbsp;
							<a href="javascript:goUpdatePassword()">( MODIFY PASSWORD )</a>
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-address-card"></i>
							성명
						</th>
						<td>
							${t_member.getName()}
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-phone"></i>
							전화번호
						</th>
						<td>
							${t_member.getTel_1()}-${t_member.getTel_2()}-${t_member.getTel_3()}
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-at"></i>
							이메일
						</th>
						<td>
							${t_member.getEmail_1()}@${t_member.getEmail_2()}
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-brands fa-bilibili"></i>
							선호장르
						</th>
						<td>
							<c:choose>
								<c:when test="${t_member.getFav() eq ''}"></c:when>
								<c:when test="${t_member.getFav() eq 'a'}">액션</c:when>
								<c:when test="${t_member.getFav() eq 'd'}">일상물</c:when>
								<c:when test="${t_member.getFav() eq 's'}">학원물</c:when>
								<c:when test="${t_member.getFav() eq 'c'}">코미디</c:when>
								<c:when test="${t_member.getFav() eq 'o'}">기타</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-regular fa-calendar"></i>
							가입일자
						</th>
						<td>
							${t_member.getReg_date()}
						</td>
					</tr>
					<c:if test="${not empty t_member.getUpdate_date()}">
						<tr>
							<th>
								<i class="fa-solid fa-calendar"></i>
								수정일자
							</th>
							<td>
								${t_member.getUpdate_date()}
							</td>
						</tr>
					</c:if>
				</table>
				
				</section>
				<section class="button">
					<a href="Index">HOME</a>
					&nbsp;&nbsp; / &nbsp;&nbsp;
					<a href="javascript:goMemberUpdateForm()">MODIFY</a>
					&nbsp;&nbsp; / &nbsp;&nbsp;
					<a href="javascript:goExit()">EXIT</a>
				</section>
			</fieldset>
				</section>
	</main>

</section>
<%@ include file="../common_footer.jsp" %>
</body>
</html>