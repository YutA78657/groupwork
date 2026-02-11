<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*"%>
<%Book book = (Book) request.getAttribute("book"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集 - <%=book.getTitle()%></title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

<!-- プレビュー用JavaScript（任意） -->
<script>
	function previewImage(event) {
		const preview = document.getElementById('preview');
		const file = event.target.files[0];
		if (file) {
			preview.src = URL.createObjectURL(file);
			preview.style.display = 'block';
		} else {
			preview.src = '#';
			preview.style.display = 'none';
		}
	}
</script>
</head>
<body>
	<%
	List<Category> clist = (List<Category>) request.getAttribute("clist");
	String mes1 = (String) request.getAttribute("mes1");
	String mes2 = (String) request.getAttribute("mes2");
	String mes3 = (String) request.getAttribute("mes3");
	String mes4 = (String) request.getAttribute("mes4");
	String mes5 = (String) request.getAttribute("mes5");
	String mes6 = (String) request.getAttribute("mes6");
	String mes7 = (String) request.getAttribute("mes7");
	%>
	<!-- 共通ヘッダー -->
	<jsp:include page="header.jsp" />

	<!-- 外部JavaScriptファイルを読み込み -->
	<script src="js/product.js"></script>

	<div id="product-container">
		<div class="product-main">

			<!-- 左：画像表示と変更 -->


			<!-- 右：商品情報フォーム -->







			<div id="product-info">
				<form id="product-form" action="product" method="post"
					enctype="multipart/form-data">
					<div id="product-image">
						<label for="image">商品画像：</label><br> <img id="preview"
							src="${pageContext.request.contextPath}/image/<%=book.getImg() %>"
							alt="商品画像"
							style="width: 300px; height: auto; object-fit: cover; border: 1px solid #ccc; padding: 5px;"><br>
						<br> <input type="file" id="image" name="image"
							accept="image/*" onchange="previewImage(event)">
					</div>
					<!-- カテゴリ（プルダウン） -->
					<label for="category">カテゴリ：</label><br> <select class="select"
						name="category" id="category">
						<%
						for (Category category : clist) {
						%>
						<option value="<%=category.getId()%>"
							<%if (category.getName().equals(book.getCname())) {%> selected
							<%}%>><%=category.getName()%></option>
						<%
						}
						%>
					</select><br> <br>

					<!-- タイトル -->
					<%
				if (mes2 != null) {
				%>
						<div class="mes"><%=mes2%></div>
				<%
				}
				%>
					<label for="title">タイトル：</label><br> <input type="text"
						id="title" name="title" size="50" value="<%=book.getTitle()%>"><br>
					<br>

					<!-- 著者 -->
					<%
				if (mes3 != null) {
				%>
						<div class="mes"><%=mes3%></div>
				<%
				}
				%>
					<label for="author">著者：</label><br> <input type="text"
						id="author" name="author" size="50" value="<%=book.getAuthor()%>"><br>
					<br>
					
					
					<%
				if (mes4 != null) {
				%>
						<div class="mes"><%=mes4%></div>
				<%
				}
				%>
					<label for="publisher">出版社：</label><br> <input type="text"
						id="publisher" name="publisher" size="50" value="<%=book.getPublisher() %>"><br>
					<br>

					<!-- 価格 -->
					<%
				if (mes5 != null) {
				%>
						<div class="mes"><%=mes5%></div>
				<%
				}
				%>
					<label for="price">価格（円）：</label><br> <input type="number"
						id="price" name="price" value="<%=book.getPrice()%>"><br>
					<br>

					<!-- 在庫数 -->
					<%
				if (mes6 != null) {
				%>
						<div class="mes"><%=mes6%></div>
				<%
				}
				%>
					<label for="stock">在庫数：</label><br> <input type="number"
						id="stock" name="stock" value="<%=book.getStock()%>"><br>
					<br>

					<!-- 商品詳細 -->
					<%
				if (mes7 != null) {
				%>
						<div class="mes"><%=mes7%></div>
				<%
				}
				%>
					<label for="description">商品詳細：</label><br>
					<textarea id="description" name="description" rows="8" cols="60"><%=book.getDescription()%></textarea>
					<br> <br>

					<!-- 更新ボタン -->
					<input type="hidden" name="id" value="<%=book.getPid()%>">
					<input type="hidden" name="action" value="update"> <input
						class="submit" type="submit" value="商品を更新">


				</form>
				<form  action="product" method="post">
					<input class="submit" type="hidden" name="action" value="reco"> 
					<input type="hidden" name="id" value="<%=book.getPid()%>"> 
					<input
						class="submit" type="submit" <%if (book.isRecommend() == false) {%>
						value="おすすめに追加" <%} else {%> value="おすすめから削除" <%}%>>
				</form>
				
				
				<form  action="product" method="post">
					<input class="submit" type="hidden" name="action" value="delete"> 
					<input type="hidden" name="id" value="<%=book.getPid()%>"> 
					<input
						class="submit" type="submit" value="商品を削除">
				</form>

			</div>
		</div>
	</div>
</body>
</html>
