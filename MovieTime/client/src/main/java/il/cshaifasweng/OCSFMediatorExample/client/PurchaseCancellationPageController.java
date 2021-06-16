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
	private float refundAmount;

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
    void initialize() throws Exception{
    	 assert OrderNumberTextField != null : "fx:id=\"OrderNumberTextField\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert orderNumberWarningLabel != null : "fx:id=\"orderNumberWarningLabel\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert searchBtn != null : "fx:id=\"searchBtn\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert purchaseCancelationFormContainer != null : "fx:id=\"purchaseCancelationFormContainer\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert firstNameLabel != null : "fx:id=\"firstNameLabel\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert lastNameLabel != null : "fx:id=\"lastNameLabel\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert emailLabel != null : "fx:id=\"emailLabel\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert phoneNumberLabel != null : "fx:id=\"phoneNumberLabel\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert dateLabel != null : "fx:id=\"dateLabel\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert orderTypeLabel != null : "fx:id=\"orderTypeLabel\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert paidAmountLabel != null : "fx:id=\"paidAmountLabel\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert refundAmountLabel != null : "fx:id=\"refundAmountLabel\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert cancelBtn != null : "fx:id=\"cancelBtn\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";
         assert weAreSorryLabel != null : "fx:id=\"weAreSorryLabel\" was not injected: check your FXML file 'PurchaseCancellationPage.fxml'.";

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
		msg.setAction("get purchase by serial");
		msg.setSerial(orderNumber);
		sendMessageToServer(msg);
	}
    
    void setPurchaseInfo() { // Still need to deal with orderTypeLabel
    	try {
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
    	
    	String email = foundPurchase.getEmail();
    	if(!email.equals(""))
    		emailLabel.setText(email);
    	else 
    		emailLabel.setText("Unknown");
    	
    	String phoneNumber = foundPurchase.getPhone();
    	if(!phoneNumber.equals(""))
    		phoneNumberLabel.setText(phoneNumber);
    	else 
    		phoneNumberLabel.setText("Unknown");
    	
    	String date = foundPurchase.getPurchaseTime().toString();
    	if(!date.equals(""))
    		dateLabel.setText(date.substring(0,10));
    	else 
    		dateLabel.setText("Unknown");
    	
    	if(foundPurchase.isTicket())
    		orderTypeLabel.setText("Screening Tickets");
    	else {
    		if(foundPurchase.isCard()) 
    			orderTypeLabel.setText("Subscription Card");
    		else {
    			if(foundPurchase.isLink()) 
    				orderTypeLabel.setText("Viewing Package");
    			else 
    				orderTypeLabel.setText("Unknown");
    		}
    	}
    	
    	String paidAmount = String.valueOf(foundPurchase.getPayment());
    	if(!paidAmount.equals(""))
    		paidAmountLabel.setText(paidAmount);
    	else 
    		paidAmountLabel.setText("Unknown");
    
    	refundAmountLabel.setText(String.valueOf(refundAmount));
    	weAreSorryLabel.setVisible(false);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	@FXML
	void cancelPurchase(ActionEvent event) throws IOException {
		if(foundPurchase == null || foundPurchase.isCard()) {
			weAreSorryLabel.setVisible(true);
			return;
		}
		Message msg = new Message();
		msg.setAction("cancellation of purchase");
		msg.setPurchase(foundPurchase);
		sendMessageToServer(msg);
	}
	
	void sendCancellationEmail() throws IOException {
		Message msg = new Message();
		msg.setAction("send purchase cancellation mail");
		msg.setPurchase(foundPurchase);
		msg.setPayment(refundAmount);
		sendMessageToServer(msg);
	}
	
	void sendMessageToServer(Message msg) {
		try {
			if(!isRegistered) {
				EventBus.getDefault().register(this);
				isRegistered = true;
			}
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @Subscribe
    public void onMessageEvent(Message msg){
    	try {
    	if(msg.getAction().equals("got purchase by serial")) {
    		if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
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
    	} 	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	if(msg.getAction().equals("got purchase cancelation by id")) {
    		if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}
    		Platform.runLater(() -> {
    			try {
					sendCancellationEmail();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		});
    	} 
    	
    	if(msg.getAction().equals("sent purchase cancellation mail")) {
    		if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}
    		Platform.runLater(() -> {
    			try {
					App.setContent("PurchaseCanceledPage");
				} catch (IOException e) {
					e.printStackTrace();
				}
    		});
    	} 
    }
}
