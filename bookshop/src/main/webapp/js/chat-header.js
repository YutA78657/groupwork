// ===============================
// チャットアイコン（クリックで開閉）
// ===============================
(function () {
    const trigger = document.getElementById("ai-chat-trigger");

    trigger.addEventListener("click", () => {
        toggleChat();
    });
})();


// ===============================
// チャットウィンドウのドラッグ移動（ヘッダーのみ）
// ===============================
(function () {
    const chat = document.getElementById("ai-chat-window");
    const header = document.getElementById("chat-header");

    let isDown = false;
    let offsetX = 0;
    let offsetY = 0;

    header.style.cursor = "move";

    header.addEventListener("mousedown", (e) => {
        isDown = true;
        offsetX = e.clientX - chat.offsetLeft;
        offsetY = e.clientY - chat.offsetTop;
    });

    document.addEventListener("mousemove", (e) => {
        if (!isDown) return;

        chat.style.left = (e.clientX - offsetX) + "px";
        chat.style.top = (e.clientY - offsetY) + "px";
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

        const extra = 8;

        chatDisplay.style.height =
            (chatWindow.offsetHeight - headerHeight - inputHeight - extra) + "px";
    }).observe(chatWindow);
})();


// ===============================
// 送信処理
// ===============================
function sendChat(contextPath) {
    const input = document.getElementById("chat-input");
    const display = document.getElementById("chat-display");
    const loading = document.getElementById("chat-loading");
    const text = input.value.trim();

    if (text === "") return;

    const userMsg = document.createElement("div");
    userMsg.className = "msg user";
    userMsg.textContent = text;
    display.appendChild(userMsg);

    input.value = "";
    display.scrollTop = display.scrollHeight;

    loading.style.display = "block";
    display.appendChild(loading);

    fetch(contextPath + "/chat", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: text })
    })
    .then(res => res.json())
    .then(data => {

        loading.style.display = "none";

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

        display.appendChild(loading);
        display.scrollTop = display.scrollHeight;
    })
    .catch(err => {

        loading.style.display = "none";

        const errMsg = document.createElement("div");
        errMsg.className = "msg ai";
        errMsg.textContent = "通信エラーが発生しました。";
        display.appendChild(errMsg);

        display.appendChild(loading);
    });
}
