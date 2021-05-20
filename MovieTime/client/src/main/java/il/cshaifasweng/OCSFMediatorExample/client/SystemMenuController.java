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
    private Button comingSoonMenuBtn;

    @FXML
    private Button fileComplaintsMenuBtn;

    @FXML
    private Button loginMenuBtn;
    

    @FXML
    void loadMainPage(ActionEvent event) throws IOException {
    	App.setContent("MainPage", "Movie Time");
    }
    
    @FXML
    void loadComingSoonPage(ActionEvent event) throws IOException {
    	App.setContent("ComingSoonPage", "Coming Soon");
    }
    
    @FXML
    void loadFilingComplaintsPage(ActionEvent event) throws IOException {
    	App.setContent("FilingComplaintsPage", "File a Compaint");
    }
    
    @FXML
    void loadLoginPage(ActionEvent event) throws IOException {
    	App.setContent("LoginPage", "Login");
    }


    
}
