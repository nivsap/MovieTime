package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Message implements Serializable {
	/* ---------- Message Necessary Info ---------- */
	private String action;
	private String DBaction;
	private String error;
	private int id;
	/* ---------- Handling Workers ---------- */
	private Worker worker;
	private String username;
	private String password;
	private String typeOfWorker;
	private NetworkAdministrator administrator;
	/* ---------- Handling Cinemas ---------- */
	private Cinema cinema;
	private int cinemaId;
	private String cinemaName;
	private ArrayList<Cinema> cinemas;
	/* ---------- Handling Movies ---------- */
	private Movie movie;
	private int movieId;
	private String movieName;
	private ArrayList<Movie> movies;
	/* ---------- Handling Filters ---------- */
	private String genre; 
	private String theater;
	private String rate;
	private String search;
	private String actionType;
	private ArrayList<String> genres;
	private String moviesType;
	/* ---------- Handling Screenings ---------- */
	private Screening screening;
	private LocalDateTime screeningDate;
	private int[][] seats;
	private ArrayList<Screening> screenings;
	/* ---------- Handling Halls ---------- */
	private Hall hall;
	private int hallId;
	/* ---------- Handling Viewing Packages ---------- */
	private ViewingPackage viewingPackage;
	private List<ViewingPackage> viewingPackages;
	/* ---------- Handling Subscription Cards ---------- */
	private SubscriptionCard subscriptionCard;
	/* ---------- Handling Purchases ---------- */
	private Purchase purchase;
	private float payment;
	private List<Purchase> purchases;
	/* ---------- Handling Complaints ---------- */
	private Complaint complaint;
	private ArrayList<Complaint> complaints;
	/* ---------- Handling Price Requests ---------- */
	private PriceRequest priceRequest;
	private float moviePrice;
	private float viewingPackagePrice;
	private float subscriptionCardPrice;
	private ArrayList<PriceRequest> priceRequests;
	/* ---------- Handling Purple Limits ---------- */
	private PurpleLimit purpleLimit;
	private int tavSagolLimit;
	private int numOfSeats;
	private boolean status;
	private ArrayList<PurpleLimit> activePurpleLimits;
	/* ---------- Handling Emails ---------- */
	private String emailMessage;
	private String customerEmail;

	public Message() {
		super();
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getDBAction() {
		return DBaction;
	}

	public void setDBAction(String dBaction) {
		DBaction = dBaction;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTypeOfWorker() {
		return typeOfWorker;
	}

	public void setTypeOfWorker(String typeOfWorker) {
		this.typeOfWorker = typeOfWorker;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public int getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}

	public void setCinemas(ArrayList<Cinema> cinemas) {
		this.cinemas = cinemas;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

	public String getMoviesType() {
		return moviesType;
	}

	public void setMoviesType(String moviesType) {
		this.moviesType = moviesType;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public ArrayList<String> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

	public LocalDateTime getScreeningDate() {
		return screeningDate;
	}

	public void setScreeningDate(LocalDateTime screeningDate) {
		this.screeningDate = screeningDate;
	}

	public int[][] getSeats() {
		return seats;
	}

	public void setSeats(int[][] seats) {
		this.seats = seats;
	}

	public ArrayList<Screening> getScreenings() {
		return screenings;
	}

	public void setScreenings(ArrayList<Screening> screenings) {
		this.screenings = screenings;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public ViewingPackage getViewingPackage() {
		return viewingPackage;
	}

	public void setViewingPackage(ViewingPackage viewingPackage) {
		this.viewingPackage = viewingPackage;
	}

	public List<ViewingPackage> getViewingPackages() {
		return viewingPackages;
	}

	public void setViewingPackages(List<ViewingPackage> viewingPackages) {
		this.viewingPackages = viewingPackages;
	}

	public SubscriptionCard getSubscriptionCard() {
		return subscriptionCard;
	}

	public void setSubscriptionCard(SubscriptionCard subscriptionCard) {
		this.subscriptionCard = subscriptionCard;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public float getPayment() {
		return payment;
	}

	public void setPayment(float payment) {
		this.payment = payment;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public ArrayList<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(ArrayList<Complaint> complaints) {
		this.complaints = complaints;
	}

	public PriceRequest getPriceRequest() {
		return priceRequest;
	}

	public void setPriceRequest(PriceRequest priceRequest) {
		this.priceRequest = priceRequest;
	}
	
	public float getMoviePrice() {
		return moviePrice;
	}

	public void setMoviePrice(float moviePrice) {
		this.moviePrice = moviePrice;
	}
	
	public float getViewingPackagePrice() {
		return viewingPackagePrice;
	}

	public void setViewingPackagePrice(float viewingPackagePrice) {
		this.viewingPackagePrice = viewingPackagePrice;
	}
	
	public float getSubscriptionCardPrice() {
		return subscriptionCardPrice;
	}

	public void setSubscriptionCardPrice(float subscriptionCardPrice) {
		this.subscriptionCardPrice = subscriptionCardPrice;
	}

	public ArrayList<PriceRequest> getPriceRequests() {
		return priceRequests;
	}

	public void setPriceRequests(ArrayList<PriceRequest> priceRequests) {
		this.priceRequests = priceRequests;
	}

	public PurpleLimit getPurpleLimit() {
		return purpleLimit;
	}

	public void setPurpleLimit(PurpleLimit purpleLimit) {
		this.purpleLimit = purpleLimit;
	}

	public int getTavSagolLimit() {
		return tavSagolLimit;
	}

	public void setTavSagolLimit(int tavSagolLimit) {
		this.tavSagolLimit = tavSagolLimit;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public ArrayList<PurpleLimit> getActivePurpleLimits() {
		return activePurpleLimits;
	}

	public void setActivePurpleLimits(ArrayList<PurpleLimit> activePurpleLimits) {
		this.activePurpleLimits = activePurpleLimits;
	}

	public String getEmailMessage() {
		return emailMessage;
	}

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public NetworkAdministrator getAdministrator() {
		return administrator;
	}
	
	public void setAdministrator(NetworkAdministrator administrator) {
		this.administrator = administrator;
	}


}
