 package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.Arrays;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Pair;


public class ContentController {	
	
	private String[] legalPageTypes = { "MainPage", "MovieInfoPage", "UpdateMoviePage" };
    private static ContentController contentController;
    private Stage primaryStage;
    
    
    private ContentController() {
    }
    
    public void setStage(Stage stage) {
    	primaryStage = stage;
    }
    
    public Stage getStage() {
    	return primaryStage;
    }

    public static ContentController getContentController() {
        if (contentController == null) 
        	contentController = new ContentController();
        return contentController;
    }
	
    public Pair<Parent, Object> loadPage(String pageType) throws IOException {
    	if(!Arrays.asList(legalPageTypes).contains(pageType)) {
    		System.out.println("Illegal page name");
    		return null;
    	}
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(pageType + ".fxml"));
        fxmlLoader.setControllerFactory(c -> {
            if (c == MainPageController.class) {
                return new MainPageController();
            }
            if (c == MovieInfoPageController.class) {
                return new MovieInfoPageController();
            }
            return null;
        });
        Parent root = fxmlLoader.load();
        Object controller = fxmlLoader.getController();
        return new Pair<>(root, controller);
    }

}
