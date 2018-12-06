package airline.service;

import java.util.ArrayList;

import airline.dal.UserDAO;
import airline.model.User;

public class UserService {
	
	public static User getOne(Integer id) {
		return UserDAO.getOne(id);
	}
	
	public static ArrayList<User> getAll(){
		return UserDAO.getAll();
	}
	
	public static boolean create(User user) {
		return UserDAO.create(user);
	}
	
	public static boolean update(User user) {
		return UserDAO.update(user);
	}
	
	public static boolean delete(User user) {
		return UserDAO.delete(user);
	}
	
	
	

}
