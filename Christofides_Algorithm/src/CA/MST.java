package CA;

import java.util.ArrayList;
/**
 * Generates a Minimum Spanning Tree from a given graph
 */
public class MST {
	private Graph pairwiseGraph;
	public MST(Graph pairwiseGraph) {
		this.pairwiseGraph = pairwiseGraph;
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
	public Graph generateMST() {
		Graph mst = new Graph(pairwiseGraph.getVertexCount());
		ArrayList<Edge> sortedEdges = pairwiseGraph.getSortedEdges(); 
		
		
		for(int i = 0; i < sortedEdges.size(); i++) {
			Edge edge = sortedEdges.get(i);
			mst.addEdge(edge);
			 boolean containsCycle = mst.containsCycle();
			if(containsCycle) {
				mst.removePreviousEdge();
			}
			if(mst.getEdgeCount() == (mst.getVertexCount() - 1)) {
				break;
			}
		}
		
		return mst;
	}
}
