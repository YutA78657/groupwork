<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細 - 応用情報技術者 試験によくでる問題集</title>
<link rel="stylesheet" href="css/product.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<%
    // 仮の在庫数（DAOがまだないのでここで直接指定）
    int stock = 50; // ここを変更すればプルダウンの数が変わり、0なら在庫なし表示
    %>

	<div id="product-container">
		<div class="product-main">
			<div id="product-image">
				<img id="book_img" src="image/9784297153335_600.jpg" alt="書籍の画像">
			</div>

			<div id="product-info">
				<p class="category">
					<a href="Category">資格・試験</a>
				</p>
				<h2 class="title">令和08-09年 応用情報技術者 試験によくでる問題集【科目A】</h2>
				<p class="author">著者：大滝 みや子</p>
				<p class="price">￥2,640 </p>

				<form id="product-form" action="" method="post">
					<%
                        if (stock > 0) {
                    %>
					<label for="quantity">個数：</label> <select class="select" name="quantity"
						id="quantity">
						<%
                            for (int i = 1; i <= stock; i++) {
                        %>
						<option value="<%=i%>"><%=i%></option>
						<%
                            }
                        %>
					</select> <input class="submit" type="submit" value="カートへ追加">
					<%
                        } else {
                    %>
					<p class="out-of-stock">在庫なし</p>
					<%
                        }
                    %>
				</form>
			</div>
		</div>

		<hr class="divider">

		<div id="product-description">
			<p><h3>商品詳細</h3></p><p>【計482問収録】テーマ別問題集で科目A試験を徹底対策。
				応用情報技術者の科目A試験（旧午前試験）によくでる問題を厳選し、ていねいに解説したテーマ別問題集です。
				最新の応用情報技術者本試験問題はもちろん、前身であるⅠ種、ソフトウェア開発技術者や高度試験出題問題など、
				広範な問題を徹底的に分析して頻出傾向問題を選り抜いています。
				解説には図解を多く配置し、あいまいな部分を残すことなく苦手分野を集中的にトレーニングできます。</p>
		</div>
	</div>
</body>
</html>
