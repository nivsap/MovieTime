package il.cshaifasweng.OCSFMediatorExample.client; // should be View package

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
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
	private AppClient client;
    private static Scene scene;
    private static Stage stage;
	private static Object currentController;
    private static String userName;
    private static String password;
    private static Boolean isLogoutClicked = false;
    @FXML
    private static BorderPane pageLayout;
    @FXML
    private static VBox menu;
    @FXML
    private static VBox content;
    @Override
    public void start(Stage primaryStage)  {
    	EventBus.getDefault().register(this);
    	stage = primaryStage;
    	client = AppClient.getClient();
    	try {
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
    	} 
    	catch (IOException e) {
			e.printStackTrace();
		}
        
        
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
    	if(currentController!= null) {
	        System.out.println("Stage is closing");
	        if(currentController.getClass().equals(PaymentPageController.class)) {
	        	Message msg = new Message();
	        	msg.setAction("cancel current order");
	        	ArrayList<Pair<Integer,Integer>> seats = new ArrayList<Pair<Integer,Integer>>();
	        	Screening screening = ((PaymentPageController) currentController).getScreening();
	        	if(screening != null) {
		        	seats = (ArrayList)((PaymentPageController) currentController).getSeats();
		
		        	for(Pair<Integer,Integer> seat : seats) {
		        		screening.getSeats()[seat.getKey()][seat.getValue()] = 0;
		        	}
		        	msg.setScreening(screening);
		        	try {
						AppClient.getClient().sendToServer(msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }
    	}
       	
        if(userName != null) {
        	App.logout(false);
        }
        else {
        	Platform.exit();
            System.exit(0);
        }
    }
    
    public static void logout(Boolean logoutClicked) {
    	if(userName == null || password == null) {
    		Platform.exit();
    		System.exit(0);
    	}
    	isLogoutClicked = logoutClicked;
    	Message msg = new Message();
        msg.setAction("log out");
        msg.setUsername(userName);
        msg.setPassword(password);
        try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    @Subscribe
    void OnMessageEvent(Message msg) {
    	if(msg.getAction().equals("logged out")) {
    		if(App.isLogoutClicked) {
    			userName = null;
    			password = null;
    			isLogoutClicked = false;
    		}
    		
    		else {
    			EventBus.getDefault().unregister(this);
    			Platform.exit();
    			System.exit(0);
    		} 
    	}
    }
   //5 
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
        currentController = (pair.getValue());
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

	public static Object getCurrentController() {
		return currentController;
	}


}


