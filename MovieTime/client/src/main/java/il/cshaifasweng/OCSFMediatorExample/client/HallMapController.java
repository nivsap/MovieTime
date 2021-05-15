package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HallMapController {

    @FXML
    private VBox seatingChart;
    
    public HallMapController() {
    	seatingChart = new VBox();
    	seatingChart.setAlignment(Pos.TOP_CENTER);
    }

    public void setMap(int rowsNumber, int collsNumber) throws IOException {
    	for(int i=0; i<rowsNumber; i++) {
    		HBox newRow = new HBox();

    		for(int j=0; j<collsNumber; j++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("Seat.fxml"));
				AnchorPane seat = fxmlLoader.load();
				SeatController seatController = fxmlLoader.getController();
				if((j%3 == 0) || (i%5 == 0))
					seatController.setIsTaken();
				newRow.getChildren().add(seat);
    		}
    		newRow.setAlignment(Pos.TOP_CENTER);
    		seatingChart.getChildren().add(newRow);
    	}
    }
}
