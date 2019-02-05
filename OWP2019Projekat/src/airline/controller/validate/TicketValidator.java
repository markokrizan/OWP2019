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
	
//	public static String validate(TicketDTO tDTO){
//		String validationMessage = "";
//		
//		if(tDTO.getId() != null) {
//			validationMessage += "Do not provide an id!\n";
//		}
//		
//		Boolean validDepartureFlight = TicketService.checkDepartureFlight(tDTO.getDepartureFlight());
//		if(validDepartureFlight == null) {
//			validationMessage += "Error checking departure flight availability!\\n";
//		}else if(!validDepartureFlight) {
//			validationMessage += "Departure flight not available!\n";
//		}
//		
//		Boolean validArrivalFlight = TicketService.checkArrivalFlight(tDTO.getArrivalFlight());
//		if(validArrivalFlight == null) {
//			validationMessage += "Error checking arrival flight availability!\\n";
//		}else if(!validArrivalFlight) {
//			validationMessage += "Arrival flight not available!\n";
//		}
//		
//		Boolean validDepartureFlightSeatNumber = TicketService.checkDepartureFlightSeat(tDTO.getDepartureFlightSeatNumber());
//		if(validDepartureFlightSeatNumber == null) {
//			validationMessage += "Error checking departure seat availability!\\n";
//		}else if(!validDepartureFlightSeatNumber) {
//			validationMessage += "Departure seat not available!\n";
//		}
//		
//		Boolean validArrivalFlightSeatNumber = TicketService.checkArrivalFlightSeat(tDTO.getDepartureFlightSeatNumber());
//		if(validArrivalFlightSeatNumber == null) {
//			validationMessage += "Error checking arrival seat availability!\\n";
//		}else if(!validArrivalFlightSeatNumber) {
//			validationMessage += "Arrival arrival not available!\n";
//		}
//		
//
//		if(tDTO.getReservationDate().after(tDTO.getDepartureFlight().getDeparture())) {
//			validationMessage += "Reservation date must be before departure date!\\n";
//		}
//		
//		if(tDTO.getTicketSaleDate().before(tDTO.getReservationDate())) {
//			validationMessage += "Sale date must be after reservation date!\n";
//		}
//		
//		
//		User user = UserService.getOne(tDTO.getUser().getId());
//		if(user == null) {
//			validationMessage += "Unknown or deleted user!\n";
//		}
//		
//		
//		return validationMessage;
//	}

}
