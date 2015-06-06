package org.shirdrn.smart.dag.mapreduce;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Job;
import org.shirdrn.smart.dag.AbstractApplication;
import org.shirdrn.smart.dag.ApplicationException;

public final class MapreduceApplication extends AbstractApplication {

	protected final Job job;
	
	public MapreduceApplication(Job job) {
		super();
		this.job = job;
	}
	
	public MapreduceApplication(String name, Job job) {
		super(name);
		this.job = job;
	}
	
	public Job getMapReduceJob() {
		return job;
	}

	@Override
	public void perform() throws ApplicationException {
		boolean succeed = false;
		Exception exception = null;
		try {
			succeed = job.waitForCompletion(true);
		} catch (ClassNotFoundException e) {
			exception = e;
		} catch (IOException e) {
			exception = e;
		} catch (InterruptedException e) {
			exception = e;
		} catch (Exception e) {
			exception = e;
		}
		if(!succeed) {
			throw new ApplicationException("Vertex execution failure: " + getName(), exception);
		}
	}
}
