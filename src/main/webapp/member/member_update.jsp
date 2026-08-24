<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script>
	function checkPassword() {
		//if (checkEmpty(mem.t_password_confirm, "비밀번호 확인을 입력해주세요.")) return;
		
		let id = mem.t_id.value;
		let password = mem.t_password_confirm.value;
		
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
	
	function goUpdate() {		
		if(checkEmpty(mem.t_name, "용사님의 성명이 필요합니다.")) return;
		if(checkEmpty(mem.t_tel_1, "용사님의 전화번호가 필요합니다.")) return;
		if(checkEmpty(mem.t_tel_2, "용사님의 전화번호가 필요합니다.")) return;
		if(checkEmpty(mem.t_tel_3, "용사님의 전화번호가 필요합니다.")) return;
		if(checkEmpty(mem.t_email_1, "용사님의 이메일이 필요합니다.")) return;
		if(checkEmpty(mem.t_email_2, "용사님의 이메일이 필요합니다.")) return;
		
		if (checkEmpty(mem.t_password_confirm, "용사님의 기존 비밀번호가 필요합니다.")) return;
		
		checkPassword();
		
		if (mem.t_password_check.value == "fail") {
			alert("정확한 비밀번호가 필요해요 용사님!");
			mem.t_password_check.focus();
			return;
		}
		
		mem.t_gubun.value="update";
		mem.method="post";
		mem.action="Member";
		mem.submit();
	}
</script>
<section id="container">
	<main class="main">
		<form name="mem">
		<input type="hidden" name="t_gubun" />
		<input type="hidden" name="t_password_check" />
		<section class="member-form">
		<fieldset>
			<h1>MODIFY</h1>
			<h4>회원 정보 수정을 위해서는 티켓의 기존 비밀번호가 정확해야 합니다.</h4>
			<h4>*은 필수 입력 항목입니다.</h4>
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
							<input type="hidden" name="t_id" value="${t_member.getId()}" />
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-address-card"></i>
							성명*
						</th>
						<td>
							<input type="text" size="10" name="t_name" value="${t_member.getName()}" />
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-phone"></i>
							전화번호*
						</th>
						<td>
							<input type="text" size="3" name="t_tel_1" value="${t_member.getTel_1()}"/> -
							<input type="text" size="4" name="t_tel_2" value="${t_member.getTel_2()}"/> -
							<input type="text" size="4" name="t_tel_3" value="${t_member.getTel_3()}"/>
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-at"></i>
							이메일*
						</th>
						<td>
							<input type="text" size="10" name="t_email_1" value="${t_member.getEmail_1()}" /> @
							<input type="text" size="10" name="t_email_2" value="${t_member.getEmail_2()}" />
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-brands fa-bilibili"></i>
							선호장르
						</th>
						<td>
							<select name="t_fav"> <c:if test="${t_member.getFav() eq ''}">selected</c:if>
								<option value="" <c:if test="${t_member.getFav() eq ''}">selected</c:if>>= 선택 =</option>
								<option value="a" <c:if test="${t_member.getFav() eq 'a'}">selected</c:if>>액션</option>
								<option value="d" <c:if test="${t_member.getFav() eq 'd'}">selected</c:if>>일상물</option>
								<option value="s" <c:if test="${t_member.getFav() eq 's'}">selected</c:if>>학원물</option>
								<option value="c" <c:if test="${t_member.getFav() eq 'c'}">selected</c:if>>코미디</option>
								<option value="o" <c:if test="${t_member.getFav() eq 'o'}">selected</c:if>>기타</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-key"></i>
							기존 비밀번호*
						</th>
						<td>
							<input type="password" size="15" name="t_password_confirm" />
						</td>
					</tr>
				</table>
				</form>
				</section>
				<section class="button">
					<a href="Index">HOME</a>
					&nbsp;&nbsp; / &nbsp;&nbsp;
					<a href="javascript:history.go(-1)">BACK</a>
					&nbsp;&nbsp; / &nbsp;&nbsp;
					<a href="javascript:goUpdate()">MODIFY</a>
				</section>
			</fieldset>
				</section>
	</main>

</section>
<%@ include file="../common_footer.jsp" %>
</body>
</html>