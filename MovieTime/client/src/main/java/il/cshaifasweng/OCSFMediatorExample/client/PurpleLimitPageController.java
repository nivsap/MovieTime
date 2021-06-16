package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.PurpleLimit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PurpleLimitPageController {
	private ArrayList<PurpleLimit> activePurpleLimits;
	private Boolean isAdding = false;
	private boolean isRegistered = false;

    @FXML
    private Label noRegulationsLabel;

    @FXML
    private VBox currentRegulationsContainer;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private Label fromWarningLabel;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private Label toWarningLabel;

    @FXML
    private TextField yFactorTextField;

    @FXML
    private Label yFactorWarningLabel;

    @FXML
    private Button setBtn;

    @FXML
    private Label setBtnWarningLabel;

    @FXML
    private Label successLabel;
    
    
    
    @FXML
    void initialize() {
    	assert noRegulationsLabel != null : "fx:id=\"noRegulationsLabel\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert currentRegulationsContainer != null : "fx:id=\"currentRegulationsContainer\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert fromDatePicker != null : "fx:id=\"fromDatePicker\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert fromWarningLabel != null : "fx:id=\"fromWarningLabel\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert toDatePicker != null : "fx:id=\"toDatePicker\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert toWarningLabel != null : "fx:id=\"toWarningLabel\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert yFactorTextField != null : "fx:id=\"yFactorTextField\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert yFactorWarningLabel != null : "fx:id=\"yFactorWarningLabel\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert setBtn != null : "fx:id=\"setBtn\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert setBtnWarningLabel != null : "fx:id=\"setBtnWarningLabel\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert successLabel != null : "fx:id=\"successLabel\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
         
        hideLabels();
        getActiveRegulations();
    }
    
    void getActiveRegulations() {
    	if(!isRegistered) {
			EventBus.getDefault().register(this);
			isRegistered = true;
		}
    	Message msg = new Message();
		msg.setAction("get active purple limits");
 		try {
			AppClient.getClient().sendToServer(msg);
		} 
 		catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    void setCurrentRegulations() {
    	if(activePurpleLimits == null || activePurpleLimits.isEmpty()) {
    		currentRegulationsContainer.getChildren().clear();
    		currentRegulationsContainer.getChildren().add(noRegulationsLabel);
    		noRegulationsLabel.setVisible(true);
    	}
    	else {
			currentRegulationsContainer.getChildren().clear();
			noRegulationsLabel.setVisible(false);
			try {
				for(PurpleLimit p : activePurpleLimits) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("PurpleLimitCard.fxml"));
					HBox card = fxmlLoader.load();	
					PurpleLimitCardController ctrl = fxmlLoader.getController();
					ctrl.SetPurpleLimitData(p);
					currentRegulationsContainer.getChildren().add(card);
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    
    void hideLabels() {
    	noRegulationsLabel.setVisible(false);
    	fromWarningLabel.setVisible(false);
    	toWarningLabel.setVisible(false);
    	yFactorWarningLabel.setVisible(false);
    	setBtnWarningLabel.setVisible(false);
    	successLabel.setVisible(false);
	}
    
    Boolean isLegalPurpleLimit(PurpleLimit purpleLimit) {
    	for(PurpleLimit p: activePurpleLimits) {
    		if(p.getFromDate() == purpleLimit.getFromDate() || p.getToDate() == purpleLimit.getToDate())
    			return false;
    		if(p.getFromDate().isBefore(purpleLimit.getFromDate()) && p.getToDate().isAfter(purpleLimit.getToDate())) 
    			return false;
    	}
    	return true;
    }
    
    @FXML
    void addPurpleLimit(ActionEvent event) {
    	if(isAdding)
    		return;
    	
    	hideLabels();

		LocalDate fromDate = fromDatePicker.getValue();
		if(fromDate == null) {
			fromWarningLabel.setText("From Date must be picked");
			fromWarningLabel.setVisible(true);
			return;
		} else {
			if(!InputTests.isValidDate(fromDate.toString())) {
				fromWarningLabel.setText("From Date is invalid");
				fromWarningLabel.setVisible(true);
				return;
			}
			
			if(fromDate.isBefore(LocalDate.now())) {
				fromWarningLabel.setText("From Date has passed");
				fromWarningLabel.setVisible(true);
				return;
			}
		}
		
		LocalDate toDate = toDatePicker.getValue();
		if(toDate == null) {
			toWarningLabel.setText("To Date must be picked");
			toWarningLabel.setVisible(true);
			return;
		} else {
			if(!InputTests.isValidDate(toDate.toString())) {
				toWarningLabel.setText("To Date is invalid");
				toWarningLabel.setVisible(true);
				return;
			}
			
			if(toDate.isBefore(fromDate)) {
				toWarningLabel.setText("To Date must be after From Date");
				toWarningLabel.setVisible(true);
				return;
			}
		}
		
    	String yFactor = yFactorTextField.getText();
		if(yFactor.equals("")) {
			yFactorWarningLabel.setText("Y Factor must be filled");
			yFactorWarningLabel.setVisible(true);
			return;
		} else {
			if(!InputTests.isValidInt(yFactor)) {
				yFactorWarningLabel.setText("Y Factor is invalid");
				yFactorWarningLabel.setVisible(true);
				return;
			}
		}
		
		PurpleLimit p = new PurpleLimit(fromDate, toDate, Integer.parseInt(yFactor));
		if(!isLegalPurpleLimit(p)) {
			setBtnWarningLabel.setText("Can't set regulation\nThere is a set regulation within the chosen dates!");
			setBtnWarningLabel.setVisible(true);
			return;
		}
		
		if(!isRegistered) {
			EventBus.getDefault().register(this);
			isRegistered = true;
		}
		Message msg = new Message();
		msg.setPurpleLimit(p);
		msg.setAction("add purple limit");
		isAdding = true;
 		try {
			AppClient.getClient().sendToServer(msg);
		} 
 		catch (IOException e) {
			e.printStackTrace();
		}	
    }
    
    void setSuccessfullyAdded() {
    	hideLabels();
        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);
        yFactorTextField.clear();
        successLabel.setVisible(true);
    	setCurrentRegulations();
    }
    
    @Subscribe
    public void OnMessageEvent(Message msg) throws IOException { 
    	System.out.println("got message in PurpleLimitPageController");
    	if(msg.getAction().equals("got active purple limits")) {
    		if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			} 
    		Platform.runLater(()-> {
    			activePurpleLimits = msg.getActivePurpleLimits();
    			setCurrentRegulations();
    		});
    	}   
    	if(msg.getAction().equals("added purple limit")) {
    		Platform.runLater(()-> {
    			if(isRegistered) {
    				EventBus.getDefault().unregister(this);
    				isRegistered = false;
    			}  
    			isAdding = false;
    			activePurpleLimits = msg.getActivePurpleLimits();
    			setSuccessfullyAdded();
    		});
    	}     
    }
}

