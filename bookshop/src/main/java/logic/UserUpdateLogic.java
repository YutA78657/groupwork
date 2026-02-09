package logic;

import dao.UsersDAO;
import model.User;

public class UserUpdateLogic {
	public static boolean execute(User user) {
		boolean result = false;
		UsersDAO ud = new UsersDAO();
		result = ud.update(user);
		return result;
	}
}
