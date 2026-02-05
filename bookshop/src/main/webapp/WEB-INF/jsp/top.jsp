<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.*,java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>

<%
    // コントローラから渡されたデータを受け取る
    List<Book> recobooks = (List<Book>) request.getAttribute("recBooks");
    List<CategorySet> categorysets = (List<CategorySet>) request.getAttribute("CategoryBooks");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Book Shop - Top</title>

<!-- 各種CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/top.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/chat.css">

<!-- JS -->
<script src="${pageContext.request.contextPath}/js/top.js" defer></script>
</head>

<body>

    <!-- ヘッダー -->
    <jsp:include page="header.jsp" />

    <div class="parent">

        <!-- おすすめスライドショー -->
        <div class="slideshow">
            <div class="category-title"><h1>おすすめ</h1></div>
            <button class="prev">&#10094;</button>
            <button class="next">&#10095;</button>

            <div class="slidesbox">
                <% for (Book book : recobooks) { %>
                <div class="book-area">
                    <div class="book">
                        <div class="book_img_area">
                            <img class="book_img" src="image/<%=book.getImg()%>">
                        </div>
                        <div class="book_text_area">
                            <p class="title">
                                <a href="product?pid=<%=book.getPid()%>"><%=book.getTitle()%></a>
                            </p>
                            <p class="auther"><%=book.getAuthor()%></p>

                            <div id="cart-btn-box">
                                <p class="price">￥<%=String.format("%,d",book.getPrice())%></p>
                                <form action="cart" method="post">
                                    <input type="hidden" name="action" value="add">
                                    <input type="hidden" name="pid" value="<%=book.getPid()%>">
                                    <input type="hidden" name="quantity" value="1">
                                    <input type="submit" value="カートに入れる">
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </div>

        <!-- カテゴリ別スライドショー -->
        <% for (CategorySet set : categorysets) {
               List<Book> books = set.getBookList();
        %>
        <div class="slideshow">
            <div class="category-title"><h1><%=set.getCategoryName()%></h1></div>
            <button class="prev">&#10094;</button>
            <button class="next">&#10095;</button>

            <div class="slidesbox">
                <% for (Book book : books) { %>
                <div class="book-area">
                    <div class="book">
                        <div class="book_img_area">
                            <img class="book_img" src="image/<%=book.getImg()%>">
                        </div>
                        <div class="book_text_area">
                            <p class="title">
                                <a href="product?pid=<%=book.getPid()%>"><%=book.getTitle()%></a>
                            </p>
                            <p class="auther"><%=book.getAuthor()%></p>

                            <div id="cart-btn-box">
                                <p class="price">￥<%=String.format("%,d",book.getPrice())%></p>
                                <form action="cart" method="post">
                                    <input type="hidden" name="action" value="add">
                                    <input type="hidden" name="pid" value="<%=book.getPid()%>">
                                    <input type="hidden" name="quantity" value="1">
                                    <input type="submit" value="カートに入れる">
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
        <% } %>

    </div>

    <!-- ★ AIチャットを好きな場所に表示できるように include -->
    <jsp:include page="chat.jsp" />

</body>
</html>
