package CA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Creates a perfect matching on a set of given vertices
 */
public class PerfectMatching {
	private ArrayList<Integer> oddDegreeVertices;
	public PerfectMatching(ArrayList<Integer> oddDegreeVertices) {
		this.oddDegreeVertices = oddDegreeVertices;
	}
	
	
	/**
	 * This method uses a greedy algorithm to attempt to find a minimum weight perfect matching on a given set of vertices
	 * It will not always find a minimum weight perfect matching.
	 * @param pairwiseGraph
	 * @return A graph containing a perfect matching on a set of given vertices
	 */
	
	public Graph generatePerfectMatching(Graph pairwiseGraph, int vertexCount) {
		Graph perfectMatching = new Graph(pairwiseGraph.getVertexCount());
		LinkedHashMap<Integer, Integer> validEdges = new LinkedHashMap<Integer, Integer>();

		
		ArrayList<Edge> sortedEdges = perfectMatching.getSortedEdges();
		for(Edge edge : sortedEdges ) {
			validEdges.put(edge.getAdjacencyTableIndexX(), edge.getAdjacencyTableIndexY());
		}
		ArrayList<Integer> evenDegreeVertices = getEvenDegreeVertices(vertexCount);
		for(int i = 0; i < evenDegreeVertices.size(); i++) {
			for(int j = 0; j < vertexCount; j++) {
				validEdges.remove(i, j);
			}
		}
		
		while(!validEdges.isEmpty()) {
			Entry<Integer, Integer> firstEntry = validEdges.entrySet().iterator().next();
			int chosenX = firstEntry.getKey();
			int chosenY = firstEntry.getValue();
			perfectMatching.addEdge(chosenX, chosenY, pairwiseGraph.getEdgeWeight(chosenX,chosenY));
			
			for(int i = 0; i < oddDegreeVertices.size(); i++) {
				for(int j = 0; j < oddDegreeVertices.size(); j++) {
					if(i == chosenX || i == chosenY || j == chosenX || j == chosenY) {
						validEdges.remove(i, j);
					}
				} 
			}
		}
		
		return perfectMatching;
	}
	
	private ArrayList<Integer> getEvenDegreeVertices(int vertexCount) {
		ArrayList<Integer> evenDegreeVertices = new ArrayList<Integer>();
		for(int i = 0; i < vertexCount; i++) {
			evenDegreeVertices.add(i);
		}
		evenDegreeVertices.removeAll(oddDegreeVertices);
		
		return evenDegreeVertices;
	}
}
