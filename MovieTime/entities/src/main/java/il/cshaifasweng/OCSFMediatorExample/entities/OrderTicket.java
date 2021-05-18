package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
@Entity
@Table(name = "OrderTicket")
public class OrderTicket implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L;
	private int number_of_tickets;
	private Screening screen;
	private Cinema cinema;
	private int sit[];
	private String results[];
	public int getNumber_of_tickets() {
		return number_of_tickets;
	}
	public void setNumber_of_tickets(int number_of_tickets) {
		this.number_of_tickets = number_of_tickets;
	}
	public Screening getScreen() {
		return screen;
	}
	public void setScreen(Screening screen) {
		this.screen = screen;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public int[] getSit() {
		return sit;
	}
	public void setSit(int sit[]) {
		this.sit = sit;
	}
	public String[] getResults() {
		return results;
	}
	public void setResults(String results[]) {
		this.results = results;
	}
	
	

}
