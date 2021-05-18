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
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
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

	
	
}