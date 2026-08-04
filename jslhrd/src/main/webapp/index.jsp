<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSLHRD</title>
<link href="css/common.css" rel="stylesheet">
<link href="css/mystyle.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<div class="header-box">
		<div class="header">
	        <div class="top-left">
	            <ul class="left-list">
	                <li><a href="index.do">HOME</a></li>
	                <li><a href="index.jsp">모집안내</a></li>
	                <li><a href="index.jsp">입학상담</a></li>
	                <li><a href="index.jsp">교육신청</a></li>
	            </ul>
	        </div>
	        <div class="top-right">
	            <ul class="right-list">
	                <li><a href="login.do">로그인</a></li>
	                <li><a href="member.do">회원가입</a></li>
	            </ul>
	        </div>
	    </div>
	</div>
	<header class="header">
		<h1 class="logo">JSLHRD</h1>
		<nav>
			<ul class="menu">
				<li>
					<a href="">기업소개</a>
					<ul class="sub-menu">
						<li><a href="">인사말</a></li>
						<li><a href="">오시는길</a></li>
					</ul>
				</li>
				<li>
					<a href="">포트폴리오</a>
					<ul class="sub-menu">
						<li><a href="">웹/앱개발</a></li>
						<li><a href="">UI/UX</a></li>
						<li><a href="">DB설계</a></li>
					</ul>
				</li>
				<li>
					<a href="">커뮤니티</a>
					<ul class="sub-menu">
						<li><a href="">질문답변</a></li>
						<li><a href="">FAQ</a></li>
						<li><a href="">메일보내기</a></li>
						<li><a href="">AI상담</a></li>
					</ul>
				</li>
			</ul>
		</nav>
    </header>
    <div class="visual">
    	<div class="inner">
    		<p class="vtitle">
	        국가와 청년실업자를 위한 <br>
	        <strong>취업선두</strong>중심기관
	        </p>
	        <p class="vtxt">
	        	지식, 기술, 태도를 공유하고 가치를 창출하는 역동적인 정보처리 중심기관으로 <br>
	        	산학연 공동체와 함께 성장하며 국가 혁신 성장의 밑거름이 되겠습니다
	        </p>
    	</div>
    </div>

	<section class="news-group">
		<div class="news-title">
			<p class="sub-title">배움의 즐거움이 있는곳</p>
			<h2>JSL COLEGE 소식</h2>
			<p class="text">JSL인재개발원 다양한 소식을 확인 하실 수 있습니다</p>
			<a href="">READ MORE</a>
		</div>

		<div class="news-list">
			<ul>
				<li>
					<img src="img/news1.jpg" alt="">
					<strong>28기 화면 구현중</strong>
					<p>즐거운 날도 코딩, 슬픈날에도 코딩을 열심히 배우는중</p>
					<span>2027-08-24</span>
				</li>
				<li>
					<img src="img/news2.jpg" alt="">
					<strong>28기 화면 구현중</strong>
					<p>즐거운 날도 코딩, 슬픈날에도 코딩을 열심히 배우는중</p>
					<span>2027-08-24</span>
				</li>
				<li>
					<img src="img/news3.jpg" alt="">
					<strong>28기 화면 구현중</strong>
					<p>즐거운 날도 코딩, 슬픈날에도 코딩을 열심히 배우는중</p>
					<span>2027-08-24</span>
				</li>
			</ul>
		</div>
	</section>

	<script>
		$(function() {
			$(".menu > li").mouseenter(function() {
				$(this).children(".sub-menu").stop().slideDown(300);
			});

			$(".menu > li").mouseleave(function() {
				$(this).children(".sub-menu").stop().slideUp(300);
			});
		});
	</script>
		
	<script>
	/*
		const menu = document.querySelectorAll(".menu > li");
		
		menu.forEach(function(item){
		
		    item.addEventListener("mouseenter", function(){
		        this.querySelector(".sub-menu")?.classList.add("active");
		    });
		
		    item.addEventListener("mouseleave", function(){
		        this.querySelector(".sub-menu")?.classList.remove("active");
		    });
		
		});
	*/
	
	</script>
</body>
</html>