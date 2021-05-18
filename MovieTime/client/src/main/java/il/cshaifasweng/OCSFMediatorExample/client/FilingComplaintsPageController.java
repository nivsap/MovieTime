package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FilingComplaintsPageController {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private ComboBox<?> complaintTypeComboBox;

    @FXML
    private DatePicker incidentDatePicket;

    @FXML
    private TextField complaintTitleTextField;

    @FXML
    private TextArea complaintDetailsTextArea;

    @FXML
    private Button fileComplaintBtn;

    @FXML
    void initialize() {
        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintTypeComboBox != null : "fx:id=\"complaintTypeComboBox\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert incidentDatePicket != null : "fx:id=\"incidentDatePicket\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintTitleTextField != null : "fx:id=\"complaintTitleTextField\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert complaintDetailsTextArea != null : "fx:id=\"complaintDetailsTextArea\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
        assert fileComplaintBtn != null : "fx:id=\"fileComplaintBtn\" was not injected: check your FXML file 'FilingComplaintsPage.fxml'.";
    }
    
    @FXML
    void fileComplain(ActionEvent event) {

    }
}
