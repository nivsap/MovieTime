package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javafx.util.Pair;

import il.cshaifasweng.OCSFMediatorExample.entities.CustomerService;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PurpleLimitPageController {

    @FXML
    private Label currentFrom;

    @FXML
    private TextField YChoose;

    @FXML
    private Button update;

    @FXML
    private DatePicker FromChooseDate;

    @FXML
    private DatePicker ToChooseDate;

    @FXML
    private Label warningFrom;

    @FXML
    private Label warningTo;
    @FXML
    private Label enterYLabel;
    
    @Subscribe
    public void OnMessageEvent(Message msg) { 
        EventBus.getDefault().unregister(this);
        System.out.println("I AM HEREEEE");
    	if(msg.getAction().equals("got purple limit")) {
    		JOptionPane.showMessageDialog(null, "Thank you for your purple limit update.");
    	} 
		    EventBus.getDefault().unregister(this);

    }
    
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);

        assert ToChooseDate != null : "fx:id=\"ToChooseDate\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert warningTo != null : "fx:id=\"warningTo\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert FromChooseDate != null : "fx:id=\"FromChooseDate\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert warningFrom != null : "fx:id=\"warningFrom\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert YChoose != null : "fx:id=\"YChoose\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        assert enterYLabel != null : "fx:id=\"enterYLabel\" was not injected: check your FXML file 'PurpleLimitPage.fxml'.";
        hideWarningLabels();

    	String string;
    	Worker worker = App.currentWorker;
    	if(((CustomerService) worker).getDatesOfPurpleLimit()!=null) {
    		string="We have purple limit from:"+((CustomerService) worker).getDatesOfPurpleLimit().getKey().toString()+" to "+((CustomerService) worker).getDatesOfPurpleLimit().getValue().toString()+".";
    	}
    	else {
    		string="There is not purple limit.";
    	}
    	currentFrom.setText(string);

    	//currentFrom.setText(string);
    }
    void hideWarningLabels() {
    	warningTo.setVisible(false);
    	warningFrom.setVisible(false);
    	enterYLabel.setVisible(false);
	}
    
    @FXML
    void PurpleLimit(ActionEvent event) {
    	boolean emptyField = true;
    	System.out.println("begingn of updatePurpleLimit ");
    	Message msg = new Message();
		Worker worker = App.currentWorker;
		if(YChoose.getText().equals("")) {
			enterYLabel.setVisible(true);
    		emptyField = false;
    	}
		((CustomerService) worker).setY(Integer.parseInt(YChoose.getText()));
		LocalDateTime localDatefrom = FromChooseDate.getValue().atStartOfDay();
		LocalDateTime localDateTo = ToChooseDate.getValue().atStartOfDay();
		if(localDatefrom.isAfter(localDateTo)) {
			warningTo.setVisible(true);
			warningFrom.setVisible(true);
    		emptyField = false;
    	}
		
		if(emptyField == false) {
    		return;
    	}
		((CustomerService) worker).setDatesOfPurpleLimit(new Pair<>(localDatefrom,localDateTo ));
    	msg.setWorker(App.currentWorker);
    	System.out.println("before send successful purchase mail");
		msg.setAction("set purple limit");
 			try {
					AppClient.getClient().sendToServer(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

 	
    }
   
}

