package main;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Map;
import java.util.Set;

import java.util.HashMap;
import java.util.HashSet;

import kol_collection_engine.*;

public class KOLCollection {
    public static void runKOLCollection() {
        try {
            Map<String,String> kolData = new HashMap<>();
            Set<String> hashtagSet = new HashSet<>();
            URL url = KOLCollection.class.getProtectionDomain().getCodeSource().getLocation();
            
            String decodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8.name());
            
            File parentFile = new File(decodedPath);
            String chromeDriverPath = parentFile.getPath() + "\\resources\\chromedriver.exe";
            String hashtagFilePath = parentFile.getPath() + "\\resources\\hashtag.txt";

            System.setProperty("webdriver.chrome.driver", chromeDriverPath);

            try (BufferedReader br = new BufferedReader(new FileReader(hashtagFilePath))) {
                String line;

                while ((line = br.readLine()) != null) {
                    hashtagSet.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            LoginEngine loginEngine = new LoginEngine();
            loginEngine.init();

            for(String hashtag : hashtagSet) {
                SearchEngine searchEngine = new SearchEngine(loginEngine.getLogin().getWebDriver());
                searchEngine.init(hashtag);
                
                CollectEngine collectEngine = new CollectEngine();
                collectEngine.init(searchEngine.getDriver());

                for(Map.Entry<String, String> entry : collectEngine.getKolData().entrySet()) {
                    kolData.put(entry.getKey(), entry.getValue());
                }

                loginEngine.getLogin().getWebDriver().get("https://x.com/home");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    		
    		FileEngine KOLFile = new FileEngine();
    		KOLFile.settingFile(kolData, loginEngine.getLogin().getWebDriver());

    		
    		
    		loginEngine.getLogin().close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
