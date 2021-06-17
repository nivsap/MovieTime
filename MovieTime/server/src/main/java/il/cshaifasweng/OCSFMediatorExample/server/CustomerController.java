package il.cshaifasweng.OCSFMediatorExample.server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;


public class CustomerController{
	public static ArrayList<Complaint> getAllCurrentComplaints() {
		ArrayList<Complaint> ComplaintArrayList = new ArrayList<>();
		ArrayList<Complaint> toReturnArrayList = new ArrayList<>();
		ComplaintArrayList = Main.getAllOfType(Complaint.class);
		for(Complaint complaint : ComplaintArrayList) {
			if(complaint.isOpen()) {
				toReturnArrayList.add(complaint);
			}
		}

		return toReturnArrayList;
	}

	public static Purchase getID(int id) {
		ArrayList<Purchase> customerList = Main.getAllOfType(Purchase.class);
		for(Purchase customer : customerList) {
			if(customer.getId() == id) {
				return customer;
			}
		}
		return null;	
	}
	
	public static Purchase getPurchaseBySerial(String serial) {
		ArrayList<Purchase> customerList = Main.getAllOfType(Purchase.class);
		for(Purchase customer : customerList) {
			if(customer.getSerial().equals(serial)) {
				return customer;
			}
		}
		return null;	
	}
	 
	//This function checks whether the customer deserves a refund and if so, returns the money to him according to the company's terms
	public static Float ReturnOnPurchase(Purchase purchase , LocalDateTime time) {
		if(purchase.isCard() == true) {
			return 0f;
		}
		else if(purchase.isLink()) {
			if(time.getDayOfYear() < purchase.getViewingPackage().getDateTime().getDayOfYear()) {
				return (float) purchase.getPayment();
			}
			else if(time.getDayOfYear() == purchase.getViewingPackage().getDateTime().getDayOfYear()) {
				if(purchase.getViewingPackage().getDateTime().getHour()  > time.getHour() + 3) {
					return (float) (purchase.getPayment()/2);
				}
				else if(purchase.getViewingPackage().getDateTime().getHour()  == time.getHour() + 3 && time.getMinute() <= purchase.getViewingPackage().getDateTime().getMinute()) {
					return (float) (purchase.getPayment()/2);
				}
				else return 0f;
			}
		}
		else if(time.getDayOfYear() < purchase.getScreening().getDateAndTime().getDayOfYear()) {
			return (float) purchase.getPayment();
		}
		else if(time.getDayOfYear() == purchase.getScreening().getDateAndTime().getDayOfYear()) {
			if(purchase.getScreening().getDateAndTime().getHour()  > time.getHour() + 3) {
				return (float) (purchase.getPayment());
			}
			else if(purchase.getScreening().getDateAndTime().getHour()  <= time.getHour() + 3 && purchase.getScreening().getDateAndTime().getHour()  >= time.getHour() + 1) {
				return (float) (purchase.getPayment()/2);
			}
			else return 0f;
		}

		return 0f;
	}
}
