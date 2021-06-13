package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HallMapController {

	private boolean tavSagol = false;
    @FXML
    private VBox hallMapContainer;
    private List<SeatController> cont = new ArrayList<SeatController>();
    public HallMapController() {
    	hallMapContainer = new VBox();
    	hallMapContainer.setAlignment(Pos.TOP_CENTER);
    }
    
    public void setTavSagol(boolean val) {
    	tavSagol = val;
    }

    public VBox setMap(int[][] ks, Hall hall) throws IOException {
    	for(int i=0; i<hall.getRows(); i++) {
    		HBox newRow = new HBox();
    		for(int j=0; j<hall.getCols(); j++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("Seat.fxml"));
				AnchorPane seat = fxmlLoader.load();
				SeatController seatController = fxmlLoader.getController();
				seatController.setSeat(ks[i][j]);
				cont.add(seatController);
				if(ks[i][j] == 1)
					seatController.setIsTaken();
				newRow.getChildren().add(seat);
				if(ks[i][j] == 2) {
					seatController.setIsTChosen();
				}
				seatController.setTavSagol(tavSagol);
    		}
    		newRow.setAlignment(Pos.TOP_CENTER);
    		hallMapContainer.getChildren().add(newRow);
    	}
    	return hallMapContainer;
    }
    
    public void getMap(Screening screening) {
    	Hall hall = screening.getHall();
    	for(int i = 0 ; i < hall.getRows() ; i++) {
    		//HBox row = (HBox) hallMapContainer.getChildren().get(i);
    		
    		for(int j = 0 ; j <  hall.getCols() ; j++) {
    			//AnchorPane seat = (AnchorPane) row.getChildren().get(j);
    			//SeatController seatController =  
    			screening.getSeats()[i][j] = cont.get(i * hall.getCols() + j).getSeat();
        	}
    		
    	}
    	
    }
    
    
}
