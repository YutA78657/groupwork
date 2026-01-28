<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集 - 管理者画面</title>

<!-- 共通のCSSを読み込み -->
<link rel="stylesheet" href="css/product.css">

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

	<!-- 共通ヘッダー -->
	<jsp:include page="header.jsp" />

	<!-- 外部JavaScriptファイルを読み込み -->
	<script src="js/product.js"></script>

	<div id="product-container">
		<div class="product-main">

			<!-- 左：画像表示と変更 -->
			<div id="product-image">
				<label for="image">商品画像：</label><br> <img id="preview"
					src="image/9784297153335_600.jpg" alt="商品画像"
					style="width: 300px; height: auto; object-fit: cover; border: 1px solid #ccc; padding: 5px;"><br>
				<br> <input type="file" id="image" name="image"
					accept="image/*" onchange="previewImage(event)">
			</div>

			<!-- 右：商品情報フォーム -->
			<div id="product-info">
				<form id="product-form" action="UpdateProductServlet" method="post"
					enctype="multipart/form-data">

					<!-- カテゴリ（プルダウン） -->
					<label for="category">カテゴリ：</label><br> <select class="select"
						name="category" id="category">
						<option value="資格・試験" selected>資格・試験</option>
						<option value="ビジネス">ビジネス</option>
						<option value="文学">文学</option>
						<option value="趣味・実用">趣味・実用</option>
						<option value="コンピュータ">コンピュータ</option>
					</select><br>
					<br>

					<!-- タイトル -->
					<label for="title">タイトル：</label><br> <input type="text"
						id="title" name="title" size="50"
						value="令和08-09年 応用情報技術者 試験によくでる問題集【科目A】"><br>
					<br>

					<!-- 著者 -->
					<label for="author">著者：</label><br> <input type="text"
						id="author" name="author" size="50" value="大滝 みや子"><br>
					<br>

					<!-- 価格 -->
					<label for="price">価格（円）：</label><br> <input type="number"
						id="price" name="price" value="2640"><br>
					<br>

					<!-- 在庫数 -->
					<label for="stock">在庫数：</label><br> <input type="number"
						id="stock" name="stock" value="0"><br>
					<br>

					<!-- 商品詳細 -->
					<label for="description">商品詳細：</label><br>
					<textarea id="description" name="description" rows="8" cols="60">【計482問収録】テーマ別問題集で科目A試験を徹底対策。
応用情報技術者の科目A試験（旧午前試験）によくでる問題を厳選し、ていねいに解説したテーマ別問題集です。
最新の応用情報技術者本試験問題はもちろん、前身であるⅠ種、ソフトウェア開発技術者や高度試験出題問題など、
広範な問題を徹底的に分析して頻出傾向問題を選り抜いています。
解説には図解を多く配置し、あいまいな部分を残すことなく苦手分野を集中的にトレーニングできます。</textarea>
					<br>
					<br>

					<!-- 更新ボタン -->
					<input class="submit" type="submit" value="商品を更新">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
