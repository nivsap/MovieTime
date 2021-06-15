 	 package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FilePickerController {
	String fileLocation;
	File loadedFile;
	FileInputStream binaryFile;
	
    @FXML
    private Button uploadFileBtn;

    @FXML
    void openFileBrowser() throws FileNotFoundException {
    	loadedFile = App.openFilePicker();
    	if(loadedFile != null) {
    		binaryFile = new FileInputStream(loadedFile);
    	}
    }
    
    public File getLoadedFile() {
    	if(loadedFile != null) {
    		return loadedFile;
    	}
    	return null;
    }
    
    public String getLoadedFileString() {
    	if(loadedFile != null) {
    		return loadedFile.toString();
    	}
    	return "";
    }

    public FileInputStream getLoadedBinaryFile() {
    	return binaryFile;
    }
}
