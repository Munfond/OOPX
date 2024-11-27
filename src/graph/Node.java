package graph;

public class Node {
	private double point;
	private int outDegree;
	private String nodeType;
	private String nodeName;
	private String nodeUsername;
	
	
	public Node(String nodeName, double point, int outDegree, String nodeType, String nodeUsername) {
		this.point = point;
		this.outDegree = outDegree;
		this.nodeType = nodeType;
		this.nodeName = nodeName;
		this.nodeUsername = nodeUsername;
	}
	
	public void increaseOutDegree() {
		this.outDegree = this.outDegree + 1;
	}
	
	public void increasePoint(double x) {
		this.point  = this.point + x;
	}
	
	
	public double getPoint() {
		return point;
	}	
	public void setPoint(double point) {
		this.point = point;
	}
	public int getOutDegree() {
		return outDegree;
	}
	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}
	public String getNodeType() {
		return nodeType;
	}	
	
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeUsername() {
		return nodeUsername;
	}

	public void setNodeUsername(String nodeUsername) {
		this.nodeUsername = nodeUsername;
	}
	
	
}
