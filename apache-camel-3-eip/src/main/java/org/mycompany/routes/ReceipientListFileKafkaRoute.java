package org.mycompany.routes;

import java.util.Date;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.routepolicy.quartz.SimpleScheduledRoutePolicy;
import org.apache.camel.spi.ThreadPoolProfile;
import org.springframework.stereotype.Component;


@Component
public class ReceipientListFileKafkaRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		// Simple Plain English
		// JavaScript,Python,Ruby
		

		
		// <orders>
		
		// <order type="">
		//	<id></id>
		// <name></name>
		
		//</order>
		
		// <order>
				//	<id></id>
				// <name></name>
				
				//</order>
		//</orders>

		
		// Processors supports parallelism
		
		// 1. recipientlist
		// 2. split
		// 3. wiretap
		// 4. aggregate
		// 5. multicast
		
		
		// Default ThreadPool (maxQueueSize =100,poolSize=10,maxPoolSize=20,rejectedPolicy)
		
		
//		ThreadPoolBuilder poolBuilder = new ThreadPoolBuilder(getContext());
//		ExecutorService customPool=poolBuilder.poolSize(30).maxPoolSize(40).maxQueueSize(100)
//				.rejectedPolicy(ThreadPoolRejectedPolicy.CallerRuns.Abort). build("customPool");
		
		ThreadPoolProfile customProfile = new ThreadPoolProfile("customPool");
		customProfile.setPoolSize(5);
		customProfile.setMaxQueueSize(100);
		customProfile.setMaxPoolSize(5);
		
		SimpleScheduledRoutePolicy policy = new SimpleScheduledRoutePolicy();
		long startTime =System.currentTimeMillis()+300000L;
		policy.setRouteStartDate(new Date(startTime));
		policy.setRouteStartRepeatCount(1);
		policy.setRouteStartRepeatInterval(3000);
		
		
		
		getContext().getExecutorServiceManager().registerThreadPoolProfile(customProfile);
		
		from("file:receipientList/input?noop=true")
		.log("Receiving Order ${file:name}")
		.routeId("receipientList").autoStartup(false).routePolicy(policy).split(xpath("/orders/order"))
		.setHeader("recipients",method("ordersRecipientBean", "recipientList"))
		.recipientList(header("recipients")).executorServiceRef("customPool");
		
		


		
	}

}
