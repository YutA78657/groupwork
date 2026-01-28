<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="model.*,java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>
<%
List<Book> recobooks = (List<Book>) request.getAttribute("recBooks");
List<CategorySet> categorysets = (List<CategorySet>) request.getAttribute("CategoryBooks");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/top.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<script src="${pageContext.request.contextPath}/js/top.js" defer></script>
</head>
<body>
	<jsp:include page="header.jsp" />


	<div class="parent">


		<div class="slideshow">
			<h1>おすすめ</h1>
			<button class="prev">&#10094;</button>
			<button class="next">&#10095;</button>
			<div class="slidesbox">

				<%
				for (Book book : recobooks) {
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
								<form action="cart" method="post">
									<input type="hidden" name="action" value="add">
									<input type="hidden" name="pid" value="<%=book.getPid()%>">
									<input type="submit" value="カートに入れる">
								</form>
							</div>

						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>
		</div>

<%-- 

		<%
		for (CategorySet set : categorysets) {
			List<Book> books = set.getBookList();
		%>
		<div class="slideshow">
			<h1><%=set.getCategoryName()%></h1>
			<button class="prev">&#10094;</button>
			<button class="next">&#10095;</button>
			<div class="slidesbox">

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
				<%
				}
				%>
			</div>
		</div>
		<%}%>--%>
	</div>
</body>
</html>