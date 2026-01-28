package test;

import java.util.List;

import dao.CategoriesDAO;
import model.Category;

public class TestCategoriesDAO {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		CategoriesDAO dao = new CategoriesDAO();
		List<Category> list = dao.findAll();
		for(Category c : list) {
			System.out.println("id="+ c.getId()+"name="+ c.getName());
		}

		//登録したいデータ
		Category category = new Category(0,"応用情報参考書");

		boolean result = dao.create(category);

		if(result) {
			System.out.println("登録完了");
		}else {
			System.out.println("登録失敗");
		}
		list = dao.findAll();
		for(Category c : list) {
			System.out.println("id="+ c.getId()+"name="+ c.getName());
		}
		
		
		//更新処理
		int updateId =list.get(list.size()-1).getId();
		System.out.println(updateId);
		
		Category updateCategory = new Category(updateId,"応用情報(毛谷版)");
		
		boolean updated = dao.update(updateCategory);
		
		if(updated) {
			System.out.println("更新成功!");
		}else {
			System.out.println("更新失敗…IDが存在しない");
		}
		

		// 削除処理
		int deleteId = list.get(0).getId();
		
		boolean deleted = dao.delete(deleteId);

		if (deleted) {
			System.out.println("削除成功！");
		} else {
			System.out.println("削除失敗… ID が存在しない");
		}
		list = dao.findAll();
		for(Category c : list) {
			System.out.println("id="+ c.getId()+"name="+ c.getName());
		}
		

	}
}

