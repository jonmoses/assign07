package assign07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {
	Graph<String> graph;
	ArrayList<String> correctTopo;

	@BeforeEach
	void setUp() throws Exception {
		correctTopo = new ArrayList<String>();
		correctTopo.add("A");
		correctTopo.add("B");
		correctTopo.add("C");
		correctTopo.add("D");

		graph = new Graph<String>();
		graph.addVertex("A", "A");
		graph.addVertex("B", "B");
		graph.addVertex("C", "C");
		graph.addVertex("D", "D");
		graph.addEdge("A", "B");
		graph.addEdge("B", "C");
		graph.addEdge("B", "D");
	}

	@Test
	void bfsFindsSimplePath() {

		assertEquals(2, graph.breadthFirstSearch("A", "B").size());
	}
	
	@Test
	void dfsFindsSimplePath() {
		assertTrue(graph.depthFirstSearch("A", "B"));
	}
	
	@Test
	void dfsFindsBiggerPath() {
		assertTrue(graph.depthFirstSearch("A", "C"));
	}
	
	@Test
	void bfsFindsBiggerPath() {
		assertEquals(3, graph.breadthFirstSearch("A", "C").size());
	}
	
	@Test
	void topoSort() {
		assertEquals(correctTopo, graph.topoSort());
	}
}
