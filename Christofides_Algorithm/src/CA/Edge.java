package CA;

/**
 * Represents an edge in a graph 
 * Holds position in the adjacency matrix and number of edges created in this position
 */
public class Edge implements Comparable<Edge>{
	private final double edgeWeight;
	private final int adjacencyTableIndexX;
	private final int adjacencyTableIndexY;
	private int edgeCount = 1;
	private boolean edgeUsed = false;
	public Edge(double edgeWeight, int adjacencyTableIndexX, int adjacencyTableIndexY) {
		this.edgeWeight = edgeWeight;
		this.adjacencyTableIndexX = adjacencyTableIndexX;
		this.adjacencyTableIndexY = adjacencyTableIndexY;
	}
	
	public void useEdge() {
		if(edgeCount > 1) {
			edgeCount--;
		}else {
			edgeUsed = true;
		}
	}
	
	public boolean getEdgeUsed() {
		return this.edgeUsed;
	}
	
	public double getEdgeWeight() {
		return this.edgeWeight;
	}
	public int getAdjacencyTableIndexX() {
		return this.adjacencyTableIndexX;
		
	}
	public int getAdjacencyTableIndexY() {
		return this.adjacencyTableIndexY;
	}
	
	public void incrementEdgeCount() {
		edgeCount++;
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(this.edgeWeight, o.edgeWeight);
	}
}
