package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SearchBarController {
	private boolean isRegistered = false;

	private int purchaseType;
	private String moviesType;
	private Boolean disableCards;
	private String actionType;
	private CardContainerController cardController;
	@FXML
	private VBox SearchBarContainer;

	@FXML
	private TextField mainSearchBar;

	@FXML
	private ComboBox<String> genreComboBox;

	@FXML
	private ComboBox<String> theaterComboBox;

	@FXML
	private ComboBox<String> rateComboBox;



	@FXML
	public void initialize() {
		try {
		if(!isRegistered) {
			EventBus.getDefault().register(this);
			isRegistered = true;
		}
		genreComboBox.getItems().clear();
		theaterComboBox.getItems().clear();
		rateComboBox.getItems().clear();
		theaterComboBox.getItems().addAll("Haifa","Tel-Aviv");
		rateComboBox.getItems().addAll("5.0","4.5","4.0","3.5","3.0","2.5");
		Message msg = new Message();
		msg.setAction("get genres");
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SetPageType(String namePage) {
		if(namePage.equals("MainPage")) {
    		setDisableCards(false);
    		actionType = "pull screening movies";
    		moviesType = "got screening movies";
    		setPurchaseType(PurchaseTypes.TICKET);
		}

		if(namePage.equals("ViewingPackagesPage")) {
			theaterComboBox.setVisible(false);
			setDisableCards(false);
			actionType = "pull movies from home";
			moviesType = "got movies from home";
			setPurchaseType(PurchaseTypes.VIEWING_PACKAGE);
		}
		if(namePage.equals("NetworkAdministratorMoviesPage")) {
			setDisableCards(true);
			actionType = "pull screening movies";
			moviesType = "got screening movies";
			setPurchaseType(PurchaseTypes.NOT_AVAILABLE);
		}
		if(namePage.equals("NetworkAdministratorComingSoonPage")) {
			setDisableCards(true);
			actionType = "pull screening movies";
			moviesType = "got screening movies";
			setPurchaseType(PurchaseTypes.NOT_AVAILABLE);
		}
		if(namePage.equals("NetworkAdministratorViewingPackagesPage")) {
			setDisableCards(true);
			theaterComboBox.setVisible(false);
			actionType = "pull movies from home";
			moviesType = "got movies from home";
			setPurchaseType(PurchaseTypes.NOT_AVAILABLE);
		}
		if(namePage.equals("BranchManagerMainPage")) {
			setDisableCards(true);
			actionType = "pull screening movies";
			moviesType = "got screening movies";
			setPurchaseType(PurchaseTypes.NOT_AVAILABLE);
		}
	}
	
	@Subscribe
	public void OnMessageEvent(Message msg) {
		try {
		if(msg.getAction().equals("got genres")) {
			genreComboBox.getItems().clear();
			if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}
			for(String genre : msg.getGenres()) {
				genreComboBox.getItems().add(genre);
			}
		}
		if(msg.getAction().equals(moviesType)) {
			Platform.runLater(()-> {
				if(isRegistered) {
					EventBus.getDefault().unregister(this);
					isRegistered = false;
				}
				cardController.setMoviesBySearchBar(msg.getMovies());
			});
		}} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void setCardController(CardContainerController controller) {
		this.cardController = controller;
	}


	@FXML
	void onComboBoxEvent() {
		Message msg = new Message();
		msg.setAction("search bar update");
		msg.setActionType(actionType);
		msg.setMoviesType(moviesType);
		msg.setGenre(genreComboBox.getValue());
		msg.setTheater(theaterComboBox.getValue());
		msg.setRate(rateComboBox.getValue());
		msg.setSearch(mainSearchBar.getText());
		try {
			if(!isRegistered) {
				EventBus.getDefault().register(this);
				isRegistered = true;
			}
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(int purchaseType) {
		this.purchaseType = purchaseType;
	}

	public Boolean getDisableCards() {
		return disableCards;
	}

	public void setDisableCards(Boolean disableCards) {
		this.disableCards = disableCards;
	}


}
