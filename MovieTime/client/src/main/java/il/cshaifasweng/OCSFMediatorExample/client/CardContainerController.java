package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CardContainerController {	
	private Boolean isRegistered;
	private int waitingForMessageCounter, currentlyDisplayedFrom, moviesNumber, type;
	private int NUM_ROWS = 2, NUM_COLS = 3;
	private String moviesType;
	private List<Movie> recentlyAdded;
	private Boolean disableCards;
	
    @FXML
    private GridPane movieContainer;

    @FXML
    private VBox cell1;

    @FXML
    private VBox cell2;

    @FXML
    private VBox cell3;

    @FXML
    private VBox cell4;

    @FXML
    private VBox cell5;

    @FXML
    private VBox cell6;

    @FXML
    private Button loadMoreBtn;

    public CardContainerController() {
    	try {
    	isRegistered = false;
    	setWaitingForMessageCounter(0);
    	moviesType = "";
    	recentlyAdded = new ArrayList<Movie>();
    	currentlyDisplayedFrom = 0;
    	moviesNumber = 0; 
    	type = PurchaseTypes.NOT_AVAILABLE;
    	disableCards = false;
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void setGridContent(String namePage) {
    	try {
		String actionType = null;
		if(!isRegistered) {
			EventBus.getDefault().register(this);
			isRegistered = true;
		}
    	if(namePage.equals("MainPage")) {
    		disableCards = false;
    		actionType = "pull screening movies";
    		moviesType = "got screening movies";
    		type = PurchaseTypes.TICKET;
		}
		if(namePage.equals("ComingSoonPage")) {
			disableCards = false;
			actionType = "pull soon movies";
			moviesType = "got soon movies";
			type = PurchaseTypes.NOT_AVAILABLE;
		}
		if(namePage.equals("ViewingPackagesPage")) {
			disableCards = false;
			actionType = "get all movies from viewing packages";
			moviesType = "got all movies from viewing packages";
			type = PurchaseTypes.VIEWING_PACKAGE;
		}
		if(namePage.equals("NetworkAdministratorMoviesPage")) {
			disableCards = true;
			actionType = "pull screening movies";
			moviesType = "got screening movies";
			type = PurchaseTypes.NOT_AVAILABLE;
		}
		if(namePage.equals("NetworkAdministratorComingSoonPage")) {
			disableCards = true;
			actionType = "pull soon movies";
			moviesType = "got soon movies";
			type = PurchaseTypes.NOT_AVAILABLE;
		}
		if(namePage.equals("NetworkAdministratorViewingPackagesPage")) {
			disableCards = true;
			actionType = "get all movies from viewing packages";
			moviesType = "got all movies from viewing packages";
			type = PurchaseTypes.NOT_AVAILABLE;
		}
		if(namePage.equals("BranchManagerMainPage")) {
			disableCards = true;
			actionType = "pull screening movies";
			moviesType = "got screening movies";
			type = PurchaseTypes.NOT_AVAILABLE;
		}
		Message msg = new Message();
		msg.setAction(actionType);
		sendMessageToServer(msg);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void sendMessageToServer(Message msg) {
    	try {
    		if(!isRegistered) {
				isRegistered=false ;
			}
			AppClient.getClient().sendToServer(msg);
			setWaitingForMessageCounter(getWaitingForMessageCounter() + 1);
		} catch (IOException e) {
			setWaitingForMessageCounter(getWaitingForMessageCounter() - 1);
			e.printStackTrace();
		}	
    }
    
    @Subscribe
	public void onMessageEvent(Message msg) {
    	try {
    	if(msg.getAction().equals(moviesType)) {
    		if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}
    		Platform.runLater(()-> {
        		if(msg.getMovies() != null) {
        			movieContainer.getChildren().clear();
        			recentlyAdded = msg.getMovies();
        			moviesNumber = recentlyAdded.size();
        			currentlyDisplayedFrom = 0;
        			try {
						SetMovies(currentlyDisplayedFrom);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
    		});
    	}}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    public void setMoviesBySearchBar(ArrayList<Movie> movies) {
    	try {
    	movieContainer.getChildren().clear();
		recentlyAdded = movies;
		moviesNumber = recentlyAdded.size();
		currentlyDisplayedFrom = 0;
		SetMovies(currentlyDisplayedFrom);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }

	public void SetMovies(int displayFrom) throws Exception {
    	int index;
		if(moviesNumber < NUM_ROWS * NUM_COLS) 
			index = 0;
		else
			index = displayFrom;
		try {
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLS; j++) {
					if(index > moviesNumber - 1) {
						if(moviesNumber < NUM_ROWS * NUM_COLS) 
							return;
						index -= moviesNumber;
					}
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("card.fxml"));
					Button cardBox = fxmlLoader.load();
					CardController cardController = fxmlLoader.getController();
					cardController.SetData(recentlyAdded.get(index), disableCards, type);
					movieContainer.add(cardBox, j, i);
					index++;
               }
            }
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

    @FXML
    void loadMoreMovies() throws Exception{
    	if(moviesNumber < NUM_ROWS * NUM_COLS)
			return;
    	
    	int nextIndex = currentlyDisplayedFrom + NUM_ROWS * NUM_COLS;
    	if(nextIndex > moviesNumber - 1)
    		currentlyDisplayedFrom = nextIndex - moviesNumber;
    	else
    		currentlyDisplayedFrom = nextIndex;
    	SetMovies(currentlyDisplayedFrom);
    }

	public int getWaitingForMessageCounter() {
		return waitingForMessageCounter;
	}

	public void setWaitingForMessageCounter(int waitingForMessageCounter) {
		this.waitingForMessageCounter = waitingForMessageCounter;
	}
}
