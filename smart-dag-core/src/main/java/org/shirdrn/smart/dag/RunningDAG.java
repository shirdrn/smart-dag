package org.shirdrn.smart.dag;

import java.util.List;

import com.google.common.collect.Lists;

@InterfaceAudience.Private
public final class RunningDAG extends DAGMetric {

	private final DAG dag;
	private final List<RunningVertex> runningVertexes = Lists.newArrayList();
	
	public RunningDAG(String name, DAG dag) {
		super(name);
		this.dag = dag;
	}
	public RunningDAG(DAG dag) {
		super();
		this.dag = dag;
	}
	
	public DAG getDAG() {
		return dag;
	}

	public List<RunningVertex> getRunningVertexes() {
		return runningVertexes;
	}
	
}
