package airline.service;

import java.util.ArrayList;


import airline.dal.TicketDAO;
import airline.model.Ticket;

public class TicketService {
	
	//Singleton example instead of static classes:
	/*
	private static TicketService single_instance = null;
	
	private TicketService() {
		
	}
	
	public static TicketService getInstance() {
		if (single_instance == null) {
			single_instance = new TicketService();
		}
		return single_instance;
	}
	*/
	
	public static Ticket getOne(Integer id) {
		return TicketDAO.getOne(id);
	}
	
	public static ArrayList<Ticket> getAll(){
		return TicketDAO.getAll();
	}
	
	public static boolean create(Ticket ticket) {
		return TicketDAO.create(ticket);
	}
	
	public static boolean update(Ticket ticket) {
		return TicketDAO.update(ticket);
	}
	
	public static boolean delete(Ticket ticket) {
		return TicketDAO.delete(ticket);
	}

}
