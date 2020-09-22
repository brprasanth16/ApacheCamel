package org.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class CBRToKafkaRoute extends RouteBuilder {

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
		
		// Builder Pattern to build the components from the URI
		
		//new FileComponent().option('data/cbr').noop(true)
		
		// new KafkaComponent().options('iphonefulfilmentcenter).brokers(localhost:9092)
		
		// Exchange (Message Header() & MessageBody (XML))
		
		
		from("file:data/cbr/input1?noop=true")
		.log("Receiving Order ${file:name}")

		.choice()
		.when()
		.xpath("/order/orderlines/orderline/product/name='IPhone'")
		.log("Sending Orders ${file:name} to UK2")
		.to("kafka:iphonefulfilmentcenter1?brokers=localhost:9092")
		.when()
		.xpath("/order/orderlines/orderline/product/name='OnePlus'")
		.log("Sending Orders ${headers[kafka.TOPIC]} to US2")
		.to("kafka:oneplusfulfilmentcenter1?brokers=localhost:9092")
		.otherwise()
		.log("Sending Orders ${file:name} to others2")
		.to("kafka:othercenters1?brokers=localhost:9092")
		.log("Done Processing of Orders ${file:name}");
		
	}

}
