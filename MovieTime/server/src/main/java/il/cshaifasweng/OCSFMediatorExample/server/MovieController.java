package il.cshaifasweng.OCSFMediatorExample.server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;

public class MovieController {
	public static List<Movie> getSoonMovies() {
		ArrayList<Movie> soonMoviesArrayList = new ArrayList<>();
		ArrayList<Movie> toReturnArrayList = new ArrayList<>();
		soonMoviesArrayList = Main.getAllOfType(Movie.class);
		for(int i = 0 ; i < soonMoviesArrayList.size() ; i++) {
			//System.out.println(movie.getName());
			if(soonMoviesArrayList.get(i).isSoonInCinema()) {
				toReturnArrayList.add(soonMoviesArrayList.get(i));
			}
		}
		return toReturnArrayList;
	}

	public static List<Movie> getGenreTypeMovies(String genre) {
		ArrayList<Movie> soonMoviesArrayList = new ArrayList<>();
		ArrayList<Movie> toReturnArrayList = new ArrayList<>();
		soonMoviesArrayList = Main.getAllOfType(Movie.class);
		for(int i = 0 ; i < soonMoviesArrayList.size() ; i++) {
			//System.out.println(movie.getName());
			if(soonMoviesArrayList.get(i).isSoonInCinema()&&soonMoviesArrayList.get(i).getGenre()==genre) {
				toReturnArrayList.add(soonMoviesArrayList.get(i));
			}
		}
		return toReturnArrayList;
	}
	

	public static List<Movie> getAllScreeningMovies() {
		ArrayList<Movie> screeningMoviesArrayList = new ArrayList<>();
		ArrayList<Movie> toReturnArrayList = new ArrayList<>();
		screeningMoviesArrayList = Main.getAllOfType(Movie.class);
		for(Movie movie : screeningMoviesArrayList) {
			if(movie.isSoonInCinema() == false) {
				toReturnArrayList.add(movie);
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
				return u2.getPopular().compareTo(u1.getPopular());
			}
		});
		return toReturn; 				 // return movies sorted by popularty
	}

	public static List<Movie> WatchingFromHome (){ 
		List<Movie> toReturn = new ArrayList<>();
		for(Movie movie : Main.getAllOfType(Movie.class)) {
			if(movie.isStreamOnline()==true)
				toReturn.add(movie);
		}
		return toReturn;				//return Movies available viewing from home 
	}
	public static List<Movie> MoviesByDate (LocalDateTime date){
		List<Movie> toReturn = new ArrayList<>();
		for(Screening movie :  Main.getAllOfType(Screening.class)) {
			if(date.getDayOfYear() == movie.getDate_screen().getDayOfYear())
				toReturn.add(movie.getMovie());
		}
		return toReturn;				//return Movies by LaunchDate
	}
	public static List<String> getAllGenreScreeningMovies() {
		ArrayList<Movie> soonMoviesArrayList = new ArrayList<>();
		ArrayList<String> toReturnArrayList = new ArrayList<>();
		soonMoviesArrayList = Main.getAllOfType(Movie.class);
		for(int i = 0 ; i < soonMoviesArrayList.size() ; i++) {
			//System.out.println(movie.getName());
			if(soonMoviesArrayList.get(i).isSoonInCinema()&&!toReturnArrayList.contains(soonMoviesArrayList.get(i).getGenre())) {
				toReturnArrayList.add(soonMoviesArrayList.get(i).getGenre());
			}
		}
		return toReturnArrayList;
	}
	
}
