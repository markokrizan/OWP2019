package airline.controller.validate;

import java.util.Date;

import airline.dto.TicketDTO;
import airline.model.User;
import airline.service.TicketService;
import airline.service.UserService;

public class TicketValidator {
	
//	private Integer id;
//	private FlightDTO departureFlight;
//	private FlightDTO arrivalFlight;
//	private Integer departureFlightSeatNumber;
//	private Integer arrivalFlightSeatNumber;
//	//If this filed is filled this is a reservation
//	private Date reservationDate;
//	//if this field is filled also reservation becomes a sale
//	private Date ticketSaleDate;
//	private UserDTO user;
	
	public static String validateCreate(TicketDTO tDTO){
		String validationMessage = "";
		
		//check nulls or empty:
		
		
		if(tDTO.getDepartureFlight() == null){
			validationMessage += "Provide departure flight!\n";
		}
		if(tDTO.getDepartureFlightSeatNumber() == null){
			validationMessage += "Provide departure flight seat!\n";
		}
		
		if(tDTO.getReservationDate() == null){
			validationMessage += "Provide reservation date!\n";
		}
		
		
		
		//=====================================
		
		
		
		if(tDTO.getId() != null) {
			validationMessage += "Do not provide an id!\n";
		}
		
		
		
		//if it exists
		Boolean validDepartureFlight = TicketService.checkFLight(tDTO.getDepartureFlight().getId());
		if(validDepartureFlight == null) {
			validationMessage += "Error checking departure flight availability!\\n";
		}else if(!validDepartureFlight) {
			validationMessage += "Departure flight not available!\n";
		}
		
		if(tDTO.getArrivalFlight() != null){
			//if exists plus not the same as departure
			Boolean validArrivalFlight = TicketService.checkFLight(tDTO.getDepartureFlight().getId());
			if(validArrivalFlight == null) {
				validationMessage += "Error checking arrival flight availability!\\n";
			}else if(tDTO.getDepartureFlight().getId() == tDTO.getArrivalFlight().getId()) {
				validationMessage += "Arrival flight must be different than departure!\\n";
			}else if(!validArrivalFlight) {
				validationMessage += "Arrival flight not available\n";
			}
			
			if(tDTO.getArrivalFlightSeatNumber() == null){
				validationMessage += "Provide arrival flight seat!\n";
			}else {
				Boolean validArrivalFlightSeatNumber = TicketService.checkSeatAvailibility(tDTO.getDepartureFlight().getId(), tDTO.getDepartureFlightSeatNumber());
				if(validArrivalFlightSeatNumber == null) {
					validationMessage += "Error checking arrival seat availability!\\n";
				}else if(!validArrivalFlightSeatNumber) {
					validationMessage += "Arrival arrival not available!\n";
				}
			}
			
			
		}
		
		
		
		
		
		Boolean validDepartureFlightSeatNumber = TicketService.checkSeatAvailibility(tDTO.getDepartureFlight().getId(), tDTO.getDepartureFlightSeatNumber());
		if(validDepartureFlightSeatNumber == null) {
			validationMessage += "Error checking departure seat availability!\\n";
		}else if(!validDepartureFlightSeatNumber) {
			validationMessage += "Departure seat not available!\n";
		}
		
		
		

		if(tDTO.getReservationDate().after(tDTO.getDepartureFlight().getDeparture())) {
			validationMessage += "Reservation date must be before departure date!\\n";
		}
		
		if(tDTO.getTicketSaleDate() != null) {
			if(tDTO.getTicketSaleDate().before(tDTO.getReservationDate())) {
				validationMessage += "Sale date must be after reservation date!\n";
			}
		}
		
		
		
		User user = UserService.getOne(tDTO.getUser().getId());
		if(user == null) {
			validationMessage += "Unknown or deleted user!\n";
		}
		
		
		return validationMessage;
	}
	
	public static String validateUpdate(TicketDTO tDTO){
		String validationMessage = "";
		
		//check nulls or empty:
		

		if(tDTO.getId() == null) {
			validationMessage += "Provide an id!\n";
		}
		if(tDTO.getDepartureFlight() == null){
			validationMessage += "Provide departure flight!\n";
		}
		
		if(tDTO.getDepartureFlightSeatNumber() == null){
			validationMessage += "Provide departure flight seat!\n";
		}
		
		if(tDTO.getReservationDate() == null){
			validationMessage += "Provide reservation date!\n";
		}
		
		
		//=====================================
		
		
		if(tDTO.getArrivalFlight() != null){
			//check?
			
			if(tDTO.getArrivalFlightSeatNumber() == null){
				//check?
			}
		}
		
		//seats?
		
		
		if(tDTO.getTicketSaleDate() != null) {
			if(tDTO.getTicketSaleDate().before(tDTO.getReservationDate())) {
				validationMessage += "Sale date must be after reservation date!\n";
			}
		}
				
		
		User user = UserService.getOne(tDTO.getUser().getId());
		if(user == null) {
			validationMessage += "Unknown or deleted user!\n";
		}
		
		
		return validationMessage;
	}

}
