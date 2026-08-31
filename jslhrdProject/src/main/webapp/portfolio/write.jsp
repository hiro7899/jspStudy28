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
	content="반응형홈페이지,  JAVA, JSP, PHP, 대전직업전문학교, 대전국비지원, 국비무료">
<meta name="Description" content="응용SW개발자를 위한 반응형 홈페이지">
<title>JSL인재개발원</title>
</head>
<body>
	<!-- sub contents -->
	<div class="sub_title">
		<h2>포트폴리오</h2>
		<div class="container">
			<div class="location">
				<ul>
					<li class="btn_home"><a href="index.html"><i
							class="fa fa-home btn_plus"></i></a></li>
					<li class="dropdown"><a href="">포트폴리오<i
							class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="../about/gratings.html">기업소개</a> <a
								href="../portfolio/portfolio.html">포트폴리오</a> <a
								href="../notice/notice.html">커뮤니티</a>
						</div></li>
					<li class="dropdown"><a href="">포트폴리오<i
							class="fa fa-plus btn_plus"></i></a>
						<div class="dropdown_menu">
							<a href="portfolio.html">포트폴리오</a>
						</div></li>
				</ul>
			</div>
		</div>
		<!-- container end -->
	</div>

	<div class="container">
		<div class="write_wrap">
			<h2 class="sr-only">포트폴리오 글쓰기</h2>
			<form name="portfolio" method="post" enctype="multipart/form-data"
				action="${pageContext.request.contextPath}/port/writepro.do"
				onsubmit="return check()">
				<!-- action을 처리하기전에 check()사용자 함수를 실행하고 되돌아 와라-->
				<table class="bord_table">
					<caption class="sr-only">포트폴리오 입력 표</caption>
					<colgroup>
						<col width="20%">
						<col width="*">
					</colgroup>
					<tbody>

						<tr>
							<th>제목</th>
							<td><input type="text" name="title"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<button type="button" id="btn-ai">AI 글 생성</button>
								<button type="button" id="btn-translate">일본어 번역</button>
								<textarea name="content"></textarea>
							</td>
						</tr>
						<tr>
							<th>첨부</th>
							<td><input type="file" name="imgfile"></td>
						</tr>
						<tr>
							<th>글쓴이</th>
							<td><input type="text" name="name" value="${sessionScope.userid}" readonly="readonly"></td>
						</tr>
					</tbody>
				</table>
				<div class="btn_wrap">
					<input type="submit" value="저장" class="btn_ok">&nbsp;&nbsp;
					<input type="reset" value="다시쓰기" class="btn_reset">&nbsp;&nbsp;
					<input type="button" value="목록" class="btn_list"
						onClick="location.href='${pageContext.request.contextPath}/port/list.do';">
				</div>
			</form>
		</div>

	</div>
	<!-- end contents -->
	<script>
		function check() {
			if (portfolio.title.value == "") {
				alert("제목을 입력");
				portfolio.title.focus();
				return false;
			}
			if (portfolio.content.value == "") {
				alert("내용을 입력");
				portfolio.content.focus();
				return false;
			}

			if (portfolio.imgfile.value == "") {
				alert("이미지를 첨부해주세요");
				portfolio.imgfile.focus();
				return false;
			}
			if (portfolio.name.value == "") {
				alert("이름을 입력하세요");
				portfolio.name.focus();
				return false;
			}

			let filename = portfolio.imgfile.value;
			let ext = filename.substring(filename.lastIndexOf(".") + 1)
					.toLowerCase();

			if (ext != "jpg" && ext != "png" && ext != "gif" && ext != "jpeg") {
				alert("이미지 파일만 업로드 가능");
				portfolio.imgfile.focus();
				return false;
			}
			return true;
		}
	</script>
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
			$(".location  .dropdown > a").on("click",function(e) {
				e.preventDefault();
				if($(this).next().is(":visible")) {
					$(".location  .dropdown > a").next().hide();
				} else {
					$(".location  .dropdown > a").next().hide();
					$(this).next().show();
				}
			});
			
			//groq
			$("#btn-ai").click(function () {
			let title = $("input[name=title]").val();

			if (!title) {
			alert("제목 먼저 입력하세요");
			return;
			}

			$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/port/aiWrite.do",
			data: { title: title },
			success: function (res) {
			$("#content").val(res.content);
			},
			error: function () {
				alert("AI 글 생성 실패");
			}
			});
			});

			$("#btn-translate").click(function () {
				$.post("${pageContext.request.contextPath}/port/translate.do", {
					content: $("textarea[name=content]").val()
				}, function (res) {
					$("textarea[name=content]").val(res.translated);
				}, "json");
				});

			
		});
	</script>
</body>
</html>
<%@ include file="/footer.jsp"%>