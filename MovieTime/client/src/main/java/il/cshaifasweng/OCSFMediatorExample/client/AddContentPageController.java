package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;



import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class AddContentPageController {
	private FilePickerController imagePickerController, largeImagePickerController;
	//private Movie newMovie;
	private Boolean isLaunchedMovie;
	private final ToggleGroup comingSoonGroup = new ToggleGroup();
	final List<String> selectedGenres = new ArrayList<String>();
	private final CheckBox[] allGenres = {new CheckBox("Action"), new CheckBox("Adventure"), 
			new CheckBox("Animation"), new CheckBox("Comedy"), new CheckBox("Crime"), 
			new CheckBox("Drama"), new CheckBox("Experimental"), new CheckBox("Fantasy"), 
			new CheckBox("Historical"), new CheckBox("Horror"), new CheckBox("Romance"), 
			new CheckBox("Science"), new CheckBox("Fiction"), new CheckBox("Thriller"), 
			new CheckBox("Western"), new CheckBox("Other")};
	
	private LocalDate launchDate;
	
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField mainActorsTextField;

    @FXML
    private TextField producersTextField;
    
    @FXML
    private TextArea shortDescriptionTextArea;

    @FXML
    private TextField hoursDurationTextField;

    @FXML
    private TextField minutesDurationTextField;

    @FXML
    private DatePicker launchDatePicker;

    @FXML
    private Label rateLabel;

    @FXML
    private TextField rateTextField;

    @FXML
    private ScrollPane genreScrollPane;

    @FXML
    private VBox genreCheckBoxContainer;

    @FXML
    private RadioButton yesRadioBtn;

    @FXML
    private RadioButton noRadioBtn;

    @FXML
    private VBox imageLoaderBtnContainer;

    @FXML
    private VBox largeImageLoaderBtnContainer;

    @FXML
    private Button addMovieBtn;

    @FXML
    private Label movieWarningLabel;

    @FXML
    private Button addViewingPackageBtn;

    public AddContentPageController() {
    	genreCheckBoxContainer = new VBox();
    	imagePickerController = new FilePickerController();
    	largeImagePickerController = new FilePickerController();
    }

    @FXML
    void initialize() {
    	assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert mainActorsTextField != null : "fx:id=\"mainActorsTextField\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert producersTextField != null : "fx:id=\"producersTextField\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert shortDescriptionTextArea != null : "fx:id=\"shortDescriptionTextArea\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert hoursDurationTextField != null : "fx:id=\"hoursDurationTextField\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert minutesDurationTextField != null : "fx:id=\"minutesDurationTextField\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert launchDatePicker != null : "fx:id=\"launchDatePicker\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert rateLabel != null : "fx:id=\"rateLabel\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert rateTextField != null : "fx:id=\"rateTextField\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert genreScrollPane != null : "fx:id=\"genreScrollPane\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert genreCheckBoxContainer != null : "fx:id=\"genreCheckBoxContainer\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert yesRadioBtn != null : "fx:id=\"yesRadioBtn\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert noRadioBtn != null : "fx:id=\"noRadioBtn\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert imageLoaderBtnContainer != null : "fx:id=\"imageLoaderBtnContainer\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert largeImageLoaderBtnContainer != null : "fx:id=\"largeImageLoaderBtnContainer\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert addMovieBtn != null : "fx:id=\"addMovieBtn\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert movieWarningLabel != null : "fx:id=\"movieWarningLabel\" was not injected: check your FXML file 'AddContentPage.fxml'.";
        assert addViewingPackageBtn != null : "fx:id=\"addViewingPackageBtn\" was not injected: check your FXML file 'AddContentPage.fxml'.";

        movieWarningLabel.setVisible(false);
        
        setEventListeners();
        genreCheckBoxContainer.getChildren().addAll(allGenres);

        noRadioBtn.setToggleGroup(comingSoonGroup);
        yesRadioBtn.setToggleGroup(comingSoonGroup);
        
        rateTextField.setDisable(true);
    }
    
    void setEventListeners() {
    	for(CheckBox cb: allGenres) {
    		cb.selectedProperty().addListener( (options, oldValue, newValue)-> {
    			if(newValue.booleanValue()) {
    				selectedGenres.add(cb.getText());
    			}
    			else {
    				selectedGenres.remove(cb.getText());
    			}
    			System.out.println(selectedGenres);
    		});
    	}
    }
    
    void loadFileLoaderButtons() throws IOException {
		FXMLLoader imageLoader = new FXMLLoader();
		imageLoader.setLocation(getClass().getResource("FilePicker.fxml"));
		VBox imagePicker = (VBox) imageLoader.load();
		imagePickerController = imageLoader.getController();
		imageLoaderBtnContainer.getChildren().add(imagePicker);
		
		FXMLLoader largeImageLoader = new FXMLLoader();
		largeImageLoader.setLocation(getClass().getResource("FilePicker.fxml"));
		VBox largeImagePicker = (VBox) largeImageLoader.load();
		largeImagePickerController = largeImageLoader.getController();
		largeImageLoaderBtnContainer.getChildren().add(largeImagePicker);
    }

    @FXML
    void loadRate(ActionEvent event) {
    	launchDate = launchDatePicker.getValue();
    	if((launchDate.getYear() < LocalDate.now().getYear()) ||
    		(launchDate.getYear() == LocalDate.now().getYear() && launchDate.getDayOfYear() <= LocalDate.now().getDayOfYear())) {
            isLaunchedMovie = true;
    		rateTextField.setDisable(false);
            noRadioBtn.setDisable(false);
    	}
    	else {
    		isLaunchedMovie = false;
    		rateTextField.setDisable(true);
    		noRadioBtn.setDisable(true);
            yesRadioBtn.setSelected(true);
            
    	}
    }
    
    @FXML
    void addMovie(ActionEvent event) throws NumberFormatException, IOException {
    	movieWarningLabel.setAlignment(Pos.TOP_CENTER);
    	movieWarningLabel.setVisible(false);
    	
    	String name = nameTextField.getText();
    	if(name.equals("")) {
    		movieWarningLabel.setText("Please fill name first");
    		movieWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String mainActors = mainActorsTextField.getText();
    	if(mainActors.equals("")) {
    		movieWarningLabel.setText("Please fill main actors first");
    		movieWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String producers = producersTextField.getText();
    	if(producers.equals("")) {
    		movieWarningLabel.setText("Please fill producers first");
    		movieWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String shortDescription = shortDescriptionTextArea.getText();
    	if(shortDescription.equals("")) {
    		movieWarningLabel.setText("Please fill short description first");
    		movieWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String hoursDuration = hoursDurationTextField.getText();
    	if(hoursDuration.equals("")) {
    		movieWarningLabel.setText("Please fill hours duration first");
    		movieWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String minutesDuration = minutesDurationTextField.getText();
    	if(minutesDuration.equals("")) {
    		movieWarningLabel.setText("Please fill minutes duration first");
    		movieWarningLabel.setVisible(true);
    		return;
    	}
    	
    	if(launchDate == null) {
    		movieWarningLabel.setText("Please pick launch date first");
    		movieWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String rate = rateTextField.getText();
    	if(isLaunchedMovie && rate.equals("")) {
    		movieWarningLabel.setText("Please fill rate first");
    		movieWarningLabel.setVisible(true);
    		return;
    	}
    	else {
    		if(!isLaunchedMovie)
    			rate = "0";
    	}
    	
    	if(selectedGenres.isEmpty()) {
    		movieWarningLabel.setText("Please select genre first");
    		movieWarningLabel.setVisible(true);
    		return;
    	}
    	
    	Boolean isYes = yesRadioBtn.isSelected();
    	Boolean isNo = noRadioBtn.isSelected();
    	if(!isYes && !isNo) {
    		movieWarningLabel.setText("Please pick coming soon first");
    		movieWarningLabel.setVisible(true);
    		return;
    	}
    	
    	if(imagePickerController.getLoadedFile().equals(null)) {
    		movieWarningLabel.setText("Please pick poster image first");
    		movieWarningLabel.setVisible(true);
    		return;
    	}
    		
    	if(largeImagePickerController.getLoadedFile().equals(null)) {
    		movieWarningLabel.setText("Please pick cover image first");
    		movieWarningLabel.setVisible(true);
    		return;
    	}
    	
    	String duration = hoursDuration + "h " + minutesDuration + "min";
    	LocalDateTime launchDateTime = LocalDate.of(launchDate.getYear(), launchDate.getMonthValue(), launchDate.getDayOfWeek().getValue()).atStartOfDay();
    	
    	Movie newMovie = new Movie(name, 
    							   duration, 
    							   Double.parseDouble(rate),
    							   selectedGenres.toString(),
    							   imagePickerController.getLoadedFile().getAbsolutePath().toString(), 
    							   largeImagePickerController.getLoadedFile().getAbsolutePath().toString(),
    							   null,
    							   false,
    							   isYes,
    							   shortDescription,
    							   mainActors,
    							   launchDateTime,
    							   0,
    							   producers,null,false,new ArrayList<>(),true);
    	sendMovieToServer(newMovie);
    }
    
    void sendMovieToServer(Movie newMovie) {
    	EventBus.getDefault().register(this);
    	Message msg = new Message();
    	msg.setAction("add movie");
    	msg.setMovie(newMovie);
    	try {
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to send msg to server from AddContentPageController");
			e.printStackTrace();
		}
    	
    }
    
    @Subscribe 
    public void onMessageEvent(Message msg){
    	if(msg.getAction().equals("added movie")) {
    		Platform.runLater(()-> {
    			EventBus.getDefault().unregister(this);
    			System.out.println("added movie");
    			JOptionPane.showMessageDialog(null, "Movie added successfully");
    		});
    		
    	}    	
    }
}
