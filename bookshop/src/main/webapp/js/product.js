// js/product.js

// 画像ファイルを選択したときに呼び出される関数
function previewImage(event) {
    const input = event.target; // ファイル入力要素
    const preview = document.getElementById("preview"); // プレビュー用のimg要素

    // ファイルが選択されているか確認
    if (input.files && input.files[0]) {
        const reader = new FileReader(); // ファイル読み込み用のオブジェクトを作成

        // 読み込み完了時に画像を表示
        reader.onload = function(e) {
            preview.src = e.target.result; // 読み込んだ画像データをimgに設定
            preview.style.display = "block"; // プレビューを表示
        };

        // ファイルをDataURLとして読み込む（画像として表示するため）
        reader.readAsDataURL(input.files[0]);
    }
}
