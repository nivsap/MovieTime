package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class CardController {
	private int purchaseType;
	private Movie cardMovie;
	
	@FXML
	private ImageView movie_img;

	@FXML
	private Button movie_name;
	    
	public void SetData(Movie movie) {
		Image image = new  Image(getClass().getResourceAsStream("images/MoviesPosters/" + movie.getImageSrc()));
		movie_img.setImage(image);
		movie_name.setText(movie.getName());
		cardMovie = movie;
	}
	
	@FXML
	void loadMovieInfoPage(ActionEvent event) throws IOException {
		App.setWindowTitle(cardMovie.getName());
	    if(!cardMovie.isSoonInCinema()) {
	    	MovieInfoPageController controller = (MovieInfoPageController) App.setContent("MovieInfoPage");
		    controller.InitPageInfo(cardMovie);
		    controller.setPurchaseType(purchaseType);
	    }
	    else {
	    	ComingSoonInfoPageController controller = (ComingSoonInfoPageController) App.setContent("ComingSoonInfoPage");
		    controller.setComingSoonInfo(cardMovie);
		    controller.setPurchaseType(PurchaseTypes.NOT_AVAILABLE);
	    }	
	}
	    
	public void setPurchaseType(int type) {
	    this.purchaseType = type;
	}

	public int getPurchaseType() {
	    return this.purchaseType;
	}
}
