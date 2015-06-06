# smart-dag-examples

[stone@hadoop6 shirdrn]$ yarn jar smart-dag-examples-0.0.1-SNAPSHOT.jar org.shirdrn.smart.dag.examples.MixedMRJobAndJavaApplicationDAG

15/06/06 22:23:03 INFO dag.DAGMonitorImpl: DAG created: dag=MixedMRJobAndJavaApplicationDAG
15/06/06 22:23:04 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
15/06/06 22:23:04 INFO dag.DAGMonitorImpl: Vertex created: dag=MixedMRJobAndJavaApplicationDAG, vertex=MapreduceApplication
15/06/06 22:23:04 INFO mapreduce.MapreduceBuilder: Vertex built: name=MapreduceApplication
15/06/06 22:23:04 INFO examples.MixedMRJobAndJavaApplicationDAG: wc1
15/06/06 22:23:04 INFO examples.MixedMRJobAndJavaApplicationDAG: /test/data/tmp/basis_common/basis_common_20141119143707376_2_0000.log.COMPLETED
15/06/06 22:23:04 INFO examples.MixedMRJobAndJavaApplicationDAG: /test/data/tmp/wc1out
15/06/06 22:23:04 INFO dag.DAGMonitorImpl: Vertex created: dag=MixedMRJobAndJavaApplicationDAG, vertex=MapreduceApplication
15/06/06 22:23:04 INFO mapreduce.MapreduceBuilder: Vertex built: name=MapreduceApplication
15/06/06 22:23:04 INFO examples.MixedMRJobAndJavaApplicationDAG: wc2
15/06/06 22:23:04 INFO examples.MixedMRJobAndJavaApplicationDAG: /test/data/tmp/basis_common/basis_common_20141120153852727_2_0000.log.COMPLETED
15/06/06 22:23:04 INFO examples.MixedMRJobAndJavaApplicationDAG: /test/data/tmp/wc2out
15/06/06 22:23:04 INFO dag.DAGMonitorImpl: Vertex created: dag=MixedMRJobAndJavaApplicationDAG, vertex=sleepAndPrint
15/06/06 22:23:04 INFO dag.DAGMonitorImpl: MixedMRJobAndJavaApplicationDAG.APPEND: [MapreduceApplication]
15/06/06 22:23:04 INFO dag.DAGMonitorImpl: MixedMRJobAndJavaApplicationDAG.APPEND: [MapreduceApplication]
15/06/06 22:23:04 INFO dag.DAGMonitorImpl: MixedMRJobAndJavaApplicationDAG.APPEND: [sleepAndPrint]
15/06/06 22:23:04 INFO dag.DAGMonitorImpl: DAG started: dag=MixedMRJobAndJavaApplicationDAG, date=2015-06-06 22:23:04
15/06/06 22:23:04 INFO dag.DAGMonitorImpl: Vertex started: dag=MixedMRJobAndJavaApplicationDAG, vertex=MapreduceApplication
15/06/06 22:23:05 INFO client.RMProxy: Connecting to ResourceManager at hadoop6/10.10.4.130:8032
15/06/06 22:23:05 WARN mapreduce.JobSubmitter: Hadoop command-line option parsing not performed. Implement the Tool interface and execute your application with ToolRunner to remedy this.
15/06/06 22:23:06 INFO input.FileInputFormat: Total input paths to process : 1
15/06/06 22:23:06 INFO mapreduce.JobSubmitter: number of splits:1
15/06/06 22:23:06 INFO Configuration.deprecation: user.name is deprecated. Instead, use mapreduce.job.user.name
15/06/06 22:23:06 INFO Configuration.deprecation: mapred.jar is deprecated. Instead, use mapreduce.job.jar
15/06/06 22:23:06 INFO Configuration.deprecation: mapred.reduce.tasks is deprecated. Instead, use mapreduce.job.reduces
15/06/06 22:23:06 INFO Configuration.deprecation: mapred.output.value.class is deprecated. Instead, use mapreduce.job.output.value.class
15/06/06 22:23:06 INFO Configuration.deprecation: mapred.mapoutput.value.class is deprecated. Instead, use mapreduce.map.output.value.class
15/06/06 22:23:06 INFO Configuration.deprecation: mapreduce.combine.class is deprecated. Instead, use mapreduce.job.combine.class
15/06/06 22:23:06 INFO Configuration.deprecation: mapreduce.map.class is deprecated. Instead, use mapreduce.job.map.class
15/06/06 22:23:06 INFO Configuration.deprecation: mapred.job.name is deprecated. Instead, use mapreduce.job.name
15/06/06 22:23:06 INFO Configuration.deprecation: mapreduce.reduce.class is deprecated. Instead, use mapreduce.job.reduce.class
15/06/06 22:23:06 INFO Configuration.deprecation: mapred.input.dir is deprecated. Instead, use mapreduce.input.fileinputformat.inputdir
15/06/06 22:23:06 INFO Configuration.deprecation: mapred.output.dir is deprecated. Instead, use mapreduce.output.fileoutputformat.outputdir
15/06/06 22:23:06 INFO Configuration.deprecation: mapred.map.tasks is deprecated. Instead, use mapreduce.job.maps
15/06/06 22:23:06 INFO Configuration.deprecation: mapred.output.key.class is deprecated. Instead, use mapreduce.job.output.key.class
15/06/06 22:23:06 INFO Configuration.deprecation: mapred.mapoutput.key.class is deprecated. Instead, use mapreduce.map.output.key.class
15/06/06 22:23:06 INFO Configuration.deprecation: mapred.working.dir is deprecated. Instead, use mapreduce.job.working.dir
15/06/06 22:23:06 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1431411582818_0058
15/06/06 22:23:06 INFO impl.YarnClientImpl: Submitted application application_1431411582818_0058 to ResourceManager at hadoop6/10.10.4.130:8032
15/06/06 22:23:06 INFO mapreduce.Job: The url to track the job: http://hadoop6:8888/proxy/application_1431411582818_0058/
15/06/06 22:23:06 INFO mapreduce.Job: Running job: job_1431411582818_0058
15/06/06 22:23:12 INFO mapreduce.Job: Job job_1431411582818_0058 running in uber mode : false
15/06/06 22:23:12 INFO mapreduce.Job:  map 0% reduce 0%
15/06/06 22:23:19 INFO mapreduce.Job:  map 100% reduce 0%
15/06/06 22:23:31 INFO mapreduce.Job:  map 100% reduce 100%
15/06/06 22:23:32 INFO mapreduce.Job: Job job_1431411582818_0058 completed successfully
15/06/06 22:23:32 INFO mapreduce.Job: Counters: 43
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
		Data-local map tasks=1
		Total time spent by all maps in occupied slots (ms)=30624
		Total time spent by all reduces in occupied slots (ms)=51768
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
		GC time elapsed (ms)=267
		CPU time spent (ms)=5820
		Physical memory (bytes) snapshot=924708864
		Virtual memory (bytes) snapshot=5298700288
		Total committed heap usage (bytes)=785121280
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
15/06/06 22:23:32 INFO dag.DAGMonitorImpl: Vertex finished: dag=MixedMRJobAndJavaApplicationDAG, vertex=MapreduceApplication
15/06/06 22:23:32 INFO dag.DAGMonitorImpl: Vertex started: dag=MixedMRJobAndJavaApplicationDAG, vertex=MapreduceApplication
15/06/06 22:23:32 INFO client.RMProxy: Connecting to ResourceManager at hadoop6/10.10.4.130:8032
15/06/06 22:23:32 WARN mapreduce.JobSubmitter: Hadoop command-line option parsing not performed. Implement the Tool interface and execute your application with ToolRunner to remedy this.
15/06/06 22:23:32 INFO input.FileInputFormat: Total input paths to process : 1
15/06/06 22:23:32 INFO mapreduce.JobSubmitter: number of splits:1
15/06/06 22:23:32 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1431411582818_0059
15/06/06 22:23:32 INFO impl.YarnClientImpl: Submitted application application_1431411582818_0059 to ResourceManager at hadoop6/10.10.4.130:8032
15/06/06 22:23:32 INFO mapreduce.Job: The url to track the job: http://hadoop6:8888/proxy/application_1431411582818_0059/
15/06/06 22:23:32 INFO mapreduce.Job: Running job: job_1431411582818_0059
15/06/06 22:23:38 INFO mapreduce.Job: Job job_1431411582818_0059 running in uber mode : false
15/06/06 22:23:38 INFO mapreduce.Job:  map 0% reduce 0%
15/06/06 22:23:48 INFO mapreduce.Job:  map 100% reduce 0%
15/06/06 22:24:01 INFO mapreduce.Job:  map 100% reduce 100%
15/06/06 22:24:01 INFO mapreduce.Job: Job job_1431411582818_0059 completed successfully
15/06/06 22:24:01 INFO mapreduce.Job: Counters: 43
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
		Total time spent by all maps in occupied slots (ms)=47376
		Total time spent by all reduces in occupied slots (ms)=54828
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
		GC time elapsed (ms)=328
		CPU time spent (ms)=9290
		Physical memory (bytes) snapshot=930209792
		Virtual memory (bytes) snapshot=5283569664
		Total committed heap usage (bytes)=891355136
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
15/06/06 22:24:01 INFO dag.DAGMonitorImpl: Vertex finished: dag=MixedMRJobAndJavaApplicationDAG, vertex=MapreduceApplication
15/06/06 22:24:01 INFO dag.DAGMonitorImpl: Vertex started: dag=MixedMRJobAndJavaApplicationDAG, vertex=sleepAndPrint
15/06/06 22:24:01 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=10
15/06/06 22:24:04 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=9
15/06/06 22:24:07 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=8
15/06/06 22:24:10 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=7
15/06/06 22:24:13 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=6
15/06/06 22:24:16 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=5
15/06/06 22:24:19 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=4
15/06/06 22:24:22 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=3
15/06/06 22:24:25 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=2
15/06/06 22:24:28 INFO java.SleepAndPrintWordsApplication: Sleeping..., maxSleepTimes=1
15/06/06 22:24:31 INFO dag.DAGMonitorImpl: Vertex finished: dag=MixedMRJobAndJavaApplicationDAG, vertex=sleepAndPrint
15/06/06 22:24:31 INFO dag.DAGMonitorImpl: DAG finished: dag=MixedMRJobAndJavaApplicationDAG, date=2015-06-06 22:24:31
15/06/06 22:24:31 INFO dag.DAGMonitorImpl: DAG[MixedMRJobAndJavaApplicationDAG]: 
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:     status    : SUCCESS
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:     startDate : 2015-06-06 22:23:04
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:     finishDate: 2015-06-06 22:24:31
15/06/06 22:24:31 INFO dag.DAGMonitorImpl: 
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:     timeTaken : 86134 ms
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:     VERTEX[MixedMRJobAndJavaApplicationDAG -> MapreduceApplication]: 
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:         status    : SUCCESS
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:         startDate : 2015-06-06 22:23:04
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:         finishDate: 2015-06-06 22:23:32
15/06/06 22:24:31 INFO dag.DAGMonitorImpl: 
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:         timeTaken : 27196 ms
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:     VERTEX[MixedMRJobAndJavaApplicationDAG -> MapreduceApplication]: 
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:         status    : SUCCESS
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:         startDate : 2015-06-06 22:23:32
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:         finishDate: 2015-06-06 22:24:01
15/06/06 22:24:31 INFO dag.DAGMonitorImpl: 
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:         timeTaken : 28934 ms
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:     VERTEX[MixedMRJobAndJavaApplicationDAG -> sleepAndPrint]: 
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:         status    : SUCCESS
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:         startDate : 2015-06-06 22:24:01
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:         finishDate: 2015-06-06 22:24:31
15/06/06 22:24:31 INFO dag.DAGMonitorImpl: 
15/06/06 22:24:31 INFO dag.DAGMonitorImpl:         timeTaken : 30003 ms
