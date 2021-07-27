package com.apache.Camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
@Component
public class ActiveMqSenderRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("timer:activemq-timer?period=10000")
		.transform().constant("Test message from activeMq MS-A")
		.log("${body}")
		.to("activemq:my-activemq-queue");
	}	
}
