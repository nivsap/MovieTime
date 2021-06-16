package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.SubscriptionCard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SubscriptionCardInfoPageController {

	private boolean isRegistered = false;

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
    private Label remainingTitleLabel;

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
        assert remainingTitleLabel != null : "fx:id=\"remainingTitleLabel\" was not injected: check your FXML file 'SubscriptionCardInfoPage.fxml'.";
        assert remainingLabel != null : "fx:id=\"remainingLabel\" was not injected: check your FXML file 'SubscriptionCardInfoPage.fxml'.";

        hideLabels();
    }
    
    void hideLabels() {
        remainingTitleLabel.setVisible(false);
        remainingLabel.setVisible(false);
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
    	App.setWindowTitle(PageTitles.PaymentPage);
    	PaymentPageController controller = (PaymentPageController) App.setContent("PaymentPage");
    	controller.setInfoForPage(PurchaseTypes.SUBSCRIPTION_CARD, null, null, null);
    }

    @FXML
    void checkRemaining(ActionEvent event) {
    	try {
    	hideLabels();
    	String subscriptionCardNumber = subscriptionCardNumberTextField.getText();
    	if(subscriptionCardNumber.equals("")) {
    		subscriptionCardNumberWarningLabel.setText("Subscription card number must be filled");
    		subscriptionCardNumberWarningLabel.setVisible(true);
    		return;
    	}
    	
    	if(!isRegistered) {
			EventBus.getDefault().register(this);
			isRegistered = true;
		}
    	Message msg = new Message();
    	msg.setAction("get subscription card");
    	msg.setId(Integer.parseInt(subscriptionCardNumber));
    		AppClient.getClient().sendToServer(msg);
    	} catch (IOException e) {
    		System.out.println("failed to send msg to server from SubscriptionCardInfoPage");
    		e.printStackTrace();
    	}
    }
    
    void setRemaining(SubscriptionCard subscriptionCard) {
    	try {
    	hideLabels();
    	if(subscriptionCard == null) {
    		subscriptionCardNumberWarningLabel.setText("Subscription card number not found");
    		subscriptionCardNumberWarningLabel.setVisible(true);
    		return;
    	}
    	remainingTitleLabel.setVisible(true);
        remainingLabel.setText(Integer.toString(subscriptionCard.getRemaining()));
        remainingLabel.setVisible(true);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }

    
    @Subscribe
    public void onMessageEvent(Message msg){
try {
    	if(msg.getAction().equals("got subscription card")) {
    		Platform.runLater(() -> {
    			if(isRegistered) {
    				EventBus.getDefault().unregister(this);
    				isRegistered = false;
    			}
    			setRemaining(msg.getSubscriptionCard());
    		});
    	} } catch (Exception e) {
			e.printStackTrace();
		}	
    }
}
