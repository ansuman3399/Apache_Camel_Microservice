package com.apache.Camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:files/input")
		.to("file:files/output");
	}
	
}
