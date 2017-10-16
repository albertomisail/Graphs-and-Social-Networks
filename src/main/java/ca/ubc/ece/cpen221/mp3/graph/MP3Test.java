package ca.ubc.ece.cpen221.mp3.graph;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class MP3Test {
	@Test(expected = InfiniteDiameterException.class)
	public void test1() throws InfiniteDiameterException{
		Vertex v1 = new Vertex("v1");
		Graph g1 = new AdjacencyListGraph();
		Graph g2 = new AdjacencyMatrixGraph();
		g1.addVertex(v1);
		g2.addVertex(v1);
		Algorithms.diameter(g1);
	}
}