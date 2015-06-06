package org.shirdrn.smart.dag;

import java.util.List;


public interface VertexMonitor {

	void vertexCreated(DAG dag, Vertex<?> vertex);
	void vertexStarted(DAG dag, Vertex<?> vertex);
	void vertexFinished(DAG dag, Vertex<?> vertex, List<Throwable> causes);
}
