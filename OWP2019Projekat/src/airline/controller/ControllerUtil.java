package airline.controller;

import java.util.HashMap;

public class ControllerUtil {
	
	
	public enum GenericUriMeaning{
		ERROR,
		
		AIRPORT_ALL,
		AIRPORT_ONE,
		AIRPORT_CREATE,
		AIRPORT_UPDATE,
		
		USER_ALL,
		USER_ONE,
		USER_CREATE,
		USER_UPDATE,
		USER_TICKETS,
		USER_SEARCH,
		
		FLIGHT_ALL,
		FLIGHT_ONE,
		FLIGHT_CREATE,
		FLIGHT_UPDATE,
		FLIGHT_SEARCH,
		FLIGHT_CURRENT,
		FLIGHT_OCCUPIED_SEATS,
		FLIGHT_RETURNING,
		FLIGHT_DEPARTURE_AIRPORT,
		FLIGHT_ARRIVAL_AIRPORT,
		
		TICKET_ALL,
		TICKET_ONE,
		TICKET_CREATE,
		TICKET_UPDATE
		
		
	}
	
	public static Integer airportId;
	public static Integer userId;
	public static String userSearchQuery;
	public static Integer flightId;
	public static Integer ticketId;
	
	

	
	public static GenericUriMeaning genericChecker(String uri) {
		HashMap<String, String> uriComponents = genericUriDissasembly(uri);
		
		if(uriComponents.get("apiName") == null) {
			//api name null:
			return GenericUriMeaning.ERROR;
		}else {
			if(!uriComponents.get("apiName").equals("airline")) {
				//wrong api name:
				return GenericUriMeaning.ERROR;
			}else {
				if(uriComponents.get("entityName") == null) {
					//entity name null:
					return GenericUriMeaning.ERROR;
				}else if(
						!uriComponents.get("entityName").equals("user") &&
						!uriComponents.get("entityName").equals("flight") &&
						!uriComponents.get("entityName").equals("ticket") &&
						!uriComponents.get("entityName").equals("airport") 
						
				) {
					//wrong entity name:
					return GenericUriMeaning.ERROR;
				}else {
					//entity name is one of the supported:
					if(uriComponents.get("entityName").equals("user")) {
						//enitity is user:
						if(uriComponents.get("entityId") == null && uriComponents.get("subcollection") == null) {
							//all users:
							return GenericUriMeaning.USER_ALL;
						}else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection") == null) {
							//one user:
							userId = parser(uriComponents.get("entityId"));
							return GenericUriMeaning.USER_ONE;
						}else if(uriComponents.get("entityId").equals("create") && uriComponents.get("subcollection") == null) {
							//create user:
							return GenericUriMeaning.USER_CREATE;
						}else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection").equals("update")) {
							//update user:
							return GenericUriMeaning.USER_UPDATE;
						}else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection").equals("tickets")) {
							//users subcollection: tickets
							return GenericUriMeaning.USER_TICKETS;
						}else if(uriComponents.get("entityId").equals("search") && uriComponents.get("subcollection") != null) {
							//users subcollection: tickets
							userSearchQuery = uriComponents.get("subcollection");
							return GenericUriMeaning.USER_SEARCH;
						}else {
							return GenericUriMeaning.ERROR;
						}
						
						
					}else if(uriComponents.get("entityName").equals("flight")) {
						//enitity is flight:
						if(uriComponents.get("entityId") == null && uriComponents.get("subcollection") == null) {
							//all flights:
							return GenericUriMeaning.FLIGHT_ALL;
						}else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection") == null) {
							//one flight:
							flightId = parser(uriComponents.get("entityId"));
							return GenericUriMeaning.FLIGHT_ONE;
						}else if(uriComponents.get("entityId").equals("create") && uriComponents.get("subcollection") == null) {
							//create user:
							return GenericUriMeaning.FLIGHT_CREATE;
						}else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection").equals("update")) {
							//update flights:
							return GenericUriMeaning.FLIGHT_UPDATE;
						}else if(uriComponents.get("entityId").equals("search") && uriComponents.get("subcollection") == null) {
							//search flights:
							return GenericUriMeaning.FLIGHT_SEARCH;
						}else if(uriComponents.get("entityId").equals("current") && uriComponents.get("subcollection") == null) {
							//search flights:
							return GenericUriMeaning.FLIGHT_CURRENT;
						}else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection").equals("occupied-seats")) {
							//flight occuped seats:
							return GenericUriMeaning.FLIGHT_OCCUPIED_SEATS;
						}else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection").equals("returning")) {
							//returnign flight for flight id:
							return GenericUriMeaning.FLIGHT_RETURNING;
						}
						else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection").equals("departureAirport")) {
							//returnign flight for flight id:
							return GenericUriMeaning.FLIGHT_DEPARTURE_AIRPORT;
						}
						else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection").equals("arrivalAirport")) {
							//returnign flight for flight id:
							return GenericUriMeaning.FLIGHT_ARRIVAL_AIRPORT;
						}else {
							return GenericUriMeaning.ERROR;
						}
						
										
					}else if(uriComponents.get("entityName").equals("ticket")) {
						//enitity is ticket:
						if(uriComponents.get("entityId") == null && uriComponents.get("subcollection") == null) {
							//all tickets:
							return GenericUriMeaning.TICKET_ALL;
						}else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection") == null) {
							//one ticket:
							ticketId = parser(uriComponents.get("entityId"));
							return GenericUriMeaning.TICKET_ONE;
						}else if(uriComponents.get("entityId").equals("create") && uriComponents.get("subcollection") == null) {
							//create ticket:
							return GenericUriMeaning.TICKET_CREATE;
						}else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection").equals("update")) {
							//update ticket:
							return GenericUriMeaning.TICKET_UPDATE;
						}else {
							return GenericUriMeaning.ERROR;
						}
						
						
						
					}else if(uriComponents.get("entityName").equals("airport")) {
						//enitity is airport:
						if(uriComponents.get("entityId") == null && uriComponents.get("subcollection") == null) {
							//all airports:
							return GenericUriMeaning.AIRPORT_ALL;
						}else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection") == null) {
							//one airport:
							airportId = parser(uriComponents.get("entityId"));
							return GenericUriMeaning.AIRPORT_ONE;
						}else if(uriComponents.get("entityId").equals("create") && uriComponents.get("subcollection") == null) {
							//create airport:
							return GenericUriMeaning.AIRPORT_CREATE;
						}else if(parsableChecker(uriComponents.get("entityId")) && uriComponents.get("subcollection").equals("update")) {
							//update airport:
							return GenericUriMeaning.AIRPORT_UPDATE;
						}else {
							return GenericUriMeaning.ERROR;
						}
						
						
						
					}
					
				}
					
			}
			
		}
		
		return GenericUriMeaning.ERROR;
		
	}
	
	public static Integer parser(String s) {
		return Integer.parseInt(s);
	}
	
	public static Boolean parsableChecker(String entityId) {
		Integer id = null;
		try {
			id = Integer.parseInt(entityId);
		}catch(Exception e) {}
		if(id == null) {
			return false;
		}else {
			return true;
		}
	}
	
	
	public static HashMap<String, String> genericUriDissasembly(String uri) {
		//example uri: localhost:8080/airline/ticket/1/update
		String[] uriParts = uri.split("/");
		String apiName = null;
		String entityName = null;
		String entityId = null;
		String subcollection = null;
		try {
			apiName = uriParts[1];
		}catch(Exception e) {};
		try {
			entityName = uriParts[2];
		}catch(Exception e) {};
		try {
			entityId = uriParts[3];
		}catch(Exception e) {};
		try {
			subcollection = uriParts[4];
		}catch(Exception e) {};
		HashMap<String, String> uriComponents = new HashMap<String, String>();
		uriComponents.put("apiName", apiName);
		uriComponents.put("entityName", entityName);
		uriComponents.put("entityId", entityId);
		uriComponents.put("subcollection", subcollection);
		return uriComponents;
		
		
		
	}
	
	
	
	

}
