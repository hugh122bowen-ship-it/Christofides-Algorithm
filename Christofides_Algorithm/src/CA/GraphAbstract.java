package CA;

import java.util.ArrayList;
public abstract class GraphAbstract {
	
	protected int vertexCount;

	public GraphAbstract(int vertexCount) {
		this.vertexCount = vertexCount;
	}
	
	public abstract void addEdge(int v1, int v2, double edgeWeight);
	/**
	 * 
	 * @return All edges of a given row from the graph sorted in ascending order based on the edge weight.
	 */
	
	public abstract ArrayList<Edge> getSortedEdgesFromRow(int row);
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return Get the weight of the edge between v1 and v2.
	 */
	public abstract double getEdgeWeight(int v1, int v2);
	
	public int getVertexCount() {
		return vertexCount;
	}
	
	
	protected abstract void generateEmptyGraph();
	/**
	 * 
	 * @return An ArrayList of all edges in the graph sorted in ascending order based on the edge weight.
	 */
	public abstract ArrayList<Edge> getSortedEdges();
}
