package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.logging.Logger;
import org.greenrobot.eventbus.EventBus;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class AppClient extends AbstractClient {

	private static AppClient client = null;
	// private Message clientMessage;
	private static final Logger LOGGER = Logger.getLogger(AppClient.class.getName());

	// unregister
	public AppClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		Message currentMsg = (Message) msg;
		EventBus.getDefault().post(currentMsg);
	}

	@Override
	protected void connectionEstablished() {
		// TODO Auto-generated method stub
		super.connectionEstablished();
		LOGGER.info("Connected to server.");
	}

	public static AppClient getClient() {

		if (client == null) {
			client = new AppClient("localhost", 3000);
		}
		return client;
	}

	public static AppClient getClient(String host, int port) {
		if (client == null) {
			client = new AppClient(host, port);
		}
		return client;
	}

	public static void setClientNull() {
		client = null;
	}

}
