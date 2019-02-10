package airline.dto;

public class ReportDTO {
	
	private String airportName;
	private Integer numberOfFlights;
	private Integer numberOfTickets;
	private Double totalRevenue;
	
	public ReportDTO() {
		
	}
	
	public ReportDTO(String airportName, Integer numberOfFlights, Integer numberOfTickets, Double totalRevenue) {
		super();
		this.airportName = airportName;
		this.numberOfFlights = numberOfFlights;
		this.numberOfTickets = numberOfTickets;
		this.totalRevenue = totalRevenue;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public Integer getNumberOfFlights() {
		return numberOfFlights;
	}

	public void setNumberOfFlights(Integer numberOfFlights) {
		this.numberOfFlights = numberOfFlights;
	}

	public Integer getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(Integer numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	
	
	

}
