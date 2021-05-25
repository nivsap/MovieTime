package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CardContainerController {

	int NUM_ROWS = 2, NUM_COLS = 3, currentlyDisplayedFrom = 0, moviesNumber = 0;

    @FXML
    private GridPane movieContainer;

    @FXML
    private VBox cell1;

    @FXML
    private VBox cell2;

    @FXML
    private VBox cell3;

    @FXML
    private VBox cell4;

    @FXML
    private VBox cell5;

    @FXML
    private VBox cell6;

    @FXML
    private Button loadMoreBtn;
    
	private List<Movie> recentlyAdded;


    public void initialize(URL location, ResourceBundle resources) {
    }
    
    public void sendMsgToServer(String namePage) {
    	EventBus.getDefault().register(this);

    	System.out.println("trying to send pull screening movies from card container  controller");
		String actionType = null;
    	if(namePage.equals("MainPage")) {
    		actionType="pull screening movies";
    		System.out.println("pull screening movies");
		}
		if(namePage.equals("ComingSoonPage")) {
			actionType="pull soon movies";
		}
		if(namePage.equals("ViewingPackagesPage")) {
			actionType="pull movies from home";
		}
		try {
			Message msg = new Message();
			msg.setAction(actionType);
			System.out.println("trying to send msg to server");
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to send msg to server from recentlyAdded");
			e.printStackTrace();
		}	
    }
    
    
    @Subscribe
	public void onMessageEvent(Message msg) {
		System.out.println("reveived message!! in card container");
		System.out.println(msg.getAction());
    	if(msg.getAction().equals("got movies from home")||msg.getAction().equals("got screening movies")||msg.getAction().equals("got soon movies")) {
    		Platform.runLater(()-> {
    			recentlyAdded = msg.getMovies();
    			moviesNumber = recentlyAdded.size();
    			currentlyDisplayedFrom = 0;
    			SetMovies(currentlyDisplayedFrom);
    		});
    	}
    }

	public void SetMovies(int displayFrom) {

    	int index;
		if(moviesNumber < NUM_ROWS * NUM_COLS) 
			index = 0;
		else
			index = displayFrom;
		try {
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLS; j++) {
					if(index > moviesNumber - 1) {
						if(moviesNumber < NUM_ROWS * NUM_COLS) 
							return;
						index -= moviesNumber;
					}

					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("card.fxml"));
					Button cardBox = fxmlLoader.load();
					CardController cardController = fxmlLoader.getController();
					cardController.SetData(recentlyAdded.get(index), "ticket");
					movieContainer.add(cardBox, j, i);
					index++;
               }
            }
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

    @FXML
    void loadMoreMovies() {
    	if(moviesNumber < NUM_ROWS * NUM_COLS)
			return;
    	
    	int nextIndex = currentlyDisplayedFrom + NUM_ROWS * NUM_COLS;
    	if(nextIndex > moviesNumber - 1)
    		currentlyDisplayedFrom = nextIndex - moviesNumber;
    	else
    		currentlyDisplayedFrom = nextIndex;
    	SetMovies(currentlyDisplayedFrom);
    }

}
