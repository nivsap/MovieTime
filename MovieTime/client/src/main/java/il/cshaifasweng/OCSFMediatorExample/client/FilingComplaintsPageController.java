package il.cshaifasweng.OCSFMediatorExample.client;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FilingComplaintsPageController  {

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
    private TextField phoneNumberTextField;

    @FXML
    private Label phoneNumberWarningLabel;

    @FXML
    private ComboBox<?> complaintTypeComboBox;

    @FXML
    private Label complaintTypeWarningLabel;

    @FXML
    private DatePicker incidentDatePicker;

    @FXML
    private Label incidentDateWarningLabel;

    @FXML
    private TextField complaintTitleTextField;

    @FXML
    private Label complaintTitleWarningLabel;

    @FXML
    private TextArea complaintDetailsTextArea;

    @FXML
    private Label complaintDetailsWarningLabel;

    @FXML
    private Button fileComplaintBtn;

    @FXML
    void fileComplain(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert firstNameWarningLabel != null : "fx:id=\"firstNameWarningLabel\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert lastNameWarningLabel != null : "fx:id=\"lastNameWarningLabel\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert emailWarningLabel != null : "fx:id=\"emailWarningLabel\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert phoneNumberWarningLabel != null : "fx:id=\"phoneNumberWarningLabel\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintTypeComboBox != null : "fx:id=\"complaintTypeComboBox\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintTypeWarningLabel != null : "fx:id=\"complaintTypeWarningLabel\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert incidentDatePicker != null : "fx:id=\"incidentDatePicket\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert incidentDateWarningLabel != null : "fx:id=\"incidentDateWarningLabel\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintTitleTextField != null : "fx:id=\"complaintTitleTextField\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintTitleWarningLabel != null : "fx:id=\"complaintTitleWarningLabel\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintDetailsTextArea != null : "fx:id=\"complaintDetailsTextArea\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintDetailsWarningLabel != null : "fx:id=\"complaintDetailsWarningLabel\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert fileComplaintBtn != null : "fx:id=\"fileComplaintBtn\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        
        hideWarningLabels();
    }
    
	void hideWarningLabels() {
		firstNameWarningLabel.setVisible(false);
		lastNameWarningLabel.setVisible(false);
		emailWarningLabel.setVisible(false);
		phoneNumberWarningLabel.setVisible(false);
		complaintTypeWarningLabel.setVisible(false);
		incidentDateWarningLabel.setVisible(false);
		complaintTitleWarningLabel.setVisible(false);
		complaintDetailsWarningLabel.setVisible(false);
	}
    
    
    @FXML
    void fileComplaint(ActionEvent event) {
    	hideWarningLabels();
    	
    	String firstName = firstNameTextField.getText();
    	if(firstName == "") {
    		firstNameWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String lastName = lastNameTextField.getText();
    	if(lastName == "") {
    		lastNameWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String email = emailTextField.getText();
    	if(email == "") {
    		emailWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String phoneNumber = phoneNumberTextField.getText();
    	if(phoneNumber == "") {
    		phoneNumberWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String complaintType = (String) complaintTypeComboBox.getValue();
    	if(complaintType == "") {
    		complaintTypeWarningLabel.setVisible(true);
    		return;
    	}

		LocalDate incidentDate = incidentDatePicker.getValue();
    	if(incidentDate == null) {
    		incidentDateWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String complaintTitle = complaintTitleTextField.getText();
    	if(complaintTitle == "") {
    		complaintTitleWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String complaintDetails = complaintDetailsTextArea.getText();
    	if(complaintDetails == "") {
    		complaintDetailsWarningLabel.setVisible(true);
    		return;
    	}
    	
    	// Else - save order in database
    }
}
