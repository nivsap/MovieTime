package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;

public class MainPageController implements Initializable {
	int NUM_ROWS = 2, NUM_COLS = 3;
    @FXML
    private SystemMenuController systemMenuController;
    
    @FXML
    private Pane menu;
    
    @FXML
    private TextField mainSearchBar;

    @FXML
    private GridPane movie_Container;

    @FXML
    private VBox card_layout1;

    @FXML
    private VBox card_layout2;

    @FXML
    private VBox card_layout3;

    @FXML
    private VBox card_layout4;

    @FXML
    private VBox card_layout5;

    @FXML
    private VBox card_layout6;
	private List<Movie> recentlyAdded;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventBus.getDefault().register(this);
		Message msg = new Message();
		msg.setAction("pull movies");
		try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("faile to send msg to server from recentlyAdded");
			e.printStackTrace();
		}
		//EventBus.getDefault().post(msg);

	}

	public void SetMovies() {
		try {
			int last = 0;
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLS; j++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("card.fxml"));
				Button cardBox = fxmlLoader.load();
				CardController cardController = fxmlLoader.getController();
				cardController.SetData(recentlyAdded.get(last));
				movie_Container.add(cardBox, j, i);
				last++;
               }
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Subscribe
	public void onMessageEvent(Message msg) {
		System.out.println("reveived message!!");
		System.out.println(msg.getAction());
    		if(msg.getAction().equals("got movies")) {
    			
    			Platform.runLater(()-> {
    				recentlyAdded = msg.getMovies();
    				SetMovies();
    			});
    		}
	}
	

	
}

/*
 * @Subscribe private List<Movie> recentlyAdded(){
 * EventBus.getDefault().register(this); Message msg= new Message();
 * msg.setAction("pull movies"); try { AppClient.getClient().sendToServer(msg);
 * } catch (IOException e) { // TODO Auto-generated catch block
 * System.out.println("faile to send msg to server from recentlyAdded");
 * e.printStackTrace(); } List<Movie> ls = new ArrayList<>();
 * 
 * Movie movie = new Movie("300","x",1,"an action packed war movie\r\n" +
 * "like no other");
 * 
 * //ls.add(movie); return ls;
 * 
 * } }
 */