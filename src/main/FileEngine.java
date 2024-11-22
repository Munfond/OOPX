package main;

import file_getter.*;

import java.util.Map;


import org.openqa.selenium.WebDriver;

public class FileEngine {
	private File file = new File();
	
	
	public void settingFile(Map<String,String> KOLData, WebDriver driver) {
		for(Map.Entry<String, String> entry : KOLData.entrySet()) {
			CollectCreateNode node = new CollectCreateNode(driver);
			node.printFile(entry.getValue(), file.getBw());
		}
	}
	
}
