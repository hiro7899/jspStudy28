<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>

<%
	//요청
	request.setCharacterEncoding("UTF-8");
	String artistId = request.getParameter("artist_id");
	String artistName = request.getParameter("artist_name");
	String artistBirth = request.getParameter("artist_birth_year") 
			+ request.getParameter("artist_birth_month")
			+ request.getParameter("artist_birth_day");
	String artistGender = request.getParameter("artist_gender");
	String talent = request.getParameter("talent");
	String agency = request.getParameter("agency");
	
	Dto dto = new Dto();
	dto.setArtistId(artistId);
	dto.setArtistName(artistName);
	dto.setArtistBirth(artistBirth);
	dto.setArtistGender(artistGender);
	dto.setTalent(talent);
	dto.setAgency(agency);
	
	Dao dao = new Dao();
	dao.insert(dto);
	
	response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>