package org.shirdrn.smart.dag;

@InterfaceAudience.Private
public final class RunningVertex extends DAGMetric {

	private final Vertex<?> vertex;

	public RunningVertex(Vertex<?> vertex) {
		super();
		this.vertex = vertex;
	}
	public RunningVertex(String name, Vertex<?> vertex) {
		super(name);
		this.vertex = vertex;
	}

	public Vertex<?> getVertex() {
		return vertex;
	}
	
 }
