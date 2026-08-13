package CA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents a weighted graph using an adjacency matrix
 */
public class Graph  {
	private Edge[][] adjacencyMatrix;
	private int vertexCount;
	private Edge previousEdge;
	private int edgeCount;
	
	public Graph(int vertexCount) {
		this.vertexCount = vertexCount;
		previousEdge = null;
		edgeCount = 0;
		generateBlankMatrix();
	}
	
	
	private void generateBlankMatrix() {
		adjacencyMatrix = new Edge[vertexCount][vertexCount];
		for(int y =0; y < vertexCount; y++) {
			for(int x = 0; x < vertexCount; x++) {
				adjacencyMatrix[x][y] = new Edge(-1, x, y);
			}
		}
	}
	

	/**
	 * 
	 * @param v1
	 * @return An ArrayList of integers representing the index of all vertices that share an edge with the given vertex
	 */
	public ArrayList<Integer> getConnectedVertices(int v1) {
		ArrayList<Integer> connectedVertices = new ArrayList<Integer>();
		
		for(int i = 0; i < vertexCount; i++) {
			if(adjacencyMatrix[i][v1].getEdgeWeight() != -1) {
				connectedVertices.add(i);
			}	
		}
		return connectedVertices;
	}
	

	public void addEdge(int v1, int v2, double weight) {
		edgeCount++;
		previousEdge = new Edge(weight, v1, v2);
		adjacencyMatrix[v1][v2] = new Edge(weight, v1, v2);
		adjacencyMatrix[v2][v1] = new Edge(weight, v2, v1);
		
	}
	
	/**
	 * Removes the last added edge from the graph
	 */
	public void removePreviousEdge() {
		edgeCount--;
		int previousEdgeX = previousEdge.getAdjacencyTableIndexX();
		int previousEdgeY = previousEdge.getAdjacencyTableIndexY();
		
		adjacencyMatrix[previousEdgeX][previousEdgeY] = new Edge(-1, previousEdgeX, previousEdgeY);
		adjacencyMatrix[previousEdgeY][previousEdgeX] = new Edge(-1, previousEdgeY, previousEdgeX);

	}

	
	public void addEdge(Edge edge) {
		edgeCount++;
		int x = edge.getAdjacencyTableIndexX();
		int y = edge.getAdjacencyTableIndexY();
		previousEdge = new Edge(edge.getEdgeWeight(), x, y);
		adjacencyMatrix[x][y] = new Edge(edge.getEdgeWeight(), x, y);
		adjacencyMatrix[y][x] = new Edge(edge.getEdgeWeight(), y, x);
		
	}

	/**
	 * 
	 * @return All edges of a given row from the graph sorted in ascending order
	 */
	public Edge[] getSortedEdgesFromRow(int row){
		Edge[] rowCopy = adjacencyMatrix[row].clone();
		Arrays.sort(rowCopy);
		int index = 0;
		for(int i = 0; i < vertexCount; i++) {
			if(rowCopy[i].getEdgeWeight() != -1) {
				index = i;
				break;
			}
		}
		return Arrays.copyOfRange(rowCopy, index, rowCopy.length);
	}
	
	/**
	 * 
	 * @return An ArrayList of all edges in the graph sorted in ascending order
	 */
	public ArrayList<Edge> getSortedEdges() {
		
		ArrayList<Edge> edgesSorted = new ArrayList<Edge>();
		for(int y = 0; y < vertexCount; y++) {
			for(int x = y+1; x < vertexCount; x++) {
				if(adjacencyMatrix[x][y].getEdgeWeight() != -1) {
					edgesSorted.add(adjacencyMatrix[x][y]);
				}
				
			}
		}
		Collections.sort(edgesSorted);
		return edgesSorted;
	}
	
	
	/**
	 * 
	 * @return True if this graph contains a cycle
	 */
	public boolean containsCycle() {
		boolean[] visited = new boolean[vertexCount+1];
		
		for(int i = 0; i < vertexCount; i++) {
			if(!visited[i]) {
				if(depthFirstTraversal(i, visited, -1)) {
					return true;
				}
				
			}
			
		}
		return false;
	}
	
	
	/**
	 * Given another graph, this method adds all edges of the given graph to its graph
	 * If there are duplicate edges, it increments an edge counter
	 * 
	 * @param g The graph whose edges are being added
	 */
	public void combineGraph(Graph g) {
		
		for(int y = 0; y < vertexCount; y++) {
			for(int x = y+1; x < vertexCount; x++) {
				Double edgeWeight = g.getEdgeWeight(x, y);
				if(edgeWeight == -1) {
					continue;
				}
				if(adjacencyMatrix[x][y].getEdgeWeight() == edgeWeight) {
					incrementEdgeCount(x ,y);
				}else {
					addEdge(x, y, edgeWeight);
				}
			}
		}
	}
	
	
	/**
	 * 
	 * A depth first traversal is performed
	 * This method detects cycles by attempting to find previously visited vertices that aren't a parent of the current vertex
	 * 
	 * 
	 * @param current 
	 * @param visited
	 * @param parent
	 * @return true if a cycle is found
	 */
	private boolean depthFirstTraversal(int current, boolean[] visited, int parent) {
		visited[current] = true;
		
		for(int i = 0; i < adjacencyMatrix[current].length; i++) {
			if(adjacencyMatrix[current][i].getEdgeWeight() != -1) {
				if(!visited[i]) {
					if(depthFirstTraversal(i, visited, current)) {
						return true;
					}
				}
				else if(i != parent) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return An ArrayList holding the index of all vertices that have an odd degree
	 */
	public ArrayList<Integer> getOddDegreeVertices() {
		ArrayList<Integer> oddDegreeVertices = new ArrayList<Integer>();
		
		for(int i = 0; i < vertexCount; i++) {
			if(isOddDegree(i)) {
				oddDegreeVertices.add(i);
			}
			
		}
		return oddDegreeVertices;
	}
	
	private boolean isOddDegree(int vertex) {
		int degreeCount = 0;
		for(int i = 0; i < vertexCount; i++) {
			if(adjacencyMatrix[vertex][i].getEdgeWeight() != -1) {
				degreeCount++;
			}
			
		}
		
		return (degreeCount % 2 != 0);
	}
	
	private void incrementEdgeCount(int v1, int v2) {
		adjacencyMatrix[v1][v2].incrementEdgeCount();
		adjacencyMatrix[v2][v1].incrementEdgeCount();
	}
	

	
	
	public int getVertexCount() {
		return this.vertexCount;
	}
	
	public int getEdgeCount() {
		
		return this.edgeCount;
	}
	
	/**
	 * 
	 * @return The sum of the edge weights of every edge in the graph
	 */
	public double getTotalGraphWeighting() {
		double totalGraphWeight = 0;
		for(int y = 0; y < vertexCount; y ++) {
			for(int x = y+1; x < vertexCount; x++) {
				double weight = adjacencyMatrix[x][y].getEdgeWeight();
				if(weight != -1) {
					totalGraphWeight += weight;
				}
			}
		}
		return totalGraphWeight;
	}
	
	public double getEdgeWeight(int v1, int v2) {
		
		return adjacencyMatrix[v1][v2].getEdgeWeight();
	}
	public int getUnusedVertex(int v) {
		ArrayList<Integer> connectedVertices = getConnectedVertices(v);
		for(int vertex : connectedVertices) {
			if(!adjacencyMatrix[v][vertex].getEdgeUsed()) {
				adjacencyMatrix[v][vertex].useEdge();
				adjacencyMatrix[vertex][v].useEdge();
				return vertex;
				
			}
		}
		return -1;
	}

}
