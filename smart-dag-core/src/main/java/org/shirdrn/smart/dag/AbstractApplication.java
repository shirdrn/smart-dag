package org.shirdrn.smart.dag;

@InterfaceAudience.Private
public abstract class AbstractApplication extends AbstractNameableComponent<Application> implements Application {

	public AbstractApplication() {
		super();
	}
	
	public AbstractApplication(String name) {
		super(name);
	}
	
}
