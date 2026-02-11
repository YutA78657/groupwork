<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String mes1 = (String) request.getAttribute("mes1");
String mes2 = (String) request.getAttribute("mes2");
String mes3 = (String) request.getAttribute("mes3");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規作成</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/userRegister.css">
</head>
<body>

	<!-- ロゴ表示 -->
	<div class="logo-area">
		<img src="${pageContext.request.contextPath}/image/material/icon3.png"
			alt="サイトロゴ" class="site-logo">
	</div>

	<!-- 新規作成フォーム -->
	<div class="register-container">
		<h1>新規作成</h1>

		<form action="userRegister" method="post">
			<div class="form-group">
				<%
				if (mes1 != null) {
				%>
				<div class="mes"><%=mes1%></div>
				<%
				}
				%>
				<label>ユーザーネーム：</label> <input type="text" name="name">
			</div>
			<div class="form-group">
				<%
				if (mes2 != null) {
				%>
				<div class="mes"><%=mes2%></div>
				<%
				}
				%>
				<label>メールアドレス：</label> <input type="text" name="email">
			</div>

			<div class="form-group">
				<%
				if (mes3 != null) {
				%>
				<div class="mes"><%=mes3%></div>
				<%
				}
				%>
				<label>パスワード：</label> <input type="password" name="pass">
			</div>

			<div class="form-actions">
				<input type="submit" value="作成">
			</div>
		</form>
</body>
</html>