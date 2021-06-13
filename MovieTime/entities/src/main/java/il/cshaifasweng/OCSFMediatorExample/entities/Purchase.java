package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javafx.util.Pair;


@Entity
@Table(name = "Purchase")
public class Purchase implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// Purchaser info
	private String firstName;
	private String lastName;
	private String email;
	private String city;
	private String phone;
	private double payment;
	// Purchase info
	private LocalDateTime purchaseTime;
	private int purchaseType; // 0 - ticket, 1 - link, 2 - card, 3 - unknown
	private Pair<Boolean, Float> isCanceled;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "screening_id")
	private Screening screening;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hall_id")
	private Hall hall;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;
	
	@Lob
	private ArrayList<Pair<Integer , Integer>> seatsList;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "viewing_package_id")
	private ViewingPackage viewingPackage;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private SubscriptionCard subscriptionCard;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Complaint complaint;

	public Purchase() {
		super();
	}
	
	public Purchase(String firstName, String lastName, String email, String city, String phone,
					double payment, LocalDateTime purchaseTime, Screening screening, ArrayList<Pair<Integer , Integer>> seatsList, 
					Complaint complaint) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.phone = phone;
		this.payment = payment;
		this.purchaseTime = purchaseTime;
		this.purchaseType = 1;
		this.isCanceled = new Pair<Boolean, Float>(false, 0f);
		this.screening = screening;
		if(screening != null) {
			this.hall = screening.getHall();
			this.cinema = screening.getCinema();
		}
		else {
			this.hall = null;
			this.cinema = null;
		}
		this.seatsList = seatsList;
		this.viewingPackage = null;
		this.subscriptionCard = null;
		this.complaint = complaint;	
	}
	
	public Purchase(String firstName, String lastName, String email, String city, String phone,
					double payment, LocalDateTime purchaseTime, Cinema cinema,
					ViewingPackage viewingPackage, Complaint complaint) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.phone = phone;
		this.payment = payment;
		this.purchaseTime = purchaseTime;
		this.purchaseType = 2;
		this.isCanceled = new Pair<Boolean, Float>(false, 0f);
		this.screening = null;
		this.hall = null;
		this.cinema = cinema;
		this.seatsList = null;
		this.viewingPackage = viewingPackage;
		this.subscriptionCard = null;
		this.complaint = complaint;	
	}
	
	public Purchase(String firstName, String lastName, String email, String city, String phone,
			double payment, LocalDateTime purchaseTime, Cinema cinema,
			SubscriptionCard subscriptionCard, Complaint complaint) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.phone = phone;
		this.payment = payment;
		this.purchaseTime = purchaseTime;
		this.purchaseType = 3;
		this.isCanceled = new Pair<Boolean, Float>(false, 0f);
		this.screening = null;
		this.hall = null;
		this.cinema = cinema;
		this.seatsList = null;
		this.viewingPackage = null;
		this.subscriptionCard = subscriptionCard;
		this.complaint = complaint;	
	}
	
	public int getId() {
		return id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public LocalDateTime getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(LocalDateTime purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	
	public int getPurchaseType() {
		return purchaseType;
	}
	
	public void setPurchaseType(int purchaseType) {
		this.purchaseType = purchaseType;
	}
	
	public Boolean isCanceled() {
		return isCanceled.getKey();
	}

	public Pair<Boolean, Float> getIsCanceled() {
		return isCanceled;
	}

	public void setIsCanceled(Pair<Boolean, Float> isCanceled) {
		if(purchaseType != 2)
			this.isCanceled = isCanceled;
	}
	
	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}
	
	public Cinema getCinema() {
		return cinema;
	}
	
	public List<Pair<Integer, Integer>> getSeatsList() {
		return seatsList;
	}

	public void setSeatsList(ArrayList<Pair<Integer, Integer>> seatsList) {
		this.seatsList = seatsList;
	}
	
	public ViewingPackage getViewingPackage() {
		return viewingPackage;
	}

	public void setViewingPackage(ViewingPackage viewingPackage) {
		this.viewingPackage = viewingPackage;
	}
	
	public SubscriptionCard getSubscriptionCard() {
		return subscriptionCard;
	}

	public void setSubscriptionCard(SubscriptionCard subscriptionCard) {
		this.subscriptionCard = subscriptionCard;
	}
	
	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	
	public void setTypeTicket() {
		purchaseType = 1;
	}
	
	public void setTypeLink() {
		purchaseType = 2;
	}
	
	public void setTypeCard() {
		purchaseType = 3;
	}
	
	public Boolean isTicket() {
		if(purchaseType == 1)
			return true;
		return false;
	}
	
	public Boolean isLink() {
		if(purchaseType == 2)
			return true;
		return false;
	}
	
	public Boolean isCard() {
		if(purchaseType == 3)
			return true;
		return false;
	}
}
