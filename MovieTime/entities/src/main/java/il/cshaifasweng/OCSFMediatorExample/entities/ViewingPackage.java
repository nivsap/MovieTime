package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name = "ViewingPackages")
public class ViewingPackage implements  Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "movies_id")
	private Movie movie;
	private String link;
	private LocalDateTime startDateTime;
	private Boolean isDeleted;
	
	public ViewingPackage() {
		super();
		this.isDeleted = false;
	}
	
	public ViewingPackage(Movie movie, LocalDateTime dateTime, String link) {
		super();
		this.link = link;
		this.movie = movie;
		this.startDateTime = dateTime;
		this.isDeleted = false;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public LocalDateTime getDateTime() {
		return startDateTime;
	}
	
	public void setDateTime(LocalDateTime dateTime) {
		this.startDateTime = dateTime;
	}

	public Boolean isDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
