package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NetworkAdministrator")
public class NetworkAdministrator extends Worker implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public Float moviePrice;
	public Float viewingPackagePrice;
	public Float subscriptionCardPrice;
	
	public NetworkAdministrator() {}

	public NetworkAdministrator(String firstName, String lastName, Cinema cinema, String userName, String password, boolean isLoggedIn, Float moviePrice, Float viewingPackagePrice, Float subscriptionCardPrice) {
		super(firstName, lastName, cinema, userName, password, isLoggedIn);
		this.moviePrice = moviePrice;
		this.viewingPackagePrice = viewingPackagePrice;
		this.subscriptionCardPrice = subscriptionCardPrice;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Float getMoviePrice() {
		return moviePrice;
	}

	public void setMoviePrice(Float moviePrice) {
		this.moviePrice = moviePrice;
	}

	public Float getViewingPackagePrice() {
		return viewingPackagePrice;
	}

	public void setViewingPackagePrice(Float viewingPackagePrice) {
		this.viewingPackagePrice = viewingPackagePrice;
	}

	public Float getSubscriptionCardPrice() {
		return subscriptionCardPrice;
	}

	public void setSubscriptionCardPrice(Float subscriptionCardPrice) {
		this.subscriptionCardPrice = subscriptionCardPrice;
	}
}
