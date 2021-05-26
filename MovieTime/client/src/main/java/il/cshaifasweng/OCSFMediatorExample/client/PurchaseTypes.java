package il.cshaifasweng.OCSFMediatorExample.client;

public final class PurchaseTypes {
	static final int TICKET = 1;
	static final int VIEWING_PACKAGE = 2;
	static final int SUBSCRIPTION_CARD = 3;
	static final int NOT_AVAILABLE = 0;
	
	public static int getType(String pageName) {
		if(pageName.equals("MainPage")) {
			return PurchaseTypes.TICKET;
		}
		
		if(pageName.equals("ViewingPackages")) {
			return PurchaseTypes.VIEWING_PACKAGE;
		}
		
		if(pageName.equals("SubscriptionCards")) {
			return PurchaseTypes.SUBSCRIPTION_CARD;
		}
		
		return PurchaseTypes.NOT_AVAILABLE;
	}
}
