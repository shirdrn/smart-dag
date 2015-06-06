package org.shirdrn.smart.dag;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class DAGConfigImpl extends PropertiesConfiguration implements DAGConfig {

	private static final String CONFIG = "dag.properties";
	
	public DAGConfigImpl() throws ConfigurationException {
		this(CONFIG);
	}
	
	public DAGConfigImpl(String config) throws ConfigurationException {
		super(config);
	}
}
