package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPageController {
	@FXML
	private TextField usernameTextField;

	@FXML
	private Label usernameWarningLabel;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private Label passwordWarningLabel;

	@FXML
	private Button loginBtn;
	
    @FXML
    private Label loginFailedLabel;

	@FXML
	void initialize() {
		System.out.println("initializing LoginPage");
		EventBus.getDefault().register(this);
		
		assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'LoginPage.fxml'.";
	    assert usernameWarningLabel != null : "fx:id=\"usernameWarningLabel\" was not injected: check your FXML file 'LoginPage.fxml'.";
	    assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'LoginPage.fxml'.";
	    assert passwordWarningLabel != null : "fx:id=\"passwordWarningLabel\" was not injected: check your FXML file 'LoginPage.fxml'.";
	    assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'LoginPage.fxml'.";
	    assert loginFailedLabel != null : "fx:id=\"loginFailedLabel\" was not injected: check your FXML file 'LoginPage.fxml'.";
	    
	    hideWarningLabels();
	    loginFailedLabel.setVisible(false);
	}
	
	void hideWarningLabels() {
		usernameWarningLabel.setVisible(false);
		passwordWarningLabel.setVisible(false);
	}
	
	@FXML
	void login(ActionEvent event) {
		hideWarningLabels();
		
		String username = usernameTextField.getText();
    	if(username.equals("")) {
    		usernameWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String password = passwordTextField.getText();
    	if(password.equals("")) {
    		passwordWarningLabel.setVisible(true);
    		return;
    	}
    	
		Message msg = new Message();
		msg.setUsername(username);
		msg.setPassword(password);
		msg.setAction("login");
		try {
			System.out.println("Trying to send a login msg to server");
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			System.out.println("Failed to send a login msg to server");
			e.printStackTrace();
		}
	}
	
	@Subscribe
	public void onMessageEvent(Message msg) {
    	if(msg.getAction().equals("login done")) {
    		Platform.runLater(()-> {
    			String menuType = msg.getTypeOfWorkerString();
    			if(menuType != null) {
	    			try {
	    				App.setWindowTitle(PageTitles.MainPage);
						App.setContent("MainPage");
						App.setMenu(menuType + "Menu");
					} catch (IOException e) {
						e.printStackTrace();
					}
    			}
    			
    			else {
    				loginFailedLabel.setVisible(true);
    			}
    		});
    	}
	}

}
