package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.PurpleLimit;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class ScreeningCardController {
    @FXML
    private AnchorPane card;

	@FXML
	private Label label_time;

	@FXML
	private Label label_sender;

	@FXML
	private Label label_type;

	@FXML
	private Label label_title;

    @FXML
    private Label label_movie_name;

    @FXML
    private Label label_cinema;

    @FXML
    private Label label_date;
    
    @FXML
    private Label label_hall;

    @FXML
    private Label fromLabel;

    @FXML
    private Label toLabel;

    @FXML
    private Label yLabel;

	private Complaint complaint;
    
    public void SetData(String movie, String cinema, String date, String time, int hall) {
    	label_movie_name.setText(movie);
    	label_cinema.setText(cinema);
    	label_date.setText(date);
    	label_time.setText(time);
    	label_hall.setText(Integer.toString(hall));
    	
    }

    public void SetComplaintData(Complaint complaint) {
		label_title.setText(complaint.getComplaintTitle());
    	label_type.setText(complaint.getComplaintType());
    	label_sender.setText(complaint.getFirstName() + " " + complaint.getLastName());
    	label_date.setText(complaint.getComplaintDate().toString());
    	label_time.setText(complaint.getComplaintTime().toString());
    	this.complaint = complaint;
    }
    
    public void SetPurpleLimitData(PurpleLimit purpleLimit) {
    	fromLabel.setText(purpleLimit.getFromDate().toString());
    	toLabel.setText(purpleLimit.getToDate().toString());
    	yLabel.setText(String.valueOf(purpleLimit.getY()));
    }
   
    public void loadComplaintHandling() throws IOException {
    	App.setWindowTitle(PageTitles.ComplaintHandling);
    	try {
    		ComplaintHandlingController controller = (ComplaintHandlingController) App.setContent("ComplaintHandling");
    		controller.setComplaintInfo(complaint);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
