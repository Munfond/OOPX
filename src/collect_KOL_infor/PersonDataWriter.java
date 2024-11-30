package collect_KOL_infor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;

import org.openqa.selenium.WebDriver;

public class PersonDataWriter {
	private GetInfor infor;
	private String logInfor = "";
	
	public PersonDataWriter(WebDriver driver) {
		this.infor = new GetInfor(driver);
	}
	
	public void printFile(String url, String filepath, FileWriter writer) {
			this.infor.getFollowers(url); // Gọi hàm lấy danh sách follower
	        this.infor.getTweet(url); // Gọi hàm lấy thông tin tweet
			String username = url.substring(url.lastIndexOf("/") + 1);


			this.logInfor += url;
			this.logInfor += ",";
			this.logInfor += username;
			this.logInfor += ",";

			this.logInfor += String.valueOf(infor.getCheck().getFollowers().size());
			this.logInfor += ",";
			for(String auto : infor.getCheck().getFollowers()) {
				this.logInfor += auto;
				this.logInfor += " ";
			}

			this.logInfor += ",";

			this.logInfor += String.valueOf(this.infor.getCheck().getTweetInfo().size());
			this.logInfor += ",";
			for(AbstractMap.SimpleEntry<String, String> entry : infor.getCheck().getTweetInfo()) {
				this.logInfor += entry.getKey();
				this.logInfor += " ";
				this.logInfor += entry.getValue();
				this.logInfor += " ";
			}

			try {
				writer.append(logInfor + "\n");
			} catch(IOException e) {
				e.printStackTrace();
			}
    }

	public GetInfor getInfor() {
		return infor;
	}

	public void setInfor(GetInfor infor) {
		this.infor = infor;
	}

	public String getLogInfor() {
		return logInfor;
	}

	public void setLogInfor(String logInfor) {
		this.logInfor = logInfor;
	}

	
}
