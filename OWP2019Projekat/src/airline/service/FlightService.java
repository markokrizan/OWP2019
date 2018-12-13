package airline.service;

import java.sql.SQLException;
import java.util.ArrayList;

import airline.dal.AirportDAO;
import airline.dal.FlightDAO;
import airline.model.Flight;

public class FlightService {
	
	public static Flight getOne(Integer id) {
		return FlightDAO.getOne(id);
	}
	
	public static ArrayList<Flight> getAll(){
		return FlightDAO.getAll();
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
	
	

}
