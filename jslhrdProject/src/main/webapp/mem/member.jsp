<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="JSL">
<meta name="Keywords"
	content="반응형홈페이지,  JAVA, JSP, PHP, 대전직업전문학교, 대전국비지원, 국비무료">
<meta name="Description" content="응용SW개발자를 위한 반응형 홈페이지">
<title>JSL인재개발원</title>
</head>
<body>
<!-- sub contents -->
	<div class="sub_title">
		<h2>회원가입</h2>
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
		<div class="con_title">
			<h1>내정보(개인회원)</h1>
			<p>HOME / 마이페이지 / 내정보(개인회원)</p>
		</div>
		<div class="join_write col_989">
			<div class="list_con">
				<ul class="icon_type1">
					<li>회원정보는 개인정보 취급방침에 따라 안전하게 보호되며 회원님의 명백한 동의 없이 공개 또는 제3자에게
						제공되지 않습니다.</li>
				</ul>
			</div>
			<form name="my" id="myform">
				<table class="table_write02" summary="회원가입을 위한 이름, 아이디, 비밀번호, 비밀번호확인, 소속, 유선전화번호, 휴대전화번호, 이메일, 주소, 본인확인질문, 본인확인답, 주활용사이트, 알림여부 정보 입력">
					<caption>회원가입을 위한 정보입력표</caption>
					<colgroup>
						<col width="160px">
						<col width="auto">
					</colgroup>
					<tbody id="joinDataBody">
						<tr>
							<th><label for="name">이름</label></th>
							<td><input type="text" name="writer" id="writer" class="w300">
								<p id="writermsg"></p></td>
						</tr>
						<tr>
							<th><label for="id">아이디</label></th>
							<td>
								<input type="text" name="userid" id="userid" class="w300">
								<p id="useridmsg"></p>
							</td>
						</tr>
						<tr>
							<th><label for="password">비밀번호</label></th>
							<td><input type="password" name="password" id="password" class="w300">
								<p id="passwordmsg"></p>
							</td>
						</tr>
						<tr>
							<th><label for="checkpassword">비밀번호확인</label></th>
							<td>
								<input type="password" name="checkpassword" id="checkpassword" class="w300">
								<p id="checkpasswordmsg"></p>
							</td>
						</tr>
						<tr>
							<th><label for="phone">전화번호</label></th>
							<td>
								<input type="text" name="phone" id="phone" class="w300">
								<p id="phonemsg"></p>
							</td>
						</tr>
						<tr>
							<th><label for="email">메일인증<span class="must"><b>필수입력</b></span></label></th>
							<td>
								<input type="email" name="email" id="email" class="w300">
								<input type="button" value="메일인증" id="btnemail">
								<p id="emailmsg"></p>
								<input type="password" name="checkauthentication" style="width: 207px;">
								<input type="button" value="인증확인" id="checkauthentication">
								<p id="authmsg">
							</td>
						</tr>
						
						<tr>
							<td colspan="2" style="text-align: center;">
								<button type="submit">회원가입 전송</button>
								<button type="reset">다시쓰기</button>
								<button type="button" onclick="location.href='/'">돌아가기</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
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
			$("#userid").blur(function(){
				if(!$("#userid").val()) {
					$("#useridmsg").html("<span style = 'color: #f00';> 아이디는 필수 입력사항 입니다 </span>");
					$("#userid").focus();
					return;
				}else {
					$("#useridmsg").html("");
				}
				$.ajax({
					type: "post",
					url: "${pageContext.request.contextPath}/member/useridcheck.do",
					data: {userid:$("#userid").val()},
					success: function(res) {
						if(res < 0){
							if($("#userid").val() != ""){
								$("#useridmsg").html("<span style = 'color: #f00';> 사용가능한 아이디 입니다. </span>");
							}
						}else {
							if($("#userid").val() != ""){
								$("#useridmsg").html("<span style = 'color: #f00';> 사용할 수 없는 아이디 입니다. </span>");
								$("#userid").val("");
								$("#userid").focus();
							}
						}
					},error: function() {
						alert("통신에러");
					}
				});
			});
			
			$("#myform").submit(function(e){
				e.preventDefault();
				
				let isvalid = true;
				
				const pwd = $("#password").val();
			    const pwdCheck = $("#checkpassword").val();
				
				$("#writermsg, #passwordmsg, #checkpassword, #phonemsg, #emailmsg").html("");
				
				if(!$("#writer").val()) {
					$("#writermsg").html("<span style = 'color: #f00';> 이름은 필수 입력사항 입니다 </span>");
					$("#writer").focus();
					isvalid = false;
				}
				if (!pwd) {
			        $("#passwordmsg").html("<span style='color: #f00;'>비밀번호는 필수 입력사항 입니다</span>");
			        if (isvalid) $("#password").focus();
			        isvalid = false;
			    } else if (pwd !== pwdCheck) {
			        $("#checkpasswordmsg").html("<span style='color: #f00;'>비밀번호가 일치하지 않습니다</span>");
			        if (isvalid) $("#checkpassword").focus();
			        isvalid = false;
			    }
				
				if(!$("#phone").val()) {
					$("#phonemsg").html("<span style = 'color: #f00';> 전화번호는 필수 입력사항 입니다 </span>");
					$("#phone").focus();
					isvalid = false;
				}
				
				if(!$("#email").val()) {
					$("#emailmsg").html("<span style = 'color: #f00';> 이메일은 필수 입력사항 입니다 </span>");
					$("#email").focus();
					isvalid = false;
				}
				
				if(!isvalid){
					e.preventDefault();
				}else {
					$(this).attr("action", "${pageContext.request.contextPath}/member/signuppro.do");
					$(this).attr("method", "post");
					this.submit();
				}
			});
		});
	</script>
</body>
</html>
<%@ include file="../footer.jsp"%>