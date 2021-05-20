package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.IOException;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ComingSoonPageController {
	int NUM_ROWS = 2, NUM_COLS = 4, currentlyDisplayedFrom = 0, comingSoonNumber = 0;
	
    @FXML
    private GridPane comingSoonContainer;

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
    private VBox cell7;

    @FXML
    private VBox cell8;

    @FXML
    private Button loadMoreBtn;
    
    private List<Movie> recentlyLoaded;
    
    
    public ComingSoonPageController() {
		currentlyDisplayedFrom = 0;
		comingSoonNumber = 0;
	}

    @FXML
    void initialize() {
		System.out.println("initializing coming soon page");
		EventBus.getDefault().register(this);
		Message msg = new Message();
		msg.setAction("pull soon movies");
		try {
			System.out.println("trying to send msg to server");
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to send msg to server from coming soon page");
			e.printStackTrace();
		}
    }
    
    public void SetComingSoon(int displayFrom) {
    	int index;
		if(comingSoonNumber < NUM_ROWS * NUM_COLS) 
			index = 0;
		else
			index = displayFrom;
		try {
			for (int i = 0; i < NUM_ROWS; i++) {
				for (int j = 0; j < NUM_COLS; j++) {
					if(index > comingSoonNumber - 1) {
						if(comingSoonNumber < NUM_ROWS * NUM_COLS) 
							return;
						index -= comingSoonNumber;
					}
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("card.fxml"));
					Button cardBox = fxmlLoader.load();
					CardController cardController = fxmlLoader.getController();
					cardController.SetData(recentlyLoaded.get(index));
					comingSoonContainer.add(cardBox, j, i);
					index++;
	               }
	            }
			} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @Subscribe
	public void onMessageEvent(Message msg) {
		System.out.println("reveived a message!!");
		System.out.println(msg.getAction());
    	if(msg.getAction().equals("got soon movies")) {
    		Platform.runLater(()-> {
    			recentlyLoaded = msg.getMovies();
    			comingSoonNumber = recentlyLoaded.size();
    			currentlyDisplayedFrom = 0;
    			SetComingSoon(currentlyDisplayedFrom);
    		});
    	}
	}
    
    
    @FXML
    void loadMoreComingSoon(ActionEvent event) {
		if(comingSoonNumber < NUM_ROWS * NUM_COLS)
			return;
    	
    	int nextIndex = currentlyDisplayedFrom + NUM_ROWS * NUM_COLS;
    	if(nextIndex > comingSoonNumber - 1)
    		currentlyDisplayedFrom = nextIndex - comingSoonNumber;
    	else
    		currentlyDisplayedFrom = nextIndex;
    	SetComingSoon(currentlyDisplayedFrom);

    }

}
