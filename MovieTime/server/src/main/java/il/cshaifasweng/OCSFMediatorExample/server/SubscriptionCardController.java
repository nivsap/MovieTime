package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.SubscriptionCard;

public class SubscriptionCardController {
	
	public static SubscriptionCard getSubscriptionCard(int id) {
		ArrayList<SubscriptionCard> list = Main.getAllOfType(SubscriptionCard.class);
	
 		for(SubscriptionCard s : list) {
 			if(s.getId() == id)
 				return s;
		}
		return null;
	}
}
