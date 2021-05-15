package il.cshaifasweng.OCSFMediatorExample.client; // should be View package

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;


public class App extends Application {
    
    private static Scene scene;
    private static Stage stage;
    @FXML
    private static BorderPane pageLayout;
    @FXML
    private static VBox menu;
    @FXML
    private static VBox content;
    private AppClient client;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
    	stage = primaryStage;
    	client = AppClient.getClient();
    	client.openConnection();
    	
    	// Setting application page components:
    	pageLayout = new BorderPane();
    	menu = (VBox) loadFXML("SystemMenu").getKey();
    	content = (VBox) loadFXML("MainPage").getKey();
    	pageLayout.setLeft(menu);
    	pageLayout.setCenter(content);
        scene = new Scene(pageLayout, 900, 700);
        // Setting application stage:
        stage.setTitle("Movie Time");
        stage.setScene(scene);
        stage.show();
    }
    
    static Object setContent(String pageName, String pageTitle) throws IOException {
    	// setContent() loads page/FXML into App's content container and returns page's controller.
    	if(pageName == "MainPage")
    		pageTitle = "Movie Time";
    	Pair<Parent, Object> pair = loadFXML(pageName);
    	pageLayout.setCenter(null);
    	content = (VBox) pair.getKey();
    	pageLayout.setCenter(content);
    	stage.setTitle(pageTitle);
        stage.setScene(scene);
        stage.show();
        return pair.getValue();
    }
    

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).getKey());
    }

    private static Pair<Parent, Object> loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        Object controller =  fxmlLoader.getController();
        return new Pair<>(root, controller);
    }

    public static void main(String[] args) {
        launch(args);
    }

}