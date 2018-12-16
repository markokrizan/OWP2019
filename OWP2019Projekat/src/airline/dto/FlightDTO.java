package airline.dto;

import java.util.ArrayList;
import java.util.Date;


import airline.model.Flight;




public class FlightDTO {
	
	private Integer id;
	private String number;
	private Date departure;
	private Date arrival;
	private AirportDTO flyingFrom;
	private AirportDTO flyingTo;
	private Integer numberOfSeats;
	private Double price;
	
	public FlightDTO() {
		
	}
	
	
	
	public FlightDTO(Integer id, String number, Date departure, Date arrival, AirportDTO flyingFrom,
			AirportDTO flyingTo, Integer numberOfSeats, Double price) {
		super();
		this.id = id;
		this.number = number;
		this.departure = departure;
		this.arrival = arrival;
		this.flyingFrom = flyingFrom;
		this.flyingTo = flyingTo;
		this.numberOfSeats = numberOfSeats;
		this.price = price;
	}
	
	public FlightDTO(Flight flight) {
		this(flight.getId(), flight.getNumber(), flight.getDeparture(), flight.getArrival(), new AirportDTO(flight.getFlyingFrom()), new AirportDTO(flight.getFlyingTo()), 
				flight.getNumberOfSeats(), flight.getPrice());
	}
	
	public static ArrayList<FlightDTO> toDTO(ArrayList<Flight> flights){
		if(flights != null) {
			ArrayList<FlightDTO> flightsDTO = new ArrayList<FlightDTO>();
			for(Flight flight : flights) {
				FlightDTO fDTO = new FlightDTO(flight);
				flightsDTO.add(fDTO);
			}
			return flightsDTO;
		}else {
			return null;
		}
		
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getDeparture() {
		return departure;
	}
	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	public AirportDTO getFlyingFrom() {
		return flyingFrom;
	}
	public void setFlyingFrom(AirportDTO flyingFrom) {
		this.flyingFrom = flyingFrom;
	}
	public AirportDTO getFlyingTo() {
		return flyingTo;
	}
	public void setFlyingTo(AirportDTO flyingTo) {
		this.flyingTo = flyingTo;
	}
	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}



	@Override
	public String toString() {
		return "FlightDTO [id=" + id + ", number=" + number + ", departure=" + departure + ", arrival=" + arrival
				+ ", flyingFrom=" + flyingFrom + ", flyingTo=" + flyingTo + ", numberOfSeats=" + numberOfSeats
				+ ", price=" + price + "]";
	}
	
	
	
	

}
