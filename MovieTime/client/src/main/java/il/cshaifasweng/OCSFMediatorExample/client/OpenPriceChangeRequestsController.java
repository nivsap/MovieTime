package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.TextArea;
import java.awt.TextField;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.PriceRequest;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class OpenPriceChangeRequestsController {
	private String textCollector;
	private int price;
	private LocalDate date;
	ObservableList<String> list = FXCollections.observableArrayList("Movie", "Viewing Package", "Card");
	private ArrayList<Cinema> cinemas;
	private Cinema theCinema;
	private PriceRequest prices;
	private Message msg2 = new Message();
	
	@FXML
	private ResourceBundle resources;

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
	private TextField NewPriceField;

	@FXML
	private Label OldPriceLeble;

	@FXML
	private Label OldPriceShow;

	@FXML
	private TextArea textFiller;

	@FXML
	private Label ReasonLabel;

	@FXML
	private Label CinemaLable;

	@FXML
	private ComboBox<String> CinemaBox;

	@FXML
	private DatePicker DateBox;
	

	@SuppressWarnings("unlikely-arg-type")
	@FXML
	void CinemaSelect(ActionEvent event) throws IOException {
		Message msg = new Message();
		EventBus.getDefault().register(this);
		msg.setAction("get all cinemas");
		AppClient.getClient().sendToServer(msg);
		for (int i = 0; i < cinemas.size(); i++) {
			if (cinemas.equals(CinemaBox.getValue())) {
				theCinema = cinemas.get(i);
			}
		}
	}

	@FXML
	void DateBoxShow(ActionEvent event) {
		date = DateBox.getValue();
		System.out.println(date);
	}

	@FXML
	void RequestHandlBtn(ActionEvent event) throws IOException {
		Message msg = new Message();
		if (textCollector.equals("Movie")) {
			prices = new PriceRequest(date, theCinema, true, textCollector, 50, true);
			msg.setPriceRequestmsg(prices);
		} else {
			prices = new PriceRequest(date, theCinema, false, textCollector, 50, true);
			msg.setPriceRequestmsg(prices);
		}
		AppClient.getClient().sendToServer(msg);
	}

	@FXML
	void RequestTypeBox(ActionEvent event) {
		textCollector = RequestBox.getValue();
	}

	@FXML
	void enterNewPrice(ActionEvent event) {
		price = (int) Double.parseDouble(NewPriceField.getText());
	}

	@FXML
	void initialize() {
		assert RqstHandlBtn != null
				: "fx:id=\"RqstHandlBtn\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert DateLabel != null
				: "fx:id=\"DateLabel\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert RqstTitle != null
				: "fx:id=\"RqstTitle\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert RequestBox != null
				: "fx:id=\"RequestBox\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert NewPriceLabel != null
				: "fx:id=\"NewPriceLabel\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert NewPriceField != null
				: "fx:id=\"NewPriceField\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert OldPriceLeble != null
				: "fx:id=\"OldPriceLeble\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert OldPriceShow != null
				: "fx:id=\"OldPriceShow\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert textFiller != null
				: "fx:id=\"textFiller\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert ReasonLabel != null
				: "fx:id=\"ReasonLabel\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert CinemaLable != null
				: "fx:id=\"CinemaLable\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert CinemaBox != null
				: "fx:id=\"CinemaBox\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";
		assert DateBox != null
				: "fx:id=\"DateBox\" was not injected: check your FXML file 'OpenPriceChangeRequests.fxml'.";

		DateBox.setPromptText("Please enter a date");
		RequestBox.setPromptText("Select Type");
		OldPriceShow.setText("20");
		
	}

	@Subscribe
	public void onMessageEvent(Message msg) {
		if (msg.getAction().equals("got all cinemas")) {
			Platform.runLater(() -> {
				CinemaBox.getItems().clear();
				cinemas = msg.getCinemasArrayList();
				for (Cinema cinema : msg.getCinemasArrayList()) {
					if (!CinemaBox.getItems().contains(cinema.getName()))
						;
					CinemaBox.getItems().add(cinema.getName());
				}
			});

			if (msg.getAction().equals("got all price request")) {
				Platform.runLater(() -> {
				
				});
			}
		}
	}
}
