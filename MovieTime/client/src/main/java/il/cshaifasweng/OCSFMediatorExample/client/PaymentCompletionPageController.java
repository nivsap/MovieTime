package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PaymentCompletionPageController {

	  @FXML
	    private Label cardNumberLabel;

	    @FXML
	    private Label firstNameLabel;

	    @FXML
	    private Label lastNameLabel;

	    @FXML
	    private Label emailLabel;

	    @FXML
	    private Label phoneNumberLabel;

	    @FXML
	    private Label cardHolderLabel;

	    @FXML
	    private Label cardHolderIdLabel;

	    @FXML
	    private Label numberOfPaymentsLabel;

	    @FXML
	    private Label cityLabel;

	    @FXML
	    private Label addressLabel;

	    @FXML
	    private TextArea paymentTA;

	    @FXML
	    private Label expirationDateLabel;

	    @FXML
	    private Label paymentAmountLabel;

	    @FXML
	    private Label purchaseIdLabel;

    
    
    
    
    public void SetData(String msg, String firstName, String lastName, String email, String phoneNumber, String cardHolder, String cardHolderId,
    		String numberOfPayments, String city, String address, String expirationDate, String paymentAmount, String cardNumber, String Id) {
    	paymentTA.setText(msg);
    	firstNameLabel.setText(firstName);
    	lastNameLabel.setText(lastName);
    	emailLabel.setText(email);
    	phoneNumberLabel.setText(phoneNumber);
    	cardHolderLabel.setText(cardHolder);
    	cardNumberLabel.setText(cardNumber);
    	cardHolderIdLabel.setText(cardHolderId);
    	numberOfPaymentsLabel.setText(numberOfPayments);
    	cityLabel.setText(city);
    	addressLabel.setText(address);
    	expirationDateLabel.setText(expirationDate);
    	paymentAmountLabel.setText(paymentAmount);
    	purchaseIdLabel.setText(Id);
    	
    	
    	
    }
    

}
