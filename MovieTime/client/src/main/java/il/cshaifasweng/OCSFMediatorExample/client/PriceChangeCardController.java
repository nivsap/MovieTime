package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.PriceRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PriceChangeCardController {
	private PriceChangeApprovalsController controller;
	private PriceRequest request;

    @FXML
    private Button cardBtn;

    @FXML
    private Label dateLabel;

    @FXML
    private Label oldPriceLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label newPriceLabel;
    
    @FXML
    void initialize()throws Exception {
        assert cardBtn != null : "fx:id=\"cardBtn\" was not injected: check your FXML file 'PriceChangeCard.fxml'.";
        assert dateLabel != null : "fx:id=\"dateLabel\" was not injected: check your FXML file 'PriceChangeCard.fxml'.";
        assert oldPriceLabel != null : "fx:id=\"oldPriceLabel\" was not injected: check your FXML file 'PriceChangeCard.fxml'.";
        assert typeLabel != null : "fx:id=\"typeLabel\" was not injected: check your FXML file 'PriceChangeCard.fxml'.";
        assert newPriceLabel != null : "fx:id=\"newPriceLabel\" was not injected: check your FXML file 'PriceChangeCard.fxml'.";
    }
    
    void setRequestData(PriceRequest request, PriceChangeApprovalsController controller) {
    	this.controller = controller;
    	this.request = request;
        dateLabel.setText(request.getRequestDate().toString().substring(0,10));
        oldPriceLabel.setText(String.valueOf(request.getOldPrice()));
        newPriceLabel.setText(String.valueOf(request.getNewPrice()));
        if(request.isTicket())
        	typeLabel.setText("Movie Price Change");
        if(request.isLink())
        	typeLabel.setText("Viewing Package Price Change");
        if(request.isCard())
        	typeLabel.setText("Subscription Card Price Change");
    }
    
    @FXML
    void loadRequestHandling() {
    	controller.setRequestHandling(request);
    }
}
