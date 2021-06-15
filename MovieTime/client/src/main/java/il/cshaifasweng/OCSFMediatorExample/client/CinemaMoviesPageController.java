package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CinemaMoviesPageController {
	int NUM_ROWS = 2, NUM_COLS = 3, currentlyDisplayedFrom = 0, moviesNumber = 0;
	private List<Movie> recentlyAdded;

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
    
    public CinemaMoviesPageController() {
    	recentlyAdded = new ArrayList<Movie>();
    }

    @FXML
    void initialize() {
        assert cardsContainer != null : "fx:id=\"cardsContainer\" was not injected: check your FXML file 'CinemaMoviesPage.fxml'.";
        assert movieContainer != null : "fx:id=\"movieContainer\" was not injected: check your FXML file 'CinemaMoviesPage.fxml'.";
        assert cell1 != null : "fx:id=\"cell1\" was not injected: check your FXML file 'CinemaMoviesPage.fxml'.";
        assert cell2 != null : "fx:id=\"cell2\" was not injected: check your FXML file 'CinemaMoviesPage.fxml'.";
        assert cell3 != null : "fx:id=\"cell3\" was not injected: check your FXML file 'CinemaMoviesPage.fxml'.";
        assert cell4 != null : "fx:id=\"cell4\" was not injected: check your FXML file 'CinemaMoviesPage.fxml'.";
        assert cell5 != null : "fx:id=\"cell5\" was not injected: check your FXML file 'CinemaMoviesPage.fxml'.";
        assert cell6 != null : "fx:id=\"cell6\" was not injected: check your FXML file 'CinemaMoviesPage.fxml'.";
        assert loadMoreBtn != null : "fx:id=\"loadMoreBtn\" was not injected: check your FXML file 'CinemaMoviesPage.fxml'.";

    }
    
    public void getCinemaMovies(Cinema cinema) {
    	List<Screening> screenings = cinema.getScreenings();
    	for(Screening s: screenings) {
    		Boolean isContained = false;
    		for(Movie m: recentlyAdded) {
    			if(s.getMovie().getName().equals(m.getName())) {
    				isContained = true;
    				continue;
    			}
    		}
    		if(!isContained)
    			recentlyAdded.add(s.getMovie());
    	}
    	moviesNumber = recentlyAdded.size();
    	setMovies(0);
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
					fxmlLoader.setLocation(getClass().getResource("card.fxml"));
					Button cardBox = fxmlLoader.load();
					CardController cardController = fxmlLoader.getController();
					cardController.SetData(recentlyAdded.get(index), true, PurchaseTypes.NOT_AVAILABLE);
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
}
