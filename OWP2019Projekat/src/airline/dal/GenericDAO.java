package airline.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import airline.model.Airport;
import airline.model.Flight;
import airline.model.Ticket;
import airline.model.User;
import airline.model.User.Role;

public class GenericDAO {
	
	
	public enum Table{
		AIRPORT,
		FLIGHT,
		USER,
		TICKET
		
	}
	
	
	private static String idHelper(Table table) {
		switch(table) {
			case AIRPORT:
				return "airport_id";
			case FLIGHT:
				return "flight_id";
			case USER:
				return "user_id";
			case TICKET:
				return "ticket_id";
			default:
				return null;
			
		}
	}
	
	
	
	public static Object getOne(Table table, Integer id){
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String idName = idHelper(table);
		
		try {
			String query = "SELECT * FROM " + table.toString() + " WHERE" + idName + "=?";
	

			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setInt(index++, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				if(table == Table.AIRPORT) {
					Integer airportId = rset.getInt("airport_id");
					String name = rset.getString("name");
					return new Airport(airportId, name);
					
				}else if(table == Table.FLIGHT) {
					Integer flightId = rset.getInt("flight_id");
					String number = rset.getString("number");
					Date departureDate = rset.getDate("departure_date");
					Date arrivalDate = rset.getDate("arrival_date");
					Airport departureAirport = (Airport)getOne(Table.AIRPORT, rset.getInt("departure_airport_id"));
					Airport arrivalAirport = (Airport)getOne(Table.AIRPORT, rset.getInt("arrival_airport_id"));
					Integer noOfSeats = rset.getInt("no_of_seats");
					Double price = rset.getDouble("price");
					return new Flight(flightId, number, departureDate, arrivalDate, departureAirport, arrivalAirport, noOfSeats,
							price);
					
				}else if(table == Table.USER) {
					Integer userId = rset.getInt("user_id");
					String userName = rset.getString("user_name");
					String password = rset.getString("password");
					String firstName = rset.getString("first_name");
					String lastName = rset.getString("last_name");
					Date registrationDate = rset.getDate("registration_date");
					Role role = Role.valueOf(rset.getString("role"));
					Boolean blocked = rset.getBoolean("blocked");
					return new User(userId, userName, password, firstName, lastName, registrationDate, role, blocked);
					
					
				}else if(table == Table.TICKET) {
					Integer ticketId = rset.getInt("ticket_id");
					Flight departureFlight = (Flight)getOne(Table.FLIGHT, rset.getInt("departure_flight_id"));
					Flight arrivalFlight = (Flight)getOne(Table.FLIGHT, rset.getInt("arrival_flight_id"));
					Integer departureFlightSeatNo = rset.getInt("departure_flight_seat_no");
					Integer arrrivalFlightSeatNo = rset.getInt("arrival_flight_seat_no");
					Date reservationDate = rset.getDate("reservation_date");
					Date saleDate = rset.getDate("sale_date");
					User user = (User)getOne(Table.USER, rset.getInt("user_id"));
					
					return new Ticket(ticketId, departureFlight, arrivalFlight, departureFlightSeatNo, arrrivalFlightSeatNo, 
							reservationDate, saleDate, user);
					
				}else {
					return null;
				}
			
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return null;
		
	}
	
	
	public static List<Object> getAll(Table table) {
		List<Object> objects = new ArrayList<Object>();

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM " + table.toString();

			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				
				if(table == Table.AIRPORT) {
					Integer airportId = rset.getInt("airport_id");
					String name = rset.getString("name");
					objects.add((new Airport(airportId, name)));
					
				}else if(table == Table.FLIGHT) {
					Integer flightId = rset.getInt("flight_id");
					String number = rset.getString("number");
					Date departureDate = rset.getDate("departure_date");
					Date arrivalDate = rset.getDate("arrival_date");
					Airport departureAirport = (Airport)getOne(Table.AIRPORT, rset.getInt("departure_airport_id"));
					Airport arrivalAirport = (Airport)getOne(Table.AIRPORT, rset.getInt("arrival_airport_id"));
					Integer noOfSeats = rset.getInt("no_of_seats");
					Double price = rset.getDouble("price");
					objects.add(new Flight(flightId, number, departureDate, arrivalDate, departureAirport, arrivalAirport, noOfSeats,
							price));
					
				}else if(table == Table.USER) {
					Integer userId = rset.getInt("user_id");
					String userName = rset.getString("user_name");
					String password = rset.getString("password");
					String firstName = rset.getString("first_name");
					String lastName = rset.getString("last_name");
					Date registrationDate = rset.getDate("registration_date");
					Role role = Role.valueOf(rset.getString("role"));
					Boolean blocked = rset.getBoolean("blocked");
					objects.add(new User(userId, userName, password, firstName, lastName, registrationDate, role, blocked));
					
					
				}else if(table == Table.TICKET) {
					Integer ticketId = rset.getInt("ticket_id");
					Flight departureFlight = (Flight)getOne(Table.FLIGHT, rset.getInt("departure_flight_id"));
					Flight arrivalFlight = (Flight)getOne(Table.FLIGHT, rset.getInt("arrival_flight_id"));
					Integer departureFlightSeatNo = rset.getInt("departure_flight_seat_no");
					Integer arrrivalFlightSeatNo = rset.getInt("arrival_flight_seat_no");
					Date reservationDate = rset.getDate("reservation_date");
					Date saleDate = rset.getDate("sale_date");
					User user = (User)getOne(Table.USER, rset.getInt("user_id"));
					
					objects.add(new Ticket(ticketId, departureFlight, arrivalFlight, departureFlightSeatNo, arrrivalFlightSeatNo, 
							reservationDate, saleDate, user));
					
				}else {
					return null;
				}
				
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return objects;
	}
	
	
	//add
	
	//update
	
	//delete
	
	
	
	

}
