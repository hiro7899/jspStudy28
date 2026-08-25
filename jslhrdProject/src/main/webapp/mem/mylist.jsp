<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="var"%>
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
		<h2>찜한 목록</h2>
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
		<div class="search_wrap">
			<div class="record_group">
				<p>
					총게시글<span>120</span>건
				</p>
			</div>
			<div class="search_group">
				<form name="myform" action="">
					<select name="sel" class="select">
						<option value="1">제목</option>
						<option value="2">내용</option>
					</select> <input type="text" name="search" class="search_word">
					<button class="btn_search">
						<i class="fa fa-search"></i><span class="sr-only">검색버튼</span>
					</button>
				</form>
			</div>
		</div>
		<!-- search end -->
		<div class="bord_list">
			<ul class="basic_board">
				<c:forEach var="item" items="${list}">
					<li><span class="date"> <em>
								${item.regdate.substring(8,10)} </em> ${item.regdate.substring(0,8)}
					</span>
						<div class="text_wrap">
							<div class="img_wrap">
								<img src="/uploads/${item.imgfile}" alt="">
							</div>
							<span class="info"> <span class="blue_text">No.${item.pbno}</span>
								<i class="bar"></i> <i class="fa fa-eye"></i> ${item.views}
							</span>
							<p class="title">
								<a
									href="${pageContext.request.contextPath}/port/view.do?bno=${item.pbno}&type=view">${item.title}</a>
							</p>
							<span class="text"> ${item.content} </span>
						</div></li>	
				</c:forEach>
			</ul>
			<div class="paging">
				<a href=""><i class="fa  fa-angle-double-left"></i></a> <a href=""><i
					class="fa fa-angle-left"></i></a> <a href="" class="active">1</a> <a
					href="">2</a> <a href="">3</a> <a href="">4</a> <a href="">5</a> <a
					href=""><i class="fa fa-angle-right"></i></a> <a href=""><i
					class="fa  fa-angle-double-right"></i></a> <a
					href="${pageContext.request.contextPath}/port/write.do"
					class="btn_write">글쓰기</a>
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
</body>
</html>

<%@ include file="/footer.jsp"%>