package il.cshaifasweng.OCSFMediatorExample.client;




import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class CardController {

	  @FXML
	    private HBox movie_box;

	    @FXML
	    private ImageView movie_img;

	    @FXML
	    private Label movie_name;

	    @FXML
	    private Label movie_desc;
	    
	    private String [] colors = {"B9E5FF","BDB2FE", "FB9AA8", "FF5056"};
	    
	    
	    public void SetData(Movie movie) {
	    	Image image = new  Image(getClass().getResourceAsStream("300.jpg"));
	    	movie_img.setImage(image);
	    	movie_name.setText(movie.getName());
	    	movie_desc.setText(movie.getGenre());
	    	movie_box.setStyle("-fx-background-color: #" + colors[(int)(Math.random()%colors.length)] + ";"
	    			+ "-fx-background-radius: 15;" + "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.1), 10,0,0,10);");
	    }
}
