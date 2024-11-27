package pair;

import java.util.ArrayList;

public class Pair {
	private double first;
	private int second;
	
	public Pair(double first, int second) {
		this.first = first;
		this.second = second;
	}
	
	public ArrayList<Pair> sortListPair(ArrayList<Pair> A) {
		for (int i = 0; i < A.size(); ++i) {
			for (int j = i + 1; j < A.size(); ++j) {
				Pair X = new Pair(A.get(i).getFirst(), A.get(i).getSecond());
				Pair Y = new Pair(A.get(j).getFirst(), A.get(j).getSecond());
				if (X.getFirst() < Y.getFirst()) {
					A.set(i, Y);
					A.set(j, X);
				}
			}
		}
		return A;
	}
	

	public double getFirst() {
		return first;
	}

	public void setFirst(double first) {
		this.first = first;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
	
	
	
}
