package airline.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;

import airline.dto.ReportDTO;
import airline.dto.SearchFlightDTO;
import airline.dto.TotalReportDTO;
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
		
		
		String idName = idHelper(table);
		
		try {
			String deletedCondition = (table == table.FLIGHT) ? ";" : "AND deleted = 0;";
			String query = "SELECT * FROM " + table.toString() + " WHERE " + idName + "= ? " + deletedCondition;
			

			pstmt = conn.prepareStatement(query);
			
			int index = 1;
			pstmt.setInt(index++, id);

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
				query = "INSERT INTO " + table.toString() + " (name, deleted) VALUES (?, ?)";
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
				query = "INSERT INTO " + table.toString() + " (number, departure_date, arrival_date, departure_airport_id, arrival_airport_id, no_of_seats, price, deleted)"
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
				query = "INSERT INTO " + table.toString() + " (user_name, password, first_name, last_name, registration_date, role, blocked, deleted)"
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
				
				query = "INSERT INTO " + table.toString() + " (departure_flight_id, arrival_flight_id, departure_flight_seat_no, arrival_flight_seat_no, reservation_date, sale_date, user_id, deleted)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				
				pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				Ticket paramObject = (Ticket)object;
				
				pstmt.setInt(1, paramObject.getDepartureFlight().getId());
				//optional arrival flight number and its seat:
				if(paramObject.getArrivalFlight() != null) {
					pstmt.setInt(2, paramObject.getArrivalFlight().getId());
					pstmt.setInt(4, paramObject.getArrivalFlightSeatNumber());
				}else {
					pstmt.setObject(2, null);
					pstmt.setObject(4, null);
				}
				pstmt.setInt(3, paramObject.getDepartureFlightSeatNumber());
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
				
				query = "UPDATE " + table.toString() + " SET " +
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
				query = "UPDATE " + table.toString() + " SET "
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
				
				pstmt.executeUpdate();
				
				return object;
				
				
			
			}else if (table == Table.USER) {
				query = "UPDATE " + table.toString() + " SET "
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
				
				pstmt.executeUpdate();
				
				return object;
		
				
			}else if (table == Table.TICKET) {
				
				query = "UPDATE " + table.toString() + " SET "
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
				if(paramObject.getArrivalFlight() != null) {
					pstmt.setInt(2, paramObject.getArrivalFlight().getId());
					pstmt.setInt(4, paramObject.getArrivalFlightSeatNumber());
				}else {
					pstmt.setObject(2, null);
					pstmt.setObject(4, null);
				}
				
				pstmt.setInt(3, paramObject.getDepartureFlightSeatNumber());
				
				
				pstmt.setObject(5, paramObject.getReservationDate());
				pstmt.setObject(6, paramObject.getTicketSaleDate());
				pstmt.setInt(7, paramObject.getUser().getId());
				pstmt.setBoolean(8, paramObject.getDeleted());
				
				pstmt.setInt(9, paramObject.getId());
				
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
	public static Boolean delete(Table table, Integer id) throws SQLException {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		String query = null;
		
		
		if(table == Table.AIRPORT) {
			
			query = "UPDATE " + table.toString() + " SET " +
					"deleted = 1 " + 
					
					"WHERE airport_id = ?";
			
			
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			return true;	
			
		}else if(table == Table.FLIGHT) {
			//transaction:
			conn.setAutoCommit(false); 
			conn.commit();
			
			
			query = "UPDATE " + table.toString() + " SET " +
					"deleted = 1 " + 
					
					"WHERE flight_id = ?";
			
			
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
			query = "UPDATE ticket SET " + 
					"deleted = 1 " + 
					"WHERE (departure_flight_id = ? OR arrival_flight_id = ?) AND sale_date IS NULL;";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			
			pstmt.executeUpdate();
			conn.commit();
			
			return true;	
			
		}else if(table == Table.TICKET) {
			
			
			query = "UPDATE " + table.toString() + " SET " +
					"deleted = 1 " + 
					
					"WHERE ticket_id = ?";
			
			
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			return true;	
			
		}else if(table == Table.USER) {
			
			query = "UPDATE " + table.toString() + " SET " +
					"deleted = 1 " + 
					
					"WHERE user_id = ?";
			
			
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			return true;	
			
		}else {
			return false;
		}
	}
	
	
	public static Boolean blockUser(Integer id) throws SQLException {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		String query = null;
		
		query = "UPDATE USER SET " +
				"blocked = 1 " + 
				
				"WHERE user_id = ?";
		
		
		pstmt = conn.prepareStatement(query);	
		pstmt.setInt(1, id);
		
		pstmt.executeUpdate();
		return true;	
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
			
			query = "select * from " + table.toString();
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
				//dont parse use string from JSON:
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				dateLowDeparture = format.parse(queryObject.getDateLowDeparture());
				dateHighDeparture = format.parse(queryObject.getDateHighDeparture());
				dateLowArrival = format.parse(queryObject.getDateLowArrival());
				dateHighArrival = format.parse(queryObject.getDateHighArrival());
				
			}catch(Exception e) {System.out.println(e.getMessage());}
			
			//one parameter at least:
			if(
					(number != null && number != "") || 
					(departureAirportIds != null && departureAirportIds.length > 0) || 
					(arrivalAirportIds != null && arrivalAirportIds.length > 0) ||
					(lowestPrice != null && hightestPrice != null) ||
					(dateLowDeparture != null && dateHighDeparture != null) ||
					(dateLowArrival != null && dateHighArrival != null)
					
			) 
			// whare if one at least
			{
				query += " where ";
			}
			
			//individual checkes:
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
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        //to convert Date to String, use format method of SimpleDateFormat class.
	        //String strDate = dateFormat.format(date);
	 
			System.out.println(dateLowDeparture);
			System.out.println(dateHighDeparture);
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
				query += "'";
				query += dateFormat.format(dateLowDeparture);
				query += "'";
				query += " and ";
				query += "'";
				query += dateFormat.format(dateHighDeparture);
				query += "'";
			}
			
			System.out.println(dateLowArrival);
			System.out.println(dateHighArrival);
			
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
				query += "'";
				query += dateFormat.format(dateLowArrival);
				query += "'";
				query += " and ";
				query += "'";
				query += dateFormat.format(dateHighArrival);
				query += "'";
			}
			
			
			query += ";";
			
