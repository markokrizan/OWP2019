package airline.controller.validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import airline.dto.UserDTO;
import airline.model.User;
import airline.model.User.Role;
import airline.security.AuthUtil;
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
	
	public static String validateCreate(UserDTO uDTO){
		String validationMessage = "";
		
		if(uDTO.getId() != null) {
			validationMessage += "Do not provide an id!\n";
		}
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
		if(uDTO.getRole() == null || !checkValidRole(uDTO.getRole())) {
			validationMessage += "Provide a valid role!\n";
		}
		if(uDTO.getBlocked() == null || !(uDTO.getBlocked() instanceof Boolean)) {
			validationMessage += "Provide a valid blocked status!\n";
		}
		

		//=====================================
		
		//Entire user object gets pulled - too much
		User existingUser = UserService.findByUserName(uDTO.getUserName());
		if(existingUser != null) {
			validationMessage += "Username already exists!\n";
		}
		
		
		return validationMessage;
	}
	
	public static ArrayList<Object> validateUsersUpdate (UserDTO uDTO){
		String validationMessage = "";
		
		//check nulls or empty:
		if(uDTO.getId() == null) {
			validationMessage += "Provide your user id!\n";
		}
		if(uDTO.getUserName() == null || uDTO.getUserName().equals("")){
			validationMessage += "Provide user name!\n";
		}
		if(uDTO.getFirstName() == null || uDTO.getFirstName().equals("")) {
			validationMessage += "Provide a first name!\n";
		}
		if(uDTO.getLastName() == null || uDTO.getLastName().equals("")) {
			validationMessage += "Provide a last name!\n";
		}
		if(uDTO.getRole() != null) {
			validationMessage += "You cannot change your own role!\n";
		}
		if(uDTO.getBlocked() != null) {
			validationMessage += "You cannot change your own blocked status!\n";
		}
		
		//=====================================
		User existingUser = UserService.getOne(uDTO.getId());
		if(existingUser == null) {
			validationMessage += "User non existant, blocked or deleted!\n";
		}
		
		ArrayList<Object> validation = new ArrayList<Object>();
		validation.add(validationMessage);
		
		//database accessed already - fill rest of data:
		if(existingUser != null && validationMessage.equals("")) {
			if(uDTO.getPassword() == null || uDTO.getPassword().equals("")){
				uDTO.setPassword(existingUser.getPassword());
			}else {
				//password wasnt null - change requested:
				uDTO.setPassword(AuthUtil.hash(uDTO.getPassword(), "MD5"));
			}
			uDTO.setRegistrationDate(existingUser.getRegistrationDate());
			uDTO.setRole(existingUser.getRole());
			uDTO.setBlocked(existingUser.getBlocked());
			validation.add(uDTO);
			return validation;
		}else {
			validation.add(null);
			return validation;
		}
		
		
		
	}
	
	public static ArrayList<Object> validateAdminsUpdate (UserDTO uDTO){
		String validationMessage = "";
		
		//check nulls or empty:
		if(uDTO.getId() == null) {
			validationMessage += "Provide your user id!\n";
		}
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
		if(uDTO.getRole() == null || checkValidRole(uDTO.getRole()) == false) {
			validationMessage += "Provide a valid role!\n";
		}
		if(uDTO.getBlocked() == null || !(uDTO.getBlocked() instanceof Boolean)) {
			validationMessage += "Provide a valid blocked state!\n";
		}
		
		//=====================================
		User existingUser = UserService.getOne(uDTO.getId());
		if(existingUser == null) {
			validationMessage += "User non existant, blocked or deleted!\n";
		}
		
		ArrayList<Object> validation = new ArrayList<Object>();
		validation.add(validationMessage);
		
		//database accessed already - fill rest of data:
		if(existingUser != null && validationMessage.equals("")) {
			if(uDTO.getPassword() == null || uDTO.getPassword().equals("")){
				uDTO.setPassword(existingUser.getPassword());
			}else {
				uDTO.setPassword(AuthUtil.hash(uDTO.getPassword(), "MD5"));
			}
			uDTO.setRegistrationDate(existingUser.getRegistrationDate());
			validation.add(uDTO);
			return validation;
		}else {
			validation.add(uDTO);
			return validation;
		}
		

		
		
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
