package il.cshaifasweng.OCSFMediatorExample.server;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;

public class ReportController {
	public static List<Purchase> getTicketReportMonthly(Cinema cinema) {
		int currentMonth = LocalDate.now().getMonthValue();
		List<Purchase> toReturn = new ArrayList<>();
		for(Purchase purchase : cinema.getPurchases()) {
			if(purchase.getPurchaseTime().getDayOfMonth() == currentMonth && purchase.isCard() == false && purchase.isLink() == false) {
				toReturn.add(purchase);
			}
		}
		return toReturn;	
	}
	public static List<Purchase> getSpecialTicketReportMonthly(Cinema cinema) {
		int currentMonth = LocalDate.now().getMonthValue();
		List<Purchase> toReturn = new ArrayList<>();
		for(Purchase purchase : cinema.getPurchases()) {
			if(purchase.getPurchaseTime().getDayOfMonth() == currentMonth && (purchase.isCard() == true || purchase.isLink() == true)) {
				toReturn.add(purchase);
			}
		}
		return toReturn;	
	}
	public static List<Purchase> statusComplaintsMonthly(Cinema cinema) {
		int currentMonth = LocalDate.now().getMonthValue();
		List<Purchase> toReturn = new ArrayList<>();
		for(Purchase purchase : cinema.getPurchases()) {
			if(purchase.getPurchaseTime().getDayOfMonth() == currentMonth && purchase.getComplaint().isOpen()) {
				toReturn.add(purchase);
			}
		}
		return toReturn;	
	}
}
