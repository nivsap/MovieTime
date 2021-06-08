package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class NetworkAdministratorMenuController {

    @FXML
    private AnchorPane menuContainer;

    @FXML
    private Button pricingApprovalsMenuBtn;

    @FXML
    private Button reportsMenuBtn;

    @FXML
    private Button logoutMenuBtn;
    
    @FXML
    void ShowPriceRequest(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.PriceChangePage);
    	App.setContent("PriceChangeApprovals");
    }
    
    @FXML
    void logout(ActionEvent event) throws IOException {
    	App.logout(true);
    	App.setWindowTitle(PageTitles.MainPage);
    	App.setBarAndGridLayout("MainPage");
    	App.setMenu("SystemMenu");
    }

}