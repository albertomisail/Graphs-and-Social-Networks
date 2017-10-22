package ca.ubc.ece.cpen221.mp3.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Parsers {

	/**
	 * This method returns a Graph after parsing a file with the corresponding
	 * graph data stored in the same format as the Enron dataset
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
		if(graphRep==1) {
			graph = new AdjacencyListGraph();
		}
		else if(graphRep==2) {
			graph = new AdjacencyMatrixGraph();
		}
		else {
			throw new IOException();
		}
		Scanner sc = new Scanner(new File(fileName));
		while(sc.hasNext()) {
			String line = sc.nextLine();
			if(line.charAt(0)!='#') {
				Vertex v = new Vertex(line.split("\\s+")[0]);
				Vertex w = new Vertex(line.split("\\s+")[1]);
				graph.addVertex(v);
				graph.addVertex(w);
				graph.addEdge(v, w);
			}
		}
		return graph;
	}

	/**
	 * This method returns a Graph after parsing a file with the corresponding
	 * graph data stored in the same format as the Marvel dataset
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
		if(graphRep==1) {
			graph = new AdjacencyListGraph();
		}
		else if(graphRep==2) {
			graph = new AdjacencyMatrixGraph();
		}
		else {
			throw new IOException();
		}
		Scanner sc = new Scanner(new File(fileName));
		String prevComic = "";
		List<Vertex> heroes = new ArrayList<Vertex>();
		while(sc.hasNext()) {
			String line = sc.nextLine();
			String aux1 = line.substring(0, line.length()-1);
			String comic = aux1.substring(aux1.lastIndexOf('"')+1);
			String aux = line.substring(1);
			String name = aux.substring(0, aux.indexOf('"'));
			if(comic.equals(prevComic)) {
				heroes.add(new Vertex(name));
			}
			else {
				prevComic = comic;
				for(Vertex v : heroes) {
					graph.addVertex(v);
				}
				for(Vertex v : heroes) {
					for(Vertex w : heroes) {
						if(!v.equals(w)) {
							graph.addEdge(v, w);
						}
					}
				}
				heroes.clear();
				heroes.add(new Vertex(name));
			}
			
		}
		return graph;
	}

}
