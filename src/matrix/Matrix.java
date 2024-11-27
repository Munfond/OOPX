package matrix;


import algorithm.PageRank;
import graph.Graph;

public class Matrix {
	private final double epsilon = 1e-15;
	private final int maxN = 7000;
	private int row, column;
	private double[][] value;
	
	public Matrix(int row_, int column_) {
		this.row = row_;
		this.column = column_;
		this.value = new double[row_][column_];
	}
	
	public Matrix multiply(Matrix a, Matrix b) {
		Matrix c = new Matrix(a.row, b.column);
		for (int i = 0; i < a.row; ++i) {
			for (int j = 0; j < b.column; ++j) {
				for (int k = 0; k < a.column; ++k) {
					c.value[i][j] += a.value[i][k] * b.value[k][j];
//					c.value[i][j] = (int)c.value[i][j];
				}
			}
		}
		return c;
	}
	
	public Matrix power(Matrix a, int x) {
		if (x == 1) return a;
		Matrix temp = power(a, x / 2);
		temp = multiply(temp, temp);
		if (x % 2 == 1) {
			temp = multiply(temp, a);
		}
		return temp;
	}
	
	public boolean checkStop(Matrix a, Matrix b, double T) {
		for (int i = 0; i < a.row; ++i) {
			for (int j = 0; j < a.column; ++j) {
				if (Math.abs(a.value[i][j] - b.value[i][j]) > epsilon * T) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Matrix makeMatrix(Graph graph) {
		Matrix matrix = new Matrix(graph.getListNodes().size(), graph.getListNodes().size());
		
		for (int i = 0; i < graph.getListEdges().size(); ++i) {
			int u = graph.getListEdges().get(i).getU();
			int v = graph.getListEdges().get(i).getV();		
			if (graph.getListNodes().get(u).getOutDegree() != 0) {
				matrix.setValue(v, u, PageRank.getTotalPoint() / graph.getListNodes().get(u).getOutDegree());
			} else {
				matrix.setValue(v, u, 0.01);
			}
		}	
		
		for (int i = 0 ; i < graph.getListNodes().size(); ++i) {
			matrix.setValue(i, i, 0.01);
		}
		
		return matrix;
	}
	
	public Matrix makeMatrix(int row, int column, double allValue) {
		Matrix matrix = new Matrix(row, column);
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < column; ++j) {
				matrix.setValue(i, j, allValue);
			}
		}
		return matrix;
	}
	
	public void printMatrix() {
		for (int i = 0; i < this.row; ++i) {
			for (int j = 0 ; j < this.column; ++j) {
				System.out.print(this.value[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public double getValue(int i, int j) {
		return value[i][j];
	}
	
	public void setValue(int x, int y, double val) {
		this.value[x][y] = val;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public double getEpsilon() {
		return epsilon;
	}

	public int getMaxN() {
		return maxN;
	}
	
}
