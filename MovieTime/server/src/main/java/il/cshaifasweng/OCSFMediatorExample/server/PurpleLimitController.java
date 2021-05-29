package il.cshaifasweng.OCSFMediatorExample.server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;

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
}
