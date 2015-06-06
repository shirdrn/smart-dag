package org.shirdrn.smart.dag;

@InterfaceAudience.Private
public abstract class AbstractVertexBuilder<A> implements VertexBuilder<A> {

	protected final DAG dag;
	private final DAGMonitor dagMonitor;
	
	public AbstractVertexBuilder(DAG dag) {
		super();
		this.dag = dag;
		this.dagMonitor = dag.getDAGMonitor();
	}
	
	protected void notifyVertexCreated(Vertex<A> vertex) {
		dagMonitor.vertexCreated(dag, vertex);
	}
}
