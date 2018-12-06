package airline.dal;

import java.util.ArrayList;
import java.util.List;

import airline.dal.GenericDAO.Table;
import airline.model.Airport;
import airline.model.Flight;

public class FlightDAO {
	
	private static final Table table = Table.FLIGHT;
	
	public static Flight getOne(Integer id) {
		if(id != null) {
			return (Flight)GenericDAO.getOne(table, id);
		}else {
			return null;
		}
		
	}
	

	@SuppressWarnings("unchecked")
	public static ArrayList<Flight> getAll(){

		return (ArrayList<Flight>)(List<?>)GenericDAO.getAll(table);

		
	}
	
	public static boolean create(Flight flight) {
		if(flight != null) {
			return GenericDAO.insert(table, flight);
		}else {
			return false;
		}
		
	}
	
	public static boolean update(Flight flight) {
		if(flight != null) {
			return GenericDAO.insert(table, flight);
		}else {
			return false;
		}
	}
	
	public static boolean delete(Flight flight) {
		if(flight != null) {
			return GenericDAO.delete(table, flight);
		}else {
			return false;
		}
	}

}
