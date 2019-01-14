package airline.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;

import airline.dto.SearchFlightDTO;
import airline.dto.SearchUserDTO;
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
	
	private static Integer getGeneratedId(PreparedStatement pstmt) throws SQLException {
		ResultSet returnedKeys = pstmt.getGeneratedKeys();
		if(returnedKeys.next()) {
			Integer generatedId = returnedKeys.getInt(1);
			return generatedId;
			
		}else {
			return null;
		}
		
	}
	
	
	
	public static Object getOne(Table table, Integer id){
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		System.out.println(conn);
		
		String idName = idHelper(table);
		
		try {
			String query = "SELECT * FROM " + table.toString() + " WHERE " + idName + "=? AND deleted = 0";
	

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
		
		System.out.println(conn);
		
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
	
	public static Object insert(Table table, Object object) throws SQLException {
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		
		String query = null;
		
		try {
			
			if(table == Table.AIRPORT) {
				query = "INSERT INTO Airport (name, deleted) VALUES (?, ?)";
				pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				Airport paramObject = (Airport)object;
				
				pstmt.setString(1, paramObject.getName());
				pstmt.setBoolean(2, paramObject.getDeleted());
				
				System.out.println(pstmt);
				pstmt.executeUpdate();
				
				Airport airport = (Airport)object;
				airport.setId(getGeneratedId(pstmt));
				
				return object;
				
				
				
				
				
			}else if (table == Table.FLIGHT) {
				query = "INSERT INTO Flight (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				Flight paramObject = (Flight)object;
				
				pstmt.setString(1, paramObject.getNumber());
				pstmt.setObject(2, paramObject.getDeparture());
				pstmt.setObject(3, paramObject.getArrival());
				pstmt.setInt(4, paramObject.getFlyingFrom().getId());
				pstmt.setInt(5, paramObject.getFlyingTo().getId());
				pstmt.setInt(6, paramObject.getNumberOfSeats());
				pstmt.setDouble(7, paramObject.getPrice());
				pstmt.setBoolean(8, paramObject.getDeleted());
				
				
				pstmt.executeUpdate();
				
				Flight flight = (Flight)object;
				flight.setId(getGeneratedId(pstmt));
				
				return object;
				
				
				
			}else if (table == Table.USER) {
				query = "INSERT INTO User(user_name, password, first_name, last_name, registration_date, role, blocked, deleted)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				User paramObject = (User)object;
				
				pstmt.setString(1, paramObject.getUserName());
				pstmt.setString(2, paramObject.getPassword());
				pstmt.setString(3, paramObject.getFirstName());
				pstmt.setString(4, paramObject.getLastName());
				pstmt.setObject(5, paramObject.getRegistrationDate());
				pstmt.setString(6, paramObject.getRole().toString());
				pstmt.setBoolean(7, paramObject.getBlocked());
				pstmt.setBoolean(8, paramObject.getDeleted());
				
				pstmt.executeUpdate();
				
				User user = (User)object;
				user.setId(getGeneratedId(pstmt));
				
				return object;
				
			}else if (table == Table.TICKET) {
				
				query = "INSERT INTO Ticket (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id, deleted)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				
				pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				Ticket paramObject = (Ticket)object;
				
				pstmt.setInt(1, paramObject.getDepartureFlight().getId());
				pstmt.setInt(2, paramObject.getArrivalFlight().getId());
				pstmt.setInt(3, paramObject.getDepartureFlightSeatNumber());
				pstmt.setInt(4, paramObject.getArrivalFlightSeatNumber());
				pstmt.setObject(5, paramObject.getReservationDate());
				pstmt.setObject(6, paramObject.getTicketSaleDate());
				pstmt.setInt(7, paramObject.getUser().getId());
				pstmt.setBoolean(8, paramObject.getDeleted());
				
				pstmt.executeUpdate();
				
				Ticket ticket = (Ticket)object;
				ticket.setId(getGeneratedId(pstmt));
				
				return object;
				
				
			}else {
				return null;
			}
			
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			//ex.printStackTrace();
			throw new SQLException(ex);
		} finally {
			// zatvaranje naredbe i rezultata
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
	
		
	}
	
	
	
	//update
	public static Object update(Table table, Object object) throws SQLException {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		String query = null;
		
		
		
		try {
			
			if(table == Table.AIRPORT) {
				
				query = "UPDATE Airport SET " +
						"name = ?, " + 
						"deleted = ? " + 
						
						"WHERE airport_id = ?";
				
				
				pstmt = conn.prepareStatement(query);
				Airport paramObject = (Airport)object;
				
				pstmt.setString(1, paramObject.getName());
				pstmt.setBoolean(2, paramObject.getDeleted());
				
				pstmt.setInt(3, paramObject.getId());
				
				System.out.println(pstmt);
				pstmt.executeUpdate();
				
				return object;
			
				
			}else if (table == Table.FLIGHT) {
				query = "UPDATE Flight SET "
						+ "number = ?, "
						+ "departure_date = ?, "
						+ "arrival_date = ?, "
						+ "departure_airport_id = ?, "
						+ "arrival_airport_id = ?, "
						+ "no_of_seats = ?, "
						+ "price = ?, "
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
				pstmt.executeUpdate();
				
				return object;
				
				
			
			}else if (table == Table.USER) {
				query = "UPDATE User SET "
						+ "user_name = ?, "
						+ "password = ?, "
						+ "first_name = ?, "
						+ "last_name = ?, "
						+ "registration_date = ?, "
						+ "role = ?, "
						+ "blocked = ?, "
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
				pstmt.executeUpdate();
				
				return object;
		
				
			}else if (table == Table.TICKET) {
				
				query = "UPDATE Ticket SET "
						+ "departure_flight_id = ?, "
						+ "arrival_flight_id = ?, "
						+ "departure_flight_seat_no = ?, "
						+ "arrival_flight_seat_no = ?, "
						+ "reservation_date = ?, "
						+ "sale_date = ?, "
						+ "user_id = ?, "
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
				pstmt.executeUpdate();
				
				return object;
				
				
				
			}else {
				return null;
			}
		
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
			throw new SQLException(ex);
		} finally {
			// zatvaranje naredbe i rezultata
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		
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
	public static Object delete(Table table, Object object) throws SQLException {
		
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
	
	
	public static String buildArrayString(Integer[] arr) {
		String stringArray = "(";
		for(int i = 0; i < arr.length; i++) {
			if(i < arr.length - 1) {
				stringArray += arr[i].toString();
				stringArray += ", ";
			}else if(i == arr.length -1){
				stringArray += arr[i].toString();
				stringArray += ")";
			}
			
		}
		return stringArray;
	}
	
	
	public static PreparedStatement generateFindQuery(Connection conn, Table table, Object o) {
		PreparedStatement pstmt = null;
		String query = null;	
		switch(table) {
		case AIRPORT:
			
			break;
		case FLIGHT:
			//what the query should look like:
			
//			select * from flight 
//			where 
//				number like 'a1' and
//			    departure_airport_id in (1, 2) and
//			    arrival_airport_id in (1, 4) and
//			    price between 0 and 800 and
//			    departure_date between '2018-12-04' and  '2018-12-05'  and
//			    arrival_date between '2018-11-04' and  '2018-11-05' ;
			
			//prepare the query:
			
			query = "select * from flight";
			SearchFlightDTO queryObject = SearchFlightDTO.class.cast(o);
			
			
			//get query parts:
			String number = null;
			Integer[] departureAirportIds = null;
			Integer[] arrivalAirportIds = null;
			Double lowestPrice = null;
			Double hightestPrice = null;
			Date dateLowDeparture = null;
			Date dateHighDeparture = null;
			Date dateLowArrival = null;
			Date dateHighArrival = null;
			
			try {
				number = queryObject.getQueryText();
				departureAirportIds = queryObject.getDepartureAirports();
				arrivalAirportIds = queryObject.getArrivalAirports();
			}catch(Exception e) {}
			try {
				lowestPrice = Double.parseDouble(queryObject.getLowestPrice());
				hightestPrice = Double.parseDouble(queryObject.getHighestPrice());
			}catch(Exception e) {}
			try {
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateLowDeparture = format.parse(queryObject.getDateLowDeparture());
				dateHighDeparture = format.parse(queryObject.getDateHighDeparture());
				dateLowArrival = format.parse(queryObject.getDateLowArrival());
				dateHighArrival = format.parse(queryObject.getDateHighArrival());
			}catch(Exception e) {}
			
			if(
					(number != null && number != "") || 
					(departureAirportIds != null && departureAirportIds.length > 0) || 
					(arrivalAirportIds != null && arrivalAirportIds.length > 0) ||
					(lowestPrice != null && hightestPrice != null) ||
					(dateLowDeparture != null && dateHighDeparture != null) ||
					(dateLowArrival != null && dateHighArrival != null)
					
			) {
				query += " where ";
			}
					
			if(number != null && number != "") {
				query += "number like '";
				query += number;
				query += "'";
			}
			if(departureAirportIds != null && departureAirportIds.length > 0) {
				if(number != null && number != "") {
					query += " and ";
				}
				query += "departure_airport_id in ";
				query += buildArrayString(departureAirportIds);
				
			}
			if(arrivalAirportIds != null && arrivalAirportIds.length > 0) {
				if(
						(number != null && number != "") || 
						(departureAirportIds != null && departureAirportIds.length > 0)
				) {
					query += " and ";
				}
				query += "arrival_airport_id in ";
				query += buildArrayString(arrivalAirportIds);
				
			}
			if(lowestPrice != null && hightestPrice != null) {
				if(
						(number != null && number != "") || 
						(departureAirportIds != null && departureAirportIds.length > 0) || 
						(arrivalAirportIds != null && arrivalAirportIds.length > 0)
						
				) {
					query += " and ";
				}
					
				query += "price between ";
				query += lowestPrice;
				query += " and ";
				query += hightestPrice;
				
			}
			if(dateLowDeparture != null && dateHighDeparture != null) {
				if(
						(number != null && number != "") || 
						(departureAirportIds != null && departureAirportIds.length > 0) || 
						(arrivalAirportIds != null && arrivalAirportIds.length > 0) ||
						(lowestPrice != null && hightestPrice != null)
						
				) {
					query += " and ";
				}
				query += "departure_date between ";
				query += dateLowDeparture;
				query += " and ";
				query += dateHighDeparture;
			}
			if(dateLowArrival != null && dateHighArrival != null) {
				if(
						(number != null && number != "") || 
						(departureAirportIds != null && departureAirportIds.length > 0) || 
						(arrivalAirportIds != null && arrivalAirportIds.length > 0) ||
						(lowestPrice != null && hightestPrice != null) ||
						(dateLowDeparture != null && dateHighDeparture != null)
						
				) {
					query += " and ";
				}
				query += "arrival_date between ";
				query += dateLowArrival;
				query += " and ";
				query += dateHighArrival;
			}
			
			
			query += ";";
			
			System.out.println("Before processing into a pstmt: " + query);
			System.out.println("DateLowDeparture " + dateLowDeparture);
			System.out.println("DateHighDeparture " + dateHighDeparture);
			
			
			try {
				pstmt = conn.prepareStatement(query);
				return pstmt;
				//EVERYTHING ELSE SQL INJECTION PRONE!!!
			}catch(SQLException e) {System.out.println("Error in query!");}
			
			break;
		case USER:
			try {
				query = "SELECT * FROM user WHERE user_name LIKE ? OR role LIKE ?";
				pstmt = conn.prepareStatement(query);
				String q = SearchUserDTO.class.cast(o).getQuery();
				pstmt.setString(1, q);
				pstmt.setString(2, q);
				return pstmt;
			}catch(SQLException ex) {System.out.println("Error in query!");}
			break;
		case TICKET:
			//doSearch(request, response);
			break;	
		default:
			return null;
		
		}
		return null;
	}
	
	public static List<Object> find(Table table, Object o){
		
		List<Object> objects = new ArrayList<Object>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		System.out.println(conn);
		
		if(o == null){
			return getAll(table);
		}
		
		try {
			pstmt = generateFindQuery(conn, table, o);
			
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

	
	public static ArrayList<Ticket> getUserTickets(Integer userId) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM ticket WHERE user_id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, userId);
			
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				
			
				Integer ticketId = rset.getInt("ticket_id");
				Flight departureFlight = (Flight)getOne(Table.FLIGHT, rset.getInt("departure_flight_id"));
				Flight arrivalFlight = (Flight)getOne(Table.FLIGHT, rset.getInt("arrival_flight_id"));
				Integer departureFlightSeatNo = rset.getInt("departure_flight_seat_no");
				Integer arrrivalFlightSeatNo = rset.getInt("arrival_flight_seat_no");
				Date reservationDate = rset.getDate("reservation_date");
				Date saleDate = rset.getDate("sale_date");
				User user = (User)getOne(Table.USER, rset.getInt("user_id"));
				Boolean deleted = rset.getBoolean("deleted");
				
				tickets.add(new Ticket(ticketId, departureFlight, arrivalFlight, departureFlightSeatNo, arrrivalFlightSeatNo, 
						reservationDate, saleDate, user, deleted));
				
				
				
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return tickets;
		
		
	}
	
	
	public static ArrayList<Flight> getCurrentFlights() {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM flight WHERE departure_date >= NOW();";
			
			pstmt = conn.prepareStatement(query);
			
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				
			
				Integer flightId = rset.getInt("flight_id");
				String number = rset.getString("number");
				Date departureDate = rset.getDate("departure_date");
				Date arrivalDate = rset.getDate("arrival_date");
				Airport departureAirport = (Airport)getOne(Table.AIRPORT, rset.getInt("departure_airport_id"));
				Airport arrivalAirport = (Airport)getOne(Table.AIRPORT, rset.getInt("arrival_airport_id"));
				Integer noOfSeats = rset.getInt("no_of_seats");
				Double price = rset.getDouble("price");
				Boolean deleted = rset.getBoolean("deleted");
				flights.add(new Flight(flightId, number, departureDate, arrivalDate, departureAirport, arrivalAirport, noOfSeats,
						price, deleted));
				
				
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return flights;
		
		
	}
	
	
	
	
	
	

}
