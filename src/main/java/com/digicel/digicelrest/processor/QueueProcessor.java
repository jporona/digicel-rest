package com.digicel.digicelrest.processor;

import java.util.Map;
import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.digicel.digicelrest.model.ServiceModel;

public class QueueProcessor implements Processor {

	private Properties props;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(QueueProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {

		String service = (String) exchange.getIn().getHeaders()
				.get("CamelHttpPath");

		Map<String, Object> parametros = ((ServiceModel) ((org.apache.cxf.message.MessageContentsList) exchange
				.getIn().getBody()).get(0)).getParameters();

		String queueName = props.getProperty(service + ".queue");
		LOGGER.info("SERVICIO = " + service);
		LOGGER.info("parameters=" + parametros);
		LOGGER.info("queue name=" + queueName);

		exchange.setProperty("QUEUE", queueName);
		exchange.setProperty("PARAMETERS", parametros);

		exchange.getIn().setBody(parametros);

		// List<String> input = (List<String>)exchange.getIn().getBody();
		// Object message = input.get(0);
		// String queueName = input.get(1);
		//
		// exchange.setProperty("QUEUE", queueName);
		// //exchange.setProperty("MESSAGE", message);
		// exchange.getOut().setBody(message);
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

}
