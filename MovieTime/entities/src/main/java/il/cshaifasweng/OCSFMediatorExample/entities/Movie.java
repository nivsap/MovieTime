package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javafx.scene.image.Image;

@Entity
@Table(name = "Movies")
public class Movie implements  Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Name;
    private String genre;
    private String description;
    private String producers;
    private String mainActors;
    private LocalDate launchDate;
    private String duration;
    private Float rate;
    @Lob
    private byte[] image;
    @Lob
    private byte[] largeImage;
    private boolean isComingSoon;
    private boolean isDeleted;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "movie")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Screening> screenings;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "movie")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ViewingPackage> viewingPackages;

     /*  For easier copy-paste:
		 this.Name;
	     this.genre;
	     this.description;
	     this.producers;
	     this.mainActors;
	     this.launchDate;
	     this.duration;
	     this.rate;
	     this.image;
	     this.largeImage;
	     this.isComingSoon;
	     this.isDeleted;
		 this.viewingPackages;
	 */
	
	public Movie() {
		super();
	}

    public Movie(String name, String genre, String description, String producers, String mainActors,
    			 LocalDate launchDate, int durationHours, int durationMinutes, Float rate, byte[] image, byte[] largeImage,
    			 boolean isComingSoon, boolean isDeleted, ArrayList<Screening> screenings, ArrayList<ViewingPackage> viewingPackages) throws IOException {
	   	 this.Name = name;
	     this.genre = genre;
	     this.description = description;
	     this.producers = producers;
	     this.mainActors = mainActors;
	     this.launchDate = launchDate;
	     this.duration = durationHours + "h " + durationMinutes + "min";
	     this.rate = rate;
	     this.image = image;
	     this.largeImage = largeImage;
	     this.isDeleted = isDeleted;
	     this.screenings = screenings;
	     if(screenings == null) {
	    	 screenings = new ArrayList<Screening>();
	     }
		 this.viewingPackages = viewingPackages;
		 if(viewingPackages == null) {
			 viewingPackages = new ArrayList<ViewingPackage>();
		 }
	   	 if(launchDate.isAfter(LocalDate.now())) {
	   		 this.rate = 0f;
	   		 this.isComingSoon = true;
	   	 }
	   	 else {
	   		this.rate = rate;
	   		this.isComingSoon = isComingSoon;
	   	 }
	}
    
    public Movie(String name, String genre, String description, String producers, String mainActors,
    			 LocalDate launchDate, int durationHours, int durationMinutes, Float rate, String image, String largeImage,
    			 boolean isComingSoon, boolean isDeleted, ArrayList<Screening> screenings, ArrayList<ViewingPackage> viewingPackages) throws IOException {
	   	 this.Name = name;
	     this.genre = genre;
	     this.description = description;
	     this.producers = producers;
	     this.mainActors = mainActors;
	     this.launchDate = launchDate;
	     this.duration = durationHours + "h " + durationMinutes + "min";
	     this.rate = rate;
	     setImage(image);
	     setLargeImage(largeImage);
	     this.isDeleted = isDeleted;
	     if(screenings == null) {
	    	 screenings = new ArrayList<Screening>();
	     }
		 this.viewingPackages = viewingPackages;
		 if(viewingPackages == null) {
			 viewingPackages = new ArrayList<ViewingPackage>();
		 }
	   	 if(launchDate.isAfter(LocalDate.now())) {
	   		 this.rate = 0f;
	   		 this.isComingSoon = true;
	   	 }
	   	 else {
	   		this.rate = rate;
	   		this.isComingSoon = isComingSoon;
	   	 }
	}
    
    public int getId() {
		return id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		if(!isDeleted)
			this.Name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		if(!isDeleted)
			this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(!isDeleted)
			this.description = description;
	}

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		if(!isDeleted)
			this.producers = producers;
	}

	public String getMainActors() {
		return mainActors;
	}

	public void setMainActors(String mainActors) {
		if(!isDeleted)
			this.mainActors = mainActors;
	}

	public LocalDate getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(LocalDate launchDate) {
		if(!isDeleted)
			this.launchDate = launchDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		if(!isDeleted)
			this.duration = duration;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		if(!isDeleted) {
			if(launchDate.isAfter(LocalDate.now())) 
				this.rate = 0f;
		   	else 
		   		this.rate = rate;
		}
	}
    
	public Image getImage() {
		if(image == null)
			return null;
		Image img = new Image(new ByteArrayInputStream(image));
		return img;
	}

	public void setImage(String imageSrc) throws IOException {
		if(isDeleted)
			return;
		
		if(imageSrc == null || imageSrc.equals("")) {
			this.image = null;
			return;
		}
		
		File imageFile = new File(imageSrc);
        byte[] binaryImage = new byte[(int) imageFile.length()];
        try {
        	FileInputStream inputStream = new FileInputStream(imageFile);
        	inputStream.read(binaryImage);
        	inputStream.close();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        	this.image = null;
        	return;
        }
        this.image = binaryImage;
	}
	
	public Image getLargeImage() {
		if(largeImage == null) 
			return null;
		Image img = new Image(new ByteArrayInputStream(largeImage));
		return img;
	}

	public void setLargeImage(String largeImageSrc) throws IOException {
		if(isDeleted)
			return;
		
		if(largeImageSrc == null || largeImageSrc.equals("")) {
			this.largeImage = null;
			return;
		}
		
		File imageFile = new File(largeImageSrc);
        byte[] binaryImage = new byte[(int) imageFile.length()];
        try {
        	FileInputStream inputStream = new FileInputStream(imageFile);
        	inputStream.read(binaryImage);
        	inputStream.close();
        } 
        catch (Exception e) {
        	e.printStackTrace();
        	this.largeImage = null;
        	return;
        }
        this.largeImage = binaryImage;
	}
	
	public boolean isComingSoon() {
		return isComingSoon;
	}

	public void setComingSoon(boolean isComingSoon) {
		if(!isDeleted) {
		   	if(launchDate.isAfter(LocalDate.now())) 
		   		this.isComingSoon = true;
		   	else 
		   		this.isComingSoon = isComingSoon;
		}
	}
	
	public void setIsComingSoon(boolean isComingSoon) {
		isComingSoon = true;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public List<Screening> getScreenings() {
		if(!isComingSoon)
			return screenings;
		return null;
	}

	public void setScreenings(ArrayList<Screening> screenings) {
		if(!isDeleted && !isComingSoon)
			this.screenings = screenings;
	}
	
	public Boolean isScreening() {
		if(!isDeleted && !isComingSoon && screenings != null && !screenings.isEmpty())
			return true;
		return false;
	}
	
	public List<ViewingPackage> getViewingPackages() {
		if(!isDeleted)
			return viewingPackages;
		return null;
	}

	public void setViewingPackages(ArrayList<ViewingPackage> viewingPackages) {
		if(!isDeleted)
			this.viewingPackages = viewingPackages;
	}
	
	public Boolean isViewingPackage() {
		if(!isDeleted && viewingPackages != null && !viewingPackages.isEmpty())
			return true;
		return false;
	}
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((producers == null) ? 0 : producers.hashCode());
		result = prime * result + ((mainActors == null) ? 0 : mainActors.hashCode());
		result = prime * result + ((launchDate == null) ? 0 : launchDate.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((largeImage == null) ? 0 : largeImage.hashCode());
		result = prime * result + (isComingSoon ? 1231 : 1237);
		result = prime * result + (isDeleted ? 1231 : 1237);
		result = prime * result + ((viewingPackages == null) ? 0 : viewingPackages.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id != other.id)
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (largeImage == null) {
			if (other.largeImage != null)
				return false;
		} else if (!largeImage.equals(other.largeImage))
			return false;
		if (launchDate == null) {
			if (other.launchDate != null)
				return false;
		} else if (!launchDate.equals(other.launchDate))
			return false;
		if (producers == null) {
			if (other.producers != null)
				return false;
		} else if (!producers.equals(other.producers))
		if (mainActors == null) {
			if (other.mainActors != null)
				return false;
		} else if (!mainActors.equals(other.mainActors))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		if (isComingSoon != other.isComingSoon)
			return false;
		if (isDeleted != other.isDeleted)
			return false;
		if (viewingPackages == null) {
			if (other.viewingPackages != null)
				return false;
		} else if (!viewingPackages.equals(other.viewingPackages))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "movie_" + id + 
				"[name: " + Name + 
				", genre: " + genre + 
				", description: " + description +
				", producers: " + producers +
				", mainActors: " + mainActors +
				", launchDate: " + launchDate +
				", duration: " + duration + 
				", rate: " + rate + 
				", isComingSoon: " + isComingSoon +
				", isDeleted: " + isDeleted +
				", viewingPackages:\n" + viewingPackages +"]\n";
	}
}
