package assign07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set
 * of edges). The graph is not generic and assumes that a string name is stored
 * at each vertex. for summer 2026 CS2420 assignment 07
 * 
 * @author Jon Moses and Jaron Burton
 * @version 2026-6-17
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
	public void addVertex(String name, Type data) {
		if (!vertices.containsKey(name)) {
			vertices.put(name, new Vertex(data, name));
		}
	}

	/**
	 * checks if a vertex with the given name exists in the graph.
	 * 
	 * @param name - string that is the vertex name
	 * @return - true if a vertex with the name exists in the graph, false
	 *         otherwise.
	 */
	public boolean containsVertex(String name) {
		return vertices.containsKey(name);
	}

	/**
	 * Adds an edge and creates the vertices if they don't exist in the graph
	 * already
	 * 
	 * @param name1 - String that is the name of the vertex, used as the key in the
	 *              vertices hashmap and to construct a new Vertex.
	 * @param name2 - String that is the name of the vertex, used as the key in the
	 *              vertices hashmap and to construct a new Vertex.
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
	 * add edge method that takes ints so that the timing experiment runs.
	 *
	 * @param sourceVertexData      - integer name for source vertex
	 * @param destinationVertexData - integer name for destination vertex
	 */
	public void addEdge(int sourceVertexData, int destinationVertexData) {
		addEdge(Integer.toString(sourceVertexData), Integer.toString(destinationVertexData));
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

	/**
	 * Calls on the dfshelper method to perform DFS after checking the validity of
	 * the source and destination vertex. uses the helper to recursively search the
	 * graph.
	 * 
	 * @param source      - any value stored in a vertex in the graph
	 * @param destination - any value stored in a vertex in the graph
	 * @return - true if a path exists, false otherwise
	 */
	public boolean depthFirstSearch(Type source, Type destination) {

		Vertex sourceVertex = vertices.get(source.toString());
		Vertex destVertex = vertices.get(destination.toString());

		for (Vertex vertex : vertices.values()) {
			vertex.visited = false;
		}

		if (sourceVertex == null || destVertex == null) {
			return false;
		}

		return depthFirstSearchHelper(sourceVertex, destVertex);

	}

	/**
	 * Performs depth first search recursively to find a path from the source vertex
	 * to the destination vertex. converts the <Type> from the main depthFirstSearch
	 * method to <Vertex> to make it easier to work with.
	 * 
	 * @param source      - Any vertex in the graph
	 * @param destination - any vertex in the graph
	 * @return true if the path exists, false otherwise
	 */
	private boolean depthFirstSearchHelper(Vertex source, Vertex destination) {
		source.visited = true;

		if (source == destination)
			return true;

		for (Vertex vertex : source.edges) {
			if (!vertex.visited) {
				if (depthFirstSearchHelper(vertex, destination))
					return true;
			}
		}

		return false;
	}

	/**
	 * Performs breadthfirstSearch non-recursively
	 * 
	 * @param source      - Any value that is held by a vertex in this graph
	 * @param destination - Any value that is held by a vertex in the graph
	 * @return - A list that is the shortest path from source to destination.
	 */
	public List<Type> breadthFirstSearch(Type source, Type destination) {
		Vertex sourceVertex = vertices.get(source.toString());
		Vertex destVertex = vertices.get(destination.toString());

		if (sourceVertex == null || destVertex == null) {
			return new LinkedList<Type>();
		}

		for (Vertex vertex : vertices.values()) {
			vertex.visited = false;
			vertex.cameFrom = null;
		}

		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		sourceVertex.visited();
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
		LinkedList<Type> path = new LinkedList<Type>();
		if (destVertex.visited) {
			for (Vertex vertex = destVertex; vertex != null; vertex = vertex.cameFrom) {
				path.addFirst(vertex.data);
			}
		}

		return path;
	}

	/**
	 * Topologically sort the graph and return a list in the order.
	 * 
	 * @return - a list that is the topoligically sorted list of all the vertices in
	 *         the graph if such a sorting exists. will return an array that is
	 *         smaller than the size of the graph if a cycle exists.
	 */
	public List<Type> topoSort() {
		HashMap<Vertex, Integer> inDegree = new HashMap<Vertex, Integer>();
		for (Vertex vertex : vertices.values()) {
			inDegree.put(vertex, 0);
		}
		for (Vertex vertex : vertices.values()) {
			for (Vertex edge : vertex.edges) {
				inDegree.put(edge, inDegree.get(edge) + 1);
			}
		}

		Queue<Vertex> queue = new LinkedList<Vertex>();
		for (Vertex vertex : vertices.values()) {
			if (inDegree.get(vertex) == 0) {
				queue.add(vertex);
			}
		}

		ArrayList<Type> returnList = new ArrayList<Type>();

		while (!queue.isEmpty()) {
			Vertex current = queue.poll();
			returnList.add(current.data);
			for (Vertex vertex : current.edges) {
				inDegree.put(vertex, inDegree.get(vertex) - 1);

				if (inDegree.get(vertex) == 0) {
					queue.add(vertex);
				}

			}

		}

		if (returnList.size() != vertices.size()) {
			throw new IllegalArgumentException("Graph contains a cycle.");
		}

		return returnList;

	}

	/**
	 * Private class that is used by the graph
	 */
	private class Vertex {
		private boolean visited;
		private Type data;
		private String name;
		private ArrayList<Vertex> edges;
		private Vertex cameFrom;

		/*
		 * Allows you to name the vertex with a string and set the stored data to a
		 * desired value.
		 */
		public Vertex(Type data, String name) {
			this.cameFrom = null;
			this.visited = false;
			this.data = data;
			this.name = name;
			this.edges = new ArrayList<Vertex>();
		}

		/**
		 * This constructor lets you just set the name, and the data is null.
		 * 
		 * @param name
		 */
		public Vertex(String name) {
			this.cameFrom = null;
			this.visited = false;
			this.data = null;
			this.name = name;
			this.edges = new ArrayList<Vertex>();
		}

		/**
		 * used by searching algorithms to flag vertices that have already been visited
		 * and stop infinite loops from occuring if the graph is cyclic.
		 */
		public void visited() {
			this.visited = true;
		}

		/**
		 * adds a vertex to the list of edges to indicate that the vertex is connected
		 * thisVertex -> toVertex
		 * 
		 * @param toVertex
		 */
		public void addEdge(Vertex toVertex) {
			edges.add(toVertex);
		}

		/**
		 * simple getter for data
		 * 
		 * @return - <Type>
		 */
		public Type getData() {
			return data;
		}

		/**
		 * removes an edge if the graph needs to be adjusted.
		 * 
		 * @param vertexToRemove
		 */
		public void removeEdge(Vertex vertexToRemove) {
			if (edges.contains(vertexToRemove)) {
				edges.remove(vertexToRemove);
			}
		}
	}



}