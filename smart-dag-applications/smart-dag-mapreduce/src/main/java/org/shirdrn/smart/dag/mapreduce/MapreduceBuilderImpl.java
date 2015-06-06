package org.shirdrn.smart.dag.mapreduce;

import java.io.IOException;
import java.net.URI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.shirdrn.smart.dag.DAG;
import org.shirdrn.smart.dag.Vertex;

public class MapreduceBuilderImpl extends MapreduceBuilder {

	private static final Log LOG = LogFactory.getLog(MapreduceBuilder.class);
	private final MapreduceVertex vertex;
	private final Job job;
	private final MapreduceApplication application;
	private boolean built = false;

	private MapreduceBuilderImpl(DAG dag, Configuration conf, String jobName) throws IOException {
		super(dag);
		job = Job.getInstance(conf, jobName);
		application = new MapreduceApplication(job);
		vertex = new MapreduceVertex(application);
		notifyVertexCreated(vertex);
	}

	public static MapreduceBuilder newBuilder(DAG dag, String jobName) throws IOException {
		Configuration conf = new Configuration();
		return newBuilder(dag, conf, jobName);
	}

	public static MapreduceBuilder newBuilder(DAG dag, Configuration conf, String jobName) throws IOException {
		return new MapreduceBuilderImpl(dag, conf, jobName);
	}

	private void ensureBuildState() {
		if (built) {
			IllegalStateException e = new IllegalStateException("After job has been built, don't permit to update a job again!");
			LOG.error(e);
			throw e;
		}
	}

	// ////////////// Configure classes running MR Job required ////////////////

	@Override
	public MapreduceBuilder setJarByClass(Class<?> cls) {
		ensureBuildState();
		job.setJarByClass(cls);
		return this;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public MapreduceBuilder setMapperClass(Class<? extends Mapper> cls) throws IllegalStateException {
		ensureBuildState();
		job.setMapperClass(cls);
		return this;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public MapreduceBuilder setReducerClass(Class<? extends Reducer> cls) throws IllegalStateException {
		ensureBuildState();
		job.setReducerClass(cls);
		return this;
	}

	@Override
	public MapreduceBuilder setMapOutputKeyClass(Class<?> theClass) throws IllegalStateException {
		ensureBuildState();
		job.setMapOutputKeyClass(theClass);
		return this;
	}

	@Override
	public MapreduceBuilder setMapOutputValueClass(Class<?> theClass) throws IllegalStateException {
		ensureBuildState();
		job.setMapOutputValueClass(theClass);
		return this;
	}

	@Override
	public MapreduceBuilder setOutputKeyClass(Class<?> theClass) throws IllegalStateException {
		ensureBuildState();
		job.setOutputKeyClass(theClass);
		return this;
	}

	@Override
	public MapreduceBuilder setOutputValueClass(Class<?> theClass) throws IllegalStateException {
		ensureBuildState();
		job.setOutputValueClass(theClass);
		return this;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public MapreduceBuilder setPartitionerClass(Class<? extends Partitioner> cls) throws IllegalStateException {
		ensureBuildState();
		job.setPartitionerClass(cls);
		return this;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public MapreduceBuilder setCombinerClass(Class<? extends Reducer> cls) throws IllegalStateException {
		ensureBuildState();
		job.setCombinerClass(cls);
		return this;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public MapreduceBuilder setSortComparatorClass(Class<? extends RawComparator> cls) throws IllegalStateException {
		ensureBuildState();
		job.setSortComparatorClass(cls);
		return this;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public MapreduceBuilder setGroupingComparatorClass(Class<? extends RawComparator> cls) throws IllegalStateException {
		ensureBuildState();
		job.setGroupingComparatorClass(cls);
		return this;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public MapreduceBuilder setInputFormatClass(Class<? extends InputFormat> cls) throws IllegalStateException {
		ensureBuildState();
		job.setInputFormatClass(cls);
		return this;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public MapreduceBuilder setOutputFormatClass(Class<? extends OutputFormat> cls) throws IllegalStateException {
		ensureBuildState();
		job.setOutputFormatClass(cls);
		return this;
	}

	// ////////////// Configure path related options ////////////////

	@Override
	public MapreduceBuilder addInputPath(Path inputPath) throws IOException {
		ensureBuildState();
		FileInputFormat.addInputPath(job, inputPath);
		return this;
	}

	@Override
	public MapreduceBuilder addInputPaths(Path... inputPaths) throws IOException {
		ensureBuildState();
		for (Path path : inputPaths) {
			FileInputFormat.addInputPath(job, path);
		}
		return this;
	}

	@Override
	public MapreduceBuilder addOutputPath(Path outputDir) throws IOException {
		ensureBuildState();
		FileOutputFormat.setOutputPath(job, outputDir);
		return this;
	}

	// ////////////// Configure numeric option for MR Job ////////////////

	@Override
	public MapreduceBuilder setNumReduceTasks(int tasks) throws IllegalStateException {
		ensureBuildState();
		job.setNumReduceTasks(tasks);
		return this;
	}

	@Override
	public MapreduceBuilder setMaxMapAttempts(int n) {
		ensureBuildState();
		job.setMaxMapAttempts(n);
		return this;
	}

	@Override
	public MapreduceBuilder setMaxReduceAttempts(int n) {
		ensureBuildState();
		job.setMaxReduceAttempts(n);
		return this;
	}

	// ////////////// Configure other options ////////////////

	@Override
	public MapreduceBuilder setUser(String user) {
		ensureBuildState();
		job.setUser(user);
		return this;
	}

	@Override
	public MapreduceBuilder setCacheArchives(URI[] archives) {
		ensureBuildState();
		job.setCacheArchives(archives);
		return this;
	}

	@Override
	public MapreduceBuilder setCacheFiles(URI[] files) {
		ensureBuildState();
		job.setCacheFiles(files);
		return this;
	}

	@Override
	public MapreduceBuilder addCacheArchive(URI uri) {
		ensureBuildState();
		job.addCacheArchive(uri);
		return this;
	}

	@Override
	public MapreduceBuilder addCacheFile(URI uri) {
		ensureBuildState();
		job.addCacheFile(uri);
		return this;
	}

	@Override
	public MapreduceBuilder addFileToClassPath(Path file) throws IOException {
		ensureBuildState();
		job.addFileToClassPath(file);
		return this;
	}

	@Override
	public Vertex<MapreduceApplication> build() {
		built = true;
		LOG.info("Vertex built: name=" + vertex.getName());
		return vertex;
	}

}
