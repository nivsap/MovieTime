package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PriceChangeApprovalsController {

	   @FXML
	    private ResourceBundle resources;

	    @FXML
	    private Button PendingBtn;

	    @FXML
	    void PendReq(ActionEvent event) {

	    }

	    @FXML
	    void initialize() {
	        assert PendingBtn != null : "fx:id=\"PendingBtn\" was not injected: check your FXML file 'PurchaseCancelationClientInfo.fxml'.";

	    }
	}