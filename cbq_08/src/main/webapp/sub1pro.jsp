<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<%
	request.setCharacterEncoding("UTF-8");

	Student student = new Student();
	
	student.setSyear(request.getParameter("syear"));
	student.setSclass(request.getParameter("sclass"));
	student.setSno(request.getParameter("sno"));
	student.setSname(request.getParameter("sname"));
	student.setBirth(request.getParameter("birth"));
	student.setGender(request.getParameter("gender"));
	student.setTel1(request.getParameter("tel1"));
	student.setTel2(request.getParameter("tel2"));
	student.setTel3(request.getParameter("tel3"));
	
	StudentDao dao = new StudentDao();
	
	dao.insertStudent(student);
	
	response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고교 성적관리 프로그램</title>
</head>
<body>
</body>
</html>