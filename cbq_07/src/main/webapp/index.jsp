<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>

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
    }
    
    footer{
    	background: #cff;
        text-align: center;
        padding: 20px 0;
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
        <h2>과정평가형 자격 CBQ</h2>

        <p>국가직무능력표준(NCS: National Competency Standard)으로 설계된 교육*훈련과정을 체계적으로 이수하고 내외부 평가를 거쳐 취득하는 국가자격입니다.</p>
        <p>산업현장 중심의 교육평가로 더 커지는 능력</p>
        <p>알고 있는 것에 할 수 있는 것을 더하는</p>
        <p>과정평가형 자격은</p>
        <p>현장 중심형 인재육성을 지원 합니다</p>
    </section>

    <footer>
        <p>HRDKOREA &copy; @2019 All rights reserved. Human Resource Development Service of Korea</p>
    </footer>
</body>
</html>