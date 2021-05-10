package il.cshaifasweng.OCSFMediatorExample.client;




import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class CardController {

	    @FXML
	    private ImageView movie_img;

	    @FXML
	    private Button movie_name;


	    
	    public void SetData(Movie movie) {
	    	Image image = new  Image(getClass().getResourceAsStream("\\images\\MoviesPosters\\" + movie.getImageSrc()));
	    	movie_img.setImage(image);
	    	movie_name.setText(movie.getName());
	    }
}
