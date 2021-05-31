package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import  javafx.util.Pair;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.CustomerService;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PurpleLimitPageController {

    @FXML
    private TextField YChoose;

    @FXML
    private Button update;

    @FXML
    private DatePicker FromChooseDate;

    @FXML
    private DatePicker ToChooseDate;
    @Subscribe
    public void OnMessageEvent(Message msg) {  	
    	if(msg.getAction().equals("got purple limit")) {
    		JOptionPane.showMessageDialog(null, "Thank you for your purple limit update.");
    	} 
		    EventBus.getDefault().unregister(this);

    }
 
    @FXML
    void updatePurpleLimit(ActionEvent event) {
    	System.out.println("begingn of updatePurpleLimit ");
        EventBus.getDefault().register(this);
    	Message msg = new Message();
		Worker worker = App.currentWorker;
		((CustomerService) worker).setY(Integer.parseInt(YChoose.getText()));
		LocalDateTime localDatefrom = FromChooseDate.getValue().atStartOfDay();
		LocalDateTime localDateTo = ToChooseDate.getValue().atStartOfDay();
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

