package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.PriceRequest;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OpenPriceChangeRequestsController {
	
	private Boolean isRegistered; 
	private PriceRequest newPriceRequest;
	private int requestType;
	
    @FXML
    private Label ticketPriceLabel;

    @FXML
    private Label viewingPackagePriceLabel;

    @FXML
    private Label subscriptionCardPriceLabel;

    @FXML
    private ComboBox<String> requestTypeComboBox;

    @FXML
    private TextField newPriceTextField;

    @FXML
    private TextArea commentsTextArea;

    @FXML
    private Button requestBtn;

    @FXML
    private Label warningLabel;

    @FXML
    private Label successLabel;
    
    public OpenPriceChangeRequestsController() {
    	isRegistered = false;
    	newPriceRequest = new PriceRequest();
    	requestType = 4;
    }
    
    @FXML
    void initialize() {
    	assert ticketPriceLabel != null : "fx:id=\"ticketPriceLabel\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert viewingPackagePriceLabel != null : "fx:id=\"viewingPackagePriceLabel\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert subscriptionCardPriceLabel != null : "fx:id=\"subscriptionCardPriceLabel\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert requestTypeComboBox != null : "fx:id=\"requestTypeComboBox\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert newPriceTextField != null : "fx:id=\"newPriceTextField\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert commentsTextArea != null : "fx:id=\"commentsTextArea\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert requestBtn != null : "fx:id=\"requestBtn\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert warningLabel != null : "fx:id=\"warningLabel\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert successLabel != null : "fx:id=\"successLabel\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
   
        warningLabel.setVisible(false);
        successLabel.setVisible(false);
        requestTypeComboBox.getItems().clear();
        requestTypeComboBox.getItems().add("Movie Price Change");
        requestTypeComboBox.getItems().add("Viewing Package Price Change");
        requestTypeComboBox.getItems().add("Subscription Card Price Change");
        ticketPriceLabel.setText("");
    	viewingPackagePriceLabel.setText("");
    	subscriptionCardPriceLabel.setText("");
    	sendMessageToServer("get prices");
    }
    
    void setPrices(float moviePrice, float viewingPackagePrice, float subscriptionCardPrice) {
    	ticketPriceLabel.setText(String.valueOf(moviePrice));
    	viewingPackagePriceLabel.setText(String.valueOf(viewingPackagePrice));
    	subscriptionCardPriceLabel.setText(String.valueOf(subscriptionCardPrice));
    }
    
    @FXML
    void request(ActionEvent event) {
    	warningLabel.setVisible(false);
    	successLabel.setVisible(false);
    	
    	if(requestTypeComboBox.getValue() == null) {
    		warningLabel.setText("Choose a request type first");
    		warningLabel.setVisible(true);
    		return;
    	}
    	
    	else {
    		if(requestTypeComboBox.getValue().equals("Movie Price Change"))
        		requestType = 0;
        	if(requestTypeComboBox.getValue().equals("Viewing Package Price Change"))
        		requestType = 1;
        	if(requestTypeComboBox.getValue().equals("Subscription Card Price Change"))
        		requestType = 2;
    	}
    	
    	
    	if(newPriceTextField.getText().equals("")) {
    		warningLabel.setText("Fill a new price first");
    		warningLabel.setVisible(true);
    		return;
    	}
    	
    	newPriceRequest.setNewPrice(Float.parseFloat(newPriceTextField.getText()));
    	
    	if(commentsTextArea.getText().equals("")) {
    		warningLabel.setText("Fill a comment first");
    		warningLabel.setVisible(true);
    		return;
    	}
    	
    	newPriceRequest.setType(requestType);
    	newPriceRequest.setComment(commentsTextArea.getText());
    	newPriceRequest.setIsOpen(true);
    	sendMessageToServer("save price request");
    	
    }
    
    void clearForm() {
    	if(isRegistered) {
    		EventBus.getDefault().unregister(this);
    		isRegistered = false;
    	}
    	requestTypeComboBox.getSelectionModel().clearSelection();
    	requestTypeComboBox.setPromptText("Request type");
        newPriceTextField.clear();
        commentsTextArea.clear();
        warningLabel.setVisible(false);
    }
    
    void sendMessageToServer(String type) {
    	if(!isRegistered) {
    		EventBus.getDefault().register(this);
    		isRegistered = true;
    	}
    	Message msg = new Message();
    	msg.setAction(type);
    	if(type.equals("save price request"))
    		msg.setPriceRequest(newPriceRequest);
    	try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Subscribe
	public void onMessageEvent(Message msg) {
    	if (msg.getAction().equals("got prices")) {
			Platform.runLater(() -> {
				setPrices(msg.getMoviePrice(), msg.getViewingPackagePrice(), msg.getSubscriptionCardPrice());
			});
		}
		if (msg.getAction().equals("done to save price request")) {
			Platform.runLater(() -> {
				successLabel.setVisible(true);
				clearForm();
			});
		}
	}
}
