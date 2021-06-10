package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;

public class CinemaController {
	public static Cinema getCinemaByName(String name) {
		List<Cinema> cinemas = Main.getAllOfType(Cinema.class);
		for(Cinema cinema : cinemas) {
			if(cinema.getName().equals(name)) {
				return cinema;
			}
		}
		return null;
	}
}
