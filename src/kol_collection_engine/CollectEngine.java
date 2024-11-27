package kol_collection_engine;

import collect_KOL_infor.*;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class CollectEngine {
	public Map<String,String> kolData;
	CollectNameAndUrl collect;
//	GetInfor get;
	
	
	public CollectEngine() {
		this.kolData = new HashMap<>();
		this.collect = new CollectNameAndUrl();
//		this.get = new GetInfor(driver);
	}
	
	public void init(WebDriver driver) {
//		get.getX("https://x.com/ChainPeople");
//		get.getFollowers();	
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