package org.shirdrn.smart.dag.examples;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.shirdrn.smart.dag.AbstractDAG;
import org.shirdrn.smart.dag.DAG;
import org.shirdrn.smart.dag.Vertex;
import org.shirdrn.smart.dag.examples.mapreduce.GrouppingDataJob;
import org.shirdrn.smart.dag.examples.mapreduce.GrouppingDataJob.MyMapper;
import org.shirdrn.smart.dag.examples.mapreduce.GrouppingDataJob.MyReducer;
import org.shirdrn.smart.dag.mapreduce.MapreduceApplication;
import org.shirdrn.smart.dag.mapreduce.MapreduceBuilderImpl;

public class GrouppingDataDAG extends AbstractDAG {

	public GrouppingDataDAG() {
		super();
	}
	
	public static void main(String[] args) throws Exception {
		int numReduceTasks = 3;
		if(args.length > 0) {
			try {
				numReduceTasks = Integer.parseInt(args[0]);
			} catch (Exception e) {}
		}
		
		DAG dag = new GrouppingDataDAG();
		
		// build vertex
		Vertex<MapreduceApplication> v1 = MapreduceBuilderImpl.newBuilder(dag, GrouppingDataDAG.class.getSimpleName())
				.setJarByClass(GrouppingDataJob.class)
				.setMapperClass(MyMapper.class)
				.setReducerClass(MyReducer.class)
				.setMapOutputKeyClass(Text.class)
				.setMapOutputValueClass(Text.class)
				.setOutputKeyClass(Text.class)
				.setOutputValueClass(Text.class)
				.addInputPath(new Path("/test/shiyj/distribute/input"))
				.addOutputPath(new Path("/test/shiyj/distribute/output"))
				.setNumReduceTasks(numReduceTasks)
				.build();
		// extra configurations for this specified MapReduce job
		MultipleOutputs.addNamedOutput(v1.getApplication().getMapReduceJob(), "basisevent", TextOutputFormat.class, Text.class, Text.class);
		MultipleOutputs.addNamedOutput(v1.getApplication().getMapReduceJob(), "basishtml", TextOutputFormat.class, Text.class, Text.class);
		MultipleOutputs.addNamedOutput(v1.getApplication().getMapReduceJob(), "basisdown", TextOutputFormat.class, Text.class, Text.class);
		
		// assemble & run a DAG
		dag.append(v1)
		.execute();
	}
	
}
