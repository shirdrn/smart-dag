package org.shirdrn.smart.dag.mapreduce;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.shirdrn.smart.dag.AbstractVertexBuilder;
import org.shirdrn.smart.dag.DAG;

public abstract class MapreduceBuilder extends AbstractVertexBuilder<MapreduceApplication> {
	
	public MapreduceBuilder(DAG dag) {
		super(dag);
	}

	// ////////////// Configure classes running MR Job required ////////////////

	public abstract MapreduceBuilder setJarByClass(Class<?> cls);

	@SuppressWarnings("rawtypes")
	public abstract MapreduceBuilder setMapperClass(Class<? extends Mapper> cls) throws IllegalStateException;

	@SuppressWarnings("rawtypes")
	public abstract MapreduceBuilder setReducerClass(Class<? extends Reducer> cls) throws IllegalStateException;

	public abstract MapreduceBuilder setMapOutputKeyClass(Class<?> theClass) throws IllegalStateException;

	public abstract MapreduceBuilder setMapOutputValueClass(Class<?> theClass) throws IllegalStateException;

	public abstract MapreduceBuilder setOutputKeyClass(Class<?> theClass) throws IllegalStateException;

	public abstract MapreduceBuilder setOutputValueClass(Class<?> theClass) throws IllegalStateException;

	@SuppressWarnings("rawtypes")
	public abstract MapreduceBuilder setPartitionerClass(Class<? extends Partitioner> cls) throws IllegalStateException;

	@SuppressWarnings("rawtypes")
	public abstract MapreduceBuilder setCombinerClass(Class<? extends Reducer> cls) throws IllegalStateException;

	@SuppressWarnings("rawtypes")
	public abstract MapreduceBuilder setSortComparatorClass(Class<? extends RawComparator> cls) throws IllegalStateException;

	@SuppressWarnings("rawtypes")
	public abstract MapreduceBuilder setGroupingComparatorClass(Class<? extends RawComparator> cls) throws IllegalStateException;

	@SuppressWarnings("rawtypes")
	public abstract MapreduceBuilder setInputFormatClass(Class<? extends InputFormat> cls) throws IllegalStateException;

	@SuppressWarnings("rawtypes")
	public abstract MapreduceBuilder setOutputFormatClass(Class<? extends OutputFormat> cls) throws IllegalStateException;

	// ////////////// Configure path related options ////////////////

	public abstract MapreduceBuilder addInputPath(Path inputPath) throws IOException;

	public abstract MapreduceBuilder addInputPaths(Path... inputPaths) throws IOException;

	public abstract MapreduceBuilder addOutputPath(Path outputDir) throws IOException;

	// ////////////// Configure numeric option for MR Job ////////////////

	public abstract MapreduceBuilder setNumReduceTasks(int tasks) throws IllegalStateException;

	public abstract MapreduceBuilder setMaxMapAttempts(int n);

	public abstract MapreduceBuilder setMaxReduceAttempts(int n);

	// ////////////// Configure other options ////////////////

	public abstract MapreduceBuilder setUser(String user);

	public abstract MapreduceBuilder setCacheArchives(URI[] archives);

	public abstract MapreduceBuilder setCacheFiles(URI[] files);

	public abstract MapreduceBuilder addCacheArchive(URI uri);

	public abstract MapreduceBuilder addCacheFile(URI uri);

	public abstract MapreduceBuilder addFileToClassPath(Path file) throws IOException;
	

}
