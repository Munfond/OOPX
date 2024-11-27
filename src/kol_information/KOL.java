package kol_information;
import java.util.ArrayList;

public class KOL {
	private String nameKOL, kolUsername;
	private ArrayList<String> listFollowers;
	private ArrayList<Tweet> listTweets;
	
	public KOL(String nameKOL, ArrayList<String> listFollowers, ArrayList<Tweet> listTweets, String kolUsername) {
		this.nameKOL = nameKOL;
		this.listFollowers = listFollowers;
		this.listTweets = listTweets;
		this.kolUsername = kolUsername;
	}
	
	public int getNumberFollowers() {
		return this.listFollowers.size();
	}	
	
	public String getNameKOL() {
		return nameKOL;
	}
	
	public void setNameKOL(String nameKOL) {
		this.nameKOL = nameKOL;
	}
	
	public ArrayList<String> getListFollowers() {
		return listFollowers;
	}
	
	public void setListFollowers(ArrayList<String> listFollowers) {
		this.listFollowers = listFollowers;
	}
	
	public ArrayList<Tweet> getListTweets() {
		return listTweets;
	}
	
	public void setListTweets(ArrayList<Tweet> listTweets) {
		this.listTweets = listTweets;
	}

	public String getKolUsername() {
		return kolUsername;
	}

	public void setKolUsername(String kolUsername) {
		this.kolUsername = kolUsername;
	}
	
	
}
