package org.shirdrn.smart.dag;

public interface Vertex<A> extends Nameable<Vertex<A>>{

	Vertex<A> setApplication(A application);
	A getApplication();
	void triggerApplication() throws VertexException;
}
