package collect_KOL_infor;

//import java.util.List;
import java.util.Map;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class GetInfor {
	final static int MAX_TWEETS = 20;
	InforOfKOL check;
	WebDriver driver;
	
	public GetInfor(WebDriver driver) {
		this.driver = driver;
		this.check = new InforOfKOL();
	}
	
	
	
	public void getFollowers(String url) {
		CollectNameAndUrl collect = new CollectNameAndUrl();
		Map<String,String> followersUrl = new HashMap<>();
		this.driver.get(url + "/followers");
	    followersUrl = collect.collectKOLData(this.driver);
	    for(Map.Entry<String,String> entry: followersUrl.entrySet()) {
	    	this.check.followers.add(entry.getValue());
	    }
		
	}

	
	public void getTweet(String url) {
		this.driver.get(url);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        int scrollCount = 0;

        try {
            while (this.check.tweetInfo.size() < 20 && scrollCount < 10) {
                List<WebElement> tweets = driver.findElements(By.cssSelector("article"));

                for (WebElement tweet : tweets) {
                    try {
                        // Tìm phần tử chứa URL bài tweet
                        WebElement ownerElement = tweet.findElement(By.xpath(".//a[contains(@href, '/status/')]"));
                        String tweetUrl = ownerElement.getAttribute("href");

                        // Lấy tên chủ bài viết
                        WebElement userElement = tweet.findElement(By.xpath(".//div[@data-testid='User-Name']//span"));
                        String ownerName = userElement.getAttribute("href");

                        // Thêm vào Set nếu chưa có
                        AbstractMap.SimpleEntry<String, String> entry = new AbstractMap.SimpleEntry<>(ownerName, tweetUrl);
                        this.check.tweetInfo.add(entry);

                        if (this.check.tweetInfo.size() >= 20) {
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Error parsing a tweet: " + e.getMessage());
                    }
                }

                // Cuộn trang xuống để tải thêm nội dung
                js.executeScript("window.scrollBy(0, 1000);");
                Thread.sleep(3000); // Chờ nội dung tải thêm
                scrollCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
	}
	
	public InforOfKOL getCheck() {
		return this.check;
	}
	
}
