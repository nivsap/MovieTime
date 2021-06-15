package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.ViewingPackage;

public class ViewingPackageController {
	
	public static ArrayList<Movie> getViewingPackageMovies() {
		ArrayList<ViewingPackage> viewingPackageMovies = new ArrayList<>();
		ArrayList<Movie> toReturnArrayList = new ArrayList<>();
		viewingPackageMovies = Main.getAllOfType(ViewingPackage.class);
		for(int i = 0 ; i < viewingPackageMovies.size() ; i++) {
			if(viewingPackageMovies.get(i).isDeleted())
				continue;
			Movie currentMovie = viewingPackageMovies.get(i).getMovie();
			if(!toReturnArrayList.contains(currentMovie))
				toReturnArrayList.add(currentMovie);
		}
		return toReturnArrayList;
	}
	
	public static ArrayList<ViewingPackage> getViewingPackagesByMovie(String movieName) {
		ArrayList<ViewingPackage> viewingPackages = Main.getAllOfType(ViewingPackage.class);
		Iterator<ViewingPackage> iter = viewingPackages.iterator();
		while (iter.hasNext()) {
			ViewingPackage s = iter.next();
			if (!s.getMovie().getName().equals(movieName)) {
				iter.remove();
			}
		}
		
		return viewingPackages;
	}
	
	public static ArrayList<Movie> getAllValidMovies() {
		ArrayList<Movie> allMovies = new ArrayList<>();
		ArrayList<Movie> toReturn = new ArrayList<>();
		allMovies = Main.getAllOfType(Movie.class);
		for(Movie movie : allMovies) {
			if(!movie.isDeleted() && !movie.isComingSoon()) {
				toReturn.add(movie);
			}
		}
		return toReturn;
	}
	
	public static ArrayList<ViewingPackage> getAllNotDeletedPackages() {
		ArrayList<ViewingPackage> allViewingPackages = new ArrayList<>();
		ArrayList<ViewingPackage> toReturn = new ArrayList<>();
		allViewingPackages = Main.getAllOfType(ViewingPackage.class);
		for(ViewingPackage v : allViewingPackages) {
			if(!v.isDeleted()) 
				toReturn.add(v);
		}
		return toReturn;
	}
}
