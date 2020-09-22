package org.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
//		 <route id="simple-route">
//         <from id="route-timer" uri="timer://foo?period=1000"/>
//         <setBody id="route-setBody">
//             <simple>Hello World from camel-context.xml</simple>
//         </setBody>
//         <log id="route-log" message=">>> ${body}"/>
//         
//     </route>
		
		from("timer://foo?period=1000").setBody(simple("Hello World from JavaDSL"))
		.log(">>> ${body}");
		
	}
	
	

}
