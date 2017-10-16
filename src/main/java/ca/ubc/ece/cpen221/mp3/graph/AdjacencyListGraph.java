package ca.ubc.ece.cpen221.mp3.graph;
import ca.ubc.ece.cpen221.mp3.staff.*;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyListGraph implements Graph {
// TODO: Implement this class
	private List<Vertex> vertexList;
	private List<List<Vertex>> edgeList;
	
	public AdjacencyListGraph() {
		this.vertexList = new ArrayList<Vertex>();
		this.edgeList = new ArrayList<List<Vertex>>();
	}
	
	public void addVertex(Vertex v) {
		vertexList.add(v);
		edgeList.add(new ArrayList<Vertex>());
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		int index = vertexList.indexOf(v1);
		edgeList.get(index).add(v2);
	}
	
	public boolean edgeExists(Vertex v1, Vertex v2) {
		int index = vertexList.indexOf(v1);
		return edgeList.get(index).contains(v2);
	}
	
	public List<Vertex> getVertices(){
		return AdjacencyListGraph.cloneList(vertexList);
	}
	
	public List<Vertex> getDownstreamNeighbors(Vertex v){
		int index = vertexList.indexOf(v);
		return AdjacencyListGraph.cloneList(edgeList.get(index));
	}
	
	public List<Vertex> getUpstreamNeighbors(Vertex v){
		ArrayList<Vertex> result = new ArrayList<Vertex>();
		for(int i=0; i<edgeList.size(); i++) {
			if(edgeList.get(i).contains(v)) {
				result.add(vertexList.get(i));
			}
		}
		return result;
	}
	
	private static List<Vertex> cloneList(List<Vertex> list){
		List<Vertex> result = new ArrayList<Vertex>();
		if(list.size()==0) {
			return result;
		}
		result.addAll(list);
		return result;
	}
}
