package kol_collection_engine;

import java.util.Map;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import org.openqa.selenium.WebDriver;

import collect_KOL_infor.CollectCreateNode;

public class FileEngine {
	private String filepath = "src/output/data.csv";

	
	
	public void settingFile(Map<String,String> KOLData, WebDriver driver) {

		try {
            File file = new File(this.filepath);
            file.getParentFile().mkdirs();

            try(FileWriter fw = new FileWriter(this.filepath)) {
                fw.append("Link,Username,CountFollowers,Followers,CountTweets,Tweets\n");
                for(Map.Entry<String, String> entry : KOLData.entrySet()) {
                    CollectCreateNode node = new CollectCreateNode(driver);
                    node.printFile(entry.getValue(), this.filepath, fw);
                }
            }
    
        } catch(IOException e) {
            e.printStackTrace();
        }



        }
	
    }