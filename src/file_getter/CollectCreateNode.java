package file_getter;
import collect_KOL_infor.*;

import java.io.BufferedWriter;
import java.util.AbstractMap;

import org.openqa.selenium.WebDriver;

public class CollectCreateNode {
	GetInfor infor;
	
	public CollectCreateNode(WebDriver driver) {
		this.infor = new GetInfor(driver);
	}
	
	public void printFile(String name, String url, BufferedWriter bw) {
	    try {
	        bw.write(name);
	        bw.newLine();

	        this.infor.getFollowers(url); // Gọi hàm lấy danh sách follower
	        this.infor.getTweet(url); // Gọi hàm lấy thông tin tweet

	        // Kiểm tra và lấy số lượng followers
	        if (infor.getCheck() != null && infor.getCheck().getFollowers() != null) {
	            String numberOfFollowers = String.valueOf(infor.getCheck().getFollowers().size());
	            bw.write(numberOfFollowers);
	            bw.newLine();

	            for (String follower : infor.getCheck().getFollowers()) {
	                bw.write(follower);
	                bw.newLine();
	            }
	        }

	        // Kiểm tra và lấy số lượng tweets
	        if (infor.getCheck() != null && infor.getCheck().getTweetInfo() != null) {
	            String numberOfTweets = String.valueOf(infor.getCheck().getTweetInfo().size());
	            bw.write(numberOfTweets);
	            bw.newLine();

	            for (AbstractMap.SimpleEntry<String, String> entry : infor.getCheck().getTweetInfo()) {
	                bw.write(entry.getKey() + " " + entry.getValue());
	                bw.newLine();
	            }
	        }

	        bw.newLine();

	    } catch (Exception e) {
	        System.out.println("Lỗi lưu node:");
	        e.printStackTrace(); // In chi tiết lỗi
	    }
	}

	
	
    }
