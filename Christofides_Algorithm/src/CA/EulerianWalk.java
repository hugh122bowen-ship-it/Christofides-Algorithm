package CA;


import java.util.ArrayList;
import java.util.Stack;
/**
 * Generates a closed Eulerian Walk (A closed walk that visits every edge in a graph) given a graph containing an Eulerian walk
 */
public class EulerianWalk {
	private ArrayList<Integer> walk;
	
	
	public EulerianWalk(){
		walk = new ArrayList<Integer>();
	}
	
	
	/**
	 * Generates a list of vertex indexes representing a closed Eulerian walk
	 * 
	 * This method generates the Eulerian walk by:
	 * 1. Pushing vertex 0 onto a stack
	 * 2. Label the current vertex current
	 * 3. Label a vertex connected to current vertex as next
	 * 4. If the current vertex has no unvisited connected vertices, then pop the stack and add current vertex to the walk
	 * 5. Repeat step 2-4 until the stack is empty
	 * 
	 * @param eulerianGraph Graph containing a closed Eulerian walk
	 */
	public void generateEulerianWalk(GraphList eulerianGraph) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		
		int current;
		int next;
		while(stack.size() != 0) {
			current = stack.peek();
			next = eulerianGraph.getUnusedVertex(current);
			if(next != -1) {
				stack.push(next);
			}else {
				walk.add(stack.pop());
			}
		}
		
	}
	
	public ArrayList<Integer> getWalk() {
		return walk;
	}
}
