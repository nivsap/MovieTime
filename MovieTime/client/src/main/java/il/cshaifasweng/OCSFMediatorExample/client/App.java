package il.cshaifasweng.OCSFMediatorExample.client; // should be View package

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class App extends Application {

    private static Scene scene;
    private AppClient client;

    @Override
    public void start(Stage stage) throws IOException {
    	
    //	EventBus.getDefault().register(this);
    	client = AppClient.getClient();
    	client.openConnection();
        scene = new Scene(loadFXML("MainPage"), 900, 700);
        stage.setTitle("Movie Time");
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}