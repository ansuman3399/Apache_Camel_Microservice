package com.apache.Camel.routes;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class FirstTimerRouter extends RouteBuilder{

	@Autowired
	private GetCurrentTime getCurrentTime;
	
	@Autowired
	private SimpleLoggingProcessingComponent simpleProcessingComponent;
	
	@Override
	public void configure() throws Exception {
		from("timer:test-timer")
		.log("${body}")
		.transform().constant("Constant message")
		.log("${body}")
		.bean(getCurrentTime,"getCurrentTime")
		.log("${body}")
		.bean(simpleProcessingComponent)
		.process(new SampleLoggingProcessor())
		.to("log:test-timer");
	}

}
@Component
class GetCurrentTime{
	public String getCurrentTime() {
		return "logging at : "+LocalDateTime.now();
	}
}

@Component
class SimpleLoggingProcessingComponent{
	private Logger logger=org.slf4j.LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
	public void process(String message) {
		logger.info("SimpleLoggingProcessingComponent {}",message);
	}
}
@Component
class SampleLoggingProcessor implements Processor {

	private Logger logger=org.slf4j.LoggerFactory.getLogger(SampleLoggingProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("SampleLoggingProcessor {}",exchange.getMessage().getBody());
	}

}