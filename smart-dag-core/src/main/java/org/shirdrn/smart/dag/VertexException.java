package org.shirdrn.smart.dag;

public class VertexException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private DAG dag;
	private Vertex<?> vertex;
	
	public VertexException() {
		super();
	}
	
	public VertexException(String message) {
		super(message);
	}
	
	public VertexException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAG getDag() {
		return dag;
	}

	public void setDag(DAG dag) {
		this.dag = dag;
	}

	public Vertex<?> getVertex() {
		return vertex;
	}

	public void setVertex(Vertex<?> vertex) {
		this.vertex = vertex;
	}
}
