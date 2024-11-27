package login_and_search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class KOLSearch {
	public String searchKeyword;
	public WebElement searchBox;
	
	public void setsearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	public void searchKOL(WebDriver driver)
	{	
		WebElement searchBox = driver.findElement(By.cssSelector("input[data-testid='SearchBox_Search_Input']"));
        searchBox.sendKeys(searchKeyword);
        searchBox.submit();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement peopleTab = driver.findElement(By.xpath("//a[contains(@href, '/search?q=') and contains(., 'People')]"));
        peopleTab.click();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
