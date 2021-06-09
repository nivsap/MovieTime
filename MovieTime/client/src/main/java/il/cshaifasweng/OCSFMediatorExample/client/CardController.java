package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class CardController {
	
	private Movie cardMovie;
	private int cardType;
	private Boolean isDisabled = false;
	
	@FXML
	private ImageView movie_img;

	@FXML
	private Button movie_name;
	    
	public void SetData(Movie movie, Boolean isDisabled, int cardType) {
		if(movie != null) {
			this.cardType = cardType;
			movie_img.setImage(movie.getImage());
			movie_name.setText(movie.getName());
			cardMovie = movie;
			this.isDisabled = isDisabled;
		}
	}
	
	@FXML
	void loadMovieInfoPage(ActionEvent event) throws IOException {
		if(isDisabled)
			return;
		
		App.setWindowTitle(cardMovie.getName());
		if(cardType == PurchaseTypes.VIEWING_PACKAGE) {
    		System.out.println("in loadMovieInfoPage");
    		ViewingPackagesInfoPageController controller = (ViewingPackagesInfoPageController) App.setContent("LinkMovieInfoPage");
	    	controller.InitPageInfo(cardMovie);
    	}

		else {
			if(cardType == PurchaseTypes.TICKET) {
	    	MovieInfoPageController controller = (MovieInfoPageController) App.setContent("MovieInfoPage");
		    controller.InitPageInfo(cardMovie);
		    }
		    else {
		    	ComingSoonInfoPageController controller = (ComingSoonInfoPageController) App.setContent("ComingSoonInfoPage");
			    controller.setComingSoonInfo(cardMovie);
		    }	
		}
	}
}
