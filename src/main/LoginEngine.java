package main;

import login.*;
public class LoginEngine {
	public TwitterLogin login;
	public SetUp setUp;
	
	public LoginEngine() {
		this.login = new TwitterLogin();
		this.setUp = new SetUp();
	}
	
	public void init() {
		setUp.getInfor();
		login.account.setGmail(setUp.getGmail());
		login.account.setUsername(setUp.getUsername());
		login.account.setPassword(setUp.getPassword());
		
		login.getToX();
		login.fillGmail();
		login.fillUserName();
		login.fillPassword();
	}

	public TwitterLogin getLogin() {
		return login;
	}

	public void setLogin(TwitterLogin login) {
		this.login = login;
	}

	public SetUp getSetUp() {
		return setUp;
	}

	public void setSetUp(SetUp setUp) {
		this.setUp = setUp;
	}
	
	
}
