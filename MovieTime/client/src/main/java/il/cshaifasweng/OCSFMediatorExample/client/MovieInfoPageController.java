package il.cshaifasweng.OCSFMediatorExample.client;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MovieInfoPageController {
    private Movie currentlyDisplayed;

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
    private Label movieMainActors;

    @FXML
    private Label movieNameSecond;

    @FXML
    private Label movieGenreSecond;

    @FXML
    private ComboBox<?> cinemaCombo;

    @FXML
    private ComboBox<?> dateCombo;

    @FXML
    private ComboBox<?> timeCombo;

    @FXML
    private ComboBox<?> LanguageCombo;

    @FXML
    private Button orderTicketBtn;
    
    void setMovieInfo(Movie movie) {
    	movieDescription.setWrapText(true);
    	currentlyDisplayed = movie;
    	movieName.setText(movie.getName());
    	movieDescription.setText(movie.getDescription());
    	movieDuration.setText(movie.getDuration());
    	movieGenre.setText(movie.getGenre());
    	movieLaunchDate.setText(movie.getLaunchDate().toString());
    	movieMainActors.setText(movie.getMainActors());
    	moviePopularity.setText(movie.getPopular().toString());
    	movieNameSecond.setText(movie.getName());
    	movieGenreSecond.setText(movie.getGenre());
    	Image image = new  Image(getClass().getResourceAsStream("\\images\\MoviesPosters\\" + movie.getImageSrc()));
    	Image largeImage = new  Image(getClass().getResourceAsStream("\\images\\MoviesPosters\\LargeImages\\" + movie.getLargeImageSrc()));
    	movieLargeImageSrc.setImage(largeImage);
    	movieImageSrc.setImage(image);
    }
    
    @FXML

    void orderTickets(ActionEvent event) throws IOException {
    	OrderTicketsPageController controller = (OrderTicketsPageController) App.setContent("OrderTicketsPage", "Order Tickets");
    	controller.loadMovieInfo(currentlyDisplayed);
    	controller.loadHallMap(7, 10);

    }
    
}
