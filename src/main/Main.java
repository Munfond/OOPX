package main;

public class Main {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Duc Anh\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
		
		LoginEngine loginEngine = new LoginEngine();
		loginEngine.init();
		
		
		SearchEngine searchEngine = new SearchEngine(loginEngine.getLogin().getWebDriver());
		searchEngine.init();
		
		CollectEngine collectEngine = new CollectEngine();
		collectEngine.init(loginEngine.getLogin().getWebDriver());
		
		FileEngine KOLFile = new FileEngine();
		KOLFile.settingFile(collectEngine.getKolData(), loginEngine.getLogin().getWebDriver());
		
		
		loginEngine.login.close();
		
		
	}
}
