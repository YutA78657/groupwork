<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*"%>

<%
    List<Cart> cartlist = (List<Cart>) session.getAttribute("cart");
    int stock = 0;  
    if(cartlist != null){
        for(Cart cart : cartlist){
            stock += cart.getQuantity();
        }
    }

    String word = (String)request.getAttribute("word");
    if(word == null){
        word = "";
    }
%>

<%
	List<Category> clist = (List<Category>)request.getAttribute("clist");
	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<header>
    <div id="header-div">
        <button id="menu-btn" class="header-btn">≡</button>

        <a id="icon" href="${pageContext.request.contextPath}/index">
            <img src="${pageContext.request.contextPath}/image/material/icon2.png">
        </a>

        <form id="search" action="search" method="get">
            <select id="search-type" name="categoryName">
                <option value="">すべて</option>
                <% 
                for(Category category:clist){
                %>
                <option><%=category.getName() %></option>
                
                
                <%	
                }
                %>
            </select>
            <input id="search-area" type="text" name="word" value="<%=word %>">
            <input id="search-btn" type="submit" class="header-btn" value="">
        </form>

        <!-- ▼ 修正ポイント：カート画像に専用クラスを付与 ▼ -->
        <div id="cart-btn" class="header-btn">
            <a href="cart" class="cart-link">
                <img src="${pageContext.request.contextPath}/image/material/cart2.png" class="header-cart-img">
                <span class="overlay-text"><%=stock %></span>
            </a>
        </div>
        <!-- ▲ 修正ポイント ▲ -->

    </div>

    <div id="menu-box">
        
        
	<%User user = (User)session.getAttribute("loginUser");
      if(user != null && user.isAdmin()){
	%>
		<h2>管理者メニュー</h2>
		<a href="${pageContext.request.contextPath}/admin/user"><p>ユーザ管理</p></a>
		<a href="${pageContext.request.contextPath}/admin/productRegister"><p>商品登録</p></a>
		<a href="${pageContext.request.contextPath}/admin/order"><p>注文一覧</p></a>
        <a href="${pageContext.request.contextPath}/logout"><p>ログアウト</p></a>
	<%
      }else if(user != null){
    %>
    	<h2><%=user.getName() %>さん</h2>
        <a href="${pageContext.request.contextPath}/mypage"><p>マイページ</p></a>
        <a href="${pageContext.request.contextPath}/order"><p>注文履歴</p></a>
        <a href="${pageContext.request.contextPath}/logout"><p>ログアウト</p></a>
    <% 
      }else{
    %>
    	<a href="${pageContext.request.contextPath}/login"><p>ログイン</p></a>
        <a href="${pageContext.request.contextPath}/mypage"><p>マイページ</p></a>
        <a href="${pageContext.request.contextPath}/order"><p>注文履歴</p></a>
        <a href="${pageContext.request.contextPath}/logout"><p>ログアウト</p></a>
    <%
      }
    %>    
    </div>

    <div id="overlay"></div>
</header>

<script>
    const menu_btn = document.querySelector("#menu-btn");
    const menu_box = document.querySelector("#menu-box");
    const overlay = document.querySelector("#overlay");

    menu_btn.addEventListener("click", () => {
        menu_box.classList.toggle("active");
        overlay.classList.toggle("active");
    });

    document.addEventListener("click", (e) => {
        if (!menu_btn.contains(e.target) && !menu_box.contains(e.target)) {
            menu_box.classList.remove("active");
            overlay.classList.remove("active");
        }
    });
</script>

</body>
</html>
