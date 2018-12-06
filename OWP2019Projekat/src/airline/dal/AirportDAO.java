package airline.dal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import airline.dal.GenericDAO.Table;
import airline.model.Airport;

public class AirportDAO {
	
	private static final Table table = Table.AIRPORT;
	
	public static Airport getOne(Integer id) {
		if(id != null) {
			return (Airport)GenericDAO.getOne(table, id);
		}else {
			return null;
		}
		
	}
	

	@SuppressWarnings("unchecked")
	public static ArrayList<Airport> getAll(){
		//hacky:
		return (ArrayList<Airport>)(List<?>)GenericDAO.getAll(table);
		//slow and redundant:
//		ArrayList<Airport> airports = new ArrayList<Airport>();
//		for(Object o : GenericDAO.getAll(table)) {
//			airports.add((Airport)o);
//		}
//		return airports;
		
	}
	
	public static boolean create(Airport airport) {
		if(airport != null) {
			return GenericDAO.insert(table, airport);
		}else {
			return false;
		}
		
	}
	
	public static boolean update(Airport airport) {
		if(airport != null) {
			return GenericDAO.insert(table, airport);
		}else {
			return false;
		}
	}
	
	public static boolean delete(Airport airport) {
		if(airport != null) {
			return GenericDAO.delete(table, airport);
		}else {
			return false;
		}
	}
	
	
	
	

}
