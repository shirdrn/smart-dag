package org.shirdrn.smart.dag;

import com.google.common.base.Preconditions;

@InterfaceAudience.Private
public abstract class AbstractVertex<A> extends AbstractNameableComponent<Vertex<A>> implements Vertex<A> {

	protected A application;
	
	public AbstractVertex() {
		this(AbstractVertex.class.getSimpleName(), null);
	}
	
	public AbstractVertex(String name) {
		this(name, null);
	}
	
	public AbstractVertex(String name, A application) {
		super(name);
		Preconditions.checkArgument(application != null, "Application instance MUST not be NULL!");
		this.application = application;
	}
	
	@Override
	public Vertex<A> setApplication(A application) {
		this.application = application;
		return this;
	}
	
	@Override
	public A getApplication() {
		return application;
	}
	
	@Override
	public String toString() {
		return "name=" + getName() + ", vertexClass=" + this.getClass().getName();
	}

}
