package collect_KOL_infor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectNameAndUrl {
    private static final int MAX_KOLS = 50;

    public Map<String, String> collectKOLData(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Map<String, String> kolData = new HashMap<>();
        try {
            int attempts = 0;
            int previousSize = 0;

            while (kolData.size() < MAX_KOLS && attempts < 10) {
                // Đợi phần tử xuất hiện
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                List<WebElement> kolProfiles = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector("button[data-testid='UserCell']")
                ));

                // Thu thập dữ liệu
                for (WebElement profile : kolProfiles) {
                    String name = profile.findElement(By.cssSelector("span")).getText();
                    String url = profile.findElement(By.cssSelector("a")).getAttribute("href");

                    if (!kolData.containsKey(name)) { // Tránh trùng lặp
                        kolData.put(name, url);
                    }

                    if (kolData.size() >= MAX_KOLS) {
                        break;
                    }
                }

                // Kiểm tra nếu không có dữ liệu mới, dừng lại
                if (kolData.size() == previousSize) {
                    attempts++;
                } else {
                    attempts = 0; // Reset nếu có thêm dữ liệu
                }
                previousSize = kolData.size();

                // Cuộn xuống cuối trang
                jsExecutor.executeScript("window.scrollBy(0, 1000);");
                Thread.sleep(2000); // Đợi nội dung mới tải
            }

        } catch (Exception e) {
            System.out.println("Error while collecting KOL data: " + e.getMessage());
        }

        return kolData;
    }
    
}
