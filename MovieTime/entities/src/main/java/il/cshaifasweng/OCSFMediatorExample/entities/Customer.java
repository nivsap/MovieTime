package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import javafx.util.Pair;


@Entity
@Table(name = "OrderTicket")
public class Customer implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String emailOrder;
	private String cityString;
	private String phoneString;
	private Pair<Boolean , Integer> cinemaTab;
	

	public Customer(String firstName, String lastName, String emailOrder, String cityString, String phoneString,
			Pair<Boolean, Integer> cinemaTab) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailOrder = emailOrder;
		this.cityString = cityString;
		this.phoneString = phoneString;
		this.cinemaTab = cinemaTab;
	}
	public Customer() {}
	
	public Pair<Boolean, Integer> getCinemaTab() {
		return cinemaTab;
	}
	public void setCinemaTab(Pair<Boolean, Integer> cinemaTab) {
		this.cinemaTab = cinemaTab;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailOrder() {
		return emailOrder;
	}
	public void setEmailOrder(String emailOrder) {
		this.emailOrder = emailOrder;
	}
	public String getCityString() {
		return cityString;
	}
	public void setCityString(String cityString) {
		this.cityString = cityString;
	}
	public String getPhoneString() {
		return phoneString;
	}
	public void setPhoneString(String phoneString) {
		this.phoneString = phoneString;
	}

}
