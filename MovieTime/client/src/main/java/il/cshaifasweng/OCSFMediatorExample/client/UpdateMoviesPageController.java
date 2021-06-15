package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UpdateMoviesPageController{

	
	private List<Movie> allMovies;
	private List<Screening> screenings;
	List<Screening> filteredScreenings;
	private String[] time;
	
	@FXML
    private DatePicker dateCard;
	
    @FXML
    private ComboBox<String> cb_hall;
    
    @FXML
    private ComboBox<String> cb_movie;

    @FXML
    private ComboBox<String> cb_time;


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
		PullScreenings();
		
		
	}
	
	@FXML
	void onChoiceCB() {
		screening_time_layout.getChildren().clear();
		filteredScreenings = new ArrayList<Screening>(screenings);
		if(cb_cinema.getValue() != null && !cb_cinema.getValue().isBlank()) {
			
			Iterator<Screening> iter = filteredScreenings.iterator();
			while (iter.hasNext()) {
			  Screening s = iter.next();
			  if (!s.getCinema().getName().equals(cb_cinema.getValue()))
				  iter.remove();
			}
		}
		
		if(cb_hall.getValue() != null && !cb_hall.getValue().isBlank()) {
			
			Iterator<Screening> iter = filteredScreenings.iterator();
			while (iter.hasNext()) {
			  Screening s = iter.next();
			  if (!(s.getHall().getHallId() == Integer.parseInt(cb_hall.getValue())))
				  iter.remove();
			}
		}
		
		if(cb_movie.getValue() != null && !cb_movie.getValue().isBlank()) {
			Iterator<Screening> iter = filteredScreenings.iterator();
			while (iter.hasNext()) {
			  Screening s = iter.next();
			  if (!s.getMovie().getName().equals(cb_movie.getValue()))
				  iter.remove();
			}
		}
		
		if(dateCard.getValue() != null) {
			System.out.println(dateCard.getValue());
			Iterator<Screening> iter = filteredScreenings.iterator();
			while (iter.hasNext()) {
			  Screening s = iter.next();
			  if (!s.getDate().toString().equals(dateCard.getValue().toString()))
				  iter.remove();
			}
		}
			
		if(cb_time.getValue() != null && !cb_time.getValue().isBlank()) {
			Iterator<Screening> iter = filteredScreenings.iterator();
			while (iter.hasNext()) {
			  Screening s = iter.next();
			  if (!s.getTime().toString().equals(cb_time.getValue()))
				  iter.remove();
			}
		}
			
		try {
			for(Screening screening : filteredScreenings) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("ScreeningCard.fxml"));
				HBox cardBox = fxmlLoader.load();				
				ScreeningCardController ctrl = fxmlLoader.getController();
				ctrl.SetData(screening.getMovie().getName(), screening.getCinema().getName(), screening.getDate().toString(), screening.getTime().toString(), screening.getHall().getHallId());
				screening_time_layout.getChildren().add(cardBox);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private void PullScreenings() {
		EventBus.getDefault().register(this);
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
		try {
			for(Screening screening : screenings) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("ScreeningCard.fxml"));
				HBox cardBox = fxmlLoader.load();				
				ScreeningCardController ctrl = fxmlLoader.getController();
				ctrl.SetData(screening.getMovie().getName(), screening.getCinema().getName(), screening.getDate().toString(), screening.getTime().toString(), screening.getHall().getHallId());
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
			
			cb_removal_addition.getItems().clear();
			cb_hall.getItems().clear();
			
			
			
			
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
				if(!cb_hall.getItems().contains((Integer.toString(screening.getHall().getHallId())))){
					cb_hall.getItems().add((Integer.toString(screening.getHall().getHallId())));
				}
				
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
		System.out.println("got msg in UpdateMoviesPageController");
    		if(msg.getAction().equals("got all screenings")) {
    			EventBus.getDefault().unregister(this);
    			Platform.runLater(()-> {
    				screenings = msg.getScreenings();
    				SetData();
    			});
    		}
    		if(msg.getAction().equals("updated movie time")) {
    			EventBus.getDefault().unregister(this);
    			Platform.runLater(()-> {
    				screenings = msg.getScreenings();
					onChoiceCB();

    			});
    		}
    		if(msg.getAction().equals("update movie time error")) {
    			EventBus.getDefault().unregister(this);
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
		if(cb_movie.getValue().isEmpty() ||  
				dateCard.getValue() == null ||
				cb_time.getValue().isEmpty() ||
				cb_cinema.getValue().isEmpty() ||
				cb_removal_addition.getValue().isEmpty()
				||cb_hall.getValue().isEmpty() ) {
			
			JOptionPane.showMessageDialog(null, "You must fill all the fields");
		}else {
			
			
				if(cb_removal_addition.getValue().equals("addition") && filteredScreenings.size() == 1) {
					JOptionPane.showMessageDialog(null, "screening already exists!");
				}
				EventBus.getDefault().register(this);
				Message msg = new Message();
				if(cb_removal_addition.getValue().equals("removal") && filteredScreenings.size() == 1) {
					msg.setScreening(filteredScreenings.get(0));
				}
				msg.setAction("update movie time");
				msg.setMovieName(cb_movie.getValue());
				msg.setDBAction(cb_removal_addition.getValue());
				msg.setCinemaName(cb_cinema.getValue());
				msg.setHallId(Integer.parseInt(cb_hall.getValue()));

				
				String onlyDate = dateCard.getValue().toString();
				String onlyTime = cb_time.getValue().toString();
				int year = Integer.parseInt(onlyDate.substring(0,4));
				int month = Integer.parseInt(onlyDate.substring(5,7));
				int day = Integer.parseInt(onlyDate.substring(8,10));
				int hour = Integer.parseInt(onlyTime.substring(0,2));
				int minutes = Integer.parseInt(onlyTime.substring(3,5));
				
				System.out.println(year);
				System.out.println(month);
				System.out.println(day);
				System.out.println(hour);
				System.out.println(minutes);
				msg.setScreeningDate(LocalDate.of(year,month,day).atTime(hour,minutes));

			 
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

