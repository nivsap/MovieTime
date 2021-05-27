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
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
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
	private List<Screening> screenings;
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
		System.out.println("initializing UpdateMoviesPage");
		EventBus.getDefault().register(this);
		PullScreenings();
		
		
		  
		 
		
	}
	
	
	private void PullScreenings() {
		Message msg= new Message();
		msg.setAction("get all screenings");
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
	 String temp;
		try {
			for(Screening screening : screenings) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("ScreeningCard.fxml"));
				HBox cardBox = fxmlLoader.load();				
				ScreeningCardController ctrl = fxmlLoader.getController();
				temp = screening.getDate_screen().toString();
				ctrl.SetData(screening.getMovie().getName(), screening.getCinema().getName(),temp.substring(0,10), temp.substring(11,16), screening.getHall().getHallId());
				screening_time_layout.getChildren().add(cardBox);
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
			String onlyDate;
			for(Screening screening : screenings) {
				if(!cb_movie.getItems().contains((screening.getMovie().getName()))){
					cb_movie.getItems().add(screening.getMovie().getName());
				}
				if(!cb_cinema.getItems().contains((screening.getCinema().getName()))){
					cb_cinema.getItems().add(screening.getCinema().getName());
				}
				if(!cb_cinema.getItems().contains((screening.getCinema().getName()))){
					cb_cinema.getItems().add(screening.getCinema().getName());
				}
				onlyDate = screening.getDate_screen().toString();
    			onlyDate = onlyDate.substring(0,10); 
    			if(!cb_date.getItems().contains(onlyDate))
    				cb_date.getItems().add(onlyDate);
			}
			
			
			cb_removal_addition.getItems().addAll("addition","removal");
			cb_time.getItems().addAll("00:00","00:30","01:00","01:30","02:00","02:30","03:00", "03:30",
					"04:00","04:30","05:00","05:30","06:00","06:30","07:00", "07:30",
					"08:00","08:30","09:00","09:30","10:00","10:30","11:00", "11:30",
					"12:00","12:30","13:00","13:30","14:00","14:30","15:00", "15:30",
					"16:00","16:30","17:00","17:30","18:00","18:30","19:00", "19:30",
					"20:00","20:30","21:00","21:30","22:00","22:30","23:00", "23:30");
			
			
			InitPage();
		
	}
	
	@Subscribe
	public void onMessageEvent(Message msg) throws IOException {
		
    		if(msg.getAction().equals("got all screenings")) {
    			
    			Platform.runLater(()-> {
    				screenings = msg.getScreeningArrayList();
    				SetData();
    			});
    		}
    		if(msg.getAction().equals("updated movie time")) {
    			
    			Platform.runLater(()-> {
    				try {
						App.setContent("UpdateMoviesPage");
						App.setWindowTitle( "Update Movie Time");
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
	
		
	
	
	/*
	 * @FXML private void OnComboBoxEvent() { ArratList<Movie> currentMovie =
	 * allMovies;
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
	
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

