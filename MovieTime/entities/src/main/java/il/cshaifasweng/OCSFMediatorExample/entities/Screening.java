package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Screening")
public class Screening implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L;
	private LocalDateTime date_screen;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hall_id")
	private Hall hall;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Movie movie;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
	private Cinema cinema;
	
	
	public Screening(LocalDateTime date_screen, Hall hall, Movie movie, Cinema cinema) {
		super();
		this.date_screen = date_screen;
		this.hall = hall;
		this.movie = movie;
		this.cinema = cinema;
	}
	public Screening() {}
	
	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public LocalDateTime getDate_screen() {
		return date_screen;
	}
	public void setDate_screen(LocalDateTime date_screen) {
		this.date_screen = date_screen;
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