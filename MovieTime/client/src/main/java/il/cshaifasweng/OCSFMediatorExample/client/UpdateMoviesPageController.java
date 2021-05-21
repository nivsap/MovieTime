package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
		PullMovies();
		
		
		  
		 
		
	}
	
	
	private void PullMovies() {
		Message msg= new Message();
		msg.setAction("pull movies");
		

		
		try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to send msg to server from updateMoviesPage");
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
					ctrl.SetData(movie.getName(), "Haifa", "20.05.21", time);
					screening_time_layout.getChildren().add(cardBox);
				}
				 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if (needUpdateMovieTime) {
			
	//	}
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
			cb_time.getItems().addAll("00:00","00:30","01:00","01:30","02:00","02:30","03:00", "03:30",
					"04:00","04:30","05:00","05:30","06:00","06:30","07:00", "07:30",
					"08:00","08:30","09:00","09:30","10:00","10:30","11:00", "11:30",
					"12:00","12:30","13:00","13:30","14:00","14:30","15:00", "15:30",
					"16:00","16:30","17:00","17:30","18:00","18:30","19:00", "19:30",
					"20:00","20:30","21:00","21:30","22:00","22:30","23:00", "23:30");
			cb_date.getItems().add("20.05.21");
			InitPage();
		
	}
	
	@Subscribe
	public void onMessageEvent(Message msg) throws IOException {
		
    		if(msg.getAction().equals("got movies")) {
    			
    			Platform.runLater(()-> {
    				allMovies = msg.getMovies();
    				SetData();
    			});
    		}
    		if(msg.getAction().equals("updated movie time")) {
    			
    			Platform.runLater(()-> {
    				try {
						App.setContent("UpdateMoviesPage", "Update Movie Time");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			});
    			
    		}
    		if(msg.getAction().equals("update movie error")) {
    			JOptionPane.showMessageDialog(null, msg.getError());
    			
    		}
    		

	}
	
		
	
	@SuppressWarnings("unlikely-arg-type")
	@FXML
	private void UpdateMovieTime(ActionEvent event)
	{
		
		boolean timeChanged = false;
		if(cb_movie.getSelectionModel().isEmpty() || 
				cb_date.getSelectionModel().isEmpty() ||
				cb_time.getSelectionModel().isEmpty() ||
				cb_cinema.getSelectionModel().isEmpty() ||
				cb_removal_addition.getSelectionModel().isEmpty()) {
			JOptionPane.showMessageDialog(null, "You must fill all the fields");
		}else {
			
			Message msg = new Message();
			msg.setAction("update movie time");
			msg.setTime(cb_time.getValue());
			msg.setMovieName(cb_movie.getValue());
			msg.setDbAction(cb_removal_addition.getValue());
			
			
			  System.out.println(cb_time.getValue());
			  System.out.println(cb_movie.getValue());
			  System.out.println(cb_removal_addition.getValue());
			 
			try {
				AppClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("failed to send msg to server from UpdateMovieController");
				e.printStackTrace();
			}
			
			
			
		}
		
		
		
	}
	
	

	
}

