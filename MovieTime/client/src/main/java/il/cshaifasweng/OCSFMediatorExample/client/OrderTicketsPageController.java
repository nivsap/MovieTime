package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class OrderTicketsPageController {
	private Movie movie;
	private HallMapController hallMapController;
	private int purchaseType;
	private Screening screeningChosen;
	private VBox hallMap;
	private boolean isTavSagol;
	private int numOfSeats;
	private ArrayList<Pair<Integer,Integer>> seatsChosen = new ArrayList<Pair<Integer, Integer>>();
	
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
    private VBox hallMapContainer;
    
    @FXML
    private Label movieNameLabel;

    @FXML
    private Label cinemaLabel;

    @FXML
    private Label hallNumberLabel;

    @FXML
    private Label screeningDateLabel;

    @FXML
    private Label screeningTimeLabel;
    
    @FXML
    private Button orderTicketsBtn;
    
	private boolean isRegistered = false;

    
    public OrderTicketsPageController() {
    	hallMapContainer = new VBox();
    }
    public void setPurchaseInfo(int type, Screening screening, boolean isTavSagol, int numOfSeats, double limit , int taken) {
    	try {
    	setPurchaseType(type);
    	screeningChosen = screening;
    	this.isTavSagol = isTavSagol;
    	this.setNumOfSeats(numOfSeats);
    	ArrayList<Pair<Integer,Integer>> tavSeats = new ArrayList<Pair<Integer, Integer>>();
    	if(isTavSagol) {
    		if(taken + numOfSeats <= limit) {
    	    	for(int i = 0 ; i < screening.getHall().getRows(); i++) {
    	    		for(int j = 0 ; j < screening.getHall().getCols() ; j++) {
    	    			if(screening.getSeats()[i][j] == 0) {
    	    				screening.getSeats()[i][j] = 2;
    	    				tavSeats.add(new Pair<Integer,Integer>(i,j));
    	    				if(tavSeats.size() == numOfSeats) {
    	    					break;
    	    				}
    	    			}
    	    		}
    	    		if(tavSeats.size() == numOfSeats) {
    					break;
    				}
    	    	}
    	    }
    	}} catch (Exception e) {
			e.printStackTrace();
		}

    }
    
    public void loadMovieInfo() {
    	movie = screeningChosen.getMovie();
    	movieName.setText(movie.getName());
    	movieGenre.setText(movie.getGenre());
    	moviePopularity.setText(movie.getRate().toString());
    	movieImageSrc.setImage(movie.getImage());
    	movieLargeImageSrc.setImage(movie.getLargeImage());
    }
    
    public void loadScreeningInfo() {
    	movieNameLabel.setText(movie.getName());
        cinemaLabel.setText(screeningChosen.getCinema().getName());
        hallNumberLabel.setText(String.valueOf(screeningChosen.getHall().getHallId()));
        screeningDateLabel.setText(screeningChosen.getDate().toString());
        screeningTimeLabel.setText(screeningChosen.getTime().toString());
    }
    
    public void loadHallMap() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("HallMap.fxml"));
		hallMap = fxmlLoader.load();
		hallMapController = fxmlLoader.getController();
		hallMapController.setTavSagol(isTavSagol);
		hallMapController.setMap(screeningChosen.getSeats(), screeningChosen.getHall());
		hallMapContainer.getChildren().add(hallMap);
		
    }
    
   
    @FXML
    void orderTickets() throws IOException {
    	hallMapController.getMap(screeningChosen);
    	Hall hall = screeningChosen.getHall();
    	for(int i = 0 ; i < hall.getRows() ; i++) {
    		for(int j = 0 ; j <  hall.getCols() ; j++) {
    			Pair<Integer,Integer> pair = new Pair<Integer,Integer>(i,j);
        		if(screeningChosen.getSeats()[i][j] == 2 ) {
        			seatsChosen.add(pair);
        			//screeningChosen.getSeats()[i][j] = 1;
        		}
        	}
    	}
    	if(seatsChosen.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Please choose a Seat");
    		return;
    	}else {
    		if(!isRegistered) {
    			EventBus.getDefault().register(this);
    			isRegistered = true;
    		}
    		Message msg  = new Message();
    		msg.setAction("picking chair");
    		msg.setScreening(screeningChosen);
    		AppClient.getClient().sendToServer(msg);
    	}
    	
    	
    	
    	// send screening (or hall technically) to server to update
    	
    }
    
    
    @Subscribe
    public void onMessageEvene(Message msg)throws Exception{

    	if(msg.getAction().equals("picking seats success")) {
    		if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}
    		Platform.runLater(() ->{;
    			orderTicketsSuccess();
    		});
    	}
    	if(msg.getAction().equals("picking seats error")) {
    		if(isRegistered) {
				EventBus.getDefault().unregister(this);
				isRegistered = false;
			}
    		JOptionPane.showMessageDialog(null, msg.getError());
    		Platform.runLater(()->{
				screeningChosen = msg.getScreening();
				try {
					hallMapContainer.getChildren().clear();
					loadHallMap();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
    	}
    	
    	
    }
    
    private void orderTicketsSuccess() {
    	App.setWindowTitle(PageTitles.OrderTicketsPage);
    	PaymentPageController controller;
		try {
			controller = (PaymentPageController) App.setContent("PaymentPage");
			controller.setInfoForPage(PurchaseTypes.TICKET, screeningChosen, seatsChosen, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	public int getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(int purchaseType) {
		this.purchaseType = purchaseType;
	}
	public int getNumOfSeats() {
		return numOfSeats;
	}
	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

}