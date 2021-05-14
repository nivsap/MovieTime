package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SystemMenuController extends Pane {
    @FXML
    private AnchorPane menuContainer;
	
    @FXML
    private Button mainPageBtn;

    @FXML
    private Button specialsBtn;

    @FXML
    private Button fileComplaintsBtn;

    @FXML
    void initialize() {
        assert mainPageBtn != null : "fx:id=\"mainPageBtn\" was not injected: check your FXML file 'SystemMenu.fxml'.";
        assert specialsBtn != null : "fx:id=\"specialsBtn\" was not injected: check your FXML file 'SystemMenu.fxml'.";
        assert fileComplaintsBtn != null : "fx:id=\"fileComplaintsBtn\" was not injected: check your FXML file 'SystemMenu.fxml'.";
    }

    @FXML
    void loadMainPage(ActionEvent event) throws IOException {
    	App.setContent("MainPage", "Movie Time");
    }
}
