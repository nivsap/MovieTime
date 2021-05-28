package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScreeningCardController {

	
    @FXML
    private Label label_movie_name;

    @FXML
    private Label label_cinema;

    @FXML
    private Label label_date;

    @FXML
    private Label label_time;
    
    @FXML
    private Label label_hall;


    
    
    public void SetData(String movie,String cinema, String date, String time, int hall) {
    	label_movie_name.setText(movie);
    	label_cinema.setText(cinema);
    	label_date.setText(date);
    	label_time.setText(time);
    	label_hall.setText(Integer.toString(hall));
    	
    }
    
    
    
}
