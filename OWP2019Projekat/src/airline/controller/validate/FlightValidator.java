package airline.controller.validate;

import java.util.Date;

import airline.dto.AirportDTO;
import airline.dto.FlightDTO;
import airline.service.AirportService;
import airline.service.FlightService;

public class FlightValidator {

//	private Integer id;
//	private String number;
//	private Date departure;
//	private Date arrival;
//	private AirportDTO flyingFrom;
//	private AirportDTO flyingTo;
//	private Integer numberOfSeats;
//	private Double price;
	

	public static String validateCreate(FlightDTO fDTO){
		
		
		String validationMessage = "";
		
		//check nulls or empty:
		
		if(fDTO.getNumber() == null || fDTO.getNumber().equals("")){
			validationMessage += "Provide flight number!\n";
		}
		if(fDTO.getDeparture() == null){
			validationMessage += "Provide departure date!\n";
		}
		if(fDTO.getArrival() == null){
			validationMessage += "Provide arrival date!\n";
		}
		if(fDTO.getFlyingFrom() == null){
			validationMessage += "Provide departure airport!\n";
		}
		if(fDTO.getFlyingTo() == null){
			validationMessage += "Provide arrival airport!\n";
		}
		if(fDTO.getNumberOfSeats() == null){
			validationMessage += "Provide nubmer of seats!\n";
		}
		if(fDTO.getPrice() == null) {
			validationMessage += "Provide price!\n";
		}
		
	
		//=====================================
		
		if(fDTO.getId() != null) {
			validationMessage += "Do not provide an id!\n";
		}
		
		Boolean numberAvailability = FlightService.checkAvailibleNumber(fDTO.getNumber());
		if(numberAvailability == null) {
			validationMessage += "Error checking number availability!\n";
		}else if(!numberAvailability) {
			validationMessage += "Number not available!\n";
		}
		
		if(fDTO.getDeparture().before(new Date())) {
			validationMessage += "Departure date must be greater than today!\n";
		}
		
		//check if date in right format:
		
		if(fDTO.getArrival().before(fDTO.getDeparture())) {
			validationMessage += "Arrival date must be after departure date!\n";
		}
		
		//check if date in right format:
		
		Boolean validDepartureFlight = AirportService.checkAirportAvailibility(fDTO.getFlyingFrom().getId());
		if(validDepartureFlight == null) {
			validationMessage += "Error checking departure airport availability!\\n";
		}else if(!validDepartureFlight) {
			validationMessage += "Departure airport not available!\n";
		}
		
		Boolean validArrivalAirport = AirportService.checkAirportAvailibility(fDTO.getFlyingTo().getId());
		if(validArrivalAirport == null) {
			validationMessage += "Error checking arrival airport availability!\\n";
		}else if(!validArrivalAirport) {
			validationMessage += "Arrival airport not available!\n";
		}
		
		if(fDTO.getNumberOfSeats() < 0 && fDTO.getNumberOfSeats() > 200) {
			validationMessage += "Number of seats must be between 0 and 200!\n";
		}
		
		if(fDTO.getPrice() < 0 && fDTO.getPrice() > 1000000) {
			validationMessage += "Price must be between 0 and 100.000!\n";
		}
		
		return validationMessage;
	}
	
	public static String validateUpdate(FlightDTO fDTO){
		
		
		String validationMessage = "";
		
		//check nulls or empty:
		
		if(fDTO.getNumber() == null || fDTO.getNumber().equals("")){
			validationMessage += "Provide flight number!\n";
		}
		if(fDTO.getDeparture() == null){
			validationMessage += "Provide departure date!\n";
		}
		if(fDTO.getArrival() == null){
			validationMessage += "Provide arrival date!\n";
		}
		if(fDTO.getFlyingFrom() == null){
			validationMessage += "Provide departure airport!\n";
		}
		if(fDTO.getFlyingTo() == null){
			validationMessage += "Provide arrival airport!\n";
		}
		if(fDTO.getNumberOfSeats() == null){
			validationMessage += "Provide nubmer of seats!\n";
		}
		if(fDTO.getPrice() == null) {
			validationMessage += "Provide price!\n";
		}
		
	
		//=====================================
		
		//check if date in right format:
		
		if(fDTO.getArrival().before(fDTO.getDeparture())) {
			validationMessage += "Arrival date must be after departure date!\n";
		}
		
		//check if date in right format:
		
		Boolean validDepartureFlight = AirportService.checkAirportAvailibility(fDTO.getFlyingFrom().getId());
		if(validDepartureFlight == null) {
			validationMessage += "Error checking departure airport availability!\\n";
		}else if(!validDepartureFlight) {
			validationMessage += "Departure airport not available!\n";
		}
		
		Boolean validArrivalAirport = AirportService.checkAirportAvailibility(fDTO.getFlyingTo().getId());
		if(validArrivalAirport == null) {
			validationMessage += "Error checking arrival airport availability!\\n";
		}else if(!validArrivalAirport) {
			validationMessage += "Arrival airport not available!\n";
		}
		
		if(fDTO.getNumberOfSeats() < 0 && fDTO.getNumberOfSeats() > 200) {
			validationMessage += "Number of seats must be between 0 and 200!\n";
		}
		
		if(fDTO.getPrice() < 0 && fDTO.getPrice() > 1000000) {
			validationMessage += "Price must be between 0 and 100.000!\n";
		}
		
		return validationMessage;
	}
	
}
