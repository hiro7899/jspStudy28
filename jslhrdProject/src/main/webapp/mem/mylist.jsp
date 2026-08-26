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
				<form name="myform" action="${pageContext.request.contextPath}/member/mylist.do">
					<select name="type" class="select">
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select> <input type="text" name="keyword" class="search_word">
					<button class="btn_search">
						<i class="fa fa-search"></i><span class="sr-only">검색버튼</span>
					</button>
				</form>
			</div>
		</div>
		<!-- search end -->
		<div class="bord_list">
			<ul class="basic_board">
				<c:if test="${empty list}">
					<div style="text-align: center; padding:100px 0;">
						<h2 style="font-size: 32px">찜한 글이 없습니다</h2>
					</div>
				</c:if>
				<c:forEach var="item" items="${list}">
					<li class="portlist-context">
						<span class="date"> <em>
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
							<span style="padding: 30px 0;"> <input
								type="checkbox" class="portCheckBox" value="${item.mbno}"
								style="width: 20px; height: 20px;">
								<button class="deleteBtn" data-mbno="${item.mbno}">삭제</button>
							</span>
						</div>
					</li>
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
			
			$(".deleteBtn").on("click", function(){
			    const mbno = $(this).data("mbno");
			    const $this = $(this);
			    
			    const $checkbox = $this.siblings(".portCheckBox");
			    if(!$checkbox.is(":checked")) {
			    	alert("삭제할 항목을 먼저 체크해주세요");
			    	return;
			    }
			    $.ajax({
			        url: "${pageContext.request.contextPath}/member/mydelete.do",
			        type: "post",
			        data: { mbno: mbno },
			        success: function(res){
			            if(res.trim() === "success"){
			                $this.closest(".portlist-context").remove();
			            } else {
			                alert("삭제 실패했습니다");
			            }
			        }, 
			        error: function(xhr, status, error){
			            alert("통신 중 오류가 발생했습니다.");
			        }
			    });
			});
			
		});
	</script>
</body>
</html>

<%@ include file="/footer.jsp"%>