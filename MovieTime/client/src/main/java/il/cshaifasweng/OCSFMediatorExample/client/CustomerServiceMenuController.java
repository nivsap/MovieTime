package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class CustomerServiceMenuController {

    @FXML
    private AnchorPane menuContainer;

    @FXML
    private Button regulationsUpdateMenuBtn;

    @FXML
    private Button complaintsHandlingMenuBtn;

    @FXML
    private Button logoutMenuBtn;
    
    
    @FXML
    void logout(ActionEvent event) throws IOException {
    	App.logout(true);
    	App.setWindowTitle(PageTitles.MainPage);
    	App.setBarAndGridLayout("MainPage");
    	App.setMenu("SystemMenu");
    }
    
    @FXML
    void loadComplaintHandling(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.OpenComplaintsPage);
    	App.setContent("OpenComplaints");
    }
    @FXML
    void loadPurpleLimit(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.PurpleLimitPage);
    	App.setContent("PurpleLimitPage");
    }
}
