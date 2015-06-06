package org.shirdrn.smart.dag;


/**
 * A DAG example is depicted as follow:
 * <pre>
 *            --- vertex2 -
 *           -             -
 *          -               ---- vertex5
 *         -               -
 * vertex1 ------ vertex3 -
 *         -
 *          -
 *           ---- vertex4 ------ vertex6
 *  </pre>
 * 
 * @author Yanjun
 *
 */
public interface DAG extends Nameable<DAG> {

	DAGConfig getDAGConfig();
	
	void execute() throws Exception;
	
	DAG append(Vertex<?>... vertexes);
	
	DAG prepend(Vertex<?>... vertexes);
	
	DAG fork(Vertex<?>... forkedVertexes);
	
	DAG fork(Vertex<?>[]... forkedVertexes);
	
	DAG join(Vertex<?> joinedVertex);
	
	DAGMonitor getDAGMonitor();
	
	public enum AssembleOpType {
		APPEND,
		PREPEND,
		FORK,
		JOIN
	}
}
