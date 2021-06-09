package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.ArrayList;

import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.ViewingPackage;

public class ViewingPackageController {
	
	public static ArrayList<Movie> getViewingPackageMovies() {
		ArrayList<ViewingPackage> viewingPackageMovies = new ArrayList<>();
		ArrayList<Movie> toReturnArrayList = new ArrayList<>();
		viewingPackageMovies = Main.getAllOfType(ViewingPackage.class);
		for(int i = 0 ; i < viewingPackageMovies.size() ; i++) {
			Movie currentMovie = viewingPackageMovies.get(i).getMovie();
			if(!toReturnArrayList.contains(currentMovie))
				toReturnArrayList.add(currentMovie);
		}
		return toReturnArrayList;
	}
}
