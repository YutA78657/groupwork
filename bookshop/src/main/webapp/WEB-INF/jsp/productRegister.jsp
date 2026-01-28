<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録 - 管理者画面</title>

<!-- 共通のCSSを読み込み（利用者側と同じデザイン） -->
<link rel="stylesheet" href="css/product.css">

<!-- 外部JavaScriptファイルを読み込み -->
<script src="js/product.js"></script>
</head>
<body>
	<!-- 共通のヘッダーを読み込み -->
	<jsp:include page="header.jsp" />

	<div id="product-container">
		<div class="product-main">
			<!-- 左側：画像アップロードとプレビュー -->
			<div id="product-image">
				<label for="image">商品画像：</label><br>
				<!-- ファイル選択時にJavaScriptでプレビュー表示 -->
				<input type="file" id="image" name="image" accept="image/*"
					onchange="previewImage(event)"> <br>
				<br>
				<!-- プレビュー表示用のimgタグ（初期状態は非表示） -->
				<img id="preview" src="#" alt="画像プレビュー"
					style="display: none; width: 300px; height: auto; object-fit: cover; border: 1px solid #ccc; padding: 5px;">
			</div>

			<!-- 右側：商品情報の入力フォーム -->
			<div id="product-info">
				<form id="product-form" action="RegisterProductServlet"
					method="post" enctype="multipart/form-data">

					<!-- カテゴリ選択（プルダウン） -->
					<label for="category">カテゴリ：</label><br> <select class="select"
						name="category" id="category">
						<option value="資格・試験">資格・試験</option>
						<option value="ビジネス">ビジネス</option>
						<option value="文学">文学</option>
						<option value="趣味・実用">趣味・実用</option>
						<option value="コンピュータ">コンピュータ</option>
					</select><br>
					<br>

					<!-- タイトル入力 -->
					<label for="title">タイトル：</label><br> <input type="text"
						id="title" name="title" size="50"><br>
					<br>

					<!-- 著者名入力 -->
					<label for="author">著者：</label><br> <input type="text"
						id="author" name="author" size="50"><br>
					<br>

					<!-- 価格入力 -->
					<label for="price">価格（円）：</label><br> <input type="number"
						id="price" name="price"><br>
					<br>

					<!-- 在庫数入力 -->
					<label for="stock">在庫数：</label><br> <input type="number"
						id="stock" name="stock"><br>
					<br>

					<!-- 商品詳細入力 -->
					<label for="description">商品詳細：</label><br>
					<textarea id="description" name="description" rows="8" cols="60"></textarea>
					<br>
					<br>

					<!-- 登録ボタン -->
					<input class="submit" type="submit" value="商品を登録">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
