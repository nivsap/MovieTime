package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.ArrayList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchaser;


public class ReportController {
	public static List<Purchaser> getTicketReportMonthly(int month , Cinema cinema) {
		List<Purchaser> toReturn = new ArrayList<>();
		for(Purchaser purchaser : cinema.getCustomers()) {
			if(purchaser.getPurchaseDate().getDayOfMonth() == month && purchaser.getCinemaTab().getKey() == false && purchaser.isWatchFromHome() == false) {
				toReturn.add(purchaser);
			}
		}
		return toReturn;	
	}
	public static List<Purchaser> getSpecialTicketReportMonthly(int month , Cinema cinema) {
		List<Purchaser> toReturn = new ArrayList<>();
		for(Purchaser purchaser : cinema.getCustomers()) {
			if(purchaser.getPurchaseDate().getDayOfMonth() == month && (purchaser.getCinemaTab().getKey() == true || purchaser.isWatchFromHome() == true)) {
				toReturn.add(purchaser);
			}
		}
		return toReturn;	
	}
	public static List<Purchaser> statusComplaintsMonthly(int month , Cinema cinema) {
		List<Purchaser> toReturn = new ArrayList<>();
		for(Purchaser purchaser : cinema.getCustomers()) {
			if(purchaser.getPurchaseDate().getDayOfMonth() == month && purchaser.getComplaint().isStatus() == true) {
				toReturn.add(purchaser);
			}
		}
		return toReturn;	
	}
}
