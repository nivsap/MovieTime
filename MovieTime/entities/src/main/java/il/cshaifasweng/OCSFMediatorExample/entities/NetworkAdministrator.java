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
	public static Float moviePrice;
	public static Float viewingPackagePrice;
	public static Float subscriptionCardPrice;
	
	public NetworkAdministrator() {}

	public NetworkAdministrator(String firstName, String lastName, Cinema cinema, String userName, String password, boolean isLoggedIn, Float movieP, Float viewingPackageP, Float subscriptionCardP) {
		super(firstName, lastName, cinema, userName, password, isLoggedIn);
		moviePrice = movieP;
		viewingPackagePrice = viewingPackageP;
		subscriptionCardPrice = subscriptionCardP;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public static Float getMoviePrice() {
		return moviePrice;
	}

	public static void setMoviePrice(Float newPrice) {
		moviePrice = newPrice;
	}

	public static Float getViewingPackagePrice() {
		return viewingPackagePrice;
	}

	public static void setViewingPackagePrice(Float newPrice) {
		viewingPackagePrice = newPrice;
	}

	public static Float getSubscriptionCardPrice() {
		return subscriptionCardPrice;
	}

	public static void setSubscriptionCardPrice(Float newPrice) {
		subscriptionCardPrice = newPrice;
	}
}
