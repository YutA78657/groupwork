<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, model.*"%>
    <%List<Cart> cartlist = (List<Cart>) session.getAttribute("cart");
      int stock = 0;  
      if(cartlist != null){
      	for(Cart cart:cartlist){
      		stock += cart.getQuantity();
      	
      	}
      }
      String word = (String)request.getAttribute("word");
      if(word == null){
      	word = "";
      }%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<header>
	<div id="header-div">
		<button id="menu-btn" class="header-btn">≡</button>
		<a id="icon" href="index"><img src="image/material/icon2.png"></a>
		<form id="search" action="SearchServlet" method="get">
			<select id="search-type" naem="categoryName">
				<option>すべて</option>
				<option>マンガ</option>
				<option>コミックス(雑誌扱)</option>
			</select>
			<input id="search-area" type="text" name="word" value="<%=word %>">
			<input id="search-btn" type="submit" class="header-btn" value="">
		</form>
		<div id="cart-btn"  class="header-btn" >
			<a href="cart"><img src="image/material/cart2.png"></a>
			<a class="overlay-text"><%=stock %></a>
		</div>
		
	</div>
	<div id="menu-box">
		<a href="Login"><p>ログイン</p></a>
		<a href="mypage"><p>マイページ</p></a>
		<a href="order"><p>注文履歴</p></a>
		<a href="logout"><p>ログアウト</p></a>
	</div>		
	<div id="overlay"></div>
</header>

<script>
	const menu_btn = document.querySelector("#menu-btn");
	const menu_box = document.querySelector("#menu-box");
	const overlay = document.querySelector("#overlay");
	menu_btn.addEventListener("click",() => {
		console.log("!");
		menu_box.classList.toggle("active");
		overlay.classList.toggle("active");
	});
	document.addEventListener("click", (e) => {
	    if (!menu_btn.contains(e.target) && !menu_box.contains(e.target)) {
	    	console.log("?");
	    	menu_box.classList.remove("active");
	    	overlay.classList.remove("active");
	    }
	});
</script>

</body>
</html>