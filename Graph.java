package assign07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;



/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set
 * of edges). The graph is not generic and assumes that a string name is stored
 * at each vertex.
 * 
 * @author Eric Heisler
 * @version 2025-6-17
 */
public class Graph<Type> {

	// the graph -- a set of vertices (String name mapped to Vertex instance)
	private HashMap<String, Vertex> vertices;

	private int numOfVertices;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<String, Vertex>();

	}

	/**
	 * 
	 * @return - the number of vertices
	 */
	public int size() {
		return vertices.size();
	}

	/**
	 * Adds to the graph a directed edge from the vertex with name "name1" to the
	 * vertex with name "name2". (If either vertex does not already exist in the
	 * graph, it is added.)
	 * 
	 * @param name1 - string name for source vertex
	 * @param name2 - string name for destination vertex
	 */
	public void addEdge(String name1, String name2) {
		Vertex vertex1;
		// if vertex already exists in graph, get its object
		if (vertices.containsKey(name1)) {
			vertex1 = vertices.get(name1);
		}
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex(name1);
			vertices.put(name1, vertex1);
		}

		Vertex vertex2;
		// do the same for vertex2
		if (vertices.containsKey(name2)) {
			vertex2 = vertices.get(name2);
		} else {
			vertex2 = new Vertex(name2);
			vertices.put(name2, vertex2);
		}

		// add new directed edge from vertex1 to vertex2
		vertex1.addEdge(vertex2);
	}

	/**
	 * Generates a simple textual representation of this graph.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();

		for (String str : vertices.keySet()) {
			result.append(vertices.get(str) + "\n");
		}

		return result.toString();
	}

	public boolean depthFirstSearch(Type source, Type destination) {

		Vertex sourceVertex = vertices.get(source);
		Vertex destVertex = vertices.get(destination);
		
		
		for (Vertex vertex : vertices.values()) {
			vertex.visited = false;
		}
		
		if (sourceVertex == null || destVertex == null) {
			return false;
		}
		
		return depthFirstSearchHelper(sourceVertex, destVertex);

			

		
	}
	
	private boolean depthFirstSearchHelper(Vertex source, Vertex destination) {
		if (source.equals(destination)) {
			return true;
		}
		for (Vertex vertex : source.edges) {
			if (!vertex.visited) {
				if (depthFirstSearchHelper(vertex, destination)) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Vertex> breadthFirstSearch(Type source, Type destination) {
		Vertex sourceVertex = vertices.get(source);
		Vertex destVertex = vertices.get(destination);
		
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		queue.offer(sourceVertex);
		
		while (!queue.isEmpty()) {
			Vertex current = queue.poll();

			if (current.equals(destVertex)) {
				break;
			}
			for (Vertex edge : current.edges) {
				if (!edge.visited) {
					edge.visited();
					edge.cameFrom = current; 
					queue.offer(edge);
				}
			}
		}
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		if (destVertex.visited) {
			for (Vertex vertex = destVertex; vertex != null; vertex = vertex.cameFrom) {
				path.addFirst(vertex);
			}
		}
		return path;
	}
	


	public List<Vertex> topoSort() {

	}

	private class Vertex {
		private boolean visited;
		private Type data;
		private String name;
		private ArrayList<Vertex> edges;
		private Vertex cameFrom;

		public Vertex(Type data, String name) {
			this.cameFrom = null;
			this.visited = false;
			this.data = data;
			this.name = name;
			this.edges = new ArrayList<Vertex>();
		}

		public Vertex(String name) {
			this.cameFrom = null;
			this.visited = false;
			this.data = null;
			this.name = name;
			this.edges = new ArrayList<Vertex>();
		}

		public void visited() {
			this.visited = true;
		}

		public void addEdge(Vertex toVertex) {
			edges.add(toVertex);
		}

		public Type getData() {
			return data;
		}

		public void removeEdge(Vertex vertexToRemove) {
			if (edges.contains(vertexToRemove)) {
				edges.remove(vertexToRemove);
			}
		}
	}

}