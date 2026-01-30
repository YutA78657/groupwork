package logic;

import dao.UsersDAO;
import model.User;

public class RegisterUser {
	public static boolean execute(User user) {
		boolean result = false;
		UsersDAO ud = new UsersDAO();
		result = ud.create(user);
		return result;
	}
}
