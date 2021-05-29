package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.Arrays;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ComingSoonSearchBarController {
	@FXML
    private ComboBox<String> genreID;
	String [] currentType;
	public ComingSoonSearchBarController() {
		EventBus.getDefault().register(this);
		ObservableList<String> options = FXCollections.observableArrayList("Option 1", "Option 2", "Option 3");
		genreID = new ComboBox<String>(options);
		genreID.getItems().addAll("Drama","Action");
		Message msg = new Message();
		msg.setAction("pull genre screening movies");
		try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to send msg to server from ComingSoonSearchBar");
			e.printStackTrace();
		}

	}
	
	@FXML
	public void initialize() {
		// TODO Auto-generated method stub
		genreID.getItems().clear();
		genreID.getItems().addAll("Drama");

	}
	public void setCurrentType(String [] newArray,int size) {
		

	}
	@Subscribe
	public void onMessageEvent(Message msg) {
    	if(msg.getAction().equals("got genre screening movies")) {
		Platform.runLater(()-> {
			EventBus.getDefault().unregister(this);
			System.out.println(Arrays.toString(msg.genreArray));
			currentType=new String[msg.genreArray.length];
			currentType=msg.genreArray; });	
		}
	}
}

