package assign07;

import java.util.List;

/**
 * graphUtility class for summer 26 CS2420 assignment 7
 * 
 * @author jonmoses and jaron burton
 * @version 2026-06-25
 */
public class GraphUtility {

	/**
	 * Determines if two vertices are connected as defined by the sources and
	 * destinations list
	 * 
	 * 
	 * @param <Type>       - anything
	 * @param sources      - a list of vertices that an edge originates from
	 * @param destinations - a list of vertices that an edge terminatates at
	 * @param srcData      - the starting node
	 * @param dstData      - the ending node
	 * @return - true if two vertices are connected, false otherwise
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) {
		if (sources == null || destinations == null) {
			throw new IllegalArgumentException();
		}

		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException();
		}

		Graph<Type> graph = new Graph<Type>();
		for (int i = 0; i < sources.size(); i++) {
			Type src = sources.get(i);
			Type dest = destinations.get(i);
			graph.addVertex(src.toString(), src);
			graph.addVertex(dest.toString(), dest);
			graph.addEdge(src.toString(), dest.toString());
		}

		if (!graph.containsVertex(srcData.toString()) || !graph.containsVertex(dstData.toString()))
			throw new IllegalArgumentException();

		return graph.depthFirstSearch(srcData, dstData);
	}

	/**
	 * Uses BFS to find the shortest path from two vertices in the graph specified
	 * by sources and destinations.
	 * 
	 * @param <Type>
	 * @param sources      - a list of vertices that an edge originates from
	 * @param destinations - a list of vertices that an edge terminatates at
	 * @param srcData      - the starting node
	 * @param dstData      - the ending node
	 * @return - A list of objects with type Type that is the path from source to
	 *         destination
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData,
			Type dstData) {
		if (sources == null || destinations == null) {
			throw new IllegalArgumentException();
		}

		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException();
		}
		Graph<Type> graph = new Graph<Type>();
		for (int i = 0; i < sources.size(); i++) {
			Type src = sources.get(i);
			Type dest = destinations.get(i);
			graph.addVertex(src.toString(), src);
			graph.addVertex(dest.toString(), dest);
			graph.addEdge(src.toString(), dest.toString());
		}

		List<Type> path = graph.breadthFirstSearch(srcData, dstData);

		if (path.isEmpty())
			throw new IllegalArgumentException();

		return path;
	}

	/**
	 * Sorts the vertices topologically with the edges defined by the sources and
	 * destinations list.
	 * 
	 * @param <Type>       - any type you can possibly imagine, the skys the limit
	 * @param sources      - the source vertex for all edges
	 * @param destinations - the destination vertex for all edges
	 * @return - A list of type <Type> objects that is the topologically sorted
	 *         list.
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) {
		if (sources == null || destinations == null) {
			throw new IllegalArgumentException();
		}

		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException();
		}
		Graph<Type> graph = new Graph<Type>();
		for (int i = 0; i < sources.size(); i++) {
			Type src = sources.get(i);
			Type dest = destinations.get(i);
			graph.addVertex(src.toString(), src);
			graph.addVertex(dest.toString(), dest);
			graph.addEdge(src.toString(), dest.toString());
		}

		return graph.topoSort();
	}
}
