<%@page import="common.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp" %>
<script>
	function goWrite() {
		if(checkEmpty(board.t_title, "제목을 입력하세요.")) return;
		if(checkEmpty(board.t_content, "내용을 입력하세요.")) return;
		
		// 1.확장자 검사
		/*
		var fileName = noti.t_attach.value;
		if(fileName != ""){ //  C:\fakepath\img_1.png
			var pathFileName = fileName.lastIndexOf(".")+1;    //확장자 제외한 경로+파일명
			var extension = (fileName.substr(pathFileName)).toLowerCase();	//확장자명
			//파일명.확장자
			if(extension != "pdf" && extension != "hwp" && extension != "png"){
				alert(extension +" 형식 파일은 업로드 안됩니다. 한글, PDF, PNG 파일만 가능!");
				return;
			}		
		}
		*/
		
		// 2.첨부 용량 체크	
		var file = board.t_attach;
		var fileMaxSize  = 10; // 첨부 최대 용량 설정(mb)
		if(file.value !=""){
			// 사이즈체크
			var maxSize  = 1024 * 1024 * fileMaxSize;
			var fileSize = 0;
			// 브라우저 확인
			var browser=navigator.appName;
			// 익스플로러일 경우
			if (browser=="Microsoft Internet Explorer"){
				var oas = new ActiveXObject("Scripting.FileSystemObject");
				fileSize = oas.getFile(file.value).size;
			}else {
			// 익스플로러가 아닐경우
				fileSize = file.files[0].size;
			}

			if(fileSize > maxSize){
				alert(" 첨부파일 사이즈는 "+fileMaxSize+"MB 이내로 등록 가능합니다. ");
				return;
			}	
		}		
		
		//board.t_gubun.value = "write";
		board.method="post";
		board.action="Board?t_gubun=write"; // 첨부파일 form 사용시 GET 방식으로 구분 값을 직접 넘김 
		board.submit();
	}
</script>
<section id="container">
	<main class="main">
	<fieldset>
		<section class="board">
		<h1>BOARD</h1>
			<form name="board" enctype="multipart/form-data">
			<input type="hidden" name="t_gubun" />
			<table class="board-table" style="margin-bottom: 10px; width: 40%;">
				<colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col width="*" />
				</colgroup>
				<tr>
					<td class="th-td">제목</td>
					<td colspan="3">
						<input type="text" name="t_title" size="70" style="width: 90%"/>
					</td>
				</tr>
				<tr>
					<td class="th-td">내용</td>
					<td colspan="3">
						<textarea name="t_content" rows="30" cols="70" style="width: 90%; resize: none;"></textarea>
					</td>
				</tr>
				<tr>
					<td class="th-td">첨부</td>
					<td colspan="3">
						<input type="file" name="t_attach" />
					</td>
				</tr>
				<tr>
					<td class="th-td">글쓴이</td>
					<td>${sessionName}</td>
					<td class="th-td">등록일자</td>
					<td>${today}</td>
				</tr>

			</table>
			</form>
			<section class="button">
				<a href="Board">LIST</a>
				&nbsp;&nbsp; / &nbsp;&nbsp;
				<a href="javascript:goWrite()">SUBMIT</a>
			</section>
		</section>
	</fieldset>
	</main>

</section>
<%@ include file="../common_footer.jsp" %>
</body>
</html>