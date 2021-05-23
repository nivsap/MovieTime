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
    private Button viewingPackagesMenuBtn;

    @FXML
    private Button subscriptionCardMenuBtn;

    @FXML
    private Button comingSoonMenuBtn;

    @FXML
    private Button fileComplaintsMenuBtn;

    @FXML
    private Button purchaseCancelationMenuBtn;

    @FXML
    private Button loginMenuBtn;
    

    @FXML
    void loadMainPage(ActionEvent event) throws IOException {
    	App.setBarAndGrid("MainPage", "Movie Time");
    }
    
    @FXML
    void loadSubscriptionCardInfoPage(ActionEvent event) throws IOException {
    	SubscriptionCardInfoPageController controller = (SubscriptionCardInfoPageController) App.setContent("SubscriptionCardInfoPage", "Subscription Cards");
    	controller.setImageSlider();
    }
    
    @FXML
    void loadComingSoonPage(ActionEvent event) throws IOException {
    	App.setBarAndGrid("ComingSoonPage", "Coming Soon");
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
