package il.cshaifasweng.OCSFMediatorExample.server;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import javafx.util.Pair;


public class CustomerController{
	static SessionFactory sessionFactory = Main.getSessionFactory();
	private static Session session;
	
	public static <T> void saveCustomerInDB(T objectType) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(objectType);
			session.flush();
			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			e.printStackTrace();
		} finally
		{
			assert session != null;
			session.close();
			System.out.println("Complaint added to database");
		}
	}
	public static <T> void updateTab(T objectType) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(objectType);
			session.flush();
			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			e.printStackTrace();
		} finally
		{
			assert session != null;
			session.close();
			System.out.println("Complaint added to database");
		}
	}
	public static Customer reduceTab(int id) {
		Customer customer = Main.getExacRow(Customer.class, id);
		Pair<Boolean, Integer> temp = new Pair<Boolean, Integer>(customer.getCinemaTab().getKey(), customer.getCinemaTab().getValue()-1);
		customer.setCinemaTab(temp);
		updateTab(customer);
		return customer;	
	}
}
