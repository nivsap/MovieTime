package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.util.Pair;

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
		System.out.println(((Message) msg).getAction() + " msg recieved in appClient!");
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
		System.out.println("client is: " + client);
		if (client == null) {
			client = new AppClient(host, port);
		}
		return client;
	}

	public static void setClientNull() {
		client = null;
	}

}
