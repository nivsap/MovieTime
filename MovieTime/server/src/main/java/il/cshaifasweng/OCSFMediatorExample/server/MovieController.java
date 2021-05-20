package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.ArrayList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;

public class MovieController {
	public static List<Movie> getSoonMovies() {
		ArrayList<Movie> soonMoviesArrayList = new ArrayList<>();
		soonMoviesArrayList = Main.getAllOfType(Movie.class);
		for(int i = 0 ; i < soonMoviesArrayList.size() ; i++) {
			//System.out.println(movie.getName());
			if(soonMoviesArrayList.get(i).isSoonInCinema()) {
				soonMoviesArrayList.add(soonMoviesArrayList.get(i));
			}
		}
		return soonMoviesArrayList;
	}
	
	public static List<Movie> getAllScreeningMovies() {
		ArrayList<Movie> soonMoviesArrayList = new ArrayList<>();
		soonMoviesArrayList = Main.getAllOfType(Movie.class);
		for(Movie movie : soonMoviesArrayList) {
			if(movie.isSoonInCinema() == false) {
				soonMoviesArrayList.add(movie);
			}
		}
		return soonMoviesArrayList;
	}
}
