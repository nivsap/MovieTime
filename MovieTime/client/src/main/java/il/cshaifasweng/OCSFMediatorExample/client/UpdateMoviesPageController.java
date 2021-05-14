package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdateMoviesPageController{

	
	private List<Movie> allMovies;
	private String[] time;

    @FXML
    private ComboBox<String> cb_movie;

    @FXML
    private ComboBox<String> cb_time;

    @FXML
    private ComboBox<String> cb_date;

    @FXML
    private ComboBox<String> cb_cinema;

    @FXML
    private ComboBox<String> cb_removal_addition;

    @FXML
    private VBox screening_time_layout;
	
    @FXML
    private Button btn_main_page;
    
    @FXML
    private Button btn_update;
	
	@FXML
	public void initialize() {
		System.out.println("initializing UpdateMoviePage");
		EventBus.getDefault().register(this);
		Message msg= new Message();
		msg.setAction("pull movies");
		

		
		try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("faile to send msg to server from recentlyAdded");
			e.printStackTrace();
		}
		
		
		  
		 
		
	}
	
	 
	public void InitPage(){		
	 System.out.println("in here");
	 
		try {
			for(Movie movie : allMovies) {
				for(String time : movie.getMovieBeginingTime()) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("ScreeningCard.fxml"));
					HBox cardBox = fxmlLoader.load();				
					ScreeningCardController ctrl = fxmlLoader.getController();
					ctrl.SetData(movie.getName(), "Haifa", "14.05.21", time);
					screening_time_layout.getChildren().add(cardBox);
				}
				 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
	public void SetData() {

			cb_time.getItems().clear();
			cb_movie.getItems().clear();
			cb_cinema.getItems().clear();
			cb_date.getItems().clear();
			cb_removal_addition.getItems().clear();
			for(Movie movie : allMovies) {
				cb_movie.getItems().add(movie.getName());
			}
			
			cb_cinema.getItems().addAll("Haifa", "Kiryat Ata" , "Kiryat Ono", "Makom Shel Teymanim");
			cb_removal_addition.getItems().addAll("addition","removal");
			cb_time.getItems().addAll("00:00","00:30","01:00","01:30","02:00","02:30","03:00");
			cb_date.getItems().add("14.05.21");
			InitPage();
		
	}
	
	@Subscribe
	public void onMessageEvent(Message msg) {
		
    		if(msg.getAction().equals("got movies")) {
    			
    			Platform.runLater(()-> {
    				allMovies = msg.getMovies();
    				SetData();
    			});
    		}

	}
	
	

@FXML
private void UpdateMovieTime(ActionEvent event)
{
	
	boolean timeChanged = false;
	if(cb_movie.getSelectionModel().isEmpty() || 
			cb_date.getSelectionModel().isEmpty() ||
			cb_time.getSelectionModel().isEmpty() ||
			cb_cinema.getSelectionModel().isEmpty() ||
			cb_removal_addition.getSelectionModel().isEmpty()) {
		//do error prompt
	}else {
		Message msg = new Message();
		for(Movie movie : allMovies) {
			if(movie.getName() == cb_movie.getValue()) {
				if(cb_removal_addition.getValue().equals("addition")) {
					movie.getMovieBeginingTime().add(cb_time.getValue());
					timeChanged = true;
				}else {
					for(String time : movie.getMovieBeginingTime()) {
						if(cb_time.getValue().equals(time)) {
							movie.getMovieBeginingTime().remove(time);
							timeChanged = true;
						}
					}
				}
				if(timeChanged) {
					msg.setMovie(movie);
					msg.setAction("update movie time");
					
					try {
						AppClient.getClient().sendToServer(msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("faile to send msg to server from UpdateMovieController");
						e.printStackTrace();
					}
					
				}else {
					//error prompt, time does not exist, and therefore cannot be removed
				}
			}
		}
		
		
	}
	
}



@FXML
private void MainPageButton(ActionEvent event)
{
	FXMLLoader Loader = new FXMLLoader();
	Loader.setLocation(getClass().getResource("MainPage.fxml"));
	try {
		Loader.load();
	} catch(IOException ex) {
		ex.printStackTrace();
	}
	
	EventBus.getDefault().unregister(this);
	
	
	Stage stage = (Stage) cb_movie.getScene().getWindow();
	Parent p =Loader.getRoot();
	stage.setScene(new Scene(p));
	
	stage.show();
	
	
	
	
}

	/*
	 * public void SetMovieInfo(Integer id, String name) { this.movieId = id;
	 * this.movieName = name; }
	 */
	
}

