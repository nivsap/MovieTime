package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class OrderTicketsPageController {
	HallMapController hallMapController;
	private String orderType;
	
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
    private Button orderTicketsBtn;
    
    public OrderTicketsPageController() {
    	hallMapContainer = new VBox();
    }
    
    @FXML
    void initialize() {
        assert movieLargeImageSrc != null : "fx:id=\"movieLargeImageSrc\" was not injected: check your FXML file 'OrderTicketsPage.fxml'.";
        assert movieImageSrc != null : "fx:id=\"movieImageSrc\" was not injected: check your FXML file 'OrderTicketsPage.fxml'.";
        assert moviePopularity != null : "fx:id=\"moviePopularity\" was not injected: check your FXML file 'OrderTicketsPage.fxml'.";
        assert movieName != null : "fx:id=\"movieName\" was not injected: check your FXML file 'OrderTicketsPage.fxml'.";
        assert movieGenre != null : "fx:id=\"movieGenre\" was not injected: check your FXML file 'OrderTicketsPage.fxml'.";
        assert hallMapContainer != null : "fx:id=\"hallMapContainer\" was not injected: check your FXML file 'OrderTicketsPage.fxml'.";
        assert orderTicketsBtn != null : "fx:id=\"orderTicketsBtn\" was not injected: check your FXML file 'OrderTicketsPage.fxml'.";
    }
    

    public void loadMovieInfo(Movie movie) {
    	movieName.setText(movie.getName());
    	movieGenre.setText(movie.getGenre());
    	moviePopularity.setText(movie.getPopular().toString());
    	Image image = new  Image(getClass().getResourceAsStream("images/MoviesPosters/" + movie.getImageSrc()));
    	Image largeImage = new  Image(getClass().getResourceAsStream("images/MoviesPosters/LargeImages/" + movie.getLargeImageSrc()));
    	movieLargeImageSrc.setImage(largeImage);
    	movieImageSrc.setImage(image);
    }
    
    public void loadHallMap(int rows, int cols) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("HallMap.fxml"));
		VBox hallMap = fxmlLoader.load();
		hallMapController = fxmlLoader.getController();
		hallMapController.setMap(rows, cols);
		hallMapContainer.getChildren().add(hallMap);
    }
    
    @FXML
    void orderTickets() throws IOException {
    	App.setWindowTitle(PageTitles.OrderTicketsPage);
    	PaymentPageController controller = (PaymentPageController) App.setContent("PaymentPage");
    }

}