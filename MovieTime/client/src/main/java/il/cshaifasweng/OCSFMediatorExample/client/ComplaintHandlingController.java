
package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ComplaintHandlingController {
	
	private Complaint complaint;
	
    @FXML
    private Label complaintInfoLabel;

    @FXML
    private Label orderInfoLabel;

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

    	String complaintInfo = "Customer Name: ";
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
    	
    	
    	complaintInfoLabel.setText(complaintInfo);
    	setPurchaseInfo();
    }
    
    void setPurchaseInfo() {
    	if(complaint == null)
    		return;
    	
    	Purchase complaintPurchase = complaint.getPurchase();
    	String purchaseInfo = "";
    	
    	if(complaintPurchase == null) {
    		orderInfoLabel.setText("Unknown Order Info :(");
    		return;
    	}
    	
    	purchaseInfo += "Purchaser Name: ";
    	if(!complaintPurchase.getFirstName().equals("")) 
    		purchaseInfo += complaintPurchase.getFirstName() + " ";
    	else 
    		purchaseInfo += "Unknown ";
    	if(!complaintPurchase.getLastName().equals("")) 
    		purchaseInfo += complaintPurchase.getLastName();
    	else 
    		purchaseInfo += "Unknown";
    	
    	purchaseInfo += "\n";
    	purchaseInfo += "Purchaser Email: ";
    	if(!complaintPurchase.getEmail().equals("")) 
    		purchaseInfo += complaintPurchase.getEmail();
    	else 
    		purchaseInfo += "Unknown";
    	
    	purchaseInfo += "\n";
    	purchaseInfo += "Purchaser Phone: ";
    	if(!complaintPurchase.getPhone().equals("")) 
    		purchaseInfo += complaintPurchase.getPhone();
    	else 
    		purchaseInfo += "Unknown";
    	
    	purchaseInfo += "\n";

    	
    	String date = complaintPurchase.getPurchaseTime().toString();
    	if(date.equals("")) 
        	purchaseInfo += "Order Date: Unknown\nOrder Time: Unknown";
    	
    	else {
    		purchaseInfo += "Order Date: " + date.substring(0,10) + "\n";
    		purchaseInfo += "Order Time: " + date.substring(11,16);
    	}

    	purchaseInfo += "\n";
    	purchaseInfo += "Paid Amount: ";
    	if(!String.valueOf(complaintPurchase.getPayment()).equals(""))
    		purchaseInfo += String.valueOf(complaintPurchase.getPayment());
    	else 
    		purchaseInfo += "Unknown";
    	
    	
    	purchaseInfo += "\n";
    	purchaseInfo += "Canceled Order: ";
    	if(complaintPurchase.isCanceled()) 
    		purchaseInfo += "Yes";
    	else 
    		purchaseInfo += "No";

    	orderInfoLabel.setText(purchaseInfo);
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
    	closedComplaintString += "\nCustomer service response to your complaint is as follows.\n\n";  	 
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
 		msg.setAction("close complaint");
 		complaint.setIsOpen(false);
 		msg.setComplaint(complaint);
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

