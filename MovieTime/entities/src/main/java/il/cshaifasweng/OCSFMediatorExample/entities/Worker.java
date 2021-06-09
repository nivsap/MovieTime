package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "Worker")
public class Worker implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private boolean isLoggedIn;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;
//	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,mappedBy = "worker")
//	private List<PriceRequest> priceRequests;

	public Worker(String firstName, String lastName, String userName, String password, Cinema cinema, boolean isLoggedIn) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.cinema = cinema;
		this.isLoggedIn = isLoggedIn;
		//this.priceRequests = new ArrayList<>();
	}
	public Worker() {}

//	public List<PriceRequest> getPriceRequests() {
//		return priceRequests;
//	}
//	public void setPriceRequests(List<PriceRequest> priceRequests) {
//		this.priceRequests = priceRequests;
//	}
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
