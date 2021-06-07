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

		if(namePage.equals("MainPage")) {
			barFXMLLoader.setLocation(getClass().getResource("SearchBar.fxml"));
			/*
			 * System.out.println("3"); SearchBarController searchBar =
			 * barFXMLLoader.getController(); System.out.println("3.5"); if(searchBar ==
			 * null) { System.out.println("search bar is null"); }
			 * searchBar.SetPageType(namePage); System.out.println("4");
			 */
		}
		if(namePage.equals("ComingSoonPage")) {
			barFXMLLoader.setLocation(getClass().getResource("ComingSoonBar.fxml"));
		}
		if(namePage.equals("ViewingPackagesPage")) {
	    	barFXMLLoader.setLocation(getClass().getResource("SearchBar.fxml"));

		}

		if(namePage.equals("NetworkAdministratorMainPage")) {
	    	barFXMLLoader.setLocation(getClass().getResource("SearchBar.fxml"));

		}
		if(namePage.equals("BranchManagerMainPage")) {
	    	barFXMLLoader.setLocation(getClass().getResource("SearchBar.fxml"));
		}
    	System.out.println("5");
		barContainer = barFXMLLoader.load();
		gridFXMLLoader.setLocation(getClass().getResource("CardContainer.fxml"));
		gridContainer = gridFXMLLoader.load();
		gridController = gridFXMLLoader.getController();
		gridController.setPurchaseType(PurchaseTypes.getType(namePage));
		gridController.setGridContent(namePage);
		CardContainerController ctrl = gridController;
		System.out.println("6");
		if(!namePage.equals("ComingSoonPage")) {
			SearchBarController Sctrl = barFXMLLoader.getController();
			System.out.println("6.5");
			System.out.println("7");
			Sctrl.SetPageType(namePage);
			Sctrl.setCardController(ctrl);
			System.out.println("8");
		}
		else {
			ComingSoonSearchBarController Sctrl = barFXMLLoader.getController();
			System.out.println("9");
			Sctrl.setCardController(ctrl);
			System.out.println("10");
		}
		
		
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
