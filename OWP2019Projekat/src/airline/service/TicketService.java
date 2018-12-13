package airline.service;

import java.sql.SQLException;
import java.util.ArrayList;


import airline.dal.TicketDAO;
import airline.model.Ticket;

public class TicketService {
	

	
	public static Ticket getOne(Integer id) {
		return TicketDAO.getOne(id);
	}
	
	public static ArrayList<Ticket> getAll(){
		return TicketDAO.getAll();
	}
	
	public static Ticket create(Ticket ticket){
		return TicketDAO.create(ticket);
	}
	
	public static Ticket update(Ticket ticket){
		return TicketDAO.update(ticket);
	}
	
	public static Ticket delete(Ticket ticket){
		return TicketDAO.delete(ticket);
	}

}
