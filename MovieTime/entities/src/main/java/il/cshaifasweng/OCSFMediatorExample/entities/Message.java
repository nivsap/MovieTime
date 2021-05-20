package il.cshaifasweng.OCSFMediatorExample.entities;


import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
	String action;
	String username; 
	String password;
	String typeOfWorkerString;
	Movie movie;
	ArrayList<Movie> movies;
	ArrayList<String> timeOfMovie; //for shir
	Worker worker;
	Complaint complaint;
	int movieId;
	int cinemaId;
	ArrayList<Cinema> cinemasArrayList;
	ArrayList<Screening> screeningArrayList;
	int row;
	int col;
	Hall hall;
	boolean status;


	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public int getRow() {
		return row;
	}



	public void setRow(int row) {
		this.row = row;
	}



	public int getCol() {
		return col;
	}



	public void setCol(int col) {
		this.col = col;
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
	
}