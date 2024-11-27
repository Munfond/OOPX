package main;

import java.util.ArrayList;

import algorithm.PageRank;
import graph.Graph;
import kol_information.KOL;
import pair.Pair;
import processing.DataRead;

public class Analysis {

	
	public static void runAnalysis() {
		DataRead dataRead = new DataRead();
		ArrayList<KOL> listKOL = dataRead.runDataRead();
		
		Graph graph1 = new Graph(null, null);
		Graph graphRank = graph1.makeGraph(listKOL);
		
		System.out.println("--> Numbers KOL: " + listKOL.size());
		
		System.out.println("--> Numbers Nodes: " + graphRank.getListNodes().size());
		System.out.println("--> Numbers Edges: " + graphRank.getListEdges().size());
		
		graphRank = PageRank.runPageRank(graphRank);
		
		ArrayList<Pair> rank = new ArrayList<>();		
		
		
		for (int i = 0; i < graphRank.getListNodes().size(); ++i) {
			rank.add(new Pair(graphRank.getListNodes().get(i).getPoint(), i));
		}
		
		
		Pair pair = new Pair(0, 0);
		rank = pair.sortListPair(rank);
		
		
		
		System.out.println("--- Results ---");
		int cnt = 0;
		for (int i = 0; i < rank.size(); ++i) {
			double point = rank.get(i).getFirst();
			int id = rank.get(i).getSecond();
			if (graphRank.getListNodes().get(id).getNodeType() == "KOL") {
				System.out.println("Rank: " + (++cnt) + " - KOL: " + graphRank.getListNodes().get(id).getNodeUsername() + " - Point: " + point);
			}
		}
		
	}
}
