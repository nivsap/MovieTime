package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Screening")
public class Screening implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L;
	//@Column(name="seats",columnDefinition="LONGTEXT")
	private int seats[][];
	private LocalDateTime date_screen;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "hall_id")
	private Hall hall;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Movie movie;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
	private Cinema cinema;
	
	
	public Screening(LocalDateTime date_screen, Hall hall, Movie movie, Cinema cinema) {
		super();
		this.date_screen = date_screen;
		this.hall = hall;
		this.movie = movie;
		this.cinema = cinema;
		this.seats = new int[hall.getRows()][hall.getCols()];
		for(int i = 0 ; i < hall.getRows(); i++) {
			for(int j = 0 ; j < hall.getCols() ; j++) {
				seats[i][j] = 0;
			}
		}
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
	public int[][] getSeats() {
		return seats;
	}
	public void setSeats(int[][] seats) {
		this.seats = seats;
	}
	
	public int getId() {
		return id;
	}


}
