package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class OpenPriceChangeRequestsController {
	
	ObservableList <String> list = FXCollections.observableArrayList("Movie", "Viewing Package", "Card");
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button RqstHandlBtn;

    @FXML
    private Label DateLabel;

    @FXML
    private Label RqstTitle;

    @FXML
    private ComboBox<String> RequestBox;

    @FXML
    private Label NewPriceLabel;

    @FXML
    private Label OldPriceLeble;

    @FXML
    private Label OldPriceShow;

    @FXML
    private Label ReasonLabel;

    @FXML
    private Label CinemaLable;

    @FXML
    private ComboBox<String> CinemaBox;

    @FXML
    private DatePicker DateBox;

    @FXML
    void CinemaSelect(ActionEvent event) {
    	
    }

    @FXML
    void DateBoxShow(ActionEvent event) {
    	LocalDate date = DateBox.getValue();
    	System.out.print(date.toString());
    }

    @FXML
    void RequestHandlBtn(ActionEvent event) {

    }

    @FXML
    void RequestTypeBox(ActionEvent event) {
    	RequestBox.setItems(list);
    }

    @FXML
    void initialize() {
        assert RqstHandlBtn != null : "fx:id=\"RqstHandlBtn\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert DateLabel != null : "fx:id=\"DateLabel\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert RqstTitle != null : "fx:id=\"RqstTitle\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert RequestBox != null : "fx:id=\"RequestBox\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert NewPriceLabel != null : "fx:id=\"NewPriceLabel\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert OldPriceLeble != null : "fx:id=\"OldPriceLeble\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert OldPriceShow != null : "fx:id=\"OldPriceShow\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert ReasonLabel != null : "fx:id=\"ReasonLabel\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert CinemaLable != null : "fx:id=\"CinemaLable\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert CinemaBox != null : "fx:id=\"CinemaBox\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
        assert DateBox != null : "fx:id=\"DateBox\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";

    }
}
