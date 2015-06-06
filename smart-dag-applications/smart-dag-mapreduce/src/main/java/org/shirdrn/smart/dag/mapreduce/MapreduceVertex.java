package org.shirdrn.smart.dag.mapreduce;

import org.shirdrn.smart.dag.AbstractVertex;
import org.shirdrn.smart.dag.ApplicationException;
import org.shirdrn.smart.dag.InterfaceAudience;
import org.shirdrn.smart.dag.Vertex;
import org.shirdrn.smart.dag.VertexException;

@InterfaceAudience.Private
public final class MapreduceVertex extends AbstractVertex<MapreduceApplication> implements Vertex<MapreduceApplication> {

	public MapreduceVertex(MapreduceApplication application) {
		super(application.getName(), application);
	}
	
	public MapreduceVertex(String name, MapreduceApplication application) {
		super(name, application);
	}
	
	@Override
	public void triggerApplication() {
		try {
			application.perform();
		} catch (ApplicationException e) {
			throw new VertexException("Unhandled application exception: ", e);
		} catch (Exception e) {
			throw new VertexException("Unknown application exception: ", e);
		}
		
	}

}
