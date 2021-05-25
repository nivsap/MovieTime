package il.cshaifasweng.OCSFMediatorExample.client.ocsf;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PurchaseCancelationPageController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private TextField OrderTextField;

	@FXML
	private Label OrderWarningLabel;

	@FXML
	private Button SearchNowBtn;


	@FXML
	void initialize() {
		assert OrderTextField != null
				: "fx:id=\"OrderTextField\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
		assert OrderWarningLabel != null
				: "fx:id=\"OrderWarningLabel\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
		assert SearchNowBtn != null
				: "fx:id=\"SearchNowBtn\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
		
		hideWarningLabels();

	}

	private void hideWarningLabels() {
		OrderWarningLabel.setVisible(false);
	}
	
	@FXML
	void padNow(ActionEvent event) {
    	hideWarningLabels();
    	boolean emptyField = true;
    	
    	String Order = OrderTextField.getText();
    	if(Order == "")
    	{
    		OrderWarningLabel.setVisible(true);
    		emptyField = false;
    	}
    	
    	if(emptyField == false) {
    		return;
    	}
    	
    	
	}

}
