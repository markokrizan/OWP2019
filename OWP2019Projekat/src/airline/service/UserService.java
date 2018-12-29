package airline.service;

import java.sql.SQLException;
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
	
	public static User create(User user){
		return UserDAO.create(user);
	}
	
	public static User update(User user){
		return UserDAO.update(user);
	}
	
	public static User delete(User user){
		return UserDAO.delete(user);
	}
	
	public static ArrayList<User> findOne(String query){
		return UserDAO.find(query);
	}
	
	
	

}
