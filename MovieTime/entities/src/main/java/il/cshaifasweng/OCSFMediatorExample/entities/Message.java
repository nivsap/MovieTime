package il.cshaifasweng.OCSFMediatorExample.entities;


import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
	String action;
	Movie movie;
	ArrayList<Movie> movies;
	ArrayList<String> timeOfMovie; //for shir
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
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
