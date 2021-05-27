package il.cshaifasweng.OCSFMediatorExample.server;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import javafx.util.Pair;


public class CustomerController{
	/*static SessionFactory sessionFactory = Main.getSessionFactory();
	private static Session session;*/
	
//	public static <T> void saveRowInDB(T objectType) {
//		try {
//			session = sessionFactory.openSession();
//			session.beginTransaction();
//			session.save(objectType);
//			session.flush();
//			session.getTransaction().commit();
//			session.clear();
//		} catch (Exception e) {
//			if (session != null) {
//				session.getTransaction().rollback();
//			}
//			System.err.println("An error occured, changes have been rolled back.");
//			e.printStackTrace();
//		} finally
//		{
//			assert session != null;
//			session.close();
//			System.out.println("saveCustomerInDB");
//		}
//	}
//	public static <T> void updateDB(T objectType) {
//		try {
//			session = sessionFactory.openSession();
//			session.beginTransaction();
//			session.update(objectType);
//			session.flush();
//			session.getTransaction().commit();
//			session.clear();
//		} catch (Exception e) {
//			if (session != null) {
//				session.getTransaction().rollback();
//			}
//			System.err.println("An error occured, changes have been rolled back.");
//			e.printStackTrace();
//		} finally
//		{
//			assert session != null;
//			session.close();
//			System.out.println("Complaint added to database");
//		}
//	}
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
