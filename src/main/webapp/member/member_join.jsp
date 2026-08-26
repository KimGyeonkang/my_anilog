<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script>
	function goSave() {
		if (checkEmpty(mem.t_id, "용사님의 아이디가 필요합니다.")) return;
		if (checkEmpty(mem.t_id_check, "아이디 중복 검사는 잊으시면 안 됩니다.")) return;
		
		if (mem.t_id_check.value == "이미 해당 아이디를 사용 중인 용사님이 계시네요. 더 멋진 이름이 있을 겁니다.") {
			alert("용사님만이 사용할 수 더 멋진 아이디를 생각해 봐요!");
			mem.t_id.focus();
			return;
		}
		
		if(checkEmpty(mem.t_password, "용사님의 비밀번호가 필요합니다.")) return;
		if(checkEmpty(mem.t_password_check, "용사님의 비밀번호 확인이 필요합니다.")) return;
		
		if (mem.t_password.value != mem.t_password_check.value) {
			alert("비밀번호와 비밀번호 확인은 동일해야 합니다 용사님.");	
			mem.t_password_check.focus();
			return;
		}
		
		if(checkEmpty(mem.t_name, "용사님의 성명이 필요합니다.")) return;
		if(checkEmpty(mem.t_tel_1, "용사님의 전화번호가 필요합니다.")) return;
		if(checkEmpty(mem.t_tel_2, "용사님의 전화번호가 필요합니다.")) return;
		if(checkEmpty(mem.t_tel_3, "용사님의 전화번호가 필요합니다.")) return;
		if(checkEmpty(mem.t_email_1, "용사님의 이메일이 필요합니다.")) return;
		if(checkEmpty(mem.t_email_2, "용사님의 이메일이 필요합니다.")) return;
		
		mem.t_gubun.value="register";
		mem.method="post";
		mem.action="Member";
		mem.submit();
	}
	
	function checkId() {
		if (checkEmpty(mem.t_id, "아이디를 먼저 입력해주세요.")) return;
		
		let id = mem.t_id.value;
		
		$.ajax({
		type :"POST",
		url : "CheckMemberId",
		data: "t_id="+id,
		async: false,
		dataType : "text",
		error : () => {
			alert('통신 실패!!!!!');
		},
		success : (data) => {
			let result = $.trim(data); // alert 창 공백 제거(제이쿼리)
			mem.t_id_check.value = result;
			alert("=="+result+"==");
		}
	});	
	}
	
	// 중복 검사 후 id 값 수정 방지: id 인풋 상자에 값 입력되면 중복 체크 인풋 값 공백 설정
	function setEmpty() {
		mem.t_id_check.value = "";
	}
</script>
<section id="container">
	<main class="main">
		<form name="mem">
		<input type="hidden" name="t_gubun" />
		<section class="member-form">
		<fieldset>
			<h1>JOIN</h1>
			<h4>길드에 입장할 새로운 티켓이 필요한 용사는 이곳으로.</h4>
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
							아이디*
						</th>
						<td>
							<input type="text" size="15" name="t_id" oninput="setEmpty()" />&nbsp;
							<button type="button" onclick="checkId()">CheckID</button>
							<input type="hidden" disabled name="t_id_check" />
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-key"></i>
							비밀번호*
						</th>
						<td>
							<input type="password" size="15" name="t_password" />
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-key"></i>
							비밀번호확인*
						</th>
						<td>
							<input type="password" size="15" name="t_password_check" />
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-address-card"></i>
							성명*
						</th>
						<td>
							<input type="text" size="10" name="t_name" />
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-phone"></i>
							전화번호*
						</th>
						<td>
							<input type="text" size="3" name="t_tel_1"/> -
							<input type="text" size="4" name="t_tel_2"/> -
							<input type="text" size="4" name="t_tel_3"/>
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-solid fa-at"></i>
							이메일*
						</th>
						<td>
							<input type="text" size="10" name="t_email_1" /> @
							<input type="text" size="10" name="t_email_2" />
						</td>
					</tr>
					<tr>
						<th>
							<i class="fa-brands fa-bilibili"></i>
							선호장르
						</th>
						<td>
							<select name="t_fav">
								<option value="">= 선택 =</option>
								<option value="a">액션</option>
								<option value="d">일상물</option>
								<option value="s">학원물</option>
								<option value="c">코미디</option>
								<option value="o">기타</option>
							</select>
						</td>
					</tr>
				</table>
				</form>
				</section>
				<section class="button">
					<a href="Index'">HOME</a>
					&nbsp;&nbsp; / &nbsp;&nbsp;
					<a href="javascript:goSave()">JOIN US</a>
				</section>
			</fieldset>
				</section>
	</main>

</section>
<%@ include file="../common_footer.jsp" %>
</body>
</html>