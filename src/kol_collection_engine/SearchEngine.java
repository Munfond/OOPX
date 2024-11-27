package kol_collection_engine;

import org.openqa.selenium.WebDriver;

import login_and_search.KOLSearch;

public class SearchEngine {
	public KOLSearch kolSearch;
	public WebDriver driver;
	
	public SearchEngine(WebDriver driver) {
		this.kolSearch = new KOLSearch();
		this.driver = driver;
	}
	
	
	public void init() {
		kolSearch.setsearchKeyword("#blockchain");
		kolSearch.searchKOL(driver);
	}
}
