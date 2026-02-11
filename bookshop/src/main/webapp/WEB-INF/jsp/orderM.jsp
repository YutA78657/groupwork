<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="model.*,java.util.ArrayList,java.util.List"%>

<!DOCTYPE html>

<%
// Controller から渡された注文セット（注文 + 注文商品一覧）
List<OrderSet> orderSets = (List<OrderSet>) request.getAttribute("orderSets");
User user = (User) session.getAttribute("loginUser");
%>

<html>
<head>
<meta charset="UTF-8">
<title>注文履歴</title>

<!-- CSS 読み込み -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/orderM.css">

</head>

<body>

	<!-- 共通ヘッダー -->
	<jsp:include page="header.jsp" />

	<div class="parent">
		<div class="box">

			<!-- ページタイトル（中央寄せ） -->
			<div class="title-box">
				<%
				if (user.isAdmin()) {
				%>
				<h1>注文一覧</h1>
				<%
				} else {
				%>
				<h1>注文履歴</h1>
				<%
				}
				%>
			</div>

			<div class="order-box">
				<%
				if (orderSets == null || orderSets.isEmpty()) {
				%>

				<div
					style="text-align: center; padding: 40px; font-size: 20px; color: #555;">
					注文履歴はありません</div>

				<%
				} else {
				%>


				<%
				// 注文セットを1件ずつ表示
				for (OrderSet orderSet : orderSets) {

					// 注文情報（注文ID、日付、ステータス）
					Orders order = orderSet.getOrder();

					// 注文商品の一覧を取得
					List<OrderItemView> orderItems = orderSet.getOrderItem();

					// 合計金額計算用
					int totalPrice = 0;
				%>

				<!-- ▼▼▼ 1件の注文カード ▼▼▼ -->
				<div class="order">

					<!-- 注文番号 + 注文日（横並び） -->
					<div class="order-header">
						<h1>
							注文番号：<%=order.getId() %></h1>
						<h1>配送予定日：<%=order.getDeliveryDate() %></h1>
					</div>
					
					<!-- 上段の区切り線 -->
					<div class="order-header-line"></div>

					<!-- 商品一覧（スクロール可能） -->
					<div class="order-items">

						<%
						// 商品ごとに表示
						for (OrderItemView orderItem : orderItems) {

							// 合計金額（小計）を加算
							totalPrice += orderItem.getPrice() * orderItem.getQuantity();

							// 画像名が null の場合に備えて安全に処理
							String imgName = orderItem.getImg();
							if (imgName == null || imgName.isEmpty()) {
								imgName = "noimage.png"; // フォールバック画像
							} else {
								imgName = imgName.replaceFirst("^/", ""); // 先頭の / を除去
							}
						%>

						<!-- ▼ 商品1つ分のブロック ▼ -->
						<div class="order-item">

							<!-- 商品画像（トップページと同じ image フォルダを使用） -->
							<img src="${pageContext.request.contextPath}/image/<%=imgName%>"
								class="item-img">

							<!-- 商品情報 -->
							<div class="item-info">
								<h2><%=orderItem.getName()%></h2>

								<p>
									￥<%=String.format("%,d", totalPrice)%>
								</p>

								<p>
									数量：<%=orderItem.getQuantity()%></p>
							</div>

						</div>
						<!-- ▲ 商品ブロック終了 ▲ -->

						<%
						} // 商品ループ終了
						%>

					</div>
					<!-- 商品一覧終了 -->

					<!-- 下段（合計金額・発送状況・キャンセルボタン） -->
					<div class="order-footer">

						<!-- 合計金額（黒・大きめ） -->
						<div class="total-price">
							合計金額：￥<%=String.format("%,d", totalPrice)%>
						</div>

						<!-- 発送状況（「発送状況：」は黒、ステータス名だけ色付き） -->
						<div class="status">


							<form id="order-form" action="order" method="post">
								発送状況： <span> <select class="status-select" name="status">
										<option <%if (order.getStatus().equals("発送準備中")) {%> selected
											<%}%>>発送準備中</option>
										<option <%if (order.getStatus().equals("発送済み")) {%> selected
											<%}%>>発送済み</option>
										<option <%if (order.getStatus().equals("配達中")) {%> selected
											<%}%>>配達中</option>
										<option <%if (order.getStatus().equals("配達完了")) {%> selected
											<%}%>>配達完了</option>
										<option <%if (order.getStatus().equals("キャンセル")) {%> selected
											<%}%>>キャンセル</option>
								</select>
								</span> <input type="hidden" name="orderId" value="<%=order.getId()%>">
								<button type="submit" class="cancel-btn">更新</button>
							</form>
						</div>

						<!-- キャンセルボタン（サーブレットへPOST） -->


					</div>

				</div>
				<!-- ▲▲▲ 注文カード終了 ▲▲▲ -->

				<%
				} // 注文ループ終了
				%>
				<%
				} // 注文なしブロック終了
				%>

			</div>
		</div>
	</div>

</body>
</html>
