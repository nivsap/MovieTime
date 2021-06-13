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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Cinemas")
public class Cinema implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private BranchManager manager;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "cinema")
	private List<Worker> workers;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "cinema")
	private List<Hall> halls;
	private int nextHallNumber;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "cinema" )
	private List<Screening> screenings;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "cinema")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Purchase> purchases;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "cinema")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Purchase> canceledPurchases;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "cinema")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Complaint> complaints;
	
	public Cinema() {
		super();
		nextHallNumber = 1;
	}
	
	public Cinema(String name, String address, BranchManager manager,  ArrayList<Worker> workers,
				  ArrayList<Hall> halls, ArrayList<Screening> screenings,
				  ArrayList<Purchase> purchases, ArrayList<Purchase> canceledPurchases , ArrayList<Complaint> complaints) {
		super();
		this.name = name;
		this.address = address;
		this.manager = manager;
		this.screenings = new ArrayList<>();
		this.halls = new ArrayList<>();
		this.workers = new ArrayList<>();
		this.purchases = new ArrayList<>();
		this.canceledPurchases = canceledPurchases;
		this.complaints = new ArrayList<>();	
		nextHallNumber = 1;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
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
	
	public BranchManager getManager() {
		return manager;
	}

	public void setManager(BranchManager manager) {
		this.manager = manager;
	}
	
	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}
	
	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(ArrayList<Hall> halls) {
		this.halls = halls;
		if(halls != null)
			for(Hall hall : halls)
				hall.setHallId(nextHallNumber++); 
	}
	
	public void setScreenings(List<Screening> screenings) {
		this.screenings = screenings;
	}

	public List<Screening> getScreenings() {
		return screenings;
	}
	
	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public List<Purchase> getCanceledPurchases() {
		return canceledPurchases;
	}

	public void setCanceledPurchases(List<Purchase> canceledPurchases) {
		this.canceledPurchases = canceledPurchases;
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

}
