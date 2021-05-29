package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OpenPriceChangeRequestsController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button RqstHandlBtn;

	@FXML
	private TextField DateField;

	@FXML
	private TextField RequestField;

	@FXML
	private Label RqstTitle;

	@FXML
	private TextField DateField1;

	@FXML
	private TextField RequestField1;

	@FXML
	private TextField RequestField2;

	@FXML
	private TextField RequestField3;

	@FXML
	private TextField DateField2;

	@FXML
	private TextField DateField3;

	@FXML
	private Button RqstHandlBtn1;

	@FXML
	private Button RqstHandlBtn2;

	@FXML
	private Button RqstHandlBtn3;

	@FXML
	private Label NoData;

	@FXML
	void RequestHandlBtn(ActionEvent event) {
		String date = DateField.getText();
		String type = RequestField.getText();
		if(date == "" && type == "" || date == " " && type == " ")
		{
			NoData.setVisible(true);
		}
		
	}

	@FXML
	void RequestHandlBtn1(ActionEvent event) {
		String date = DateField1.getText();
		String type = RequestField1.getText();
		if(date == "" && type == "" || date == " " && type == " ")
		{
			NoData.setVisible(true);
		}
	}

	@FXML
	void RequestHandlBtn2(ActionEvent event) {
		String date = DateField2.getText();
		String type = RequestField2.getText();
		if(date == "" && type == "" || date == " " && type == " ")
		{
			NoData.setVisible(true);
		}
	}

	@FXML
	void RequestHandlBtn3(ActionEvent event) {
		String date = DateField3.getText();
		String type = RequestField3.getText();
		if(date == "" && type == "" || date == " " && type == " ")
		{
			NoData.setVisible(true);
		}
	}

	@FXML
	void initialize() {
		assert RqstHandlBtn != null
				: "fx:id=\"RqstHandlBtn\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert DateField != null
				: "fx:id=\"DateField\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert RequestField != null
				: "fx:id=\"RequestField\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert RqstTitle != null
				: "fx:id=\"RqstTitle\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert DateField1 != null
				: "fx:id=\"DateField1\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert RequestField1 != null
				: "fx:id=\"RequestField1\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert RequestField2 != null
				: "fx:id=\"RequestField2\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert RequestField3 != null
				: "fx:id=\"RequestField3\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert DateField2 != null
				: "fx:id=\"DateField2\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert DateField3 != null
				: "fx:id=\"DateField3\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert RqstHandlBtn1 != null
				: "fx:id=\"RqstHandlBtn1\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert RqstHandlBtn2 != null
				: "fx:id=\"RqstHandlBtn2\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert RqstHandlBtn3 != null
				: "fx:id=\"RqstHandlBtn3\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert NoData != null
				: "fx:id=\"NoData\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		hideAlert();
	}

	private void hideAlert() {
		NoData.setVisible(false);
	}
}
