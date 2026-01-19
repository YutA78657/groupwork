<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>ログイン</h1>

<form action="Login" method="post">
	メールアドレス：<input type="text" name="mail"><br>
	パスワード：<input type="password" name="pass"><br>
	<input type="submit" value="ログイン">
	<button type="button" onclick="location.href='/userRegister'">
    新規登録はこちら
    </button>
</form>

</body>
</html>