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
    private Button mainPageMenuBtn;

    @FXML
    private Button specialsMenuBtn;

    @FXML
    private Button fileComplaintsMenuBtn;

    @FXML
    private Button loginMenuBtn;
    
    @FXML
    private Button btn;

    @FXML
    void loadMainPage(ActionEvent event) throws IOException {
    	App.setContent("MainPage", "Movie Time");
    }
    
    @FXML
    void loadLoginPage(ActionEvent event) throws IOException {
    	App.setContent("LoginPage", "Login");
    }
    

    @FXML
    void showSomething(ActionEvent event) throws IOException {
    	HallMapController controller = (HallMapController) App.setContent("HallMap", "Hall Map");
    	controller.setMap(10, 10);
    }

}
