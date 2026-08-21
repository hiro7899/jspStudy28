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
		<div class="board_view">
			<h2>${dto.title }</h2>
			<p class="info">
				<span class="user">${dto.name }</span> | ${dto.regdate.substring(0, 10) }
				| <i class="fa fa-eye"></i> ${dto.views }
			</p>
			<div class="board_body">
				<p>${dto.content }</p>
				<div style="text-align: center; margin-top: 20px;">
					<img src="/uploads/${dto.imgfile }" alt=""
						style="width: 30%; height: auto;">
				</div>
			</div>
			<div class="prev_next">
				<c:if test="${prevDto != null}">
					<a
						href="${pageContext.request.contextPath}/port/view.do?bno=${prevDto.bno}&type=view"
						class="btn_prev"><i class="fa fa-angle-left"></i> <span
						class="prev_wrap"> <strong>이전글</strong><span>${prevDto.title}</span>
					</span> </a>
				</c:if>
				<div class="btn_3wrap">
					<a href="${pageContext.request.contextPath}/port/list.do">목록</a> <a
						href="${pageContext.request.contextPath}/port/update.do?bno=${dto.bno}&type=update"
						onClick="return updateCheck();">수정</a> <a
						href="${pageContext.request.contextPath}/port/delete.do?bno=${dto.bno}"
						onClick="return deleteCheck();">삭제</a>
				</div>
				<c:if test="${nextDto != null}">
					<a
						href="${pageContext.request.contextPath}/port/view.do?bno=${nextDto.bno}&type=view"
						class="btn_next"> <span class="next_wrap"> <strong>다음글</strong><span>${nextDto.title}</span>
					</span> <i class="fa fa-angle-right"></i></a>
				</c:if>
			</div>
		</div>
	</div>

	<!-- end contents -->
	<script>
		function updateCheck() {
			const userid = "${sessionScope.userid}";
			const writer = "${dto.name}";
			
			if (userid === "") {
				alert("로그인 후 이용 가능합니다.");
				return false;
			} else if (userid !== writer) {
				alert("작성자만 수정할 수 있습니다.");
				return false;
			} else {
				return true;
			}
		}
	</script>
	
	<script>
		function deleteCheck() {
			const userid = "${sessionScope.userid}";
			const writer = "${dto.name}";
			
			if (userid === "") {
				alert("로그인 후 이용 가능합니다.");
				return false;
			} else if (userid !== writer) {
				alert("작성자만 삭제할 수 있습니다.");
				return false;
			} else {
				if(!confirm("정말 삭제하시겠습니까?")) {
					return false;
				}else{
					return true;
				}
			}
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
</body>
</html>
<%@ include file="/footer.jsp"%>