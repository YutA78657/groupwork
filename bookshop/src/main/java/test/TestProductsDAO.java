package test;

import java.util.List;

import dao.ProductsDAO;
import model.Product;

public class TestProductsDAO {

	public static void main(String[] args) {
		ProductsDAO dao = new ProductsDAO();

		System.out.println("===== 商品登録テスト =====");

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

		boolean createResult = dao.create(product);
		System.out.println(createResult ? "登録成功" : "登録失敗");

		System.out.println("\n===== 全件取得 =====");

		List<Product> allList = dao.findAll();
		printList(allList);

		System.out.println("\n===== 部分一致検索 =====");

		String word = "科目A";
		List<Product> searchList = dao.findByword(word);

		System.out.println("検索ワード：" + word);
		System.out.println("件数：" + searchList.size());
		printList(searchList);

		if (!allList.isEmpty()) {

			System.out.println("\n===== 更新テスト =====");

			Product updateProduct = allList.get(0);
			updateProduct.setPrice(3000);
			updateProduct.setStock(50);

			boolean updateResult = dao.update(updateProduct);
			System.out.println(updateResult ? "更新成功" : "更新失敗");

			System.out.println("\n===== 更新後一覧 =====");
			printList(dao.findAll());

			System.out.println("\n===== 削除テスト =====");

			boolean deleteResult = dao.delete(updateProduct.getId());
			System.out.println(deleteResult ? "削除成功" : "削除失敗");

			System.out.println("\n===== 削除後一覧 =====");
			printList(dao.findAll());
		}
	}

	// 表示用
	private static void printList(List<Product> list) {

		if (list.isEmpty()) {
			System.out.println("データなし");
			return;
		}

		for (Product p : list) {
			System.out.println(
					"ID=" + p.getId()
					+ " / タイトル=" + p.getTitle()
					+ " / 価格=" + p.getPrice()
					+ " / 在庫=" + p.getStock()
					+ " / category_id=" + p.getCategory_id()
					+ " / series_id=" + p.getSeries_id()
					);
		}
	}
}
