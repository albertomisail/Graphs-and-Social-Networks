package ca.ubc.ece.cpen221.mp3.graph;
import ca.ubc.ece.cpen221.mp3.staff.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyListGraph implements Graph {
// TODO: Implement this class
	private final Map<Vertex,HashSet<Vertex>> adjacencyList;
	/*public List<Vertex> vertexList;
	public List<List<Vertex>> edgeList;*/
	
	public AdjacencyListGraph() {
		adjacencyList = new LinkedHashMap<Vertex, HashSet<Vertex>>();
		/vertexList = new ArrayList<Vertex>();
		edgeList = new ArrayList<List<Vertex>>();
		//assert vertexList.size()==edgeList.size();
	}
	
	public void addVertex(Vertex v) {
		if(!adjacencyList.containsKey(v)) {
			adjacencyList.put(v,new HashSet<Vertex>());
		}
		vertexList.add(v);
		edgeList.add(new ArrayList<Vertex>());
		//assert vertexList.size()==edgeList.size();*/
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		adjacencyList.get(v1).add(v2);
		int index = vertexList.indexOf(v1);
		edgeList.get(index).add(v2);
		//assert vertexList.size()==edgeList.size();*/
	}
	
	public boolean edgeExists(Vertex v1, Vertex v2) {
		return adjacencyList.get(v1).contains(v2);/*
		int index = vertexList.indexOf(v1);
		assert vertexList.size()==edgeList.size();
		return edgeList.get(index).contains(v2);*/
	}
	
	public List<Vertex> getVertices(){
		List<Vertex> result = new ArrayList<Vertex>();
		for (Map.Entry<Vertex, HashSet<Vertex>> entry : adjacencyList.entrySet()) {
			result.add(entry.getKey());
		}
		return result;
		/*assert vertexList.size()==edgeList.size();
		return AdjacencyListGraph.cloneList(vertexList);*/
	}
	
	public List<Vertex> getDownstreamNeighbors(Vertex v){
		Set<Vertex> downstream = adjacencyList.get(v);
		List<Vertex> result = new ArrayList<Vertex>();
		for(Vertex neighbor: downstream) {
			result.add(neighbor);
		}
		return result;
		/*int index = vertexList.indexOf(v);
		assert vertexList.size()==edgeList.size();
		return AdjacencyListGraph.cloneList(edgeList.get(index));*/
	}
	
	public List<Vertex> getUpstreamNeighbors(Vertex v){
		ArrayList<Vertex> result = new ArrayList<Vertex>();
		for (Map.Entry<Vertex, HashSet<Vertex>> entry : adjacencyList.entrySet()) {
			if(entry.getValue().contains(v)) {
				result.add(entry.getKey());
			}
		}
		return result;
		/*ArrayList<Vertex> result = new ArrayList<Vertex>();
		for(int i=0; i<edgeList.size(); i++) {
			if(edgeList.get(i).contains(v)) {
				result.add(vertexList.get(i));
			}
		}
		assert vertexList.size()==edgeList.size();
		return result;*/
	}
	
	private static List<Vertex> cloneList(List<Vertex> list){
		List<Vertex> result = new ArrayList<Vertex>();
		if(list.size()==0) {
			return result;
		}
		result.addAll(list);
		return result;
	}
	
	public Map<Vertex, HashSet<Vertex>> getMap(){
		return adjacencyList;
	}
}
