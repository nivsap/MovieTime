package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SubscriptionCardInfoPageController {
    @FXML
    private VBox slideShowContainer;
    
    public SubscriptionCardInfoPageController() {
    	slideShowContainer = new VBox();
    }

    @FXML
    void initialize() {
        assert slideShowContainer != null : "fx:id=\"slideShowContainer\" was not injected: check your FXML file 'SubscriptionCardInfoPage.fxml'.";
    }
    
    public void setImageSlider() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(getClass().getResource("ImageSlider.fxml"));
		ImageView slider = fxmlLoader.load();
		slideShowContainer.getChildren().add(slider);
		ImageSliderController imageSliderController = fxmlLoader.getController();
		imageSliderController.rotateImages();
    }
}
