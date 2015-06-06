package org.shirdrn.smart.dag.examples.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class GrouppingDataJob {

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
