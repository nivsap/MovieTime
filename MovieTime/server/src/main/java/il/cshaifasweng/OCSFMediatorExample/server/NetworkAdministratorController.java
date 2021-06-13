package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.NetworkAdministrator;

public class NetworkAdministratorController {
	
	public static NetworkAdministrator getAdministrator() {
		return Main.getAllOfType(NetworkAdministrator.class).get(0);
	}
}
