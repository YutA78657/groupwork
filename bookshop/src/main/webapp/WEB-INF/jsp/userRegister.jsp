<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>

	<!-- ロゴ表示 -->
	<div class="logo-area">
		<img src="${pageContext.request.contextPath}/image/material/icon2.png"
			alt="サイトロゴ" class="site-logo">
	</div>

	<!-- 新規作成フォーム -->
	<div class="register-container">
		<h1>新規作成</h1>

		<form action="" method="post">
			<div class="form-group">
				<label>ユーザーネーム：</label> <input type="text" name="name">
			</div>
			<div class="form-group">
				<label>メールアドレス：</label> <input type="text" name="mail">
			</div>

			<div class="form-group">
				<label>パスワード：</label> <input type="password" name="pass">
			</div>

			<div class="form-actions">
				<input type="submit" value="作成">
			</div>
		</form>
</body>
</html>