package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ComplaintAddedPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Label complaintTypeLabel;

    @FXML
    private Label incidentDateLabel;

    @FXML
    private Label complaintTitleLabel;

    @FXML
    private Label complaintDetailsLabel;

    @FXML
    void initialize() {
        assert firstNameLabel != null : "fx:id=\"firstNameLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert lastNameLabel != null : "fx:id=\"lastNameLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert emailLabel != null : "fx:id=\"emailLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert phoneNumberLabel != null : "fx:id=\"phoneNumberLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert complaintTypeLabel != null : "fx:id=\"complaintTypeLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert incidentDateLabel != null : "fx:id=\"incidentDateLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert complaintTitleLabel != null : "fx:id=\"complaintTitleLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert complaintDetailsLabel != null : "fx:id=\"complaintDetailsLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
    }
    
    
    void setData(Complaint complaint) {
    	complaintDetailsLabel.setWrapText(true);
        firstNameLabel.setText(complaint.getFirstName());
        lastNameLabel.setText(complaint.getLastName());
        emailLabel.setText(complaint.getEmail());
        phoneNumberLabel.setText(complaint.getPhoneNumber());
        complaintTypeLabel.setText(complaint.getComplaintType());
        incidentDateLabel.setText(complaint.getIncidentDate().toString());
        complaintTitleLabel.setText(complaint.getComplaintTitle());
        complaintDetailsLabel.setText(complaint.getComplaintDetails());
    }
}
