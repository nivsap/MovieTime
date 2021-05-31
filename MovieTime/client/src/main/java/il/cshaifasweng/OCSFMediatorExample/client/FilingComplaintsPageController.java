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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FilingComplaintsPageController  {
	private Boolean isRegistered;
	private int waitingForMessageCounter;
	private Complaint newComplaint;

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
    private ComboBox<String> complaintTypeComboBox;

    @FXML
    private Label complaintTypeWarningLabel;

    @FXML
    private TextField orderNumberTextField;

    @FXML
    private Label orderNumberWarningLabel;

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

    public FilingComplaintsPageController() {
    	isRegistered = false;
    	waitingForMessageCounter = 0;
    	newComplaint = new Complaint();
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
        assert orderNumberTextField != null : "fx:id=\"orderNumberTextField\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert orderNumberWarningLabel != null : "fx:id=\"orderNumberWarningLabel\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintTitleTextField != null : "fx:id=\"complaintTitleTextField\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintTitleWarningLabel != null : "fx:id=\"complaintTitleWarningLabel\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintDetailsTextArea != null : "fx:id=\"complaintDetailsTextArea\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintDetailsWarningLabel != null : "fx:id=\"complaintDetailsWarningLabel\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert fileComplaintBtn != null : "fx:id=\"fileComplaintBtn\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        
        complaintTypeComboBox.getItems().addAll(Complaint.getComplaintTypes());
        hideWarningLabels();
    }
    
	void hideWarningLabels() {
		firstNameWarningLabel.setVisible(false);
		lastNameWarningLabel.setVisible(false);
		emailWarningLabel.setVisible(false);
		phoneNumberWarningLabel.setVisible(false);
		complaintTypeWarningLabel.setVisible(false);
		orderNumberWarningLabel.setVisible(false);
		complaintTitleWarningLabel.setVisible(false);
		complaintDetailsWarningLabel.setVisible(false);
	}
    
    @FXML
    void fileComplaint() {
    	hideWarningLabels();
    	
    	String firstName = firstNameTextField.getText();
    	if(firstName.equals("")) {
    		firstNameWarningLabel.setVisible(true);
    		return;
    	}
    	newComplaint.setFirstName(firstName);
    	
    	String lastName = lastNameTextField.getText();
    	if(lastName.equals("")) {
    		lastNameWarningLabel.setVisible(true);
    		return;
    	}
    	newComplaint.setLastName(lastName);
    	
    	String email = emailTextField.getText();
    	if(email.equals("")) {
    		emailWarningLabel.setVisible(true);
    		return;
    	}
    	newComplaint.setEmail(email);
    	
    	String phoneNumber = phoneNumberTextField.getText();
    	if(phoneNumber.equals("")) {
    		phoneNumberWarningLabel.setVisible(true);
    		return;
    	}
    	newComplaint.setPhoneNumber(phoneNumber);
    	
    	String complaintType = (String) complaintTypeComboBox.getValue();
    	if(complaintType.equals("")) {
    		complaintTypeWarningLabel.setVisible(true);
    		return;
    	}
    	newComplaint.setComplaintType(complaintType);

    	String complaintTitle = complaintTitleTextField.getText();
    	if(complaintTitle.equals("")) {
    		complaintTitleWarningLabel.setVisible(true);
    		return;
    	}
    	newComplaint.setComplaintTitle(complaintTitle);
    	
    	String complaintDetails = complaintDetailsTextArea.getText();
    	if(complaintDetails.equals("")) {
    		complaintDetailsWarningLabel.setVisible(true);
    		return;
    	}
    	newComplaint.setComplaintDetails(complaintDetails);
    	
    	String orderNumber = orderNumberTextField.getText();
    	if(orderNumber == null) {
    		orderNumberWarningLabel.setText("Order number must be filled");
    		orderNumberWarningLabel.setVisible(true);
    		return;
    	}
    	getOrderByID((Integer.parseInt(orderNumber)));
    }
 
    public void getOrderByID(int orderNumber) {
		Message msg = new Message();
		msg.setAction("get purchase by id");
		msg.setId(orderNumber);
		sendMessageToServer(msg);
    }
    
    public void fileComplaint(Purchase foundPurchase) {
    	newComplaint.setPurchase(foundPurchase);
    	newComplaint.setCinema(foundPurchase.getCinema());
    	newComplaint.setIncidentDate(null);
    	newComplaint.setIsOpen(true);
    	newComplaint.setStatus(true);
    	Message msg = new Message();
		msg.setComplaint(newComplaint);
		msg.setAction("add a complaint");
		sendMessageToServer(msg);
    }
    
    void sendMessageToServer(Message msg) {
		try {
			if(!isRegistered) {
				EventBus.getDefault().register(this);
				isRegistered = true;
			}
			AppClient.getClient().sendToServer(msg);
			waitingForMessageCounter++;
		} catch (IOException e) {
			System.out.println("failed to send msg to server from FilingComplaintsPage");
			waitingForMessageCounter--;
			e.printStackTrace();
		}
	}

    @Subscribe
	public void onMessageEvent(Message msg) throws IOException {
    	
       	if(msg.getAction().equals("got purchase by id")) {
    		Platform.runLater(() -> {
    			Purchase foundPurchase = msg.getPurchase();
        		waitingForMessageCounter--;
            	if(waitingForMessageCounter == 0 && isRegistered) {
            		EventBus.getDefault().unregister(this);
            		isRegistered = false;
            	}
    			if(foundPurchase == null) {
    				orderNumberWarningLabel.setText("Order number not found");
    				orderNumberWarningLabel.setVisible(true);
    			}
    			else {
    				fileComplaint(foundPurchase);
    			}
    		});
    	} 	
    	if(msg.getAction().equals("added a complaint")) {
        	Platform.runLater(()-> {
        		waitingForMessageCounter--;
            	if(waitingForMessageCounter == 0 && isRegistered) {
            		EventBus.getDefault().unregister(this);
            		isRegistered = false;
            	}
    			try {
    				App.setWindowTitle("Thank you");
    				ComplaintAddedPageController controller = (ComplaintAddedPageController) App.setContent("ComplaintAddedPage");
    	    		controller.setData(newComplaint);
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		});
    	}
    }
}
