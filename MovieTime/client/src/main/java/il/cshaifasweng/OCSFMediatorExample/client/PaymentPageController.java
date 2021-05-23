package il.cshaifasweng.OCSFMediatorExample.client;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaymentPageController {
	

	  
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
    	if(firstName == "") {
    		firstNameWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String lastName = lastNameTextField.getText();
    	if(lastName == "") {
    		lastNameWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String email = emailTextField.getText();
    	if(email == "") {
    		emailWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    		
    	String address = addressTextField.getText();
    	if(address == "") {
    		addressWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String city = cityTextField.getText();
    	if(city == "") {
    		cityWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String phoneNumber = phoneNumberTextField.getText();
    	if(phoneNumber == "") {
    		phoneNumberWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String cardHoldersName = cardHoldersNameTextField.getText();
    	if(cardHoldersName == "") {
    		cardHoldersNameWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String cardHoldersID = cardHoldersIDTextField.getText();
    	if(cardHoldersID == "") {
    		cardHoldersIDWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String cardNumber = cardNumberTextField.getText();
    	if(cardNumber == "") {
    		cardNumberWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	LocalDate cardExpirationDate = cardExpirationDatePicker.getValue();
    	if(cardExpirationDate == null) {
    		cardExpirationDateWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	String cardCVV = cardCVVTextField.getText();
    	if(cardCVV == "") {
    		cardCVVWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	if(emptyField == false) {
    		return;
    	}
    	
    	// Else - save order in database
    }
}
