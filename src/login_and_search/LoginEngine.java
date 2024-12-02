package login_and_search;

public class LoginEngine {
	private TwitterLogin login;
	private SetUp setUp;
	
	public LoginEngine() {
		this.login = new TwitterLogin();
		this.setUp = new SetUp();
	}
	
	public void init() {
		setUp.getInfor();
		login.setAccount(setUp.getAccount());
		
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