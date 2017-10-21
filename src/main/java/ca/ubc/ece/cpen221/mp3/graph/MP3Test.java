package ca.ubc.ece.cpen221.mp3.graph;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.*;

import org.junit.Test;

public class MP3Test {
	/*@Test
	public void bla() throws IOException {

		Graph g = Parsers.parseEnronDataset("datasets/enron.txt", 1);
	}*/
	
	@Test
	public void bla2() throws IOException {

		Graph g = Parsers.parseMarvelDataset("datasets/marvel.txt", 1);
	}
	
	@Test
	public void BreathFirstSearch() {
		Graph testAdjacencyList = new AdjacencyListGraph ();
		Vertex v1 = new Vertex ("v1");
		Vertex v2 = new Vertex ("v2");
		Vertex v3 = new Vertex ("v3");
		Vertex v4 = new Vertex ("v4");
		Vertex v5 = new Vertex ("v5");
		Vertex v6 = new Vertex ("v6");
		Vertex v7 = new Vertex ("v7");
		Vertex v8 = new Vertex ("v8");
		testAdjacencyList.addVertex(v1);
		testAdjacencyList.addVertex(v2);
		testAdjacencyList.addVertex(v3);
		testAdjacencyList.addVertex(v4);
		testAdjacencyList.addVertex(v5);
		testAdjacencyList.addVertex(v6);
		testAdjacencyList.addVertex(v7);
		testAdjacencyList.addVertex(v8);
		testAdjacencyList.addEdge(v1, v2);
		testAdjacencyList.addEdge(v1, v3);
		testAdjacencyList.addEdge(v2, v4);
		testAdjacencyList.addEdge(v2, v5);
		testAdjacencyList.addEdge(v3, v6);
		testAdjacencyList.addEdge(v3, v7);
		testAdjacencyList.addEdge(v5, v8);

		Set<List<Vertex>> bfs = new HashSet<List<Vertex>> ();
		System.out.print(Algorithms.breadthFirstSearch(testAdjacencyList));
	}
	
	@Test
	public void test1() throws NotFoundException{
		Vertex v1 = new Vertex("v1");
		Vertex v = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Vertex v4 = new Vertex("v4");
		Graph g1 = new AdjacencyListGraph();
		g1.addVertex(v1);
		g1.addVertex(v);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addVertex(v4);
		g1.addEdge(v1, v2);
		g1.addEdge(v2, v3);
		g1.addEdge(v2, v4);
		assertEquals(0, Algorithms.shortestDistance(g1, v1, v));
		assertEquals(0, Algorithms.shortestDistance(g1, v1, v1));
		assertEquals(1, Algorithms.shortestDistance(g1, v1, v2));
		assertEquals(2, Algorithms.shortestDistance(g1, v1, v3));
		assertEquals(2, Algorithms.shortestDistance(g1, v1, v4));
	}
	
	@Test(expected = NotFoundException.class)
	public void test2() throws NotFoundException{
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Vertex v4 = new Vertex("v4");
		Graph g1 = new AdjacencyListGraph();
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addVertex(v4);
		g1.addEdge(v2, v3);
		System.out.println(Algorithms.shortestDistance(g1, v2, v4));
	}
	
	@Test(expected = InfiniteDiameterException.class)
	public void test3() throws InfiniteDiameterException{
		Vertex v1 = new Vertex("v1");
		Graph g1 = new AdjacencyMatrixGraph();
		g1.addVertex(v1);
		Algorithms.diameter(g1);
	}
	
	@Test(expected = InfiniteDiameterException.class)
	public void test4() throws InfiniteDiameterException{
		Graph g1 = new AdjacencyMatrixGraph();
		Algorithms.diameter(g1);
	}
	
	@Test
	public void test5() throws InfiniteDiameterException, NoCenterException{
		Graph g1 = new AdjacencyMatrixGraph();
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addEdge(v1, v2);
		assertEquals(1,Algorithms.diameter(g1));
		assertEquals(v1,Algorithms.center(g1));
		g1.addEdge(v2, v3);
		g1.addEdge(v3, v1);
		assertEquals(2,Algorithms.diameter(g1));
	}
	
	
}