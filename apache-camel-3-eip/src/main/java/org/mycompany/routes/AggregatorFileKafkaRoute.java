package org.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.processor.aggregate.GroupedBodyAggregationStrategy;
import org.springframework.stereotype.Component;


//@Component
public class AggregatorFileKafkaRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		// EIP
		
		// Message pattern 
		// Message Channel Pattern
		// Message Endpoint  Pattern
		// Pipes & Filters patterns
		
		// Simple Plain English
		// JavaScript,Python,Ruby
		

		
		
		
		
		from("file:aggregator/input?noop=true").process(exchange->{
			exchange.getIn().setBody(exchange.getIn().getBody(),String.class);
            exchange.getIn().setHeader(KafkaConstants.PARTITION_KEY, 0);
            exchange.getIn().setHeader(KafkaConstants.KEY, "1");
		})
		.log("Receiving Order ${file:name}")
		.routeId("aggregator").aggregate(xpath("/orders/order/customer/@id"),
				new GroupedBodyAggregationStrategy()).completionSize(2).completionTimeout(5000)
		.log("Sending Out Message ${body}").to("kafka:aggregateOfOrdersdeliverycenter1?brokers=localhost:9092");
		
		
		// Custom Aggregation Strategy
		
//		from("file:aggregator/input?noop=true")
//		.log("Receiving Order ${file:name}")
//		.routeId("aggregator").aggregate(xpath("/orders/order/customer/@id"),
//				( oldExchange,newExchange)->{return oldExchange;})
//		.log("Sending Out Message ${body}").to("kafka:aggregateOfOrdersdeliverycenter?brokers=localhost:9092");
//		
//		


		
	}

}
