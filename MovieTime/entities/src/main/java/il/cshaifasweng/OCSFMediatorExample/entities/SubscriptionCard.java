package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SubscriptionCards")
public class SubscriptionCard implements  Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int remaining;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Purchase purchase;
	
	public SubscriptionCard() {
		super();
	}
	
	public SubscriptionCard(Purchase purchase) {
		setInitialRemaining();
		this.purchase = purchase;
	}
	
	public int getId() {
		return id;
	}
	
	public void setInitialRemaining() {
		remaining = 20;
	}
	
	public int getRemaining() {
		return remaining;
	}
	
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	
	public Purchase getPurchase() {
		return purchase;
	}
	
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	
	public String getEmail() {
		return purchase.getEmail();
	}
}
