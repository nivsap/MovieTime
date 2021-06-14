package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.PriceRequest;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PriceChangeApprovalsController {
	ArrayList<PriceRequest> allRequests;
	PriceRequest currentRequest;
	Boolean isRegistered;
	
    @FXML
    private Label noRequestsLabel;
	
    @FXML
    private VBox cardsContainer;

    @FXML
    private AnchorPane requestHandlingContainer;

    @FXML
    private TextArea responseTextArea;

    @FXML
    private Button declineBtn;

    @FXML
    private Button approveBtn;

    @FXML
    private Label warningLabel;

    @FXML
    private Label reasonLabel;
    
    public PriceChangeApprovalsController() {
    	allRequests = new ArrayList<PriceRequest>();
    	currentRequest = new PriceRequest();
    	isRegistered = false;
    }

    @FXML
    void initialize() {
    	 assert noRequestsLabel != null : "fx:id=\"noRequestsLabel\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
         assert cardsContainer != null : "fx:id=\"cardsContainer\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
         assert requestHandlingContainer != null : "fx:id=\"requestHandlingContainer\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
         assert responseTextArea != null : "fx:id=\"responseTextArea\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
         assert declineBtn != null : "fx:id=\"declineBtn\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
         assert approveBtn != null : "fx:id=\"approveBtn\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
         assert warningLabel != null : "fx:id=\"warningLabel\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
         assert reasonLabel != null : "fx:id=\"reasonLabel\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";

        noRequestsLabel.setVisible(false);
        requestHandlingContainer.setVisible(false);
        pullRequests();
    }
    
	void pullRequests() {
		if(!isRegistered) {
			EventBus.getDefault().register(this);
			isRegistered = true;
		}
		Message msg= new Message();
		msg.setAction("get all open price requests");
		try {
			AppClient.getClient().sendToServer(msg);
		} 
		catch (IOException e) {
			System.out.println("failed to send msg to server from PriceChangeApprovalsController");
			e.printStackTrace();
		}
	}
	
	void initRequestsContainer() {
		if(allRequests == null || allRequests.isEmpty()) {
			cardsContainer.getChildren().clear();
			cardsContainer.getChildren().add(noRequestsLabel);
			noRequestsLabel.setVisible(true);
		}
		else {
			cardsContainer.getChildren().clear();
			noRequestsLabel.setVisible(false);
			try {
				for(PriceRequest request : allRequests) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("PriceChangeCard.fxml"));
					HBox cardBox = fxmlLoader.load();	
					PriceChangeCardController ctrl = fxmlLoader.getController();
					ctrl.setRequestData(request, this);
					cardsContainer.getChildren().add(cardBox);
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	void clearRequestHandlingContainer() {
		reasonLabel.setText("");
		responseTextArea.clear();
		warningLabel.setVisible(false);
		requestHandlingContainer.setVisible(false);
	}
    
    void setRequestHandling(PriceRequest request) {
    	warningLabel.setVisible(false);
    	currentRequest = request;
    	reasonLabel.setText(request.getComment());
    	requestHandlingContainer.setVisible(true);
    }
    
    @FXML
    void approveRequest() {
    	if(responseTextArea.getText().equals(""))
    		warningLabel.setVisible(true);
    	currentRequest.setIsOpen(false);
    	currentRequest.setAdministratorComment(responseTextArea.getText());
    	sendMessageToServer(true);
    }

    @FXML
    void declineRequest() {
    	if(responseTextArea.getText().equals(""))
    		warningLabel.setVisible(true);
    	currentRequest.setIsOpen(false);
    	currentRequest.setAdministratorComment(responseTextArea.getText());
    	sendMessageToServer(false);
    }
    
    void sendMessageToServer(Boolean isApproved) {
    	if(!isRegistered) {
        	EventBus.getDefault().register(this);
        	isRegistered = true;
    	}
    	Message msg = new Message();
    	if(isApproved)
    		msg.setAction("approve price request");
    	else
    		msg.setAction("decline price request");
 		msg.setPriceRequest(currentRequest);
 		try {
 			AppClient.getClient().sendToServer(msg);
		} 
 		catch (IOException e) {
			e.printStackTrace();
		}
    }

    
    @Subscribe
	public void onMessageEvent(Message msg) throws IOException {
    	if(msg.getAction().equals("got all open price requests") || 
    			msg.getAction().equals("approved price request") || msg.getAction().equals("declined price request")) {
    		Platform.runLater(()-> {
	    		allRequests = msg.getPriceRequests();
	    		initRequestsContainer();
	    		clearRequestHandlingContainer();
    		});
    	}
	}		
}
