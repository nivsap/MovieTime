
package il.cshaifasweng.OCSFMediatorExample.client; // should be View package

import java.io.File;
import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Pair;

public class App extends Application {

    private static Scene scene;
    private static String userName;
    private static String password;
    private static Stage stage;
    @FXML
    private static BorderPane pageLayout;
    @FXML
    private static VBox menu;
    @FXML
    private static VBox content;
    private AppClient client;
    public static Worker currentWorker;
    @Override
    public void start(Stage primaryStage) throws IOException {
    	
    	stage = primaryStage;
    	client = AppClient.getClient();
    	client.openConnection();
    	
    	// Setting Layout's Content
    	pageLayout = new BorderPane();
    	menu = (VBox) loadFXML("SystemMenu").getKey();
    	content = new VBox();
    	setBarAndGridLayout("MainPage");
    	pageLayout.setLeft(menu);
    	pageLayout.setCenter(content);
    	
    	// Setting App's Window
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
    
    @Override
    public void stop(){
        System.out.println("Stage is closing");
        EventBus.getDefault().register(this);
       
        if(userName != null) {
        	System.out.println(userName);
        	System.out.println(password);
        	
        	Message msg = new Message();
            msg.setAction("log out");
	        msg.setUsername(userName);
	        msg.setPassword(password);
	        try {
				AppClient.getClient().sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        //Message msg = new Message();
        }
        else {
        	Platform.exit();
            System.exit(0);
        }
    }
    
    
    @Subscribe
    void OnMessageEvent(Message msg) {
    	if(msg.getAction().equals("logged out")) {
    		EventBus.getDefault().unregister(this);
    		Platform.exit();
            System.exit(0);
    	}
    }
    
    static Object setContent(String pageName) throws IOException {
    	// setContent() loads page/FXML into App's content container and returns page's controller.
    	if(content != null)
    		content.getChildren().clear();
    	Pair<Parent, Object> pair = loadFXML(pageName);
    	pageLayout.setCenter(null);
    	content = (VBox) pair.getKey();
    	pageLayout.setCenter(content);
        stage.setScene(scene);
        stage.show();
        return pair.getValue();
    }
    
    static Object setMenu(String menuType) throws IOException {
    	// setMenu() loads page/FXML into App's menu container and returns menu's controller.
    	if(menu != null)
    		menu.getChildren().clear();
    	Pair<Parent, Object> pair = loadFXML(menuType);
    	pageLayout.setLeft(null);
    	menu = (VBox) pair.getKey();
    	pageLayout.setLeft(menu);
        stage.setScene(scene);
        stage.show();
        return pair.getValue();
    }
    
    static void setBarAndGridLayout(String pageName) throws IOException {
    	if(content != null)
    		content.getChildren().clear();
	    BarAndGridLayoutController controller = new BarAndGridLayoutController();
	    controller.setBarAndGrid(pageName);
	    content.getChildren().setAll(controller.getTopBar(),controller.getCardContainer());
    }
    
    static File openFilePicker() {
    	FileChooser filePicker = new FileChooser();
    	ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg");
    	filePicker.getExtensionFilters().add(fileExtensions);
    	File pickedFile = filePicker.showOpenDialog(stage);
    	return pickedFile;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).getKey());
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

    private static Pair<Parent, Object> loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        Object controller =  fxmlLoader.getController();
        return new Pair<>(root, controller);
    }
    
    public static Worker getCurrentWorker() {
		return currentWorker;
	}

	public static void setCurrentWorker(Worker worker) {
		currentWorker = worker;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		App.password = password;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		App.userName = userName;
	}
}


