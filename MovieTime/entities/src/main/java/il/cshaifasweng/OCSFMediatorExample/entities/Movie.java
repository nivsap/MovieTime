package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Movies")
public class Movie implements  Serializable
{

    /**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L;
	private String Name;
    private String duration;
    private Double popular;
    private String genre;
    private String imageSrc;
    private String largeImageSrc;
    private ArrayList<String> MovieBeginingTime;
    private boolean streamOnline;
    private boolean soonInCinema;
    private String  description;
    private String mainActors;
    private Date launchDate;
    //TODO ending time, duration = ending time - starting time


    public String getDuration() {
        return duration;
    }

    public Movie(String name, String duration, Double popular, String genre, String imageSrc, String largeImageSrc,
			ArrayList<String> movie_Begining_Time, boolean streamOnline, boolean soonInCinema, String description,
			String mainActors, Date launchDate) {
		super();
		Name = name;
		this.duration = duration;
		this.popular = popular;
		this.genre = genre;
		this.imageSrc = imageSrc;
		this.largeImageSrc = largeImageSrc;
		this.MovieBeginingTime = new ArrayList<>();
		this.streamOnline = streamOnline;
		this.soonInCinema = soonInCinema;
		this.description = description;
		this.mainActors = mainActors;
		this.launchDate = launchDate;
	}
    


	public Movie(String name, String imageSrc, String duration, Double popular, String genre) {

		super();
		Name = name;
		this.duration = duration;
		this.imageSrc = imageSrc;
		this.popular = popular;
		this.genre = genre;
	}
	
	public Movie() {
		super();
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

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
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
    
	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	public String getLargeImageSrc() {
		return largeImageSrc;
	}

	public void setLargeImageSrc(String largeImageSrc) {
		this.largeImageSrc = largeImageSrc;
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
		result = prime * result + ((imageSrc == null) ? 0 : imageSrc.hashCode());
		result = prime * result + ((largeImageSrc == null) ? 0 : largeImageSrc.hashCode());
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
		if (imageSrc == null) {
			if (other.imageSrc != null)
				return false;
		} else if (!imageSrc.equals(other.imageSrc))
			return false;
		if (largeImageSrc == null) {
			if (other.largeImageSrc != null)
				return false;
		} else if (!largeImageSrc.equals(other.largeImageSrc))
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
				+ ", imageSrc=" + imageSrc + ", largeImageSrc=" + largeImageSrc + ", MovieBeginingTime=" 
				+ MovieBeginingTime + ", streamOnline=" + streamOnline + ", soonInCinema=" + soonInCinema
				+ ", description=" + description + ", mainActors=" + mainActors + ", launchDate=" + launchDate + "]";
	}
	//not needed for now

//to compare our obj






}
