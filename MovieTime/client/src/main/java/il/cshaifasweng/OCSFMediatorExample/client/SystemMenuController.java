
package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

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
    	cancelOrder();
      	App.setWindowTitle(PageTitles.MainPage);
    	App.setBarAndGridLayout("MainPage");
    }
    
    @FXML
    void loadSubscriptionCardInfoPage(ActionEvent event) throws IOException {
    	cancelOrder();
    	App.setWindowTitle(PageTitles.SubscriptionCardInfoPage);
    	SubscriptionCardInfoPageController controller = (SubscriptionCardInfoPageController) App.setContent("SubscriptionCardInfoPage");
    	controller.setImageSlider();
    }
    
    @FXML
    void loadComingSoonPage(ActionEvent event) throws IOException {
    	cancelOrder();
    	App.setWindowTitle(PageTitles.ComingSoonPage);
    	App.setBarAndGridLayout("ComingSoonPage");
    }
    
    @FXML
    void loadFilingComplaintsPage(ActionEvent event) throws IOException {
    	cancelOrder();
    	App.setWindowTitle(PageTitles.FilingComplaintsPage);
    	App.setContent("FilingComplaintsPage");
    }
    
    @FXML
    void loadLoginPage(ActionEvent event) throws IOException {
    	cancelOrder();
    	App.setWindowTitle(PageTitles.LoginPage);
    	App.setContent("LoginPage");
    }
    
    @FXML
    void loadCancelationPage(ActionEvent event) throws IOException {
    	cancelOrder();
    	App.setWindowTitle(PageTitles.PurchaseCancellationPage);
    	App.setContent("PurchaseCancellationPage");
    }
    

    @FXML
    void loadViewingPackages(ActionEvent event) throws IOException {
    	cancelOrder();
    	App.setWindowTitle(PageTitles.ViewingPackages);
    	App.setBarAndGridLayout("ViewingPackagesPage");
    }
    
    private void cancelOrder() {    
    	if(App.getCurrentController()!= null) {	 
    		if( App.getCurrentController().getClass().equals(PaymentPageController.class)) {
		    	PaymentPageController currentController = (PaymentPageController)App.getCurrentController();
		    	Message msg = new Message();
		    	msg.setAction("cancel current order");
		    	ArrayList<Pair<Integer,Integer>> seats = new ArrayList<Pair<Integer,Integer>>();
		    	Screening screening = ((PaymentPageController) currentController).getScreening();
		    	
		    	if(screening != null) {
			    	seats = (ArrayList)((PaymentPageController) currentController).getSeats();
			    	for(Pair<Integer,Integer> seat : seats) {
			    		screening.getSeats()[seat.getKey()][seat.getValue()] = 0;
			    	}
			    	msg.setScreening(screening);
			    	try {
						AppClient.getClient().sendToServer(msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
    		}
    	}
    }
    
}
