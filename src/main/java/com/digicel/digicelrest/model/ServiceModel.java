package com.digicel.digicelrest.model;

import java.util.HashMap;
import java.util.Map;

public class ServiceModel {

	private Map<String, Object> parameters = new HashMap<String, Object>();

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

}
