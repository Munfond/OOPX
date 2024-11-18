package collect_KOL_infor;

import java.util.Set;
import java.util.AbstractMap;
import java.util.HashSet;

public class InforOfKOL {
	public String nameOfKOL;
	public Set<String> followers;
	public Set<AbstractMap.SimpleEntry<String, String>> tweetInfo;
	
	
	public InforOfKOL() {
		this.followers = new HashSet<>();
		this.tweetInfo = new HashSet<>();
	}


	public String getNameOfKOL() {
		return nameOfKOL;
	}


	public void setNameOfKOL(String nameOfKOL) {
		this.nameOfKOL = nameOfKOL;
	}


	public Set<String> getFollowers() {
		return followers;
	}


	public void setFollowers(Set<String> followers) {
		this.followers = followers;
	}


	public Set<AbstractMap.SimpleEntry<String, String>> getTweetInfo() {
		return tweetInfo;
	}


	public void setTweetInfo(Set<AbstractMap.SimpleEntry<String, String>> tweetInfo) {
		this.tweetInfo = tweetInfo;
	}
	
	
	 
}
