package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MovieInfoPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label movieName;

    @FXML
    private Label movieDescription;

    @FXML
    private Label movieDuration;

    @FXML
    private Label movieGenre;

    @FXML
    private ImageView movieImageSrc;
    
    @FXML
    private ImageView movieLargeImageSrc;

    @FXML
    private Label movieLaunchDate;

    @FXML
    private Label movieMainActors;

    @FXML
    private Label moviePopularity;
    
    @FXML
    void initialize() {
        assert movieName != null : "fx:id=\"movieNameLabel\" was not injected: check your FXML file 'MovieInfoPage.fxml'.";
        assert movieDescription != null : "fx:id=\"movieDescriptionLabel\" was not injected: check your FXML file 'MovieInfoPage.fxml'.";
        assert movieDuration != null : "fx:id=\"movieDurationLabel\" was not injected: check your FXML file 'MovieInfoPage.fxml'.";
        assert movieGenre != null : "fx:id=\"movieGenreLabel\" was not injected: check your FXML file 'MovieInfoPage.fxml'.";
        assert movieImageSrc != null : "fx:id=\"movieImageSrc\" was not injected: check your FXML file 'MovieInfoPage.fxml'.";
        assert movieLaunchDate != null : "fx:id=\"movieLaunchDate\" was not injected: check your FXML file 'MovieInfoPage.fxml'.";
        assert movieMainActors != null : "fx:id=\"movieMainActors\" was not injected: check your FXML file 'MovieInfoPage.fxml'.";
        assert moviePopularity != null : "fx:id=\"moviePopularity\" was not injected: check your FXML file 'MovieInfoPage.fxml'.";

    }
    
    void setMovieInfo(Movie movie) {
    	movieName.setText(movie.getName());
    	movieDescription.setText(movie.getDescription());
    	movieDuration.setText(movie.getDuration());
    	movieGenre.setText(movie.getGenre());
    	movieLaunchDate.setText(movie.getLaunchDate().toString());
    	movieMainActors.setText(movie.getMainActors());
    	moviePopularity.setText(movie.getPopular().toString());
    	Image image = new  Image(getClass().getResourceAsStream("\\images\\MoviesPosters\\" + movie.getImageSrc()));
    	Image largeImage = new  Image(getClass().getResourceAsStream("\\images\\MoviesPosters\\LargeImages\\" + movie.getLargeImageSrc()));
    	movieLargeImageSrc.setImage(largeImage);
    	movieImageSrc.setImage(image);
    }
}
