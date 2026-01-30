<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, model.*"%>
<html>
<head>
<title>カートの中身</title>

<!-- 共通CSS -->
<link rel="stylesheet" href="css/style.css">

<%-- 
<link rel="stylesheet" href="css/cart.css">--%>
</head>
<body>

	<!-- 共通ヘッダー -->
	<jsp:include page="header.jsp" />

	<!-- カート全体の外枠 -->
	<div class="parent">
		<div class="cart-container">

			<!-- タイトル -->
			<h2>カート</h2>

			<%
                // セッションからカート情報を取得
                List<Cart> cartlist = (List<Cart>) session.getAttribute("cart");

                // カートが空の場合
                if (cartlist == null) {
            %>

			<!-- 空表示 -->
			<p style="padding: 20px;">カートは空です。</p>

			<%
                } else {
                    int total = 0; // 合計金額
            %>

			<!-- 商品一覧（スクロール可能） -->
			<div class="cart-list">

				<%
                    // カート内の商品を1件ずつ表示
                    for (Cart cart : cartlist) {
                        Product p = cart.getProduct();
                        int quantity = cart.getQuantity();
                        int subtotal = p.getPrice() * quantity;
                        total += subtotal;
                        int stock = p.getStock();
                %>

				<!-- 商品1件分 -->
				<div class="cart-item">

					<!-- 商品画像 -->
					<img src="<%=request.getContextPath()%>/image/<%=p.getImg()%>"
						alt="商品画像">

					<!-- 商品情報 -->
					<div class="cart-details">

						<!-- タイトル -->
						<h3><%=p.getTitle()%></h3>

						<!-- 著者 -->
						<p>
							著者：<%=p.getAuthor()%></p>

						<!-- 価格 -->
						<p>
							￥<%=String.format("%,d", p.getPrice())%></p>

						<%
                            // 在庫がある場合のみ数量変更と削除を表示
                            if (stock > 0) {
                        %>

						<!-- 数量変更 + 削除フォーム（商品ごと） -->
						<form action="cart" method="post">

							<!-- 削除アクション -->
							<input type="hidden" name="action" value="delete">

							<!-- 商品ID -->
							<input type="hidden" name="pid" value="<%=p.getId()%>">

							数量：

							<!-- 数量セレクト（変更時に hidden を同期） -->
							<select name="quantity" class="quantity-select"
								data-price="<%=p.getPrice()%>"
								onchange="
                                        document.getElementById('shared<%=p.getId()%>').value=this.value;
                                        document.getElementById('shared-buy<%=p.getId()%>').value=this.value;
                                    ">
								<%
                                    for (int i = 1; i <= stock; i++) {
                                        String selected = (i == quantity) ? "selected" : "";
                                %>
								<option value="<%=i%>" <%=selected%>><%=i%></option>
								<%
                                    }
                                %>
							</select>

							<!-- 削除ボタン -->
							<input type="submit" value="削除">
						</form>

						<%
                            } else {
                        %>

						<!-- 在庫なし表示 -->
						<p class="out-of-stock">在庫なし</p>

						<%
                            }
                        %>

					</div>
				</div>

				<%
                    } // 商品ループ終了
                %>

			</div>
			<!-- cart-list 終了 -->

			<!-- 合計金額 + 更新フォーム + 購入フォーム -->
			<div class="cart-footer">

				<!-- 合計金額（左端） -->
				<div class="total" id="total-amount">
					合計￥<%=String.format("%,d", total)%>
				</div>

				<!-- 更新フォームと購入フォームを隣に並べるためのラッパ -->
				<div style="display: flex; gap: 10px;">

					<!-- 更新フォーム（購入の左隣） -->
					<form action="cart" method="post">

						<!-- 更新アクション -->
						<input type="hidden" name="action" value="update">

						<%
                            // 全商品の hidden を埋め込む
                            for (Cart cart : cartlist) {
                                Product p = cart.getProduct();
                        %>
						<input type="hidden" name="pid[]" value="<%=p.getId()%>">
						<input type="hidden" id="shared<%=p.getId()%>" name="quantity[]"
							value="<%=cart.getQuantity()%>">
						<%
                            }
                        %>

						<!-- 更新ボタン -->
						<input type="submit" value="更新">
					</form>

					<!-- 購入フォーム（更新の右隣） -->
					<form action="cart" method="post">

						<!-- 購入フラグ -->
						<input type="hidden" name="purchase" value="true">

						<%
                            // 購入用 hidden（ID 重複防止のため shared-buyX）
                            for (Cart cart : cartlist) {
                                Product p = cart.getProduct();
                        %>
						<input type="hidden" name="pid[]" value="<%=p.getId()%>">
						<input type="hidden" id="shared-buy<%=p.getId()%>"
							name="quantity[]" value="<%=cart.getQuantity()%>">
						<%
                            }
                        %>

						<!-- 購入ボタン -->
						<input type="submit" value="購入する">
					</form>

				</div>
				<!-- ボタンラッパ終了 -->

			</div>
			<!-- cart-footer 終了 -->

			<%
                } // カートが空でない場合の終了
            %>

		</div>
	</div>

	<!-- 合計金額を動的に更新する JS -->
	<script src="js/cartTotal.js"></script>

</body>
</html>
