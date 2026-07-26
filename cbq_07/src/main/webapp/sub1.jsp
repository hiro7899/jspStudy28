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
        <h2>오디션 등록</h2>

        <form action="sub1pro.jsp" method="post" name="my" onsubmit="return check()">
            <table>
                <tr>
                    <th>참가번호</th>
                    <td>
                        <input type="text" name="artist_id">*참가번호는(A000)4자리입니다
                    </td>
                </tr>

                <tr>
                    <th>참가자명</th>
                    <td>
                        <input type="text" name="artist_name">
                    </td>
                </tr>

                <tr>
                    <th>생년월일</th>
                    <td>
                        <input type="text" name="artist_birth_year">년
                        <input type="text" name="artist_birth_month">월
                        <input type="text" name="artist_birth_day">일
                    </td>
                </tr>

                <tr>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th></th>
                    <td></td>
                </tr>

            </table>
        </form>
        
    </section>

    <footer>
        <p>HRDKOREA &copy; @2019 All rights reserved. Human Resource Development Service of Korea</p>
    </footer>
</body>
</html>