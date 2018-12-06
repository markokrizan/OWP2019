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
			String query = "SELECT * FROM " + table.toString() + " WHERE" + idName + "=? AND deleted = 0";
	

			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setInt(index++, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				if(table == Table.AIRPORT) {
					Integer airportId = rset.getInt("airport_id");
					String name = rset.getString("name");
					Boolean deleted = rset.getBoolean("deleted");
					return new Airport(airportId, name, deleted);
					
				}else if(table == Table.FLIGHT) {
					Integer flightId = rset.getInt("flight_id");
					String number = rset.getString("number");
					Date departureDate = rset.getDate("departure_date");
					Date arrivalDate = rset.getDate("arrival_date");
					Airport departureAirport = (Airport)getOne(Table.AIRPORT, rset.getInt("departure_airport_id"));
					Airport arrivalAirport = (Airport)getOne(Table.AIRPORT, rset.getInt("arrival_airport_id"));
					Integer noOfSeats = rset.getInt("no_of_seats");
					Double price = rset.getDouble("price");
					Boolean deleted = rset.getBoolean("deleted");
					return new Flight(flightId, number, departureDate, arrivalDate, departureAirport, arrivalAirport, noOfSeats,
							price, deleted);
					
				}else if(table == Table.USER) {
					Integer userId = rset.getInt("user_id");
					String userName = rset.getString("user_name");
					String password = rset.getString("password");
					String firstName = rset.getString("first_name");
					String lastName = rset.getString("last_name");
					Date registrationDate = rset.getDate("registration_date");
					Role role = Role.valueOf(rset.getString("role"));
					Boolean blocked = rset.getBoolean("blocked");
					Boolean deleted = rset.getBoolean("deleted");
					return new User(userId, userName, password, firstName, lastName, registrationDate, role, blocked, deleted);
					
					
				}else if(table == Table.TICKET) {
					Integer ticketId = rset.getInt("ticket_id");
					Flight departureFlight = (Flight)getOne(Table.FLIGHT, rset.getInt("departure_flight_id"));
					Flight arrivalFlight = (Flight)getOne(Table.FLIGHT, rset.getInt("arrival_flight_id"));
					Integer departureFlightSeatNo = rset.getInt("departure_flight_seat_no");
					Integer arrrivalFlightSeatNo = rset.getInt("arrival_flight_seat_no");
					Date reservationDate = rset.getDate("reservation_date");
					Date saleDate = rset.getDate("sale_date");
					User user = (User)getOne(Table.USER, rset.getInt("user_id"));
					Boolean deleted = rset.getBoolean("deleted");
					
					return new Ticket(ticketId, departureFlight, arrivalFlight, departureFlightSeatNo, arrrivalFlightSeatNo, 
							reservationDate, saleDate, user, deleted);
					
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
			String query = "SELECT * FROM " + table.toString() + " WHERE deleted = 0";

			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				
				if(table == Table.AIRPORT) {
					Integer airportId = rset.getInt("airport_id");
					String name = rset.getString("name");
					Boolean deleted = rset.getBoolean("deleted");
					objects.add((new Airport(airportId, name, deleted)));
					
				}else if(table == Table.FLIGHT) {
					Integer flightId = rset.getInt("flight_id");
					String number = rset.getString("number");
					Date departureDate = rset.getDate("departure_date");
					Date arrivalDate = rset.getDate("arrival_date");
					Airport departureAirport = (Airport)getOne(Table.AIRPORT, rset.getInt("departure_airport_id"));
					Airport arrivalAirport = (Airport)getOne(Table.AIRPORT, rset.getInt("arrival_airport_id"));
					Integer noOfSeats = rset.getInt("no_of_seats");
					Double price = rset.getDouble("price");
					Boolean deleted = rset.getBoolean("deleted");
					objects.add(new Flight(flightId, number, departureDate, arrivalDate, departureAirport, arrivalAirport, noOfSeats,
							price, deleted));
					
				}else if(table == Table.USER) {
					Integer userId = rset.getInt("user_id");
					String userName = rset.getString("user_name");
					String password = rset.getString("password");
					String firstName = rset.getString("first_name");
					String lastName = rset.getString("last_name");
					Date registrationDate = rset.getDate("registration_date");
					Role role = Role.valueOf(rset.getString("role"));
					Boolean blocked = rset.getBoolean("blocked");
					Boolean deleted = rset.getBoolean("deleted");
					objects.add(new User(userId, userName, password, firstName, lastName, registrationDate, role, blocked, deleted));
					
					
				}else if(table == Table.TICKET) {
					Integer ticketId = rset.getInt("ticket_id");
					Flight departureFlight = (Flight)getOne(Table.FLIGHT, rset.getInt("departure_flight_id"));
					Flight arrivalFlight = (Flight)getOne(Table.FLIGHT, rset.getInt("arrival_flight_id"));
					Integer departureFlightSeatNo = rset.getInt("departure_flight_seat_no");
					Integer arrrivalFlightSeatNo = rset.getInt("arrival_flight_seat_no");
					Date reservationDate = rset.getDate("reservation_date");
					Date saleDate = rset.getDate("sale_date");
					User user = (User)getOne(Table.USER, rset.getInt("user_id"));
					Boolean deleted = rset.getBoolean("deleted");
					
					objects.add(new Ticket(ticketId, departureFlight, arrivalFlight, departureFlightSeatNo, arrrivalFlightSeatNo, 
							reservationDate, saleDate, user, deleted));
					
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
	
	public static boolean insert(Table table, Object object) {
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		
		String query = null;
		
		try {
			
			if(table == Table.AIRPORT) {
				query = "INSERT INTO Airport (name, deleted) VALUES (?, ?)";
				pstmt = conn.prepareStatement(query);
				Airport paramObject = (Airport)object;
				
				pstmt.setString(1, paramObject.getName());
				pstmt.setBoolean(2, paramObject.getDeleted());
				
				System.out.println(pstmt);
				return pstmt.executeUpdate() == 1;
				
			}else if (table == Table.FLIGHT) {
				query = "INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query);
				Flight paramObject = (Flight)object;
				
				pstmt.setString(1, paramObject.getNumber());
				pstmt.setObject(2, paramObject.getDeparture());
				pstmt.setObject(3, paramObject.getArrival());
				pstmt.setInt(4, paramObject.getFlyingFrom().getId());
				pstmt.setInt(5, paramObject.getFlyingTo().getId());
				pstmt.setInt(6, paramObject.getNumberOfSeats());
				pstmt.setDouble(7, paramObject.getPrice());
				pstmt.setBoolean(8, paramObject.getDeleted());
				
				System.out.println(pstmt);
				return pstmt.executeUpdate() == 1;
				
				
				
			}else if (table == Table.USER) {
				query = "INSERT INTO User(user_name, password, first_name, last_name, registration_date, role, blocked, deleted)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query);
				User paramObject = (User)object;
				
				pstmt.setString(1, paramObject.getUserName());
				pstmt.setString(2, paramObject.getPassword());
				pstmt.setString(3, paramObject.getFirstName());
				pstmt.setString(4, paramObject.getLastName());
				pstmt.setObject(5, paramObject.getRegistrationDate());
				pstmt.setString(6, paramObject.getRole().toString());
				pstmt.setBoolean(7, paramObject.getBlocked());
				pstmt.setBoolean(8, paramObject.getDeleted());
				
				System.out.println(pstmt);
				return pstmt.executeUpdate() == 1;
				
			}else if (table == Table.TICKET) {
				
				query = "INSERT INTO Ticket (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id, deleted)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				
				pstmt = conn.prepareStatement(query);
				Ticket paramObject = (Ticket)object;
				
				pstmt.setInt(1, paramObject.getDepartureFlight().getId());
				pstmt.setInt(2, paramObject.getArrivalFlight().getId());
				pstmt.setInt(3, paramObject.getDepartureFlightSeatNumber());
				pstmt.setInt(4, paramObject.getArrivalFlightSeatNumber());
				pstmt.setObject(5, paramObject.getReservationDate());
				pstmt.setObject(6, paramObject.getTicketSaleDate());
				pstmt.setInt(7, paramObject.getUser().getId());
				pstmt.setBoolean(8, paramObject.getDeleted());
				
				System.out.println(pstmt);
				return pstmt.executeUpdate() == 1;
				
				
			}else {
				return false;
			}
			
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			// zatvaranje naredbe i rezultata
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
		
	}
	
	
	
	//update
	public static boolean update(Table table, Object object) {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		String query = null;
		
		
		
		try {
			
			if(table == Table.AIRPORT) {
				
				query = "UPDATE Airport SET " +
						"name = ? " + 
						"deleted = ? " + 
						
						"WHERE airport_id = ?";
				
				
				pstmt = conn.prepareStatement(query);
				Airport paramObject = (Airport)object;
				
				pstmt.setString(1, paramObject.getName());
				pstmt.setBoolean(2, paramObject.getDeleted());
				
				pstmt.setInt(3, paramObject.getId());
				
				System.out.println(pstmt);
				return pstmt.executeUpdate() == 1;
			
				
			}else if (table == Table.FLIGHT) {
				query = "UPDATE Flight SET "
						+ "number = ? "
						+ "departure_date = ? "
						+ "arrival_date = ? "
						+ "departure_airport_id = ? "
						+ "arrival_airport_id = ? "
						+ "no_of_seats = ? "
						+ "price = ? "
						+ "deleted = ? "
						
						+ "WHERE flight_id = ? ";
				
				pstmt = conn.prepareStatement(query);
				Flight paramObject = (Flight)object;
				
				pstmt.setString(1, paramObject.getNumber());
				pstmt.setObject(2, paramObject.getDeparture());
				pstmt.setObject(3, paramObject.getArrival());
				pstmt.setInt(4, paramObject.getFlyingFrom().getId());
				pstmt.setInt(5, paramObject.getFlyingTo().getId());
				pstmt.setInt(6, paramObject.getNumberOfSeats());
				pstmt.setDouble(7, paramObject.getPrice());
				pstmt.setBoolean(8, paramObject.getDeleted());
				
				pstmt.setInt(9, paramObject.getId());
				
				System.out.println(pstmt);
				return pstmt.executeUpdate() == 1;
				
				
			
			}else if (table == Table.USER) {
				query = "UPDATE User SET "
						+ "user_name = ? "
						+ "password = ? "
						+ "first_name = ? "
						+ "last_name = ? "
						+ "registration_date = ? "
						+ "role = ? "
						+ "blocked = ? "
						+ "deleted = ? "
						
						+ "WHERE user_id = ?";
				
				pstmt = conn.prepareStatement(query);
				User paramObject = (User)object;
				
				pstmt.setString(1, paramObject.getUserName());
				pstmt.setString(2, paramObject.getPassword());
				pstmt.setString(3, paramObject.getFirstName());
				pstmt.setString(4, paramObject.getLastName());
				pstmt.setObject(5, paramObject.getRegistrationDate());
				pstmt.setString(6, paramObject.getRole().toString());
				pstmt.setBoolean(7, paramObject.getBlocked());
				pstmt.setBoolean(8, paramObject.getDeleted());
				
				pstmt.setInt(9, paramObject.getId());
				
				System.out.println(pstmt);
				return pstmt.executeUpdate() == 1;
		
				
			}else if (table == Table.TICKET) {
				
				query = "UPDATE Ticket SET "
						+ "departure_flight_id = ? "
						+ "arrival_flight_id = ? "
						+ "departure_flight_seat_no = ?"
						+ "arrival_flight_seat_no = ?"
						+ "reservation_date = ? "
						+ "sale_date = ? "
						+ "user_id = ? "
						+ "deleted = ? "
						
						
						+ "WHERE ticket_id = ?";
				
				pstmt = conn.prepareStatement(query);
				Ticket paramObject = (Ticket)object;
				
				pstmt.setInt(1, paramObject.getDepartureFlight().getId());
				pstmt.setInt(2, paramObject.getArrivalFlight().getId());
				pstmt.setInt(3, paramObject.getDepartureFlightSeatNumber());
				pstmt.setInt(4, paramObject.getArrivalFlightSeatNumber());
				pstmt.setObject(5, paramObject.getReservationDate());
				pstmt.setObject(6, paramObject.getTicketSaleDate());
				pstmt.setInt(7, paramObject.getUser().getId());
				pstmt.setBoolean(8, paramObject.getDeleted());
				
				pstmt.setInt(9, paramObject.getId());
				
				System.out.println(pstmt);
				return pstmt.executeUpdate() == 1;
				
				
				
			}else {
				return false;
			}
		
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			// zatvaranje naredbe i rezultata
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	

	//Potrebno je administratorima prikazati komandu za brisanje leta. Ako let ima rezervacije i one bivaju obrisane.
	//Ako let ima prodate karte, brisanje treba da bude samo logičko
	//Samo rezervacija se može brisati. Potrebno je omogućiti brisanje rezervacije. Brisanje može da izvrši korisnik
	//koji je napravio rezervaciju i administrator.
	//Administratorima je potrebno omogućiti brisanje korisnika. Ako korisnik ima rezervacije i one bivaju obrisane.
	//Ako korisnik ima prodate karte, brisanje treba da bude samo logičko (korisnik ne treba da se prikazuje u
	//administratorovoj tabeli korisnika, ali mu se i dalje može pristupiti putem link-ova na stranicama prodatih
	//karata).
	
	
	//delete - only logical
	//redudandant a bit!
	public static boolean delete(Table table, Object object) {
		
		if(table == Table.AIRPORT) {
			Airport paramObject = (Airport)object;
			paramObject.setDeleted(true);
			return update(table, paramObject);
			
		}else if(table == Table.FLIGHT) {
			Flight paramObject = (Flight)object;
			paramObject.setDeleted(true);
			return update(table, paramObject);
			
		}else if(table == Table.TICKET) {
			Ticket paramObject = (Ticket)object;
			paramObject.setDeleted(true);
			return update(table, paramObject);
			
		}else if(table == Table.USER) {
			
			User paramObject = (User)object;
			paramObject.setDeleted(true);
			return update(table, paramObject);
			
		}else {
			return false;
		}
	}
	
	
	
	
	
	

}
