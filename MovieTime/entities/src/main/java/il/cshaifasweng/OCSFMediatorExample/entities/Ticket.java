package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javafx.util.Pair;
@Entity
@Table(name = "Tickets")
public class Ticket implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   // @JoinColumn(name = "customer_id")
	//private Customer orderTicket;
	private Pair<Boolean , Integer> cinemaTab;
	public Ticket(Purchase orderTicket, Pair<Boolean, Integer> cinemaTab) {
		super();
	//	this.orderTicket = orderTicket;
		this.cinemaTab = cinemaTab;
	}
	public Ticket() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public Customer getOrderTicket() {
//		return orderTicket;
//	}
//	public void setOrderTicket(Customer orderTicket) {
//		this.orderTicket = orderTicket;
//	}
	public Pair<Boolean, Integer> getCinemaTab() {
		return cinemaTab;
	}
	public void setCinemaTab(Pair<Boolean, Integer> cinemaTab) {
		this.cinemaTab = cinemaTab;
	}
	
}
