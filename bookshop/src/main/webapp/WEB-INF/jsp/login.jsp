<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%String mes = (String)request.getAttribute("mes"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>

	<!-- ロゴ表示 -->
	<div class="logo-area">
		<img src="${pageContext.request.contextPath}/image/material/icon2.png"
			alt="サイトロゴ" class="site-logo">
	</div>

	<!-- ログインフォーム -->
	<div class="login-container">
		<h1>ログイン</h1>
		<%if(mes != null){ %>
		<div class="mes"><%=mes %></div>
		<%} %>
		<form action="login" method="post">
			<div class="form-group">
				<label>メールアドレス：</label> <input type="text" name="email">
			</div>

			<div class="form-group">
				<label>パスワード：</label> <input type="password" name="pass">
			</div>

			<div class="form-actions">
				<input type="submit" value="ログイン">
				<button type="button" onclick="location.href='userRegister'">
					新規登録へ</button>
			</div>
		</form>
	</div>

</body>
</html>
