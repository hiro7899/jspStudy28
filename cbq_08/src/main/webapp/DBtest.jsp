<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
	Connection conn = DBmanager.getInstance();
	
	if(conn != null){
		out.print("접속 성공");
	}else{
		out.print("실패");
	}
	%>

</body>
</html>