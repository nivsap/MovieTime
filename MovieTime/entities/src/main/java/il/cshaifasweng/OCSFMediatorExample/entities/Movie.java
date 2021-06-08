package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import javafx.scene.image.Image;
@Entity
@Table(name = "Movies")
public class Movie implements  Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L;
	private String Name;
    private String duration;
    private Double popular;
    private String genre;
    @Lob
    private byte[] image;
    @Lob
    private byte[] largeImage;
    private ArrayList<String> MovieBeginingTime;
    private boolean streamOnline;
    private boolean soonInCinema;
    private String  description;
    private String mainActors;
    private int priceMovie;
    private String producersMovie;
    private LocalDateTime launchDate;
    private boolean isDeleted;
    private boolean isScreening;
  //  @OneToMany(mappedBy = "movies", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   // private List<ViewingPackage> viewingPackages;
//    @OneToOne(mappedBy = "movie", cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
//	private Screening screening;
    //TODO ending time, duration = ending time - starting time


    public String getDuration() {
        return duration;
    }

    public Movie(String name, String duration, Double popular, String genre, String imageSrc, String largeImageSrc,
			ArrayList<String> movie_Begining_Time, boolean streamOnline, boolean soonInCinema, String description,
			String mainActors, LocalDateTime launchDate ,int priceMovie, String producersMovie,Screening screening, boolean isDeleted , List<ViewingPackage> viewingPackages,boolean isScreening) throws IOException {
		Name = name;
		this.duration = duration;
		this.popular = popular;
		this.genre = genre;
		this.MovieBeginingTime = new ArrayList<>();
		this.streamOnline = streamOnline;
		this.soonInCinema = soonInCinema;
		this.description = description;
		this.mainActors = mainActors;
		this.launchDate = launchDate;
		this.priceMovie = priceMovie;
		this.producersMovie = producersMovie;
		this.isDeleted = isDeleted;
		this.isScreening = isScreening;
		setImage(imageSrc);
		setLargeImage(largeImageSrc);
	}
  
	public boolean isScreening() {
		return isScreening;
	}

	public void setScreening(boolean isScreening) {
		this.isScreening = isScreening;
	}

	public boolean isDeleted() {
		return this.isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Movie() {
		super();
	}
	
	public int getPriceMovie() {
		return priceMovie;
	}

	public void setPriceMovie(int priceMovie) {
		this.priceMovie = priceMovie;
	}

	public String getProducersMovie() {
		return producersMovie;
	}

	public void setProducersMovie(String producersMovie) {
		this.producersMovie = producersMovie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public ArrayList<String> getMovieBeginingTime() {
		return MovieBeginingTime;
	}

	public void setMovieBeginingTime(ArrayList<String> movieBeginingTime) {
		MovieBeginingTime = movieBeginingTime;
	}

	public boolean isStreamOnline() {
		return streamOnline;
	}

	public void setStreamOnline(boolean streamOnline) {
		this.streamOnline = streamOnline;
	}

	public boolean isSoonInCinema() {
		return soonInCinema;
	}

	public void setSoonInCinema(boolean soonInCinema) {
		this.soonInCinema = soonInCinema;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMainActors() {
		return mainActors;
	}

	public void setMainActors(String mainActors) {
		this.mainActors = mainActors;
	}

	public LocalDateTime getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(LocalDateTime launchDate) {
		this.launchDate = launchDate;
	}

	public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getPopular() {
        return popular;
    }

    public void setPopular(Double popular) {
        this.popular = popular;
    }
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
    
	public Image getImage() {
		if(image == null)
			return null;
		Image img = new Image(new ByteArrayInputStream(image));
		return img;
	}

	public void setImage(String imageSrc) throws IOException {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((MovieBeginingTime == null) ? 0 : MovieBeginingTime.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((largeImage == null) ? 0 : largeImage.hashCode());
		result = prime * result + ((launchDate == null) ? 0 : launchDate.hashCode());
		result = prime * result + ((mainActors == null) ? 0 : mainActors.hashCode());
		result = prime * result + ((popular == null) ? 0 : popular.hashCode());
		result = prime * result + (soonInCinema ? 1231 : 1237);
		result = prime * result + (streamOnline ? 1231 : 1237);
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
		if (MovieBeginingTime == null) {
			if (other.MovieBeginingTime != null)
				return false;
		} else if (!MovieBeginingTime.equals(other.MovieBeginingTime))
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
		if (mainActors == null) {
			if (other.mainActors != null)
				return false;
		} else if (!mainActors.equals(other.mainActors))
			return false;
		if (popular == null) {
			if (other.popular != null)
				return false;
		} else if (!popular.equals(other.popular))
			return false;
		if (soonInCinema != other.soonInCinema)
			return false;
		if (streamOnline != other.streamOnline)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [Name=" + Name + ", duration=" + duration + ", popular=" + popular + ", genre=" + genre
				+ ", MovieBeginingTime=" + MovieBeginingTime + ", streamOnline=" + streamOnline + ", soonInCinema=" + soonInCinema
				+ ", description=" + description + ", mainActors=" + mainActors + ", launchDate=" + launchDate + "]";
	}
}
