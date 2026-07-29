<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<%
List<Student> list = new StudentDao().selectDept();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고교 성적관리 프로그램</title>
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
	text-align: center;
}

table {
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
		<h2>반별통계</h2>

		<table>
			<tr>
				<th>학년</th>
				<th>반</th>
				<th>교사명</th>
				<th>국어총점</th>
				<th>영어총점</th>
				<th>수학총점</th>
				<th>국어평균</th>
				<th>영어평균</th>
				<th>수학평균</th>
			</tr>
			
			<%
			DecimalFormat df = new DecimalFormat("#.0");
			
			for(Student student: list) {
			%>
			<tr>
				<td><%= student.getSyear() %></td>
				<td><%= student.getSclass() %></td>
				<td><%= student.getTname() %></td>
				<td><%= student.getTkor() %></td>
				<td><%= student.getTeng() %></td>
				<td><%= student.getTmat() %></td>
				<td><%= df.format(student.getAkor()) %></td>
				<td><%= df.format(student.getAeng()) %></td>
				<td><%= df.format(student.getAmat()) %></td>
			</tr>
			<%} %>
		</table>

	</section>

	<footer>
		<p>HRDKOREA Copyright&copy;2016 All rights reserve. Human Resource
			Development Service of Korea</p>
	</footer>
</body>
</html>