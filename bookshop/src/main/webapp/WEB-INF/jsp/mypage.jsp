<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User"%>
<!DOCTYPE html>
<%  User user = (User)session.getAttribute("loginUser"); %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
	<div class="parent">
		<div class="box">
			<h1>マイページ</h1>
			<div class="user">
				<form action="mypage" method="post">
					<div class="form-group">
						<label>ユーザネーム</label> <input type="text"
							value="<%=user.getName()%>">
					</div>

					<div class="form-group">
						<label>メールアドレス</label> <input type="text"
							value="<%=user.getEmail()%>">
					</div>

					<div class="form-group">
						<label>住所</label> <input type="text"
							value="<%=user.getAddress()%>">
					</div>

					<div class="form-actions">
						<input type="submit" value="変更">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="parent">
		<div class="box">
			<h1>パスワードリセット</h1>
			<div class="user">
				<form action="mypage" method="post">
					<div class="form-group">
						<label>新しいパスワード</label> <input type="password">
					</div>

					<div class="form-group">
						<label>確認</label> <input type="password">
					</div>
					<div class="form-actions">
						<input type="submit" value="変更">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="parent">
		<div class="box">
			<h1>退会</h1>
			<div class="user">
				<form action="mypage" method="post">
					<div class="form-actions">
						<input type="submit" value="アカウント削除">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>