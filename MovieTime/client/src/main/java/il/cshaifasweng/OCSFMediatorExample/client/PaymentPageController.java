package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.NetworkAdministrator;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.SubscriptionCard;

import il.cshaifasweng.OCSFMediatorExample.entities.ViewingPackage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.util.Pair;

public class PaymentPageController {
	
	private int purchaseType;
	private Screening screening;
	private ArrayList<Pair<Integer,Integer>> seats; 
	private SubscriptionCard subscriptionCard;
	private ViewingPackage viewingPackage;
	private Purchase purchase;
	private float price, ticketPrice, linkPrice, cardPrice;
	  
    @FXML
    private TextArea orderSummeryTextArea;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Label firstNameWarningLabel;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Label lastNameWarningLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label emailWarningLabel;

    @FXML
    private TextField addressTextField;

    @FXML
    private Label addressWarningLabel;

    @FXML
    private TextField cityTextField;

    @FXML
    private Label cityWarningLabel;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Label phoneNumberWarningLabel;

    @FXML
    private TextField cardHoldersNameTextField;

    @FXML
    private Label cardHoldersNameWarningLabel;

    @FXML
    private TextField cardHoldersIDTextField;

    @FXML
    private Label cardHoldersIDWarningLabel;

    @FXML
    private ComboBox<String> paymentNumberComboBox;

    @FXML
    private TextField cardNumberTextField;

    @FXML
    private Label cardNumberWarningLabel;

    @FXML
    private DatePicker cardExpirationDatePicker;

    @FXML
    private Label cardExpirationDateWarningLabel;

    @FXML
    private TextField cardCVVTextField;

    @FXML
    private Label cardCVVWarningLabel;

    @FXML
    private Label paymentLabel;

    @FXML
    private Button payNowBtn;
    
	
    @FXML
    private Label numberOfPaymentsWarningLabel;

	public PaymentPageController() {
    	purchaseType = 4;
    	screening = null;
    	seats = null; 
    	subscriptionCard = null;
    	viewingPackage = null;
    	purchase = null;
    	ticketPrice = 0f;
    	linkPrice = 0f;
    	cardPrice = 0f;
    	getPrices();
    }
    @FXML
    void initialize() {
        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert firstNameWarningLabel != null : "fx:id=\"firstNameWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert lastNameWarningLabel != null : "fx:id=\"lastNameWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert emailWarningLabel != null : "fx:id=\"emailWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert addressTextField != null : "fx:id=\"addressTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert addressWarningLabel != null : "fx:id=\"addressWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cityTextField != null : "fx:id=\"cityTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cityWarningLabel != null : "fx:id=\"cityWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert phoneNumberWarningLabel != null : "fx:id=\"phoneNumberWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardHoldersNameTextField != null : "fx:id=\"cardHoldersNameTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardHoldersNameWarningLabel != null : "fx:id=\"cardHoldersNameWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardHoldersIDTextField != null : "fx:id=\"cardHoldersIDTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardHoldersIDWarningLabel != null : "fx:id=\"cardHoldersIDWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert paymentNumberComboBox != null : "fx:id=\"paymentNumberComboBox\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardNumberTextField != null : "fx:id=\"cardNumberTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardNumberWarningLabel != null : "fx:id=\"cardNumberWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardExpirationDatePicker != null : "fx:id=\"cardExpirationDatePicker\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardExpirationDateWarningLabel != null : "fx:id=\"cardExpirationDateWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert numberOfPaymentsWarningLabel != null : "fx:id=\"numberOfPaymentsWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardCVVTextField != null : "fx:id=\"cardCVVTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardCVVWarningLabel != null : "fx:id=\"cardCVVWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert paymentLabel != null : "fx:id=\"paymentLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert payNowBtn != null : "fx:id=\"payNowBtn\" was not injected: check your FXML file 'PaymentPage.fxml'.";

        paymentNumberComboBox.getItems().clear();
        paymentNumberComboBox.getItems().add("1");
        paymentNumberComboBox.getItems().add("2");
        paymentNumberComboBox.getItems().add("3");
        hideWarningLabels();
    }
    
