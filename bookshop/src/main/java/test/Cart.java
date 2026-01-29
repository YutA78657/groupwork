package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Product;

@WebServlet("/carttest")
public class Cart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Cart() {
        super();
    }

    // カートに商品を追加して表示する処理（GET）
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションを取得（ユーザーごとのカート情報を保持）
        HttpSession session = request.getSession();

        // セッションから既存のカート情報（商品リストと数量マップ）を取得
        @SuppressWarnings("unchecked")
        List<Product> productList = (List<Product>) session.getAttribute("productList");

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> quantityMap = (Map<Integer, Integer>) session.getAttribute("quantityMap");

        // 初回アクセス時は空のリストとマップを作成
        if (productList == null || quantityMap == null) {
            productList = new ArrayList<>();
            quantityMap = new HashMap<>();
        }

        // ★ ① 元の商品（削除しない指定あり）
        Product product = new Product(
            0,
            "令和08-09年　応用情報技術者 試験によくでる問題集【科目A】",
            2640,
            100,
            "大滝　みや子",
            "応用情報技術者の科目A試験（旧午前試験）によくでる問題を厳選し、ていねいに解説したテーマ別問題集です。",
            "技術評論社",
            0,
            "9784297153335_600.jpg",
            1,
            1
        );

        // すでにカートにある場合は数量を+1、なければ追加
        if (!quantityMap.containsKey(product.getId())) {
            productList.add(product);
            quantityMap.put(product.getId(), 1);
        } else {
            quantityMap.put(product.getId(), quantityMap.get(product.getId()) + 1);
        }

        // ★ ② テスト用の商品を複数追加（スクロール確認用）
        List<Product> sampleProducts = new ArrayList<>();
        sampleProducts.add(new Product(1, "Java入門書", 1980, 10, "java.jpg", 1, 1));
        sampleProducts.add(new Product(2, "HTML&CSSデザイン", 2200, 8, "htmlcss.jpg", 1, 1));
        sampleProducts.add(new Product(3, "Spring Boot実践", 3080, 5, "spring.jpg", 1, 1));
        sampleProducts.add(new Product(4, "データベース基礎", 2500, 6, "db.jpg", 1, 1));
        sampleProducts.add(new Product(5, "GitとGitHub", 1800, 12, "git.jpg", 1, 1));
        sampleProducts.add(new Product(6, "Pythonで自動化", 2700, 7, "python.jpg", 1, 1));
        sampleProducts.add(new Product(7, "セキュリティ入門", 2400, 4, "security.jpg", 1, 1));
        sampleProducts.add(new Product(8, "ネットワーク基礎", 2300, 9, "network.jpg", 1, 1));
        sampleProducts.add(new Product(9, "アルゴリズム図鑑", 2900, 3, "algo.jpg", 1, 1));
        sampleProducts.add(new Product(10, "クラウドのしくみ", 2600, 5, "cloud.jpg", 1, 1));

        // 各商品をカートに追加（すでにある場合は数量を+1）
        for (Product p : sampleProducts) {
            if (!quantityMap.containsKey(p.getId())) {
                productList.add(p);
                quantityMap.put(p.getId(), 1);
            } else {
                quantityMap.put(p.getId(), quantityMap.get(p.getId()) + 1);
            }
        }

        // ★ ③ セッションに保存してJSPへ転送
        session.setAttribute("productList", productList);
        session.setAttribute("quantityMap", quantityMap);

        // カート表示ページへフォワード
        request.getRequestDispatcher("WEB-INF/jsp/cart.jsp").forward(request, response);
    }

    // 数量変更・削除処理（POST）
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); // "update" または "delete"
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Product> productList = (List<Product>) session.getAttribute("productList");

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> quantityMap = (Map<Integer, Integer>) session.getAttribute("quantityMap");

        if (productList == null || quantityMap == null) {
            response.sendRedirect("cart");
            return;
        }

        int productId = Integer.parseInt(request.getParameter("productId"));

        if ("update".equals(action)) {
            // 数量変更処理
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            quantityMap.put(productId, quantity);
        } else if ("delete".equals(action)) {
            // 商品削除処理
            quantityMap.remove(productId);
            productList.removeIf(p -> p.getId() == productId);
        }

        // セッションに保存してリダイレクト
        session.setAttribute("productList", productList);
        session.setAttribute("quantityMap", quantityMap);

        response.sendRedirect("cart");
    }
}
