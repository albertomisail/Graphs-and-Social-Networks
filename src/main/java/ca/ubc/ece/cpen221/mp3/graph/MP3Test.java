package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.*;

import org.junit.Test;

public class MP3Test {

	@Test
	public void test0() throws InfiniteDiameterException {
		System.out.println("Test 0");
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Vertex v4 = new Vertex("v4");
		Vertex v5 = new Vertex("v5");
		Vertex v6 = new Vertex("v6");

		Graph g1 = new AdjacencyListGraph();
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addVertex(v4);
		g1.addVertex(v5);
		g1.addVertex(v6);
		g1.addEdge(v1, v2);
		g1.addEdge(v1, v4);
		g1.addEdge(v2, v3);
		g1.addEdge(v3, v4);
		g1.addEdge(v4, v5);
		g1.addEdge(v2, v6);

		assertEquals(3, Algorithms.diameter(g1));

		System.out.println(Algorithms.center(g1));

	}

	@Test
	public void test1() {
		System.out.println("Test 1");
		Vertex v1 = new Vertex("v1");
		Graph a = new AdjacencyMatrixGraph();
		Graph b = new AdjacencyListGraph();
		System.out.println(Algorithms.breadthFirstSearch(a));
		System.out.println(Algorithms.breadthFirstSearch(b));
		System.out.println(Algorithms.depthFirstSearch(a));
		System.out.println(Algorithms.depthFirstSearch(b));
		a.addVertex(v1);
		b.addVertex(v1);
		System.out.println(Algorithms.breadthFirstSearch(a));
		System.out.println(Algorithms.breadthFirstSearch(b));
		System.out.println(Algorithms.depthFirstSearch(a));
		System.out.println(Algorithms.depthFirstSearch(b));
		try {
			Algorithms.diameter(a);
		} catch (InfiniteDiameterException e) {
			System.out.println("Pass");
		}
		try {
			Algorithms.diameter(b);
		} catch (InfiniteDiameterException e) {
			System.out.println("Pass");
		}
		assertEquals(v1, Algorithms.center(a));
	}

	@Test
	public void test2() {
		System.out.println("Test 2");
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Graph g = new AdjacencyMatrixGraph();
		Graph h = new AdjacencyListGraph();
		g.addVertex(v1);
		g.addVertex(v2);
		g.addVertex(v3);
		h.addVertex(v1);
		h.addVertex(v2);
		h.addVertex(v3);
		System.out.println(Algorithms.breadthFirstSearch(g));
		System.out.println(Algorithms.breadthFirstSearch(h));
		System.out.println(Algorithms.depthFirstSearch(g));
		System.out.println(Algorithms.depthFirstSearch(h));
		try {
			Algorithms.diameter(g);
		} catch (InfiniteDiameterException e) {
			System.out.println("Pass");
		}
		try {
			Algorithms.diameter(h);
		} catch (InfiniteDiameterException e) {
			System.out.println("Pass");
		}
		assertEquals(v1, Algorithms.center(g));
	}

	@Test
	public void test3() throws InfiniteDiameterException {
		System.out.println("Test 3");
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Vertex v4 = new Vertex("v4");
		Vertex v5 = new Vertex("v5");
		Vertex v6 = new Vertex("v6");

		Graph g1 = new AdjacencyMatrixGraph();
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addVertex(v4);
		g1.addVertex(v5);
		g1.addVertex(v6);
		g1.addEdge(v1, v2);
		g1.addEdge(v1, v3);
		g1.addEdge(v2, v3);
		g1.addEdge(v2, v4);
		g1.addEdge(v5, v6);

		Graph g2 = new AdjacencyListGraph();
		g2.addVertex(v1);
		g2.addVertex(v2);
		g2.addVertex(v3);
		g2.addVertex(v4);
		g2.addVertex(v5);
		g2.addVertex(v6);
		g2.addEdge(v1, v2);
		g2.addEdge(v1, v3);
		g2.addEdge(v2, v3);
		g2.addEdge(v2, v4);
		g2.addEdge(v5, v6);

		System.out.println(Algorithms.breadthFirstSearch(g1));
		System.out.println(Algorithms.breadthFirstSearch(g2));
		System.out.println(Algorithms.depthFirstSearch(g1));
		System.out.println(Algorithms.depthFirstSearch(g2));

		assertEquals(2, Algorithms.diameter(g1));
		assertEquals(2, Algorithms.diameter(g2));

		assertEquals(v2, Algorithms.center(g1));
		assertEquals(v2, Algorithms.center(g2));
	}

