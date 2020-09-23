package org.mycompany.routes;

import org.apache.camel.language.xpath.XPath;
import org.springframework.stereotype.Component;

// XPath
// XQuery
// PHP
// Python

@Component("ordersRecipientBean")
public class OrdersRecipientBean {
	
	public String[] recipientList(@XPath("/order/@type")String orderType) {
		
		if(isGadget(orderType))
		return new String[] {"kafka:gadgetsdeliverycenter?brokers=localhost:9092"};
		else {
			if(isHomeApplicance(orderType))
				return new String[] {"kafka:homeappliancesdeliverycenter?brokers=localhost:9092"};
			else if(isPantry(orderType))
				return new String[] {"kafka:pantryitemsdeliverycenter?brokers=localhost:9092"};
		}
		return null;
	}
	
	
	private boolean isGadget(String orderType) {
		return orderType.equalsIgnoreCase("gadgets");
	}
	
	private boolean isHomeApplicance(String orderType) {
		return orderType.equalsIgnoreCase("homeappliances");
	}
	
	private boolean isPantry(String orderType) {
		return orderType.equalsIgnoreCase("pantry");
	}
	

}
