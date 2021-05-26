package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PurchaseCancelationPage {
	private String Order;
	
	private Purchase cust;

	@FXML
	private ResourceBundle resources;

	@FXML
	private TextField OrderTextField;

	@FXML
	private Label OrderWarningLabel;

	@FXML
	private Button SearchNowBtn;

	@FXML
	private Label OrderWarningLabel2;

	@FXML
	private TextField FirstNameTextField;

	@FXML
	private TextField SecondNameTextField;

	@FXML
	private TextField EmailTextField;

	@FXML
	private TextField RefundAmntTextField;

	@FXML
	private Button CancelNowBtn1;

	@FXML
	void CancelBtn(ActionEvent event) {
		
	}

	@FXML
	void padNow(ActionEvent event) throws IOException {
		Order = OrderTextField.getText();
		Message msg = new Message();
		System.out.println("got here " + Order);
		msg.setAction("get purchase by id");
		msg.setId(Order);
		AppClient.getClient().sendToServer(msg);
		
	}
	
    @Subscribe
    public void onMessageEvene(Message msg){
    	if(msg.getAction().equals("got purchase by id")) {
    		Platform.runLater(() ->{
    		cust = msg.getPurchase();
    		gotCustomer();
    		});
    	} 	
    }
    
	@FXML
	void initialize() {
		EventBus.getDefault().register(this);
		assert OrderTextField != null
				: "fx:id=\"OrderTextField\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
		assert OrderWarningLabel != null
				: "fx:id=\"OrderWarningLabel\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
		assert SearchNowBtn != null
				: "fx:id=\"SearchNowBtn\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
		assert OrderWarningLabel2 != null
				: "fx:id=\"OrderWarningLabel2\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
		assert FirstNameTextField != null
				: "fx:id=\"FirstNameTextField\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
		assert SecondNameTextField != null
				: "fx:id=\"SecondNameTextField\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
		assert EmailTextField != null
				: "fx:id=\"EmailTextField\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
		assert RefundAmntTextField != null
				: "fx:id=\"RefundAmntTextField\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";
		assert CancelNowBtn1 != null
				: "fx:id=\"CancelNowBtn1\" was not injected: check your FXML file 'PurchaseCancelation.fxml'.";

		hideWarningLabels();
	}

	private void hideWarningLabels() {
		// TODO Auto-generated method stub
		OrderWarningLabel.setVisible(false);
		OrderWarningLabel2.setVisible(false);
		RefundAmntTextField.setVisible(false);
		FirstNameTextField.setVisible(false);
		SecondNameTextField.setVisible(false);
		EmailTextField.setVisible(false);
		CancelNowBtn1.setVisible(false);
	}
	
	private void foundPurchase()
	{
		FirstNameTextField.setVisible(true);
		SecondNameTextField.setVisible(true);
		EmailTextField.setVisible(true);
		CancelNowBtn1.setVisible(true);
		RefundAmntTextField.setVisible(true);
	}
	
	private void gotCustomer()
	{

		FirstNameTextField.setText(cust.getFirstName());
		SecondNameTextField.setText(cust.getLastName());
		EmailTextField.setText(cust.getEmailOrder());
		RefundAmntTextField.setText(Integer.toString(cust.getPayment()));
		foundPurchase();
	}

}
