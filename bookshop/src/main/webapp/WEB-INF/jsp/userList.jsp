<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー管理</title>

<!-- 外部CSSを読み込み（後で自由に編集可能） -->
<link rel="stylesheet" href="css/userList.css">
<link rel="stylesheet" href="css/style.css">

<!-- 外部JSを読み込み（searchUser関数をここに移動） -->
<script src="js/userList.js"></script>

</head>
<body>

    <!-- 共通ヘッダー -->
    <jsp:include page="header.jsp" />

    <!-- ===============================
         ページタイトル
         =============================== -->
    <h1 class="page-title">ユーザー管理</h1>

    <!-- ===============================
         検索ボックス
         =============================== -->
    <div class="search-box">
        <input type="text" id="keyword" class="search-input"
            placeholder="ユーザー名 / ID / メールで検索"
            onkeyup="searchUser()">
    </div>

    <%
        // ===============================================
        // 仮データ作成（DB未接続でも動作確認できるように）
        // UserList.java からのデータ取得に置き換え可能
        // ===============================================
        class User {
            int id;
            String name;
            String email;

            User(int id, String name, String email) {
                this.id = id;
                this.name = name;
                this.email = email;
            }
        }

        java.util.List<User> list = new java.util.ArrayList<>();

        // 仮データ15件
        list.add(new User(1, "tanaka", "tanaka@example.com"));
        list.add(new User(2, "suzuki", "suzuki@example.com"));
        list.add(new User(3, "yamada", "yamada@example.com"));
        list.add(new User(4, "satou", "satou@example.com"));
        list.add(new User(5, "kobayashi", "kobayashi@example.com"));
        list.add(new User(6, "kato", "kato@example.com"));
        list.add(new User(7, "inoue", "inoue@example.com"));
        list.add(new User(8, "kimura", "kimura@example.com"));
        list.add(new User(9, "shimizu", "shimizu@example.com"));
        list.add(new User(10, "hayashi", "hayashi@example.com"));
        list.add(new User(11, "mori", "mori@example.com"));
        list.add(new User(12, "ishikawa", "ishikawa@example.com"));
        list.add(new User(13, "okada", "okada@example.com"));
        list.add(new User(14, "fujita", "fujita@example.com"));
        list.add(new User(15, "nakamura", "nakamura@example.com"));
    %>

    <!-- ===============================
         ユーザー一覧表示
         1ユーザーごとにカード表示
         クリックで userManage.jsp に遷移
         =============================== -->
    <%
        for (User u : list) {
    %>
        <div class="user-card"
            onclick="location.href='userManage.jsp?id=<%=u.id%>'">

            <div class="user-name">ユーザー名：<%=u.name%></div>
            <div class="user-id">ID：<%=u.id%></div>
            <div class="user-email">メール：<%=u.email%></div>

        </div>
    <%
        }
    %>

</body>
</html>
