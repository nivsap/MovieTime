package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaymentPageController {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField zipCodeTextField;

    @FXML
    private TextField cardHoldersNameTextField;

    @FXML
    private TextField cardHoldersIDTextField;

    @FXML
    private ComboBox<?> paymentNumberComboBox;

    @FXML
    private TextField cardNumberTextField;

    @FXML
    private DatePicker cardExpirationDatePicker;

    @FXML
    private TextField cardCVVTextField;

    @FXML
    private Label paymentLabel;

    @FXML
    private Button payNowBtn;

    @FXML
    void padNow(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert addressTextField != null : "fx:id=\"addressTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cityTextField != null : "fx:id=\"cityTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert zipCodeTextField != null : "fx:id=\"zipCodeTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardHoldersNameTextField != null : "fx:id=\"cardHoldersNameTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardHoldersIDTextField != null : "fx:id=\"cardHoldersIDTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert paymentNumberComboBox != null : "fx:id=\"paymentNumberComboBox\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardNumberTextField != null : "fx:id=\"cardNumberTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardExpirationDatePicker != null : "fx:id=\"cardExpirationDatePicker\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert cardCVVTextField != null : "fx:id=\"cardCVVTextField\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert paymentLabel != null : "fx:id=\"paymentLabel\" was not injected: check your FXML file 'PaymentPage.fxml'.";
        assert payNowBtn != null : "fx:id=\"payNowBtn\" was not injected: check your FXML file 'PaymentPage.fxml'.";
    }
}
