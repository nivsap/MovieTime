package il.cshaifasweng.OCSFMediatorExample.entities;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
//purchas
@SuppressWarnings("serial")
public class Message implements Serializable {
	String action;
	String username; 
	String password;
	String actionType;
	String moviesType;
	String typeOfWorkerString;
	Movie movie;
	Screening screening;
	ArrayList<Movie> movies;
	ArrayList<String> timeOfMovie; //for shir
	Worker worker;
	Complaint complaint;
	ArrayList<Complaint> complaints;
	String emailMessage;
	String customerEmail;
	String cinemaName;
	String genere;
	String theater;
	String rate;
	String search;
	int id;
	int tavSagolLimit;
	int numOfSeats;
	double payment;
	ArrayList<String> genres;
	ArrayList<PurpleLimit> activePurpleLimits;
	PurpleLimit purpleLimit;
	int movieId;            				//clientSide need to fill/set is field when send msg to server
	int cinemaId;           				//clientSide need to fill/set is field when send msg to server
	ArrayList<Cinema> cinemasArrayList;
	ArrayList<PriceRequest> priceRequestsArrayList;
	ArrayList<Screening> screeningArrayList;
	List<Pair<Integer,Integer>> chairsHall;
	Hall hall;              				//clientSide need to fill/set is field when send msg to server
	boolean status;
	//HANDLE TICKETS **for shir
	boolean isTab;
	public String genreString;

	public String[] genreArray;

	int[][] seats;
	String firstName;       				//clientSide need to fill/set is field when send msg to server
	String lastName;        				//clientSide need to fill/set is field when send msg to server
	String emailOrder;      				//clientSide need to fill/set is field when send msg to server
	String cityString;      				//clientSide need to fill/set is field when send msg to server
	String phoneString;     				//clientSide need to fill/set is field when send msg to server
	Pair<Boolean , Integer> cinemaTab;      //clientSide need to fill/set is field when send msg to server
	String time;
	String movieName;
	String DbAction;
	String error;
	int hallId;
	String genre;    						//clientSide need to fill/set is field when send msg to server
	LocalDateTime dateMovie;    			//clientSide need to fill/set is field when send msg to server
	Purchase purchase;
	int month;
	Cinema cinema;
	List<Purchase> purchasesList;
	ViewingPackage viewingPackage;
	PriceRequest priceRequestmsg;
	List<ViewingPackage> viewingPackageList;
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ViewingPackage> getViewingPackageList() {
		return viewingPackageList;
	}

	public void setViewingPackageList(List<ViewingPackage> viewingPackageList) {
		this.viewingPackageList = viewingPackageList;
	}

	public PriceRequest getPriceRequestmsg() {
		return priceRequestmsg;
	}

	public void setPriceRequestmsg(PriceRequest priceRequestmsg) {
		this.priceRequestmsg = priceRequestmsg;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public ViewingPackage getViewingPackage() {
		return viewingPackage;
	}

	public void setViewingPackage(ViewingPackage viewingPackage) {
		this.viewingPackage = viewingPackage;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public ArrayList<Complaint> getComplaints() {
		return complaints;
	}
	public void setComplaints(ArrayList<Complaint> complaints) {
		this.complaints = complaints;
	}
	public int getMonth() {
		return month;
	}


	public List<Purchase> getPurchasesList() {
		return purchasesList;
	}

	public void setPurchasesList(List<Purchase> purchasesList) {
		this.purchasesList = purchasesList;
	}

	public void setMonth(int month) {
		this.month = month;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Purchase getPurchase() {
		return purchase;
	}



	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}



	public boolean getStatus() {
		return status;
	}



	public List<Pair<Integer, Integer>> getChairsHall() {
		return chairsHall;
	}



	public void setChairsHall(List<Pair<Integer, Integer>> chairsHall) {
		this.chairsHall = chairsHall;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public String getGenre() {
		return genre;
	}



	public void setGenre(String genre) {
		this.genre = genre;
	}



	public LocalDateTime getDateMovie() {
		return dateMovie;
	}

	


	public void setDateMovie(LocalDateTime dateMovie) {
		this.dateMovie = dateMovie;
	}



	public boolean isTab() {
		return isTab;
	}



	public void setTab(boolean isTab) {
		this.isTab = isTab;
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



	public Pair<Boolean, Integer> getCinemaTab() {
		return cinemaTab;
	}



	public void setCinemaTab(Pair<Boolean, Integer> cinemaTab) {
		this.cinemaTab = cinemaTab;
	}


	public Hall getHall() {
		return hall;
	}



	public void setHall(Hall hall) {
		this.hall = hall;
	}



	public int getCinemaId() {
		return cinemaId;
	}



	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}



	public ArrayList<Screening> getScreeningArrayList() {
		return screeningArrayList;
	}



	public void setScreeningArrayList(ArrayList<Screening> screeningArrayList) {
		this.screeningArrayList = screeningArrayList;
	}



	public ArrayList<Cinema> getCinemasArrayList() {
		return cinemasArrayList;
	}



	public void setCinemasArrayList(ArrayList<Cinema> cinemasArrayList) {
		this.cinemasArrayList = cinemasArrayList;
	}



	public int getMovieId() {
		return movieId;
	}



	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}



	public String getTypeOfWorkerString() {
		return typeOfWorkerString;
	}



	public void setTypeOfWorkerString(String typeOfWorkerString) {
		this.typeOfWorkerString = typeOfWorkerString;
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



	public Worker getWorker() {
		return worker;
	}



	public void setWorker(Worker worker) {
		this.worker = worker;
	}



	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

	public ArrayList<String> getTimeOfMovie() {
		return timeOfMovie;
	}

	public void setTimeOfMovie(ArrayList<String> timeOfMovie) {
		this.timeOfMovie = timeOfMovie;
	}


	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getTime() {
		return time;
	}
	
	
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	public String getMovieName() {
		return movieName;
	}
	
	public void setDbAction(String action) {
		this.DbAction = action;
	}
	
	public String getDbAction() {
		return DbAction;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public String getError() {
		return error;
	}
	
	public int[][] getSeats(){
		return seats;
	}
	
	public void setSeats(int[][] seats) {
		this.seats = seats;
	}
	
	public Screening getScreening() {
		return screening;
	}
	
	public void setScreening(Screening screening) {
		this.screening = screening;
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

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}


	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public ArrayList<PriceRequest> getPriceRequestsArrayList() {
		return priceRequestsArrayList;
	}

	public void setPriceRequestsArrayList(ArrayList<PriceRequest> priceRequestsArrayList) {
		this.priceRequestsArrayList = priceRequestsArrayList;
	}

	public int getTavSagolLimit() {
		return tavSagolLimit;
	}

	public void setTavSagolLimit(int tavSagolLimit) {
		this.tavSagolLimit = tavSagolLimit;
	}

	public ArrayList<String> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getMoviesType() {
		return moviesType;
	}

	public void setMoviesType(String moviesType) {
		this.moviesType = moviesType;
	}
	
	public void setPurpleLimit(PurpleLimit p) {
		this.purpleLimit = p;
	}
	
	public PurpleLimit getPurpleLimit() {
		return this.purpleLimit;
	}
	
	public ArrayList<PurpleLimit> getActivePurpleLimit() {
		return this.activePurpleLimits;
	}
	
	public void setActivePurpleLimit(ArrayList<PurpleLimit> activePurpleLimits) {
		this.activePurpleLimits = activePurpleLimits;
	}
}

