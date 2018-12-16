package airline.model;

import java.util.Date;

import airline.dto.UserDTO;

public class User {
	
	public enum Role{
		REGULAR,
		ADMIN
	}
	
	private Integer id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private Date registrationDate;
	private Role role;
	private Boolean blocked;
	private Boolean deleted;
	
	public User() {
		
	}
	
	
	public User(Integer id, String userName, String password, String firstName, String lastName, Date registrationDate, Role role, Boolean blocked, Boolean deleted) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationDate = registrationDate;
		this.role = role;
		this.blocked = blocked;
		this.deleted = deleted;
	}
	
	public static User userFromDTO(UserDTO userDTO) {
		User newUser = new User();
		newUser.setId(userDTO.getId());
		newUser.setUserName(userDTO.getUserName());
		newUser.setPassword(userDTO.getPassword());
		newUser.setFirstName(userDTO.getFirstName());
		newUser.setLastName(userDTO.getLastName());
		newUser.setRegistrationDate(userDTO.getRegistrationDate());
		newUser.setRole(userDTO.getRole());
		newUser.setBlocked(userDTO.getBlocked());
		newUser.setDeleted(false);
		
		return newUser;
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


	public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", registrationDate=" + registrationDate + ", role=" + role + ", blocked="
				+ blocked + ", deleted=" + deleted + "]";
	}
	
	
	
	
	
	
	
	
	

}
