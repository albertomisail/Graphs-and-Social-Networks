package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyListGraph implements Graph {
	// TODO: Implement this class
	/**
	 * Rep invariant: If a is a vertex of graph and there is an edge from a to b,
	 * then b is a vertex of the graph: If a vertex v is an element of any of the
	 * sets of the value set of adjacenyList, v is part of the key set of
	 * adjacencyList. There is no edge that goes from a vertex to itself: A vertex v
	 * cannot be contained by the set s, where s is the value of the entry whose key
	 * is v
	 */
	private final Map<Vertex, HashSet<Vertex>> adjacencyList;

	public AdjacencyListGraph() {
		adjacencyList = new LinkedHashMap<Vertex, HashSet<Vertex>>();
	}

	public void addVertex(Vertex v) {
		adjacencyList.put(v, new HashSet<Vertex>());
	}

	public void addEdge(Vertex v1, Vertex v2) {
		adjacencyList.get(v1).add(v2);
	}

	public boolean edgeExists(Vertex v1, Vertex v2) {
		return adjacencyList.get(v1).contains(v2);
	}

	public List<Vertex> getVertices() {
		List<Vertex> result = new ArrayList<Vertex>();
		for (Map.Entry<Vertex, HashSet<Vertex>> entry : adjacencyList.entrySet()) {
			result.add(entry.getKey());
		}
		return result;
	}

	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		Set<Vertex> downstream = adjacencyList.get(v);
		List<Vertex> result = new ArrayList<Vertex>();
		for (Vertex neighbor : downstream) {
			result.add(neighbor);
		}		
		return result;
	}

	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		ArrayList<Vertex> result = new ArrayList<Vertex>();
		for (Map.Entry<Vertex, HashSet<Vertex>> entry : adjacencyList.entrySet()) {
			if (entry.getValue().contains(v)) {
				result.add(entry.getKey());
			}
		}
		return result;
	}
}
