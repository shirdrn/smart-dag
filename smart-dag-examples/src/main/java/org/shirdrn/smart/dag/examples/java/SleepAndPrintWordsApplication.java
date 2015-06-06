package org.shirdrn.smart.dag.examples.java;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shirdrn.smart.dag.java.JavaApplication;

public class SleepAndPrintWordsApplication extends JavaApplication {

	private static final Log LOG = LogFactory.getLog(SleepAndPrintWordsApplication.class); 
	
	public SleepAndPrintWordsApplication(String name) {
		super(name);
	}
	
	@Override
	public void perform() {
		int maxSleepTimes = 10;
		int sleep = 3000;
		while(maxSleepTimes>0) {
			try {
				LOG.info("Sleeping..., maxSleepTimes=" + maxSleepTimes);
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				--maxSleepTimes;
			}
		}

	}

}
