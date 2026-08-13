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

		 ArrayList<Edge> potentialEdges = new ArrayList<>();
		 
		 ArrayList<Edge> sortedEdges = pairwiseGraph.getSortedEdges();
		 
		 for(Edge edge : sortedEdges) {
			 int x = edge.getAdjacencyTableIndexX();
			 int y = edge.getAdjacencyTableIndexY();
			 
			 if(oddDegreeVertices.contains(x) && oddDegreeVertices.contains(y)) {
				 potentialEdges.add(edge);
			 }
		 }
		Collections.sort(potentialEdges);
			 
			 
		while(potentialEdges.size() != 0) {
			Edge chosenEdge = potentialEdges.get(0);
				 
			int chosenX = chosenEdge.getAdjacencyTableIndexX();
			int chosenY = chosenEdge.getAdjacencyTableIndexY();
				 
			perfectMatching.addEdge(chosenEdge);
				 
			for(int i = potentialEdges.size()-1 ; i >= 0; i--) {
					 
				Edge edge = potentialEdges.get(i);
					 
				int x = edge.getAdjacencyTableIndexX();
				int y = edge.getAdjacencyTableIndexY();
					 
				if(x == chosenX || x == chosenY || y == chosenX || y == chosenY) {
					potentialEdges.remove(i);
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
