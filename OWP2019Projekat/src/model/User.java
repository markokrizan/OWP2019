package model;

import java.util.Date;

public class User {
	
	private enum Role{
		REGULAR,
		ADMIN
	}
	
	private Integer id;
	private String userName;
	private String password;
	private Date registrationDate;
	private Role role;
	private Boolean blocked;
	
	
	public User(Integer id, String userName, String password, Date registrationDate, Role role, Boolean blocked) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.registrationDate = registrationDate;
		this.role = role;
		this.blocked = blocked;
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
	
	
	
	
	

}
