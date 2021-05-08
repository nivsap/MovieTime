package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;

public class MainPageController implements Initializable {

	@FXML
	private HBox card_layout_new_movies;
	@FXML
	private GridPane movie_Container;
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
			for (int i = 0; i < recentlyAdded.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("card.fxml"));
				HBox cardBox = fxmlLoader.load();
				CardController cardController = fxmlLoader.getController();
				cardController.SetData(recentlyAdded.get(i));

				card_layout_new_movies.getChildren().add(cardBox);
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
    			recentlyAdded = msg.getMovies();
    			SetMovies();
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