package assign07;

import java.util.List;

public class GraphUtility {

	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) {
		if (sources == null || destinations == null) {
			throw new IllegalArgumentException("Source and destination lists cannot be null.");
		}

		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException("Source and destination lists must be the same size.");
		}

		Graph<Type> graph = new Graph<Type>();
		for (int i = 0; i < sources.size(); i++) {
			Type src = sources.get(i);
			Type dest = destinations.get(i);
			graph.addVertex(src.toString(), src);
			graph.addVertex(dest.toString(), dest);
			graph.addEdge(src.toString(), dest.toString());
		}
		return graph.depthFirstSearch(srcData, dstData);
	}

	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData,
			Type dstData) {
		if (sources == null || destinations == null) {
			throw new IllegalArgumentException("Source and destination lists cannot be null.");
		}

		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException("Source and destination lists must be the same size.");
		}
		Graph<Type> graph = new Graph<Type>();
		for (int i = 0; i < sources.size(); i++) {
			Type src = sources.get(i);
			Type dest = destinations.get(i);
			graph.addVertex(src.toString(), src);
			graph.addVertex(dest.toString(), dest);
			graph.addEdge(src.toString(), dest.toString());
		}

		return graph.breadthFirstSearch(srcData, dstData);
	}

	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) {
		if (sources == null || destinations == null) {
			throw new IllegalArgumentException("Source and destination lists cannot be null.");
		}

		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException("Source and destination lists must be the same size.");
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
