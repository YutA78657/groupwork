<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
				<label>ユーザネーム：</label> <input type="text" name="mail">
			</div>

			<div class="form-group">
				<label>メールアドレス：</label> <input type="password" name="pass">
			</div>
			
			<div class="form-group">
				<label>住所：</label> <input type="password" name="pass">
			</div>

			<div class="form-actions">
				<input type="submit" value="変更">
			</div>
		</form>
		</div>
	</div>
	
</div>

</body>
</html>