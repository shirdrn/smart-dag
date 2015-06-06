package org.shirdrn.smart.dag;

import java.util.List;

import org.shirdrn.smart.dag.DAG.AssembleOpType;

public interface DAGMonitor extends VertexMonitor {

	void dagPrepare(DAG dag, AssembleOpType opType, Vertex<?>... vertex);
	void dagCreated(DAG dag);
	void dagStared(DAG dag);
	void dagFinished(DAG dag, List<Throwable> causes);
	void dumpStat();
	
	interface DumpPolicy {
		
		void write(RunningDAG runningDAG);
		
	}
	
}
