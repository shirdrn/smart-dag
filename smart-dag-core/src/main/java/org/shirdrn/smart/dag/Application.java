package org.shirdrn.smart.dag;

@InterfaceAudience.Private
public interface Application {

	/**
	 * Execute the actual business logic. If you are use it in a multi-threaded
	 * environment, ensure that before exiting {@link #perform()} method, all your
	 * alive threads finish executing normally.
	 * 
	 * @throws ApplicationException
	 */
	void perform() throws ApplicationException;
	
}
