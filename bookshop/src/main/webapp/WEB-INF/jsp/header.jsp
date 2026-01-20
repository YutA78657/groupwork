<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<form id="search" action="top" method="get">
			<select id="search-type" >
				<option>すべて</option>
				<option>マンガ</option>
				<option>コミックス(雑誌扱)</option>
			</select>
			<input id="search-area" type="text">
			<input id="search-btn" type="submit" class="header-btn" value="">
		</form>
		<div id="cart-btn"  class="header-btn" >
			<a href="cart"><img src="image/material/cart2.png"></a>
			<span class="overlay-text">0</span>
		</div>
		
	</div>
	<div id="menu-box">
		
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