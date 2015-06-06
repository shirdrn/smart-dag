package org.shirdrn.smart.dag;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;
	private Application application;
	
	public ApplicationException() {
		super();
	}
	
	public ApplicationException(String message) {
		super(message);
	}
	
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

}
