package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SeatController {
	private Integer seat;
    @FXML
    private Button seatBtn;

    @FXML
    private ImageView seatIcon;
    private boolean tavSagol = false;
    
    public void setTavSagol(boolean val) {
    	tavSagol = val;
    }
    
    public SeatController() {
    	
    }
	
	public void setIsTaken() {
		if(!tavSagol) {
			seat = 1;
			Image image = new Image(getClass().getResourceAsStream("images/TakenSeatIcon.png"));
	    	seatIcon.setImage(image);
		}
	}
	
	public void setSeat(Integer seat) {
		this.seat = seat;
	}
	public int getSeat() {
		return seat;
	}

	public void setIsTChosen() {
		if(!tavSagol) {
			seat = 2;
			Image image = new Image(getClass().getResourceAsStream("images/SelectedSeatIcon.png"));
	    	seatIcon.setImage(image);
		}
	}
    @FXML
    void switchColor(ActionEvent event) {
    	if(!tavSagol) {
	    	if(seat == 1) 
	    		return;
	        String imageSrc = "";
	        if(seat == 0) {
	        	seat = 2;
	        	imageSrc = "SelectedSeatIcon.png";
	        }
	        else {
	        	seat = 0;
	        	imageSrc = "AvailableSeatIcon.png";
	        }
	        Image image = new Image(getClass().getResourceAsStream("images/" + imageSrc));
	        seatIcon.setImage(image);
    	}
    }
}
