package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class DeleteCardController {
	DeleteMoviePageController controller;
	private Movie movie;

    @FXML
    private VBox cardContainer;
	
    @FXML
    private ImageView movieImage;

    @FXML
    private Button deleteBtn;

    @FXML
    private Label movieNameLabel;

    @FXML
    void deleteMovie(ActionEvent event) {
    	cardContainer.getChildren().clear();
    	controller.deleteMovie(movie);
    }

    @FXML
    void initialize() {
        assert cardContainer != null : "fx:id=\"cardContainer\" was not injected: check your FXML file 'DeleteCard.fxml'.";
        assert movieImage != null : "fx:id=\"movieImage\" was not injected: check your FXML file 'DeleteCard.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'DeleteCard.fxml'.";
        assert movieNameLabel != null : "fx:id=\"movieNameLabel\" was not injected: check your FXML file 'DeleteCard.fxml'.";
    }
    
	public void setData(Movie movie, DeleteMoviePageController controller) {
		Image image = new  Image(getClass().getResourceAsStream("images/MoviesPosters/" + movie.getImageSrc()));
		movieImage.setImage(image);
		movieNameLabel.setText(movie.getName());
		this.movie = movie;
		this.controller = controller;
	}
}