    public void getPrices() {
    	EventBus.getDefault().register(this);
    	Message msg = new Message();
    	msg.setAction("get prices");
    	msg.setPurchase(purchase);
    	
    	try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void setInfoTicket(Screening screening, ArrayList<Pair<Integer, Integer>> seatsChosen) {
    	this.purchaseType = PurchaseTypes.TICKET;
    	this.screening = screening;
    	this.seats = seatsChosen;
    	String order;
    	price = seats.size() * ticketPrice;
		paymentLabel.setText(Double.toString(price));
    	order = "Screened Movie:" + screening.getMovie().getName() + 
    			"\nIn " + screening.getCinema().getName() + " Cinema, hall number: " + screening.getHall().getHallId() + 
    			"\nScreening Date: " + screening.getDate() + ", Screening Time: " +  screening.getTime() + "\n";
		for(Pair<Integer,Integer> seat : seats) {
			order += "Seat " + seat.getKey() + "," + seat.getValue() + "\n";
		}
		order += "Total price: " + price;
        orderSummeryTextArea.setText(order);
        paymentLabel.setText(Double.toString(price));
    }
    
    public void setInfoSubscription() {
    	purchaseType = PurchaseTypes.SUBSCRIPTION_CARD;
    	String order;
    	price = cardPrice; 
		paymentLabel.setText(Double.toString(cardPrice));
		order = "With a subscription card, you can freely attend 20 screenings of your choice, in a cinema of your choice!\n";
		order += "Total price: " + price;

        orderSummeryTextArea.setText(order);
    }


    public void setInfoLink(ViewingPackage viewingPackage) {
    	this.purchaseType = PurchaseTypes.VIEWING_PACKAGE;
    	this.viewingPackage = viewingPackage;

    	String order;
		paymentLabel.setText(Double.toString(linkPrice));
    	order = screening.getMovie().getName() + " " + screening.getDate();
		order += "Total price: " + paymentLabel.getText();
        orderSummeryTextArea.setText(order);	
    }
    
    private void createPurchase() {
      	if(purchaseType == PurchaseTypes.TICKET) {
        	/* Ticket Purchase Constructor:
        	 * String firstName, String lastName, String email, String city, String phone,
        	 * double payment, LocalDateTime purchaseTime, Pair<Boolean, Float> isCanceled, 
        	 * Screening screening, ArrayList<Pair<Integer , Integer>> seatsList, Complaint complaint
        	 */
        	for(Pair<Integer,Integer> seat : seats) {
        		screening.getSeats()[seat.getKey()][seat.getValue()] = 1;
        	}
    		purchase = new Purchase(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), cityTextField.getText(), phoneNumberTextField.getText(),
    								price, LocalDateTime.now(), screening, seats, null);
    	}
    	
    	if(purchaseType == PurchaseTypes.VIEWING_PACKAGE) {
    		/* Viewing Package Constructor:
    		 * (String firstName, String lastName, String email, String city, String phone,
    		 * double payment, LocalDateTime purchaseTime, Cinema cinema,
    		 * ViewingPackage viewingPackage, Complaint complaint)
    		 */
    		purchase = new Purchase(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), cityTextField.getText(), phoneNumberTextField.getText(),
									price, LocalDateTime.now(), null, viewingPackage, null);
    	}
    	
