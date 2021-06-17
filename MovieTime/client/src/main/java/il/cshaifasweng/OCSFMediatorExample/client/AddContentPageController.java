package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.ViewingPackage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
	private Boolean isLaunchedMovie, isRegistered;
	private final ToggleGroup comingSoonGroup = new ToggleGroup();
	private final List<String> selectedGenres = new ArrayList<String>();
	private final CheckBox[] allGenres = { new CheckBox("Action"), new CheckBox("Adventure"), 
			new CheckBox("Animation"), new CheckBox("Comedy"), new CheckBox("Crime"), 
			new CheckBox("Drama"), new CheckBox("Fantasy"), new CheckBox("Fiction"),
			new CheckBox("Horror"), new CheckBox("Mystery"), new CheckBox("Romance"), 
			new CheckBox("Science"), new CheckBox("Thriller"), new CheckBox("Other") };
	private List<Movie> moviesForViewingPackage;
	private Movie selectedMovie;
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
    private ComboBox<String> movieComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> timeComboBox;

    @FXML
    private TextField linkTextField;

    @FXML
    private Button addViewingPackageBtn;

    @FXML
    private Label viewingPackageWarningLabel;
    
    public AddContentPageController() throws IOException {
    	isRegistered = false;
    	genreCheckBoxContainer = new VBox();
    	imagePickerController = new FilePickerController();
    	largeImagePickerController = new FilePickerController(); 
    }

    @FXML
    void initialize() {
    	try {
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
	        assert movieComboBox != null : "fx:id=\"movieComboBox\" was not injected: check your FXML file 'AddContentPage.fxml'.";
	        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'AddContentPage.fxml'.";
	        assert timeComboBox != null : "fx:id=\"timeComboBox\" was not injected: check your FXML file 'AddContentPage.fxml'.";
	        assert linkTextField != null : "fx:id=\"linkTextField\" was not injected: check your FXML file 'AddContentPage.fxml'.";
	        assert addViewingPackageBtn != null : "fx:id=\"addViewingPackageBtn\" was not injected: check your FXML file 'AddContentPage.fxml'.";
	        assert viewingPackageWarningLabel != null : "fx:id=\"viewingPackageWarningLabel\" was not injected: check your FXML file 'AddContentPage.fxml'.";
	
	        movieWarningLabel.setVisible(false);
	        viewingPackageWarningLabel.setVisible(false);
	        
	        setEventListeners();
	        genreCheckBoxContainer.getChildren().addAll(allGenres);
	
	        noRadioBtn.setToggleGroup(comingSoonGroup);
	        yesRadioBtn.setToggleGroup(comingSoonGroup);
	        
	        rateLabel.setVisible(false);
	        rateTextField.setVisible(false);
	        
	        timeComboBox.getItems().clear();
	        timeComboBox.getItems().addAll(Arrays.asList("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"));
	        
	        getMovies();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    void setEventListeners() {
    	try {
	    	for(CheckBox cb: allGenres) {
	    		cb.selectedProperty().addListener( (options, oldValue, newValue)-> {
	    			if(newValue.booleanValue()) {
	    				selectedGenres.add(cb.getText());
	    			}
	    			else {
	    				selectedGenres.remove(cb.getText());
	    			}
	    		});
	    	}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    void loadFileLoaderButtons() {
    	try {
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
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void loadRate(ActionEvent event) {
    	try {
	    	launchDate = launchDatePicker.getValue();
	    	if((launchDate.getYear() < LocalDate.now().getYear()) ||
	    		(launchDate.getYear() == LocalDate.now().getYear() && launchDate.getDayOfYear() <= LocalDate.now().getDayOfYear())) {
	            isLaunchedMovie = true;
	            rateLabel.setVisible(true);
	            rateTextField.setVisible(true);
	            noRadioBtn.setDisable(false);
	    	}
	    	else {
	    		isLaunchedMovie = false;
	            rateLabel.setVisible(false);
	            rateTextField.setVisible(false);
	    		noRadioBtn.setDisable(true);
	            yesRadioBtn.setSelected(true);
	    	}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void addMovie(ActionEvent event) throws NumberFormatException, IOException {
    	try {
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
	    	
	    	if(!InputTests.isValidInt(hoursDuration)) {
	    		movieWarningLabel.setText("Hours duration is invalid");
	    		movieWarningLabel.setVisible(true);
	    		return;
	    	}
	    	
	    	String minutesDuration = minutesDurationTextField.getText();
	    	if(minutesDuration.equals("")) {
	    		movieWarningLabel.setText("Please fill minutes duration first");
	    		movieWarningLabel.setVisible(true);
	    		return;
	    	}
	    	
	    	if(!InputTests.isValidInt(minutesDuration)) {
	    		movieWarningLabel.setText("Minutes duration is invalid");
	    		movieWarningLabel.setVisible(true);
	    		return;
	    	}
	    	
	    	if(launchDate == null) {
	    		movieWarningLabel.setText("Please pick launch date first");
	    		movieWarningLabel.setVisible(true);
	    		return;
	    	}
	    	
	    	if(!InputTests.isValidDate(launchDate.toString())) {
	    		movieWarningLabel.setText("Launch date is invalid");
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
	    	
	    	if(isLaunchedMovie && !InputTests.isValidFloat(rate)) {
	    		movieWarningLabel.setText("Rate is invalid");
	    		movieWarningLabel.setVisible(true);
	    		return;
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
	    	
	    	String genre = "";
	    	for(String g: selectedGenres) {
	    		genre += g + "   •   ";
	    	}
	    	genre = genre.substring(0, genre.length() - 7);
	    	Movie newMovie = new Movie(name, genre, shortDescription, producers, mainActors, launchDate, Integer.parseInt(hoursDuration), Integer.parseInt(minutesDuration),
	    							   Float.parseFloat(rate), imagePickerController.getLoadedFile().getAbsolutePath().toString(), 
	    							   largeImagePickerController.getLoadedFile().getAbsolutePath().toString(), isYes,
	    							   false, null, null);
	    	sendMovieToServer(newMovie);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    void getMovies() {
    	try {
    	if(!isRegistered) {
    		EventBus.getDefault().register(this);
    		isRegistered = true;
    	}
    	Message msg = new Message();
    	msg.setAction("get all valid for viewing package movies");
			AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		} 	
    }
    
    void setMovies()  {
    	try {
	    	movieComboBox.getItems().clear();
	    	for(Movie m: moviesForViewingPackage) {
	    		movieComboBox.getItems().add(m.getName());
	    	} 
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void setSelectedMovie(ActionEvent event) {
    	try {
	    	String movieName = movieComboBox.getValue();
	    	for(Movie m: moviesForViewingPackage) {
	    		if(m.getName().equals(movieName)) {
	    			selectedMovie = m;
	    			return;
	    		}
	    	}
	    	selectedMovie = null;
	    } catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void addViewingPackage(ActionEvent event) {
    	try {
	    	viewingPackageWarningLabel.setVisible(false);
	
	    	if(selectedMovie == null) {
	    		viewingPackageWarningLabel.setText("Please pick movie first");
	    		viewingPackageWarningLabel.setVisible(true);
	    		return;
	    	}
	    	
	    	if(datePicker.getValue() == null) {
	    		viewingPackageWarningLabel.setText("Please pick a date first");
	    		viewingPackageWarningLabel.setVisible(true);
	    		return;
	    	}
	    	
	    	if(!InputTests.isValidDate(datePicker.getValue().toString())) {
	    		viewingPackageWarningLabel.setText("Date is invalid");
	    		viewingPackageWarningLabel.setVisible(true);
	    		return;
	    	}
	    	
	    	if(datePicker.getValue().isBefore(LocalDate.now())) {
	    		viewingPackageWarningLabel.setText("Date has already passed");
	    		viewingPackageWarningLabel.setVisible(true);
	    		return;
	    	}
	    	
	    	if(timeComboBox.getValue() == null) {
	    		viewingPackageWarningLabel.setText("Please pick a time first");
	    		viewingPackageWarningLabel.setVisible(true);
	    		return;
	    	}
	    	
	    	String link = linkTextField.getText();
	    	if(link.equals("")) {
	    		viewingPackageWarningLabel.setText("Please fill link first");
	    		viewingPackageWarningLabel.setVisible(true);
	    		return;
	    	}
	    	
	    	if(!InputTests.isValidLink(link)) {
	    		viewingPackageWarningLabel.setText("Link is invalid");
	    		viewingPackageWarningLabel.setVisible(true);
	    		return;
	    	}
	    	
	    	LocalDateTime viewingPackageTime = LocalDateTime.of(datePicker.getValue(), LocalTime.of(Integer.parseInt(timeComboBox.getValue().substring(0,2)), 0));
	    	if(viewingPackageTime.isBefore(LocalDateTime.now())) {
	    		viewingPackageWarningLabel.setText("Time has already passed");
	    		viewingPackageWarningLabel.setVisible(true);
	    		return;
	    	}
	
	    	ViewingPackage newViewingPackage = new ViewingPackage(selectedMovie, viewingPackageTime, link);
	    	sendViewingPackageToServer(newViewingPackage);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    

    
    void sendMovieToServer(Movie newMovie) {
    	try {
	    	if(!isRegistered) {
	    		EventBus.getDefault().register(this);
	    		isRegistered = true;
	    	}
	    	Message msg = new Message();
	    	msg.setAction("add movie");
	    	msg.setMovie(newMovie);
				AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    void sendViewingPackageToServer(ViewingPackage newViewingPackage) {
    	try {
	    	if(!isRegistered) {
	    		EventBus.getDefault().register(this);
	    		isRegistered = true;
	    	}
	    	Message msg = new Message();
	    	msg.setAction("add viewing package");
	    	msg.setViewingPackage(newViewingPackage);
				AppClient.getClient().sendToServer(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    void clearForm() {
    	try {
	    	nameTextField.clear();
	    	mainActorsTextField.clear();
	    	producersTextField.clear();
	    	shortDescriptionTextArea.clear();
	    	hoursDurationTextField.clear();
	    	minutesDurationTextField.clear();
	    	launchDatePicker.setValue(null);
	    	rateLabel.setVisible(false);
	    	rateTextField.clear();
	    	yesRadioBtn.selectedProperty().set(false);
	    	yesRadioBtn.setDisable(false);
	    	noRadioBtn.selectedProperty().set(false);
	    	noRadioBtn.setDisable(false);
	    	for(CheckBox cb: allGenres) {
	    		cb.setSelected(false);
	    	}
	    	movieWarningLabel.setVisible(false);
	    	movieComboBox.valueProperty().set(null);
	    	datePicker.setValue(null);
	    	timeComboBox.valueProperty().set(null);
	    	linkTextField.clear();
	    	viewingPackageWarningLabel.setVisible(false);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Subscribe 
    public void onMessageEvent(Message msg) throws IOException{
    	if(msg.getAction().equals("got all valid for viewing package movies")) {
    		Platform.runLater(()-> {
    			try {
    			if(isRegistered) {
        			EventBus.getDefault().unregister(this);
        			isRegistered = false;
    			}
    			moviesForViewingPackage = msg.getMovies();
					setMovies();
				} catch (Exception e) {
					e.printStackTrace();
				}
    		});
    		
    	}   
    	if(msg.getAction().equals("added movie")) {
    		try {
    		Platform.runLater(()-> {
    			if(isRegistered) {
        			EventBus.getDefault().unregister(this);
        			isRegistered = false;
    			}
    			clearForm();
    			JOptionPane.showMessageDialog(null, "Movie added successfully");
    		});
    		} catch (Exception e) {
				e.printStackTrace();
			}
    	}   
    	if(msg.getAction().equals("added viewing package")) {
    		try {
    		Platform.runLater(()-> {
    			if(isRegistered) {
        			EventBus.getDefault().unregister(this);
        			isRegistered = false;
    			}
    			clearForm();
    			if(msg.getViewingPackage() != null)
    				JOptionPane.showMessageDialog(null, "Viewing package added successfully");
    			else
    				JOptionPane.showMessageDialog(null, "Viewing package already exists");
    		});
    		} catch (Exception e) {
				e.printStackTrace();
			}
    	} 	
    }
}
