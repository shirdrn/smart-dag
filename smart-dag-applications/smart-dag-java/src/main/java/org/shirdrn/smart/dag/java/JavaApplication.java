package org.shirdrn.smart.dag.java;

import org.shirdrn.smart.dag.AbstractApplication;
import org.shirdrn.smart.dag.InterfaceAudience;


/**
 * Java application programming interface, which orients to Java
 * programmers to develop the actual business logic.
 * 
 * @author yanjun
 */
@InterfaceAudience.Public
public abstract class JavaApplication extends AbstractApplication {

	public JavaApplication() {
		super();
	}
	
	public JavaApplication(String name) {
		super(name);
	}
}
