package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OpenComplaintsController {
	@FXML
    private ComboBox<?> cb_movie;

    @FXML
    private ComboBox<?> cb_time;

    @FXML
    private ComboBox<?> cb_date;

    @FXML
    private ComboBox<?> cb_cinema;

    @FXML
    private ComboBox<?> cb_removal_addition;

    @FXML
    private Button btn_update;

    @FXML
    private VBox screening_time_layout;
	private List<Complaint> allComplaints;
	
	    @FXML
	    private Button btn_main_page;
	    
	   
		
	
	@FXML
	public void initialize() {
		System.out.println("initializing aopen com[lain");
		EventBus.getDefault().register(this);
		PullComplaint();
		System.out.println("end aopen com[lain");

	}
	
	
	private void PullComplaint() {
		System.out.println("start PullComplaint");
		Message msg= new Message();
		msg.setAction("pull current complaint");
		try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to send msg to server from updateMoviesPage");
			e.printStackTrace();
		}
		System.out.println("end PullComplaint");

	}
	public void InitPage(){		
		 System.out.println("start InitPage");
		 String string;
		 
			try {
				for(Complaint complaint : allComplaints) {
						FXMLLoader fxmlLoader = new FXMLLoader();
						System.out.println("in card");
						fxmlLoader.setLocation(getClass().getResource("cardContainerComplaint.fxml"));
						System.out.println("end in card");
						HBox cardBox = fxmlLoader.load();	
						System.out.println("end in card1");
						ScreeningCardController ctrl = fxmlLoader.getController();
						System.out.println("end in card2");
						string=complaint.getIncidentDate().toString();

						//!!!!!!!!!!! need to decide which of the following 2 lines we keep !!!!!!!!!!!
						ctrl.SetData(string, complaint.getFirstName(), complaint.getComplaintType(), complaint.getComplaintTitle(),0);
						ctrl.SetDataComplain(complaint);

						System.out.println("end in card4");
						screening_time_layout.getChildren().add(cardBox);
					
					 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//if (needUpdateMovieTime) {
				
		//	}
			 System.out.println("end InitPage");

		}
	public void SetData() {
		 System.out.println("start SetData");

		InitPage();
		 System.out.println("end SetData");

	
}

	@Subscribe
	public void onMessageEvent(Message msg) throws IOException {
		 System.out.println("start onMessageEvent");

    		if(msg.getAction().equals("got complaints")) {
    			
    			Platform.runLater(()-> {
    				allComplaints = msg.getComplaints();
    				System.out.println(allComplaints.toString());
    				 for (Complaint model : allComplaints) {
    			            System.out.println(model.getFirstName());
    			        }
    				SetData();
    			});
    		}
   		 System.out.println("end onMessageEvent");

	}		
}
