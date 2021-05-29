package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PurchaseCancelationFinalStage {

	@FXML
	private ResourceBundle resources;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private TextField emailTextField;

	@FXML
	private TextField cardNumberTextField;

	@FXML
	private DatePicker cardExpirationDatePicker;

	@FXML
	private TextField cardCVVTextField;

	@FXML
	private Label paymentLabel;

	@FXML
	private Button CancelBtn;

	@FXML
	void padNow(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert firstNameTextField != null
				: "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'PurchaseCancelationClientInfo.fxml'.";
		assert lastNameTextField != null
				: "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'PurchaseCancelationClientInfo.fxml'.";
		assert emailTextField != null
				: "fx:id=\"emailTextField\" was not injected: check your FXML file 'PurchaseCancelationClientInfo.fxml'.";
		assert cardNumberTextField != null
				: "fx:id=\"cardNumberTextField\" was not injected: check your FXML file 'PurchaseCancelationClientInfo.fxml'.";
		assert cardExpirationDatePicker != null
				: "fx:id=\"cardExpirationDatePicker\" was not injected: check your FXML file 'PurchaseCancelationClientInfo.fxml'.";
		assert cardCVVTextField != null
				: "fx:id=\"cardCVVTextField\" was not injected: check your FXML file 'PurchaseCancelationClientInfo.fxml'.";
		assert paymentLabel != null
				: "fx:id=\"paymentLabel\" was not injected: check your FXML file 'PurchaseCancelationClientInfo.fxml'.";
		assert CancelBtn != null
				: "fx:id=\"CancelBtn\" was not injected: check your FXML file 'PurchaseCancelationClientInfo.fxml'.";

	}
}
