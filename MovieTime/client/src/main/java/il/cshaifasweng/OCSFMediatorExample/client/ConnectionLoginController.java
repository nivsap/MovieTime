package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ConnectionLoginController {

	private AppClient client;
	
    @FXML
    private TextField tf_host;

    @FXML
    private TextField tf_port;

    @FXML
    private Button loginBtn;
      
    @FXML
    private void Connect(ActionEvent event) throws IOException {
    	if(tf_host.getText().isEmpty()||tf_port.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "please fill all fields");
    	}
    	else {
    		AppClient.setClientNull();
    		client = AppClient.getClient(tf_host.getText(), Integer.parseInt(tf_port.getText()));
    		if(client == null) {
    			System.out.println("client is null");
    		}
    		else {
				try {
					System.out.println(client);
					client.openConnection();
					System.out.println("sending set client msg to server");
					App.setWindowTitle(PageTitles.MainPage);
					App.setBarAndGridLayout("MainPage");
					App.setMenu("SystemMenu");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "incorrect host or port, please try agian.");
				} 
    		}
    	}
    }
    
    public AppClient getClient() {
    	return client;
    }

}
