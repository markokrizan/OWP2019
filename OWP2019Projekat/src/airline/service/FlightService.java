package airline.service;

import java.util.ArrayList;

import airline.dal.FlightDAO;
import airline.dto.SearchFlightDTO;
import airline.model.Flight;

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
	
	public static Flight create(Flight flight){
		return FlightDAO.create(flight);
	}
	
	public static Flight update(Flight flight){
		return FlightDAO.update(flight);
	}
	
	public static Flight delete(Flight flight){
		return FlightDAO.delete(flight);
	}
	
	public static ArrayList<Flight> search(SearchFlightDTO sfdto){
		return FlightDAO.search(sfdto);
	}
	
	public static ArrayList<Flight> returning(Integer flightId){
		return FlightDAO.returning(flightId);
	}
	
	

}
