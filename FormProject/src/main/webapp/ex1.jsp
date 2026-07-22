<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    h1 {
        text-align: center; 
        background: #00f;
        color: #fff;
        padding: 16px 0;
    }

    table,th,td {
        border: 1px solid #ccc;
    }

</style>
</head>
<body>
    <h1>폼태그 연습</h1>
    <form name="my" method="post" action="ex1pro.jsp">
        이름 <input type="text" name="name"><br>
        아이디 <input type="text" name="userid"><br>
        비밀번호 <input type="password" name="password"><br>
        성별 <input type="radio" name="gender" value="t">남자
        <input type="radio" name="gender" value="f">여자<br>
        <!-- checkbox 타입은 배열의 형태로 저장된다-->
        취미 <input type="checkbox" name="hobby" value="1">등산
        <input type="checkbox" name="hobby" value="2">골프
        <input type="checkbox" name="hobby" value="3">IT공부<br>

        <select name="tel">
            <option value="" selected>지역번호</option>
            <option value="042">대전</option>
            <option value="02">서울</option>
            <option value="051">부산</option>
        </select><br>
        
        <input type="submit" value="전송">
        <!-- type 이 submit 이면 form 태그 속성인 action 을 실행한다-->
        <input type="reset" value="다시쓰기">
        <input type="button" value="검색">
    </form>
</body>
</html> 