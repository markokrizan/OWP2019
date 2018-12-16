package airline.dto;

import java.util.ArrayList;
import java.util.Date;

import airline.model.Flight;
import airline.model.Ticket;
import airline.model.User;

public class TicketDTO {
	
	private Integer id;
	private FlightDTO departureFlight;
	private FlightDTO arrivalFlight;
	private Integer departureFlightSeatNumber;
	private Integer arrivalFlightSeatNumber;
	//If this filed is filled this is a reservation
	private Date reservationDate;
	//if this field is filled also reservation becomes a sale
	private Date ticketSaleDate;
	private UserDTO user;
	
	
	public TicketDTO() {
		
	}
	
	
	public TicketDTO(Integer id, FlightDTO departureFlight, FlightDTO arrivalFlight, Integer departureFlightSeatNumber,
			Integer arrivalFlightSeatNumber, Date reservationDate, Date ticketSaleDate, UserDTO user) {
		super();
		this.id = id;
		this.departureFlight = departureFlight;
		this.arrivalFlight = arrivalFlight;
		this.departureFlightSeatNumber = departureFlightSeatNumber;
		this.arrivalFlightSeatNumber = arrivalFlightSeatNumber;
		this.reservationDate = reservationDate;
		this.ticketSaleDate = ticketSaleDate;
		this.user = user;
	}
	
	public TicketDTO(Ticket ticket) {
		this(ticket.getId(), FlightDTO.FlightDTOFactory(ticket.getDepartureFlight()), FlightDTO.FlightDTOFactory(ticket.getArrivalFlight()), ticket.getDepartureFlightSeatNumber(), ticket.getArrivalFlightSeatNumber(),
				ticket.getReservationDate(), ticket.getTicketSaleDate(), new UserDTO(ticket.getUser()));
	}
	
	public static ArrayList<TicketDTO> toDTO(ArrayList<Ticket> tickets){
		if(tickets != null) {
			ArrayList<TicketDTO> ticketsDTO = new ArrayList<TicketDTO>();
			for(Ticket ticket : tickets) {
				TicketDTO tDTO = new TicketDTO(ticket);
				ticketsDTO.add(tDTO);
			}
			return ticketsDTO;
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
	public FlightDTO getDepartureFlight() {
		return departureFlight;
	}
	public void setDepartureFlight(FlightDTO departureFlight) {
		this.departureFlight = departureFlight;
	}
	public FlightDTO getArrivalFlight() {
		return arrivalFlight;
	}
	public void setArrivalFlight(FlightDTO arrivalFlight) {
		this.arrivalFlight = arrivalFlight;
	}
	public Integer getDepartureFlightSeatNumber() {
		return departureFlightSeatNumber;
	}
	public void setDepartureFlightSeatNumber(Integer departureFlightSeatNumber) {
		this.departureFlightSeatNumber = departureFlightSeatNumber;
	}
	public Integer getArrivalFlightSeatNumber() {
		return arrivalFlightSeatNumber;
	}
	public void setArrivalFlightSeatNumber(Integer arrivalFlightSeatNumber) {
		this.arrivalFlightSeatNumber = arrivalFlightSeatNumber;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Date getTicketSaleDate() {
		return ticketSaleDate;
	}
	public void setTicketSaleDate(Date ticketSaleDate) {
		this.ticketSaleDate = ticketSaleDate;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "TicketDTO [id=" + id + ", departureFlight=" + departureFlight + ", arrivalFlight=" + arrivalFlight
				+ ", departureFlightSeatNumber=" + departureFlightSeatNumber + ", arrivalFlightSeatNumber="
				+ arrivalFlightSeatNumber + ", reservationDate=" + reservationDate + ", ticketSaleDate="
				+ ticketSaleDate + ", user=" + user + "]";
	}
	
	
	

}
