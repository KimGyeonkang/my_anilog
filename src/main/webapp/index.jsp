<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common_header.jsp" %>
<script>
	function goBoardView(no) {
		view.t_no.value = no;
		view.t_gubun.value = "view";
		view.method="post";
		view.action="Board";
		view.submit();
	}
</script>
<form name="view">
	<input type="hidden" name="t_no"/>
	<input type="hidden" name="t_gubun"/>
</form>
<section id="container">
	<main class="main">
		<section class="about">
			<section class="about_img">
				<img src="Gemini_Generated_Owner.png" width="200" height="200" />
			</section>
			<section class="about-content">
				<section class="about-header">
					<h2>About This Site</h2>
					<p style="margin-bottom:10px;">Welcome to my animation review blog</p>
				</section>
				<section class="about-text">
					<p>
						제 개인 블로그에 방문해주셔서 감사합니다.<br>
						본 사이트는 각종 일본 애니메이션이나 서브컬처를 주인장 나름의 관점으로 자유롭게 풀어보기 위한 블로그입니다.<br>
						재밌게 봐 주셨으면 좋겠습니다!<br>
						* 이미지는 생성형 AI Gemini를 활용해 만든, 주인장의 오너 캐릭터입니다.
					</p>
				</section>
			</section>
		</section>
		
		<hr class="divider">
		
		<section class="border-section">
			<section class="post-title">
				<h2>Board (Total: ${totalCount})</h2>
			</section>
			<ul class="post-list">
				<li class="first-post">
					<section>
						<img src="Gemini_Generated_Owner.png" width="100" height="100" />
					</section>
					<section class="first-post-content">
						<a href="javascript:goBoardView('${list[0].getNo()}')">
							<h3>
								<c:choose>
									<c:when test="${fn:length(list[0].getTitle()) > 15}">
										${fn:substring(list[0].getTitle(), 0, 15)}...
									</c:when>
									<c:otherwise>${list[0].getTitle()}</c:otherwise>
								</c:choose>
								(${list[0].getReg_date()})
							</h3>
						</a>
						<a>
							<p>
								<c:choose>
									<c:when test="${fn:length(list[0].getContent()) > 15}">
										${fn:substring(list[0].getContent(), 0, 15)}...
									</c:when>
									<c:otherwise>${list[0].getContent()}</c:otherwise>
								</c:choose>
							</p>
						</a>
					</section>
				</li>
				<section class="sub-post-list">
				<c:forEach items="${subList}" var="sub">
					<li class="text-post">
						<a href="javascript:goBoardView('${sub.getNo()}')">
							<span>${sub.getTitle()}</span>
						</a>
						<span>${sub.getReg_date()}</span>
					</li>
				</c:forEach>
					<li style="text-align: right;">
						<p>
							<a href="Board">▶More</a>
						</p>
					</li>
				</section>
			</ul>
		</section>
	</main>

</section>
<%@ include file="common_footer.jsp" %>
</body>
</html>