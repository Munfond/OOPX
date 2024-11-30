package kol_collection_engine;

import java.util.Map;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import org.openqa.selenium.WebDriver;

import collect_KOL_infor.PersonDataWriter;

public class FileEngine {
	private String filepath = "src/output/data.csv";

	
	
	public void settingFile(Map<String,String> KOLData, WebDriver driver) {

		try {
            File file = new File(this.filepath);
            file.getParentFile().mkdirs();

            try(FileWriter fw = new FileWriter(this.filepath)) {
                fw.append("Link,Username,CountFollowers,Followers,CountTweets,Tweets\n");
                for(Map.Entry<String, String> entry : KOLData.entrySet()) {
                    PersonDataWriter node = new PersonDataWriter(driver);
                    
                    if(node.getInfor().getNumberOfFollowers(entry.getValue()) >= 100000) {
                        node.printFile(entry.getValue(), this.filepath, fw);
                        System.out.println("Lưu thông tin node thành công");
                    }
                    
                }
            }
    
        } catch(IOException e) {
            e.printStackTrace();
        }



        }
	
    }