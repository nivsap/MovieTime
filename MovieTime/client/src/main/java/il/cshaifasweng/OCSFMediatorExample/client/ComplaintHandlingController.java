
package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class ComplaintHandlingController {
	
	private Complaint complaint;

	
    @FXML
    private TextArea orderSummeryTextArea;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField cardCVVTextField;

    @FXML
    private Button payNowBtn;
    
   public ComplaintHandlingController() {
	   complaint=new Complaint();
   }
    @FXML
    void initialize() {
    EventBus.getDefault().register(this);
System.out.println("in the initialize of complaint hanglinsg");
      
       // hideWarningLabels();
       // System.out.println("before setinfo");
    	if(this.complaint==null) {
    		System.out.println("nulllllllllllllll !!!!!!!!!!!!!!##");
    		    	}
       // setInfo(complaint);
        System.out.println("ens setinfo");

        
    }
    void setInfoComplaint(Complaint complaint) {
    	this.complaint=new Complaint();
    	this.complaint=complaint;
    	if(this.complaint==null) {
System.out.println("nulllllllllllllll ###");
    	}
    	setInfo(this.complaint);
    }
    public void setInfo(Complaint complaint) {
        System.out.println(" setinfo");

    	String order = "Complaint date: ";
    	if(complaint==null)
        System.out.println(" after order");

    	if(complaint.getIncidentDate()!=null) {
            System.out.println("before setinfo");
    		order+=complaint.getIncidentDate().toString();
            System.out.println("enddddddddddd setinfo");

    	}
    	else
    		order+="Unknown";
    	 order+= " Complaint from: ";
    	if(complaint.getFirstName()!=null)
    		order+=complaint.getFirstName();
    	else
    		order+="Unknown";

        System.out.println("enddddddddddd getLastName");
    	if(complaint.getLastName()!=null)
    		order+=complaint.getLastName();
    	else
    		order+="Unknown";
		order+="\n";
		
        System.out.println("enddddddddddd Email");
		order+="Email: ";
		if(complaint.getEmail()!=null)
    		order+=complaint.getEmail();
    	else
    		order+="Unknown";

        System.out.println("enddddddddddd Phone");
		order+=" Phone number: ";
    	if(complaint.getPhoneNumber()!=null)
    		order+=complaint.getPhoneNumber();
    	else
    		order+="Unknown";
		order+="\n";
		
        System.out.println("enddddddddddd type");
		order+="Complaint type: ";
		if(complaint.getComplaintType()!=null)
    		order+=complaint.getComplaintType();
    	else
    		order+="Unknown";
		order+="\n";
        System.out.println("enddddddddddd title");
		order+="Complaint title: ";
    	if(complaint.getComplaintTitle()!=null)
    		order+=complaint.getComplaintTitle();
    	else
    		order+="Unknown";
		order+="\n";
		order+="Complaint details: ";
    	if(complaint.getComplaintDetails()!=null)
    		order+=complaint.getComplaintDetails();
    	else
    		order+="Unknown";
        System.out.println("bodore orderSummeryTextArea");
        orderSummeryTextArea.setText(order);
        System.out.println("after orderSummeryTextArea");

        
       // paymentLabel.setText(Double.toString(seats.size() * screening.getCinema().getMoviePrice()));
    	
    	
    }
    @Subscribe
    public void OnMessageEvent(Message msg) {  	
    	if(msg.getAction().equals("sent successful purchase mail")) {
    		JOptionPane.showMessageDialog(null, "Thank you for your response, an email has been sent to the customer with the details");
    	} 	
    }
 
    @FXML
    void padNow(ActionEvent event) {
    	System.out.println("begingn of paynow ");
    	//hideWarningLabels();
    	boolean emptyField = true;
    	String phoneNumber = phoneNumberTextField.getText();
    	 String cardCVV = cardCVVTextField.getText();
    	 String successfulPurchaseString;
 		successfulPurchaseString = "Dear ";
    	if(complaint.getFirstName()!=null)
    		successfulPurchaseString+=complaint.getFirstName();
    	else
    		successfulPurchaseString+="Unknown";
    	successfulPurchaseString+= " ";
    	if(complaint.getLastName()!=null)
    		successfulPurchaseString+=complaint.getLastName();
    	else
    		successfulPurchaseString+="Unknown";
		successfulPurchaseString+=" we have carefully considered your complaint about ";
		if(complaint.getComplaintTitle()!=null)
    		successfulPurchaseString+=complaint.getComplaintTitle();
    	else
    		successfulPurchaseString+="Unknown";
		successfulPurchaseString+=" from ";
		if(complaint.getIncidentDate()!=null)
    		successfulPurchaseString+=complaint.getIncidentDate();
    	else
    		successfulPurchaseString+="Unknown";
		successfulPurchaseString+=".";
		successfulPurchaseString+="\n Your complaint: ";
		if(complaint.getComplaintDetails()!=null)
    		successfulPurchaseString+=complaint.getComplaintDetails();
    	else
    		successfulPurchaseString+="Unknown";
    	 
		successfulPurchaseString+=".\n Our answer: ";
    		successfulPurchaseString+=phoneNumberTextField.getText();
    		successfulPurchaseString+=".\n";    	 

		if(cardCVVTextField.getText().equals("")||cardCVVTextField.getText().equals("0")) {
 			successfulPurchaseString += "Sorry, You did not receive monetary compensation.";}
		else {
			successfulPurchaseString +="Your compensation is:"+cardCVVTextField.getText();}
    	Message msg = new Message();
    	System.out.println("before send successful purchase mail");
		msg.setAction("send successful purchase mail");
 			msg.setCustomerEmail(complaint.getEmail());
 			msg.setEmailMessage(successfulPurchaseString);
 			try {
					AppClient.getClient().sendToServer(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 	
}

   

   
}

