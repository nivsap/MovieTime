package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class BranchManagerMenuController {
	
	BranchManager currentManager;

    @FXML
    private AnchorPane menuContainer;

    @FXML
    private Button cinemaMoviesMenuBtn;

    @FXML
    private Button reportsMenuBtn;

    @FXML
    private Button logoutMenuBtn;
    
    void setManager(BranchManager currentManager) {
    	this.currentManager = currentManager;
    }
    
    BranchManager getManager() {
    	return currentManager;
    }
    
    @FXML
    void loadCinemaMovies(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.ReportsPage);
    	App.setContent("CinemaMoviesPage");
    }
    
    @FXML
    void reports(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.ReportsPage);
    	App.setContent("ReportsPage");
    }
    
    
    
    @FXML
    void logout(ActionEvent event) throws IOException {
    	App.logout(true);
    	App.setWindowTitle(PageTitles.MainPage);
    	App.setBarAndGridLayout("MainPage");
    	App.setMenu("SystemMenu");
    }
}
