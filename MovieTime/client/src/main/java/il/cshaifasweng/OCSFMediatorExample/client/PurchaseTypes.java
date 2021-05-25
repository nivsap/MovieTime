package il.cshaifasweng.OCSFMediatorExample.client;

public final class PurchaseTypes {
	static final int TICKET = 1;
	static final int VIEWING_PACKAGE = 2;
	static final int SUBSCRIPTION_CARD = 3;
	static final int NOT_AVAILABLE = 0;
	
	public static int getType(String pageName) {
		if(pageName == "MainPage") {
			return PurchaseTypes.TICKET;
		}
		
		if(pageName == "ViewingPackages") {
			return PurchaseTypes.VIEWING_PACKAGE;
		}
		
		if(pageName == "SubscriptionCards") {
			return PurchaseTypes.SUBSCRIPTION_CARD;
		}
		
		return PurchaseTypes.NOT_AVAILABLE;
	}
}
