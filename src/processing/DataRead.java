package processing;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import kol_information.KOL;
import kol_information.Tweet;

public class DataRead {
	
	private String dataPath;
	
	public ArrayList<KOL> runDataRead() {
		ArrayList<KOL> listKOL = new ArrayList<>();
    	
		try {
			URL url = DataRead.class.getProtectionDomain().getCodeSource().getLocation();
			String decodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8.name());  	
	    	File parentFile = new File(decodedPath);
	    	this.dataPath = parentFile.getPath() + "\\resources\\KOLData.txt";
		} catch(Exception e) {
			System.out.println("Có lỗi xảy ra khi đọc file: " + e.getMessage());
		}
    	
    	ArrayList<String> listStr = new ArrayList<>();
    	
    	
    	try {
	    	File dataFile = new File(this.dataPath); 		    	
	    	Scanner scanner = new Scanner(dataFile);
	    	
	    	while (scanner.hasNextLine()) {
	    		String str = scanner.nextLine();
	    		if (str.length() != 0) {
	    			listStr.add(str);
	    		}
	    	}
	    	
	    	scanner.close();
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}    	
    	
    	int index = 0;
    	
    	while (index < listStr.size()) {
    		ArrayList<String> listFollowers = new ArrayList<>();
    		ArrayList<Tweet> listTweets = new ArrayList<>();
    		
    		String temp = listStr.get(index++);
    		String nameKOL = "", kolUsername = "";
    		boolean foundSpace = false;
    		for (int i = 0; i < temp.length(); ++i) {
    			char c = temp.charAt(i);
    			if (c == ' ') {
    				foundSpace = true;
    			} else if (foundSpace == false) {
    				nameKOL += c;
    			} else {
    				kolUsername += c;
    			}
    		}
    		
    		int numberFollowers = Integer.parseInt(listStr.get(index++));
    		
    		while ((numberFollowers--) > 0) {
    			listFollowers.add(listStr.get(index++));
    		}
    		
    		int numberTweets = Integer.parseInt(listStr.get(index++));
    		
    		while ((numberTweets--) > 0 ){
    			String tweetLink = "", tweetOwner = "";
    			String temp1 = listStr.get(index++);
    			int type = 0;
    			for (int i = 0; i < temp1.length(); ++i) {
    				char c = temp1.charAt(i);
    				if (c == ' ') {
    					type = 1;
    				} else if (type == 1) {
    					tweetLink += c;
    				} else {
    					tweetOwner += c;
    				}
    			}
    			listTweets.add(new Tweet(tweetLink, tweetOwner));
    		}
    		listKOL.add(new KOL(nameKOL, listFollowers, listTweets, kolUsername));
//    		if (listKOL.size() == 10) break;
    	}
    	
//    	for (int i = 0 ; i < listKOL.size(); ++i) {
//    		System.out.println(listKOL.get(i).getNameKOL());
//    		System.out.println("-> Number Followers: " + listKOL.get(i).getListFollowers().size());
//    		System.out.println("-> Number Tweets: " + listKOL.get(i).getListTweets().size());
//    	}
    	
    	return listKOL;
	}
	
}
