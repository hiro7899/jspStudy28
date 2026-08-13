<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="JSL">
<meta name="Keywords"
	content="반응형홈페이지, JAVA, JSP, PHP, 대전직업전문학교, 대전국비지원, 국비무료">
<meta name="Description" content="응용SW개발자를 위한 반응형 홈페이지">
<title>JSL인재개발원</title>
</head>
<body>
	<!-- sub contents -->
	<div class="sub_title">
		<h2>접속자 로그인</h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="index.html"><i
							class="fa fa-home btn_plus"></i></a></li>
					<li class="dropdown"><a href="">커뮤니티<i
							class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="gratings.html">공지사항</a> <a href="allclass.html">학과및모집안내</a>
							<a href="portfolio.html">포트폴리오</a> <a href="online.html">온라인접수</a>
							<a href="notice.html">커뮤니티</a>
						</div></li>
					<li class="dropdown"><a href="">공지사항<i
							class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="notice.html">공지사항</a> <a href="qa.html">질문과답변</a> <a
								href="faq.html">FAQ</a>
						</div></li>
				</ul>
			</div>
		</div>
		<!-- container end -->
	</div>

	<div class="container">
		<div class="member_boxL">
			<h2>개인회원</h2>
			<div class="login_form">
				<form id="frmLogin">
					<div class="fl_clear">
						<label for="userid">아이디</label>
						<input name="userid" id="userid" type="text">
					</div>
					<div class="fl_clear">
						<label for="password">비밀번호</label>
						<input name="password" id="password" type="password">
					</div>
					<!-- <a class="btn_login btn_Blue" href="javascript:fn_login();">로그인</a> -->
					<button type="button" id="loginBtn" class="btn_login btn_Blue">로그인</button>
					<div style="padding-top: 30px;">
						<input type="checkbox" name="useridcheck" id="saveid" style="width: 16px; height: 16px; margin-right: 10px">아이디 저장
					</div>
					<div id="errmsg" style="padding-top: 30px; color: #f00;"></div>
				</form>
			</div>
		</div>
	</div>
	<!-- end contents -->

	<script>
		$(function() {
			$(".location  .dropdown > a").on("click", function(e) {
				e.preventDefault();
				if ($(this).next().is(":visible")) {
					$(".location  .dropdown > a").next().hide();
				} else {
					$(".location  .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
			
		});
	</script>
	
	<script>
		$(function() {
			if ($.cookie("saveid")) {
				$("#userid").val(savedId);
				$("#saveid").prop("checked", true);
			}
			
			$("#loginBtn").on("click", function() {
				let userid = $("#userid").val();
				let password = $("#password").val();
				
				$.ajax({
					type: "post",
					url: "${pageContext.request.contextPath}/member/loginpro.do",
					data: {userid:userid, password:password},
					success: function(data){
						if(data === "success"){
							if($("#saveid").prop("checked")){
								//아이디 쿠키에 저장
								$.cookie("saveid", userid, {expires: 7});
							}else{
								//쿠키 삭제
								$.removeCookie("saveid");
							}
							
							location.href="${pageContext.request.contextPath}/main.do";
						} else{
							$("#errmsg").text("아이디 또는 패스워드를 확인하세요");
						}
					}, error: function(){
						alert("통신 실패");
					}
				});
				
			});
		});
	</script>
</body>
</html>
<%@ include file="/footer.jsp"%>