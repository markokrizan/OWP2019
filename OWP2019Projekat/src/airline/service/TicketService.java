package airline.service;

import java.sql.SQLException;
import java.util.ArrayList;

import airline.dal.FlightDAO;
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
	
	public static Boolean delete(Integer ticketId){
		return TicketDAO.delete(ticketId);
	}
	
	public static Boolean checkFLight(Integer flightId) {
		return FlightDAO.checkIfExists(flightId);
	}
	
	public static Boolean checkSeatAvailibility(Integer flightId, Integer seatNo) {
		return FlightDAO.checkSeatAvailibility(flightId, seatNo);
	}
	

}
