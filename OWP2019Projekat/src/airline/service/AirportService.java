package airline.service;

import java.sql.SQLException;
import java.util.ArrayList;

import airline.dal.AirportDAO;
import airline.model.Airport;

public class AirportService {
	
	public static Airport getOne(Integer id) {
		return AirportDAO.getOne(id);
	}
	
	public static ArrayList<Airport> getAll(){
		return AirportDAO.getAll();
	}
	
	public static Airport create(Airport airport){
		return AirportDAO.create(airport);
	}
	
	public static Airport update(Airport airport){
		return AirportDAO.update(airport);
	}
	
	public static Boolean delete(Integer airportId){
		return AirportDAO.delete(airportId);
	}
	


}
