package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

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
	 *Perform a complete breadth first search of the given graph starting at a
	 *Returns the smallest x for which there is a path from a to b using x edges of the graph
	 *If no path exists, it throws an exception
	 *
	 * @param graph requires: graph is not null
	 * @param a requires: a is a vertex of graph
	 * @param b requires: b is a vertex of graph
	 * @return minimum x such that it is possible to get from a to b in a path formed with x different edges of the graph
	 * 		   for any value y less than x it is impossible to get from a to b in a path formed with y different edges of the graph
	 * @throws NotFoundException if there is no path that gets from a to b
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) throws NotFoundException {
		// TODO: Implement this method and others
		Map<Vertex, Integer> distances = Algorithms.BFS(graph, a);
		if(!distances.containsKey(b)) {
			throw new NotFoundException();
		}
		return distances.get(b);
	}

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
	 * @param graph requires: graph is not null
	 * @return a set of list of vertices. Each list represents the vertices visited by carrying a DFS in each of graph vertices
	 */
	public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
		//
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
	
	private static Map<Vertex, Integer> BFS(Graph graph, Vertex v){
		Map<Vertex, Integer> result = new LinkedHashMap<Vertex, Integer>();
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
	 
public static boolean directed(Graph graph) {
	boolean undirected = true;
	 int i = 0;
	 while(undirected&&i<graph.getVertices().size()) {
		 Vertex v = graph.getVertices().get(i);
		 int j = i;
		 while(j<graph.getVertices().size()) {
			 Vertex w = graph.getVertices().get(j);
			 System.out.println(v);
			 System.out.println(w);
			 if(graph.edgeExists(v, w)!=graph.edgeExists(w, v)) {
				 undirected = false;
			 }
			 j++;
		 }
		 i++;
	 }
	 return undirected;
}
	 
	private static int calculateEccentricity(Graph graph, Vertex v){
		int eccentricity = 0;
		Map<Vertex, Integer> eccentricities = Algorithms.BFS(graph, v);
		for(Map.Entry<Vertex, Integer> entry : eccentricities.entrySet()) {
			if(entry.getValue()>eccentricity) {
				eccentricity=entry.getValue();
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
