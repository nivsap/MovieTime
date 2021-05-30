package il.cshaifasweng.OCSFMediatorExample.server;


import java.time.LocalDateTime;
import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import javafx.util.Pair;


public class CustomerController{

	
	public static ArrayList<Complaint> getAllCurrentComplaints() {
		ArrayList<Complaint> ComplaintArrayList = new ArrayList<>();
		ArrayList<Complaint> toReturnArrayList = new ArrayList<>();
        System.out.println("I am here in getAllCurrentComplaints ");
		ComplaintArrayList = Main.getAllOfType(Complaint.class);
        System.out.println("in getAllCurrentComplaints");
		for(Complaint complaint : ComplaintArrayList) {
	         System.out.println(complaint.getFirstName());
			if(complaint.getIsOpen() == true) {
				toReturnArrayList.add(complaint);
			}
		}
        System.out.println("end getAllCurrentComplaints ");

		return toReturnArrayList;
	}

	public static Purchase getID(int id) {
		//Customer customer = null;
		ArrayList<Purchase> customerList = Main.getAllOfType(Purchase.class);
		System.out.println(customerList.size());
		for(Purchase customer : customerList) {
			if(customer.getId() == id) {
				return customer;
			}
		}
		return null;
	}
	public static void reduceTab(int id) {
		//Customer customer = null;
		ArrayList<Purchase> customerList = Main.getAllOfType(Purchase.class);
		for(Purchase customer : customerList) {
			if(customer.getId() == id) {
				Pair<Boolean, Integer> temp = new Pair<Boolean, Integer>(customer.getCinemaTab().getKey(), customer.getCinemaTab().getValue()-1);
				customer.setCinemaTab(temp);
				Main.updateRowDB(customer);
			}
		}
		
	}
	public static int ReturnOnPurchase(Purchase purchase , LocalDateTime time) {
		if(purchase.getCinemaTab().getKey() == true) {
			return 0;
		}
		else if(purchase.isWatchFromHome()) {
			if(time.getDayOfYear() < purchase.getPurchaseDate().getDayOfYear()) {
				return purchase.getPayment();
			}
			else if(time.getDayOfYear() == purchase.getPurchaseDate().getDayOfYear()) {
				if(purchase.getPurchaseDate().getHour()  > time.getHour() + 3) {
					return purchase.getPayment()/2;
				}
				else if(purchase.getPurchaseDate().getHour()  == time.getHour() + 3 && time.getMinute() <= purchase.getPurchaseDate().getMinute()) {
					return purchase.getPayment()/2;
				}
				else return 0;
			}
		}
		else if(time.getDayOfYear() < purchase.getScreening().getDate_screen().getDayOfYear()) {
			return purchase.getPayment();
		}
		else if(time.getDayOfYear() == purchase.getScreening().getDate_screen().getDayOfYear()) {
			if(purchase.getPurchaseDate().getHour()  > time.getHour() + 3) {
				return purchase.getPayment()/2;
			}
			else if(purchase.getPurchaseDate().getHour()  == time.getHour() + 3 && time.getMinute() <= purchase.getPurchaseDate().getMinute()) {
				return purchase.getPayment()/2;
			}
			else return 0;
		}

		return 0;
	}
}
