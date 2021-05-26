package il.cshaifasweng.OCSFMediatorExample.server;


import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import javafx.util.Pair;


public class CustomerController{	

	public static ArrayList<Complaint> getAllCurrentComplaints() {
		ArrayList<Complaint> screeningComplaintArrayList = new ArrayList<>();
		ArrayList<Complaint> toReturnArrayList = new ArrayList<>();
		screeningComplaintArrayList = Main.getAllOfType(Complaint.class);
        System.out.println("in getAllCurrentComplaints");
		for(Complaint complaint : screeningComplaintArrayList) {
	         System.out.println(complaint.getFirstName());
			if(complaint.getIsOpen() == true) {
				toReturnArrayList.add(complaint);
			}
		}
		return toReturnArrayList;
	}
	
	public static Purchase getID(int id) {
		//Customer customer = null;
		ArrayList<Purchase> customerList = Main.getAllOfType(Purchase.class);
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
}
