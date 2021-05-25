package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class CooningSoonSearchBarController {
	@FXML
    private ComboBox<String> genreID;
	String [] currentType;
	public CooningSoonSearchBarController() {
		EventBus.getDefault().register(this);
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Option 1",
			        "Option 2",
			        "Option 3"
			    );
			//final ComboBox comboBox = new ComboBox(options);
			genreID=new ComboBox<String>(options);
		System.out.println("initializing hhhhhhhhhhhhh page");
		genreID.getItems().addAll("Drama","hada");

		Message msg = new Message();
		msg.setAction("pull genre screening movies");
		try {
			System.out.println("trying to send hadar to server");
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to send msg to server from recentlyAdded");
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
		System.out.println("reveived message hadar!!");
		System.out.println(msg.getAction());
    	if(msg.getAction().equals("got genre screening movies")) {
		Platform.runLater(()-> {
			System.out.println(Arrays.toString(msg.genreArray));
			currentType=new String[msg.genreArray.length];
			currentType=msg.genreArray;
			//for( String genre : currentType){
			//	genreID.getItems().add(genre);
			//	}

		});	
    }
		
    	}
}

