package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MovieInfoPageController {
    private Movie currentlyDisplayed;  
    private Screening screeningChosen;
    private ArrayList<Cinema> cinemas;
    private ArrayList<Screening> screenings;
    private ArrayList<Screening> filteredScreenings;
    private int purchaseType;
    private boolean isTavSagol = false;
    private int tavSagolLimit;
    private double seatsLimit;
    private int seatsTaken;

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
    private Label movieProducers;

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
    private ComboBox<String> numberOfSeatsCombo;

    @FXML
    private Button orderTicketBtn;
    
    void InitPageInfo(Movie movie) {
    	currentlyDisplayed = movie;
    	purchaseType = PurchaseTypes.TICKET;
    	movieImageSrc.setImage(movie.getImage());
    	movieLargeImageSrc.setImage(movie.getLargeImage());
    	movieName.setText(movie.getName());
    	movieGenre.setText(movie.getGenre());
    	moviePopularity.setText(movie.getPopular().toString());
    	movieNameSecond.setText(movie.getName());
    	movieGenreSecond.setText(movie.getGenre());
    	movieDescription.setText(movie.getDescription());
    	movieDescription.setWrapText(true);
    	movieProducers.setText(movie.getProducersMovie());
    	movieMainActors.setText(movie.getMainActors());
    	movieDuration.setText(movie.getDuration());
    	movieLaunchDate.setText(movie.getLaunchDate().toString());
    	getCinemas(movie.getId());
    	cinemaCombo.getItems().clear();
    	dateCombo.getItems().clear();
    	timeCombo.getItems().clear();
    	numberOfSeatsCombo.getItems().clear();
    	numberOfSeatsCombo.setVisible(false);
    }
    
    private void getCinemas(int id) {
    	EventBus.getDefault().register(this);
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
    		EventBus.getDefault().unregister(this);
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
    		EventBus.getDefault().unregister(this);
    		Platform.runLater(()-> {
    	    	String onlyDate;
	    		screenings = msg.getScreeningArrayList();
	    		System.out.println("screening for movie done, arraylist size: " + screenings.size());
	    		dateCombo.getItems().clear();
	    		for(Screening screening : screenings) {
	    			onlyDate = screening.getDate_screen().toString();
	    			onlyDate = onlyDate.substring(0,10); 
	    			if(!dateCombo.getItems().contains(onlyDate))
	    				dateCombo.getItems().add(onlyDate);
	    		}
    		});	
    	}	
    	
    	
    	if(msg.getAction().equals("done check purple limit")) {
    		EventBus.getDefault().unregister(this);
    		isTavSagol = msg.getStatus();
    		System.out.println("tab saglo is" + isTavSagol);
    		tavSagolLimit = msg.getTavSagolLimit();
    		System.out.println("tav sagol limit is " + tavSagolLimit);

    		
    	}
    }
    
    

    @FXML
    private void cinemaChosen() {
    	EventBus.getDefault().register(this);
    	filteredScreenings = screenings;
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
    	EventBus.getDefault().register(this);
    	Message msg = new Message();
    	msg.setAction("check purple limit");
    	msg.setDateMovie(screenings.get(0).getDate_screen());
    	try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String onlyTime;
    	if(dateCombo.getValue() == null) {
    		return;
    	}
    	if(dateCombo.getValue().isEmpty()) {
    		return;
    	}
    	for(Screening screening : screenings) {
    		if(screening.getDate_screen().toString().substring(0,10).equals(dateCombo.getValue())){
    			onlyTime = screening.getDate_screen().toString().substring(11,16);
        		timeCombo.getItems().add(onlyTime);
    		}
    				
    	}
    	
    }
    
    
    @FXML
    void timeChosen() {
    	String LDT;
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
    	Hall hall = screeningChosen.getHall();
    	if(isTavSagol) {

    		seatsLimit = hall.getRows() * hall.getCols();
    		System.out.println("seats limit is " + seatsLimit);
    		if((double)tavSagolLimit*1.2 < seatsLimit) {
    			seatsLimit = tavSagolLimit;
    		}
    		if(seatsLimit > 0.8* (double)tavSagolLimit) {
    			seatsLimit = 0.8* (double)tavSagolLimit;
    		}
    		if(seatsLimit <= 0.8* (double)tavSagolLimit) {
    			seatsLimit = seatsLimit/2;
    		}
    		System.out.println("seats limit: " + seatsLimit);
    		seatsTaken = 0;
    		for(int i = 0 ; i < hall.getRows() ; i++) {
    			for(int j = 0 ; j < hall.getCols() ; j++) {
    				
    				if(screeningChosen.getSeats()[i][j] == 1) {
    					seatsTaken++;
    				}
    			}
    		}
    		System.out.println("seats taken:" + seatsTaken);
    		for(int i = 1 ; i + seatsTaken <= seatsLimit ; i++) {
    			numberOfSeatsCombo.getItems().add(Integer.toString(i));
    			
    		}
        	numberOfSeatsCombo.setVisible(true);
    		
    	}
    }
    
    @FXML
    void ChooseSeats(ActionEvent event) throws IOException {
    	try {
    		if(cinemaCombo.getValue().isEmpty() || dateCombo.getValue().isEmpty() || timeCombo.getValue().isEmpty()) {
    		
    			JOptionPane.showMessageDialog(null, "You must fill all the fields");
    			return;
    		}
    		if(isTavSagol && numberOfSeatsCombo.getValue().isBlank()) {
    			JOptionPane.showMessageDialog(null, "You must fill all the fields");
    		}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		return;
    	}
    
    	App.setWindowTitle(PageTitles.OrderTicketsPage);
    	OrderTicketsPageController controller = (OrderTicketsPageController) App.setContent("OrderTicketsPage");
    	
    	if(numberOfSeatsCombo.getValue() == null) {
    		numberOfSeatsCombo.setValue(Integer.toString(0));
    	}
    	controller.setPurchaseInfo(purchaseType, screeningChosen,isTavSagol, Integer.parseInt(numberOfSeatsCombo.getValue()),seatsLimit, seatsTaken);
    	controller.loadMovieInfo();
    	controller.loadScreeningInfo();
    	controller.loadHallMap();
    }

}
