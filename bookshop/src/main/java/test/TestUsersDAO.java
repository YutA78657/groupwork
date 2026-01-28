package test;

import java.util.List;

import dao.UsersDAO;
import model.User;

public class TestUsersDAO {

    public static void main(String[] args) {

    	UsersDAO dao = new UsersDAO();

        // 新規登録
        User newUser = new User(
            "test@example.com",
            "1234",
            "テスト太郎"
        );

        boolean created = dao.create(newUser);
        System.out.println(created ? "登録成功" : "登録失敗");

        // ログイン認証
        User loginUser = dao.findByEmailAndPass("test@example.com", "1234");
        System.out.println("ログイン結果：" + (loginUser != null));

        if (loginUser != null) {
            System.out.println(
                "ID=" + loginUser.getId()
                + " / 名前=" + loginUser.getName()
                + " / admin=" + loginUser.getAdminFlg()
            );
        }

        // 全件取得
        System.out.println("----- 全ユーザー -----");
        List<User> list = dao.findAll();

        for (User u : list) {
            System.out.println(
                "ID=" + u.getId()
                + " / email=" + u.getEmail()
                + " / name=" + u.getName()
                + " / admin=" + u.getAdminFlg()
            );
        }

        // 更新
        if (!list.isEmpty()) {
            User u = list.get(list.size() - 1);
            u.setEmail("5353@gmail.com");
            u.setName("テスト");


            boolean updated = dao.update(u);
            System.out.println(updated ? "更新成功" : "更新失敗");
        }
        
     // 全件取得
        System.out.println("----- 全ユーザー -----");
        list = dao.findAll();

        for (User u : list) {
            System.out.println(
                "ID=" + u.getId()
                + " / email=" + u.getEmail()
                + " / name=" + u.getName()
                + " / admin=" + u.getAdminFlg()
            );
        }
        
        // パスワード更新
        loginUser.setPass("newpass");

        boolean passUpdated = dao.updatePassword(loginUser);
        System.out.println(
            passUpdated ? "パスワード変更成功" : "パスワード変更失敗"
        );
        
     // 全件取得
        System.out.println("----- 全ユーザー -----");
        list = dao.findAll();

        for (User u : list) {
            System.out.println(
                "ID=" + u.getId()
                + " / email=" + u.getEmail()
                + " / name=" + u.getName()
                + " / admin=" + u.getAdminFlg()
            );
        }

        // 削除（最後のユーザーを削除）
        if (!list.isEmpty()) {
            int deleteId = list.get(list.size() - 1).getId();
            boolean deleted = dao.delete(deleteId);
            System.out.println(deleted ? "削除成功" : "削除失敗");
        }
        
     // 全件取得
        System.out.println("----- 全ユーザー -----");
        list = dao.findAll();

        for (User u : list) {
            System.out.println(
                "ID=" + u.getId()
                + " / email=" + u.getEmail()
                + " / name=" + u.getName()
                + " / admin=" + u.getAdminFlg()
            );
        }
    }
}