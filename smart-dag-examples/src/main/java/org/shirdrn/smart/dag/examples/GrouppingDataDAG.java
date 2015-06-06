package org.shirdrn.smart.dag.examples;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.shirdrn.smart.dag.AbstractDAG;
import org.shirdrn.smart.dag.DAG;
import org.shirdrn.smart.dag.Vertex;
import org.shirdrn.smart.dag.examples.mapreduce.GrouppingDataJob.MyMapper;
import org.shirdrn.smart.dag.examples.mapreduce.GrouppingDataJob.MyReducer;
import org.shirdrn.smart.dag.mapreduce.MapreduceBuilderImpl;
import org.shirdrn.smart.dag.mapreduce.MapreduceApplication;

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
				.setJarByClass(DistributeDataJob.class)
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
	
	public static class DistributeDataJob {
		public static class MyMapper extends Mapper<Object, Text, Text, Text> {
			@Override
			protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
				FileSplit fileSplit = (FileSplit) context.getInputSplit();
				String fileName = fileSplit.getPath().getName();
				context.write(new Text(fileName), value);
			}
		}
		
		public static class MyReducer extends Reducer<Text, Text, Text, Text> {
			private MultipleOutputs<Text, Text> mos; 
			@Override
			protected void setup(Context context) throws IOException, InterruptedException {
				super.setup(context);
				mos = new MultipleOutputs<Text, Text>(context);
			}
			
			@Override
			protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
				String k = key.toString();
				String table = "";
				for(Text value : values) {
					if(k.startsWith("basis_event")) {
						table = "basisevent";
					} else if(k.startsWith("basis_html")) {
						table = "basishtml";
					} else if(k.startsWith("basis_down")) {
						table = "basisdown";
					}
					mos.write(table, "", table + "\t" + value);
				}
			}
			
			@Override
			protected void cleanup(Context context) throws IOException, InterruptedException {
				super.cleanup(context);
				mos.close();
			}
		}
	}

}
