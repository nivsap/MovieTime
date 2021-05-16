package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class OrderTicketsPageController {
	HallMapController hallMapController;
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
    
    public OrderTicketsPageController() {
    	hallMapContainer = new VBox();
    }
    

    public void loadMovieInfo(Movie movie) {
    	movieName.setText(movie.getName());
    	movieGenre.setText(movie.getGenre());
    	moviePopularity.setText(movie.getPopular().toString());
    	Image image = new  Image(getClass().getResourceAsStream("\\images\\MoviesPosters\\" + movie.getImageSrc()));
    	Image largeImage = new  Image(getClass().getResourceAsStream("\\images\\MoviesPosters\\LargeImages\\" + movie.getLargeImageSrc()));
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

}