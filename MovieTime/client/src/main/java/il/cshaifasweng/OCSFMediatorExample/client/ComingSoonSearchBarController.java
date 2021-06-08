package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.Arrays;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ComingSoonSearchBarController {
	@FXML
    private ComboBox<String> genreComboBox;
	private int purchaseType;
	private String moviesType;
	private Boolean disableCards;
	private String actionType;
	private CardContainerController cardController;
	
	String [] currentType;


	
	public void setCardController(CardContainerController controller) {
		this.cardController = controller;
	}
	@FXML
	public void initialize() {
		// TODO Auto-generated method stub
		EventBus.getDefault().register(this);
		disableCards = false;
		actionType = "pull soon movies";
		moviesType = "got soon movies";
		purchaseType = PurchaseTypes.NOT_AVAILABLE;
		Message msg = new Message();
		msg.setAction("get genres");
		try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	@FXML
	void onComboBoxEvent() {
		Message msg = new Message();
		msg.setAction("search bar update");
		msg.setActionType(actionType);
		msg.setMoviesType(moviesType);
		msg.setGenere(genreComboBox.getValue());
		try {
			EventBus.getDefault().register(this);
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Subscribe
	public void onMessageEvent(Message msg) {
		if(msg.getAction().equals("got genres")) {
			EventBus.getDefault().unregister(this);
			genreComboBox.getItems().clear();
			for(String genre : msg.getGenres()) {
				genreComboBox.getItems().add(genre);
			}
		}
		if(msg.getAction().equals(moviesType)) {
			EventBus.getDefault().unregister(this);
			Platform.runLater(()-> {
			cardController.setMoviesBySearchBar(msg.getMovies());
			});
		}
	}
}

