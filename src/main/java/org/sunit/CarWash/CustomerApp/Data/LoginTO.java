package org.sunit.CarWash.CustomerApp.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Login")
public class LoginTO{
	
	@Id 
	@Column (name = "LOGIN_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int loginId;
	
	@Column (name = "LOGIN_USERNAME")
	@NotNull
    @Size(min=2, max=30)
	private String username;
	
	@Column (name = "LOGIN_NAME")
	@NotNull
	private String name;
	
	@Column (name = "LOGIN_CONTACTNO")
	@NotNull
	private String contactNo;
	
	
	@Column (name = "LOGIN_PASSWORD")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public int getLoginId() {
		return loginId;
	}

	
	

}
