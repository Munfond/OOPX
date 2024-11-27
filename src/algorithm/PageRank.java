package algorithm;

import graph.Graph;
import matrix.Matrix;

public class PageRank {
	
	public static Graph runPageRank(Graph graph) {
		
		for (int i = 0; i < graph.getListEdges().size(); ++i) {
			int u = graph.getListEdges().get(i).getU();	
			
			graph.getListNodes().get(u).increaseOutDegree();
		}
		
		for (int i = 0; i < graph.getListNodes().size(); ++i) {
			graph.getListNodes().get(i).setPoint(PageRank.getTotalPoint() / graph.getListNodes().size());
		}
		
//		for (int i = 0 ; i < graph.getListNodes().size(); ++i) {
//			System.out.print(" - NodeType: " + graph.getListNodes().get(i).getNodeType() + " ");
//			System.out.print(" --> Point: " + graph.getListNodes().get(i).getPoint() + " ");
//			System.out.println(" --> outDegree: " + graph.getListNodes().get(i).getOutDegree() + " ");			
//		}
		
		Matrix matrix1 = new Matrix(0, 0);		
		Matrix matrixRank = matrix1.makeMatrix(graph.getListNodes().size(), 1, PageRank.getTotalPoint() / graph.getListNodes().size());
		Matrix matrixM = matrix1.makeMatrix(graph);
		
//		matrixM.printMatrix();
//		matrixRank.printMatrix();

//		matrixM = matrix1.power(matrixM, PageRank.getStepLoop()); 
		Matrix newMatrixRank = matrix1.multiply(matrixM, matrixRank);
		
//		newMatrixRank.printMatrix();
		
		for (int i = 0; i < PageRank.getStepLoop(); ++i) {
			
			if (matrix1.checkStop(matrixRank, newMatrixRank, PageRank.getTotalPoint())) {
				matrixRank = newMatrixRank;
				break;
			}
			matrixRank = newMatrixRank;
			newMatrixRank = matrix1.multiply(matrixM, matrixRank);
//			System.out.println("--> Loop: " + i);
//			matrixRank.printMatrix();
		}
		
		
		for (int i = 0; i < graph.getListNodes().size(); ++i) {
			graph.getListNodes().get(i).setPoint(matrixRank.getValue(i, 0));
		}
		
		return graph;
	}
	

	public static int getStepLoop() {
		return 100000;
	}


	public static double getTotalPoint() {
		return 10000000;
	}
}
