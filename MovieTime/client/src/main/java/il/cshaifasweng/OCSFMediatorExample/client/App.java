package il.cshaifasweng.OCSFMediatorExample.client; // should be View package

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
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
	private AppClient client;
    private static Scene scene;
    private static Stage stage;
    
    @FXML
    private static BorderPane pageLayout;
    
    @FXML
    private static VBox menu;
    
    @FXML
    private static VBox content;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
    	EventBus.getDefault().register(this);
    	stage = primaryStage;
    	client = AppClient.getClient();
    	client.openConnection();
    	
    	// Setting Layout's Content:
    	pageLayout = new BorderPane();
    	menu = (VBox) loadFXML("SystemMenu").getKey();
    	content = new VBox();
    	setBarAndGridLayout("MainPage");
    	pageLayout.setLeft(menu);
    	pageLayout.setCenter(content);
    	
    	// Setting App's Window:
    	setWindowTitle(PageTitles.MainPage);
        scene = new Scene(pageLayout, 900, 700);
        stage.setScene(scene);
        stage.show();

		 /* For Connection Page:
		 * pageLayout = new BorderPane(); 
		 * content = (VBox) loadFXML("ConnectionLogin").getKey();
		 * pageLayout.setCenter(content); 
		 * scene = new Scene(pageLayout, 900, 700);
		 * stage.setTitle("Establish Connection"); 
		 * stage.setScene(scene); 
		 * stage.show();
		 */
    }
    
    static void setWindowTitle(String title) {
    	stage.setTitle(title);
    }

    static void setBarAndGridLayout(String pageName) throws IOException {
    	BarAndGridLayoutController controller = new BarAndGridLayoutController();
    	controller.setBarAndGrid(pageName);
    	content.getChildren().setAll(controller.getTopBar(),controller.getCardContainer());
    }
      
    static Object setContent(String pageName) throws IOException {
    	// setContent() loads page/FXML into App's content container and returns page's controller.
    	Pair<Parent, Object> pair = loadFXML(pageName);
    	pageLayout.setCenter(null);
    	content = (VBox) pair.getKey();
    	pageLayout.setCenter(content);
        stage.setScene(scene);
        stage.show();
        return pair.getValue();
    }
    
    static Object setMenu(String menuType) throws IOException {
    	Pair<Parent, Object> pair = loadFXML(menuType);
    	pageLayout.setLeft(null);
    	menu = (VBox) pair.getKey();
    	pageLayout.setLeft(menu);
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
    
    @Subscribe
	public void SetClient(Message msg) throws IOException {	
    	if(msg.getAction().equals("set client")) {
    		client = AppClient.getClient();
    	}
	}

}