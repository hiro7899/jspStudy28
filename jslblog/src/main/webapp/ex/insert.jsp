<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="my" method="post" action="memberWrite.do">
		<input type="text" name="id" placeholder="아이디 입력"><br>
		<input type="password" name="pw" placeholder="패스워드 입력"><br>
		<input type="checkbox" name="language" value="java">자바
		<input type="checkbox" name="language" value="jsp">JSP
		<input type="checkbox" name="language" value="html">화면구현
		<input type="checkbox" name="language" value="oracle">오라클DB <br>
		<input type="radio" name="answer" value="yes">예
		<input type="radio" name="answer" value="no">아니오<br>
		<button type="submit">전송</button>
	</form>
</body>
</html>