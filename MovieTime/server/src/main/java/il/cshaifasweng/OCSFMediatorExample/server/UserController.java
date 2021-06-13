package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.ContentManager;
import il.cshaifasweng.OCSFMediatorExample.entities.CustomerService;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.NetworkAdministrator;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;

public class UserController {
	
	public static void getUser (Message msg) {
		ArrayList<Worker> allWorkers = Main.getAllOfType(Worker.class);
		int flag = 0;
		for(Worker arr : allWorkers) {
			if(arr.getUserName().equals(msg.getUsername()) && arr.getPassword().equals( msg.getPassword()) && arr.isLoggedIn()==false) {
				if(arr instanceof BranchManager) {

					msg.setTypeOfWorker("BranchManager");
					System.out.println(msg.getTypeOfWorker());
					arr.setLoggedIn(true);
					msg.setWorker(arr);
					Main.updateRowDB(arr);
					flag = 1;
				}
				else if(arr instanceof ContentManager) {

					msg.setTypeOfWorker("ContentManager");
					arr.setLoggedIn(true);
					System.out.println(msg.getTypeOfWorker());
					msg.setWorker(arr);
					Main.updateRowDB(arr);
					flag = 1;
				}
				else if(arr instanceof CustomerService) {
					msg.setTypeOfWorker("CustomerService");
					System.out.println(msg.getTypeOfWorker());
					arr.setLoggedIn(true);
					msg.setWorker(arr);
					Main.updateRowDB(arr);
					flag = 1;
				}
				else if(arr instanceof NetworkAdministrator) {

					msg.setTypeOfWorker("NetworkAdministrator");
					System.out.println(msg.getTypeOfWorker());
					arr.setLoggedIn(true);
					msg.setWorker(arr);
					Main.updateRowDB(arr);
					flag = 1;
				}
				else msg.setTypeOfWorker("null");
			}
			else if(arr.getUserName().equals(msg.getUsername()) && arr.getPassword().equals( msg.getPassword()) &&arr.isLoggedIn()==true) {
				msg.setTypeOfWorker("you are already logged in");
				//arr.setLoggedIn(false);
				Main.updateRowDB(arr);
				flag = 1;
			}

		}
		if(flag==0) {
			msg.setTypeOfWorker("This user does not exist");
		}
	}
	
	public static void logUserOut (Message msg) {
		ArrayList<Worker> allWorkers = Main.getAllOfType(Worker.class);
		System.out.println("about to log out");

		for(Worker arr : allWorkers) {
			if(arr.getUserName().equals(msg.getUsername()) && arr.getPassword().equals( msg.getPassword())) {
				if(arr instanceof BranchManager) {
					arr.setLoggedIn(false);
					Main.updateRowDB(arr);

				}
				else if(arr instanceof ContentManager) {
					arr.setLoggedIn(false);
					Main.updateRowDB(arr);

				}
				else if(arr instanceof CustomerService) {
					arr.setLoggedIn(false);
					Main.updateRowDB(arr);

				}
				else if(arr instanceof NetworkAdministrator) {
					arr.setLoggedIn(false);
					Main.updateRowDB(arr);

				}

			}
			else if(arr.getUserName().equals(msg.getUsername()) && arr.getPassword().equals( msg.getPassword()) &&arr.isLoggedIn()==true) {
				msg.setTypeOfWorker("you are already logged out");
			}

		}
		
	}
	
	
	

}


