package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.PurpleLimit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PurpleLimitCardController {

    @FXML
    private Label fromLabel;

    @FXML
    private Label toLabel;

    @FXML
    private Label yLabel;

    @FXML
    void initialize() throws Exception{
        assert fromLabel != null : "fx:id=\"fromLabel\" was not injected: check your FXML file 'PurpleLimitCard.fxml'.";
        assert toLabel != null : "fx:id=\"toLabel\" was not injected: check your FXML file 'PurpleLimitCard.fxml'.";
        assert yLabel != null : "fx:id=\"yLabel\" was not injected: check your FXML file 'PurpleLimitCard.fxml'.";

    }
    
    public void SetPurpleLimitData(PurpleLimit purpleLimit) {
    	fromLabel.setText(purpleLimit.getFromDate().toString());
    	toLabel.setText(purpleLimit.getToDate().toString());
    	yLabel.setText(String.valueOf(purpleLimit.getY()));
    }
}
