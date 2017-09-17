package com.digicel.digicelrest.processor;

import java.util.Map;
import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.digicel.digicelrest.model.ServiceModel;

public class ParameterProcessor implements Processor {

	private Properties props;

	private static final Logger LOGGER = LoggerFactory.getLogger(ParameterProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		Map<String, Object> parametros = ((ServiceModel) ((org.apache.cxf.message.MessageContentsList) exchange
				.getIn().getBody()).get(0)).getParameters();

		String service = (String) exchange.getIn().getHeaders().get("CamelHttpPath");
		
		String authorization = (String) exchange.getIn().getHeaders().get("Authorization");
		
		LOGGER.info("SERVICIO = " + service);
		LOGGER.info("parameters=" + parametros);
		
		
		String path = props.getProperty(service + ".path");
		String method = props.getProperty(service + ".method");
		
		LOGGER.info("PATH = " + path);
		LOGGER.info("METHOD= " + method);
		
		 StrSubstitutor sub = new StrSubstitutor(parametros);		 
		 String resolvedPath = sub.replace(path);
		 
		 exchange.setProperty("PATH", resolvedPath);
		 exchange.setProperty("METHOD", method);
		 exchange.setProperty("PARAMETERS", parametros);
		 exchange.setProperty("AUTHORIZATION", authorization);
		 
//		 	<!-- POST, GET, DELETE, PUT -->
//			<log message="Processing CXF route....http method ${header.CamelHttpMethod}" />
		 exchange.getIn().setHeader("CamelHttpMethod", method);
//			<!-- /getaccount -->
//			<log message="Processing CXF route....path is ${header.CamelHttpPath}" />
		 exchange.getIn().setHeader("CamelHttpPath", path);
//			<!-- EN ESTE CASO EMPTY  -->
//			<log message="Processing CXF route....body is ${body}" />
//			<!-- Basic cHJ1ZWJhOnBydWViYQ== -->
//			<log message="Processing CXF route....body is ${header.Authorization}" />
		 exchange.getIn().setHeader("Authorization", authorization);
		 
		 
		 
//		 exchange.getOut().setHeaders(new HashMap<String, Object>());

	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

}
