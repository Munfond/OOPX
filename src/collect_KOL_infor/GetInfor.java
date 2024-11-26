package collect_KOL_infor;

import java.util.Map;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class GetInfor {
	private int MAX_TWEETS;
    private int MAX_FOLLOWERS;
	InforOfKOL check;
	WebDriver driver;
	
	public GetInfor(WebDriver driver) {
		this.driver = driver;
		this.check = new InforOfKOL();
	}
	
	public int  getNumberOfFollowers(String url) {
		this.driver.get(url);
		
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement element = driver.findElement(By.xpath("//a[contains(@href, '/verified_followers')]"));
        String result = element.getText();

		int followers = 0;
        int i = 0;
        while(result.charAt(i) != ' ') {
        	i++;
        }
        if(Character.isDigit(result.charAt(i - 1))) {
        	for(int k = 0; k <= i-1; k++) {
        		if(Character.isDigit(result.charAt(k))) {
        			followers = followers * 10 + (result.charAt(k) - 48);
        		}
        	}
        }
        else if(result.charAt(i-1) == 'K') {
            int position = 0;
            int cnt = 0;
            for(int k = 0; k <= i-2; k++) {
        		if(Character.isDigit(result.charAt(k))) {
        			followers = followers * 10 + (result.charAt(k) - 48);
                    cnt++;
        		}
                else {
                    position = k;
                }
        	}
            if (position != 0) {
                followers = followers * (int) (Math.pow(10, 3 - (cnt - position)));
            }
            else {
                followers = followers * (int) (Math.pow(10, 3));
            }
        }
        else if(result.charAt(i-1) == 'M') {
            int position = 0;
            int cnt = 0;
            for(int k = 0; k <= i-2; k++) {
        		if(Character.isDigit(result.charAt(k))) {
        			followers = followers * 10 + (result.charAt(k) - 48);
                    cnt++;
        		}
                else {
                    position = k;
                }
        	}
            if (position != 0) {
                followers = followers * (int) (Math.pow(10, 6 - (cnt - position)));
            }
            else {
                followers = followers * (int) (Math.pow(10, 6));
            }
        }
        else {
        	System.out.println("LỖI LẤY FOLLOWERS");
        }
        
		return followers;
	}
	
	public void getFollowers(String url) {
		setMAX_FOLLOWERS(Math.max(getNumberOfFollowers(url) / 5000, 50));
		CollectNameAndUrl collect = new CollectNameAndUrl();
		collect.setMAX_KOLS(MAX_FOLLOWERS);
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
            while (this.check.tweetInfo.size() < MAX_TWEETS && scrollCount < (MAX_TWEETS / 2)) {
                List<WebElement> tweets = driver.findElements(By.cssSelector("article"));

                for (WebElement tweet : tweets) {
                    try {
                        // Tìm phần tử chứa URL bài tweet
                        WebElement ownerElement = tweet.findElement(By.xpath(".//a[contains(@href, '/status/')]"));
                        String tweetUrl = ownerElement.getAttribute("href");

                        WebElement userElement = tweet.findElement(By.xpath(".//div[@data-testid='User-Name']//a"));
                        String ownerName = userElement.getAttribute("href");
                        
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



	public int getMAX_TWEETS() {
		return MAX_TWEETS;
	}



	public void setMAX_TWEETS(int mAX_TWEETS) {
		MAX_TWEETS = mAX_TWEETS;
	}



	public int getMAX_FOLLOWERS() {
		return MAX_FOLLOWERS;
	}



	public void setMAX_FOLLOWERS(int mAX_FOLLOWERS) {
		MAX_FOLLOWERS = mAX_FOLLOWERS;
	}



	
	
}
