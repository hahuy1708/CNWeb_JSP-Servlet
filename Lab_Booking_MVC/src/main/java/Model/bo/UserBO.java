package Model.bo;

import Model.dao.UserDAO;
import Model.bean.User;

public class UserBO{
	private UserDAO userDAO = new UserDAO();
	public User checkAuth(String username, String password) throws ClassNotFoundException {
		return userDAO.authenticate(username, password);
	}
}