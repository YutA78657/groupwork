<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.*,java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>
<%List<Book> books = (List<Book>) request.getAttribute("books"); %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/top.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<div class="parent">
	<div class="box">
		<%
		for (Book book : books) {
		%>
		<div class="book-area">
			<div class="book">
				<div class="book_img_area">
					<img class="book_img" src="image/9784297153335_600.jpg">
				</div>
				<div class="book_text_area">
					<p class="title">
						<a href="https://www.hanmoto.com/bd/isbn/9784297153335#"><%=book.getTitle()%></a>
					</p>
					<p class="auther"><%=book.getAuther()%></p>
					<div id="cart-btn-box">
						<p class="price">
							￥<%=book.getPrice()%></p>
						<form action="" method="">
							<input type="submit" value="カートに入れる">
						</form>
					</div>

				</div>
			</div>
		</div>
		<% } %>
	</div>
</div>
</body>
</html>