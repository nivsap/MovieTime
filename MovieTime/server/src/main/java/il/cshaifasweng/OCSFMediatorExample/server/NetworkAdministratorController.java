package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.NetworkAdministrator;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;

public class NetworkAdministratorController {

	public static NetworkAdministrator getAdministrator() {
		NetworkAdministrator networkAdministrator = null;
		for(Worker arr : Main.getAllOfType(Worker.class)) {
			if(arr instanceof NetworkAdministrator) {
				networkAdministrator =(NetworkAdministrator) arr;
			}

		}
		return networkAdministrator;
	}
}