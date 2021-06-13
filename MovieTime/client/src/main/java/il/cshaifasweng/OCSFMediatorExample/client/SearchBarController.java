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
		EventBus.getDefault().register(this);
		genreComboBox.getItems().clear();
		theaterComboBox.getItems().clear();
		rateComboBox.getItems().clear();
		theaterComboBox.getItems().addAll("Haifa","Tel-Aviv");
		rateComboBox.getItems().addAll("5.0","4.5","4.0","3.5","3.0","2.5");
		Message msg = new Message();
		msg.setAction("get genres");
		try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	
	public void SetPageType(String namePage) {
		if(namePage.equals("MainPage")) {
    		disableCards = false;
    		actionType = "pull screening movies";
    		moviesType = "got screening movies";
    		purchaseType = PurchaseTypes.TICKET;
		}

		if(namePage.equals("ViewingPackagesPage")) {
			disableCards = false;
			actionType = "pull movies from home";
			moviesType = "got movies from home";
			purchaseType = PurchaseTypes.VIEWING_PACKAGE;
		}
		if(namePage.equals("NetworkAdministratorMainPage")) {
			disableCards = true;
			actionType = "pull screening movies";
			moviesType = "got screening movies";
			purchaseType = PurchaseTypes.NOT_AVAILABLE;
		}
		if(namePage.equals("BranchManagerMainPage")) {
			disableCards = true;
			actionType = "pull screening movies";
			moviesType = "got screening movies";
			purchaseType = PurchaseTypes.NOT_AVAILABLE;
		}
	}
	
	@Subscribe
	public void OnMessageEvent(Message msg) {
		if(msg.getAction().equals("got genres")) {
			genreComboBox.getItems().clear();
			EventBus.getDefault().unregister(this);
			for(String genre : msg.getGenres()) {
				genreComboBox.getItems().add(genre);
			}
		}
		if(msg.getAction().equals(moviesType)) {
			Platform.runLater(()-> {
			EventBus.getDefault().unregister(this);
			cardController.setMoviesBySearchBar(msg.getMovies());
			});
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
			EventBus.getDefault().register(this);
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}












}
