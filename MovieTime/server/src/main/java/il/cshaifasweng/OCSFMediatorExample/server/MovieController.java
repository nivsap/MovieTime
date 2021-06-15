package il.cshaifasweng.OCSFMediatorExample.server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;

public class MovieController {
	
	public static Movie getMovieByName(String name) {
		ArrayList<Movie> movies = new ArrayList<>();
		movies = Main.getAllOfType(Movie.class);
		for(Movie movie : movies) {
			if(movie.getName().equals(name)) {
				return movie;
			}
		}
		return null;
	}
	
	public static List<Movie> getMoviesByDate(LocalDateTime date){ //return Movies by LaunchDate
		List<Movie> toReturn = new ArrayList<>();
		for(Screening movie :  Main.getAllOfType(Screening.class)) {
			if(date == movie.getDateAndTime())
				toReturn.add(movie.getMovie());
		}
		return toReturn;				
	}
	
	public static ArrayList<Movie> getNotDeletedMovies() {
		ArrayList<Movie> allMovies = new ArrayList<>();
		ArrayList<Movie> toReturn = new ArrayList<>();
		allMovies = Main.getAllOfType(Movie.class);
		for(Movie movie : allMovies) {
			if(!movie.isDeleted()) {
				toReturn.add(movie);
			}
		}
		return toReturn;
	}
	
	public static List<Movie> getCurrentlyScreeningMovies() {
		ArrayList<Movie> allMovies = new ArrayList<>();
		ArrayList<Movie> toReturn = new ArrayList<>();
		allMovies = Main.getAllOfType(Movie.class);
		for(Movie movie : allMovies) {
			if(movie.isScreening()) {
				toReturn.add(movie);
			}
		}
		return toReturn;
	}
	
	public static List<Movie> getComingSoonMovies() {
		ArrayList<Movie> allMovies = new ArrayList<>();
		ArrayList<Movie> toReturn = new ArrayList<>();
		allMovies = Main.getAllOfType(Movie.class);
		for(Movie m: allMovies) {
			if(m.isComingSoon()) 
				toReturn.add(m);
		}
		return toReturn;
	}

	public static List<Movie> getGenreTypeMovies(String genre) {
		ArrayList<Movie> soonMoviesArrayList = new ArrayList<>();
		ArrayList<Movie> toReturnArrayList = new ArrayList<>();
		soonMoviesArrayList = Main.getAllOfType(Movie.class);
		for(int i = 0 ; i < soonMoviesArrayList.size() ; i++) {
			//System.out.println(movie.getName());
			if(soonMoviesArrayList.get(i).isComingSoon()&&soonMoviesArrayList.get(i).getGenre()==genre) {
				toReturnArrayList.add(soonMoviesArrayList.get(i));
			}
		}
		return toReturnArrayList;
	}
	
	public static List<Movie> MoviesByGener (String genre){
		List<Movie> toReturn = new ArrayList<>();
		List<Movie> allMovies = Main.getAllOfType(Movie.class);
		for(Movie movie : allMovies) {
			if(movie.getGenre().equals(genre)) {
				toReturn.add(movie);
			}
		}
		return toReturn; 				//return All movies from a specific genre
	}

	public static List<Movie> MoviesByPopularty (){
		List<Movie> toReturn = Main.getAllOfType(Movie.class);
		Collections.sort(toReturn, new Comparator<Movie>() {
			@Override
			public int compare(Movie u1, Movie u2) {
				return u2.getRate().compareTo(u1.getRate());
			}
		});
		return toReturn; 				 // return movies sorted by popularty
	}

	
	public static List<String> getAllGenreScreeningMovies() {
		ArrayList<Movie> soonMoviesArrayList = new ArrayList<>();
		ArrayList<String> toReturnArrayList = new ArrayList<>();
		soonMoviesArrayList = Main.getAllOfType(Movie.class);
		for(int i = 0 ; i < soonMoviesArrayList.size() ; i++) {
			//System.out.println(movie.getName());
			if(soonMoviesArrayList.get(i).isComingSoon()&&!toReturnArrayList.contains(soonMoviesArrayList.get(i).getGenre())) {
				toReturnArrayList.add(soonMoviesArrayList.get(i).getGenre());
			}
		}
		return toReturnArrayList;
	}
}
