package CA;

import java.util.ArrayList;

/**
 * Calculates a lower bound for the length of a TSP tour
 */
public class TSPLowerBound {
	
	
	/**
	 * This method finds a lower bound for a TSP tour by:
	 * 1. Removes the final vertex from the original graph
	 * 2. Finds an MST of the graph with the removed vertex
	 * 3. Finds the two shortest edges incident to the removed vertex
	 * 4. Sums the length of the MST and the two shortest edges to calculate a lower bound for a TSP tour
	 * 
	 * @param pairwiseGraph
	 * @param vertexCount	
	 * @return A lower bound for a TSP tour
	 */
	public double findTSPLowerBound(GraphMatrix pairwiseGraph, int vertexCount) {
		GraphMatrix pairwiseGraphOneVertexRemoved = new GraphMatrix(vertexCount-1);
		
		for(int y = 0; y < vertexCount-1; y++) {
			for(int x = y+1; x < vertexCount-1; x++) {
				pairwiseGraphOneVertexRemoved.addEdge(x, y, pairwiseGraph.getEdgeWeight(x, y));
			}
		}
		MST mstGenerator = new MST();
		GraphList mstOneVertexRemoved = mstGenerator.generateMST(pairwiseGraphOneVertexRemoved);
		
		ArrayList<Edge> sortedEdges = pairwiseGraph.getSortedEdgesFromRow(vertexCount-1);
		
		double lowerTSPBound = 0;
		lowerTSPBound += mstOneVertexRemoved.getTotalGraphWeighting();
		lowerTSPBound += sortedEdges.get(0).getEdgeWeight();
		lowerTSPBound += sortedEdges.get(1).getEdgeWeight();
		
		
		return lowerTSPBound;
	}
}
