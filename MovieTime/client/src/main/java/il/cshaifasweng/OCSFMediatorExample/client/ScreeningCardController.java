package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class ScreeningCardController {

	

	    @FXML
	    private Label label_time;

	    @FXML
	    private Label label_sender;

	    @FXML
	    private Label label_type;

	    @FXML
	    private Label label_title;

	    @FXML
	    private Label label_Id;

	  
	

    @FXML
    private Label label_movie_name;

    @FXML
    private Label label_cinema;

    @FXML
    private Label label_date;

    
    public Complaint complaint;
   
	public int complainID;
	
	
    public void SetData(String movie,String cinema, String date, String time) {
    	label_movie_name.setText(movie);
    	label_cinema.setText(cinema);
    	label_date.setText(date);
    	label_time.setText(time);
    	
    }

    public void SetDataComplain(Complaint complaint) {
		System.out.println("in SetDataComplain");
		//tring, complaint.getFirstName()+complaint.getLastName(), complaint.getComplaintType(), complaint.getComplaintTitle(),complaint.getId()
		label_title.setText(complaint.getComplaintTitle());
    	label_type.setText(complaint.getComplaintType());
    	label_sender.setText(complaint.getFirstName()+complaint.getLastName());
    	label_time.setText(complaint.getIncidentDate().toString());
    	label_Id.setText(Integer.toString(complaint.getID()));
    	this.complaint=complaint;
    	if(this.complaint==null)
		System.out.println("!!!!!!!!!!!!!!!!!end SetDataComplain");
		

    	
    }
   
    public void loadComplaintHandeling() throws IOException {
    	    	App.setWindowTitle(PageTitles.ComplaintHandeling);
    	    	//ComplaintHandlingController controller1 = new ComplaintHandlingController();;
    			try {
    				ComplaintHandlingController controller1 = (ComplaintHandlingController) ( App.setContent("ComplaintHandling"));
    			controller1.setInfoComplaint(complaint);
    			if(complaint!=null)
    				System.out.println(complaint.getFirstName());
    				
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	System.out.println("befor load loadComplaintHandeling");
    	System.out.println("after load loadComplaintHandeling");
    	

    	
    }
    
    
    
}
