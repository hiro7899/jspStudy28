<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>태그연습</title>
<style>
    *{
        margin: 0;
        padding: 0;
    }

    div{
        border: 1px solid #ccc;
        padding: 20px;
    }

    table, th, td{
        border: 1px solid #ccc;
    }

    th, td {
        padding: 12px;
    }
    table {
        margin: 0 auto;
    }
</style>
</head>
<body>
    <div>
        <form action="">
            <table>
                <tr>
                    <th>아이디</th>
                    <td><input type="text" name="userid"></td>
                </tr>
                <tr>
                    <th>패스워드</th>
                    <td><input type="password" name="pw"></td>
                </tr>
                <tr>
                    <th>성별</th>
                    <td>
                        <input type="radio" name="gender" value="t">남성
                        <input type="radio" name="gender" value="f">여성
                    </td>
                </tr>
                <tr>
                    <th>취미</th>
                    <td>
                        <input type="checkbox" name="hobby" value="1">골프
                        <input type="checkbox" name="hobby" value="2">등산
                    </td>
                </tr>
                <tr>
                    <th>전화</th>
                    <td>
                        <select name="tel">
                            <option value="">지역</option>
                            <option value="042">대전</option>
                            <option value="051">부산</option>
                            <option value="02">서울</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input type="submit" value="전송">
                        <input type="reset" value="다시쓰기">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>