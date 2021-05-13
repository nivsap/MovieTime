package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UpdateMoviesPageController{

	

    @FXML
    private ComboBox<?> cb_movie;

    @FXML
    private ComboBox<?> cb_time;

    @FXML
    private ComboBox<?> cb_date;

    @FXML
    private ComboBox<?> cb_cinema;

    @FXML
    private ComboBox<?> cb_removal_addition;

    @FXML
    private VBox screening_time_layout;
	
	
	@FXML
	public void initialize() {
		/*
		 * cb_movie.getItems().clear();
		 * 
		 * cb_movie.getItems().addAll("300","Wonder Woman");
		 * cb_movie.getSelectionModel().select("Option B");
		 */
		InitPage();
	}
	
	 
	public void InitPage(){		
	 System.out.println("in here");
		try {
			for(int i = 0 ; i < 5 ; i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("ScreeningCard.fxml"));
				HBox cardBox = fxmlLoader.load();				
				
				 screening_time_layout.getChildren().add(cardBox);
				 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
	/*
	 * public void SetMovieInfo(Integer id, String name) { this.movieId = id;
	 * this.movieName = name; }
	 */
	
}

