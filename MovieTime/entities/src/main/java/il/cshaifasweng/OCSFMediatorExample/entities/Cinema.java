package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Cinema")
public class Cinema implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L
	
	private String name;
	private String address;
	private int nextHallId;
	//@Column(name ="moviePrice")
	@Column(name="movie_price")
	private double moviePrice;
	private double linkPrice;
	private double subscriptionPrice;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private BranchManager manager;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "cinema" )
	private List<Screening> screeningArray;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "cinema")
	private List<Hall> hallArray;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "cinema")
	private List<Worker> workerArray;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "cinema")
	private List<Purchase> purchases;
	//private int nextHallId;
	
	public Cinema() {}
	
	public Cinema(String name, String address, BranchManager manager, ArrayList<Screening> screeningArray,
			ArrayList<Hall> hallArray, ArrayList<Worker> workerArray , ArrayList<Purchase> purchases, double moviePrice,double linkPrice, double rate) {
		super();
		//nextHallId = 1;
		this.name = name;
		this.address = address;
		this.manager = manager;
		this.screeningArray = new ArrayList<>();
		this.hallArray = new ArrayList<>();
		this.workerArray = new ArrayList<>();
		this.purchases = new ArrayList<>();
		this.moviePrice = moviePrice;
		nextHallId = 1;
		this.setLinkPrice(linkPrice);
		this.setSubscriptionPrice(20 * moviePrice * rate);
		
	}


	public String getName() {
		return name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Purchase> getCustomers() {
		return purchases;
	}

	public void setCustomers(List<Purchase> customers) {
		this.purchases = customers;
	}

	public BranchManager getManager() {
		return manager;
	}


	public void setManager(BranchManager manager) {
		this.manager = manager;
	}


	public List<Worker> getWorkerArray() {
		return workerArray;
	}


	public void setWorkerArray(List<Worker> workerArray) {
		this.workerArray = workerArray;
	}


	public void setScreeningArray(List<Screening> screeningArray) {
		this.screeningArray = screeningArray;
	}


	public void setHallArray(List<Hall> hallArray) {
		this.hallArray = hallArray;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


/*	public BranchManager getManager() {
		return manager;
	}


	public void setManager(BranchManager manager) {
		this.manager = manager;
	}*/


	public List<Screening> getScreeningArray() {
		return screeningArray;
	}


	public void setScreeningArray(ArrayList<Screening> screeningArray) {
		this.screeningArray = screeningArray;
	}


	public List<Hall> getHallArray() {
		return hallArray;
	}


	public void setHallArray(ArrayList<Hall> hallArray) {
		this.hallArray = hallArray;
		
		  for(Hall hall : hallArray)
		  {
			  hall.setHallId(nextHallId); 
			  nextHallId++;
		  }
		 
	}

	public double getMoviePrice() {
		return moviePrice;
	}

	public void setMoviePrice(double moviePrice) {
		this.moviePrice = moviePrice;
	}

	public double getLinkPrice() {
		return linkPrice;
	}

	public void setLinkPrice(double linkPrice) {
		this.linkPrice = linkPrice;
	}

	public double getSubscriptionPrice() {
		return subscriptionPrice;
	}

	public void setSubscriptionPrice(double subscriptionPrice) {
		this.subscriptionPrice = subscriptionPrice;
	}

	


//	public List<Worker> getWorkerArray() {
//		return workerArray;
//	}
//
//
//	public void setWorkerArray(ArrayList<Worker> workerArray) {
//		this.workerArray = workerArray;
//	}
//	
	
	
	


}
