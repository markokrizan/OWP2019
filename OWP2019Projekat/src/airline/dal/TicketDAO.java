package airline.dal;

import java.util.ArrayList;
import java.util.List;

import airline.dal.GenericDAO.Table;
import airline.model.Ticket;

public class TicketDAO {
	
	private static final Table table = Table.TICKET;
	
	public static Ticket getOne(Integer id) {
		if(id != null) {
			return (Ticket)GenericDAO.getOne(table, id);
		}else {
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Ticket> getAll(){

		return (ArrayList<Ticket>)(List<?>)GenericDAO.getAll(table);

	}
	
	public static boolean create(Ticket ticket) {
		if(ticket != null) {
			return GenericDAO.insert(table, ticket);
		}else {
			return false;
		}
		
	}
	
	public static boolean update(Ticket ticket) {
		if(ticket != null) {
			return GenericDAO.insert(table, ticket);
		}else {
			return false;
		}
	}
	
	public static boolean delete(Ticket ticket) {
		if(ticket != null) {
			return GenericDAO.delete(table, ticket);
		}else {
			return false;
		}
	}


}
