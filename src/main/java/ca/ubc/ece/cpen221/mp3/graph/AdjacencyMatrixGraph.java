package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrixGraph implements Graph {
	// TODO: Implement this class
	/**
	 * Rep invariant: If a is a vertex of graph and there is an edge from a to b,
	 * then b is a vertex of the graph: The size of vertex list is equal to the size
	 * of connection matrix The size of each list of connection matrix is equal to
	 * the size of connection matrix There is no edge that goes from a vertex to
	 * itself: If i is any integer between 0 and connectionMatrix.size()-1
	 * (included) connectionMatrix.get(i).get(i) is false
	 */
	private final List<List<Boolean>> connectionMatrix;
	private final List<Vertex> vertexList;

	public AdjacencyMatrixGraph() {
		connectionMatrix = new ArrayList<List<Boolean>>();
		vertexList = new ArrayList<Vertex>();
	}

	public void addVertex(Vertex v) {
		vertexList.add(v);
		//add false to whether there is an edge from the any other vertex to v
		for (int i = 0; i < connectionMatrix.size(); i++) {
			connectionMatrix.get(i).add(false);
		}
		//add the a new column with the connection from v to all the other vertices
		List<Boolean> column = new ArrayList<Boolean>();
		for (int i = 0; i < vertexList.size(); i++) {
			column.add(false);
		}
		connectionMatrix.add(column);
	}

	public void addEdge(Vertex v1, Vertex v2) {
		List<Boolean> column = connectionMatrix.get(vertexList.indexOf(v1));
		column.set(vertexList.indexOf(v2), true);
	}

	public boolean edgeExists(Vertex v1, Vertex v2) {
		List<Boolean> column = connectionMatrix.get(vertexList.indexOf(v1));
		return column.get(vertexList.indexOf(v2));
	}

	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		List<Vertex> result = new ArrayList<Vertex>();
		List<Boolean> column = connectionMatrix.get(vertexList.indexOf(v));
		for (int i = 0; i < column.size(); i++) {
			if (column.get(i)) {
				result.add(vertexList.get(i));
			}
		}
		return result;
	}

	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		List<Vertex> result = new ArrayList<Vertex>();
		for (int i = 0; i < connectionMatrix.size(); i++) {
			List<Boolean> column = connectionMatrix.get(i);
			if (column.get(vertexList.indexOf(v))) {
				result.add(vertexList.get(i));
			}
		}
		return result;
	}

	public List<Vertex> getVertices() {
		//defensive copying to not give back the actual list of vertices
		return AdjacencyMatrixGraph.cloneList(vertexList);
	}
	
	private static List<Vertex> cloneList(List<Vertex> list) {
		List<Vertex> result = new ArrayList<Vertex>();
		if (list.size() == 0) {
			return result;
		}
		result.addAll(list);
		return result;
	}
}