package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Algorithms {

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
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		// TODO: Implement this method and others
		return 0;
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
	 * @param
	 * @return
	 */
	public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
		// TODO: Implement this method
		return null; // this should be changed

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
		return null; // this should be changed
	}

	/**
	 * You should write the spec for this method
	 */
	 public static Vertex center(Graph graph) {
		 // TODO: Implement this method
		 return null; // this should be changed
	 }

	 /**
	  * You should write the spec for this method
		*/
		public static int diameter(Graph graph) {
			// TODO: Implement this method
			return -1; // this should be changed
		}

	/**
	 * You should write the spec for this method
	 */
	 public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b) {
		 // TODO: Implement this method
 		return null; // this should be changed
	 }

	 /**
 	 * You should write the spec for this method
 	 */
 	 public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex a, Vertex b) {
 		 // TODO: Implement this method
  		return null; // this should be changed
 	 }

}
