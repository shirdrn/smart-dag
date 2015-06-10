# smart-dag
Smart DAG computation framework written in Java.

#Features
We can build a computation `DAG`, whose basic computation unit is abstracted as `Vertex`, a internal representation, and a `DAG` is comprised of `Vertex`s. A Vertex encapsulates some computation logic, called `Application`. Usually users can implements a application by extending the specified `Application`. 

Currently supports 2 types' `Application`: `JavaApplication`, `MapreduceApplication`.

#Usage
Here, we show a example which including 3 [Vertex]()s. The Java code building procedure is depicted as follow:
```java
		DAGConfig config = new DAGConfigImpl("dag-examples.properties");
		DAG dag = new MixedMRJobAndJavaApplicationDAG(config);
		
		// build vertex v1
		Vertex<MapreduceApplication> v1 = MapreduceBuilderImpl.newBuilder(dag, config.getString("dag.job.wc1.name"))
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
		
		// build vertex v1
		Vertex<MapreduceApplication> v2 = MapreduceBuilderImpl.newBuilder(dag, config.getString("dag.job.wc2.name"))
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
		
		// build vertex v3
		JavaApplication application = new SleepAndPrintWordsApplication("sleepAndPrint");
		Vertex<JavaApplication> v3 = JavaApplicationBuilderImpl.newBuilder(dag, application)
				.build();
		
		// assemble sequential vertex's DAG
		dag.append(v1)
			.append(v2)
			.append(v3)
			.execute();
		
		// assemble mixed sequential & parallel vertex's DAG
//		dag.fork(v1, v2)
//			.append(v3)
//			.execute();

```
For more detail about above example code, please check the source: https://github.com/shirdrn/smart-dag/blob/master/smart-dag-examples/src/main/java/org/shirdrn/smart/dag/examples/MixedMRJobAndJavaApplicationDAG.java.