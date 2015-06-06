package org.shirdrn.smart.dag;

@InterfaceAudience.Private
public abstract class AbstractNameableComponent<T> implements Nameable<T> {

	private final String name;
	
	public AbstractNameableComponent() {
		super();
		this.name = this.getClass().getSimpleName();
	}
	
	public AbstractNameableComponent(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Nameable<T> setName(String name) {
		return this;
	}

}
