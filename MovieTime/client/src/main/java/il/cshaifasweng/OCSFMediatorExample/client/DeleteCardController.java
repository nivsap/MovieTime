package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class DeleteCardController {
	Object controller;
	String controllerType;
	
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
    	try {
    	cardContainer.getChildren().clear();
    	if(controllerType == "DeleteMovie")
    		((DeleteMoviePageController) controller).deleteMovie(movie);
    	if(controllerType == "DeleteViewingPackage")
    		((DeleteViewingPackagePageController) controller).deleteViewingPackage(movie);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() throws Exception{
        assert cardContainer != null : "fx:id=\"cardContainer\" was not injected: check your FXML file 'DeleteCard.fxml'.";
        assert movieImage != null : "fx:id=\"movieImage\" was not injected: check your FXML file 'DeleteCard.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'DeleteCard.fxml'.";
        assert movieNameLabel != null : "fx:id=\"movieNameLabel\" was not injected: check your FXML file 'DeleteCard.fxml'.";
    }
    
	public void setData(Movie movie, Object controller, String controllerType) {
		try {
		movieImage.setImage(movie.getImage());
		movieNameLabel.setText(movie.getName());
		this.movie = movie;
		this.controller = controller;
		this.controllerType = controllerType;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
