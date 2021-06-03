package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OpenComplaintsController {
	private List<Complaint> allComplaints;
	
    @FXML
    private VBox complaints_container;

	@FXML
	public void initialize() {
		EventBus.getDefault().register(this);
		PullComplaint();
	}

	private void PullComplaint() {
		Message msg= new Message();
		msg.setAction("pull current complaint");
		try {
			AppClient.getClient().sendToServer(msg);
		} 
		catch (IOException e) {
			System.out.println("failed to send msg to server from OpenComplaintsController");
			e.printStackTrace();
		}
	}
	
	
	public void InitPage() {		
		try {
			for(Complaint complaint : allComplaints) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("cardContainerComplaint.fxml"));
				HBox cardBox = fxmlLoader.load();	
				ScreeningCardController ctrl = fxmlLoader.getController();
				ctrl.SetComplaintData(complaint);
				complaints_container.getChildren().add(cardBox);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			}
	}

	@Subscribe
	public void onMessageEvent(Message msg) throws IOException {
    	if(msg.getAction().equals("got complaints")) {
    		Platform.runLater(()-> {
	    		allComplaints = msg.getComplaints();
	    		InitPage();
    		});
    	}
	}		
}
