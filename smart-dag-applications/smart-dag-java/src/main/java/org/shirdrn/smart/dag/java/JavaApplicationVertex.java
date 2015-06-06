package org.shirdrn.smart.dag.java;

import org.shirdrn.smart.dag.AbstractVertex;
import org.shirdrn.smart.dag.ApplicationException;
import org.shirdrn.smart.dag.InterfaceAudience;
import org.shirdrn.smart.dag.VertexException;

@InterfaceAudience.Private
public final class JavaApplicationVertex extends AbstractVertex<JavaApplication> {

	public JavaApplicationVertex(String name, JavaApplication application) {
		super(name, application);
	}

	@Override
	public void triggerApplication() throws VertexException {
		try {
			application.perform();
		} catch (ApplicationException e) {
			throw new VertexException("Unhandled application exception: ", e);
		} catch (Exception e) {
			throw new VertexException("Unknown application exception: ", e);
		}	
	}


}
