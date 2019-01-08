package airline.dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import airline.dal.GenericDAO.Table;
import airline.model.Ticket;
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
	
	public static User create(User user){
		if(user != null) {
			try {
				return (User)GenericDAO.insert(table, user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
		
	}
	
	public static User update(User user){
		if(user != null) {
			try {
				return (User)GenericDAO.insert(table, user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}
	
	public static User delete(User user){
		if(user != null) {
			try {
				return (User)GenericDAO.delete(table, user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<User> find(String query){
		if(query != null) {
			return (ArrayList<User>)(List<?>)GenericDAO.find(table, query);
		}else {
			return null;
		}
	}


	public static ArrayList<Ticket> getTickets(Integer userId) {
		return GenericDAO.getUserTickets(userId);
	}

}
