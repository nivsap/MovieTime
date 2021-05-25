package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MovieInfoPageController {
    private Movie currentlyDisplayed;  
    private ArrayList<Cinema> cinemas;
    private ArrayList<Screening> screenings;
    private int purchaseType;

    @FXML
    private ImageView movieLargeImageSrc;

    @FXML
    private ImageView movieImageSrc;

    @FXML
    private Label moviePopularity;

    @FXML
    private Label movieName;

    @FXML
    private Label movieGenre;

    @FXML
    private Label movieDescription;

    @FXML
    private Label movieDuration;

    @FXML
    private Label movieLaunchDate;

    @FXML
    private Label movieMainActors;

    @FXML
    private Label movieNameSecond;

    @FXML
    private Label movieGenreSecond;
    
    @FXML
    private AnchorPane orderTicketsContainer;

    @FXML
    private ComboBox<String> cinemaCombo;

    @FXML
    private ComboBox<String> dateCombo;

    @FXML
    private ComboBox<String> timeCombo;

    @FXML
    private ComboBox<String> LanguageCombo;

    @FXML
    private Button orderTicketBtn;
    
    void InitPageInfo(Movie movie) {
    	EventBus.getDefault().register(this);
    	movieDescription.setWrapText(true);
    	currentlyDisplayed = movie;
    	movieName.setText(movie.getName());
    	movieDescription.setText(movie.getDescription());
    	movieDuration.setText(movie.getDuration());
    	movieGenre.setText(movie.getGenre());
    	movieLaunchDate.setText(movie.getLaunchDate().toString());
    	movieMainActors.setText(movie.getMainActors());
    	moviePopularity.setText(movie.getPopular().toString());
    	movieNameSecond.setText(movie.getName());
    	movieGenreSecond.setText(movie.getGenre());
    	Image image = new  Image(getClass().getResourceAsStream("images/MoviesPosters/" + movie.getImageSrc()));
    	Image largeImage = new  Image(getClass().getResourceAsStream("images/MoviesPosters/LargeImages/" + movie.getLargeImageSrc()));
    	movieLargeImageSrc.setImage(largeImage);
    	movieImageSrc.setImage(image);
    	getCinemas(movie.getId());
    	cinemaCombo.getItems().clear();
    	dateCombo.getItems().clear();
    	timeCombo.getItems().clear();
    }
    
    private void getCinemas(int id) {
    	Message msg = new Message();
    	msg.setAction("cinema contained movies");
    	msg.setMovieId(id);
    	try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setPurchaseType(int type) {
    	this.purchaseType = type;
    }
    
    public int getPurchaseType() {
    	return this.purchaseType;
    }
    
    @Subscribe 
    public void onMessageEvent(Message msg){
    	if(msg.getAction().equals("cinema contained movies done")) {
    		Platform.runLater(()-> {
	    		cinemaCombo.getItems().clear();
	    		cinemas = msg.getCinemasArrayList();
	    		for(Cinema cinema : msg.getCinemasArrayList()) {	
	    			if(!cinemaCombo.getItems().contains(cinema.getName()));
	    				cinemaCombo.getItems().add(cinema.getName());
	    		}
    		});
    		
    	}    	
    	
    	if(msg.getAction().equals("screening for movie done")) {
    		Platform.runLater(()-> {
    	    	String onlyDate;
	    		screenings = msg.getScreeningArrayList();
	    		dateCombo.getItems().clear();
	    		for(Screening screening : screenings) {
	    			onlyDate = screening.getDate_screen().toString();
	    			onlyDate = onlyDate.substring(0,10); 
	    			if(!dateCombo.getItems().contains(onlyDate))
	    				dateCombo.getItems().add(onlyDate);
	    		}
    		});	
    	}	
    }
    
    @FXML
    private void cinemaChosen() {
    	int cinemaId = -1;
    	if(cinemaCombo.getValue() == null) {
    		return;
    	}
    	if(cinemaCombo.getValue().isEmpty()) {
    		return;
    	}
    		
    	for(Cinema cinema : cinemas) {
    		if(cinema.getName().equals(cinemaCombo.getValue())) {
    			cinemaId = cinema.getId();
    			break;
    		}
    	}
    	
    	Message msg = new Message();
    	msg.setAction("screening for movie");
    	msg.setCinemaId(cinemaId);
    	msg.setMovieId(currentlyDisplayed.getId());
    	try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void dateChosen() {
    	String onlyTime;
    	if(dateCombo.getValue() == null) {
    		return;
    	}
    	if(dateCombo.getValue().isEmpty()) {
    		return;
    	}
    	for(Screening screening : screenings) {
    		onlyTime = screening.getDate_screen().toString();
    		onlyTime = onlyTime.substring(11,16);
    		timeCombo.getItems().add(onlyTime);
    	}
    }
    
    @FXML
    void ChooseSeats(ActionEvent event) throws IOException {
    	try {
    		if(cinemaCombo.getValue().isEmpty() || dateCombo.getValue().isEmpty() || timeCombo.getValue().isEmpty()) {
    		
    		JOptionPane.showMessageDialog(null, "You must fill all the fields");
    		}
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "You must fill all the fields");
    		return;
    	}
    
    	Screening screeningChosen = null;
    	String LDT;
    	App.setWindowTitle(PageTitles.OrderTicketsPage);
    	OrderTicketsPageController controller = (OrderTicketsPageController) App.setContent("OrderTicketsPage");
    	for (Screening screening : screenings) {
    		if(screening.getCinema().getName().equals(cinemaCombo.getValue())) {
    			LDT = screening.getDate_screen().toString();
    			if(LDT.substring(0, 10).equals(dateCombo.getValue()) && LDT.substring(11, 16).equals(timeCombo.getValue())) {
    				screeningChosen = screening;
    				break;
    			}
    			
    		}
    	}
    	if(screeningChosen == null) {
    		System.out.println("Error in movieInfoPage, screeningChosen is null!!");
    	}
    	controller.setPurchaseInfo(purchaseType, screeningChosen);
    	controller.loadMovieInfo();
    	controller.loadHallMap();
    }

}
