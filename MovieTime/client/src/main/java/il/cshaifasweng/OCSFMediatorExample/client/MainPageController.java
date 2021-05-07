package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.IOException;
import java.net.URL;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;

public class MainPageController implements Initializable {
	
    @FXML
    private HBox card_layout_new_movies;
    @FXML
    private GridPane movie_Container;
    private List<Movie> recentlyAdded;
   
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	//EventBus.getDefault().register(this);
		recentlyAdded = new ArrayList<>(recentlyAdded());
		try {
			for(int i = 0 ; i < recentlyAdded.size() ; i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("card.fxml"));
				HBox cardBox = fxmlLoader.load();
				CardController cardController = fxmlLoader.getController();
				cardController.SetData(recentlyAdded.get(i));
				
				card_layout_new_movies.getChildren().add(cardBox);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

 
    private List<Movie> recentlyAdded(){
    	
    	List<Movie> ls = new ArrayList<>();
    	Movie movie = new Movie("300","x",1,"an action packed war movie\r\n"
    			+ "like no other");
    	ls.add(movie);
    	return ls;
    	
    }
}
