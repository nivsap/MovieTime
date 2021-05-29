package il.cshaifasweng.OCSFMediatorExample.server;


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

	public static Purchase getID(String name) {
		//Customer customer = null;
		ArrayList<Purchase> customerList = Main.getAllOfType(Purchase.class);
		System.out.println(customerList.size());
		for(Purchase customer : customerList) {
			if(customer.getFirstName().equals(name)) {
				return customer;
			}
			else
			{
				System.out.println(customer.getFirstName());
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
