<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>
<%	List<Book> books = (List<Book>)request.getAttribute("books");%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/top.css">
</head>
<body>
<jsp:include page="header.jsp" />

<div id="parent">
<%	for(Book book:books){ %>
	<div class="book">
		<div class="book_img_area">
			<img class="book_img" src="image/9784297153335_600.jpg">
		</div>
		<div class="book_text_area">
			<p class="title"><a href="https://www.hanmoto.com/bd/isbn/9784297153335#"><%=book.getTitle()%></a></p>
			<p class="auther"><%=book.getAuther()%></p>
			<p class="price">￥<%=book.getPrice()%></p>
		</div>
			
		<div>
		</div>
	</div>	
<% } %>
</div>
</body>
</html>