package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.ArrayList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;

public class ReportController {
	public static List<Purchase> getTicketReportMonthly(int month , Cinema cinema) {
		List<Purchase> toReturn = new ArrayList<>();
		for(Purchase purchase : cinema.getCustomers()) {
			if(purchase.getPurchaseTime().getDayOfMonth() == month && purchase.isCard() == false && purchase.isLink() == false) {
				toReturn.add(purchase);
			}
		}
		return toReturn;	
	}
	public static List<Purchase> getSpecialTicketReportMonthly(int month , Cinema cinema) {
		List<Purchase> toReturn = new ArrayList<>();
		for(Purchase purchase : cinema.getCustomers()) {
			if(purchase.getPurchaseTime().getDayOfMonth() == month && (purchase.isCard() == true || purchase.isLink() == true)) {
				toReturn.add(purchase);
			}
		}
		return toReturn;	
	}
	public static List<Purchase> statusComplaintsMonthly(int month , Cinema cinema) {
		List<Purchase> toReturn = new ArrayList<>();
		for(Purchase purchase : cinema.getCustomers()) {
			if(purchase.getPurchaseTime().getDayOfMonth() == month && purchase.getComplaint().getIsOpen() == true) {
				toReturn.add(purchase);
			}
		}
		return toReturn;	
	}
}
