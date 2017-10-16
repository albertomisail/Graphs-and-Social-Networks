package ca.ubc.ece.cpen221.mp3.graph;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrixGraph implements Graph {
// TODO: Implement this class
	private List<List<Boolean>> connectionMatrix;
	private List<Vertex> vertexList;
	
	public AdjacencyMatrixGraph() {
		this.connectionMatrix = new ArrayList<List<Boolean>>();
		this.vertexList = new ArrayList<Vertex>();
		assert vertexList.size()==connectionMatrix.size();
		for(int i=0; i<vertexList.size(); i++) {
			assert connectionMatrix.size()==connectionMatrix.get(i).size();
		}
	}
	
	public void addVertex(Vertex v) {
		vertexList.add(v);
		for(int i=0; i<connectionMatrix.size(); i++) {
			connectionMatrix.get(i).add(false);
		}
		List<Boolean> column = new ArrayList<Boolean>();
		for(int i=0; i<vertexList.size(); i++) {
			column.add(false);
		}
		connectionMatrix.add(column);
		assert vertexList.size()==connectionMatrix.size();
		for(int i=0; i<vertexList.size(); i++) {
			assert connectionMatrix.size()==connectionMatrix.get(i).size();
		}
	}
	
	public void addEdge(Vertex v1, Vertex v2) {
		List<Boolean> column = connectionMatrix.get(vertexList.indexOf(v1));
		column.set(vertexList.indexOf(v2), true);
		assert vertexList.size()==connectionMatrix.size();
		for(int i=0; i<vertexList.size(); i++) {
			assert connectionMatrix.size()==connectionMatrix.get(i).size();
		}
	}
	
	public boolean edgeExists(Vertex v1, Vertex v2) {
		List<Boolean> column = connectionMatrix.get(vertexList.indexOf(v1));
		assert vertexList.size()==connectionMatrix.size();
		for(int i=0; i<vertexList.size(); i++) {
			assert connectionMatrix.size()==connectionMatrix.get(i).size();
		}
		return column.get(vertexList.indexOf(v2));
	}
	
	public List<Vertex> getDownstreamNeighbors(Vertex v){
		List<Vertex> result = new ArrayList<Vertex>();
		List<Boolean> column = connectionMatrix.get(vertexList.indexOf(v));
		for(int i=0; i<column.size(); i++) {
			if(column.get(i)) {
				result.add(vertexList.get(i));
			}
		}
		assert vertexList.size()==connectionMatrix.size();
		for(int i=0; i<vertexList.size(); i++) {
			assert connectionMatrix.size()==connectionMatrix.get(i).size();
		}
		return result;
	}
	
	public List<Vertex> getUpstreamNeighbors(Vertex v){
		List<Vertex> result = new ArrayList<Vertex>();
		for(int i=0; i<connectionMatrix.size(); i++) {
			List<Boolean> column = connectionMatrix.get(i);
			if(column.get(vertexList.indexOf(v))) {
				result.add(vertexList.get(i));
			}
		}
		assert vertexList.size()==connectionMatrix.size();
		for(int i=0; i<vertexList.size(); i++) {
			assert connectionMatrix.size()==connectionMatrix.get(i).size();
		}
		return result;
	}
	
	public List<Vertex> getVertices(){
		assert vertexList.size()==connectionMatrix.size();
		for(int i=0; i<vertexList.size(); i++) {
			assert connectionMatrix.size()==connectionMatrix.get(i).size();
		}
		return AdjacencyMatrixGraph.cloneList(vertexList);
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