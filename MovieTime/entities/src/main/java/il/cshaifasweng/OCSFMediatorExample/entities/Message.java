package il.cshaifasweng.OCSFMediatorExample.entities;


import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
	String action;
	Movie movie;
	ArrayList<Movie> movies;
	ArrayList<String> timeOfMovie; //for shir
	String time;
	String movieName;
	String DbAction;
	String error;
	
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
	
}
