package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;
import java.util.ResourceBundle;
import org.greenrobot.eventbus.EventBus;

public class MainPageAndComingSoonController {

	@FXML
    private VBox vbox1;
	
	@FXML
    private VBox vbox2;
	
	public VBox getTopBar() {
		return vbox1;
	}
	public VBox getCardContainer() {
		return vbox2;
	}
	
	public void decide(String namePage) throws IOException {
		CardContainerController controller;
		FXMLLoader fxmlLoader1 = new FXMLLoader();
		FXMLLoader fxmlLoader2 = new FXMLLoader();

		if(namePage=="MainPage") {
			fxmlLoader1.setLocation(getClass().getResource("SearchBar.fxml"));

		}
		if(namePage=="ComingSoonPage") {
			fxmlLoader1.setLocation(getClass().getResource("ComingSoonBar.fxml"));
		}
		vbox1 = fxmlLoader1.load();
		fxmlLoader2.setLocation(getClass().getResource("CardContainer.fxml"));
		vbox2 = fxmlLoader2.load();
		controller = fxmlLoader2.getController();	
		controller.sendMsgToServer(namePage);
	}
}



