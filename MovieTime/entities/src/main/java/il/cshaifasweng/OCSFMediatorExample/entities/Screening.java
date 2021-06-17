package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Screenings")
public class Screening implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime date;
	@Lob
	private int seats[][];
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
	private Movie movie;	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "hall_id")
	private Hall hall;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
	private Cinema cinema;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "screening")
	private List<Purchase> purchases;
	
	public Screening() {
		super();
	}
	
	public Screening(LocalDateTime date, Movie movie, Hall hall, Cinema cinema, ArrayList<Purchase> purchases) {
		super();
		this.date = date;
		this.movie = movie;
		this.hall = hall;
		this.cinema = cinema;
		this.seats = new int[hall.getRows()][hall.getCols()];
		for(int i = 0 ; i < hall.getRows(); i++) {
			for(int j = 0 ; j < hall.getCols() ; j++) 
				this.seats[i][j] = 0;
		}
		this.purchases = purchases;
	}
	
	public void initSeats () {
		for(int i = 0 ; i < hall.getRows(); i++) {
			for(int j = 0 ; j < hall.getCols() ; j++) 
				this.seats[i][j] = 0;
		}
	}
	
	public int getId() {
		return id;
	}

	public LocalDate getDate() {
		return date.toLocalDate();
	}
	
	public LocalTime getTime() {
		return date.toLocalTime();
	}
	
	public LocalDateTime getDateAndTime() {
		return date;
	}
	
	public void setDateAndTime(LocalDateTime date) {
		this.date = date;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public Hall getHall() {
		return hall;
	}
	
	public void setHall(Hall hall) {
		this.hall = hall;
	}
	
	public int[][] getSeats() {
		return seats;
	}
	
	public void setSeats(int[][] seats) {
		this.seats = seats;
	}
	
	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(ArrayList<Purchase> purchases) {
		this.purchases = purchases;
	}
}
