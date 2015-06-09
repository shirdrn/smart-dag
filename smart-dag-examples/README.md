# smart-dag-examples

After packaging example codes, issue the following command:

    $ yarn jar smart-dag-examples-0.0.1-SNAPSHOT.jar org.shirdrn.smart.dag.examples.MixedMRJobAndJavaApplicationDAG

Result of program running information:

	15/06/06 22:58:58 INFO dag.DAGMonitorImpl: DAG created: dag=MixedMRJobAndJavaApplicationDAG
	15/06/06 22:58:59 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
	15/06/06 22:58:59 INFO dag.DAGMonitorImpl: Vertex created: dag=MixedMRJobAndJavaApplicationDAG, vertex=MapreduceApplication
	15/06/06 22:59:00 INFO mapreduce.MapreduceBuilder: Vertex built: name=MapreduceApplication
	15/06/06 22:59:00 INFO examples.MixedMRJobAndJavaApplicationDAG: wc1
	15/06/06 22:59:00 INFO examples.MixedMRJobAndJavaApplicationDAG: /test/data/tmp/basis_common/basis_common_20141119143707376_2_0000.log.COMPLETED
	15/06/06 22:59:00 INFO examples.MixedMRJobAndJavaApplicationDAG: /test/data/tmp/wc1out
	15/06/06 22:59:00 INFO dag.DAGMonitorImpl: Vertex created: dag=MixedMRJobAndJavaApplicationDAG, vertex=MapreduceApplication
	15/06/06 22:59:00 INFO mapreduce.MapreduceBuilder: Vertex built: name=MapreduceApplication
	15/06/06 22:59:00 INFO examples.MixedMRJobAndJavaApplicationDAG: wc2
	15/06/06 22:59:00 INFO examples.MixedMRJobAndJavaApplicationDAG: /test/data/tmp/basis_common/basis_common_20141120153852727_2_0000.log.COMPLETED
	15/06/06 22:59:00 INFO examples.MixedMRJobAndJavaApplicationDAG: /test/data/tmp/wc2out
	15/06/06 22:59:00 INFO dag.DAGMonitorImpl: Vertex created: dag=MixedMRJobAndJavaApplicationDAG, vertex=sleepAndPrint
	15/06/06 22:59:00 INFO dag.DAGMonitorImpl: MixedMRJobAndJavaApplicationDAG.APPEND: [MapreduceApplication]
	15/06/06 22:59:00 INFO dag.DAGMonitorImpl: MixedMRJobAndJavaApplicationDAG.APPEND: [MapreduceApplication]
	15/06/06 22:59:00 INFO dag.DAGMonitorImpl: MixedMRJobAndJavaApplicationDAG.APPEND: [sleepAndPrint]
	15/06/06 22:59:00 INFO dag.DAGMonitorImpl: DAG started: dag=MixedMRJobAndJavaApplicationDAG, date=2015-06-06 22:59:00
	15/06/06 22:59:00 INFO dag.DAGMonitorImpl: Vertex started: dag=MixedMRJobAndJavaApplicationDAG, vertex=MapreduceApplication
	15/06/06 22:59:00 INFO client.RMProxy: Connecting to ResourceManager at hadoop6/10.10.4.130:8032
	15/06/06 22:59:00 WARN mapreduce.JobSubmitter: Hadoop command-line option parsing not performed. Implement the Tool interface and execute your application with ToolRunner to remedy this.
	15/06/06 22:59:00 INFO input.FileInputFormat: Total input paths to process : 1
	15/06/06 22:59:01 INFO mapreduce.JobSubmitter: number of splits:1
	15/06/06 22:59:01 INFO Configuration.deprecation: user.name is deprecated. Instead, use mapreduce.job.user.name
	15/06/06 22:59:01 INFO Configuration.deprecation: mapred.jar is deprecated. Instead, use mapreduce.job.jar
	15/06/06 22:59:01 INFO Configuration.deprecation: mapred.reduce.tasks is deprecated. Instead, use mapreduce.job.reduces
	15/06/06 22:59:01 INFO Configuration.deprecation: mapred.output.value.class is deprecated. Instead, use mapreduce.job.output.value.class
	15/06/06 22:59:01 INFO Configuration.deprecation: mapred.mapoutput.value.class is deprecated. Instead, use mapreduce.map.output.value.class
	15/06/06 22:59:01 INFO Configuration.deprecation: mapreduce.combine.class is deprecated. Instead, use mapreduce.job.combine.class
	15/06/06 22:59:01 INFO Configuration.deprecation: mapreduce.map.class is deprecated. Instead, use mapreduce.job.map.class
	15/06/06 22:59:01 INFO Configuration.deprecation: mapred.job.name is deprecated. Instead, use mapreduce.job.name
	15/06/06 22:59:01 INFO Configuration.deprecation: mapreduce.reduce.class is deprecated. Instead, use mapreduce.job.reduce.class
	15/06/06 22:59:01 INFO Configuration.deprecation: mapred.input.dir is deprecated. Instead, use mapreduce.input.fileinputformat.inputdir
	15/06/06 22:59:01 INFO Configuration.deprecation: mapred.output.dir is deprecated. Instead, use mapreduce.output.fileoutputformat.outputdir
	15/06/06 22:59:01 INFO Configuration.deprecation: mapred.map.tasks is deprecated. Instead, use mapreduce.job.maps
	15/06/06 22:59:01 INFO Configuration.deprecation: mapred.output.key.class is deprecated. Instead, use mapreduce.job.output.key.class
	15/06/06 22:59:01 INFO Configuration.deprecation: mapred.mapoutput.key.class is deprecated. Instead, use mapreduce.map.output.key.class
	15/06/06 22:59:01 INFO Configuration.deprecation: mapred.working.dir is deprecated. Instead, use mapreduce.job.working.dir
	15/06/06 22:59:01 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1431411582818_0060
	15/06/06 22:59:01 INFO impl.YarnClientImpl: Submitted application application_1431411582818_0060 to ResourceManager at hadoop6/10.10.4.130:8032
	15/06/06 22:59:01 INFO mapreduce.Job: The url to track the job: http://hadoop6:8888/proxy/application_1431411582818_0060/
	15/06/06 22:59:01 INFO mapreduce.Job: Running job: job_1431411582818_0060
	15/06/06 22:59:08 INFO mapreduce.Job: Job job_1431411582818_0060 running in uber mode : false
	15/06/06 22:59:08 INFO mapreduce.Job:  map 0% reduce 0%
	15/06/06 22:59:15 INFO mapreduce.Job:  map 100% reduce 0%
	15/06/06 22:59:26 INFO mapreduce.Job:  map 100% reduce 100%
	15/06/06 22:59:26 INFO mapreduce.Job: Job job_1431411582818_0060 completed successfully
	15/06/06 22:59:27 INFO mapreduce.Job: Counters: 43
		File System Counters
			FILE: Number of bytes read=365190
			FILE: Number of bytes written=890621
			FILE: Number of read operations=0
			FILE: Number of large read operations=0
			FILE: Number of write operations=0
			HDFS: Number of bytes read=3113635
			HDFS: Number of bytes written=309154
			HDFS: Number of read operations=6
			HDFS: Number of large read operations=0
			HDFS: Number of write operations=2
		Job Counters 
			Launched map tasks=1
			Launched reduce tasks=1
			Rack-local map tasks=1
			Total time spent by all maps in occupied slots (ms)=30228
			Total time spent by all reduces in occupied slots (ms)=51204
		Map-Reduce Framework
			Map input records=8682
			Map output records=336750
			Map output bytes=4331728
			Map output materialized bytes=365190
			Input split bytes=163
			Combine input records=336750
			Combine output records=14868
			Reduce input groups=14868
			Reduce shuffle bytes=365190
			Reduce input records=14868
			Reduce output records=14868
			Spilled Records=29736
			Shuffled Maps =1
			Failed Shuffles=0
			Merged Map outputs=1
			GC time elapsed (ms)=262
			CPU time spent (ms)=5470
			Physical memory (bytes) snapshot=890617856
			Virtual memory (bytes) snapshot=5305532416
			Total committed heap usage (bytes)=811794432
		Shuffle Errors
			BAD_ID=0
			CONNECTION=0
			IO_ERROR=0
			WRONG_LENGTH=0
			WRONG_MAP=0
			WRONG_REDUCE=0
		File Input Format Counters 
			Bytes Read=3113472
		File Output Format Counters 
			Bytes Written=309154
	15/06/06 22:59:27 INFO dag.DAGMonitorImpl: Vertex finished: dag=MixedMRJobAndJavaApplicationDAG, vertex=MapreduceApplication
	15/06/06 22:59:27 INFO dag.DAGMonitorImpl: Vertex started: dag=MixedMRJobAndJavaApplicationDAG, vertex=MapreduceApplication
	15/06/06 22:59:27 INFO client.RMProxy: Connecting to ResourceManager at hadoop6/10.10.4.130:8032
	15/06/06 22:59:27 WARN mapreduce.JobSubmitter: Hadoop command-line option parsing not performed. Implement the Tool interface and execute your application with ToolRunner to remedy this.
	15/06/06 22:59:27 INFO input.FileInputFormat: Total input paths to process : 1
	15/06/06 22:59:27 INFO mapreduce.JobSubmitter: number of splits:1
	15/06/06 22:59:27 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1431411582818_0061
	15/06/06 22:59:27 INFO impl.YarnClientImpl: Submitted application application_1431411582818_0061 to ResourceManager at hadoop6/10.10.4.130:8032
	15/06/06 22:59:27 INFO mapreduce.Job: The url to track the job: http://hadoop6:8888/proxy/application_1431411582818_0061/
	15/06/06 22:59:27 INFO mapreduce.Job: Running job: job_1431411582818_0061
	15/06/06 22:59:33 INFO mapreduce.Job: Job job_1431411582818_0061 running in uber mode : false
	15/06/06 22:59:33 INFO mapreduce.Job:  map 0% reduce 0%
	15/06/06 22:59:44 INFO mapreduce.Job:  map 100% reduce 0%
	15/06/06 22:59:55 INFO mapreduce.Job:  map 100% reduce 100%
	15/06/06 22:59:55 INFO mapreduce.Job: Job job_1431411582818_0061 completed successfully
	15/06/06 22:59:55 INFO mapreduce.Job: Counters: 43
		File System Counters
			FILE: Number of bytes read=2077212
			FILE: Number of bytes written=4314665
			FILE: Number of read operations=0
			FILE: Number of large read operations=0
			FILE: Number of write operations=0
			HDFS: Number of bytes read=18322083
			HDFS: Number of bytes written=1778253
			HDFS: Number of read operations=6
			HDFS: Number of large read operations=0
			HDFS: Number of write operations=2
		Job Counters 
			Launched map tasks=1
			Launched reduce tasks=1
			Data-local map tasks=1
			Total time spent by all maps in occupied slots (ms)=49362
			Total time spent by all reduces in occupied slots (ms)=56112
		Map-Reduce Framework
			Map input records=49851
			Map output records=1934924
			Map output bytes=25327820
			Map output materialized bytes=2077212
			Input split bytes=163
			Combine input records=1934924
			Combine output records=79189
			Reduce input groups=79189
			Reduce shuffle bytes=2077212
			Reduce input records=79189
			Reduce output records=79189
			Spilled Records=158378
			Shuffled Maps =1
			Failed Shuffles=0
			Merged Map outputs=1
			GC time elapsed (ms)=345
			CPU time spent (ms)=9410
			Physical memory (bytes) snapshot=932732928
			Virtual memory (bytes) snapshot=5276262400
			Total committed heap usage (bytes)=893714432
		Shuffle Errors
			BAD_ID=0
			CONNECTION=0
			IO_ERROR=0
			WRONG_LENGTH=0
			WRONG_MAP=0
			WRONG_REDUCE=0
		File Input Format Counters 
			Bytes Read=18321920
		File Output Format Counters 
			Bytes Written=1778253
	15/06/06 22:59:55 INFO dag.DAGMonitorImpl: Vertex finished: dag=MixedMRJobAndJavaApplicationDAG, vertex=MapreduceApplication
	15/06/06 22:59:55 INFO dag.DAGMonitorImpl: Vertex started: dag=MixedMRJobAndJavaApplicationDAG, vertex=sleepAndPrint
	15/06/06 22:59:55 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=10
	15/06/06 22:59:58 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=9
	15/06/06 23:00:01 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=8
	15/06/06 23:00:04 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=7
	15/06/06 23:00:07 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=6
	15/06/06 23:00:10 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=5
	15/06/06 23:00:13 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=4
	15/06/06 23:00:16 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=3
	15/06/06 23:00:19 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=2
	15/06/06 23:00:22 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=1
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl: Vertex finished: dag=MixedMRJobAndJavaApplicationDAG, vertex=sleepAndPrint
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl: DAG finished: dag=MixedMRJobAndJavaApplicationDAG, date=2015-06-06 23:00:25
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl: DAG[MixedMRJobAndJavaApplicationDAG]: 
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:     status    : SUCCESS
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:     startDate : 2015-06-06 22:59:00
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:     finishDate: 2015-06-06 23:00:25
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl: 
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:     timeTaken : 85728 ms
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:     VERTEX[MixedMRJobAndJavaApplicationDAG -> MapreduceApplication]: 
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:         status    : SUCCESS
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:         startDate : 2015-06-06 22:59:00
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:         finishDate: 2015-06-06 22:59:27
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl: 
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:         timeTaken : 26945 ms
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:     VERTEX[MixedMRJobAndJavaApplicationDAG -> MapreduceApplication]: 
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:         status    : SUCCESS
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:         startDate : 2015-06-06 22:59:27
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:         finishDate: 2015-06-06 22:59:55
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl: 
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:         timeTaken : 28779 ms
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:     VERTEX[MixedMRJobAndJavaApplicationDAG -> sleepAndPrint]: 
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:         status    : SUCCESS
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:         startDate : 2015-06-06 22:59:55
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:         finishDate: 2015-06-06 23:00:25
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl: 
	15/06/06 23:00:25 INFO dag.DAGMonitorImpl:         timeTaken : 30003 ms
