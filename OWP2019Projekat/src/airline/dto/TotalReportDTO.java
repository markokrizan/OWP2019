package airline.dto;

public class TotalReportDTO {
	
	private Integer totalFlights;
	private Integer totalTickets;
	private Double totalRevenue;
	
	public TotalReportDTO() {
		
	}

	public TotalReportDTO(Integer totalFlights, Integer totalTickets, Double totalRevenue) {
		super();
		this.totalFlights = totalFlights;
		this.totalTickets = totalTickets;
		this.totalRevenue = totalRevenue;
	}

	public Integer getTotalFlights() {
		return totalFlights;
	}

	public void setTotalFlights(Integer totalFlights) {
		this.totalFlights = totalFlights;
	}

	public Integer getTotalTickets() {
		return totalTickets;
	}

	public void setTotalTickets(Integer totalTickets) {
		this.totalTickets = totalTickets;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	
	

}
