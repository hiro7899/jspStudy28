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

        if (my.sname.value=="") {
			alert("이름이 입력되지 않았습니다!");
            my.sname.focus();
            return false;
		}

        if (my.birth.value=="") {
			alert("생년월일이 입력되지 않았습니다!");
            my.birth.focus();
            return false;
		}

        if (!my.gender[0].checked && !my.gender[1].checked) {
			alert("성별을 선택하세요!");
            my.gender[0].focus();
            return false;
		}

        if (my.tel1.value=="" || my.tel2.value=="" || my.tel3.value=="") {
			alert("전화번호가 입력되지 않았습니다!");
            my.tel1.focus();
            return false;
		}
        
        alert("학생등록이 정상적으로 등록되었습니다!")
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
		<h2>학생등록</h2>

		<form name="my" action="sub1pro.jsp" method="post" onsubmit="return check()">
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
					<th>이름</th>
					<td><input type="text" name="sname"></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input type="text" name="birth">(예)20190301</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<input type="radio" name="gender" value="M">남성
						<input type="radio" name="gender" value="F">여성
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="text" name="tel1" style="width: 80px">- 
						<input type="text" name="tel2" style="width: 80px">- 
						<input type="text" name="tel3" style="width: 80px">
					</td>
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