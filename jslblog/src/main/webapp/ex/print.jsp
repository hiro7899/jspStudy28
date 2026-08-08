<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="dto.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원정보</h2>
	<table>
		<tr>
			<th>아이디</th>
			<td>${exdto.getId()}</td>
		</tr>
		<tr>
			<th>패스워드</th>
			<td>${exdto.getPw()}</td>
		</tr>
		<tr>
			<th>관심분야</th>
			<td> 
				<c:forEach var="lang" items="${exdto.getLang()}">
					${lang}
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>정보공개여부</th>
			<td>${exdto.getAnswer()}</td>
		</tr>
		<tr>
			<td colspan="2">
				<!--  <a href="./insertForm.do">다시입력</a> -->
				<button type="button" id="reinsert" onclick="alert('다시입력합니다'); location.href = 'insertForm.do';">다시입력</button>
			</td>
		</tr>
	</table>

</body>
</html>