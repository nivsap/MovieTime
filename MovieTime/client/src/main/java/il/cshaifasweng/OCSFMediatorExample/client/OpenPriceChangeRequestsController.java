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
	private float ticketPrice, linkPrice, cardPrice, oldPrice;
	
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
    
    void setPrices() {
    	ticketPriceLabel.setText(String.valueOf(ticketPrice) + " ₪");
    	viewingPackagePriceLabel.setText(String.valueOf(linkPrice) + " ₪");
    	subscriptionCardPriceLabel.setText(String.valueOf(cardPrice) + " ₪");
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
    		if(requestTypeComboBox.getValue().equals("Movie Price Change")) {
    			requestType = 0;
    			oldPrice = ticketPrice;
    		}
        	if(requestTypeComboBox.getValue().equals("Viewing Package Price Change")) {
        		requestType = 1;
        		oldPrice = linkPrice;
        	}
        	if(requestTypeComboBox.getValue().equals("Subscription Card Price Change")) {
        		requestType = 2;
        		oldPrice = cardPrice;
        	}
    	}
    	
    	
    	if(newPriceTextField.getText().equals("")) {
    		warningLabel.setText("Fill a new price first");
    		warningLabel.setVisible(true);
    		return;
    	}
    	
    	if(!InputTests.isValidFloat(newPriceTextField.getText())) {
    		warningLabel.setText("New price is invalid");
    		warningLabel.setVisible(true);
    		return;
    	}
    	
    	newPriceRequest.setNewPrice(Float.parseFloat(newPriceTextField.getText()));
    	
    	if(commentsTextArea.getText().equals("")) {
    		warningLabel.setText("Fill a comment first");
    		warningLabel.setVisible(true);
    		return;
    	}
    	newPriceRequest.setOldPrice(oldPrice);
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
   		EventBus.getDefault().register(this);
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
    	System.out.println("got message in OpenPriceChangeRequest");
    	if (msg.getAction().equals("got prices")) {
    		EventBus.getDefault().unregister(this);
			Platform.runLater(() -> {
				ticketPrice = msg.getMoviePrice();
				linkPrice = msg.getViewingPackagePrice();
				cardPrice = msg.getSubscriptionCardPrice();
				setPrices();
			});
		}
		if (msg.getAction().equals("done to save price request")) {
			EventBus.getDefault().unregister(this);
			Platform.runLater(() -> {
				successLabel.setVisible(true);
				clearForm();
			});
		}
	}
}
