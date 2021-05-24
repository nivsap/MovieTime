package il.cshaifasweng.OCSFMediatorExample.entities;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class Message implements Serializable {
	String action;
	String username; 
	String password;
	String typeOfWorkerString;
	Movie movie;
	Screening screening;
	ArrayList<Movie> movies;
	ArrayList<String> timeOfMovie; //for shir
	Worker worker;
	Complaint complaint;
	int id;
	int movieId;            				//clientSide need to fill/set is field when send msg to server
	int cinemaId;           				//clientSide need to fill/set is field when send msg to server
	ArrayList<Cinema> cinemasArrayList;
	ArrayList<Screening> screeningArrayList;
	List<Pair<Integer,Integer>> chairsHall;
	Hall hall;              				//clientSide need to fill/set is field when send msg to server
	boolean status;
	//HANDLE TICKETS **for shir
	boolean isTab;

	Integer[][] seats;
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
	
	String genre;    						//clientSide need to fill/set is field when send msg to server
	LocalDateTime dateMovie;    			//clientSide need to fill/set is field when send msg to server
	Purchaser purchaser;
	int month;
	Cinema cinema;
	List<Purchaser> purchasersList;
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}


	public int getMonth() {
		return month;
	}


	public List<Purchaser> getPurchasersList() {
		return purchasersList;
	}

	public void setPurchasersList(List<Purchaser> purchasersList) {
		this.purchasersList = purchasersList;
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



	public Purchaser getPurchaser() {
		return purchaser;
	}



	public void setPurchaser(Purchaser purchaser) {
		this.purchaser = purchaser;
	}



	public boolean isStatus() {
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
	
	public Integer[][] getSeats(){
		return seats;
	}
	
	public void setSeats(Integer[][] seats) {
		this.seats = seats;
	}
	
	public Screening getScreening() {
		return screening;
	}
	
	public void setScreening(Screening screening) {
		this.screening = screening;
	}
}

