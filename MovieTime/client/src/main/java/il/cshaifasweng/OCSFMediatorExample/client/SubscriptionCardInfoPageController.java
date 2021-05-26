package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SubscriptionCardInfoPageController {
    @FXML
    private VBox slideShowContainer;

    @FXML
    private Button buySubscriptionCardsBtn;

    @FXML
    private AnchorPane checkRemainingContainer;

    @FXML
    private TextField subscriptionCardNumberTextField;

    @FXML
    private Label subscriptionCardNumberWarningLabel;

    @FXML
    private Button checkRemainingBtn;

    @FXML
    private Label remainingLabel;
    
    public SubscriptionCardInfoPageController() {
    	slideShowContainer = new VBox();
    }
    
    @FXML
    void initialize() throws IOException {
        assert slideShowContainer != null : "fx:id=\"slideShowContainer\" was not injected: check your FXML file 'SubscriptionCardInfoPage.fxml'.";
        assert buySubscriptionCardsBtn != null : "fx:id=\"buySubscriptionCardsBtn\" was not injected: check your FXML file 'SubscriptionCardInfoPage.fxml'.";
        assert checkRemainingContainer != null : "fx:id=\"checkRemainingContainer\" was not injected: check your FXML file 'SubscriptionCardInfoPage.fxml'.";
        assert subscriptionCardNumberTextField != null : "fx:id=\"subscriptionCardNumberTextField\" was not injected: check your FXML file 'SubscriptionCardInfoPage.fxml'.";
        assert subscriptionCardNumberWarningLabel != null : "fx:id=\"subscriptionCardNumberWarningLabel\" was not injected: check your FXML file 'SubscriptionCardInfoPage.fxml'.";
        assert checkRemainingBtn != null : "fx:id=\"checkRemainingBtn\" was not injected: check your FXML file 'SubscriptionCardInfoPage.fxml'.";
        assert remainingLabel != null : "fx:id=\"remainingLabel\" was not injected: check your FXML file 'SubscriptionCardInfoPage.fxml'.";
        
        subscriptionCardNumberWarningLabel.setVisible(false);
    }

    
    public void setImageSlider() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(getClass().getResource("ImageSlider.fxml"));
		ImageView slider = fxmlLoader.load();
		slideShowContainer.getChildren().add(slider);
		ImageSliderController imageSliderController = fxmlLoader.getController();
		imageSliderController.rotateImages();
    }
    

    @FXML
    void buySubscriptionCard(ActionEvent event) throws IOException {
    	PaymentPageController controller = (PaymentPageController) App.setContent("PaymentPage");
    	controller.setInfo(PurchaseTypes.SUBSCRIPTION_CARD, null, null);
    }

    @FXML
    void checkRemaining(ActionEvent event) {
    	subscriptionCardNumberWarningLabel.setVisible(false);
    	String remaining = subscriptionCardNumberTextField.getText();
    	if(remaining == "") {
    		subscriptionCardNumberWarningLabel.setVisible(true);
    	}
    	else {
    		// Check remaining
    	}
    }
}