    	if(purchaseType == PurchaseTypes.SUBSCRIPTION_CARD) {
    		/* Subscription Card Constructor:
    		 * public Purchase(String firstName, String lastName, String email, String city, String phone,
    		 * double payment, LocalDateTime purchaseTime, Cinema cinema,
    		 * SubscriptionCard subscriptionCard, Complaint complaint)
    		 */
    		purchase = new Purchase(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), cityTextField.getText(), phoneNumberTextField.getText(),
									price, LocalDateTime.now(), null, subscriptionCard, null);	
    	}

    	Message msg = new Message();
    	msg.setAction("save customer");
    	msg.setPurchase(purchase);
    	
    	try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	try {
			PaymentCompletionPageController controller= (PaymentCompletionPageController)App.setContent("PaymentCompletionPage");
			controller.SetData("msg", firstNameTextField.getText(), lastNameTextField.getText(),
					emailTextField.getText(), phoneNumberTextField.getText(),
					cardHoldersNameTextField.getText(), cardHoldersIDTextField.getText(),
					paymentNumberComboBox.getValue().toString(), cityTextField.getText(),
					addressTextField.getText(), cardExpirationDatePicker.getValue().toString(),
					paymentLabel.getText(), cardNumberTextField.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    //in the func handleMessageFromClient
    @Subscribe
    public void OnMessageEvent(Message msg) {
    	if(msg.getAction().equals("got prices")) {
    		ticketPrice = msg.getMoviePrice();
    		linkPrice = msg.getViewingPackagePrice();
    		cardPrice = msg.getSubscriptionCardPrice();
    	}
    	
    	if(msg.getAction().equals("save customer done")) {
    		purchase = msg.getPurchase();
    		String successfulPurchaseString;
    		successfulPurchaseString = "Dear " + this.purchase.getFirstName() +" " + this.purchase.getLastName() + "\nThank you for your order, number: " + purchase.getId() + "\n\nOrder details:\n"; 
    		if(purchase.isTicket()) {
    			successfulPurchaseString += "\nScreening Movie: " + purchase.getScreening().getMovie().getName() + "\nScreening Date: " + purchase.getScreening().getDate().toString() + ", Time: " +
    										purchase.getScreening().getTime().toString() + "\nCinema: " + purchase.getCinema().getName() + "\nHall number: " + purchase.getScreening().getHall().getHallId() + "\nOrdered seats:";
        		for(Pair<Integer,Integer> seat : seats) {
        			successfulPurchaseString += "\n\tSeat " + seat.getKey() + "," + seat.getValue();
        		}	
    		}
    		
    		if(purchase.isLink()) {
    			successfulPurchaseString += "\nViewing Package Movie: " + purchase.getViewingPackage().getMovie().getName() + "\nLink: " + purchase.getViewingPackage().getLink() +
    										"\nA reminder will be sent to you an hour before your link becomes available";
    		}
    		
    		if(purchase.isCard()) {
    			subscriptionCard = msg.getSubscriptionCard();
    			successfulPurchaseString += "\nYour subscription card number: " +  subscriptionCard.getId() + "\nSubscription card remaining: " + subscriptionCard.getRemaining();
    		}
    		
    		successfulPurchaseString += "\nTotal price: " + this.price + "\n\nCheers,\nThe Sirtiya";
    		msg.setAction("send successful purchase mail");
    		msg.setCustomerEmail(purchase.getEmail());
    		msg.setEmailMessage(successfulPurchaseString);
    		try {
				AppClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	if(msg.getAction().equals("sent successful purchase mail")) {
    		EventBus.getDefault().unregister(this);
    
    		JOptionPane.showMessageDialog(null, "Thank you for your purchase, an email has been sent with the details");
    	}  	
    }
    
    public Screening getScreening() {
    	return screening;
    }
    
    public List<Pair<Integer,Integer>> getSeats(){
    	return seats;
    }
    
 
	void hideWarningLabels() {
		firstNameWarningLabel.setVisible(false);
		lastNameWarningLabel.setVisible(false);
		emailWarningLabel.setVisible(false);
		addressWarningLabel.setVisible(false);
		cityWarningLabel.setVisible(false);
		phoneNumberWarningLabel.setVisible(false);
		cardHoldersNameWarningLabel.setVisible(false);
		cardHoldersIDWarningLabel.setVisible(false);
		cardNumberWarningLabel.setVisible(false);
		cardExpirationDateWarningLabel.setVisible(false);
		cardCVVWarningLabel.setVisible(false);
		numberOfPaymentsWarningLabel.setVisible(false);
	}

    @FXML
    void padNow(ActionEvent event) {
    	hideWarningLabels();
    	boolean emptyField = true;
    	
    	String firstName = firstNameTextField.getText();
    	if(firstName.equals("")) {
    		firstNameWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String lastName = lastNameTextField.getText();
    	if(lastName.equals("")) {
    		lastNameWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String email = emailTextField.getText();
    	if(email.equals("")) {
    		emailWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    		
    	String address = addressTextField.getText();
    	if(address.equals("")) {
    		addressWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String city = cityTextField.getText();
    	if(city.equals("")) {
    		cityWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String phoneNumber = phoneNumberTextField.getText();
    	if(phoneNumber.equals("")) {
    		phoneNumberWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String cardHoldersName = cardHoldersNameTextField.getText();
    	if(cardHoldersName.equals("")) {
    		cardHoldersNameWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String cardHoldersID = cardHoldersIDTextField.getText();
    	if(cardHoldersID.equals("")) {
    		cardHoldersIDWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String cardNumber = cardNumberTextField.getText();
    	if(cardNumber.equals("")) {
    		cardNumberWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	LocalDate cardExpirationDate = cardExpirationDatePicker.getValue();
    	if(cardExpirationDate == null) {
    		cardExpirationDateWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String cardCVV = cardCVVTextField.getText();
    	if(cardCVV.equals("")) {
    		cardCVVWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	if(emptyField == false) {
    		return;
    	}
    	
    	if(paymentNumberComboBox.getValue() == null) {
    		numberOfPaymentsWarningLabel.setVisible(true);
    		emptyField = false;
    		if(emptyField == false) {
        		return;
        	}
    	}
    	
    	createPurchase();
    }
}
