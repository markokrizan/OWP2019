package airline.security.model;

import airline.model.User.Role;

public class Payload {
	
	private Integer userId;
	private String userName;
	private String firstName;
	private String lastName;
	private Boolean blocked;
	private Role role;
	
	

	
	public Payload() {
		
	}
	

	
	public Payload(Integer userId, String userName, String firstName, String lastName, Boolean blocked, Role role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.blocked = blocked;
		this.role = role;
	}
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	
	public Boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}



	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "Payload [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", role=" + role + "]";
	}
	
	
	
	
	

}
