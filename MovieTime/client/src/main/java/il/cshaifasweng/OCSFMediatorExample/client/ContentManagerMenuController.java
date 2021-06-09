package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ContentManagerMenuController {
  @FXML
    private AnchorPane menuContainer;

    @FXML
    private Button deleteMoviesMenuBtn;

    @FXML
    private Button updateViewingPackagesMenuBtn;

    @FXML
    private Button addContentBtn;

    @FXML
    private Button logoutMenuBtn;
    

    @FXML
    private Button RequestPriceChangeBtn;

    @FXML
    void RequestPriceChange(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.OpenPriceChangeRequests);
    	App.setContent("OpenPriceChangeRequests");
    }

    @FXML
    void loadDeleteMoviesPage(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.DeleteMoviesPage);
    	App.setContent("DeleteMoviePage");
    }
    
    @FXML
    void loadDeleteViewingPackagePage(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.DeleteViewingPackagePage);
    	App.setContent("DeleteViewingPackagePage");
    }
    
    
    @FXML
    void loadUpdateMovies(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.UpdateMoviesPage);
    	App.setContent("UpdateMoviesPage");
    }
    

    @FXML
    void loadAddContentePage(ActionEvent event) throws IOException {
    	App.setWindowTitle(PageTitles.AddContentPage);
    	AddContentPageController controller = (AddContentPageController) App.setContent("AddContentPage");
    	controller.loadFileLoaderButtons();
    }
    
    @FXML
    void logout(ActionEvent event) throws IOException {
    	App.logout(true);
    	App.setWindowTitle(PageTitles.MainPage);
    	App.setBarAndGridLayout("MainPage");
    	App.setMenu("SystemMenu");
    }

}
