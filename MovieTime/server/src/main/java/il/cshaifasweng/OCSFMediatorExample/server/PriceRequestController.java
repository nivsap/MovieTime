package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.PriceRequest;

public class PriceRequestController {
	
	
	public static ArrayList<PriceRequest> getAllOpenPriceRequests() {
		ArrayList<PriceRequest> list = Main.getAllOfType(PriceRequest.class);
		ArrayList<PriceRequest> returnList = new ArrayList<>();
 		for(PriceRequest p : list) {
			if(p.isOpen())
				returnList.add(p);
		}
		return returnList;
	}
}
