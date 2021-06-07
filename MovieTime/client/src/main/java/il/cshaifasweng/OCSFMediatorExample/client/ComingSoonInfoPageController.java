package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ComingSoonInfoPageController {
	private int purchaseType;

    @FXML
    private ImageView movieLargeImageSrc;

    @FXML
    private ImageView movieImageSrc;

    @FXML
    private Label movieName;

    @FXML
    private Label movieGenre;

    @FXML
    private Label movieNameSecond;

    @FXML
    private Label movieGenreSecond;

    @FXML
    private Label movieDescription;

    @FXML
    private Label movieProducers;

    @FXML
    private Label movieMainActors;

    @FXML
    private Label movieDuration;

    @FXML
    private Label movieLaunchDate;
    
    void setComingSoonInfo(Movie movie) {
    	movieImageSrc.setImage(movie.getImage());
    	movieLargeImageSrc.setImage(movie.getLargeImage());
    	movieName.setText(movie.getName());
    	movieGenre.setText(movie.getGenre());
    	movieNameSecond.setText(movie.getName());
    	movieGenreSecond.setText(movie.getGenre());
    	movieDescription.setText(movie.getDescription());
    	movieDescription.setWrapText(true);
    	movieProducers.setText(movie.getProducersMovie());
    	movieMainActors.setText(movie.getMainActors());
    	movieDuration.setText(movie.getDuration());
    	movieLaunchDate.setText(movie.getLaunchDate().toString());
    }
    
    public void setPurchaseType(int type) {
    	this.purchaseType = type;
    }
    
    public int getPurchaseType() {
    	return this.purchaseType;
    }

}
