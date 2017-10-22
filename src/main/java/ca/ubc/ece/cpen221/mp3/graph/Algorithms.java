package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Algorithms {

	/**
	 * *********************** Algorithms ****************************
	 *
	 * Please see the README for this machine problem for a more detailed
	 * specification of the behavior of each method that one should implement.
	 */

	/**
	 * This is provided as an example to indicate that this method and other methods
	 * should be implemented here.
	 *
	 * You should write the specs for this and all other methods.
	 * 
	 * Perform a complete breadth first search of the given graph starting at a
	 * Returns the smallest x for which there is a path from a to b using x edges of
	 * the graph If no path exists, it throws an exception
	 *
	 * @param graph,
	 *            the graph for which the distance of the shortest path between two
	 *            of it vertices is desired requires: graph is not null and
	 *            non-empty
	 * @param a,
	 *            the start vertex of the path requires: a is a vertex of graph
	 * @param b,
	 *            the end of the path requires: b is a vertex of graph
	 * @return minimum x such that it is possible to get from a to b in a path
	 *         formed with x different edges of the graph for any value y less than
	 *         x it is impossible to get from a to b in a path formed with y
	 *         different edges of the graph
	 * @throws NotFoundException
	 *             if there is no path that gets from a to b
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) throws NotFoundException {
		// TODO: Implement this method and others
		// Do a BFS starting at vertex a and recording at what depth is each vertex
		Map<Vertex, Integer> distances = Algorithms.bfsForVertex(graph, a);
		if (!distances.containsKey(b)) {
			// Condition is only true if b was not visited by the BFS of a
			throw new NotFoundException();
		}
		return distances.get(b);
	}

	/**
	 * Perform a complete depth first search of the given graph. Start with the
	 * search at each vertex of the graph and create a list of the vertices visited.
	 * Return a set where each element of the set is a list of elements seen by
	 * starting a DFS at a specific vertex of the graph (the number of elements in
	 * the returned set should correspond to the number of graph vertices).
	 *
	 * @param graph,
	 *            the graph to do the DFS requires: graph is not null
	 * @return a set of list of vertices. Each list represents the vertices visited
	 *         by carrying a DFS in each of graph vertices
	 */
	public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
		Set<List<Vertex>> result = new LinkedHashSet<List<Vertex>>();
		for (Vertex vertex : graph.getVertices()) {
			// Do a DFS starting at each vertex of the graph
			result.add(Algorithms.dfsForVertex(graph, vertex));
		}
		return result; // this should be changed
	}

	/**
	 * Performs a depth first search of the given graph starting at the indicated
	 * vertex
	 * 
	 * @param graph,
	 *            the graph to do the DFS requires: graph is not null and non-empty
	 * @param start,
	 *            the vertex at which the DFS is going to start requires: start is a
	 *            vertex of graph
	 * @return a list of the vertices visited by carrying a DFS starting at start
	 *         The number of elements in this list is equal to the number of
	 *         vertices visited in the DFS
	 */
	private static List<Vertex> dfsForVertex(Graph graph, Vertex start) {
		Stack<Vertex> queue = new Stack<Vertex>();
		List<Vertex> result = new ArrayList<Vertex>();
		queue.push(start);
		while (!queue.isEmpty()) {
			Vertex vertex = queue.pop();
			if (!result.contains(vertex)) {
				result.add(vertex);
				for (Vertex downstreamNeighbors : graph.getDownstreamNeighbors(vertex)) {
					queue.push(downstreamNeighbors);
				}
			}
		}
		return result;
	}

	/**
	 * Perform a complete breadth first search of the given graph. Start with the
	 * search at each vertex of the graph and create a list of the vertices visited.
	 * Return a set where each element of the set is a list of elements seen by
	 * starting a BFS at a specific vertex of the graph (the number of elements in
	 * the returned set should correspond to the number of graph vertices).
	 *
	 * @param graph,
	 *            the graph to do the BFS requires: graph is not null
	 * @return a set of list of vertices. Each list represents the vertices visited
	 *         by carrying a BFS in each of graph vertices
	 */
	public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
		// TODO: Implement this method
		Set<List<Vertex>> result = new LinkedHashSet<List<Vertex>>();
		for (Vertex vertex : graph.getVertices()) {
			// Get a map with each vertex visited and the depth of it in the bfs starting
			// from vertex
			Map<Vertex, Integer> depths = Algorithms.bfsForVertex(graph, vertex);
			List<Vertex> visitedVertices = new ArrayList<Vertex>();
			for (Map.Entry<Vertex, Integer> entry : depths.entrySet()) {
				visitedVertices.add(entry.getKey());
			}
			result.add(visitedVertices);
		}
		return result; // this should be changed
	}

	/**
	 * Performs a breadth first search of the given graph starting at the indicated
	 * vertex
	 * 
	 * @param graph,
	 *            the graph to do the BFS requires: graph is not null and non-empty
	 * @param start,
	 *            the vertex at which the BFS should start requires: start is a
	 *            vertex of graph
	 * @return a map of the vertices visited by carrying a BFS starting at start and
	 *         the depth at which they were found The number of elements in this map
	 *         is equal to the number of vertices visited in the BFS
	 */
	private static Map<Vertex, Integer> bfsForVertex(Graph graph, Vertex start) {
		Map<Vertex, Integer> result = new LinkedHashMap<Vertex, Integer>();
		result.put(start, 0);
		List<Vertex> queue = new ArrayList<Vertex>();
		queue.add(start);
		while (!queue.isEmpty()) {
			Vertex vertex = queue.remove(0);
			for (Vertex downstreamNeighbors : graph.getDownstreamNeighbors(vertex)) {
				if (!result.containsKey(downstreamNeighbors)) {
					queue.add(downstreamNeighbors);
					result.put(downstreamNeighbors, result.get(vertex) + 1);
				}
			}
		}
		return result;
	}

	/**
	 * Calculates the eccentricity for each vertex of the graph. Returns a vertex
	 * for which the eccentricity is minimum
	 * 
	 * @param graph,
	 *            the graph for which it is wanted to calculate the center requires:
	 *            graph is not null and non-empty
	 * @return a vertex v for which the eccentricity of v is less or equal to the
	 *         eccentricity of any other vertex of graph
	 */
	public static Vertex center(Graph graph) {
		// TODO: Implement this method
		Vertex result = graph.getVertices().get(0);
		//The eccentricity of each vertex is always less than the number of vertices + 1, except when it is infinity
		int minEccentricity = graph.getVertices().size() + 1;
		for (Vertex vertex : graph.getVertices()) {
			try{
				int eccentricity = calculateEccentricity(graph, vertex, minEccentricity);
				if (eccentricity < minEccentricity) {
					result = vertex;
					minEccentricity = eccentricity;
				}
			}catch(InfiniteEccentricityException e) {
				
			}
			 
			
		}
		return result; // this should be changed
	}

	private static int calculateEccentricity(Graph graph, Vertex v, int max) throws InfiniteEccentricityException{
		Map<Vertex, Integer> depths = new LinkedHashMap<Vertex, Integer>();
		depths.put(v, 0);
		List<Vertex> queue = new ArrayList<Vertex>();
		queue.add(v);
		int eccentricity = 0;
		while (!queue.isEmpty()) {
			Vertex w = queue.remove(0);
			for (Vertex downstreamNeighbors : graph.getDownstreamNeighbors(w)) {
				if (!depths.containsKey(downstreamNeighbors)) {
					queue.add(downstreamNeighbors);
					int value = depths.get(w) + 1;
					depths.put(downstreamNeighbors, value);
					if (value >= max) {
						return value;
					}
				}
			}
		}
		for (Map.Entry<Vertex, Integer> entry : depths.entrySet()) {
			if (entry.getValue() > eccentricity) {
				eccentricity = entry.getValue();
			}
		}
		if(eccentricity==0) {
			throw new InfiniteEccentricityException();
		}
		return eccentricity;
	}

	/*
	 * private static int calculateEccentricity(Graph graph, Vertex v) { int
	 * eccentricity = 0; Map<Vertex, Integer> eccentricities = Algorithms.BFS(graph,
	 * v); for (Map.Entry<Vertex, Integer> entry : eccentricities.entrySet()) { if
	 * (entry.getValue() > eccentricity) { eccentricity = entry.getValue(); } }
	 * return eccentricity; }
	 */

	/**
	 * You should write the spec for this method
	 */
	public static int diameter(Graph graph) throws InfiniteDiameterException {
		// TODO: Implement this method
		int diameter = 0;
		for (Vertex v : graph.getVertices()) {
			try{
				int eccentricity = Algorithms.calculateEccentricity(graph, v, graph.getVertices().size() + 1);
				if (eccentricity > diameter) {
					diameter = eccentricity;
				}
			}catch(InfiniteEccentricityException e) {
				
			}
		}
		if (diameter == 0) {
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

	private static List<Vertex> commonVertices(List<Vertex> list1, List<Vertex> list2) {
		List<Vertex> result = new ArrayList<Vertex>();
		for (int i = 0; i < list1.size(); i++) {
			Vertex v = list1.get(i);
			if (list2.contains(v)) {
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
