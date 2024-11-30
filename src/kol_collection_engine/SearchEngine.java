package kol_collection_engine;

import org.openqa.selenium.WebDriver;

import login_and_search.KOLSearch;

public class SearchEngine {
	private KOLSearch kolSearch;
	private WebDriver driver;
	
	public SearchEngine(WebDriver driver) {
		this.kolSearch = new KOLSearch();
		this.driver = driver;
	}
	
	
	public void init(String hashtag) {
		kolSearch.setsearchKeyword(hashtag);
		kolSearch.searchKOL(driver);
	}


	public KOLSearch getKolSearch() {
		return kolSearch;
	}


	public void setKolSearch(KOLSearch kolSearch) {
		this.kolSearch = kolSearch;
	}


	public WebDriver getDriver() {
		return driver;
	}


	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	
}
