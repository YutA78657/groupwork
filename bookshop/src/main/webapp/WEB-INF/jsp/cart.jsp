<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, model.Product"%>
<html>
<head>
<title>カートの中身</title>
<link rel="stylesheet" href="css/cart.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<!-- カート全体を囲むコンテナ -->
	<div class="cart-container">
		<h2>カート</h2>

		<%
      List<Product> productList = (List<Product>) session.getAttribute("productList");
      Map<Integer, Integer> quantityMap = (Map<Integer, Integer>) session.getAttribute("quantityMap");

      if (productList == null || productList.isEmpty()) {
    %>
		<p style="padding: 20px;">カートは空です。</p>
		<%
      } else {
        int total = 0;
    %>

		<!-- 商品一覧をスクロール可能にする -->
		<div class="cart-list">
			<%
        for (Product p : productList) {
          int quantity = quantityMap.get(p.getId()) != null ? quantityMap.get(p.getId()) : 0;
          int subtotal = p.getPrice() * quantity;
          total += subtotal;
          int stock = 5; // 仮の在庫数
      %>

			<!-- 商品1件分の表示 -->
			<div class="cart-item">
				<!-- 商品画像 -->
				<img src="<%=request.getContextPath()%>/image/<%=p.getImg()%>"
					alt="商品画像">

				<!-- 商品情報と操作 -->
				<div class="cart-details">
					<h3><%=p.getTitle()%></h3>
					<p>
						著者：<%=p.getAuthor()%></p>
					<p>
						￥<%=String.format("%,d", p.getPrice())%></p>

					<% if (stock > 0) { %>
					<!-- 数量変更と削除 -->
					<form action="cart" method="post">
						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="productId" value="<%=p.getId()%>">

						数量：
						<!-- 数量セレクトにクラスと価格データ属性を追加 -->
						<select name="quantity" class="quantity-select"
							data-price="<%=p.getPrice()%>">
							<% for (int i = 1; i <= stock; i++) {
                   String selected = (i == quantity) ? "selected" : ""; %>
							<option value="<%=i%>" <%=selected%>><%=i%></option>
							<% } %>
						</select> <input type="submit" value="削除">
					</form>
					<% } else { %>
					<p class="out-of-stock">在庫なし</p>
					<% } %>
				</div>
			</div>
			<% } %>
		</div>

		<!-- 合計金額と購入・更新ボタン -->
		<div class="cart-footer">
			<!-- 合計金額にIDを追加してJavaScriptで更新できるようにする -->
			<div class="total" id="total-amount">
				合計￥<%=String.format("%,d", total)%>
			</div>

			<!-- 購入・更新ボタンを横並びで表示 -->
			<form action="purchase" method="post"
				style="display: flex; gap: 10px;">
				<!-- 更新ボタン（仮のsubmit） -->
				<input type="submit" value="更新" name="update">
				<!-- 購入ボタン -->
				<input type="submit" value="購入する" name="purchase">
			</form>
		</div>

		<% } %>
	</div>

	<!-- 外部JavaScriptファイルを読み込む -->
	<script src="js/cartTotal.js"></script>
</body>
</html>
