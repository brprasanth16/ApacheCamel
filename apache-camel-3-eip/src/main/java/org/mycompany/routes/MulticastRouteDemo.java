package org.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class MulticastRouteDemo extends RouteBuilder {

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
		
		// order.customer.country=UK
		
		from("kafka:name1?brokers=localhost:9092")
		.log("Receiving Order ")
.multicast().parallelProcessing().to("kafka:aggregateOfOrderDeliveryCenter2?brokers=localhost:9092",
		"kafka:aggregateOfOrderDeliveryCenter3?brokers=localhost:9092",
		"kafka:aggregateOfOrderDeliveryCenter4?brokers=localhost:9092");
		
	}

}

// Zookeeper











// Creating Real Time Data Streams

// Process Real Time Data Streams


// ElectronicMeterIOTEnabled  --> IOTHub  ------>   Apache Kafka  ---> ApacheCamel --> ProcessRealtimeData ---> SendSMS



//ElectronicMeterIOTEnabled

//ElectronicMeterIOTEnabled



// Asynchronous Communication


// Message Publisher (IOTHub)  ---->   MessageBroker    <------ MessageConsumer (ApacheCamel)

// Apache Kafka Evolved
// ServerSoftware Broker
// ClientAPI (JavaLibrary) 1. ProducerAPI 2. ConsumerAPI
// kafkaConnect
// KafkaStreams
// KSQL

// Five Major Components
// 1.kafka Broker
// 2. KafkaClient
// 3. KafkaConnect
// 4. KafkaStreams
// 5. KSQl (Commercial Version Which is Managed By Confluent)


// WebApps --> ApacheKafka <--- IOT


// KafkaConnect is a InvoicingApplication Backed With Database --> {SourceConnector KafkaConnect} --> KafkaCluster <-- {SinkConnector KafkaConnect} <--- Finance | Analytics 


// KafkaStream is a JavaLibrary all the input data is streamed inside a Kafkatopic 

// ParallelProcessing,scalibility,faulttolerance
































