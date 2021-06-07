package il.cshaifasweng.OCSFMediatorExample.client;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PriceChangeApprovalsController {

	private PriceRequest request;
	private String Text;
	private Double price;
	private ArrayList<PriceRequest> requests;
	private LocalDate date;
	private Cinema cinema;

	ObservableList<String> list = FXCollections.observableArrayList("Approve", "Denied");
	@FXML
	private ResourceBundle resources;

	@FXML
	private Label DateTitle;

	@FXML
	private Label RequestTitle;

	@FXML
	private Label OldPriceTitle;

	@FXML
	private Label NewPriceTitle;

	@FXML
	private Label ReasonTitle;

	@FXML
	private TextArea CommentBox;

	@FXML
	private Label CommentTitle;

	@FXML
	private ComboBox<String> decisionBox;

	@FXML
	private Button PendingBtn1;

	@FXML
	private Label ShowTheOldPrice;

	@FXML
	private Label ShowTheNewPrice;

	@FXML
	private Label DecisionTitle;

	@FXML
	private Label numRequestLabel;

	@FXML
	private Label ReasonShow;

	@FXML
	private Label RequestTypeShow;

	@FXML
	private Label DateShow;

	@FXML
	private Label numRequestShow;

	@FXML
	void ShowApproveDenied(ActionEvent event) {
		Text = decisionBox.getValue();
	}

	@FXML
	void SubBtn(ActionEvent event) {
		Message msg = new Message();
		if (Text.equals("Approve")) {
			request.setOpen(false);
			request.getCinema().setMoviePrice(request.getNewPrice());
			try {
				App.setWindowTitle(PageTitles.PriceChangePage);
				App.setContent("PriceChangeApprovals");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (Text.equals("Denied")) {
			request.setOpen(false);
			try {
				App.setWindowTitle(PageTitles.PriceChangePage);
				App.setContent("PriceChangeApprovals");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void initialize() {
		assert DateTitle != null
				: "fx:id=\"DateTitle\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert RequestTitle != null
				: "fx:id=\"RequestTitle\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert OldPriceTitle != null
				: "fx:id=\"OldPriceTitle\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert NewPriceTitle != null
				: "fx:id=\"NewPriceTitle\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert ReasonTitle != null
				: "fx:id=\"ReasonTitle\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert CommentBox != null
				: "fx:id=\"CommentBox\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert CommentTitle != null
				: "fx:id=\"CommentTitle\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert decisionBox != null
				: "fx:id=\"decisionBox\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert PendingBtn1 != null
				: "fx:id=\"PendingBtn1\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert ShowTheOldPrice != null
				: "fx:id=\"ShowTheOldPrice\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert ShowTheNewPrice != null
				: "fx:id=\"ShowTheNewPrice\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert DecisionTitle != null
				: "fx:id=\"DecisionTitle\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert numRequestLabel != null
				: "fx:id=\"numRequestLabel\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert ReasonShow != null
				: "fx:id=\"ReasonShow\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert RequestTypeShow != null
				: "fx:id=\"RequestTypeShow\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert DateShow != null
				: "fx:id=\"DateShow\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert numRequestShow != null
				: "fx:id=\"numRequestShow\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";

		EventBus.getDefault().register(this);
		Message msg = new Message();
		msg.setAction("get all price request");
		try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		decisionBox.setItems(list);

	}

	void textSetter() {
		numRequestShow.setText(String.valueOf(requests.size()));
		date = request.getRequestDate();
		price = request.getNewPrice();
		ShowTheNewPrice.setText(String.valueOf(price));
		if (request.isMovie()) {
			RequestTypeShow.setText("Movie");
		} else {
			RequestTypeShow.setText("not Movie");
		}
		ReasonShow.setText(request.getCommentString());
		DateShow.setText(date.toString());
		ShowTheOldPrice.setText(String.valueOf(request.getCinema().getMoviePrice()));

	}

	void hidePlease() {
		numRequestShow.setVisible(false);
		DateShow.setVisible(false);
		ShowTheNewPrice.setVisible(false);
		ShowTheOldPrice.setVisible(false);
		RequestTypeShow.setVisible(false);
		decisionBox.setVisible(false);
		ReasonShow.setVisible(false);
		CommentBox.setVisible(false);
	}

	@Subscribe
	public void onMessageEvent(Message msg) {
		EventBus.getDefault().unregister(this);
		if (msg.getAction().equals("got all price request")) {
			Platform.runLater(() -> {
				requests = msg.getPriceRequestsArrayList();
				for (PriceRequest priceReq : requests) {
					if (priceReq.isOpen()) {
						request = priceReq;
						textSetter();
					}
					if (!priceReq.isOpen()) {
						hidePlease();
					}
				}

			});

		}
	}

}