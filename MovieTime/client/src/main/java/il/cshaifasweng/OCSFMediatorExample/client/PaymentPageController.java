package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
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
	private Hall hall;
	private Screening screening;
	private ArrayList<Pair<Integer,Integer>> seats; 
	private  Pair<Boolean, Integer> subscriptionCard;
	private boolean watchFromHome;
	private Complaint complaint;
	private Purchase purchase;
	  
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
    private ComboBox<?> paymentNumberComboBox;

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
    void initialize() {
    	EventBus.getDefault().register(this);
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
        assert cardCVVTextField != null : "fx:id=\"cardCVVTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardCVVWarningLabel != null : "fx:id=\"cardCVVWarningLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert paymentLabel != null : "fx:id=\"paymentLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert payNowBtn != null : "fx:id=\"payNowBtn\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        
        hideWarningLabels();
        
        
    }

    public void setInfoTicket(int type, Screening screening, ArrayList<Pair<Integer, Integer>> seatsChosen) {
    	this.purchaseType = type;
    	this.screening = screening;
    	this.seats = seatsChosen;
    	String order;
    	
		paymentLabel.setText(Double.toString(seats.size() * screening.getCinema().getMoviePrice()));
    	order = screening.getCinema().getName() + " Cinema, hall " + screening.getHall().getHallId() + "\n" +
    			screening.getMovie().getName() + " " + screening.getDate_screen() + "\n";
		for(Pair<Integer,Integer> seat : seats) {
			order += "Seat " + seat.getKey() + "," + seat.getValue() + "\n";
		}
		order += "Total price: " + seats.size() * screening.getCinema().getMoviePrice();
        orderSummeryTextArea.setText(order);
	
		watchFromHome = false;
		subscriptionCard = new Pair<Boolean,Integer>(false,0);
        paymentLabel.setText(Double.toString(seats.size() * screening.getCinema().getMoviePrice()));
   	
    }
    
    public void setInfoSubscription(int type, double price) {
    	this.purchaseType = type;
    	String order;
    
		paymentLabel.setText(Double.toString(price));
		order = "The subscription card will act as a realy time ticket to enter any movie\n in any of our cinemas 20 times!\n";
		order += "Total price: " + paymentLabel.getText();
        orderSummeryTextArea.setText(order);
	
		watchFromHome = false;
		subscriptionCard = new Pair<Boolean,Integer>(true,20);

	
    	
    }
    
    
    public void setInfoLink(int type, Screening screening) {
    	this.purchaseType = type;
    	this.screening = screening;
    	String order;
		paymentLabel.setText(Double.toString(screening.getCinema().getLinkPrice()));
    	order = screening.getMovie().getName() + " " + screening.getDate_screen();
		order += "Total price: " + paymentLabel.getText();
        orderSummeryTextArea.setText(order);
		watchFromHome = true;
		subscriptionCard = new Pair<Boolean,Integer>(false,0);    		
    	
    }
    
    private void createPurchase() {
    	//complaint = new Complaint(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), phoneNumberTextField.getText(),false, null ,false);
    	for(Pair<Integer,Integer> seat : seats) {
    		screening.getSeats()[seat.getKey()][seat.getValue()] = 1;
    	}
    	
    	if(purchaseType == PurchaseTypes.TICKET) {
    		purchase = new Purchase(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), cityTextField.getText(), phoneNumberTextField.getText(),
    			subscriptionCard, watchFromHome, LocalDateTime.now(), screening.getCinema(), screening.getHall(), seats, 0 , null,screening);
    	}
    	purchase = new Purchase(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), cityTextField.getText(), phoneNumberTextField.getText(),
    			subscriptionCard, watchFromHome, LocalDateTime.now(), screening.getCinema(), screening.getHall(), seats, 0 , null,screening);
    	//complaint.setPurchase(purchase);
    	
    	Message msg = new Message();
    	msg.setAction("save customer");
    	msg.setPurchase(purchase);
    	try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    @Subscribe
    public void OnMessageEvent(Message msg) {
    	if(msg.getAction().equals("save customer done")) {
    		String successfulPurchaseString;
    		successfulPurchaseString = "Dear " + purchase.getFirstName() +" " + purchase.getLastName() + ", Thank you for your purchase.\n"
    				+ "the details of your order are:\n"
    				+ purchase.getCinema().getName() + " Cinema, hall " + purchase.getHall().getHallId() + "with the following seats:\n";
    			for(Pair<Integer,Integer> seat : seats) {
    				successfulPurchaseString += "Seat " + seat.getKey() + "," + seat.getValue() + "\n";
    			}
    			successfulPurchaseString += "Total price: " + purchase.getPayment();
    			msg.setAction("send successful purchase mail");
    			msg.setCustomerEmail(purchase.getEmailOrder());
    			msg.setEmailMessage(successfulPurchaseString);
    			try {
					AppClient.getClient().sendToServer(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	}
    	
    	if(msg.getAction().equals("sent successful purchase mail")) {
    		JOptionPane.showMessageDialog(null, "Thank you for your purchase, an email has been sent with the details");
    	}
    	
    	
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
    	
    	createPurchase();
    }
}
