package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
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

public class DeleteViewingPackagePageController {
	int NUM_ROWS = 2, NUM_COLS = 3, currentlyDisplayedFrom = 0, moviesNumber = 0;
	private List<Movie> recentlyAdded;
	private boolean isRegistered = false;

    @FXML
    private VBox cardsContainer;

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

    @FXML
    void initialize() throws Exception{
        assert cardsContainer != null : "fx:id=\"cardsContainer\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert movieContainer != null : "fx:id=\"movieContainer\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cell1 != null : "fx:id=\"cell1\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cell2 != null : "fx:id=\"cell2\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cell3 != null : "fx:id=\"cell3\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cell4 != null : "fx:id=\"cell4\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cell5 != null : "fx:id=\"cell5\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cell6 != null : "fx:id=\"cell6\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert loadMoreBtn != null : "fx:id=\"loadMoreBtn\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        sendMessageToServer("get all movies from viewing packages", null);
    }

    public void setMovies(int displayFrom) {
		movieContainer.getChildren().clear();
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
					fxmlLoader.setLocation(getClass().getResource("DeleteCard.fxml"));
					VBox card = fxmlLoader.load();
					DeleteCardController deleteCardController = fxmlLoader.getController();
					deleteCardController.setData(recentlyAdded.get(index), this, "DeleteViewingPackage");
					movieContainer.add(card, j, i);
					index++;
               }
            }
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @FXML
    void loadMoreMovies() {
    	try {
    	if(moviesNumber < NUM_ROWS * NUM_COLS)
			return;
    	
    	int nextIndex = currentlyDisplayedFrom + NUM_ROWS * NUM_COLS;
    	if(nextIndex > moviesNumber - 1)
    		currentlyDisplayedFrom = nextIndex - moviesNumber;
    	else
    		currentlyDisplayedFrom = nextIndex;
    	
    	setMovies(currentlyDisplayedFrom);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void deleteViewingPackage(Movie movie) {
    	try {
    	recentlyAdded.remove(movie);
    	--moviesNumber;
    	sendMessageToServer("delete a viewing package", movie);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void sendMessageToServer(String actionType, Movie movie) {
    	try {
    		if(!isRegistered) {
    			EventBus.getDefault().register(this);
    			isRegistered = true;
    		}
    		Message msg = new Message();
			if(actionType.equals("delete a viewing package")) {
				msg.setMovie(movie); 
			}
			msg.setAction(actionType);
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {

			if(actionType.equals("delete a viewing package")) {
				recentlyAdded.add(movie);
		    	++moviesNumber;
			}
			e.printStackTrace();
		}	
    }

    @Subscribe
	public void onMessageEvent(Message msg) {
try {
    	if(msg.getAction().equals("got all movies from viewing packages")) {
    		if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}
    		Platform.runLater(()-> {
    			movieContainer.getChildren().clear();
    			recentlyAdded = msg.getMovies();
    			moviesNumber = recentlyAdded.size();
    			currentlyDisplayedFrom = 0;
    			setMovies(currentlyDisplayedFrom);
    		});
    	}
    	if(msg.getAction().equals("deleted a viewing package")) {
    		if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}
    		Platform.runLater(()-> {
    			movieContainer.getChildren().clear();
    			currentlyDisplayedFrom = 0;
    			setMovies(currentlyDisplayedFrom);
    		});
    	}} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
