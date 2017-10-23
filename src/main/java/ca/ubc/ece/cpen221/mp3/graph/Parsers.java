package ca.ubc.ece.cpen221.mp3.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Parsers {

	/**
	 * This method returns a Graph after parsing a file with the corresponding graph
	 * data stored in the same format as the Enron dataset
	 * 
	 * @param fileName
	 *            is the name of the file with the dataset
	 * @param graphRep
	 *            is 1 for AdjacencyListGraph and 2 for AdjacencyMatrixGraph
	 * @return a Graph that represents the dataset in fileName and uses the
	 *         representation specified by graphRep
	 * @throws IOException
	 *             if there is a problem opening/reading data from the specified
	 *             file
	 */
	static public Graph parseEnronDataset(String fileName, int graphRep) throws IOException {
		// TODO: Implement this method
		Graph graph;
		if (graphRep == 1) {
			graph = new AdjacencyListGraph();
		} else if (graphRep == 2) {
			graph = new AdjacencyMatrixGraph();
		} else {
			throw new IOException();
		}
		Scanner sc = new Scanner(new File(fileName));
		Map<String, List<String>> conections = new HashMap<String, List<String>>();
		while (sc.hasNext()) {
			String line = sc.nextLine();
			//lines that start with '#' are just information, not the actual data
			if (line.charAt(0) != '#') {
				String from = line.split("\\s+")[0];
				String to = line.split("\\s+")[1];
				if(!conections.containsKey(from)) {
					conections.put(from, new ArrayList<String>());
				}
				conections.get(from).add(to);
			}
		}
		sc.close();
		Map<String, Vertex> equivalent = new HashMap<String, Vertex>();
		for(Map.Entry<String, List<String>> entry : conections.entrySet()) {
			Vertex from;
			if(!equivalent.containsKey(entry.getKey())) {
				from = new Vertex(entry.getKey());
				equivalent.put(entry.getKey(), from);
				graph.addVertex(from);
			}
			else {
				from = equivalent.get(entry.getKey());
			}
			for(String name : entry.getValue()) {
				Vertex to;
				if(!equivalent.containsKey(name)) {
					to = new Vertex(name);
					equivalent.put(name, to);
					graph.addVertex(to);
				}
				else {
					to = equivalent.get(name);
				}
				graph.addEdge(from, to);
			}
		}
		return graph;
	}

	/**
	 * This method returns a Graph after parsing a file with the corresponding graph
	 * data stored in the same format as the Marvel dataset
	 * 
	 * @param fileName
	 *            is the name of the file with the dataset
	 * @param graphRep
	 *            is 1 for AdjacencyListGraph and 2 for AdjacencyMatrixGraph
	 * @return a Graph that represents the dataset in fileName and uses the
	 *         representation specified by graphRep
	 * @throws IOException
	 *             if there is a problem opening/reading data from the specified
	 *             file
	 */
	static public Graph parseMarvelDataset(String fileName, int graphRep) throws IOException {
		// TODO: Implement this method
		Graph graph;
		if (graphRep == 1) {
			graph = new AdjacencyListGraph();
		} else if (graphRep == 2) {
			graph = new AdjacencyMatrixGraph();
		} else {
			throw new IOException();
		}
		Scanner sc = new Scanner(new File(fileName));
		Map<String, List<String>> groups = new HashMap<String, List<String>>();
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String name = line.split("\t")[0];
			String comic = line.split("\t")[1];
			//Keep track of all the heroes in the same comic
			if(!groups.containsKey(comic)) {
				List<String> heros = new ArrayList<String>();
				heros.add(name);
				groups.put(comic, heros);
			}
			else {
				groups.get(comic).add(name);
			}
			
				//Different comic so take all the heroes in the previous comic and add them to the group
				
				
				//Start a blank list of heroes for the new comic
		}
		sc.close();
		Map<String, Vertex> equivalent = new HashMap<String, Vertex>();
		for(Map.Entry<String, List<String>> entry : groups.entrySet()) {
			List<String> group = entry.getValue();
			for(String hero:group) {
				Vertex from;
				if(!equivalent.containsKey(hero)) {
					from = new Vertex(hero);
					equivalent.put(hero, from);
					graph.addVertex(from);
				}
				else {
					from = equivalent.get(hero);
				}
				for(String other:group) {
					Vertex to;
					if(!other.equals(hero)) {
						if(!equivalent.containsKey(other)) {
							to = new Vertex(other);
							equivalent.put(other, to);
							graph.addVertex(to);
						}
						else {
							to=equivalent.get(other);
						}
						graph.addEdge(from, to);
					}
				}
			}
		}
		
		return graph;
	}

}
