package airline.service;

public class UserService {
	
	private static UserService single_instance = null;
	
	private UserService() {
		
	}
	
	public static UserService getInstance() {
		if (single_instance == null) {
			single_instance = new UserService();
		}
		return single_instance;
	}
	
	
	

}
