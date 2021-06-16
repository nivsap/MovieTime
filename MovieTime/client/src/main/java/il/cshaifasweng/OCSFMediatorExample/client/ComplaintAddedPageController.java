package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ComplaintAddedPageController {
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
    private Label orderNumberLabel;

    @FXML
    private Label complaintTitleLabel;

    @FXML
    private Label complaintDetailsLabel;

    @FXML
    void initialize() throws Exception{
        assert firstNameLabel != null : "fx:id=\"firstNameLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert lastNameLabel != null : "fx:id=\"lastNameLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert emailLabel != null : "fx:id=\"emailLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert phoneNumberLabel != null : "fx:id=\"phoneNumberLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert complaintTypeLabel != null : "fx:id=\"complaintTypeLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert orderNumberLabel != null : "fx:id=\"orderNumberLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert complaintTitleLabel != null : "fx:id=\"complaintTitleLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
        assert complaintDetailsLabel != null : "fx:id=\"complaintDetailsLabel\" was not injected: check your FXML file 'ComplaintAddedPage.fxml'.";
    }
     
    void setData(Complaint complaint) {
    	try {
    	complaintDetailsLabel.setWrapText(true);
        firstNameLabel.setText(complaint.getFirstName());
        lastNameLabel.setText(complaint.getLastName());
        emailLabel.setText(complaint.getEmail());
        phoneNumberLabel.setText(complaint.getPhoneNumber());
        if(complaint.getPurchase().isTicket())
        	complaintTypeLabel.setText("Issues with tickets order");
        if(complaint.getPurchase().isLink())
        	complaintTypeLabel.setText("Issues with viewing packages");
        if(complaint.getPurchase().isCard())
        	complaintTypeLabel.setText("Issues with subscription cards");
        orderNumberLabel.setText(String.valueOf(complaint.getPurchase().getId()));
        complaintTitleLabel.setText(complaint.getComplaintTitle());
        complaintDetailsLabel.setText(complaint.getComplaintDetails());
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
