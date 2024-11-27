package login_and_search;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.io.File;


public class SetUp {
	private String gmail;
	private String username;
	private String password;
	private String accountInforPath;
	
	public void getInfor() {
		try {
			URL url = SetUp.class.getProtectionDomain().getCodeSource().getLocation();
			String decodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8.name());  	
	    	File parentFile = new File(decodedPath);
	    	this.accountInforPath = parentFile.getPath() + "\\resources\\infor";
		} catch(Exception e) {
			System.out.println("Có lỗi xảy ra khi đọc file: " + e.getMessage());
		}
		try (BufferedReader br = new BufferedReader(new FileReader(accountInforPath))) {
            this.gmail = br.readLine();  
            this.username = br.readLine();
            this.password = br.readLine();  
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra khi đọc file: " + e.getMessage());
        }
	}

	public String getGmail() {
		return this.gmail;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}
	
}