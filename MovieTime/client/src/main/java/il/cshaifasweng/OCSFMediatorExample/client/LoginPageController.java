package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPageController {
	private Boolean isRegistered = false;
	
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
	void initialize() throws Exception{
		assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'LoginPage.fxml'.";
	    assert usernameWarningLabel != null : "fx:id=\"usernameWarningLabel\" was not injected: check your FXML file 'LoginPage.fxml'.";
	    assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'LoginPage.fxml'.";
	    assert passwordWarningLabel != null : "fx:id=\"passwordWarningLabel\" was not injected: check your FXML file 'LoginPage.fxml'.";
	    assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'LoginPage.fxml'.";
	    assert loginFailedLabel != null : "fx:id=\"loginFailedLabel\" was not injected: check your FXML file 'LoginPage.fxml'.";
	    
	    hideWarningLabels();
	}
	
	void hideWarningLabels() {
		usernameWarningLabel.setVisible(false);
		passwordWarningLabel.setVisible(false);
		loginFailedLabel.setVisible(false);
	}
	
	@FXML
	void login(ActionEvent event) {
		try {
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
    	
    	sendMessageToServer(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessageToServer(String username, String password) {
		try {
		if(!isRegistered) {
			EventBus.getDefault().register(this);
			isRegistered = true;
		}		
		Message msg = new Message();
		msg.setUsername(username);
		msg.setPassword(password);
		msg.setAction("login");
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Subscribe
	public void onMessageEvent(Message msg) throws Exception{
    	if(msg.getAction().equals("login done")) {
    		if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}
    		Platform.runLater(()-> {
    			String workerType = msg.getTypeOfWorker();
    			if(workerType != null) {
    				if(workerType.equals("you are already logged in")) {
    					loginFailedLabel.setText("Login Failed- user is already logged in");
    					loginFailedLabel.setVisible(true);
    					return;
    				}
    				if(workerType.equals("This user does not exist")) {
    					loginFailedLabel.setText("Login Failed- incorrect username or password");
    					loginFailedLabel.setVisible(true);
    					return;
    				}
    				
    				App.setUserName(msg.getUsername());
    				App.setPassword(msg.getPassword());
	    			try {
	    				if(workerType.equals("NetworkAdministrator")) {
	    					App.setWindowTitle(PageTitles.SirtiyaMoviesPage);
	    					App.setBarAndGridLayout("NetworkAdministratorMoviesPage");
	    					
	    				}
	    				if(workerType.equals("ContentManager")) {
	    					App.setWindowTitle(PageTitles.MainPage);
	    					App.setContent("DeleteMoviePage");
	    				}
	    				
	    				if(workerType.equals("BranchManager"))  {
	    					App.setWindowTitle(PageTitles.CinemaMoviesPage);
	    			    	CinemaMoviesPageController controller = (CinemaMoviesPageController) App.setContent("CinemaMoviesPage");
	    			    	controller.getCinemaMovies(((BranchManager)(msg.getWorker())).getCinema());
	    					
	    				}

	    				if(workerType.equals("CustomerService")) {
	    					App.setWindowTitle(PageTitles.PurpleLimitPage);
	    					App.setContent("PurpleLimitPage");
	    				}
	    				

	    				if(workerType.equals("BranchManager")) {
		    				BranchManagerMenuController controller = (BranchManagerMenuController) App.setMenu(workerType + "Menu");
		    				controller.setManager((BranchManager)(msg.getWorker()));
	    				}
	    				else 
	    					 App.setMenu(workerType + "Menu");
					} catch (IOException e) {
						e.printStackTrace();
					}
    			}
    		});
    	}
    	
	}

}
