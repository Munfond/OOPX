package login;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class SetUp {
	private String gmail;
	private String username;
	private String password;
	
	public void getInfor() {
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Duc Anh\\eclipse-workspace\\OOPX\\src\\resources\\infor"))) {
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
