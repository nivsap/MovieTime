package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ViewingPackagesInfoPageController {
	
    private int purchaseType;
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
    private Label movieProducers;

    @FXML
    private Label movieMainActors;

    @FXML
    private Label movieNameSecond;

    @FXML
    private Label movieGenreSecond;

    @FXML
    private AnchorPane orderTicketsContainer;

    @FXML
    private ComboBox<?> dateCombo;

    @FXML
    private ComboBox<?> timeCombo;

    @FXML
    private Button orderTicketBtn;

    
    
    public void setPurchaseType(int type) {
    	this.purchaseType = type;
    }
    
    public int getPurchaseType() {
    	return this.purchaseType;
    }
    
    void InitPageInfo(Movie movie) {
    	currentlyDisplayed = movie;
    	purchaseType = PurchaseTypes.TICKET;
    	movieImageSrc.setImage(movie.getImage());
    	movieLargeImageSrc.setImage(movie.getLargeImage());
    	movieName.setText(movie.getName());
    	movieGenre.setText(movie.getGenre());
    	moviePopularity.setText(movie.getPopular().toString());
    	movieNameSecond.setText(movie.getName());
    	movieGenreSecond.setText(movie.getGenre());
    	movieDescription.setText(movie.getDescription());
    	movieDescription.setWrapText(true);
    	movieProducers.setText(movie.getProducersMovie());
    	movieMainActors.setText(movie.getMainActors());
    	movieDuration.setText(movie.getDuration());
    	movieLaunchDate.setText(movie.getLaunchDate().toString());
    	dateCombo.getItems().clear();
    	timeCombo.getItems().clear();
    }
}

