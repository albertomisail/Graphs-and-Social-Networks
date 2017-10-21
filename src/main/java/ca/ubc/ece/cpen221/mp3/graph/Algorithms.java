package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import cosineDocumentSimilarity.Document;

public class Algorithms{
	
	/**
	 * *********************** Algorithms ****************************
	 *
	 * Please see the README for this machine problem for a more detailed
	 * specification of the behavior of each method that one should
	 * implement.
	 */

	/**
	 * This is provided as an example to indicate that this method and
	 * other methods should be implemented here.
	 *
	 * You should write the specs for this and all other methods.
	 *
	 * @param graph
	 * @param a
	 * @param b
	 * @return
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) throws NotFoundException {
		// TODO: Implement this method and others
		/*if (a.equals(b)) {
			return 0;
		} else {
			return shortestDistance(graph, a, b, 1);
		}*/
		Map<Vertex, Integer> distances = Algorithms.BFS(graph, a);
		if(!distances.containsKey(b)) {
			throw new NotFoundException();
		}
		return distances.get(b);
	}

	/*private static int shortestDistance(Graph graph, Vertex a, Vertex b, int k) throws NotFoundException {
		List<Vertex> downstreamNeighbors = graph.getDownstreamNeighbors(a);
		int result = -1;// ask
		if (k >= graph.getVertices().size() || downstreamNeighbors.size()==0) {
			throw new NotFoundException();
		}
		if (downstreamNeighbors.contains(b)) {
			result = k;
		} else {
			for (int i = 0; i < downstreamNeighbors.size(); i++) {
				result = shortestDistance(graph, downstreamNeighbors.get(i), b, k + 1);
			}
		}
		return result;
	}*/

	/**
	 * Perform a complete depth first search of the given
	 * graph. Start with the search at each vertex of the
	 * graph and create a list of the vertices visited.
	 * Return a set where each element of the set is a list
	 * of elements seen by starting a DFS at a specific
	 * vertex of the graph (the number of elements in the
	 * returned set should correspond to the number of graph
	 * vertices).
	 *
	 * @param
	 * @return
	 */
	public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
		// TODO: Implement this method
		Set<List<Vertex>> result = new HashSet<List<Vertex>>();
		for(Vertex v : graph.getVertices()) {
			result.add(Algorithms.DFS(graph, v));
		}
		return result; // this should be changed

	}
	
	private static List<Vertex> DFS(Graph graph, Vertex v){
		Stack<Vertex> queue = new Stack<Vertex>();
		List<Vertex> result = new ArrayList<Vertex>();
		queue.push(v);
		while(!queue.isEmpty()) {
			Vertex w = queue.pop();
			if(!result.contains(w)) {
				result.add(w);
				for(Vertex downstreamNeighbors : graph.getDownstreamNeighbors(w)) {
					queue.push(downstreamNeighbors);
				}
			}
		}
		return result;
	}

	/**
	 * Perform a complete breadth first search of the given
	 * graph. Start with the search at each vertex of the
	 * graph and create a list of the vertices visited.
	 * Return a set where each element of the set is a list
	 * of elements seen by starting a BFS at a specific
	 * vertex of the graph (the number of elements in the
	 * returned set should correspond to the number of graph
	 * vertices).
	 *
	 * @param
	 * @return
	 */
	public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
		// TODO: Implement this method
		Set<List<Vertex>> result = new HashSet<List<Vertex>>();
		for(Vertex v : graph.getVertices()) {
			Map<Vertex, Integer> bfs = Algorithms.BFS(graph, v);
			List<Vertex> visitedVertices = new ArrayList<Vertex>();
			for (Map.Entry<Vertex, Integer> entry : bfs.entrySet()) {
				visitedVertices.add(entry.getKey());
			}
			result.add(visitedVertices);
		}
		return result; // this should be changed
	}
	
	/*private static List<Vertex> BFS(Graph graph, Vertex v){
		List<Vertex> result = new ArrayList<Vertex>();
		result.add(v);
		List<Vertex> queue = new ArrayList<Vertex>();
		queue.add(v);
		while(!queue.isEmpty()) {
			Vertex w = queue.remove(0);
			for(Vertex downstreamNeighbors : graph.getDownstreamNeighbors(w)) {
				if(!result.contains(downstreamNeighbors)) {
					queue.add(downstreamNeighbors);
					result.add(downstreamNeighbors);
				}
			}
		}
		return result;
	}*/
	
	private static Map<Vertex, Integer> BFS(Graph graph, Vertex v){
		Map<Vertex, Integer> result = new HashMap<Vertex, Integer>();
		result.put(v, 0);
		List<Vertex> queue = new ArrayList<Vertex>();
		queue.add(v);
		while(!queue.isEmpty()) {
			Vertex w = queue.remove(0);
			for(Vertex downstreamNeighbors : graph.getDownstreamNeighbors(w)) {
				if(!result.containsKey(downstreamNeighbors)) {
					queue.add(downstreamNeighbors);
					result.put(downstreamNeighbors, result.get(w)+1);
				}
			}
		}
		System.out.println(result);
		return result;
	}

	/**
	 * You should write the spec for this method
	 */
	 public static Vertex center(Graph graph) throws NoCenterException{
		 // TODO: Implement this method
		 Map<Vertex, Integer> eccentricities = new HashMap<Vertex, Integer>();
		 for (int i = 0; i < graph.getVertices().size(); i++) {
		 	Vertex v = graph.getVertices().get(i);
			int eccentricity = calculateEccentricity(graph, v);
			if(eccentricity!=0) {
				eccentricities.put(v, eccentricity);
			}
		 }
		 if (eccentricities.size() == 0) {
				throw new NoCenterException();
			}
			int min_eccentricity = Integer.MAX_VALUE;
			Vertex center = null;
			for (Map.Entry<Vertex, Integer> entry : eccentricities.entrySet()) {
				if (entry.getValue() <= min_eccentricity) {
					min_eccentricity = entry.getValue();
					center = entry.getKey();
				}
			}
		 return center; // this should be changed
	 }
		 
	public static int calculateEccentricity(Graph graph, Vertex v){
		int eccentricity = 0;
		for (int i = 0; i < graph.getVertices().size(); i++) {
			Vertex w = graph.getVertices().get(i);
			try {
				int distance = shortestDistance(graph, v, w);
				if (distance > eccentricity) {
					eccentricity = distance;
				}
			}catch(NotFoundException e) {
				
			}
		}
		return eccentricity;
	}

	 /**
	  * You should write the spec for this method
		*/
	public static int diameter(Graph graph) throws InfiniteDiameterException{
		// TODO: Implement this method
		int diameter = 0;
		List<Vertex> notVisited = graph.getVertices();
		while(!notVisited.isEmpty()) {
			Vertex v = notVisited.get(0);
			notVisited.remove(v);
			Map<Vertex, Integer> bfs = Algorithms.BFS(graph, v);
			int max = 0;
			for(Map.Entry<Vertex, Integer> e : bfs.entrySet()) {
				if(max<e.getValue()) {
					max = e.getValue();
				}
			}
			for(Map.Entry<Vertex, Integer> e : bfs.entrySet()) {
				if(max>e.getValue()) {
					notVisited.remove(e.getKey());
				}
			}
			if(max>diameter) {
				diameter=max;
			}
		}
		if(diameter==0) {
			throw new InfiniteDiameterException();
		}
		return diameter;
		/*int diameter = 0;
		for(int i=0; i<graph.getVertices().size(); i++) {
			Vertex v = graph.getVertices().get(i);
			for(int j=0; j<graph.getVertices().size(); j++) {
				Vertex w = graph.getVertices().get(j);
				try{
					if(diameter<Algorithms.shortestDistance(graph, v, w)) {
						diameter = Algorithms.shortestDistance(graph, v, w);
					}
				}catch(NotFoundException e) {
					
				}
			}
		}
		if(diameter==0) {
			throw new InfiniteDiameterException();
		}
		return diameter; */// this should be changed
	}
	
	

	/**
	 * You should write the spec for this method
	 */
	 public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b) {
		// TODO: Implement this method
		List<Vertex> upstreamVerticesA = graph.getUpstreamNeighbors(a);
		List<Vertex> upstreamVerticesB = graph.getUpstreamNeighbors(b);
		return commonVertices(upstreamVerticesA, upstreamVerticesB); // this should be changed
	 }

	 
	private static List<Vertex> commonVertices(List<Vertex> list1, List<Vertex> list2){
		List<Vertex> result = new ArrayList<Vertex>();
		for(int i=0; i<list1.size(); i++) {
			Vertex v = list1.get(i);
			if(list2.contains(v)) {
				result.add(v);
			}
		}
		return result;
	}
	/**
 	 * You should write the spec for this method
 	 */
 	 public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex a, Vertex b) {
 		// TODO: Implement this method
 		List<Vertex> downstreamVerticesA = graph.getDownstreamNeighbors(a);
		List<Vertex> downstreamVerticesB = graph.getDownstreamNeighbors(b);
		return commonVertices(downstreamVerticesA, downstreamVerticesB);
 	 }

}
