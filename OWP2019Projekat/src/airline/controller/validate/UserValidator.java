package airline.controller.validate;

import airline.dto.UserDTO;
import airline.model.User;
import airline.model.User.Role;
import airline.service.UserService;

public class UserValidator {
	
//	private Integer id;
//	private String userName;
//	private String password;
//	private String firstName;
//	private String lastName;
//	private Date registrationDate;
//	private Role role;
//	private Boolean blocked;
	
	public static String validate(UserDTO uDTO){
		String validationMessage = "";
		
		//check nulls or empty:
		if(uDTO.getUserName() == null || uDTO.getUserName().equals("")){
			validationMessage += "Provide user name!\n";
		}
		if(uDTO.getPassword() == null || uDTO.getPassword().equals("")){
			validationMessage += "Provide a password!\n";
		}
		if(uDTO.getFirstName() == null || uDTO.getFirstName().equals("")) {
			validationMessage += "Provide a first name!\n";
		}
		if(uDTO.getLastName() == null || uDTO.getLastName().equals("")) {
			validationMessage += "Provide a last name!\n";
		}
		if(uDTO.getRegistrationDate() == null) {
			validationMessage += "Provide a registration date!\n";
		}
		if(uDTO.getRole() == null) {
			validationMessage += "Provide a valid role!\n";
		}
		if(uDTO.getBlocked() == null) {
			validationMessage += "Provide blocked status!\n";
		}
		

		//=====================================
		
		if(uDTO.getId() != null) {
			validationMessage += "Do not provide an id!\n";
		}
		
		//Entire user object gets pulled - too much
		User existingUser = UserService.findByUserName(uDTO.getUserName());
		if(existingUser != null) {
			validationMessage += "Username already exists!\n";
		}
		
		if(!checkValidRole(uDTO.getRole())) {
			validationMessage += "Provide a valid role!\n";
		}
		
		
		return validationMessage;
	}
	
	public static Boolean checkValidRole(Role sentRole) {
		for(Role role : Role.values()) {
			if(role == sentRole) {
				return true;
			}
		}
		return false;
	}

}
