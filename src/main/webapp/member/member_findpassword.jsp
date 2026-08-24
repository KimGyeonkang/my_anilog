<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<<script type="text/javascript">
	function goSendPasswordMail() {
		if (checkEmpty(mem.t_id, "용사님의 아이디가 필요합니다.")) return;
		if (checkEmpty(mem.t_tel_1, "용사님의 전화번호가 필요합니다.")) return;
		if (checkEmpty(mem.t_tel_2, "용사님의 전화번호가 필요합니다.")) return;
		if (checkEmpty(mem.t_tel_3, "용사님의 전화번호가 필요합니다.")) return;
		
		mem.t_gubun.value = "sendPasswordMail";
		mem.method = "post";
		mem.action = "Member";
		mem.submit();
	}
</script>
<section id="container">
	<main class="main">
		<section class="member-form">
		<fieldset>
		<h1>FIND PASSWORD</h1>
		<h4>혹시 비밀번호 티켓을 분실하셨다면 여기를 이용해주세요!</h4>
		<h4>* 아래 정확한 정보를 입력해주시면 임시 비밀번호 티켓을 용사님의 이메일로 보내드려요!</h4>
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
						<input type="text" name="t_id" autofocus onkeypress="if(event.keyCode==13){mem.t_tel_1.focus();}" size="30" />
					</td>
				</tr>
				<tr>
					<th>
						<i class="fa-solid fa-key"></i>
						전화번호
					</th>
					<td>
						<input type="text" name="t_tel_1" onkeypress="if(event.keyCode==13){mem.t_tel_2.focus();}" size="6" />
						- <input type="text" name="t_tel_2" onkeypress="if(event.keyCode==13){mem.t_tel_3.focus();}" size="6" />
						- <input type="text" name="t_tel_3" onkeypress="if(event.keyCode==13){goSendPasswordMail()}" size="6" />
					</td>
				</tr>
			</table>
				<section class="button">
					<a href="javascript:history.back()">BACK</a>
					&nbsp;&nbsp; / &nbsp;&nbsp;
					<a href="javascript:goSendPasswordMail()">SEND EMAIL</a>
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