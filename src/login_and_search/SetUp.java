package login_and_search;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.io.File;


public class SetUp {
	private AccountDetails account;
	private String accountInforPath;

	public SetUp(){
		this.account = new AccountDetails();
	}
	
	public void getInfor() {
		try {
			URL url = SetUp.class.getProtectionDomain().getCodeSource().getLocation();
			String decodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8.name());  	
	    	File parentFile = new File(decodedPath);
	    	this.accountInforPath = parentFile.getPath() + "\\resources\\infor.txt";
		} catch(Exception e) {
			System.out.println("Có lỗi xảy ra khi đọc file: " + e.getMessage());
		}
		try (BufferedReader br = new BufferedReader(new FileReader(accountInforPath))) {
            account.setGmail(br.readLine());
            account.setUsername(br.readLine());
            account.setPassword(br.readLine());
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra khi đọc file: " + e.getMessage());
        }
	}

	public AccountDetails getAccount() {
		return account;
	}
	
	
}