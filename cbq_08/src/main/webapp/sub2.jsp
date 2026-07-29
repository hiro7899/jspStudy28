<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고교 성적관리 프로그램</title>
<script>
	function check() {
		if (my.syear.value=="") {
			alert("학년정보가 입력되지 않았습니다!");
            my.syear.focus();
            return false;
		}

        if (my.sclass.value=="") {
			alert("반정보가 입력되지 않았습니다!");
            my.sclass.focus();
            return false;
		}

        if (my.sno.value=="") {
			alert("번호정보가 입력되지 않았습니다!");
            my.sno.focus();
            return false;
		}
        
        if (my.kor.value=="") {
			alert("국어점수가 입력되지 않았습니다!");
            my.kor.focus();
            return false;
		}
        
        if (my.eng.value=="") {
			alert("영어점수가 입력되지 않았습니다!");
            my.eng.focus();
            return false;
		}
        
        if (my.mat.value=="") {
			alert("수학점수가 입력되지 않았습니다!");
            my.mat.focus();
            return false;
		}

        alert("성적입력이 정상적으로 등록되었습니다!")
        return true;
	}
</script>

<style>
* {
	margin: 0;
	padding: 0;
}

header {
	background: #00f;
	padding: 20px 0;
}

h1 {
	color: #fff;
	text-align: center;
}

nav {
	background-color: #555;
	padding: 18px;
}

a {
	color: #fff;
	padding: 0 18px;
	text-decoration: none;
}

section {
	height: 590px;
}

section>h2 {
	text-align: center;
	padding: 20px;
}

section>p {
	padding: 0 20px;
}

footer {
	background: #00f;
	padding: 20px;
}

footer>p {
	text-align: center;
}

table, th, td {
	border: 1px solid #ccc;
}
table{
	width: 600px;
	margin: 0 auto;
}
</style>
</head>
<body>
	<header>
		<h1>(과정평가형 정보처리산업기사)고교성적관리프로그램ver2019-06</h1>
	</header>
	<nav>
		<a href="sub1.jsp">학생등록</a> <a href="sub2.jsp">성적입력</a> <a
			href="sub3.jsp">성적조회</a> <a href="sub4.jsp">반별통계</a> <a
			href="index.jsp">홈으로</a>
	</nav>

	<section>
		<h2>성적등록</h2>

		<form name="my" action="sub2pro.jsp" method="post" onsubmit="return check()">
			<table>
				<tr>
					<th>학년</th>
					<td><input type="text" name="syear">(예)1</td>
				</tr>
				<tr>
					<th>반</th>
					<td><input type="text" name="sclass">(예)01</td>
				</tr>
				<tr>
					<th>번호</th>
					<td><input type="text" name="sno">(예)01</td>
				</tr>
				<tr>
					<th>국어</th>
					<td><input type="text" name="kor">점수범위(0~100)</td>
				</tr>
				<tr>
					<th>영어</th>
					<td><input type="text" name="eng">점수범위(0~100)</td>
				</tr>
				<tr>
					<th>수학</th>
					<td><input type="text" name="mat">점수범위(0~100)</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<button type="submit">학생등록</button>
						<button type="reset">다시쓰기</button>
					</td>
				</tr>
			</table>
		</form>

	</section>

	<footer>
		<p>HRDKOREA Copyright&copy;2016 All rights reserve. Human Resource
			Development Service of Korea</p>
	</footer>
</body>
</html>