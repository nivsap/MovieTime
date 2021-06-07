package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ViewingPackagesInfoPageController {

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
    private AnchorPane orderTicketsContainer;

    @FXML
    private ComboBox<?> dateCombo;

    @FXML
    private ComboBox<?> timeCombo;

    @FXML
    private Button orderTicketBtn;
    private int purchaseType;
    private Movie currentlyDisplayed;  
    public void setPurchaseType(int type) {
    	this.purchaseType = type;
    }
    
    public int getPurchaseType() {
    	return this.purchaseType;
    }
 void InitPageInfo(Movie movie) {
    	purchaseType = PurchaseTypes.TICKET;
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
    	movieImageSrc.setImage(movie.getImage());
    	movieLargeImageSrc.setImage(movie.getLargeImage());
    	//getCinemas(movie.getId());
    	//cinemaCombo.getItems().clear();
    	dateCombo.getItems().clear();
    	timeCombo.getItems().clear();
    }
}

