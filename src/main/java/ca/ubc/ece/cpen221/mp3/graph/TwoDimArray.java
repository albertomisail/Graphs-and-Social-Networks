package ca.ubc.ece.cpen221.mp3.graph;
import ca.ubc.ece.cpen221.mp3.staff.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoDimArray {
	private final int size;
	private boolean[][] array;
	
	public TwoDimArray(AdjacencyListGraph graph) {
		size = graph.getMap().keySet().size();
		array = new boolean[size][size];
		
		
		
		
		
		
		
		Object[] vertices = graph.getMap().keySet().toArray();
		for(Map.Entry<Vertex, HashSet<Vertex>> entry : graph.getMap().entrySet()) {
			Set<Vertex> endVertices = entry.getValue();
			Vertex startVertex = entry.getKey();
			for(Vertex endVertex : endVertices) {
				graph.getMap().keySet().
			}
		}
	}
}
