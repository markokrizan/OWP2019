package airline.dto;

import java.util.ArrayList;
import java.util.Date;

import airline.model.Ticket;
import airline.model.User;
import airline.model.User.Role;

public class UserDTO {
	
	private Integer id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private Date registrationDate;
	private Role role;
	private Boolean blocked;
	
	
	
	public UserDTO() {
		
	}
	
	

	public UserDTO(Integer id, String userName, String password, String firstName, String lastName,
			Date registrationDate, Role role, Boolean blocked) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationDate = registrationDate;
		this.role = role;
		this.blocked = blocked;
	}
	
	public UserDTO(User user) {
		this(user.getId(), user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getRegistrationDate(), user.getRole(), user.getBlocked());
	}
	
	public static ArrayList<UserDTO> toDTO(ArrayList<User> users){
		if(users != null) {
			ArrayList<UserDTO> usersDTO = new ArrayList<UserDTO>();
			for(User user : users) {
				UserDTO uDTO = new UserDTO(user);
				usersDTO.add(uDTO);
			}
			return usersDTO;
		}else {
			return null;
		}
		
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Boolean getBlocked() {
		return blocked;
	}
	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}



	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", registrationDate=" + registrationDate + ", role=" + role + ", blocked="
				+ blocked + "]";
	}
	
	

}
