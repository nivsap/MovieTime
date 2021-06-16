package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class ImageSliderController {
	int currentlyDisplayed;
	ArrayList<Image> images;
    @FXML
    private ImageView imageSlider;
    
    public ImageSliderController() throws Exception{
    	currentlyDisplayed = 1;
    	images = new ArrayList<Image>();
    	images.add(new Image(getClass().getResourceAsStream("images/MoviesPosters/LargeImages/AvengersEndgame.png")));
    	images.add(new Image(getClass().getResourceAsStream("images/MoviesPosters/LargeImages/BabyDriver.png")));
    	images.add(new Image(getClass().getResourceAsStream("images/MoviesPosters/LargeImages/It.png")));
    	images.add(new Image(getClass().getResourceAsStream("images/MoviesPosters/LargeImages/Minions.png")));
    	images.add(new Image(getClass().getResourceAsStream("images/MoviesPosters/LargeImages/SherlockHolmes.png")));
    	images.add(new Image(getClass().getResourceAsStream("images/MoviesPosters/LargeImages/StarWars.png")));
    	images.add(new Image(getClass().getResourceAsStream("images/MoviesPosters/LargeImages/ToyStory.png")));
    	images.add(new Image(getClass().getResourceAsStream("images/MoviesPosters/LargeImages/WonderWoman1984.png")));
    }

    @FXML
    void initialize() {
        assert imageSlider != null : "fx:id=\"imageView\" was not injected: check your FXML file 'ImageSlider.fxml'.";
    }
    
    void rotateImages() {
    	try {
    	Timeline timeline = new Timeline();
    	KeyFrame key =  new KeyFrame(Duration.seconds(2), event -> {
    		imageSlider.setImage(images.get(currentlyDisplayed));
	        currentlyDisplayed++;
	        if(currentlyDisplayed > images.size() - 1)
	        	currentlyDisplayed = 0; 
	    });
    	timeline.getKeyFrames().add(key);   
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
