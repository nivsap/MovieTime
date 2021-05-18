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
		for(Worker arr : allWorkers) {
			if(arr.getUserName().equals(msg.getUsername()) && arr.getPassword().equals( msg.getPassword())) {
				if(arr instanceof BranchManager) {
					msg.setTypeOfWorkerString("BranchManager");
					System.out.println(msg.getTypeOfWorkerString());
					msg.setWorker(arr);
				}
				else if(arr instanceof ContentManager) {
					msg.setTypeOfWorkerString("ContentManager");
					
					System.out.println(msg.getTypeOfWorkerString());
					msg.setWorker(arr);
				}
				else if(arr instanceof CustomerService) {
					msg.setTypeOfWorkerString("CustomerService");
					System.out.println(msg.getTypeOfWorkerString());
					msg.setWorker(arr);
				}
				else if(arr instanceof NetworkAdministrator) {
					msg.setTypeOfWorkerString("NetworkAdministrator");
					System.out.println(msg.getTypeOfWorkerString());
					msg.setWorker(arr);
				}
				else msg.setTypeOfWorkerString("null");
			}
		}
	}
}