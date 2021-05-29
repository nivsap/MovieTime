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
	//private Message clientMessage;
	private static final Logger LOGGER = Logger.getLogger(AppClient.class.getName());
	

	
	public AppClient(String host, int port) {
		super(host, port);
	}
	@Override
	protected void handleMessageFromServer(Object msg) {
		System.out.println("msg recieved in appClient!");
		System.out.println("msg is: " + ((Message)msg).getAction());
		Message currentMsg = (Message) msg;
		if (currentMsg.getAction().equals("got movies"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("updated movie time"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("update movie time error"))
		{
			EventBus.getDefault().post(currentMsg);
		}
		if (currentMsg.getAction().equals("login done"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("added a complaint"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("cinema contained movies done"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("screening for movie done"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("got soon movies"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("got screening movies"))
		{
			System.out.println("AppClient got screening movies");
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("sorted movies by genre"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("done to sort by date"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("done to sort by popular"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("got movies from home"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("picking chair is done"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("set client"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("update movie error"))
		{
			EventBus.getDefault().post(((Message) msg));
		}

		if (currentMsg.getAction().equals("got genre screening movies"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("got movies from home"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("picking seats success"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (currentMsg.getAction().equals("picking seats error"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if(currentMsg.getAction().equals("save customer done")) {
			EventBus.getDefault().post(((Message) msg));
		}
	    if(currentMsg.getAction().equals("sent successful purchase mail")) {
	    		EventBus.getDefault().post(((Message) msg));
	    }
	    if(currentMsg.getAction().equals("got all screenings")) {
    		EventBus.getDefault().post(((Message) msg));
	    }
		if(currentMsg.getAction().equals("got purchase by id")) {
    		EventBus.getDefault().post(((Message) msg));
    	}
    	
    	if(currentMsg.getAction().equals("got purchase cancelation by id")) {
    		EventBus.getDefault().post(((Message) msg));
    	}
		
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
