<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*"%>
<%
List<Category> clist = (List<Category>) request.getAttribute("clist");
String mes1 = (String) request.getAttribute("mes1");
String mes2 = (String) request.getAttribute("mes2");
String mes3 = (String) request.getAttribute("mes3");
String mes4 = (String) request.getAttribute("mes4");
String mes5 = (String) request.getAttribute("mes5");
String mes6 = (String) request.getAttribute("mes6");
String mes7 = (String) request.getAttribute("mes7");
String title = (String)request.getAttribute("title");
String author = (String)request.getAttribute("author");
String publisher = (String)request.getAttribute("publisher");
String price = (String)request.getAttribute("price");
String stock = (String)request.getAttribute("stock");
String description = (String)request.getAttribute("description");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録</title>

<!-- 共通のCSSを読み込み（利用者側と同じデザイン） -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">

<!-- 外部JavaScriptファイルを読み込み -->
<script src="${pageContext.request.contextPath}/js/product.js"></script>
</head>
<body>
	<!-- 共通のヘッダーを読み込み -->
	<jsp:include page="header.jsp" />

	<div id="product-container">
		<div class="product-main">
			<!-- 右側：商品情報の入力フォーム -->
			<div id="product-info">
				<form id="product-form"
					action="${pageContext.request.contextPath}/admin/productRegister"
					method="post" enctype="multipart/form-data">

					<div id="product-image">
				<%
				if (mes1 != null) {
				%>
						<div class="mes"><%=mes1%></div>
				<%
				}
				%>
						<label for="image">商品画像：</label><br>
						<!-- ファイル選択時にJavaScriptでプレビュー表示 -->
						<input type="file" id="image" name="image" accept="image/*"
							onchange="previewImage(event)"> <br> <br>
						<!-- プレビュー表示用のimgタグ（初期状態は非表示） -->
						<img id="preview" src="#" alt="画像プレビュー"
							style="display: none; width: 300px; height: auto; object-fit: cover; border: 1px solid #ccc; padding: 5px;">
					</div>

					<!-- カテゴリ選択（プルダウン） -->
					<label for="category">カテゴリ：</label><br> <select class="select"
						name="category" id="category">
						<%
						for (Category category : clist) {
						%>
						<option value="<%=category.getId() %>"><%=category.getName()%></option>
						<%
						}
						%>
					</select><br> <br>

					<!-- タイトル入力 -->
				<%
				if (mes2 != null) {
				%>
						<div class="mes"><%=mes2%></div>
				<%
				}
				%>
					<label for="title">タイトル：</label><br> <input type="text"
						id="title" name="title" size="50" <%if(title != null){ %>value="<%=title%>"<%} %>><br> <br>

					<!-- 著者名入力 -->
				<%
				if (mes3 != null) {
				%>
						<div class="mes"><%=mes3%></div>
				<%
				}
				%>
					<label for="author">著者：</label><br> <input type="text"
						id="author" name="author" size="50" <%if(title != null){ %>value="<%=author%>"<%} %>><br> <br>
					<!-- 出版社名入力 -->
				<%
				if (mes4 != null) {
				%>
						<div class="mes"><%=mes4%></div>
				<%
				}
				%>
					<label for="author">出版社：</label><br> <input type="text"
						id="author" name="publisher" size="50"<%if(title != null){ %> value="<%=publisher%>"<%} %>><br> <br>

					<!-- 価格入力 -->
				<%
				if (mes5 != null) {
				%>
						<div class="mes"><%=mes5%></div>
				<%
				}
				%>
					<label for="price">価格（円）：</label><br> <input type="number"
						id="price" name="price" <%if(title != null){ %>value="<%=price%>"<%} %>><br> <br>

					<!-- 在庫数入力 -->
				<%
				if (mes6 != null) {
				%>
						<div class="mes"><%=mes6%></div>
				<%
				}
				%>
					<label for="stock">在庫数：</label><br> <input type="number"
						id="stock" name="stock" <%if(title != null){ %>value="<%=stock%>"<%} %>><br> <br>

					<!-- 商品詳細入力 -->
				<%
				if (mes7 != null) {
				%>
						<div class="mes"><%=mes7%></div>
				<%
				}
				%>
					<label for="description">商品詳細：</label><br>
					<textarea id="description" name="description" rows="8" cols="60" <%if(title != null){ %>value="<%=description%>"<%} %>></textarea>
					<br> <br>

					<!-- 登録ボタン -->
					<input class="submit" type="submit" value="商品を登録">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
