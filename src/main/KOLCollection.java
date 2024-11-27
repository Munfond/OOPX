package main;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import kol_collection_engine.*;

public class KOLCollection {
    public static void runKOLCollection() {
        try {
            URL url = KOLCollection.class.getProtectionDomain().getCodeSource().getLocation();
            
            String decodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8.name());
            
            File parentFile = new File(decodedPath);
            String chromeDriverPath = parentFile.getPath() + "\\resources\\chromedriver.exe";

            System.setProperty("webdriver.chrome.driver", chromeDriverPath);

            LoginEngine loginEngine = new LoginEngine();
            loginEngine.init();
            
            SearchEngine searchEngine = new SearchEngine(loginEngine.getLogin().getWebDriver());
    		searchEngine.init();
    		
    		CollectEngine collectEngine = new CollectEngine();
    		collectEngine.init(loginEngine.getLogin().getWebDriver());
    		
    		FileEngine KOLFile = new FileEngine();
    		KOLFile.settingFile(collectEngine.getKolData(), loginEngine.getLogin().getWebDriver());
    		
    		
    		loginEngine.login.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
