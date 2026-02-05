<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--  
    chat.jsp  
    どのページにも include できる AI チャットウィンドウ。
    ・チャットアイコン
    ・チャットウィンドウ
    ・ローディングアニメーション
    ・初期メッセージ
    ・入力欄
--%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/chat.css">

<!-- ===============================
     チャット起動アイコン（ドラッグ可能）
     =============================== -->
<div id="ai-chat-trigger">
    <img src="${pageContext.request.contextPath}/image/material/chat.jpg" alt="AI店員">
</div>

<!-- ===============================
     チャットウィンドウ本体
     =============================== -->
<div id="ai-chat-window" style="display:none;">

    <!-- ヘッダー（ドラッグ可能） -->
    <div id="chat-header">
        <img src="${pageContext.request.contextPath}/image/material/chat.jpg" class="avatar-mini">
        <strong>AI店員さんに相談</strong>

        <!-- 閉じるボタン -->
        <span class="close-btn" onclick="toggleChat()">×</span>
    </div>

    <!-- メッセージ表示エリア -->
    <div id="chat-display">
        <div class="msg ai">こんにちは！今日はどんな本をお探しですか？</div>
    </div>

    <!-- ★ ローディング（クルクル） -->
    <div id="chat-loading" style="display:none;">
        <div class="loader"></div>
    </div>

    <!-- 入力エリア -->
    <div id="chat-input-area">
        <input type="text" id="chat-input" placeholder="例：ミステリーで軽いもの"
               onkeypress="if(event.keyCode==13) sendChat('${pageContext.request.contextPath}')">

        <button onclick="sendChat('${pageContext.request.contextPath}')">送信</button>
    </div>
</div>

<!-- JS 読み込み -->
<script src="${pageContext.request.contextPath}/js/chat.js"></script>
