package airline.dto;

import java.util.Arrays;

public class SearchFlightDTO {
	
	private String queryText;
	private Integer[] departureAirports;
	private Integer[] arrivalAirports;
	private String lowestPrice;
	private String highestPrice;
	private String dateLowDeparture;
	private String dateHighDeparture;
	private String dateLowArrival;
	private String dateHighArrival;
	
	
	public SearchFlightDTO() {
		
	}


	

	public String getQueryText() {
		return queryText;
	}




	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}




	public Integer[] getDepartureAirports() {
		return departureAirports;
	}




	public void setDepartureAirports(Integer[] departureAirports) {
		this.departureAirports = departureAirports;
	}




	public Integer[] getArrivalAirports() {
		return arrivalAirports;
	}




	public void setArrivalAirports(Integer[] arrivalAirports) {
		this.arrivalAirports = arrivalAirports;
	}




	public String getLowestPrice() {
		return lowestPrice;
	}




	public void setLowestPrice(String lowestPrice) {
		this.lowestPrice = lowestPrice;
	}




	public String getHighestPrice() {
		return highestPrice;
	}




	public void setHighestPrice(String highestPrice) {
		this.highestPrice = highestPrice;
	}




	public String getDateLowDeparture() {
		return dateLowDeparture;
	}




	public void setDateLowDeparture(String dateLowDeparture) {
		this.dateLowDeparture = dateLowDeparture;
	}




	public String getDateHighDeparture() {
		return dateHighDeparture;
	}




	public void setDateHighDeparture(String dateHighDeparture) {
		this.dateHighDeparture = dateHighDeparture;
	}




	public String getDateLowArrival() {
		return dateLowArrival;
	}




	public void setDateLowArrival(String dateLowArrival) {
		this.dateLowArrival = dateLowArrival;
	}




	public String getDateHighArrival() {
		return dateHighArrival;
	}




	public void setDateHighArrival(String dateHighArrival) {
		this.dateHighArrival = dateHighArrival;
	}




	@Override
	public String toString() {
		return "SearchFlightDTO [queryText=" + queryText + ", departureAirports=" + Arrays.toString(departureAirports)
				+ ", arrivalAirports=" + Arrays.toString(arrivalAirports) + ", lowestPrice=" + lowestPrice
				+ ", highestPrice=" + highestPrice + ", dateLowDeparture=" + dateLowDeparture + ", dateHighDeparture="
				+ dateHighDeparture + ", dateLowArrival=" + dateLowArrival + ", dateHighArrival=" + dateHighArrival
				+ "]";
	}




	



	
	




	
	
	

}
