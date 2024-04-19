
package com.numetry.regform.dto;


public class LoginRequest {
	
    private String username;
    private String password;
    private String userType;
    

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
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public LoginRequest(String username, String password, String userType) {
		super();
		this.username = username;
		this.password = password;
		this.userType=userType;
	}
	//
	public LoginRequest() {
		super();
	}
	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", password=" + password + ", userType=" + userType + "]";
	}
	
    
}
