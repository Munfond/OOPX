package kol_collection_engine;

import collect_KOL_infor.*;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class CollectEngine {
	private Map<String,String> kolData;
	private CollectNameAndUrl collect;
	
	
	public CollectEngine() {
		this.kolData = new HashMap<>();
		this.collect = new CollectNameAndUrl();
	}
	
	public void init(WebDriver driver) {
		collect.setMAX_KOLS(100);
		kolData = collect.collectKOLData(driver);
	}

	public Map<String, String> getKolData() {
		return kolData;
	}

	public void setKolData(Map<String, String> kolData) {
		this.kolData = kolData;
	}

	public CollectNameAndUrl getCollect() {
		return collect;
	}

	public void setCollect(CollectNameAndUrl collect) {
		this.collect = collect;
	}
	
	
}