package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.graph.*;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.graph.InfiniteDiameterException;
import ca.ubc.ece.cpen221.mp3.graph.NotFoundException;
import ca.ubc.ece.cpen221.mp3.graph.Parsers;
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

	}

	@Test
	public void test1() {
		System.out.println("Test 1");
		boolean test1 = false;
		boolean test2 = false;
		Vertex v1 = new Vertex("v1");
		Graph a = new AdjacencyMatrixGraph();
		Graph b = new AdjacencyListGraph();

		a.addVertex(v1);
		b.addVertex(v1);

		assertEquals(false, b.edgeExists(v1, v1));
		assertEquals(v1, Algorithms.center(a));

	}

	@Test(expected = InfiniteDiameterException.class)
	public void test2_1() throws InfiniteDiameterException {
		//test for center and diameter of a disconnected graph and matrix rep
		System.out.println("Test 2_1");
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Graph h = new AdjacencyMatrixGraph();
		h.addVertex(v1);
		h.addVertex(v2);
		h.addVertex(v3);
		assertEquals(v1, Algorithms.center(h));
		Algorithms.diameter(h);
	}

	@Test(expected = InfiniteDiameterException.class)
	public void test2_2() throws InfiniteDiameterException {
		//test for center and diameter of a disconnected graph and list rep
		System.out.println("Test 2_2");
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Graph h = new AdjacencyListGraph();
		h.addVertex(v1);
		h.addVertex(v2);
		h.addVertex(v3);
		assertEquals(v1, Algorithms.center(h));
		Algorithms.diameter(h);
	}

	@Test
	public void test3() throws InfiniteDiameterException {
		//Test for diameter and center of a disconnected graph
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

		assertEquals(g1.getVertices().size(), Algorithms.breadthFirstSearch(g1).size());
		assertEquals(g1.getVertices().size(), Algorithms.depthFirstSearch(g1).size());
		assertEquals(Algorithms.depthFirstSearch(g1).size(), Algorithms.breadthFirstSearch(g1).size());

		assertEquals(2, Algorithms.diameter(g1));
		assertEquals(2, Algorithms.diameter(g2));

		List<Vertex> possibleCenter = new ArrayList<Vertex>();
		possibleCenter.add(v2);
		possibleCenter.add(v5);
		assertEquals(true, possibleCenter.contains(Algorithms.center(g1)));

		assertEquals(v2, Algorithms.center(g1));
		assertEquals(v2, Algorithms.center(g2));
	}

	@Test
	public void test4() throws InfiniteDiameterException {
		//tests for a connected graph
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

		assertEquals(3, Algorithms.diameter(g1));
		assertEquals(3, Algorithms.diameter(g2));

		List<Vertex> possibleCenter = new ArrayList<Vertex>();
		possibleCenter.addAll(g1.getVertices());
		possibleCenter.remove(v4);
		assertEquals(true, possibleCenter.contains(Algorithms.center(g1)));

		List<Vertex> commonUpstream1 = new ArrayList<Vertex>();
		commonUpstream1.add(v3);
		assertEquals(commonUpstream1.size(), Algorithms.commonUpstreamVertices(g2, v4, v1).size());

		List<Vertex> commonUpstream2 = new ArrayList<Vertex>();
		assertEquals(commonUpstream2.size(), Algorithms.commonUpstreamVertices(g1, v4, v2).size());

		List<Vertex> commonDownstream1 = new ArrayList<Vertex>();
		commonDownstream1.add(v3);
		assertEquals(commonDownstream1.size(), Algorithms.commonDownstreamVertices(g2, v2, v1).size());

		List<Vertex> commonDownstream2 = new ArrayList<Vertex>();
		assertEquals(commonDownstream2.size(), Algorithms.commonDownstreamVertices(g2, v4, v2).size());
	}

	@Test
	public void test5() throws InfiniteDiameterException {
		//tests for a cyclic graph
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

		assertEquals(2, Algorithms.diameter(g1));
		assertEquals(2, Algorithms.diameter(g2));

		assertEquals(v1, Algorithms.center(g1));
		assertEquals(v1, Algorithms.center(g2));

	}

	@Test
	public void test6() throws InfiniteDiameterException {
		//tests for a connected undirected graph
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

		assertEquals(1, Algorithms.diameter(g1));
		assertEquals(1, Algorithms.diameter(g2));

		List<Vertex> possibleCenter = new ArrayList<Vertex>();
		possibleCenter.addAll(g1.getVertices());
		assertEquals(true, possibleCenter.contains(Algorithms.center(g1)));
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

		assertEquals(2, Algorithms.diameter(g1));
		assertEquals(2, Algorithms.diameter(g2));
		List<Vertex> possibleCenter = new ArrayList<Vertex>();
		possibleCenter.addAll(g1.getVertices());
		assertEquals(true, possibleCenter.contains(Algorithms.center(g1)));

	}

	@Test
	public void test8() throws IOException, InfiniteDiameterException {
		//test for the parsers
		System.out.println("Test 8");
		Graph g = Parsers.parseMarvelDataset("datasets/marvel.txt", 1);
		assertEquals(6445, g.getVertices().size());
		/*System.out.println(Algorithms.diameter(g));
		System.out.println(Algorithms.center(g));*/
	}
	
	@Test
	public void test9() throws IOException, InfiniteDiameterException {
		//test for the parsers
		System.out.println("Test 9");
		Graph g = Parsers.parseEnronDataset("datasets/enron.txt", 1);
		assertEquals(36692, g.getVertices().size());
		/*System.out.println(g.getVertices().size());
		System.out.println(Algorithms.diameter(g));*/
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
		List<Vertex> possibleCenter = new ArrayList<Vertex>();
		possibleCenter.addAll(g1.getVertices());
		assertEquals(true, possibleCenter.contains(Algorithms.center(g1)));
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

	@Test
	public void test12() {
		Vertex v = new Vertex("a");
		v.setLabel(v.getLabel().toUpperCase());
		System.out.println(v);
		Graph g = new AdjacencyListGraph();
		assertEquals(false, v.equals(g));
	}

	@Test(expected = NotFoundException.class)
	public void test13() throws NotFoundException {
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Graph g1 = new AdjacencyMatrixGraph();
		g1.addVertex(v1);
		g1.addVertex(v2);
		Algorithms.shortestDistance(g1, v1, v2);
	}

	@Test
	public void test14() throws NotFoundException {
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Graph g1 = new AdjacencyMatrixGraph();
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v2);
		g1.addEdge(v1, v2);
		assertEquals(true, g1.edgeExists(v1, v2));
		assertEquals(false, g1.edgeExists(v2, v1));
		assertEquals(0, Algorithms.shortestDistance(g1, v1, v1));
		assertEquals(1, Algorithms.shortestDistance(g1, v1, v2));
	}

	@Test(expected = InfiniteDiameterException.class)
	public void test15() throws InfiniteDiameterException {
		Graph g = new AdjacencyMatrixGraph();
		List<Vertex> emptyList = new ArrayList<Vertex>();
		assertEquals(emptyList.size(), g.getVertices().size());
		Algorithms.diameter(g);
	}

	@Test(expected = IOException.class)
	public void test16() throws IOException {
		//test for the parsers
		Graph g = Parsers.parseEnronDataset("datasets/enron2.txt", 2);
		Graph h = Parsers.parseMarvelDataset("datasets/marvel2.txt", 2);
		Graph j = Parsers.parseEnronDataset("datasets/enron.txt", 3);
	}

	@Test(expected = IOException.class)
	public void test17() throws IOException {
		//test for the parsers
		Graph j = Parsers.parseMarvelDataset("datasets/marvel.txt", 3);
	}
}