package il.cshaifasweng.OCSFMediatorExample.client;

//import java.awt.TextArea;
//import java.awt.TextField;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class OpenPriceChangeRequestsController {
	private String textCollector;
	private String textfields;
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

		for (Cinema cinema : cinemas) {
			if (cinema.getName().equals(CinemaBox.getValue())) {
				theCinema = cinema;
				OldPriceShow.setText(String.valueOf(cinema.getMoviePrice()));
				OldPriceLeble.setVisible(true);
				OldPriceShow.setVisible(true);
			}
		}

		System.out.println(CinemaBox.getValue());
	}

	@FXML
	void DateBoxShow(ActionEvent event) {
		date = DateBox.getValue();

		System.out.println(date);
	}

	@FXML
	void RequestHandlBtn(ActionEvent event) throws IOException {
		Message msg = new Message();
		msg.setAction("save price request");
		textfields = textFiller.getText();
		if (textCollector.equals("Movie")) {
			prices = new PriceRequest(date.atStartOfDay(), theCinema, true, textfields, price, true);
			msg.setPriceRequestmsg(prices);
		} else {
			prices = new PriceRequest(date.atStartOfDay(), theCinema, false, textfields, price, true);
			msg.setPriceRequestmsg(prices);
		}

		try {
			System.out.println("hi");
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void RequestTypeBox(ActionEvent event) {
		textCollector = RequestBox.getValue();
		System.out.println(textCollector);
	}

	@FXML
	void enterNewPrice(ActionEvent event) {
		price = (int) Double.parseDouble(NewPriceField.getText());
		System.out.println(price);
	}

	@FXML
	void initialize() {
		EventBus.getDefault().register(this);
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

		Message msg = new Message();
		msg.setAction("get all cinemas");
		try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DateBox.setPromptText("Please enter a date");
		RequestBox.setPromptText("Select Type");
		RequestBox.setItems(list);
		hideLabels();

	}

	private void hideLabels() {
		// TODO Auto-generated method stub
		OldPriceShow.setVisible(false);
		OldPriceLeble.setVisible(false);
	}

	@Subscribe
	public void onMessageEvent(Message msg) {
		EventBus.getDefault().unregister(this);
		if (msg.getAction().equals("got all cinemas")) {
			Platform.runLater(() -> {
				CinemaBox.getItems().clear();
				cinemas = msg.getCinemasArrayList();
				System.out.println(cinemas.size());
				System.out.println(cinemas);
				for (Cinema cinema : msg.getCinemasArrayList()) {
					if (!CinemaBox.getItems().contains(cinema.getName())) {
						CinemaBox.getItems().add(cinema.getName());
					}
				}
			});

		}
		if (msg.getAction().equals("done to save price request")) {
			Platform.runLater(() -> {
				try {
					App.setContent("OpenPriceChangeRequests");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}
}
