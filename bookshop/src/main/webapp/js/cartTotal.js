// js/cartTotal.js

// 合計金額を再計算して表示する関数
function updateTotal() {
  let total = 0;
  document.querySelectorAll('.quantity-select').forEach(select => {
    const price = parseInt(select.dataset.price);
    const quantity = parseInt(select.value);
    total += price * quantity;
  });
  const totalElem = document.getElementById('total-amount');
  if (totalElem) {
    totalElem.textContent = '合計￥' + total.toLocaleString();
  }
}

// ページ読み込み後に各セレクトボックスにイベントを設定
window.addEventListener('DOMContentLoaded', () => {
  document.querySelectorAll('.quantity-select').forEach(select => {
    select.addEventListener('change', updateTotal);
  });
});
/**
 * 
 */