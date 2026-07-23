<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>민원신청</title>
<style>
    * {
        margin: 0;
        padding: 0;
    }
    header{
        background: #00f;
        padding: 20px 0;
    }
    
    nav{
        background: #0cf;
        padding: 12px 0;
    }

    a{
        text-decoration: none;
	        color: #fff;
        padding: 0 18px;
    }
    
    h1{
    	color: #fff;
        text-align: center;
    }

    h2{
        text-align: center;
    }

    table, th, td {
        border: 1px solid #ccc;
    }

    table{
        width: 600px;
        margin: 0 auto;
    }
</style>
<script>
    function check() {
        if(my.area.value == ""){
            alert("지역을 선택하세요");
            my.area.focus();
            return false;
        }
        if(my.writer.value == ""){
            alert("작성자 입력은 필수입니다");
            my.writer.focus();
            return false;
        }
        if(!my.id[0].checked && !my.id[1].checked){
            alert("아이디 공개여부를 선택하세요");
            my.id[0].focus();
            return false;
        }
        if(!my.agree.checked){
            alert("약관동의 여부는 필수입니다");
            my.agree.focus();
            return false;
        }
        alert("입력저장 완료 되었습니다");
        return true;
    }
</script>

</head>

<body>
    <header>
        <h1>정보처리 산업기사 2027-02</h1>
    </header>

    <nav>
        <a href="ex1.jsp">민원등록</a>
        <a href="ex2.jsp">민원목록</a>
        <a href="https://www.naver.com">민원안내</a>
        <a href="ex3.jsp">홈</a>
    </nav>

    <h2>민원신청</h2>
    <form name="my" method="post" action="minwon.jsp" onsubmit="return check();">
        <table>
            <tr>
                <th>지역선택</th>
                <td>
                    <select name="area">
                        <option value="">선택</option>
                        <option value="043">충북</option>
                        <option value="042">대전</option>
                    </select>
                </td>
            </tr>

            <tr>
                <th>작성자</th>
                <td>
                    <input type="text" name="writer">
                </td>
            </tr>

            <tr>
                <th>작성일</th>
                <td>
                    <input type="date" name="wdate">
                </td>
            </tr>

            <tr>
                <th>내용</th>
                <td>
                    <textarea name="content"></textarea>
                </td>
            </tr>

            <tr>
                <th>아이디공개여부</th>
                <td>
                    <input type="radio" name="id" value="t">적용
                    <input type="radio" name="id" value="f">비적용
                </td>
            </tr>

            <tr>
                <th>비밀번호 입력</th>
                <td>
                    <input type="password" name="pw">
                </td>
            </tr>

            <tr>
                <th>약관 동의 여부</th>
                <td>
                    <input type="checkbox" name="agree" value="1">약관동의
                </td>
            </tr>

            <tr>
                <td colspan="2" style="text-align: center;">
                    <button type="submit">전송</button>
                    <button type="reset">다시쓰기</button>
                </td>
            </tr>
        </table>

    </form>

</body>
</html>