package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.ArrayList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.util.Pair;

public class ScreeningController {
	
	
	public static List<Cinema> getCinemas(int id) {
		ArrayList<Cinema> list = Main.getAllOfType(Cinema.class);
		ArrayList<Cinema> toReturnArrayList = new ArrayList<>();
 		for(Cinema cinema : list) {
			for(Screening screening : cinema.getScreenings()) {
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
				for(Screening screening : cinema.getScreenings()) {
					if(screening.getMovie().getId() == idMovie) {
						toReturnArrayList.add(screening);
					}
				}
			}
		}
		return toReturnArrayList;
	}
	
	public static Screening getScreening(int id) {
		ArrayList<Screening> screenings = Main.getAllOfType(Screening.class);
		for(Screening screening : screenings) {
			if(screening.getId() == id) {
				return screening;

			}
		}
		return null;
	}
	
	public static Hall getHallById(int id) {
		ArrayList<Hall> halls = Main.getAllOfType(Hall.class);
		for(Hall hall : halls) {
			if(hall.getId() == id) {
				return hall;

			}
		}
		return null;
	}
	
	public static boolean pickChair (int[][] ks, Screening screening) {
		for(int i = 0 ; i < screening.getHall().getRows() ; i++) {
			for(int j = 0 ; j < screening.getHall().getCols() ; j++) {
				if(ks[i][j] == 2 && screening.getSeats()[i][j] == 1) {
					return false;
				}
			}
		}

		return true;

	}
	
	
}
