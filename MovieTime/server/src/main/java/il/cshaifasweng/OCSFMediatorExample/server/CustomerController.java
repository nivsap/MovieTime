package il.cshaifasweng.OCSFMediatorExample.server;


import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import javafx.util.Pair;


public class CustomerController{

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
