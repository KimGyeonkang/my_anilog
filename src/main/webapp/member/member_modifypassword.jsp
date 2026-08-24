<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<<script type="text/javascript">
	function goPassword() {
		mem.t_modify_password.focus();
	}
	
	function updatePassword() {
		if (checkEmpty(mem.t_password, "용사님의 기존 비밀번호가 필요합니다.")) return;
		if (checkEmpty(mem.t_modify_password, "용사님의 새 비밀번호가 필요합니다.")) return;
		if (checkEmpty(mem.t_modify_password_confirm, "새 비밀번호는 한번 더 입력해야 합니다 용사님.")) return;
		
		if (mem.t_modify_password.value != mem.t_modify_password_confirm.value) {
			alert("입력하신 새 비밀번호는 서로 같아야 합니다!");
			mem.t_modify_password_confirm.focus();
			return;
		}
		
		checkPassword();
		
		if (mem.t_password_check.value == "success") {
			if (mem.t_password.value == mem.t_modify_password.value) {
				alert("새 비밀번호는 기존 것과 같을 수 없습니다 용사님.");
				mem.t_password.focus();
				return;
			}
		} else {
			alert("기존 비밀번호를 정확하게 입력해 주세요!");
			mem.t_password.focus();
			return;
		}
		
		mem.t_gubun.value = "updatePassword";
		mem.method = "post";
		mem.action = "Member";
		mem.submit();
	}
	
	function checkPassword() {
		let id = mem.t_id.value;
		let password = mem.t_password.value;
		
		$.ajax({
		type :"POST",
		url : "CheckMemberPassword",
		data: "t_id="+id+"&t_password="+password,
		async: false,
		dataType : "text",
		error : () => {
			alert('통신 실패!!!!!');
		},
		success : (data) => {
			let result = $.trim(data); // alert 창 공백 제거(제이쿼리)
			mem.t_password_check.value = result;
			//alert("=="+result+"==");
		}
	});	
	}
</script>
<section id="container">
	<main class="main">
		<section class="member-form">
		<fieldset>
		<h1>MODIFY PASSWORD</h1>
		<h4>입장권에 새로운 비밀번호를 새기려고 하시는 군요! 여기서 도와드릴게요.</h4>
		<section class="login">
			<form name="mem">
			<input type="hidden" name="t_gubun" />
			<input type="hidden" name="t_id" value="${sessionId}" />
			<input type="hidden" name="t_password_check" />
			<table class="member-table">
				<colgroup>
					<col width="30%" />
					<col width="*" />
				</colgroup>
				<tr>
					<th>
						<i class="fa-solid fa-key"></i>
						기존 비밀번호
					</th>
					<td>
						<input type="password" name="t_password" autofocus onkeypress="if(event.keyCode==13){goPassword()}" size="30" />
					</td>
				</tr>
				<tr>
					<th>
						<i class="fa-solid fa-key"></i>
						새 비밀번호
					</th>
					<td>
						<input type="password" name="t_modify_password" onkeypress="if(event.keyCode==13){mem.t_modify_password_confirm.focus()}" size="30" />
					</td>
				</tr>
				<tr>
					<th>
						<i class="fa-solid fa-key"></i>
						비밀번호 확인
					</th>
					<td>
						<input type="password" name="t_modify_password_confirm" onkeypress="if(event.keyCode==13){updatePassword()}" size="30" />
					</td>
				</tr>
			</table>
				<section class="button">
					<a href="javascript:history.back()">BACK</a>
					&nbsp;&nbsp; / &nbsp;&nbsp;
					<a href="javascript:updatePassword()">SUBMIT</a>
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