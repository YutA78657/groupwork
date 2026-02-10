// ===============================================
// 検索処理（部分一致：名前 / ID / メール）
// ===============================================
function searchUser() {
    const keyword = document.getElementById("keyword").value.toLowerCase();
    const users = document.getElementsByClassName("user-card");

    for (let i = 0; i < users.length; i++) {
        const text = users[i].innerText.toLowerCase();
        // 検索ヒット時は "flex" にして横並びを維持
        users[i].style.display = text.includes(keyword) ? "flex" : "none";
    }
}
