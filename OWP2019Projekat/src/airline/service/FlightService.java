package airline.service;

public class FlightService {
	
	private static FlightService single_instance = null;
	
	private FlightService() {
		
	}
	
	public static FlightService getInstance() {
		if (single_instance == null) {
			single_instance = new FlightService();
		}
		return single_instance;
	}
	
	

}
