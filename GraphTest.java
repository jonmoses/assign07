package assign07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void bfsFindsSimplePath() {
		GraphFix<String> graph = new GraphFix<String>();
		graph.addEdge("A", "B");

		assertEquals(2, graph.breadthFirstSearch("A", "B").size());
	}

}
