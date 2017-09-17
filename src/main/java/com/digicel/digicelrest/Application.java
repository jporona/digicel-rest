package com.digicel.digicelrest;

import javax.jms.ConnectionFactory;
import javax.naming.Context;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@ImportResource("classpath:camel-routes.xml")
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		setUpDatasourceJNDI();
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public SpringCamelContext camelContext(
			org.springframework.context.ApplicationContext ctx)
			throws Exception {
		SpringCamelContext camelCtx = new SpringCamelContext(ctx);

		return camelCtx;

	}

	@Bean
	public RoutesBuilder routerBuilder() {
		// return new SimpleRoute();
		return null;
	}

	public static void setUpDatasourceJNDI() throws Exception {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"vm://localhost?broker.persistent=false");

		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.naming.java.javaURLContextFactory");
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		//
		builder.bind("osgi:service/jms/jmsCF", connectionFactory);
		//
		builder.activate();

	}

}
