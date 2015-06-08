package org.shirdrn.smart.dag;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shirdrn.smart.dag.DAG.AssembleOpType;
import org.shirdrn.smart.dag.DAGMetric.Status;
import org.shirdrn.smart.dag.utils.ReflectionUtils;
import org.shirdrn.smart.dag.utils.TimeUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DAGMonitorImpl implements DAGMonitor {

	private static final Log LOG = LogFactory.getLog(DAGMonitorImpl.class); 
	private final DAGConfig dagConfig;
	private final ConcurrentMap<DAG, RunningDAG> runningDAGs = Maps.newConcurrentMap();
	private final ConcurrentMap<DAG, Map<Vertex<?>, RunningVertex>> runningVertexes = 
			new ConcurrentHashMap<DAG, Map<Vertex<?>,RunningVertex>>();
	private final DumpPolicy dumpPolicy;
	private static final String DF_FORMAT = "yyyy-MM-dd HH:mm:ss"; 
	
	public DAGMonitorImpl(DAGConfig dagConfig) {
		this.dagConfig = dagConfig;
		if(dagConfig == null) {
			dumpPolicy = ReflectionUtils.newInstance(DefaultDumpPolicy.class);
		} else {
			String dumpClass = this.dagConfig.getString("smart.dag.monitor.dump.policy");
			if(dumpClass == null) {
				dumpPolicy = ReflectionUtils.newInstance(DefaultDumpPolicy.class);
			} else {
				dumpPolicy = ReflectionUtils.newInstance(dumpClass, DumpPolicy.class);
			}
		}
	}
	
	public DAGMonitorImpl() {
		this(null);
	}
	
	
	// //////////////////////// DAG monitor behaviors /////////////////
	
	@Override
	public void dagPrepare(DAG dag, AssembleOpType opType, Vertex<?>... vertex) {
		traceDAGAssembleOps(dag, opType, vertex);
	}
	
	private void traceDAGAssembleOps(DAG dag, AssembleOpType opType, Vertex<?>... vertex) {
		Collection<String> vertexNames = extractJobNames(vertex);
		LOG.info(dag.getName() + "." + opType + ": " + vertexNames);
	}
	
	private Collection<String> extractJobNames(Vertex<?>... vertexes) {
		List<String> result = Lists.newArrayList();
		for(Vertex<?> vertex : vertexes) {
			result.add(vertex.getName());
		}
		return result;
	}

	@Override
	public void dagCreated(DAG dag) {
		RunningDAG runningJobDAG = runningDAGs.putIfAbsent(dag, new RunningDAG(dag));
		if(runningJobDAG != null) {
			LOG.warn("DAG already created:" + runningDAGs.get(dag));
		} else {
			LOG.info("DAG created: dag=" + dag.getName());
		}
	}

	@Override
	public void dagStared(DAG dag) {
		RunningDAG runningJobDAG = runningDAGs.get(dag);
		if(runningJobDAG != null) {
			runningJobDAG.setStartDate(new Date());
			LOG.info("DAG started: dag=" + dag.getName() + ", date=" + format(runningJobDAG.getStartDate()));
		} else {
			throwDAGIllegalStateError(dag);
		}
	}

	@Override
	public void dagFinished(DAG dag, List<Throwable> causes) {
		RunningDAG runningDAG = runningDAGs.get(dag);
		if(runningDAG != null) {
			synchronized(runningDAG) {
				if(causes != null && !causes.isEmpty()) {
					runningDAG.setCauses(causes);
					runningDAG.setStatus(Status.FAILURE);
				} else {
					runningDAG.setStatus(Status.SUCCESS);
				}
				runningDAG.setFinishDate(new Date());
				// compute time taken
				timeTaken(runningDAG);
			}
			LOG.info("DAG finished: dag=" + dag.getName() + ", date=" + format(runningDAG.getFinishDate()));
		} else {
			throwDAGIllegalStateError(dag);
		}
	}
	
	private void throwDAGIllegalStateError(DAG dag) {
		String message = "DAG not found: dag="  + dag;
		LOG.error(message);
		throw new DAGException("Illegal state error, " + message);
	}
	
	private void timeTaken(DAGMetric runningObj) {
		runningObj.setTimeTaken(
				runningObj.getFinishDate().getTime() - runningObj.getStartDate().getTime());
	}
	

	// //////////////////////// Vertex monitor behaviors /////////////////
	
	@Override
	public void vertexCreated(DAG dag, Vertex<?> vertex) {
		Map<Vertex<?>, RunningVertex> runningVertexKVs = runningVertexes.get(dag);
		if(runningVertexKVs == null) {
			runningVertexKVs = Collections.synchronizedMap(new LinkedHashMap<Vertex<?>, RunningVertex>());
			runningVertexes.put(dag, runningVertexKVs);
		}
		synchronized(runningVertexKVs) {
			runningVertexKVs.put(vertex, new RunningVertex(vertex));
		}
		RunningVertex runningVertex = runningVertexKVs.get(vertex);
		synchronized(runningVertex) {
			// collect running vertexes belonging to the running DAG
			runningDAGs.get(dag).getRunningVertexes().add(runningVertex);
		}
		LOG.info("Vertex created: dag=" + dag.getName() + ", vertex=" + vertex.getName());		
	}

	@Override
	public void vertexStarted(DAG dag, Vertex<?> vertex) {
		RunningVertex runningVertex = runningVertexes.get(dag).get(vertex);
		if(runningVertex != null) {
			runningVertex.setStartDate(new Date());
			LOG.info("Vertex started: dag=" + dag.getName() + ", vertex=" + vertex.getName());		
		} else {
			throwVertexIllegalStateError(dag, vertex);
		}
	}
	
	@Override
	public void vertexFinished(DAG dag, Vertex<?> vertex, List<Throwable> causes) {
		RunningVertex runningVertex = runningVertexes.get(dag).get(vertex);
		if(runningVertex != null) {
			synchronized(runningVertex) {
				if(causes != null && !causes.isEmpty()) {
					runningVertex.setCauses(causes);
					runningVertex.setStatus(Status.FAILURE);
				} else {
					runningVertex.setStatus(Status.SUCCESS);
				}
				runningVertex.setFinishDate(new Date());
			}
			// compute time taken
			timeTaken(runningVertex);
			LOG.info("Vertex finished: dag=" + dag.getName() + ", vertex=" + vertex.getName());		
		} else {
			throwVertexIllegalStateError(dag, vertex);
		}
	}
	
	private void throwVertexIllegalStateError(DAG dag, Vertex<?> vertex) {
		String message = "Vertex not found: dag="  + dag + ", vertex=" + vertex;
		LOG.error(message);
		throw new DAGException("Illegal state error, " + message);
	}

	@Override
	public void dumpStat() {
		for(RunningDAG runningDAG : runningDAGs.values()) {
			dumpPolicy.write(runningDAG);
		}
	}
	
	private static String format(Date date) {
		return TimeUtils.format(date, DF_FORMAT);
	}
	
	public static class DefaultDumpPolicy implements DumpPolicy {

		@Override
		public void write(RunningDAG runningDAG) {
			LOG.info("DAG[" + runningDAG.getDAG().getName() + "]: ");
			LOG.info("    " + "status    : " + runningDAG.getStatus());
			LOG.info("    " + "startDate : " + format(runningDAG.getStartDate()));
			LOG.info("    " + "finishDate: " + format(runningDAG.getFinishDate()));
			LOG.info("    " + "causes    : " + runningDAG.getCauses() == null ? "NONE" : runningDAG.getCauses());
			LOG.info("    " + "timeTaken : " + runningDAG.getTimeTaken() + " ms");
			// for vertexes
			for(RunningVertex runningVertex : runningDAG.getRunningVertexes()) {
				LOG.info("    VERTEX[" + runningDAG.getDAG().getName() + " -> " + runningVertex.getVertex().getName() + "]: ");
				LOG.info("        " + "status    : " + runningVertex.getStatus());
				LOG.info("        " + "startDate : " + format(runningVertex.getStartDate()));
				LOG.info("        " + "finishDate: " + format(runningVertex.getFinishDate()));
				LOG.info("        " + "causes    : " + runningVertex.getCauses() == null ? "NONE" : runningVertex.getCauses());
				LOG.info("        " + "timeTaken : " + runningVertex.getTimeTaken() + " ms");
			}
		}
		
	}

}
