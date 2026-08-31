<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="JSL">
  <meta name="Keywords" content="반응형홈페이지, JAVA, JSP, PHP, 대전직업전문학교, 대전국비지원, 국비무료">
  <meta name="Description" content="응용SW개발자를 위한 반응형 홈페이지">
  <title>JSL인재개발원</title>
  <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/layout.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
</head>
<body>

	<div class="sr-only">
		<p><a href="#contents">본문 바로가기</a></p>
	</div>

	<!-- 상단 네비게이션 영역 -->
	<div class="top_navigation">
		<header class="header">
			<nav class="top_left">
			  <ul>
			  	<li class="first"><a href="${pageContext.request.contextPath}/main.do">HOME</a></li>
				<li><a href="#">모집안내</a></li>
				<li><a href="#">입학상담</a></li>
				<li><a href="#">교육신청</a></li>
			  </ul>
			</nav>
			<nav class="top_right">
				<ul>
					<c:choose>
						<c:when test="${empty sessionScope.userid}">
							<li class="first"><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
							<li><a href="${pageContext.request.contextPath}/member/signup.do">회원가입</a></li>
						</c:when>
						<c:otherwise>
							<li class="first"><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
		               		<li><a href="${pageContext.request.contextPath}/member/mylist.do">마이페이지</a></li>
	               		</c:otherwise>
               		</c:choose>
				</ul>
				
			</nav>
			
			<div class="gnb_group">
				<h1 class="logo">JSL CO</h1>
				<nav class="gnb">
					<ul class="nav_1depth">
						<li><a href="gratings.html">기업소개</a>
							<ul class="nav_2depth">
								<li><a href="about/gratings.html">인사말</a></li>
								<li><a href="about/history.html">연혁</a></li>
								<li><a href="about/gratings.html">교직원소개</a></li>
								<li><a href="gallery/photo.html">대우갤러리</a></li>
								<li><a href="about/map.html">찾아오시는길</a></li>
							</ul>
						</li>
						<li><a href="${pageContext.request.contextPath}/port/list.do">포트폴리오</a>
							<ul class="nav_2depth">
								<li><a href="${pageContext.request.contextPath}/port/list.do">포트폴리오</a></li>
							</ul>
						</li>
						<li><a href="notice.html">커뮤니티</a>
							<ul class="nav_2depth">
								<li><a href="${pageContext.request.contextPath}/noti/list">공지사항</a></li>
								<li><a href="qna/qa.html">질문과답변</a></li>
								<li><a href="faq/faq.html">FAQ</a></li>
								<li><a href="pds/pds.html">자료실</a></li>
								<li><a href="adm/admin.html">관리자</a></li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
		</header>
		<div class="line"></div>
	</div>

	<!-- GNB 드롭다운 스크립트 -->
	<script>
		$(function() {
			$(".gnb>.nav_1depth>li").hover(function() {
				$(".gnb>.nav_1depth>li").removeClass("active");
				$(this).addClass("active");
				$(this).children(".nav_2depth").stop().slideDown("fast");
			}, function() {
				$(".gnb>.nav_1depth>li").removeClass("active");
				$(this).children(".nav_2depth").stop().slideUp("fast");
			});
		});
	</script>
</body>
</html>