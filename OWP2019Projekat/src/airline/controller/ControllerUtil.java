package airline.controller;

import java.net.URI;

public class ControllerUtil {
	
	
	public static Integer userId;
	public static String userErrorMessage;
	
	public static Integer flightId;
	public static String flightErrorMessage;
	
	public enum UserUriMeaning{
		ALL,
		ONE,
		ERROR,
		USER_TICKETS
		
	}
	
	public enum FlightUriMeaning{
		ALL,
		ONE,
		ERROR,
		SEARCH,
		CURRENT,
		OCCUPIED_SEATS,
		RETURNING,
		DEPARTURE_AIRPORT,
		ARRIVAL_AIRPORT
		
	}
	
	
	public static UserUriMeaning checkUserURI(String uri) {
		
		
		
		String[] uriParts = uri.split("/");
		Integer numberOfParts = uriParts.length;

		System.out.println(uri);
	
		Integer userId = null;
		String userCollection = null;
		try {
			userId = Integer.parseInt(uriParts[3]);
			
		}catch(Exception e) {ControllerUtil.userErrorMessage = e.getMessage();}
		try {
			userCollection = uriParts[4];
		}catch(Exception e) {ControllerUtil.userErrorMessage += ", " + e.getMessage();}
		
	
		
		if(userId == null && userCollection == null) {
			return UserUriMeaning.ALL;
		}
		
		if(userId != null && userCollection == null) {
			ControllerUtil.userId = userId;
			return UserUriMeaning.ONE;
		}
		
		if(userId == null && userCollection != null) {
			return UserUriMeaning.ERROR;
		}
		
		if(userId != null && userCollection != null) {
			if(userCollection.equals("tickets")){
				ControllerUtil.userId = userId;
				return UserUriMeaning.USER_TICKETS;
			}
		}
		
		return UserUriMeaning.ERROR;
	
		
		
	}
	
	public static FlightUriMeaning checkFlightURI(String uri) {
			
			
			
		String[] uriParts = uri.split("/");

		System.out.println(uri);
		
		// /search
		Boolean searching = false;
		// /current
		Boolean current = false;
	
		Integer flightId = null;
		String flightCollection = null;
		
		try {
			flightId = Integer.parseInt(uriParts[3]);
		}catch(Exception e) {
			if(uriParts.length > 3) {
				if(uriParts[3].contentEquals("search")) {
					searching = true;
				
				}else if(uriParts[3].contentEquals("current")) {
					current = true;
				}else {
					ControllerUtil.flightErrorMessage = e.getMessage();
					
				}
			}else {
				ControllerUtil.flightErrorMessage = e.getMessage();
			}
			
		}
		try {
			flightCollection = uriParts[4];
		}catch(Exception e) {ControllerUtil.flightErrorMessage += ", " + e.getMessage();}
		
	
		
		if(flightId == null && flightCollection == null) {
			if(searching == true) {
				return FlightUriMeaning.SEARCH;
			}else if(current == true) {
				return FlightUriMeaning.CURRENT;
			}
			else {
				return FlightUriMeaning.ALL;
			}
			
		}
		
		if(flightId != null && flightCollection == null) {
			ControllerUtil.flightId = flightId;
			return FlightUriMeaning.ONE;
		}
		
		if(flightId == null && flightCollection != null) {
			return FlightUriMeaning.ERROR;
		}
		
		if(flightId != null && flightCollection != null) {
			if(flightCollection.equals("departureAirport")){
				ControllerUtil.flightId = flightId;
				return FlightUriMeaning.DEPARTURE_AIRPORT;
			}else if(flightCollection.equals("arrivalAirport")) {
				ControllerUtil.flightId = flightId;
				return FlightUriMeaning.ARRIVAL_AIRPORT;
			}else if(flightCollection.equals("returning")) {
				ControllerUtil.flightId = flightId;
				return FlightUriMeaning.RETURNING;
			}else if(flightCollection.equals("occupied-seats")) {
				ControllerUtil.flightId = flightId;
				return FlightUriMeaning.OCCUPIED_SEATS;
			}else {
				return FlightUriMeaning.ERROR;
			}
		}
		
		
		
		return FlightUriMeaning.ERROR;
		
			
			
	}
	
	

}