//			System.out.println("Before processing into a pstmt: " + query);
//			System.out.println("DateLowDeparture " + dateLowDeparture);
//			System.out.println("DateHighDeparture " + dateHighDeparture);
			
			System.out.println(query);
			
			
			try {
				pstmt = conn.prepareStatement(query);
				return pstmt;
				//EVERYTHING ELSE SQL INJECTION PRONE!!!
			}catch(SQLException e) {System.out.println("Error in query!");}
			
			break;
		case USER:
			try {
				query = "SELECT * FROM " + table.toString() + " WHERE user_name LIKE ? OR role LIKE ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, o.toString());
				pstmt.setString(2, o.toString());
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
		
		if(o == null || o.toString().equals("") || o.toString() == null){
			return getAll(table);
		}
		
		try {
			pstmt = generateFindQuery(conn, table, o);
			

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
			String query = "SELECT * FROM TICKET WHERE user_id = ? AND deleted = 0;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, userId);
			

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
	
	public static ArrayList<Ticket> getFlightTickets(Integer flightId) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM TICKET WHERE departure_flight_id = ?  OR arrival_flight_id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, flightId);
			pstmt.setInt(2, flightId);
			

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
			String query = "SELECT * FROM FLIGHT WHERE departure_date >= NOW() AND deleted = 0;";
			
			pstmt = conn.prepareStatement(query);
			

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
	
	public static ArrayList<Flight> getReturningFlights(Integer flight){
		ArrayList<Flight> flights = new ArrayList<Flight>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM FLIGHT WHERE departure_airport_id IN (SELECT arrival_airport_id FROM FLIGHT WHERE flight_id = ? AND departure_date > NOW()) AND departure_date >= (SELECT arrival_date FROM FLIGHT WHERE flight_id = ? AND departure_date > NOW() AND deleted = 0);";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, flight);
			pstmt.setInt(2, flight);
			

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
	
	


	public static ArrayList<Integer> getOccupiedSeats(Integer flight){
		ArrayList<Integer> occupiedSeats = new ArrayList<Integer>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset1 = null;
		ResultSet rset2 = null;
		
		
		
		try {
			
			String query1 = "SELECT departure_flight_seat_no FROM TICKET WHERE departure_flight_id = ? AND deleted = 0;";
			
			pstmt1 = conn.prepareStatement(query1);
			pstmt1.setInt(1, flight);
			rset1 = pstmt1.executeQuery();
			
			
			
			String query2 = "SELECT arrival_flight_seat_no FROM TICKET WHERE arrival_flight_id = ? AND deleted = 0;";
			
			pstmt2 = conn.prepareStatement(query2);
			pstmt2.setInt(1, flight);
			rset2 = pstmt2.executeQuery();
			
			System.out.println(pstmt2);
			
		
			while (rset1.next()) {
				Integer seatNo = rset1.getInt("departure_flight_seat_no");
				occupiedSeats.add(seatNo);
			}
			
		
			while (rset2.next()) {
				Integer seatNo = rset2.getInt("arrival_flight_seat_no");
				occupiedSeats.add(seatNo);
			}
			
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();

		} finally {
			try {pstmt1.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {pstmt2.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset1.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset2.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		
		return occupiedSeats;
		
		
	}
	
	public static Boolean checkAvailibleFlightNumber(String newNumber) {
		//SELECT number FROM flight WHERE number LIKE 'a1';

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT number FROM FLIGHT WHERE number LIKE ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, newNumber);
	

			rset = pstmt.executeQuery();
			if (rset.next()) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return null;
		
	}
	

	
	public static Boolean checkAirportAvailibility(Integer airportId) {
		//SELECT number FROM flight WHERE number LIKE 'a1';

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT airport_id FROM AIRPORT WHERE airport_id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, airportId);
	

			rset = pstmt.executeQuery();
			if (rset.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return null;
		
	}
	
	public static Boolean checkFlightExistance(Integer flightId) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT flight_id FROM FLIGHT WHERE flight_id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, flightId);
	
			rset = pstmt.executeQuery();
			if (rset.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return null;
	}
	
	
	
	
	public static Boolean checkFlightSeatAvailibility(Integer flightId, Integer seatNo) {
		ArrayList<Integer> occupiedSeats =  getOccupiedSeats(flightId);
		if(occupiedSeats.contains(seatNo)) {
			return false;
		}else {
			return true;
		}
	}
	
	
	public static ArrayList<ReportDTO> getReportByAirportAllTime(){
		ArrayList<ReportDTO> reports = new ArrayList<ReportDTO>();
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM total_by_airport_all_time;";
			
			pstmt = conn.prepareStatement(query);
			

			rset = pstmt.executeQuery();
			while (rset.next()) {
				String airportName = rset.getString("name");
				Integer numberOfFlights = rset.getInt("no_of_flights");
				Integer numberOfTickets = rset.getInt("total_tickets_sold");
				Double totalRevenue = rset.getDouble("total_revenue");
				
				reports.add(new ReportDTO(airportName, numberOfFlights, numberOfTickets, totalRevenue));
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return reports;
		
	}
	
	public static ArrayList<ReportDTO> getSpecificReportByAirport(Date dateLow, Date dateHigh){
		ArrayList<ReportDTO> reports = new ArrayList<ReportDTO>();
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT name, count(flight_id) as no_of_flights, sum(no_of_sold_tickets) as total_tickets_sold, sum(total_revenue) as total_revenue \r\n" + 
					"		FROM (\r\n" + 
					"			SELECT a.airport_id, a.name, flight_id, count(ticket_id) as no_of_sold_tickets, price * count(ticket_id) AS total_revenue\r\n" + 
					"			FROM \r\n" + 
					"				AIRPORT a join FLIGHT f on a.airport_id = f.departure_airport_id or a.airport_id = f.arrival_airport_id\r\n" + 
					"					left outer join TICKET t on (f.flight_id = t.departure_flight_id or f.flight_id = t.arrival_flight_id) and t.sale_date IS NOT NULL\r\n" + 
					"			WHERE departure_date between ? and ? or sale_date between ? and ?\r\n" + 
					"			GROUP BY a.airport_id, f.flight_id\r\n" + 
					"		) AS nested\r\n" + 
					"GROUP BY airport_id, name;";
			
			pstmt = conn.prepareStatement(query);
			
			if(dateLow == null) {
				pstmt.setString(1, "1000-01-01 00:00:00");
				pstmt.setString(3, "1000-01-01 00:00:00");
			}else {
				pstmt.setObject(1, dateLow);
				pstmt.setObject(3, dateLow);
			}
			
			if(dateHigh == null) {
				pstmt.setString(2, "9999-12-31 23:59:59");
				pstmt.setString(4, "9999-12-31 23:59:59");
			}else {
				pstmt.setObject(2, dateHigh);
				pstmt.setObject(4, dateHigh);
			}
			
			

			rset = pstmt.executeQuery();
			while (rset.next()) {
				String airportName = rset.getString("name");
				Integer numberOfFlights = rset.getInt("no_of_flights");
				Integer numberOfTickets = rset.getInt("total_tickets_sold");
				Double totalRevenue = rset.getDouble("total_revenue");
				
				reports.add(new ReportDTO(airportName, numberOfFlights, numberOfTickets, totalRevenue));
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return reports;
		
	}
	
	public static ArrayList<TotalReportDTO> getReportAllTime(){
		ArrayList<TotalReportDTO> reports = new ArrayList<TotalReportDTO>();
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM total_all_time;";
			
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Integer totalFlights = rset.getInt("total_flights");
				Integer totalTickets = rset.getInt("total_tickets");
				Double totalRevenue = rset.getDouble("total");
				
				reports.add(new TotalReportDTO(totalFlights, totalTickets, totalRevenue));
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return reports;
		
	}
	
	

	
	
	
	

}
