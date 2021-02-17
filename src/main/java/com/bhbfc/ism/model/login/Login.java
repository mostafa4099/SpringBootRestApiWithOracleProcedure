package com.bhbfc.ism.model.login;

public class Login{
	
    String userId = "";
	String fullName = "";
	String userEmail = "";

	String outCode = "";
	String outMessage = "";
	
	public Login() {
		super();
	}

	public Login(String userId, String fullName, String userEmail) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userEmail = userEmail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getOutCode() {
		return outCode;
	}

	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}

	public String getOutMessage() {
		return outMessage;
	}

	public void setOutMessage(String outMessage) {
		this.outMessage = outMessage;
	}
}
