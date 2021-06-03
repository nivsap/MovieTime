package il.cshaifasweng.OCSFMediatorExample.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "PriceRequest")
public class PriceRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate requestDate;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Cinema cinema;
	private boolean isMovie;
	private String commentString;
	private double newPrice;
	private boolean isOpen;
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "worker_id")
//	private Worker worker;
	public PriceRequest(LocalDate requestDate, Cinema cinema, boolean isMovie, String commentString,
			double newPrice, boolean isOpen) {
		super();
		this.requestDate = requestDate;
		this.cinema = cinema;
		this.isMovie = isMovie;
		this.commentString = commentString;
		this.newPrice = newPrice;
		this.isOpen = isOpen;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public boolean isMovie() {
		return isMovie;
	}
	public void setMovie(boolean isMovie) {
		this.isMovie = isMovie;
	}
	public String getCommentString() {
		return commentString;
	}
	public void setCommentString(String commentString) {
		this.commentString = commentString;
	}
	public double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	
	
}
