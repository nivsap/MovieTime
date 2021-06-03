
package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ComplaintHandlingController {
	
	private Complaint complaint;
	
    @FXML
    private Label complaintInfoTextField;

    @FXML
    private TextArea responseTextArea;

    @FXML
    private TextField compensationTextField;

    @FXML
    private Button closeComplaintBtn;
    
    @FXML
    private Label warningLabel;

    
   public ComplaintHandlingController() {
	   complaint = new Complaint();
   }
   
    @FXML
    void initialize() {
    	EventBus.getDefault().register(this);
    	warningLabel.setVisible(false);
    }
    
    public void setComplaintInfo(Complaint currentComplaint) {
    	complaint = currentComplaint;
    	
    	if(complaint == null)
    		return;
    	
    	String complaintInfo = "Complaint Date: ";
    	if(complaint.getComplaintDate() != null ) 
    		complaintInfo += complaint.getComplaintDate().toString();
    	else
    		complaintInfo += "Unknown";
    	
    	complaintInfo += "\n";
    	complaintInfo += "Complaint Time: ";
    	if(complaint.getComplaintTime() != null ) 
    		complaintInfo += complaint.getComplaintTime().toString();
    	else
    		complaintInfo += "Unknown";
    	
    	complaintInfo += "\n";
    	complaintInfo += " Customer Name: ";
    	if(complaint.getFirstName()!=null)
    		complaintInfo += complaint.getFirstName() + " ";
    	else
    		complaintInfo += "Unknown ";
    	if(complaint.getLastName()!=null)
    		complaintInfo+=complaint.getLastName();
    	else
    		complaintInfo+="Unknown";
    	
    	complaintInfo += "\n";
    	complaintInfo += "Customer Email: ";
		if(complaint.getEmail()!=null)
			complaintInfo += complaint.getEmail();
    	else
    		complaintInfo += "Unknown";

		complaintInfo += "\n";
		complaintInfo += "Customer Phone Number: ";
    	if(complaint.getPhoneNumber()!=null)
    		complaintInfo += complaint.getPhoneNumber();
    	else
    		complaintInfo += "Unknown";
    	
    	
    	complaintInfo += "\n\n";
    	complaintInfo += "Complaint Type: ";
		if(complaint.getComplaintType()!=null)
			complaintInfo+=complaint.getComplaintType();
    	else
    		complaintInfo += "Unknown";
		
		complaintInfo += "\n";
		complaintInfo += "Complaint Title: ";
    	if(complaint.getComplaintTitle()!=null)
    		complaintInfo += complaint.getComplaintTitle();
    	else
    		complaintInfo += "Unknown";
    	
    	complaintInfo += "\n";
    	complaintInfo += "Complaint Details:\n";
    	if(complaint.getComplaintDetails()!=null)
    		complaintInfo += complaint.getComplaintDetails();
    	else
    		complaintInfo += "Unknown";

    	complaintInfoTextField.setText(complaintInfo);
    }
    @FXML
    void closeComplaint() {
    	warningLabel.setVisible(false);
    	String response = responseTextArea.getText();
    	if(response.equals("")) {
    		warningLabel.setText("Please fill a response first");
    		warningLabel.setVisible(true);
    		return;
    	}
    	
    	String compensationString = compensationTextField.getText();
    	if(compensationString.equals("")) {
    		warningLabel.setText("Please fill a compensation first");
    		warningLabel.setVisible(true);
    		return;
    	}
    	
    	float compensation = Float.parseFloat(compensationString);
    	String closedComplaintString;
    	closedComplaintString = "Dear ";
    	if(complaint.getFirstName()!=null)
    		closedComplaintString += complaint.getFirstName();
    	else
    		closedComplaintString+="Unknown";
    	closedComplaintString+= " ";
    	if(complaint.getLastName()!=null)
    		closedComplaintString+=complaint.getLastName();
    	else
    		closedComplaintString+="Unknown";

    	closedComplaintString += ",\nWe have carefully considered your complaint.";
    	closedComplaintString += "Customer service response to you complaint is as followed.\n\n";  	 
    	closedComplaintString += response;
    	if(compensation == 0f) {
    		closedComplaintString += "\nAfter much thought, we have decided no compensation is required.";
    	}
    	else {
    		closedComplaintString += "\nWe have sent a compensation of " + String.valueOf(compensation) + "₪ to the credit card you paid with";
    	}
    	closedComplaintString += "\n\nMany thanks,\nThe Sirtiya";
		
    	Message msg = new Message();
		msg.setAction("send successful purchase mail");
 		msg.setCustomerEmail(complaint.getEmail());
 		msg.setEmailMessage(closedComplaintString);
 		try {
 			AppClient.getClient().sendToServer(msg);
		} 
 		catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Subscribe
    public void OnMessageEvent(Message msg) throws IOException {  	
    	if(msg.getAction().equals("sent successful purchase mail")) {
    		Platform.runLater(()-> {
	        	App.setWindowTitle(PageTitles.OpenComplaintsPage);
	        	try {
					App.setContent("OpenComplaints");
				} catch (IOException e) {
					e.printStackTrace();
				}
    		});
    	} 	
    }
}

