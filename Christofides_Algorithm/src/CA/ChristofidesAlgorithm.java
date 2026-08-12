package CA;

import java.util.ArrayList;

/**
 * Generates a graph containing a TSP tour using Christofides Algorithm
 */
public class ChristofidesAlgorithm {

	final int vertexCount; 

	public ChristofidesAlgorithm(int vertexCount) {
		this.vertexCount = vertexCount;
	}
	
	/**
	 * This method uses Christofides Algorithm to generate a TSP tour.
	 * 
	 * This method is as follows:
	 * 1. Generate a MST on a set of vertices.
	 * 2. Generate a Perfect Matching on all odd degree vertices of the MST.
	 * 3. Combine the Perfect Matching and the MST.
	 * 4. Find an Eulerian walk on the combined graph, one must exist.
	 * 5. Create a Hamiltonian walk on the Eulerian Walk by short-cutting any vertices visited multiple times.
	 * 6. Generate a graph using this walk.
	 * Note that this will is not a perfect Christofides Algorithm as a greedy algorithm is used to find the perfect
	 * matching, and will not necessarily produce a minimum weight perfect matching.
	 * 
	 * @return A graph containing a TSP tour
	 */
	public Graph generateTour(Graph pairwiseGraph) {
		
		MST mstGenerator = new MST(pairwiseGraph);
		Graph mst = mstGenerator.generateMST();
		
		PerfectMatching perfectMatchingGenerator = new PerfectMatching(mst.getOddDegreeVertices());
		Graph mpm = perfectMatchingGenerator.generatePerfectMatching(pairwiseGraph, vertexCount);
		mst.combineGraph(mpm);
		
		EulerianWalk eulerianWalk = new EulerianWalk();
		eulerianWalk.generateEulerianWalk(mst);
		ArrayList<Integer> walk = eulerianWalk.getWalk();
		
		ArrayList<Integer> hamiltonianWalk = shortcutEulerianCircuit(walk, vertexCount);
		
		Graph tSPTour = generateGraph(hamiltonianWalk, vertexCount, pairwiseGraph);
		
		return tSPTour;
	}
	
	private ArrayList<Integer> shortcutEulerianCircuit(ArrayList<Integer> eulerianCircuit, int vertexCount) {
		boolean[] nodesVisited = new boolean[vertexCount];
		ArrayList<Integer> hamiltonianCircuit = new ArrayList<Integer>();
		for(int i = 0; i < eulerianCircuit.size(); i++) {
			if(nodesVisited[eulerianCircuit.get(i)]) {
				continue;
			}
			hamiltonianCircuit.add(eulerianCircuit.get(i));
			nodesVisited[eulerianCircuit.get(i)] = true;
		}
		hamiltonianCircuit.add(eulerianCircuit.get(0));
		return hamiltonianCircuit;
	}
	
	private Graph generateGraph(ArrayList<Integer> hamiltonianCircuit, int vertexCount, Graph pairwiseGraph) {
		Graph hamiltonianGraph = new Graph(vertexCount);
		
		for(int i = 1; i < hamiltonianCircuit.size(); i++) {
			int currentVertex = hamiltonianCircuit.get(i);
			int previousVertex = hamiltonianCircuit.get(i-1);
			hamiltonianGraph.addEdge(currentVertex, previousVertex, pairwiseGraph.getEdgeWeight(currentVertex, previousVertex));	
		}
		return hamiltonianGraph;
	}
}
