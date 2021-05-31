
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
      	App.setWindowTitle(PageTitles.MainPage);
    	App.setBarAndGridLayout("MainPage");
    }
    
    @FXML
    void loadSubscriptionCardInfoPage(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.SubscriptionCardInfoPage);
    	SubscriptionCardInfoPageController controller = (SubscriptionCardInfoPageController) App.setContent("SubscriptionCardInfoPage");
    	controller.setImageSlider();
    }
    
    @FXML
    void loadComingSoonPage(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.ComingSoonPage);
    	App.setBarAndGridLayout("ComingSoonPage");
    }
    
    @FXML
    void loadFilingComplaintsPage(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.FilingComplaintsPage);
    	App.setContent("FilingComplaintsPage");
    }
    
    @FXML
    void loadLoginPage(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.LoginPage);
    	App.setContent("LoginPage");
    }
    
    @FXML
    void loadCancelationPage(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.PurchaseCancellationPage);
    	App.setContent("PurchaseCancellationPage");
    }
    @FXML
    void loadViewingPackages(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.ViewingPackages);
    	App.setBarAndGridLayout("ViewingPackagesPage");
    }
    
}
