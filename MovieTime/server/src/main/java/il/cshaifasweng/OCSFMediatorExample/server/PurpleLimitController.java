package il.cshaifasweng.OCSFMediatorExample.server;

import java.time.LocalDate;
import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.PurpleLimit;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.util.Pair;

public class PurpleLimitController {
	
	public static ArrayList<PurpleLimit> getActivePurpleLimits() {
		ArrayList<PurpleLimit> list = Main.getAllOfType(PurpleLimit.class);
		ArrayList<PurpleLimit> returnList = new ArrayList<>();
		LocalDate today = LocalDate.now();
 		for(PurpleLimit p : list) {
			if(today.isBefore(p.getToDate()))
				returnList.add(p);
		}
		return returnList;
	}
	
	public static Pair<Boolean,Integer> checkPurpleLimit(LocalDate date) {
		ArrayList<PurpleLimit> purpleLimits = Main.getAllOfType(PurpleLimit.class);
		for(PurpleLimit p: purpleLimits) {
			if(date.isAfter(p.getFromDate()) && date.isBefore(p.getToDate()))
				return new Pair<Boolean,Integer>(true, p.getY());
		}
		return new Pair<Boolean,Integer>(false, 0);
	}
	
	public static void cancelPurchases(LocalDate fromDate, LocalDate toDate) {
		try {
			ArrayList<Screening> allScreenings = Main.getAllOfType(Screening.class);
			for(Screening screening : allScreenings) {
				LocalDate screeningDate = screening.getDate();
				if(screeningDate.isAfter(fromDate) && screeningDate.isBefore(toDate)) {
					screening.getCinema().getCanceledPurchases().addAll(screening.getPurchases());
					screening.initSeats(); //need to check if its working
					for(Purchase purchase : screening.getPurchases()) {
						JavaMailUtil.sendMessage(purchase.getEmail(), "Cancellation of purchase at Sirtiya", "Due to a purple restriction, we are noble to cancel the screening and return the money to you. You are welcome to purchase another ticket in the app");
					}
					Main.updateRowDB(screening);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int[][] setSeatsPurpleLimit(Screening screening , int numOfSeats) {
	    int size = screening.getSeats().length * screening.getSeats()[0].length;
	    int sumOfPeople = screening.getPurchases().size();
	    int limit = 0 ;
	    if(size > 80)
	    	limit = 70;
	    else if(size <= 80 && size >= 56)
	    	limit = (size * 80)/100;
	    else limit = size/2;
	    int temp = 0;
	    int[][] toReturn = new int[numOfSeats][2];
	    if(sumOfPeople + numOfSeats < limit) {
	    	for(int i = 0 ; i < screening.getSeats().length ; i++) {
	    		for(int j = 0 ; j < screening.getSeats()[0].length -1 ; j++) {
	    			if(screening.getSeats()[i][j] == 0) {
	    				screening.getSeats()[i][j] = 1;
	    				toReturn[temp][0] = i;
	    				toReturn[temp][1] = j;
	    				temp++;
	    			}
	    		}
	    	}
	    	Main.updateRowDB(screening);
	    	return toReturn;
	    }
		return null;
	}
}
