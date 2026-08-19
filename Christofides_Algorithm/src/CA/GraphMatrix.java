package CA;

import java.util.ArrayList;
import java.util.Collections;

public class GraphMatrix extends GraphAbstract{
	
	private final double nonEdgeValue = -1;
	private double[][] adjacencyMatrix;
	
	
	public GraphMatrix(int vertexCount) {
		super(vertexCount);
		generateEmptyGraph();
	}
	
	@Override
	protected void generateEmptyGraph() {
		adjacencyMatrix = new double[vertexCount][vertexCount];
		for(int y = 0; y < vertexCount; y++) {
			for(int x = 0; x < vertexCount; x++) {
				adjacencyMatrix[x][y] = nonEdgeValue;
			}
		}
	}

	@Override
	public void addEdge(int v1, int v2, double edgeWeight) {
		adjacencyMatrix[v1][v2] = edgeWeight;
		adjacencyMatrix[v2][v1] = edgeWeight;
		
	}

	@Override
	public ArrayList<Edge> getSortedEdgesFromRow(int row) {
		ArrayList<Edge> sortedEdges = new ArrayList<Edge>();
		for(int i = 0; i < vertexCount; i++) {
			sortedEdges.add(new Edge(adjacencyMatrix[row][i], row, i));
		}
		Collections.sort(sortedEdges);
		
		int index = 0;
		for(int i = 0; i < vertexCount; i++) {
			if(sortedEdges.get(i).getEdgeWeight() != nonEdgeValue) {
				index = i;
				break;
			}
		}
		return new ArrayList<>(sortedEdges.subList(index, sortedEdges.size()));
		
	}

	@Override
	public double getEdgeWeight(int v1, int v2) {
		return adjacencyMatrix[v1][v2];
	}

	@Override
	public ArrayList<Edge> getSortedEdges() {
		ArrayList<Edge> sortedEdges = new ArrayList<Edge>();
		for(int y = 0; y < vertexCount; y++) {
			for(int x = y+1; x < vertexCount; x++) {
				double weight = adjacencyMatrix[x][y];
				if(weight != nonEdgeValue) {
					sortedEdges.add(new Edge(adjacencyMatrix[x][y], x, y));
				}
			}
		}
		Collections.sort(sortedEdges);
		return sortedEdges;
	}
	
	
}
