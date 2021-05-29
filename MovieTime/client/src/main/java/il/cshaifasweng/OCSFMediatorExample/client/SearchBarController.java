package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SearchBarController {

    @FXML
    private VBox SearchBarContainer;

    @FXML
    private TextField mainSearchBar;

    @FXML
    private ComboBox<String> genreComboBox;

    @FXML
    private ComboBox<String> theaterComboBox;

    @FXML
    private ComboBox<String> rateComboBox;
    
    
    
    @FXML
	public void initialize() {
		//EventBus.getDefault().register(this);
    	genreComboBox.getItems().clear();
    	theaterComboBox.getItems().clear();
    	rateComboBox.getItems().clear();
    	theaterComboBox.getItems().addAll("Haifa","Tel-Aviv");
    	rateComboBox.getItems().addAll("5.0","4.5","4.0","3.5","3.0","2.5");
    	
    	
    	
	}
    
	/*
	 * @Subscribe public void onMessageEvent(Message msg) {
	 * 
	 * 
	 * if(msg.getAction().equals("got screening movies")) {
	 * EventBus.getDefault().unregister(this); Platform.runLater(()-> {
	 * ArrayList<Movie> movies = msg.getMovies();
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }); } }
	 */
    
    
    
    @FXML
    void onComboBoxEvent() {
    	Message msg = new Message();
    	msg.setAction("search bar update");
    	msg.setGenere(genreComboBox.getValue());
    	msg.setTheater(theaterComboBox.getValue());
    	msg.setRate(rateComboBox.getValue());
    	msg.setSearch(mainSearchBar.getText());
    	try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    
    
    
    
    
    
    
    

}
