package airline.service;

public class AirportService {
	
	private static AirportService single_instance = null;
	
	private AirportService() {
		
	}
	
	public static AirportService getInstance() {
		if (single_instance == null) {
			single_instance = new AirportService();
		}
		return single_instance;
	}

}
