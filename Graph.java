package assign07;

import java.util.List;

public class Graph<T> {
	private Vertex[] vertices;
	
	
	public boolean depthFirstSearch(Vertex source, Vertex destination) {
		
	}
	
	public List<Vertex> breadthFirstSearch(Vertex source, Vertex destination) {
		
	}

	
	public List<Vertex> topoSort() {
		
	}
	
	private class Vertex {
		private T data;
		
		
		public Vertex(T data) {
			this.data = data;
		}
	}
}
