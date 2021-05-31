package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PurchaseCancellationPageController {
	private Boolean isRegistered;
	private int waitingForMessageCounter;
	private String orderNumber;
	private Purchase foundPurchase;
	private int refundAmount; // Needs to be changed to float...

	@FXML
	private TextField OrderNumberTextField;

	@FXML
	private Label orderNumberWarningLabel;

	@FXML
	private Button searchBtn;

	@FXML
	private AnchorPane purchaseCancelationFormContainer;

	@FXML
	private Label firstNameLabel;

	@FXML
	private Label lastNameLabel;

	@FXML
	private Label emailLabel;

	@FXML
	private Label phoneNumberLabel;

	@FXML
	private Label dateLabel;

	@FXML
	private Label orderTypeLabel;

	@FXML
	private Label paidAmountLabel;

	@FXML
	private Label refundAmountLabel;

	@FXML
	private Button cancelBtn;

	@FXML
	private Label weAreSorryLabel;
	
	public PurchaseCancellationPageController() {
		isRegistered = false;
		waitingForMessageCounter = 0;
		foundPurchase = new Purchase();
		orderNumber = "";
		refundAmount = 0;
	}
	
    @FXML
    void initialize() {
        assert OrderNumberTextField != null : "fx:id=\"OrderNumberTextField\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert orderNumberWarningLabel != null : "fx:id=\"orderNumberWarningLabel\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert searchBtn != null : "fx:id=\"searchBtn\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert purchaseCancelationFormContainer != null : "fx:id=\"purchaseCancelationFormContainer\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert firstNameLabel != null : "fx:id=\"firstNameLabel\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert lastNameLabel != null : "fx:id=\"lastNameLabel\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert emailLabel != null : "fx:id=\"emailLabel\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert phoneNumberLabel != null : "fx:id=\"phoneNumberLabel\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert dateLabel != null : "fx:id=\"dateLabel\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert orderTypeLabel != null : "fx:id=\"orderTypeLabel\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert paidAmountLabel != null : "fx:id=\"paidAmountLabel\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert refundAmountLabel != null : "fx:id=\"refundAmountLabel\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert cancelBtn != null : "fx:id=\"cancelBtn\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        assert weAreSorryLabel != null : "fx:id=\"weAreSorryLabel\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
        
    	purchaseCancelationFormContainer.setVisible(false);
    	orderNumberWarningLabel.setVisible(false);
    }

	@FXML
	void getPurchaseInfo(ActionEvent event) throws IOException {
		foundPurchase = null;
		orderNumberWarningLabel.setVisible(false);
		orderNumber = OrderNumberTextField.getText();
		if(orderNumber.equals("") || orderNumber.equals(null))
		{
			orderNumberWarningLabel.setVisible(true);
			orderNumberWarningLabel.setText("Order number must be entered");
			return;
		}	
		
		Message msg = new Message();
		msg.setAction("get purchase by id");
		msg.setId(Integer.parseInt(orderNumber));
		if(!isRegistered) 
			EventBus.getDefault().register(this);
		AppClient.getClient().sendToServer(msg);
		waitingForMessageCounter++;
	}
    
    void setPurchaseInfo() { // Still need to deal with orderTypeLabel
    	
    	String firstName = foundPurchase.getFirstName();
    	if(!firstName.equals(""))
    		firstNameLabel.setText(firstName);
    	else 
    		firstNameLabel.setText("Unknown");
    	
    	String lastName = foundPurchase.getLastName();
    	if(!lastName.equals(""))
    		lastNameLabel.setText(lastName);
    	else 
    		lastNameLabel.setText("Unknown");
    	
    	String email = foundPurchase.getEmailOrder();
    	if(!email.equals(""))
    		emailLabel.setText(email);
    	else 
    		emailLabel.setText("Unknown");
    	
    	String phoneNumber = foundPurchase.getPhoneString();
    	if(!phoneNumber.equals(""))
    		phoneNumberLabel.setText(phoneNumber);
    	else 
    		phoneNumberLabel.setText("Unknown");
    	
    	String date = foundPurchase.getPurchaseDate().toString();
    	if(!date.equals(""))
    		dateLabel.setText(date.substring(0,10));
    	else 
    		dateLabel.setText("Unknown");
    	
    	
    	String paidAmount = String.valueOf(foundPurchase.getPayment());
    	if(!paidAmount.equals(""))
    		paidAmountLabel.setText(paidAmount);
    	else 
    		paidAmountLabel.setText("Unknown");
    
    	refundAmountLabel.setText(String.valueOf(refundAmount));
    	weAreSorryLabel.setVisible(false);
    }
    
	@FXML
	void cancelPurchase(ActionEvent event) throws IOException {
		if(foundPurchase == null) {
			return;
		}
		if(foundPurchase.getCinemaTab().getKey()) {
			weAreSorryLabel.setVisible(true);
			return;
		}
		Message msg = new Message();
		msg.setAction("cancellation of purchase");
		msg.setPurchase(foundPurchase);
		if(!isRegistered) 
			EventBus.getDefault().register(this);
		AppClient.getClient().sendToServer(msg);
		waitingForMessageCounter++;
	}
	
	void sendCancellationEmail() throws IOException {
		Message msg = new Message();
		msg.setAction("send purchase cancellation mail");
		msg.setPurchase(foundPurchase);
		msg.setPayment(refundAmount);
		if(!isRegistered) 
			EventBus.getDefault().register(this);
		AppClient.getClient().sendToServer(msg);
		waitingForMessageCounter++;
	}
	
    @Subscribe
    public void onMessageEvent(Message msg){
    	if(msg.getAction().equals("got purchase by id")) {
    		waitingForMessageCounter--;
        	if(waitingForMessageCounter == 0) {
        		EventBus.getDefault().unregister(this);
        	}
    		Platform.runLater(() -> {
    			orderNumber = "";
    			foundPurchase = msg.getPurchase();
    			if(foundPurchase == null) {
    				refundAmount = 0;
    				orderNumberWarningLabel.setText("Order number not found");
    				orderNumberWarningLabel.setVisible(true);
    				purchaseCancelationFormContainer.setVisible(false);
    			}
    			else {
    				refundAmount = msg.getPayment();
    				orderNumberWarningLabel.setVisible(false);
    				purchaseCancelationFormContainer.setVisible(true);
    				setPurchaseInfo();
    			}
    		});
    	} 	
    	
    	if(msg.getAction().equals("got purchase cancelation by id")) {
    		waitingForMessageCounter--;
        	if(waitingForMessageCounter == 0) {
        		EventBus.getDefault().unregister(this);
        	}
    		Platform.runLater(() -> {
    			try {
					sendCancellationEmail();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		});
    	} 
    	
    	if(msg.getAction().equals("sent purchase cancellation mail")) {
    		waitingForMessageCounter--;
        	if(waitingForMessageCounter == 0) {
        		EventBus.getDefault().unregister(this);
        	}
    		Platform.runLater(() -> {
    			try {
					App.setContent("PurchaseCanceledPage");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		});
    	} 
    }
}
