package airline.dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import airline.dal.GenericDAO.Table;
import airline.dto.SearchFlightDTO;
import airline.model.Airport;
import airline.model.Flight;
import airline.model.Ticket;

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
	
	public static ArrayList<Ticket> getTickets(Integer flightId){
		return GenericDAO.getFlightTickets(flightId);
	}
	

	public static ArrayList<Flight> getCurrent(){
		return GenericDAO.getCurrentFlights();
	}
	
	
	public static ArrayList<Integer> getOccupiedSeats(Integer flightId){
		return GenericDAO.getOccupiedSeats(flightId);
	}
	
	public static ArrayList<Flight> returning(Integer flightId){
		return GenericDAO.getReturningFlights(flightId);
	}
	
	public static Flight create(Flight flight) {
		if(flight != null) {
			try {
				return (Flight)GenericDAO.insert(table, flight);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
		
	}
	
	public static Flight update(Flight flight) {
		if(flight != null) {
			try {
				return (Flight)GenericDAO.update(table, flight);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}
	
	public static Boolean delete(Integer flightId){
		if(flightId != null) {
			try {
				return GenericDAO.delete(table, flightId);
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
			
		}else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Flight> search(SearchFlightDTO sfdto){

		return (ArrayList<Flight>)(List<?>)GenericDAO.find(table, sfdto);

		
	}

}
