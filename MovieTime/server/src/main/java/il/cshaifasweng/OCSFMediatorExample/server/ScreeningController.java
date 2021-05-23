package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.ArrayList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;

public class ScreeningController {
	public static List<Cinema> getCinemas(int id) {
		ArrayList<Cinema> list = Main.getAllOfType(Cinema.class);
		ArrayList<Cinema> toReturnArrayList = new ArrayList<>();
				
 		for(Cinema cinema : list) {
			for(Screening screening : cinema.getScreeningArray()) {
				if(screening.getMovie().getId() == id) {
					toReturnArrayList.add(cinema);
					break;
				}
			}
		}
		return toReturnArrayList;
	}
	public static List<Screening> getAllDateOfMovie(int idMovie , int idCinema) {
		ArrayList<Cinema> list = Main.getAllOfType(Cinema.class);
		ArrayList<Screening> toReturnArrayList = new ArrayList<>();
		for(Cinema cinema : list) {
			if(cinema.getId()==idCinema) {
				for(Screening screening : cinema.getScreeningArray()) {
					if(screening.getMovie().getId() == idMovie) {
						toReturnArrayList.add(screening);
					}
				}
			}
		}
		return toReturnArrayList;
	}
	
	public static boolean pickChair (int row , int col , Hall hall) {
		//System.out.println(hall.getChair()[0][0]);
		if(hall.getChair()[row][col] == false) {
			hall.getChair()[row][col] = true;
			Main.updateRowDB(hall);
			System.out.println("ok");
			return true;
		}
		return false;
	}
	
	
}
