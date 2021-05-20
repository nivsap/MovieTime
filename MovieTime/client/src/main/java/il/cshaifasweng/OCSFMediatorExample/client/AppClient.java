package il.cshaifasweng.OCSFMediatorExample.client;
import java.util.logging.Logger;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

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
		System.out.println("msg is: " + ((Message)msg));
		
		if (((Message) msg).getAction().equals("got movies"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (((Message) msg).getAction().equals("updated movie time"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (((Message) msg).getAction().equals("login done"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (((Message) msg).getAction().equals("added a complaint"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (((Message) msg).getAction().equals("cinema contained movies done"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (((Message) msg).getAction().equals("screening for movie done"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (((Message) msg).getAction().equals("got soon movies"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (((Message) msg).getAction().equals("got screening movies"))
		{
			EventBus.getDefault().post(((Message) msg));
		}
		if (((Message) msg).getAction().equals("picking chair is done"))
		{
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


}
