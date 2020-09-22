package org.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class CBRRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		// Simple Plain English
		// JavaScript,Python,Ruby
		
//		  <route id="simple-route">
//          <from id="route-timer" uri="timer://foo?period=1000"/>
//          <setBody id="route-setBody">
//              <simple>Hello World from camel-context.xml</simple>
//          </setBody>
//          <log id="route-log" message=">>> ${body}"/>
//      </route>

		// Content Based Router
		
		from("file:data/cbr/input1?noop=true")
		.log("Receiving Order ${file:name}")

		.choice()
		.when()
		.xpath("/order/customer/country='UK'")
		.log("Sending Orders ${file:name} to UK2")
		.to("file:output/cbr/uk2")
		.when()
		.xpath("/order/customer/country='US'")
		.log("Sending Orders ${file:name} to US2")
		.to("file:output/cbr/us2")
		.otherwise()
		.log("Sending Orders ${file:name} to others2")
		.to("file:output/cbr/others2")
		.log("Done Processing of Orders ${file:name}");
		
	}

}
