<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>

<%
	Dao dao = new Dao();
	List<Dto> list = dao.selectRank();
	DecimalFormat df = new DecimalFormat("#.00");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오디션 관리 프로그램</title>
<script>

</script>
<style>
    *{margin: 0; padding: 0;}
    header{
        background-color: #00f;
        text-align: center;
        padding: 20px 0px;
    }
    h1{
        color: #fff;
    }
    nav{
        background: #777;
        padding: 10px 0;
    }
    
    a{
        text-decoration: none;
        margin: 12px 10px;
        color: #fff;
    }
   	
    section{
        margin: 0 auto;
        padding: 50px 30px;
        height: 500px;
    }

    section>p{
        margin: 20px 0;
    }

    h2{
        text-align: center;
        margin-bottom: 20px;
    }
    
    footer{
    	background: #cff;
        text-align: center;
        padding: 20px 0;
    }
    
    table, th, td{
        border: 1px solid #ccc;
        text-align: center;
    }

    table{
        width: 600px;
        margin: 0 auto;
    }
    
</style>

</head>
<body>
    <header>
        <h1>(과정평가형 정보처리산업기사)오디션 관리 프로그램 ver 2009-06</h1>
    </header>
    
    <nav>
        <a href="sub1.jsp">오디션 등록</a>
        <a href="sub2.jsp">참가자목록조회</a>
        <a href="sub3.jsp">멘토점수조회</a>
        <a href="sub4.jsp">참가자등수조회</a>
        <a href="index.jsp">홈으로</a>
    </nav>

    <section>
        <h2>참가자 등수 조회</h2>
		
		<table>
            <tr>
                <th>참가번호</th>
                <th>참가자명</th>
                <th>성별</th>
                <th>총점</th>
                <th>평균</th>
                <th>등수</th>
            </tr>

			<%	
			for(Dto d : list){ 
				String gender = d.getArtistGender().equals("M") ? "남성" : "여성";
			%>
            <tr>
                <td><%= d.getArtistId() %></td>
                <td><%= d.getArtistName() %></td>
                <td><%= gender %></td>
                <td><%= d.getTpoint() %></td>
                <td><%= df.format(d.getApoint()) %></td>
                <td><%= d.getRank() %></td>
            </tr>
            <%} %>
        </table>
       
    </section>

    <footer>
        <p>HRDKOREA &copy; @2019 All rights reserved. Human Resource Development Service of Korea</p>
    </footer>
</body>
</html>