package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
@Entity
@Table(name = "Screening")
public class Screening implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L;
	private Date date_screen;
	private LocalTime time_screen;
	private Hall hall;
	private Movie movie;
	public Date getDate_screen() {
		return date_screen;
	}
	public void setDate_screen(Date date_screen) {
		this.date_screen = date_screen;
	}
	public LocalTime getTime_screen() {
		return time_screen;
	}
	public void setTime_screen(LocalTime time_screen) {
		this.time_screen = time_screen;
	}
	public Hall getHall() {
		return hall;
	}
	public void setHall(Hall hall) {
		this.hall = hall;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}


}
