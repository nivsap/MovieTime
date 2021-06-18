package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
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
	private boolean isRegistered = false;


	
	public void setCardController(CardContainerController controller) {
		this.cardController = controller;
	}
	@FXML
	public void initialize() {
		// TODO Auto-generated method stub
		try {
		if(!isRegistered) {
			EventBus.getDefault().register(this);
			isRegistered = true;
		}
		setDisableCards(false);
		actionType = "pull soon movies";
		moviesType = "got soon movies";
		setPurchaseType(PurchaseTypes.NOT_AVAILABLE);
		Message msg = new Message();
		msg.setAction("get genres");
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	@FXML
	void onComboBoxEvent() {
		try {
		if(!isRegistered) {
			EventBus.getDefault().register(this);
			isRegistered = true;
		}
		Message msg = new Message();
		msg.setAction("search bar update");
		msg.setActionType(actionType);
		msg.setMoviesType(moviesType);
		msg.setGenre(genreComboBox.getValue());
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
	
	@Subscribe
	public void onMessageEvent(Message msg) {
		try {
		if(msg.getAction().equals("got genres")) {
			if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}			genreComboBox.getItems().clear();
			for(String genre : msg.getGenres()) {
				genreComboBox.getItems().add(genre);
			}
		}
		if(msg.getAction().equals(moviesType)) {
			if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}
			Platform.runLater(()-> {
			cardController.setMoviesBySearchBar(msg.getMovies());
			});
		}} catch (Exception e) {
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

