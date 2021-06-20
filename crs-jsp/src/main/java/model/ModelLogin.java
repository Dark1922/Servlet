package model;

import java.io.Serializable;

public class ModelLogin implements Serializable  {
	
	/* parte de compilação das classes implements e uid abaixo*/ 
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String password;
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}

}
