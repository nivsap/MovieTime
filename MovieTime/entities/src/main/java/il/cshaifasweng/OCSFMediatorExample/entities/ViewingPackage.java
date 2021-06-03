package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "ViewingPackage")
public class ViewingPackage implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "movies_id")
	private Movie movie;
	//@Column (name = "link string")
	private String link;
	private LocalDateTime startDateTime;
	private ArrayList<LocalDateTime> datesAvailables;
	
	
	public ViewingPackage(Movie movie, LocalDateTime dateTime, ArrayList<LocalDateTime> datesAvailables,String link) {
		super();
	//	this.link = "www.sirtiya-" + this.movie.getName() + "." + this.id + "co.il";
		this.link = link;
		this.movie = movie;
		this.startDateTime = dateTime;
		this.datesAvailables = new ArrayList<>();
	}
	public ViewingPackage() {}
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
	



}
