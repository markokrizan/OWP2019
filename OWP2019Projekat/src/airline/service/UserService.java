package airline.service;

import java.util.ArrayList;

import airline.dal.UserDAO;
import airline.model.Ticket;
import airline.model.User;

public class UserService {
	
	public static User getOne(Integer id) {
		return UserDAO.getOne(id);
	}
	
	public static ArrayList<User> getAll(){
		return UserDAO.getAll();
	}
	
	public static ArrayList<Ticket> getUserTickets(Integer userId){
		return UserDAO.getTickets(userId);
	}
	
	public static User create(User user){
		return UserDAO.create(user);
	}
	
	public static User update(User user){
		return UserDAO.update(user);
	}
	
	public static Boolean delete(Integer userId){
		return UserDAO.delete(userId);
	}
	
	public static Boolean block(Integer userId){
		return UserDAO.block(userId);
	}
	
	public static ArrayList<User> findOne(String query){
		return UserDAO.find(query);
	}
	
	
	

}
