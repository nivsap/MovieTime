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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DeleteMoviePageController {
	int NUM_ROWS = 2, NUM_COLS = 3, currentlyDisplayedFrom = 0, moviesNumber = 0;
	private List<Movie> recentlyAdded;


    @FXML
    private TextField search;

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
    void initialize() {
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cardsContainer != null : "fx:id=\"cardsContainer\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert movieContainer != null : "fx:id=\"movieContainer\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cell1 != null : "fx:id=\"cell1\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cell2 != null : "fx:id=\"cell2\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cell3 != null : "fx:id=\"cell3\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cell4 != null : "fx:id=\"cell4\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cell5 != null : "fx:id=\"cell5\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert cell6 != null : "fx:id=\"cell6\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        assert loadMoreBtn != null : "fx:id=\"loadMoreBtn\" was not injected: check your FXML file 'DeleteMoviePage.fxml'.";
        sendMessageToServer("get all movies for delete page", null);
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
					deleteCardController.setData(recentlyAdded.get(index), this, "DeleteMovie");
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
    	if(moviesNumber < NUM_ROWS * NUM_COLS)
			return;
    	
    	int nextIndex = currentlyDisplayedFrom + NUM_ROWS * NUM_COLS;
    	if(nextIndex > moviesNumber - 1)
    		currentlyDisplayedFrom = nextIndex - moviesNumber;
    	else
    		currentlyDisplayedFrom = nextIndex;
    	
    	setMovies(currentlyDisplayedFrom);
    }
    
    public void deleteMovie(Movie movie) {
    	recentlyAdded.remove(movie);
    	--moviesNumber;
    	sendMessageToServer("delete movie", movie);
    }
    
    public void sendMessageToServer(String actionType, Movie movie) {
    	try {
    		EventBus.getDefault().register(this);
			Message msg = new Message();
			if(actionType.equals("delete movie")) {
				msg.setMovie(movie); 
			}
			msg.setAction(actionType);
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			System.out.println("failed to send msg to server from DeleteMoviePageController");
			if(actionType.equals("delete movie")) {
				recentlyAdded.add(movie);
		    	++moviesNumber;
			}
			e.printStackTrace();
		}	
    }
    
    @Subscribe
	public void onMessageEvent(Message msg) {
    	System.out.println("got message in DeleteMoviePageController");
		System.out.println(msg.getAction());
    	if(msg.getAction().equals("got all movies for delete page")) {
    		EventBus.getDefault().unregister(this);
    		Platform.runLater(()-> {
    			movieContainer.getChildren().clear();
    			recentlyAdded = msg.getMovies();
    			moviesNumber = recentlyAdded.size();
    			currentlyDisplayedFrom = 0;
    			setMovies(currentlyDisplayedFrom);
    		});
    	}
    	if(msg.getAction().equals("deleted movie")) {
    		EventBus.getDefault().unregister(this);
    		Platform.runLater(()-> {
    			movieContainer.getChildren().clear();
    			currentlyDisplayedFrom = 0;
    			setMovies(currentlyDisplayedFrom);
    		});
    	}
    }
}
