package test;

import java.util.List;

import dao.BookDAO;
import model.Book;

public class TestBookDAO {

	public static void main(String[] args) {
		BookDAO dao = new BookDAO();

        System.out.println("----- おすすめ本 -----");
        List<Book> recBooks = dao.findRecommendBooks(5);

        if (recBooks.isEmpty()) {
            System.out.println("おすすめ本が取得できませんでした");
        } else {
            for (Book b : recBooks) {
                printBook(b);
            }
        }

        System.out.println("\n----- カテゴリ別（資格・試験） -----");
        List<Book> examBooks = dao.findRandomByCategory("資格・試験", 5);

        if (examBooks.isEmpty()) {
            System.out.println("資格・試験カテゴリの本が取得できませんでした");
        } else {
            for (Book b : examBooks) {
                printBook(b);
            }
        }
    }

    // 表示用メソッド
    private static void printBook(Book b) {
        System.out.println(
            "PID=" + b.getPid() +
            " / タイトル=" + b.getTitle() +
            " / 価格=" + b.getPrice() +
            " / 在庫=" + b.getStock() +
            " / 著者=" + b.getAuther() +
            " / カテゴリ=" + b.getCname() +
            " / おすすめ=" + b.getRecommend_flg()
        );
    }

}
