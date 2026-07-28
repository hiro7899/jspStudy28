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
    function check(){
        if(my.artist_id.value==""){
            alert("참가번호가 입력되지 않았습니다");
            my.artist_id.focus();
            return false;
        }

        if(my.artist_name.value==""){
            alert("참가자명이 입력되지 않았습니다");
            my.artist_name.focus();
            return false;
        }

        if(my.artist_birth_year.value=="" || my.artist_birth_month.value=="" || my.artist_birth_month.value==""){
            alert("생년월일이 입력되지 않았습니다");
            my.artist_birth_year.focus();
            return false;
        }

        if(!my.artist_gender[0].checked && !my.artist_gender[1].checked){
            alert("성별이 선택되지 않았습니다");
            my.artist_gender.focus();
            return false;
        }

        if(my.talent.value==""){
            alert("특기가 선택되지 않았습니다");
            my.talent.focus();
            return false;
        }
        
        if(my.agency.value==""){
            alert("소속사가 입력되지 않았습니다");
            my.agency.focus();
            return false;
        }
        alert("오디션 지원자 정보가 등록되었습니다");
        return true; //호출한 곳으로 true가 리턴되면 action을 실행한다
    }
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
                        <input type="text" name="artist_birth_year" style="width: 55px;">년
                        <input type="text" name="artist_birth_month" style="width: 55px;">월
                        <input type="text" name="artist_birth_day" style="width: 55px;">일
                    </td>
                </tr>

                <tr>
                    <th>성별</th>
                    <td>
                    	<input type="radio" name="artist_gender" value="M" checked>남자
                    	<input type="radio" name="artist_gender" value="F">여자
                    </td>
                </tr>
                <tr>
                    <th>특기</th>
                    <td>
                    	<select name="talent">
                    		<option value="" selected>특기선택</option>
                    		<option value="1">댄스</option>
                    		<option value="2">랩</option>
                    		<option value="3">노래</option>
                    	</select>
                    </td>
                </tr>
                <tr>
                    <th>소속사</th>
                    <td>
                        <input type="text" name="agency">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <button type="submit">오디션등록</button>
                        <button type="reset">다시쓰기</button>
                    </td>
                </tr>

            </table>
        </form>
        
    </section>

    <footer>
        <p>HRDKOREA &copy; @2019 All rights reserved. Human Resource Development Service of Korea</p>
    </footer>
</body>
</html>