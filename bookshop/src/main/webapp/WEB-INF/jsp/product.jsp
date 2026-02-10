<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*"%>
<!DOCTYPE html>
<%  Book book = (Book)request.getAttribute("book"); %>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細 - 応用情報技術者 試験によくでる問題集</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/product.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<%
    // 仮の在庫数（DAOがまだないのでここで直接指定）
    int stock = book.getStock(); // ここを変更すればプルダウンの数が変わり、0なら在庫なし表示
    %>

	<div id="product-container">
		<div class="product-main">
			<div id="product-image">
				<img id="book_img" src="image/<%=book.getImg() %>" alt="書籍の画像">
			</div>

			<div id="product-info">
				<p class="category">
					<a href="Category"><%=book.getCname() %></a>
				</p>
				<h2 class="title"><%=book.getTitle() %></h2>
				<p class="author">著者：<%=book.getAuthor() %></p>
				<p class="publisher">出版社：<%=book.getPublisher() %></p>
				<p class="price">￥<%=book.getPrice() %> </p>

				<form id="product-form" action="cart" method="post">
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
                    <input type="hidden" name="pid" value="<%=book.getPid()%>">
                    <input type="hidden" name="action" value="add">
				</form>
			</div>
		</div>

		<hr class="divider">

		<div id="product-description">
			<p><h3>商品詳細</h3></p><p><%=book.getDescription() %></p>
		</div>
	</div>
</body>
</html>
