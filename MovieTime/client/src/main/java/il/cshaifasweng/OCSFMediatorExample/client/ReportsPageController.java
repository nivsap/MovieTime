package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

public class ReportsPageController {
	
	private
	List<Purchase> purchases;
	List<Cinema> cinemas;
	List<Complaint> complaints;
	private boolean isAdministrator;
	private Cinema personalCinema;
    @FXML
    private BarChart<Integer, String> reportChart;
	private boolean isRegistered = false;

    @FXML
    private ComboBox<String> reportNameComboBox;

    @FXML
    private ComboBox<String> monthComboBox;
    
    
    @FXML
    void getReport() {
    	setData();	
    }
    

    public void SetUserType(boolean isAdministrator, Cinema cinema) {
    	this.isAdministrator = isAdministrator;
    	personalCinema = cinema;
    	if(!isAdministrator) {
    		reportNameComboBox.getItems().clear();
    		reportNameComboBox.getItems().addAll("Ticket Sales", "Tav Sagol Refunds", "Complaints by day");
        	
    	}
    }
    @Subscribe
    public void OnMessageEvent(Message msg) {
    	System.out.println("got message in ReportsPageController");
    	
    	if(msg.getAction().equals("got cinemas and purchases and complaints")) {
    		if(isRegistered) {
    			EventBus.getDefault().unregister(this);
    			isRegistered = false;
    		}
    		Platform.runLater(()-> {
    			this.purchases = msg.getPurchases();
    			this.cinemas = msg.getCinemas();
    			this.complaints = msg.getComplaints();
    			if(!isAdministrator) {
    				ArrayList<Cinema> temp = new ArrayList();
    				temp.add(personalCinema);
        			this.cinemas = temp;
        		}
    			//setData();
    		});
    		
    	}  	
    	
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void setData() {
    	
    	//ArrayList<Integer> count = new ArrayList<Integer>(cinemas.size());
    	reportChart.getData().clear();
    	
    	if(reportNameComboBox.getValue().equals("Ticket Sales")){
	    	XYChart.Series set1 = new XYChart.Series<>();
	    	for(Cinema cinema : cinemas) {
	        	int count = 0;
		    	for(Purchase purchase : purchases) {
		    		if(purchase.getPurchaseType() == PurchaseTypes.TICKET) {
			    		if(purchase.getScreening().getCinema().getName().equals(cinema.getName())) {
				    		if(purchase.getPurchaseTime().getMonthValue() == Integer.parseInt(monthComboBox.getValue())) {
								count++;
							}
			    		}
		    		}
		    	}
		    	System.out.println(count);
		    	set1.getData().add(new XYChart.Data(cinema.getName(), count));
	    	}
	    	reportChart.getData().add(set1);
    	}
    	
    	if(reportNameComboBox.getValue().equals("ViewingPackages and Subscription Card sales")){
	    	XYChart.Series set1 = new XYChart.Series<>();
        	int Vcount = 0;
        	int Scount = 0;
	    	for(Purchase purchase : purchases) {
	    		if(purchase.getPurchaseType() == PurchaseTypes.VIEWING_PACKAGE) {
						Vcount++;
	    		}
		    	if(purchase.getPurchaseType() == PurchaseTypes.SUBSCRIPTION_CARD)
					Scount++;
		
	    	}
	    	set1.getData().add(new XYChart.Data("Viewing Packages", Vcount));
	    	set1.getData().add(new XYChart.Data("Subscription Card", Scount));
	    	reportChart.getData().addAll(set1);
	    	
	    	
    	}
    	
    	if(reportNameComboBox.getValue().equals("Tav Sagol Refunds")){
	    	XYChart.Series set1 = new XYChart.Series<>();
	    	for(Cinema cinema : cinemas) {
	    		
	        	double count = 0;
		    	for(Purchase purchase : cinema.getCanceledPurchases()) {
		    		if(purchase.getPurchaseType() == PurchaseTypes.TICKET) {
			    		if(purchase.isCancelByPurpleLimit()) {
				    		if(purchase.getPurchaseTime().getMonthValue() == Integer.parseInt(monthComboBox.getValue())) {
								count+= purchase.getPayment();
							}
			    		}
		    		}
		    	}
		    	System.out.println(count);
		    	set1.getData().add(new XYChart.Data(cinema.getName() + " tav sagol refunds", count));
	    	}
	    	reportChart.getData().addAll(set1);
    	}
    	
    	
    	if(reportNameComboBox.getValue().equals("Complaints by day")){
	    	XYChart.Series set1 = new XYChart.Series<>();
	    	YearMonth yearMonthObject = YearMonth.of(2021,Integer.parseInt(monthComboBox.getValue()) );
	    	int daysInMonth = yearMonthObject.lengthOfMonth(); //28 
	    	System.out.println(daysInMonth + " days in month");
	    	ArrayList<Integer> count;
	    	count = new ArrayList<Integer>();
	    	
    		for(int j = 0 ; j <= daysInMonth ; j++) {
    			count.add(0);
    		}

    		if(isAdministrator) {
		    	for(Complaint complaint : complaints) {
		    		if(complaint.getComplaintDate().getMonthValue() == Integer.parseInt(monthComboBox.getValue())) {
		    			count.set(complaint.getComplaintDate().getDayOfMonth(), count.get((complaint.getComplaintDate().getDayOfMonth())) + 1 );
					}
		    	}
    		}else {
    			for(Complaint complaint : complaints) {
    	    		if(complaint.getPurchase().getPurchaseType() == PurchaseTypes.TICKET 
    	    				&& complaint.getPurchase().getCinema().getName().equals(personalCinema.getName())) {
    	    			
			    		if(complaint.getComplaintDate().getMonthValue() == Integer.parseInt(monthComboBox.getValue())) {
			    			count.set(complaint.getComplaintDate().getDayOfMonth(), count.get((complaint.getComplaintDate().getDayOfMonth())) + 1 );
						}
    	    		}
		    	}
    		}
    		
    		

			    	
	    	for(int i = 1 ; i <= daysInMonth ; i++) {
	    		set1.getData().add(new XYChart.Data(Integer.toString(i) + "." + monthComboBox.getValue(), count.get(i)));
	    	}
	    	reportChart.getData().addAll(set1);
    	}
    	
    	
    	
    	
    	
    	//set1.getData().add(new XYChart.Data("haifa", 1000));
    	//set1.getData().add(new XYChart.Data("tel-aviv", 500));
		
    	//reportChart.getData().addAll(set1);
    }
    
    @FXML
	public void initialize() {
    	
    	monthComboBox.getItems().clear();
    	reportChart.getData().clear();
    	reportChart.setAnimated(false);
    	reportNameComboBox.getItems().clear();
		
    	reportNameComboBox.getItems().addAll("Ticket Sales", "ViewingPackages and Subscription Card sales", "Tav Sagol Refunds", "Complaints by day");
    	
    	for(int i = 1 ; i < 13 ; i++) {
        	monthComboBox.getItems().add(Integer.toString(i));
        }
    	
    	if(!isRegistered) {
			EventBus.getDefault().register(this);
			isRegistered = true;
		}
    	Message msg = new Message();
    	msg.setAction("get cinemas and purchases and complaints");
    	try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
		/*
		 * XYChart.Series set1 = new XYChart.Series<>(); set1.getData().add(new
		 * XYChart.Data("haifa", 1)); set1.getData().add(new XYChart.Data("haifa", 1));
		 * set1.getData().add(new XYChart.Data("haifa", 1)); set1.getData().add(new
		 * XYChart.Data("tel-aviv", 1)); reportChart.getData().addAll(set1);
		 */
	}
    
    
    
    
    
    
    

}
