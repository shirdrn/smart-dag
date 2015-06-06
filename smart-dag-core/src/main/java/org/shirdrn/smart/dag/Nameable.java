package org.shirdrn.smart.dag;

public interface Nameable<T> {

	Nameable<T> setName(String name);
	String getName();
}
