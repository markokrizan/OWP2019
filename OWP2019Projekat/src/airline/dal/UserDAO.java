package airline.dal;

import java.util.ArrayList;
import java.util.List;

import airline.dal.GenericDAO.Table;
import airline.model.User;

public class UserDAO {
	
private static final Table table = Table.USER;
	
	public static User getOne(Integer id) {
		if(id != null) {
			return (User)GenericDAO.getOne(table, id);
		}else {
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<User> getAll(){

		return (ArrayList<User>)(List<?>)GenericDAO.getAll(table);

	}
	
	public static boolean create(User user) {
		if(user != null) {
			return GenericDAO.insert(table, user);
		}else {
			return false;
		}
		
	}
	
	public static boolean update(User user) {
		if(user != null) {
			return GenericDAO.insert(table, user);
		}else {
			return false;
		}
	}
	
	public static boolean delete(User user) {
		if(user != null) {
			return GenericDAO.delete(table, user);
		}else {
			return false;
		}
	}

}
