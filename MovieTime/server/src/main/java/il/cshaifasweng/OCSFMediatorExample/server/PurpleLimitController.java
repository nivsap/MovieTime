package il.cshaifasweng.OCSFMediatorExample.server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import il.cshaifasweng.OCSFMediatorExample.entities.CustomerService;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;

public class PurpleLimitController {
	
	
	public static void SetPurpleLimit (LocalDateTime fromDate , LocalDateTime toDate) {
		try {
			ArrayList<Screening> allScreenings = Main.getAllOfType(Screening.class);
			for(Screening screen : allScreenings) {
				if(screen.getDate_screen().getDayOfYear() >= fromDate.getDayOfYear() && screen.getDate_screen().getDayOfYear() <= toDate.getDayOfYear()) {
					screen.getCinema().getCancelPurchases().addAll(screen.getPurchases());

					screen.initSeats(); //need to check if its working
					for(Purchase purchase : screen.getPurchases()) {
						JavaMailUtil.sendMessage(purchase.getEmailOrder(), "Cancellation of purchase at Sirtiya", "Due to a purple restriction, we are noble to cancel the screening and return the money to you. You are welcome to purchase another ticket in the app");
					}
					Main.updateRowDB(screen);
					System.out.println("done set purple limit on screening");
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public static boolean CheckPurpleLimit (LocalDateTime date) {
		ArrayList<Worker> workers = Main.getAllOfType(Worker.class);
		for(Worker worker : workers) {
			if(worker instanceof CustomerService) {
				//worker = ((CustomerService) worker);
				if(date.getDayOfYear() >= ((CustomerService) worker).getDatesOfPurpleLimit().getKey().getDayOfYear() && date.getDayOfYear() <= ((CustomerService) worker).getDatesOfPurpleLimit().getValue().getDayOfYear()) {
					return true;
				}
				break;
			}
		}
		return false;
	}
	
	
	public static int[][] SetSeatsPurpleLimit(Screening screening , int numOfSeats) {
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
