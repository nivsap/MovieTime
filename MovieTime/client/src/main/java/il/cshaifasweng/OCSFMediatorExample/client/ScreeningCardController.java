package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.PurpleLimit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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

	private Complaint complaint;
    
    public void SetData(String movie, String cinema, String date, String time, int hall) {
    	label_movie_name.setText(movie);
    	label_cinema.setText(cinema);
    	label_date.setText(date);
    	label_time.setText(time);
    	label_hall.setText(Integer.toString(hall));
    	
    }

    public void SetComplaintData(Complaint complaint) {
    	try {
		label_title.setText(complaint.getComplaintTitle());
        if(complaint.getPurchase().isTicket())
        	label_type.setText("Issues with tickets order");
        if(complaint.getPurchase().isLink())
        	label_type.setText("Issues with viewing packages");
        if(complaint.getPurchase().isCard())
        	label_type.setText("Issues with subscription cards");
   
    	label_sender.setText(complaint.getFirstName() + " " + complaint.getLastName());
    	label_date.setText(complaint.getComplaintDate().toString());
    	label_time.setText(complaint.getComplaintTime().toString());
    	this.complaint = complaint;
    	} catch (Exception e) {
			e.printStackTrace();
		}
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
