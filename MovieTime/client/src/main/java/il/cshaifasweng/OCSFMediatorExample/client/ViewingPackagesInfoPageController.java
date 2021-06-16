package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.ViewingPackage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ViewingPackagesInfoPageController {
	private boolean isRegistered = false;

    private int purchaseType;
    @SuppressWarnings("unused")
	private Movie currentlyDisplayed;  
    private ArrayList<ViewingPackage> viewingPackages;
    private String date, time;
    private Message msg = new Message();

    @FXML
    private ImageView movieLargeImageSrc;

    @FXML
    private ImageView movieImageSrc;

    @FXML
    private Label moviePopularity;

    @FXML
    private Label movieName;

    @FXML
    private Label movieGenre;

    @FXML
    private Label movieDescription;

    @FXML
    private Label movieDuration;

    @FXML
    private Label movieLaunchDate;
    
    @FXML
    private Label movieProducers;

    @FXML
    private Label movieMainActors;

    @FXML
    private Label movieNameSecond;

    @FXML
    private Label movieGenreSecond;

    @FXML
    private AnchorPane orderTicketsContainer;

    @FXML
    private ComboBox<String> dateCombo;

    @FXML
    private ComboBox<String> timeCombo;

    @FXML
    private Button orderTicketBtn;

    
    
    public void setPurchaseType(int type) {
    	this.purchaseType = type;
    }
    
    public int getPurchaseType() {
    	return this.purchaseType;
    }
    
    
    
    @FXML
    void dateChosen() {
    	try {
    	timeCombo.getItems().clear();
    	for(ViewingPackage view : viewingPackages) {
			time = view.getDateTime().toString().substring(11,16);
			if(view.getDateTime().toString().substring(0,10).equals(dateCombo.getValue()) &&  !timeCombo.getItems().contains(time)) {
				timeCombo.getItems().add(time);
			}
		}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    @FXML
    void ChoosePackage(ActionEvent event) throws IOException {
    	
		if(dateCombo.getValue().isEmpty() || timeCombo.getValue().isEmpty()|| dateCombo.getValue().isEmpty() && timeCombo.getValue().isEmpty() ) {
			JOptionPane.showMessageDialog(null, "You must fill all the fields");
			return;
		}
    	
    	App.setWindowTitle(PageTitles.PaymentPage);
    	PaymentPageController controller = (PaymentPageController) App.setContent("PaymentPage");
    	ViewingPackage chosenView = null;
    	for(ViewingPackage view : viewingPackages) {
		    date = view.getDateTime().toString().substring(0,10);
			time = view.getDateTime().toString().substring(11,16);
			if(dateCombo.getValue().equals(date) && timeCombo.getValue().equals(time)) {
				chosenView = view;
			}
		}
   
    	controller.setInfoForPage(PurchaseTypes.VIEWING_PACKAGE, null, null, chosenView);    	
    
    }
   
    
    void InitPageInfo(Movie movie) {
    	try {
    	currentlyDisplayed = movie;
    	purchaseType = PurchaseTypes.TICKET;
    	movieImageSrc.setImage(movie.getImage());
    	movieLargeImageSrc.setImage(movie.getLargeImage());
    	movieName.setText(movie.getName());
    	movieGenre.setText(movie.getGenre());
    	moviePopularity.setText(movie.getRate().toString());
    	movieNameSecond.setText(movie.getName());
    	movieGenreSecond.setText(movie.getGenre());
    	movieDescription.setText(movie.getDescription());
    	movieDescription.setWrapText(true);
    	movieProducers.setText(movie.getProducers());
    	movieMainActors.setText(movie.getMainActors());
    	movieDuration.setText(movie.getDuration());
    	movieLaunchDate.setText(movie.getLaunchDate().toString());
    	dateCombo.getItems().clear();
    	timeCombo.getItems().clear();

    	if(!isRegistered) {
			EventBus.getDefault().register(this);
			isRegistered = true;
		}
    	msg.setAction("get viewing packages by movie");
    	msg.setMovieName(movie.getName());
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Subscribe
    public void OnMessageEvent(Message msg) {
    	try {
    	System.out.println("got msg in ViewingPackgeInfoPageController");
    	if(msg.getAction().equals("got viewing packages by movie")) {
    		if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}
    		Platform.runLater(()-> {
	    		viewingPackages = (ArrayList)msg.getViewingPackages();
				dateCombo.getItems().clear();
	    		for(ViewingPackage view : viewingPackages) {
	    		     date = view.getDateTime().toString().substring(0,10);
	    			if(!dateCombo.getItems().contains(date)) {
	    				dateCombo.getItems().add(date);
	    			}
	    		}
    		});
    		
    	}} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}

