// ===============================
// アイコン短押し → 開閉
// アイコン長押し → ドラッグ移動（チャット欄と連動）
// ===============================
(function () {
    const trigger = document.getElementById("ai-chat-trigger");
    const chat = document.getElementById("ai-chat-window");

    let isDown = false;
    let isDragging = false;
    let offsetX = 0;
    let offsetY = 0;
    let pressTimer = null;

    trigger.style.cursor = "pointer";

    trigger.addEventListener("dragstart", (e) => e.preventDefault());

    trigger.addEventListener("mousedown", (e) => {
        if (e.button !== 0) return;

        isDown = true;
        isDragging = false;

        offsetX = e.clientX - trigger.offsetLeft;
        offsetY = e.clientY - trigger.offsetTop;

        pressTimer = setTimeout(() => {
            isDragging = true;
            trigger.style.cursor = "grabbing";
        }, 150);
    });

    document.addEventListener("mousemove", (e) => {
        if (!isDown || !isDragging) return;

        const newLeft = e.clientX - offsetX;
        const newTop = e.clientY - offsetY;

        trigger.style.left = newLeft + "px";
        trigger.style.top = newTop + "px";

        // チャット欄をアイコンの左上に配置（重ならない）
        chat.style.left = (newLeft - chat.offsetWidth - 20) + "px";
        chat.style.top = (newTop - chat.offsetHeight - 20) + "px";
    });

    document.addEventListener("mouseup", () => {
        if (pressTimer) clearTimeout(pressTimer);

        if (isDown && !isDragging) {
            toggleChat();
        }

        isDown = false;
        isDragging = false;
        trigger.style.cursor = "pointer";
    });
})();


// ===============================
// チャット欄のドラッグ移動（ヘッダーを掴む）
// ===============================
(function () {
    const chat = document.getElementById("ai-chat-window");
    const trigger = document.getElementById("ai-chat-trigger");
    const header = document.getElementById("chat-header");

    let isDown = false;
    let offsetX = 0;
    let offsetY = 0;

    header.addEventListener("mousedown", (e) => {
        isDown = true;
        offsetX = e.clientX - chat.offsetLeft;
        offsetY = e.clientY - chat.offsetTop;
    });

    document.addEventListener("mousemove", (e) => {
        if (!isDown) return;

        const newLeft = e.clientX - offsetX;
        const newTop = e.clientY - offsetY;

        chat.style.left = newLeft + "px";
        chat.style.top = newTop + "px";

        // アイコンをチャット欄の右下に配置（重ならない）
        trigger.style.left = (newLeft + chat.offsetWidth + 20) + "px";
        trigger.style.top = (newTop + chat.offsetHeight + 20) + "px";
    });

    document.addEventListener("mouseup", () => {
        isDown = false;
    });
})();


// ===============================
// チャット開閉
// ===============================
function toggleChat() {
    const chat = document.getElementById("ai-chat-window");

    if (chat.style.display === "none" || chat.style.display === "") {
        chat.style.display = "block";
    } else {
        chat.style.display = "none";
    }
}


// ===============================
// チャット欄サイズ変更時にスクロール領域を更新
// ===============================
(function () {
    const chatWindow = document.getElementById("ai-chat-window");
    const chatDisplay = document.getElementById("chat-display");

    new ResizeObserver(() => {
        const headerHeight = document.getElementById("chat-header").offsetHeight;
        const inputHeight = document.getElementById("chat-input-area").offsetHeight;

        chatDisplay.style.height =
            (chatWindow.offsetHeight - headerHeight - inputHeight) + "px";
    }).observe(chatWindow);
})();


// ===============================
// 送信処理（/chat に POST）
// ===============================
function sendChat(contextPath) {
    const input = document.getElementById("chat-input");
    const display = document.getElementById("chat-display");
    const loading = document.getElementById("chat-loading");
    const text = input.value.trim();

    if (text === "") return;

    // ユーザーのメッセージ表示
    const userMsg = document.createElement("div");
    userMsg.className = "msg user";
    userMsg.textContent = text;
    display.appendChild(userMsg);

    input.value = "";
    display.scrollTop = display.scrollHeight;

    // ★ ローディング表示
    loading.style.display = "block";

    // ★ ぐるぐるを一番下に移動
    display.appendChild(loading);

    fetch(contextPath + "/chat", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: text })
    })
    .then(res => res.json())
    .then(data => {

        // ★ ローディング非表示
        loading.style.display = "none";

        // AI の返答
        const aiMsg = document.createElement("div");
        aiMsg.className = "msg ai";

        let html = `<strong>${data.title}</strong><br>${data.reason}<br>`;

        if (data.pid !== -1) {
            html += `
                <a href="${contextPath}/product?pid=${data.pid}">
                    <img src="${contextPath}/image/${data.img}"
                         style="width:120px;margin-top:5px;border-radius:5px;">
                </a>
            `;
        }

        aiMsg.innerHTML = html;
        display.appendChild(aiMsg);

        // ★ ぐるぐるを一番下に移動（これが重要）
        display.appendChild(loading);

        display.scrollTop = display.scrollHeight;
    })
    .catch(err => {

        loading.style.display = "none";

        const errMsg = document.createElement("div");
        errMsg.className = "msg ai";
        errMsg.textContent = "通信エラーが発生しました。";
        display.appendChild(errMsg);

        // ★ エラー時もぐるぐるを一番下に移動
        display.appendChild(loading);
    });
}
