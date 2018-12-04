package airline.service;

public class TicketService {
	
	private static TicketService single_instance = null;
	
	private TicketService() {
		
	}
	
	public static TicketService getInstance() {
		if (single_instance == null) {
			single_instance = new TicketService();
		}
		return single_instance;
	}

}
