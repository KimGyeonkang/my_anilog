<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<<script type="text/javascript">
	function goPassword() {
		mem.t_password.focus();
	}
	function goMemberLogin() {
		if (checkEmpty(mem.t_id, "용사님의 아이디가 필요합니다.")) return;
		if (checkEmpty(mem.t_password, "용사님의 비밀번호가 필요합니다.")) return;
		
		mem.t_gubun.value = "memberLogin";
		mem.method = "post";
		mem.action = "Member";
		mem.submit();
	}
	function goFindPassword() {
		mem.t_gubun.value = "findPasswordForm";
		mem.method = "post";
		mem.action = "Member";
		mem.submit();
	}
</script>
<section id="container">
	<main class="main">
		<section class="member-form">
		<fieldset>
		<h1>LOGIN</h1>
		<h4>길드에 오신 걸 환영합니다 용사님! 아래 정확한 티켓 정보를 입력해주세요.</h4>
		<section class="login">
			<form name="mem">
			<input type="hidden" name="t_gubun" />
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
						<input type="text" name="t_id" autofocus onkeypress="if(event.keyCode==13){goPassword()}" size="30" />
					</td>
				</tr>
				<tr>
					<th>
						<i class="fa-solid fa-key"></i>
						비밀번호
					</th>
					<td>
						<input type="password" name="t_password" onkeypress="if(event.keyCode==13){goMemberLogin()}" size="30" />
					</td>
				</tr>
			</table>
				<section class="button">
					<a href="javascript:goMemberLogin()">LOGIN</a>
					&nbsp;&nbsp; / &nbsp;&nbsp;
					<a href="javascript:goFindPassword()">FIND PASSWORD</a>
				</section>
		</section>
		</form>
		</fieldset>
		</section>
	</main>
</section>
<%@ include file="../common_footer.jsp" %>
</body>
</html>