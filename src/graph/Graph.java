package graph;
import java.util.*;

import kol_information.KOL;
import kol_information.Tweet;

public class Graph {
	
	private ArrayList<Node> listNodes;
	private ArrayList<Edge> listEdges;

	public Graph(ArrayList<Node> listNodes, ArrayList<Edge> listEdges) {
		this.listNodes = listNodes;
		this.listEdges = listEdges;
	}
	
	public Graph makeGraph(ArrayList<KOL> listKOL) {		
		ArrayList<Node> listNode = new ArrayList<>();
		ArrayList<Edge> listEdge = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		int cntNode = 0;
		
		for (int i = 0; i < listKOL.size(); ++i) {
			KOL kol = listKOL.get(i);
			Node node = new Node(kol.getNameKOL(), 0, 0, "KOL", kol.getKolUsername());
			listNode.add(node);
			map.put(kol.getNameKOL(), cntNode++);
		}
		
		for (int i = 0; i < listKOL.size(); ++i) {
			KOL kol = listKOL.get(i);
			ArrayList<String> listFollowers = kol.getListFollowers();
			for (int j = 0; j < listFollowers.size(); ++j) {
				if (!map.containsKey(listFollowers.get(j))) {
					Node node = new Node(listFollowers.get(j), 0, 0, "Followers", "");
					listNode.add(node);
					map.put(listFollowers.get(j), cntNode++);
				}
				Edge edge = new Edge(map.get(listFollowers.get(j)), map.get(kol.getNameKOL()), 1);
				listEdge.add(edge);
			}
			
			ArrayList<Tweet> listTweets = kol.getListTweets();
			for (int j = 0; j < listTweets.size(); ++j) {
				if (!map.containsKey(listTweets.get(j).getTweetLink())) {
					Node node = new Node(listTweets.get(j).getTweetLink(), 0, 0, "Tweet", "");
					listNode.add(node);
					map.put(listTweets.get(j).getTweetLink(), cntNode++);
				}
				if (kol.getNameKOL().equals(listTweets.get(j).getTweetOwner())) {
					Edge edge = new Edge(map.get(listTweets.get(j).getTweetLink()), map.get(kol.getNameKOL()), 1);
					listEdge.add(edge);
				} else {
					Edge edge = new Edge(map.get(kol.getNameKOL()), map.get(listTweets.get(j).getTweetLink()), 1);
					listEdge.add(edge);
				}
			}
		}

		return new Graph(listNode, listEdge);
	}

	public ArrayList<Node> getListNodes() {
		return listNodes;
	}

	public void setListNodes(ArrayList<Node> listNodes) {
		this.listNodes = listNodes;
	}

	public ArrayList<Edge> getListEdges() {
		return listEdges;
	}

	public void setListEdges(ArrayList<Edge> listEdges) {
		this.listEdges = listEdges;
	}
	
	
}
