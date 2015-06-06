package org.shirdrn.smart.dag;

@InterfaceAudience.Private
public interface VertexBuilder<A> {

	Vertex<A> build();
	
}
