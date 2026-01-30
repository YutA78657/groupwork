package logic;

import dao.UsersDAO;
import model.User;

public class LoginLogic {
	public static User execute(String email,String pass) {
		 UsersDAO dao = new UsersDAO();
	     User user = dao.findByEmailAndPass(email, pass);
	     return user;
	}
}
