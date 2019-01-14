package airline.dal;

import java.sql.SQLException;
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
		return (ArrayList<Airport>)(List<?>)GenericDAO.getAll(table);
	}
	
	public static Airport create(Airport airport){
		if(airport != null) {
			try {
				return (Airport)GenericDAO.insert(table, airport);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
		
	}
	
	public static Airport update(Airport airport){
		if(airport != null) {
			try {
				return (Airport)GenericDAO.update(table, airport);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}else {
			return null;
		}
	}
	
	public static Airport delete(Airport airport){
		if(airport != null) {
			try {
				return (Airport)GenericDAO.delete(table, airport);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}else {
			return null;
		}
	}
	
	
	
	

}
