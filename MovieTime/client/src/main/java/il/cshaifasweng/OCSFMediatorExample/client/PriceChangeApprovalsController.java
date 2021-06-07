package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PriceChangeApprovalsController {

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
	private ComboBox<?> decisionBox;

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

	}

	@FXML
	void SubBtn(ActionEvent event) {

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
		
		Massage msg = new Message();

	}
}