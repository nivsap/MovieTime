package il.cshaifasweng.OCSFMediatorExample.client;


import java.io.IOException;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Pair;


public class CardController {

	    @FXML
	    private ImageView movie_img;

	    @FXML
	    private Button movie_name;

	    Movie cardMovie;
	    
	    public void SetData(Movie movie) {
	    	Image image = new  Image(getClass().getResourceAsStream("\\images\\MoviesPosters\\" + movie.getImageSrc()));
	    	movie_img.setImage(image);
	    	movie_name.setText(movie.getName());
	    	cardMovie = movie;
	    }
	    
	    @FXML
	    void loadMovieInfoPage(ActionEvent event) throws IOException {
	    	Stage stage = new Stage();
            Scene scene;
            Pair<Parent, Object> result =  ContentController.getContentController().loadPage("MovieInfoPage");
            Parent parent = result.getKey();
            MovieInfoPageController controller = (MovieInfoPageController) result.getValue();
            controller.setMovieInfo(cardMovie);
	    	scene = new Scene(parent);
	    	stage.setScene(scene);
	    	stage.setTitle(cardMovie.getName());
            stage.show();
	    }
	    

}
