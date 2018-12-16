package airline.model;

import java.util.Date;

import airline.dto.AirportDTO;
import airline.dto.TicketDTO;

public class Ticket {

	
	private Integer id;
	private Flight departureFlight;
	private Flight arrivalFlight;
	private Integer departureFlightSeatNumber;
	private Integer arrivalFlightSeatNumber;
	//If this filed is filled this is a reservation
	private Date reservationDate;
	//if this field is filled also reservation becomes a sale
	private Date ticketSaleDate;
	private User user;
	private Boolean deleted;
	
	public Ticket() {
		
	
	}

	public Ticket(Integer id, Flight departureFlight, Flight arrivalFlight, Integer departureFlightSeatNumber,
			Integer arrivalFlightSeatNumber, Date reservationDate, Date ticketSaleDate, User user, Boolean deleted) {
		super();
		this.id = id;
		this.departureFlight = departureFlight;
		this.arrivalFlight = arrivalFlight;
		this.departureFlightSeatNumber = departureFlightSeatNumber;
		this.arrivalFlightSeatNumber = arrivalFlightSeatNumber;
		this.reservationDate = reservationDate;
		this.ticketSaleDate = ticketSaleDate;
		this.user = user;
		this.deleted = deleted;
	}
	
	public static Ticket ticketFromDTO(TicketDTO ticketDTO) {
		Ticket newTicket = new Ticket();
		newTicket.setId(ticketDTO.getId());
		newTicket.setDepartureFlight(Flight.flightFromDTO(ticketDTO.getDepartureFlight()));
		newTicket.setArrivalFlight(Flight.flightFromDTO(ticketDTO.getArrivalFlight()));
		newTicket.setDepartureFlightSeatNumber(ticketDTO.getDepartureFlightSeatNumber());
		newTicket.setArrivalFlightSeatNumber(ticketDTO.getArrivalFlightSeatNumber());
		newTicket.setReservationDate(ticketDTO.getReservationDate());
		newTicket.setTicketSaleDate(ticketDTO.getTicketSaleDate());
		newTicket.setUser(User.userFromDTO(ticketDTO.getUser()));
		
		newTicket.setDeleted(false);
		return newTicket;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Flight getDepartureFlight() {
		return departureFlight;
	}

	public void setDepartureFlight(Flight departureFlight) {
		this.departureFlight = departureFlight;
	}

	public Flight getArrivalFlight() {
		return arrivalFlight;
	}

	public void setArrivalFlight(Flight arrivalFlight) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", departureFlight=" + departureFlight + ", arrivalFlight=" + arrivalFlight
				+ ", departureFlightSeatNumber=" + departureFlightSeatNumber + ", arrivalFlightSeatNumber="
				+ arrivalFlightSeatNumber + ", reservationDate=" + reservationDate + ", ticketSaleDate="
				+ ticketSaleDate + ", user=" + user + ", deleted=" + deleted + "]\n";
	}
	
	
	
	
	
	
	
}
