package logic;

import dao.UsersDAO;
import model.User;

public class UserAdminUpdateLogic {
	public static boolean execute(User user) {
		boolean result = false;
		UsersDAO ud = new UsersDAO();
		result = ud.adminUpdate(user);
		return result;
	}
}
