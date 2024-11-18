package main;

import search.*;

import org.openqa.selenium.WebDriver;

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