	@Test
	public void test4() throws InfiniteDiameterException {
		System.out.println("Test 4");
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Vertex v4 = new Vertex("v4");

		Graph g1 = new AdjacencyMatrixGraph();
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addVertex(v4);
		g1.addEdge(v1, v2);
		g1.addEdge(v1, v3);
		g1.addEdge(v3, v1);
		g1.addEdge(v2, v3);
		g1.addEdge(v3, v4);
		g1.addEdge(v4, v2);

		Graph g2 = new AdjacencyListGraph();
		g2.addVertex(v1);
		g2.addVertex(v2);
		g2.addVertex(v3);
		g2.addVertex(v4);
		g2.addEdge(v1, v2);
		g2.addEdge(v1, v3);
		g2.addEdge(v3, v1);
		g2.addEdge(v2, v3);
		g2.addEdge(v3, v4);
		g2.addEdge(v4, v2);

		System.out.println(Algorithms.breadthFirstSearch(g1));
		System.out.println(Algorithms.breadthFirstSearch(g2));
		System.out.println(Algorithms.depthFirstSearch(g1));
		System.out.println(Algorithms.depthFirstSearch(g2));

		assertEquals(3, Algorithms.diameter(g1));
		assertEquals(3, Algorithms.diameter(g2));

		System.out.println(Algorithms.center(g1));
		System.out.println(Algorithms.center(g2));
	}

	@Test
	public void test5() throws InfiniteDiameterException {
		System.out.println("Test 5");
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");

		Graph g1 = new AdjacencyMatrixGraph();
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addEdge(v2, v1);
		g1.addEdge(v1, v3);

		Graph g2 = new AdjacencyListGraph();
		g2.addVertex(v1);
		g2.addVertex(v2);
		g2.addVertex(v3);
		g2.addEdge(v2, v1);
		g2.addEdge(v1, v3);

		System.out.println(Algorithms.breadthFirstSearch(g1));
		System.out.println(Algorithms.breadthFirstSearch(g2));
		System.out.println(Algorithms.depthFirstSearch(g1));
		System.out.println(Algorithms.depthFirstSearch(g2));

		assertEquals(2, Algorithms.diameter(g1));
		assertEquals(2, Algorithms.diameter(g2));

		assertEquals(v1, Algorithms.center(g1));
		assertEquals(v1, Algorithms.center(g2));

	}

	@Test
	public void test6() throws InfiniteDiameterException {
		System.out.println("Test 6");
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");

		Graph g1 = new AdjacencyMatrixGraph();
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addEdge(v1, v2);
		g1.addEdge(v2, v1);

		Graph g2 = new AdjacencyListGraph();
		g2.addVertex(v1);
		g2.addVertex(v2);
		g2.addEdge(v1, v2);
		g2.addEdge(v2, v1);

		System.out.println(Algorithms.breadthFirstSearch(g1));
		System.out.println(Algorithms.breadthFirstSearch(g2));
		System.out.println(Algorithms.depthFirstSearch(g1));
		System.out.println(Algorithms.depthFirstSearch(g2));

		assertEquals(1, Algorithms.diameter(g1));
		assertEquals(1, Algorithms.diameter(g2));

		System.out.println(Algorithms.center(g1));

		// assertEquals(true, Algorithms.directed(g1));
	}

	@Test
	public void test7() throws InfiniteDiameterException {
		System.out.println("Test 7");
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");

		Graph g1 = new AdjacencyMatrixGraph();
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addEdge(v1, v2);
		g1.addEdge(v2, v3);
		g1.addEdge(v3, v1);

		Graph g2 = new AdjacencyListGraph();
		g2.addVertex(v1);
		g2.addVertex(v2);
		g2.addVertex(v3);
		g2.addEdge(v1, v2);
		g2.addEdge(v2, v3);
		g2.addEdge(v3, v1);

		System.out.println(Algorithms.breadthFirstSearch(g1));
		System.out.println(Algorithms.breadthFirstSearch(g2));
		System.out.println(Algorithms.depthFirstSearch(g1));
		System.out.println(Algorithms.depthFirstSearch(g2));

		assertEquals(2, Algorithms.diameter(g1));
		assertEquals(2, Algorithms.diameter(g2));

		System.out.println(Algorithms.center(g1));

		// assertEquals(false, Algorithms.directed(g1));
	}

	@Test
	public void test8() throws IOException, InfiniteDiameterException {
		System.out.println("Test 8");
		Graph g = Parsers.parseMarvelDataset("datasets/marvel.txt", 1);
		System.out.println(Algorithms.diameter(g));
		System.out.println(Algorithms.center(g));
		// assertEquals(true, Algorithms.directed(g));
	}

	@Test
	public void test9() throws IOException, InfiniteDiameterException {
		System.out.println("Test 9");
		Graph g = Parsers.parseEnronDataset("datasets/enron.txt", 1);
		System.out.println(Algorithms.diameter(g));
		// assertEquals(true, Algorithms.directed(g));
	}

	@Test
	public void test10() throws InfiniteDiameterException {
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");

		Graph g1 = new AdjacencyMatrixGraph();
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addEdge(v1, v2);
		g1.addEdge(v2, v3);
		g1.addEdge(v2, v1);

		assertEquals(2, Algorithms.diameter(g1));
		assertEquals(v2, Algorithms.center(g1));
	}

	@Test
	public void test11() {
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Graph g1 = new AdjacencyMatrixGraph();
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addEdge(v1, v3);
		g1.addEdge(v2, v1);

		assertEquals(v1, Algorithms.center(g1));
	}

}