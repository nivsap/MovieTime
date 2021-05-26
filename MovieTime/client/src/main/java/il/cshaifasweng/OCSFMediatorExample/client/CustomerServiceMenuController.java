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
    private Button ComplaintsHandlingMenuBtn;

    @FXML
    private Button logoutMenuBtn;
    
    @FXML
    void logout(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.MainPage);
    	App.setBarAndGridLayout("MainPage");
    	App.setMenu("SystemMenu");
    }
    @FXML
    void loadComplaint(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.FilingComplaintsPage);
    	System.out.println("I am before OpenComplaints set content");
    	App.setContent("OpenComplaints");
    	System.out.println("I am afterrr OpenComplaints set content");

    }
}
