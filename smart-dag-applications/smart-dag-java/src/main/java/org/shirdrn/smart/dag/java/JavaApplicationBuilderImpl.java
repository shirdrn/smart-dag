package org.shirdrn.smart.dag.java;

import java.io.IOException;

import org.shirdrn.smart.dag.DAG;
import org.shirdrn.smart.dag.Vertex;

public class JavaApplicationBuilderImpl extends JavaApplicationBuilder {

	private final JavaApplication application;
	private final JavaApplicationVertex vertex;
	
	private JavaApplicationBuilderImpl(DAG dag, JavaApplication application) {
		super(dag);
		this.application = application;
		vertex = new JavaApplicationVertex(this.application.getName(), this.application);
		notifyVertexCreated(vertex);
	}
	
	public static JavaApplicationBuilder newBuilder(DAG dag, JavaApplication application) throws IOException {
		return new JavaApplicationBuilderImpl(dag, application);
	}
	
	@Override
	public Vertex<JavaApplication> build() {
		return vertex;
	}

}
