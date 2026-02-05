<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー管理</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/userList.css">
<script src="${pageContext.request.contextPath}/js/userList.js"></script>

</head>
<body>

	<!-- 共通ヘッダー -->
	<jsp:include page="header.jsp" />

	<!-- ===============================
         ページタイトル
         =============================== -->
	<div class="box">
		<h2 class="title">ユーザー管理</h2>

		<!-- ===============================
         検索ボックス
         =============================== -->
		<div class="user-search-box">
			<input type="text" id="keyword" class="user-search-input"
				placeholder="ユーザー名 / ID / メールで検索" onkeyup="searchUser()">
		</div>


		<%
    	List<User> list = (List<User>)request.getAttribute("userList");
        for (User u : list) {
    %>
		<a class="user-card"
            href="${pageContext.request.contextPath}/admin/userpage?id=<%=u.getId()%>">

            <div class="user-name">ユーザー名：<%=u.getName()%></div>
            <div class="user-id">ID：<%=u.getId()%></div>
            <div class="user-email">メール：<%=u.getEmail()%></div>

        </a>
		<%
        }
    %>

	</div>


</body>
</html>
