package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SeatController {
	private Boolean isChosen, isTaken;
	
    @FXML
    private Button seatBtn;

    @FXML
    private ImageView seatIcon;
    
    public SeatController() {
    	isChosen = false;
    	isTaken =  false;
    }
	
	public void setIsTaken() {
		isTaken = true;
		Image image = new Image(getClass().getResourceAsStream("images/TakenSeatIcon.png"));
    	seatIcon.setImage(image);
	}

    @FXML
    void switchColor(ActionEvent event) {
    	if(isTaken) 
    		return;
        String imageSrc = "";
        if(!isChosen) {
        	isChosen = true;
        	imageSrc = "SelectedSeatIcon.png";
        }
        else {
        	isChosen = false;
        	imageSrc = "AvailableSeatIcon.png";
        }
        Image image = new Image(getClass().getResourceAsStream("images/" + imageSrc));
        seatIcon.setImage(image);
    }
}
