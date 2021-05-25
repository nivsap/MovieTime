package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;


public class BarAndGridLayoutController {
	CardContainerController gridController;
	
	@FXML
    private VBox barContainer;
	
	@FXML
    private VBox gridContainer;
	
	public BarAndGridLayoutController() {
		gridController = new CardContainerController();
	}

	public void setBarAndGrid(String namePage) throws IOException {
		FXMLLoader barFXMLLoader = new FXMLLoader();
		FXMLLoader gridFXMLLoader = new FXMLLoader();

		if(namePage=="MainPage") {
			barFXMLLoader.setLocation(getClass().getResource("SearchBar.fxml"));
		}
		if(namePage=="ComingSoonPage") {
			barFXMLLoader.setLocation(getClass().getResource("ComingSoonBar.fxml"));
		}
		barContainer = barFXMLLoader.load();
		gridFXMLLoader.setLocation(getClass().getResource("CardContainer.fxml"));
		gridContainer = gridFXMLLoader.load();
		gridController = gridFXMLLoader.getController();
		gridController.setPurchaseType(PurchaseTypes.getType(namePage));
		gridController.sendMsgToServer(namePage);
	}
	
	public VBox getTopBar() {
		return barContainer;
	}
	
	public VBox getCardContainer() {
		return gridContainer;
	}
	
	public CardContainerController getGridController() {
		return gridController;
	}
}
