package test;

import java.util.List;

import dao.SeriesDAO;
import model.Series;

public class TestSeriesDAO {

	public static void main(String[] args) {

		SeriesDAO dao = new SeriesDAO();

		
		// =====================
		// ① INSERT テスト
		// =====================

		// ★ テストデータ作成（1回だけ）
		Series series2 = new Series("てすとシリーズ"); // ← insertWithIdならidも必要
		System.out.println("INSERT呼ばれた: id=" + series2.getId() + ", name=" + series2.getName());

		// ★ INSERT 実行（1回だけ）
		boolean result = dao.insertWithId(series2);

		// ★ 結果表示
		if (result) {
			System.out.println("登録成功");
		} else {
			System.out.println("登録失敗");
		}

		// ★ 一覧表示（ここは何回でもOK）
		System.out.println("=== 登録後一覧 ===");
		List<Series> list = dao.findAll();
		for (Series s : list) {
			System.out.println("id=" + s.getId() + " name=" + s.getName());
		}
		// =====================
		// ② SELECT テスト（id指定）
		// =====================
		Series one = dao.findById(1003);
		if(one != null) {
			System.out.println("id=" +one.getId() + " name=" + one.getName());
		} else {
			System.out.println("存在しません");
		}
		
		// =====================
		// ③ DELETE テスト（id指定）
		// =====================
		int deleteId = 1002; // ← 削除したいIDを指定
		System.out.println("===削除処理で呼ばれた: id=" + deleteId + "===");

		boolean deleteResult = dao.deleteById(deleteId);

		if (deleteResult) {
			System.out.println("削除成功");
		} else {
			System.out.println("削除失敗");
		}

		System.out.println("=== 削除後一覧 ===");
		list = dao.findAll();
		for (Series s : list) {
			System.out.println("id=" + s.getId() + " name=" + s.getName());
		}

		// =====================
		// ④ UPDATE テスト（id指定）
		// =====================

		System.out.println("=== 変更前一覧 ===");
		List<Series> list2 = dao.findAll();
		for (Series s : list2) {
			System.out.println("id=" + s.getId() + " name=" + s.getName());
		}
		int updateId = 1;
		String newName = "mikuoシリーズ";

		System.out.println("===UPDATE呼ばれた: id=" + updateId + ", newName=" + newName + "===");

		boolean updateResult = dao.updateNameById(updateId, newName);

		System.out.println(updateResult ? "更新成功" : "更新失敗");
		System.out.println("=== 変更後一覧 ===");
		list = dao.findAll();
		for (Series s : list) {
			System.out.println("id=" + s.getId() + " name=" + s.getName());

		}


	}
}