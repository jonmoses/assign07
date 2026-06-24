package assign07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {
	Graph<String> graph;

	@BeforeEach
	void setUp() throws Exception {
		graph = new Graph<String>();
		graph.addEdge("A", "B");
		graph.addEdge("B", "C");
		graph.addEdge("B", "D");
		graph.addEdge("A", "D");
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
	

}
