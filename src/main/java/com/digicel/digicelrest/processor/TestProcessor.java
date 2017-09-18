package com.digicel.digicelrest.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestProcessor implements Processor{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestProcessor.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		byte[] bytes = exchange.getIn().getBody(String.class).getBytes();
		
		LOGGER.info("....... longitud="+bytes.length);
		
	}
	
	

}
