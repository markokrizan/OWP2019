package airline.service;

import java.util.ArrayList;

import airline.dal.FlightDAO;
import airline.dto.SearchFlightDTO;
import airline.model.Flight;
import airline.model.Ticket;

public class FlightService {
	
	public static Flight getOne(Integer id) {
		return FlightDAO.getOne(id);
	}
	
	public static ArrayList<Flight> getAll(){
		return FlightDAO.getAll();
	}
	
	public static ArrayList<Flight> getCurrent(){
		return FlightDAO.getCurrent();
	}
	
	public static ArrayList<Ticket> getTickets(Integer flightId){
		return FlightDAO.getTickets(flightId);
	}
	
	public static ArrayList<Integer> getOccupiedSeats(Integer flightId){
		return FlightDAO.getOccupiedSeats(flightId);
	}
	
	public static Flight create(Flight flight){
		return FlightDAO.create(flight);
	}
	
	public static Flight update(Flight flight){
		return FlightDAO.update(flight);
	}
	
	public static Boolean delete(Integer flightId){
		return FlightDAO.delete(flightId);
	}
	
	public static ArrayList<Flight> search(SearchFlightDTO sfdto){
		return FlightDAO.search(sfdto);
	}
	
	public static ArrayList<Flight> returning(Integer flightId){
		return FlightDAO.returning(flightId);
	}
	
	public static Boolean checkAvailibleNumber(String newNumber){
		return FlightDAO.checkAvailibleNumber(newNumber);
		
	}
	
	

}
