package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.ArrayList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;

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
	public static List<Movie> WatchingFromHome (){ 
		List<Movie> toReturn = new ArrayList<>();
		for(Movie movie : Main.getAllOfType(Movie.class)) {
			if(movie.isStreamOnline()==true)
				toReturn.add(movie);
		}
		return toReturn;				//return Movies available viewing from home 
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
}
