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
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OpenComplaintsController {
	private List<Complaint> allComplaints;
	private boolean isRegistered = false;

    @FXML
    private VBox complaints_container;
    
    @FXML
    private Label noComplaintsLabel;

	@FXML
	public void initialize() {
		PullComplaint();
		noComplaintsLabel.setVisible(false);
	}

	private void PullComplaint() {
		try {
		if(!isRegistered) {
			EventBus.getDefault().register(this);
			isRegistered = true;
		}
		Message msg= new Message();
		msg.setAction("pull current complaint");
			AppClient.getClient().sendToServer(msg);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void InitPage() {
		
		if(allComplaints == null || allComplaints.isEmpty()) {
			complaints_container.getChildren().clear();
			complaints_container.getChildren().add(noComplaintsLabel);
			noComplaintsLabel.setVisible(true);
		}
		else {
			complaints_container.getChildren().clear();
			noComplaintsLabel.setVisible(false);
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
	}

	@Subscribe
	public void onMessageEvent(Message msg) throws IOException {
    	if(msg.getAction().equals("got complaints")) {
    		if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}
    		Platform.runLater(()-> {
	    		allComplaints = msg.getComplaints();
	    		InitPage();
    		});
    	}
	}		
}
