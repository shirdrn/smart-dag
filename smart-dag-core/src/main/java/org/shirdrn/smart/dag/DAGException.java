package org.shirdrn.smart.dag;

public class DAGException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DAGException() {
		super();
	}
	
	public DAGException(String message) {
		super(message);
	}
	
	public DAGException(String message, Throwable cause) {
		super(message, cause);
	}

}
