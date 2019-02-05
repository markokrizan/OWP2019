package airline.dal;

import java.sql.SQLException;
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
	
	public static Ticket create(Ticket ticket){
		if(ticket != null) {
			try {
				return (Ticket)GenericDAO.insert(table, ticket);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
		
	}
	
	public static Ticket update(Ticket ticket){
		if(ticket != null) {
			try {
				return (Ticket)GenericDAO.update(table, ticket);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}
	
	public static Boolean delete(Integer ticketId){
		if(ticketId != null) {
			try {
				return GenericDAO.delete(table, ticketId);
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
			
		}else {
			return false;
		}
	}
	
	


}
