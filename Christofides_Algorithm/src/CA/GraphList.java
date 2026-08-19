package CA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GraphList extends GraphAbstract{
	private Map<Integer, ArrayList<Edge>> adjacencyList;
	public GraphList(int vertexCount) {
		super(vertexCount);
		adjacencyList = new HashMap<>();
		generateEmptyGraph();
	}

	
	private void addVertex(int v1) {
		adjacencyList.put(v1, new ArrayList<Edge>());
	}
	
	@Override
	public void addEdge(int v1, int v2, double edgeWeight) {
		Edge potentialEdge1 = getEdge(v1, v2);
		Edge potentialEdge2 = getEdge(v2, v1);
		if(potentialEdge1 != null) {
			potentialEdge1.incrementEdgeCount();
			potentialEdge2.incrementEdgeCount();
		}
		else {
			adjacencyList.get(v1).add(new Edge(edgeWeight, v1, v2));
			adjacencyList.get(v2).add(new Edge(edgeWeight, v2, v1));
		}
	}

	@Override
	public ArrayList<Edge> getSortedEdgesFromRow(int row) {
		ArrayList<Edge> sortedEdges = adjacencyList.get(row);
		Collections.sort(sortedEdges);
		return sortedEdges;
	}
	
	public ArrayList<Edge> getUnsortedEdgesFromRow(int row) {
		return adjacencyList.get(row);
	}

	@Override
	public double getEdgeWeight(int v1, int v2) {
		ArrayList<Edge> edges = adjacencyList.get(v1);
		for(Edge edge : edges) {
			if(edge.getAdjacencyTableIndexY() == v2) {
				return edge.getEdgeWeight();
			}
		}
		return 0;
	}

	@Override
	public ArrayList<Edge> getSortedEdges() {
		ArrayList<Edge> allEdges = new ArrayList<Edge>();
		for(int i = 0; i < vertexCount; i++) {
			allEdges.addAll(getUnsortedEdgesFromRow(i));
		}
		Collections.sort(allEdges);

	    ArrayList<Edge> unique = new ArrayList<Edge>();
	    for(Edge edge : allEdges) {
	        if(edge.getAdjacencyTableIndexX() < edge.getAdjacencyTableIndexY()) {
	            unique.add(edge);
	        }
	    }
	    return unique;
	}


	@Override
	protected void generateEmptyGraph() {
		for(int i = 0; i < vertexCount; i++) {
			addVertex(i);
		}
		
	}
	
	private Edge getEdge(int v1, int v2) {
		ArrayList<Edge> edges = adjacencyList.get(v1);
		for(Edge edge : edges) {
			if(edge.getAdjacencyTableIndexY() == v2) {
				return edge;
			}
		}
		return null;
	}
	
	public void removeEdge(int v1, int v2) {
		ArrayList<Edge> edgesV1 = adjacencyList.get(v1);
		for(int i = edgesV1.size()-1; i >= 0; i--) {
			if(edgesV1.get(i).getAdjacencyTableIndexY() == v2) {
				edgesV1.remove(i);
				break;
			}
		}
		
		
		ArrayList<Edge> edgesV2 = adjacencyList.get(v2);
		for(int i = edgesV2.size()-1; i >= 0; i--) {
			if(edgesV2.get(i).getAdjacencyTableIndexY() == v1) {
				edgesV2.remove(i);
				break;
			}
		}
	}
	
	public int getUnusedVertex(int v1) {
		ArrayList<Edge> edges = getUnsortedEdgesFromRow(v1);
		for(Edge edge : edges) {
			if(!edge.getEdgeUsed()) {
				int v2 = edge.getAdjacencyTableIndexY();
				
				edge.useEdge();
				getEdge(v2, v1).useEdge();
				
				return edge.getAdjacencyTableIndexY();
			}
		}
		return -1;
	}
	
	
	/**
	 * Given another graph, this method adds all edges of the given graph to its graph
	 * If there are duplicate edges, it increments an edge counter
	 * 
	 * @param g The graph whose edges are being added
	 */
	public void combineGraph(GraphList graph) {
		ArrayList<Edge> edgesToBeAdded = new ArrayList<>();
		for(int i = 0; i < vertexCount; i++) {
			edgesToBeAdded.addAll(graph.getUnsortedEdgesFromRow(i));
		}
		for(Edge edge : edgesToBeAdded) {
			int posX = edge.getAdjacencyTableIndexX();
			int posY = edge.getAdjacencyTableIndexY();
			if(posX > posY) {
				addEdge(posX, posY, edge.getEdgeWeight());
			}
			
		}
	}

	/**
	 * 
	 * @return An ArrayList holding the index of all vertices that have an odd number of edges connected to them
	 */
	public ArrayList<Integer> getOddDegreeVertices() {
		ArrayList<Integer> oddDegreeVertices = new ArrayList<Integer>();
		for(int i = 0; i < vertexCount; i++) {
			if(adjacencyList.get(i).size() % 2 != 0) {
				oddDegreeVertices.add(i);
			}
		}
		return oddDegreeVertices;
	}
	/**
	 * 
	 * @return The sum of the edge weights of every edge in the graph
	 */
	public double getTotalGraphWeighting() {
		double totalWeight = 0;
		for(int i = 0; i < vertexCount; i++) {
			ArrayList<Edge> edges = getUnsortedEdgesFromRow(i);
			for(Edge edge : edges) {
				totalWeight += edge.getEdgeWeight();
			}
		}
		return (totalWeight / 2);
	}

}
