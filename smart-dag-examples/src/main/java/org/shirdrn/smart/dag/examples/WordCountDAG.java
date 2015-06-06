package org.shirdrn.smart.dag.examples;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.shirdrn.smart.dag.AbstractDAG;
import org.shirdrn.smart.dag.DAG;
import org.shirdrn.smart.dag.DAGConfig;
import org.shirdrn.smart.dag.DAGConfigImpl;
import org.shirdrn.smart.dag.Vertex;
import org.shirdrn.smart.dag.examples.mapreduce.WordCountJob;
import org.shirdrn.smart.dag.examples.mapreduce.WordCountJob.IntSumReducer;
import org.shirdrn.smart.dag.examples.mapreduce.WordCountJob.TokenizerMapper;
import org.shirdrn.smart.dag.mapreduce.MapreduceBuilderImpl;
import org.shirdrn.smart.dag.mapreduce.MapreduceApplication;

public class WordCountDAG extends AbstractDAG {

	private static final Log LOG = LogFactory.getLog(WordCountDAG.class); 
	
	public WordCountDAG(DAGConfig config) {
		super(config);
	}
	
	public static void main(String[] args) throws Exception {
		DAGConfig config = new DAGConfigImpl("dag-examples.properties");
		DAG wcDAG = new WordCountDAG(config);
		
		Vertex<MapreduceApplication> v1 = MapreduceBuilderImpl.newBuilder(wcDAG, config.getString("dag.job.wc1.name", WordCountDAG.class.getSimpleName()))
			.setJarByClass(WordCountJob.class)
			.setMapperClass(TokenizerMapper.class)
			.setReducerClass(IntSumReducer.class)
			.setCombinerClass(IntSumReducer.class)
			.setMapOutputKeyClass(Text.class)
			.setMapOutputValueClass(IntWritable.class)
			.setOutputKeyClass(Text.class)
			.setOutputValueClass(IntWritable.class)
			.addInputPath(new Path(config.getString("dag.job.wc1.input")))
			.addOutputPath(new Path(config.getString("dag.job.wc1.output")))
			.setNumReduceTasks(1)
			.build();
		LOG.info(config.getString("dag.job.wc1.name", WordCountDAG.class.getSimpleName()));
		LOG.info(config.getString("dag.job.wc1.input"));
		LOG.info(config.getString("dag.job.wc1.output"));
		
		
		Vertex<MapreduceApplication> v2 = MapreduceBuilderImpl.newBuilder(wcDAG, config.getString("dag.job.wc2.name", WordCountDAG.class.getSimpleName()))
				.setJarByClass(WordCountJob.class)
				.setMapperClass(TokenizerMapper.class)
				.setReducerClass(IntSumReducer.class)
				.setCombinerClass(IntSumReducer.class)
				.setMapOutputKeyClass(Text.class)
				.setMapOutputValueClass(IntWritable.class)
				.setOutputKeyClass(Text.class)
				.setOutputValueClass(IntWritable.class)
				.addInputPath(new Path(config.getString("dag.job.wc2.input")))
				.addOutputPath(new Path(config.getString("dag.job.wc2.output")))
				.setNumReduceTasks(1)
				.build();
		LOG.info(config.getString("dag.job.wc2.name", WordCountDAG.class.getSimpleName()));
		LOG.info(config.getString("dag.job.wc2.input"));
		LOG.info(config.getString("dag.job.wc2.output"));
		
//		// sequential jobs' DAG
		wcDAG.append(v1)
			.append(v2)
			.execute();
		
		// parallel jobs' DAG
//		wcDAG.fork(v1, v2)
//			.execute();
	}

}
