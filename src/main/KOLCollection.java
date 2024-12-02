package main;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Set;

import login_and_search.LoginEngine;
import login_and_search.SearchEngine;
import record_crawled_data.FileRecorded;

import java.util.HashSet;


public class KOLCollection {
    public static void runKOLCollection() {
        try {
            Set<String> hashtagSet = new HashSet<>();
            Set<String> KOLUrl = new HashSet<>();
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


                FileRecorded fileRecorded = new FileRecorded(loginEngine.getLogin().getWebDriver());
                fileRecorded.getKOLs().crawlingInfor();


                for(String entry : fileRecorded.getKOLs().getCollection()) {
                    KOLUrl.add(entry);
                }

                loginEngine.getLogin().getWebDriver().get("https://x.com/home");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            FileRecorded fileRecorded = new FileRecorded(loginEngine.getLogin().getWebDriver());
            fileRecorded.getKOLs().setCollection(KOLUrl);
            fileRecorded.settingFile(loginEngine.getLogin().getWebDriver());


            loginEngine.getLogin().getWebDriver().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
