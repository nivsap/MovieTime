package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PriceChangeApprovalsController {
	private Boolean isRegistered;
	@SuppressWarnings("unused")
	private int waitingForMessageCounter;
	
	public PriceChangeApprovalsController() {
		isRegistered = false;
		waitingForMessageCounter = 0;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private Label PendintAlert;

	@FXML
	private Button PendingBtn;

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
	private TextField DateOfRequest;

	@FXML
	private TextField RequestType;

	@FXML
	private TextField ReasonField;

	@FXML
	private ComboBox<String> decisionBox;

	@FXML
	private Button PendingBtn1;

	@FXML
	private Label SubmitAlert;

	@FXML
	private Label ShowTheOldPrice;

	@FXML
	private Label ShowTheNewPrice;

	@FXML
	private Label DecisionTitle;

	@FXML
	void PendReq(ActionEvent event) {
		Message msg = new Message();
		msg.setAction("got pending price change request");
		sendMessageToServer(msg);
	}

	ObservableList<String> list = FXCollections.observableArrayList("Accept", "Reject");

	private void sendMessageToServer(Message msg) {
		try {
			if (!isRegistered) {
				EventBus.getDefault().register(this);
				isRegistered = true;
			}
			AppClient.getClient().sendToServer(msg);
			waitingForMessageCounter++;
		} catch (IOException e) {
			System.out.println("failed to send msg to server from PurchaseCancellationPage");
			waitingForMessageCounter--;
			e.printStackTrace();
		}
	}

	@FXML
	void ShowApproveDenied(ActionEvent event) {
		
	}

	@FXML
	void SubBtn(ActionEvent event) {
		Message msg = new Message();
		msg.setAction("price has changed");
		sendMessageToServer(msg);

	}

	@FXML
	void initialize() {
		
		EventBus.getDefault().register(this);
		assert PendintAlert != null
				: "fx:id=\"PendintAlert\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert PendingBtn != null
				: "fx:id=\"PendingBtn\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
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
		assert DateOfRequest != null
				: "fx:id=\"DateOfRequest\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert RequestType != null
				: "fx:id=\"RequestType\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert ReasonField != null
				: "fx:id=\"ReasonField\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert decisionBox != null
				: "fx:id=\"decisionBox\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert PendingBtn1 != null
				: "fx:id=\"PendingBtn1\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert SubmitAlert != null
				: "fx:id=\"SubmitAlert\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert ShowTheOldPrice != null
				: "fx:id=\"ShowTheOldPrice\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert ShowTheNewPrice != null
				: "fx:id=\"ShowTheNewPrice\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		assert DecisionTitle != null
				: "fx:id=\"DecisionTitle\" was not injected: check your FXML file 'PriceChangeApprovals.fxml'.";
		hideWarnings();
		decisionBox.setItems(list);

	}

	private void hideWarnings() {
		PendintAlert.setVisible(false);
		DateTitle.setVisible(false);
		RequestTitle.setVisible(false);
		OldPriceTitle.setVisible(false);
		NewPriceTitle.setVisible(false);
		ReasonTitle.setVisible(false);
		CommentBox.setVisible(false);
		CommentTitle.setVisible(false);
		DateOfRequest.setVisible(false);
		RequestType.setVisible(false);
		ReasonField.setVisible(false);
		decisionBox.setVisible(false);
		PendingBtn1.setVisible(false);
		SubmitAlert.setVisible(false);
		ShowTheOldPrice.setVisible(false);
		ShowTheNewPrice.setVisible(false);
		DecisionTitle.setVisible(false);
	}

	@Subscribe
	public void onMessageEvene(Message msg) {
		if (msg.getAction().equals("got pending price change request")) {
			Platform.runLater(() -> {
				try {
					App.setContent("OpenPriceChangeRequests");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			if (msg.getAction().equals("price has changed")) {
				Platform.runLater(() -> {
					hideWarnings();
				});
			}

		}
	}
}