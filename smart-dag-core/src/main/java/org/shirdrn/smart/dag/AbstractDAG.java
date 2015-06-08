package org.shirdrn.smart.dag;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.shirdrn.smart.dag.utils.NamedThreadFactory;
import org.shirdrn.smart.dag.utils.ReflectionUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

@InterfaceAudience.Public
public abstract class AbstractDAG extends AbstractNameableComponent<DAG> implements DAG {
	
	protected final DAGConfig dagConfig;
	private final LinkedList<InternalVertex<?>> dagVertexes = Lists.newLinkedList();
	private final DAGMonitor dagMonitor;

	public AbstractDAG() {
		super();
		dagConfig = null;
		dagMonitor = new DAGMonitorImpl();
		dagMonitor.dagCreated(this);
	}
	
	public AbstractDAG(DAGConfig config) {
		super();
		this.dagConfig = config;
		String monitorClazz = config.getString("smart.dag.monitor.class", "org.shirdrn.smart.dag.DAGMonitorImpl");
		dagMonitor = ReflectionUtils.newInstance(monitorClazz, DAGMonitor.class, dagConfig);
		dagMonitor.dagCreated(this);
	}

	@Override
	public void execute() throws Exception {
		List<Throwable> causes = null;
		dagMonitor.dagStared(this);
		try {
			for (InternalVertex<?> vertex : dagVertexes) {
				vertex.fire();
			}
		} catch (VertexException ve) {
			collectCause(causes, ve);
		} catch (Exception e) {
			collectCause(causes, e);
		} finally {
			dagMonitor.dagFinished(this, causes);
			dagMonitor.dumpStat();
		}
	}
	
	private void collectCause(List<Throwable> causes, Throwable exception) {
		if(causes == null) {
			causes = Lists.newArrayList();
		}
		causes.add(exception);
	}

	@Override
	public DAG append(Vertex<?>... vertexes) {
		LinkedList<Vertex<?>> vertexSet = Lists.newLinkedList();
		for(Vertex<?> vertex : vertexes) {
			vertexSet.add(vertex);
			dagMonitor.dagPrepare(this, AssembleOpType.APPEND, vertex);
		}
		SequentialVertex v = new SequentialVertex(vertexSet);
		dagVertexes.addLast(v);
		return this;
	}
	
	@Override
	public DAG prepend(Vertex<?>... vertexes) {
		LinkedList<Vertex<?>> vertexSet = Lists.newLinkedList();
		for (Vertex<?> vertex : vertexes) {
			vertexSet.addLast(vertex);
			dagMonitor.dagPrepare(this, AssembleOpType.PREPEND, vertex);
		}
		SequentialVertex v = new SequentialVertex(vertexSet);
		dagVertexes.addFirst(v);
		return this;
	}

	@Override
	public DAG fork(Vertex<?>... forkedVertexes) {
		LinkedList<Vertex<?>> vertexSet = Lists.newLinkedList();
		for (Vertex<?> vertex : forkedVertexes) {
			vertexSet.addLast(vertex);
		}
		ParallelVertex pv = new ParallelVertex(vertexSet);
		dagVertexes.addLast(pv);
		dagMonitor.dagPrepare(this, AssembleOpType.FORK, forkedVertexes);
		return this;
	}
	
	@Override
	public DAG fork(Vertex<?>[]... forkedVertexes) {
		throw new UnsupportedOperationException("Currently don't support to use this opteration!");
	}

	@Override
	public DAG join(Vertex<?> joinedVertex) {
		this.append(joinedVertex);
		dagMonitor.dagPrepare(this, AssembleOpType.JOIN, joinedVertex);
		return this;
	}
	
	@Override
	public DAGConfig getDAGConfig() {
		return dagConfig;
	}
	
	@Override
	public DAGMonitor getDAGMonitor() {
		return dagMonitor;
	}

	@Override
	public String toString() {
		return "name=" + getName() + ", dagClass=" + this.getClass().getName();
	}
	
	private class ParallelVertex extends AbstractVertexCollection {
		
		private final ExecutorService executorService;
		private CountDownLatch latch;

		public ParallelVertex(Collection<Vertex<?>> vertex) {
			super(vertex);
			Preconditions.checkArgument(vertex.size() > 0, "Parallel vertex collection MUST NOT be empty!");
			latch = new CountDownLatch(vertex.size());
			int nThreads = Math.min(vertex.size(), Runtime.getRuntime().availableProcessors());
			ThreadFactory threadFactory = new NamedThreadFactory("FORK-vertex");
			executorService = Executors.newFixedThreadPool(nThreads, threadFactory);
		}
		
		@Override
		public void fire() {
			try {
				for (final Vertex<?> v : vertex) {
					VertexRunner runner = new VertexRunner(v);
					executorService.execute(runner);
				}
			} finally {
				try {
					latch.await();
				} catch (InterruptedException e) {}
				// reset the latch
				latch = new CountDownLatch(vertex.size());
				executorService.shutdown();
			}		
		}
		
		protected class VertexRunner extends Thread {

			private final Vertex<?> vertex;

			public VertexRunner(final Vertex<?> vertex) {
				this.vertex = vertex;
			}

			@Override
			public void run() {
				try {
					runVertexInternal(vertex);
				} catch(VertexException ve) {
					ve.printStackTrace();
				} finally {
					latch.countDown();
				}
			}
		}

	}

	private class SequentialVertex extends AbstractVertexCollection {

		public SequentialVertex(LinkedList<Vertex<?>> vertex) {
			super(vertex);
		}

		@Override
		public void fire() throws VertexException {
			for(Vertex<?> v : vertex) {
				runVertexInternal(v);
			}
		}

	}
	
	private abstract class AbstractVertexCollection extends InternalVertex<Collection<Vertex<?>>> {

		public AbstractVertexCollection(Collection<Vertex<?>> vertex) {
			super(vertex);
			Preconditions.checkArgument(vertex != null, "Vertex collection MUST NOT be NULL!");
		}
	}
	
	/**
	 * A {@link InternalVertex} is a basic unit of a {@link DAG}, which is a internal 
	 * representation and contains the actual computation logic. 
	 * 
	 * @author yanjun
	 *
	 * @param <V>
	 * 
	 * @see SequentialVertex
	 * @see ParallelVertex
	 */
	private abstract class InternalVertex<V> {
		
		protected final V vertex;
		
		public InternalVertex(V vertex) {
			Preconditions.checkArgument(vertex != null, "Vertex MUST NOT be NULL!");
			this.vertex = vertex;
		}
		
		protected abstract void fire() throws VertexException;
		
		protected void runVertexInternal(final Vertex<?> vertex) throws VertexException {
			List<Throwable> causes = null;
			dagMonitor.vertexStarted(AbstractDAG.this, vertex);
			try {
				vertex.triggerApplication();
			} catch(VertexException e) {
				collectCause(causes, e);
				if(e.getCause() instanceof ApplicationException) {
					collectAndThrow(causes, e.getCause(), "Application error: ");
				} else {
					collectAndThrow(causes, e.getCause(), "Unknown application error: ");
				}
			} catch(Exception e) {
				collectAndThrow(causes, e, "Internal DAG error caught: ");
			} finally {
				dagMonitor.vertexFinished(AbstractDAG.this, vertex, causes);
			}
		}
		
		private void collectAndThrow(List<Throwable> causes, Throwable e, String message) {
			collectCause(causes, e.getCause());
			throw new VertexException(message, e);
		}
		
	}
	
}
