<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*"%>
<!DOCTYPE html>
<%
User user = (User) session.getAttribute("loginUser");
List<Cart> cartlist = (List<Cart>) session.getAttribute("cart");
int price = 0;
for (Cart cart : cartlist) {
	price += cart.getProduct().getPrice() * cart.getQuantity();
}
String mes = (String)request.getAttribute("mes");
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/checkPreview.css">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="parent">
		<div class="box">
			<h2 class="title">注文内容確認</h2>
			<div class="contents">
				<div class="address">
					<div class="address_title">
						<h2>お届け先住所</h2>
						<a href="mypage" class="address_title_link">変更</a>
					</div>
					<%if(mes != null){ %>
					<div class="mes"><%=mes %></div>
					<%} %>
					<h3>郵便番号</h3>
					<p>
						<%
						if (user.getAddressNum() == null || user.getAddressNum().isEmpty()) {
						%>
						未設定
						<%
						} else {
						%>
						<%=user.getAddressNum()%>
						<%}%>
					</p>
					<h3>住所</h3>
					<p>
						<%
						if ((user.getAddress1() == null || user.getAddress1().isEmpty()) ||
								(user.getAddress2() == null || user.getAddress2().isEmpty()) ||
								(user.getAddress3() == null || user.getAddress3().isEmpty())) {
						%>
						未設定
						<%
						} else {
						%>
						<%=user.getAddress1() + user.getAddress2() + user.getAddress3()%>
						<%}%>
					</p>
				</div>
				<div class="price-box">
					<h2>ご請求額</h2>
					<h2 class="price">￥<%=String.format("%,d", price)%></h2>
				</div>
				<form class="form-area" method="post"action="check">
					<input class="btn" type="submit" value="注文を確定">
				</form>

			</div>



		</div>
	</div>

</body>
</html>