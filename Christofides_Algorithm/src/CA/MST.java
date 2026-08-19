package CA;

import java.util.ArrayList;
/**
 * Generates a Minimum Spanning Tree from a given graph
 */

public class MST {
	private GraphList mst;
	public MST() {
	}
	
	
	/**
	 * This method generates an MST using Kruskal's algorithm
	 * 
	 * This method generates an MST by:
	 * 1. Getting a list of every edge in the graph sorted by edge weight in ascending order
	 * 2. Adds the lowest weight edge to the graph such that no cycles are formed
	 * 3. Repeat step two ignoring any previously considered edges
	 * 4. Stop when the MST contains, the vertex count - 1 edges
	 * @return A graph containing an MST
	 */
	public GraphList generateMST(GraphMatrix pairwiseGraph) {
		mst = new GraphList(pairwiseGraph.getVertexCount());
		ArrayList<Edge> sortedEdges = pairwiseGraph.getSortedEdges(); 
		
		

		int edgeCount = 0;
		
		
		for(int i = 0; i < sortedEdges.size(); i++) {
			Edge edge = sortedEdges.get(i);
			
			int posX = edge.getAdjacencyTableIndexX();
			int posY = edge.getAdjacencyTableIndexY();
			mst.addEdge(posX, posY, edge.getEdgeWeight());
			
			if(containsCycle()) {
				mst.removeEdge(posX, posY);
			}else {
				edgeCount++;
			}
			if(edgeCount == (mst.getVertexCount() - 1)) {
				break;
			}
		}
		
		return mst;
	}
	
	private boolean containsCycle() {
		boolean[] visited = new boolean[mst.getVertexCount()];
		for(int i = 0; i < visited.length; i++) {
			if(!visited[i]) {
				if(depthFirstTraversal(i, -1, visited)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean depthFirstTraversal(int current, int parent, boolean[] visited) {
		visited[current] = true;
		
		ArrayList<Edge> incidentEdges = mst.getUnsortedEdgesFromRow(current);
		for(int i = 0; i < incidentEdges.size(); i++) {
			int index = incidentEdges.get(i).getAdjacencyTableIndexY();
			if(!visited[index]) {
				if(depthFirstTraversal(index, current, visited)) {
					return true;
				}
			}else if(index != parent) {
				return true;
			}
		}
		
		return false;
	}
}
