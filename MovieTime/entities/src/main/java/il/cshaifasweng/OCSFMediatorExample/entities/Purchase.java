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
	//private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String emailOrder;
	private String cityString;
	private String phoneString;
	private Pair<Boolean , Integer> cinemaTab;
	private boolean watchFromHome;
	private LocalDateTime purchaseDate;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hall_id")
	private Hall hall;
//	private List<Pair<Integer , Integer>> sitsList;
	private double payment;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Complaint complaint;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "screening_id")
	private Screening screening;
	private boolean isCanceled;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "viewingPackage_id")
	private ViewingPackage viewingPackage;


	public Purchase(String firstName, String lastName, String emailOrder, String cityString, String phoneString,
			Pair<Boolean, Integer> cinemaTab , boolean watchFromHome , LocalDateTime purchaseDate,Cinema cinema , Hall hall ,List<Pair<Integer , Integer>> sitsList ,double payment,Complaint complaint, Screening screening,boolean isCanceled,ViewingPackage viewingPackage) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailOrder = emailOrder;
		this.cityString = cityString;
		this.phoneString = phoneString;
		this.cinemaTab = cinemaTab;
		this.watchFromHome = watchFromHome;
		this.purchaseDate = purchaseDate;
		this.cinema = cinema;
		this.hall = hall;
		//this.sitsList = new ArrayList<>();
		this.payment = payment;
		this.complaint = complaint;
		this.screening = screening;
		this.isCanceled = isCanceled;
		this.viewingPackage = viewingPackage;
	}
	public Purchase() {}
	public ViewingPackage getViewingPackage() {
		return viewingPackage;
	}
	public void setViewingPackage(ViewingPackage viewingPackage) {
		this.viewingPackage = viewingPackage;
	}
	public boolean isCanceled() {
		return isCanceled;
	}
	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}
	public Screening getScreening() {
		return screening;
	}
	public void setScreening(Screening screening) {
		this.screening = screening;
	}
	public Complaint getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public Hall getHall() {
		return hall;
	}
	public void setHall(Hall hall) {
		this.hall = hall;
	}
//	public List<Pair<Integer, Integer>> getSitsList() {
//		return sitsList;
//	}
//	public void setSitsList(List<Pair<Integer, Integer>> sitsList) {
//		this.sitsList = sitsList;
//	}
	public boolean isWatchFromHome() {
		return watchFromHome;
	}
	public void setWatchFromHome(boolean watchFromHome) {
		this.watchFromHome = watchFromHome;
	}
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
