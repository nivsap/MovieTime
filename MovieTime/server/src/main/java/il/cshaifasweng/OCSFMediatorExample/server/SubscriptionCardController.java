package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.SubscriptionCard;

public class SubscriptionCardController {
	
	public static SubscriptionCard getSubscriptionCard(String serial) {
		ArrayList<SubscriptionCard> list = Main.getAllOfType(SubscriptionCard.class);
	
 		for(SubscriptionCard s : list) {
 			if(s.getSerial().equals(serial))
 				return s;
		}
		return null;
	}
}
