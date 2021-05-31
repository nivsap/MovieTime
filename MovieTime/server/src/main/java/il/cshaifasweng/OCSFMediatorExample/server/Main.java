package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;

import java.time.LocalDate;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import il.cshaifasweng.OCSFMediatorExample.entities.BranchManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Cinema;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.ContentManager;
import il.cshaifasweng.OCSFMediatorExample.entities.CustomerService;
import il.cshaifasweng.OCSFMediatorExample.entities.Hall;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.NetworkAdministrator;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.ViewingPackage;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import javafx.util.Pair;

public class Main extends AbstractServer {

	private static Session session;
	public static SessionFactory sessionFactory = getSessionFactory();
	Message serverMsg;

	public Main(int port) {
		
		super(port);
	}

	public static SessionFactory getSessionFactory() throws HibernateException {

		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Movie.class);
		configuration.addAnnotatedClass(Worker.class);
		configuration.addAnnotatedClass(NetworkAdministrator.class);
		configuration.addAnnotatedClass(Cinema.class);
		configuration.addAnnotatedClass(Hall.class);
		// configuration.addAnnotatedClass(Seat.class);
		configuration.addAnnotatedClass(Screening.class);
		configuration.addAnnotatedClass(ContentManager.class);
		configuration.addAnnotatedClass(BranchManager.class);
		configuration.addAnnotatedClass(CustomerService.class);
		configuration.addAnnotatedClass(Purchase.class);
		configuration.addAnnotatedClass(Complaint.class);
		configuration.addAnnotatedClass(ViewingPackage.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static LocalDateTime getTime(int year, int month, int day) {
		return LocalDate.of(year, month, day).atStartOfDay();
	}

	private static LocalDateTime getExacTime(int year, int month, int day, int hours, int minutes) {
		return LocalDate.of(year, month, day).atTime(hours, minutes);
	}

	public static void addDataToDB() {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Worker shirWorker = new BranchManager("shir", "shir", "shir", "shir", false,null);
			Worker nivWorker = new BranchManager("niv", "niv", "niv", "niv", false,null);
			Worker lielWorker = new ContentManager("liel", "liel", "liel", "liel", null,false);
			Worker asafWorker = new CustomerService("asaf", "asaf", "asaf", "asaf", null, false,true,new Pair<LocalDateTime, LocalDateTime>(getTime(2021,1, 1), getTime(2021,3, 4)),70);
			Worker hadarWorker = new NetworkAdministrator("hadar", "hadar", "hadar", "hadar", null,false);

			// create movie
			ArrayList<String> movieStartTimes = new ArrayList<String>(
					Arrays.asList("10:00", "12:00", "16:00", "18:00", "20:00", "22:00", "00:00"));
			Movie avengersEndgame = new Movie("Avengers: Endgame", "3h 1min", 5.00,
					"Action   •   Adventure   •   Drama", "AvengersEndgame.jpg", "AvengersEndgame.png", movieStartTimes,
					true, false,
					"After the devastating events of Avengers: Infinity War (2018), the \nuniverse is in ruins. With the help of remaining allies, \nthe Avengers assemble once more in order to reverse Thanos' \nactions and restore balance to the universe.",
					"Robert Downey Jr., Chris Evans, Mark Ruffalo", getTime(2019, 4, 26), 50, "Producers",null,false,new ArrayList<>(),true);
			Movie sherlockHolmes = new Movie("Sherlock Holmes", "2h 8min", 4.5, "Action   •   Adventure   •   Mystery",
					"SherlockHolmes.jpg", "SherlockHolmes.png", movieStartTimes, true, false,
					"Detective Sherlock Holmes and his stalwart partner Watson engage in \na battle of wits and brawn with a nemesis whose plot \nis a threat to all of England.",
					"Robert Downey Jr., Jude Law, Rachel McAdams", getTime(2009, 12, 25), 50, "Producers",null,false,new ArrayList<>(),true);
			Movie babyDriver = new Movie("Baby Driver", "1h 53min", 4.00, "Action   •   Crime   •   Drama ",
					"BabyDriver.jpg", "BabyDriver.png", movieStartTimes, true, false,
					"After being coerced into working for a crime boss, a young getaway \ndriver finds himself taking part in a heist doomed to fail.",
					"Ansel Elgort, Jon Bernthal, Jon Hamm", getTime(2017, 6, 28), 50, "Producers",null,false,new ArrayList<>(),true);
			Movie wonderWoman1984 = new Movie("Wonder Woman 1984", "2h 31min", 5.00,
					"Action   •   Adventure   •   Fantasy", "WonderWoman1984.jpg", "WonderWoman1984.png",
					movieStartTimes, true, false,
					"Diana must contend with a work colleague and businessman, whose desire \nfor extreme wealth sends the world down a path of destruction, \nafter an ancient artifact that grants wishes goes missing.",
					"Gal Gadot, Chris Pine, Kristen Wiig", getTime(2020, 12, 21), 50, "Producers",null,false,new ArrayList<>(),false);
			Movie it = new Movie("IT", "2h 15min", 5.00, "Horror", "It.jpg", "It.png", movieStartTimes, true, false,
					"In the summer of 1989, a group of bullied kids band together\n to destroy a shape-shifting monster, which disguises itself \nas a clown and preys on the children of Derry, \ntheir small Maine town.",
					"Bill Skarsgard, Jaeden Martell, Finn Wolfhard", getTime(2017, 9, 8), 50, "Producers",null,false,new ArrayList<>(),true);
			Movie toyStory = new Movie("Toy Story", "1h 40min", 5.00, "Animation   •   Adventure   •   Comedy",
					"ToyStory.jpg", "ToyStory.png", movieStartTimes, true, false,
					"When a new toy called 'Forky' joins Woody and the gang, \na road trip alongside old and new friends reveals how \nbig the world can be for a toy.",
					"Tom Hanks, Tim Allen, Annie Potts", getTime(2017, 6, 21), 50, "Producers",null,false,new ArrayList<>(),true);
			Movie Minions = new Movie("Minions", "1h 31min", 4.50, "Animation   •   Adventure   •   Comedy",
					"Minions.jpg", "Minions.png", movieStartTimes, false, false,
					"Minions Stuart, Kevin, and Bob are recruited by Scarlet Overkill, \na supervillain who, alongside her inventor husband Herb, \nhatches a plot to take over the world.",
					"Sandra Bullock, Jon Hamm, Michael Keaton", getTime(2015, 7, 10), 50, "Producers",null,false,new ArrayList<>(),true);
			Movie StarWars = new Movie("Star Wars", "2h 21min", 5.00, "Action   •   Adventure   •   Fantasy",
					"StarWars.jpg", "StarWars.png", movieStartTimes, false, true,
					"The surviving members of the Resistance face the First Order once \nagain, and the legendary conflict between the Jedi and the Sith reaches \nits peak, bringing the Skywalker saga to its end.",
					"Daisy Ridley, John Boyega, Oscar Isaac", getTime(2019, 12, 20), 50, "Producers",null,false,new ArrayList<>(),false);

			avengersEndgame.setMovieBeginingTime(new ArrayList<String>(Arrays.asList("10:00", "12:00")));
			sherlockHolmes.setMovieBeginingTime(new ArrayList<String>(Arrays.asList("16:00", "18:00")));
			babyDriver.setMovieBeginingTime(new ArrayList<String>(Arrays.asList("20:00", "22:00")));

			// Worker shirWorker = new BranchManager("shir", "shir", "shir", "shir",null);
			// Worker nivWorker = new BranchManager("niv", "niv", "niv", "niv",null);
			// Worker lielWorker = new ContentManager("liel", "liel", "liel", "liel",null);
			// Worker asafWorker = new CustomerService("asaf", "asaf", "asaf", "asaf",null,false);
			// Worker hadarWorker = new NetworkAdministrator("hadar", "hadar", "hadar", "hadar",null);


			// //create movie 
			// ArrayList<String> movieStartTimes = new ArrayList<String>(Arrays.asList("10:00" , "12:00" , "16:00" , "18:00" , "20:00" , "22:00" , "00:00"));
			// Movie avengersEndgame = new Movie("Avengers: Endgame","3h 1min", 5.00, "Action   •   Adventure   •   Drama", "AvengersEndgame.jpg",  "AvengersEndgame.png", movieStartTimes, true, false, "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
			// 		"Robert Downey Jr., Chris Evans, Mark Ruffalo", getTime(2019, 4, 26), 50 , "Producers");
			// Movie sherlockHolmes = new Movie("Sherlock Holmes", "2h 8min", 4.5, "Action   •   Adventure   •   Mystery", "SherlockHolmes.jpg", "SherlockHolmes.png", movieStartTimes, true, false, "Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England.",
			// 		"Robert Downey Jr., Jude Law, Rachel McAdams", getTime(2009, 12, 25), 50 , "Producers");
			// Movie babyDriver = new Movie("Baby Driver", "1h 53min", 4.00, "Action   •   Crime   •   Drama ", "BabyDriver.jpg", "BabyDriver.png", movieStartTimes, true, false, "After being coerced into working for a crime boss, a young getaway driver finds himself taking part in a heist doomed to fail.",
			// 		"Ansel Elgort, Jon Bernthal, Jon Hamm", getTime(2017, 6, 28), 50 , "Producers");
			// Movie wonderWoman1984  = new Movie("Wonder Woman 1984", "2h 31min", 5.00, "Action   •   Adventure   •   Fantasy", "WonderWoman1984.jpg", "WonderWoman1984.png", movieStartTimes, true, false, "Diana must contend with a work colleague and businessman, whose desire for extreme wealth sends the world down a path of destruction, after an ancient artifact that grants wishes goes missing.",
			// 		"Gal Gadot, Chris Pine, Kristen Wiig", getTime(2020, 12, 21), 50 , "Producers");
			// Movie it  = new Movie("IT", "2h 15min", 5.00, "Horror", "It.jpg", "It.png", movieStartTimes, true, false, "In the summer of 1989, a group of bullied kids band together to destroy a shape-shifting monster, which disguises itself as a clown and preys on the children of Derry, their small Maine town.",
			// 		"Bill Skarsgard, Jaeden Martell, Finn Wolfhard", getTime(2017, 9, 8), 50 , "Producers");
			// Movie toyStory = new Movie("Toy Story", "1h 40min", 5.00, "Animation   •   Adventure   •   Comedy", "ToyStory.jpg", "ToyStory.png", movieStartTimes, true, false, "When a new toy called 'Forky' joins Woody and the gang, a road trip alongside old and new friends reveals how big the world can be for a toy.",
			// 		"Tom Hanks, Tim Allen, Annie Potts", getTime(2017, 6, 21), 50 , "Producers");
			// Movie Minions = new Movie("Minions", "1h 31min", 4.50, "Animation   •   Adventure   •   Comedy", "Minions.jpg", "Minions.png", movieStartTimes, false, false, "Minions Stuart, Kevin, and Bob are recruited by Scarlet Overkill, a supervillain who, alongside her inventor husband Herb, hatches a plot to take over the world.",
			// 		"Sandra Bullock, Jon Hamm, Michael Keaton", getTime(2015, 7, 10), 50 , "Producers");
			// Movie StarWars = new Movie("Star Wars", "2h 21min", 5.00, "Action   •   Adventure   •   Fantasy", "StarWars.jpg", "StarWars.png", movieStartTimes, false, true, "The surviving members of the Resistance face the First Order once again, and the legendary conflict between the Jedi and the Sith reaches its peak, bringing the Skywalker saga to its end.",
			// 		"Daisy Ridley, John Boyega, Oscar Isaac", getTime(2019, 12, 20) , 50 , "Producers");

			// avengersEndgame.setMovieBeginingTime(new ArrayList<String>(Arrays.asList("10:00" , "12:00")));
			// sherlockHolmes.setMovieBeginingTime(new ArrayList<String>(Arrays.asList( "16:00" , "18:00")));
			// babyDriver.setMovieBeginingTime(new ArrayList<String>(Arrays.asList( "20:00" , "22:00")));
			wonderWoman1984.setMovieBeginingTime(new ArrayList<String>(Arrays.asList("00:00")));
			it.setMovieBeginingTime(new ArrayList<String>(Arrays.asList("11:00", "13:00")));
			toyStory.setMovieBeginingTime(new ArrayList<String>(Arrays.asList("15:00", "17:00")));

			session.save(avengersEndgame);
			session.save(sherlockHolmes);
			session.save(babyDriver);
			session.save(wonderWoman1984);
			session.save(it);
			session.save(toyStory);
			session.save(Minions);
			session.save(StarWars);
			session.flush();

			//creating whole data base to cinema,screening,Hall
			Cinema haifaCinema = new Cinema("Haifa", "Haifa,Carmel st", (BranchManager)shirWorker, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),new ArrayList<>(),40,20,0.8,new ArrayList<>(),new ArrayList<>());
			Cinema telAvivCinema = new Cinema("Tel-Aviv", "Tel-Aviv,Wieztman st", (BranchManager)nivWorker, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),new ArrayList<>(),45,20,0.9,new ArrayList<>(),new ArrayList<>());
			shirWorker.setCinema(haifaCinema);
			nivWorker.setCinema(telAvivCinema);
			haifaCinema.getWorkerArray().add(asafWorker);
			telAvivCinema.getWorkerArray().add(asafWorker);
			haifaCinema.getWorkerArray().add(lielWorker);
			telAvivCinema.getWorkerArray().add(lielWorker);

			// Integer[][] planeArray1 = new Integer[3][5];

			Hall hall1 = new Hall(3, 5, new ArrayList<>(), haifaCinema, new ArrayList<>());
			Hall hall2 = new Hall(4, 4, new ArrayList<>(), haifaCinema, new ArrayList<>());
			Hall hall3 = new Hall(7, 5, new ArrayList<>(), telAvivCinema, new ArrayList<>());
			Hall hall4 = new Hall(2, 5, new ArrayList<>(), telAvivCinema, new ArrayList<>());

			Screening screeningOfFilm_1 = new Screening(getTime(2021, 2, 3), hall1, avengersEndgame,
					haifaCinema,new ArrayList<>());
			Screening screeningOfFilm_2 = new Screening(getExacTime(2021, 6, 25, 20, 00), hall1, sherlockHolmes,
					haifaCinema,new ArrayList<>());
			Screening screeningOfFilm_3 = new Screening(getExacTime(2021, 6, 26, 20, 00), hall2, sherlockHolmes,
					haifaCinema,new ArrayList<>());
			Screening screeningOfFilm_4 = new Screening(getExacTime(2021, 6, 27, 20, 00), hall2, babyDriver,
					haifaCinema,new ArrayList<>());
			Screening screeningOfFilm_9 = new Screening(getExacTime(2021, 6, 27, 20, 30), hall2, sherlockHolmes,
					haifaCinema,new ArrayList<>());
			Screening screeningOfFilm_10 = new Screening(getExacTime(2021, 6, 27, 20, 30), hall1, wonderWoman1984,
					haifaCinema,new ArrayList<>());
			Screening screeningOfFilm_5 = new Screening(getExacTime(2021, 6, 26, 20, 00), hall3, wonderWoman1984,
					telAvivCinema,new ArrayList<>());
			Screening screeningOfFilm_6 = new Screening(getExacTime(2021, 6, 27, 20, 00), hall3, it, telAvivCinema,new ArrayList<>());
			Screening screeningOfFilm_7 = new Screening(getExacTime(2021, 6, 28, 13, 30), hall4, toyStory,
					telAvivCinema,new ArrayList<>());
			Screening screeningOfFilm_8 = new Screening(getExacTime(2021, 6, 28, 20, 00), hall4, Minions,
					telAvivCinema,new ArrayList<>());
			Screening screeningOfFilm_11 = new Screening(getExacTime(2021, 6, 28, 10, 15), hall4, Minions,
					telAvivCinema,new ArrayList<>());
			Screening screeningOfFilm_12 = new Screening(getExacTime(2021, 6, 28, 10, 15), hall3, it, telAvivCinema,new ArrayList<>());

			hall1.getScreeningArray().add(screeningOfFilm_1);
			hall1.getScreeningArray().add(screeningOfFilm_2);
			hall1.getScreeningArray().add(screeningOfFilm_10);
			hall2.getScreeningArray().add(screeningOfFilm_3);
			hall2.getScreeningArray().add(screeningOfFilm_4);
			hall2.getScreeningArray().add(screeningOfFilm_9);
			hall3.getScreeningArray().add(screeningOfFilm_5);
			hall3.getScreeningArray().add(screeningOfFilm_6);
			hall3.getScreeningArray().add(screeningOfFilm_12);
			hall4.getScreeningArray().add(screeningOfFilm_7);
			hall4.getScreeningArray().add(screeningOfFilm_8);
			hall4.getScreeningArray().add(screeningOfFilm_11);

			haifaCinema.getScreeningArray().add(screeningOfFilm_1);
			haifaCinema.getScreeningArray().add(screeningOfFilm_2);
			haifaCinema.getScreeningArray().add(screeningOfFilm_3);
			haifaCinema.getScreeningArray().add(screeningOfFilm_4);
			haifaCinema.getScreeningArray().add(screeningOfFilm_9);
			haifaCinema.getScreeningArray().add(screeningOfFilm_10);
			haifaCinema.setHallArray(new ArrayList<Hall>(Arrays.asList(hall1,hall2)));

			telAvivCinema.getScreeningArray().add(screeningOfFilm_5);
			telAvivCinema.getScreeningArray().add(screeningOfFilm_6);
			telAvivCinema.getScreeningArray().add(screeningOfFilm_7);
			telAvivCinema.getScreeningArray().add(screeningOfFilm_8);
			telAvivCinema.getScreeningArray().add(screeningOfFilm_11);
			telAvivCinema.getScreeningArray().add(screeningOfFilm_12);
			telAvivCinema.setHallArray(new ArrayList<Hall>(Arrays.asList(hall3,hall4)));
			Purchase customer = new Purchase("Shir", "Avneri", "shiravneri@gmail.com", "Some details", "0523456789",
					new Pair<Boolean, Integer>(true, 20), false, getTime(2021, 2, 3), haifaCinema, hall1, new ArrayList<>(), 10, null,screeningOfFilm_1,false);
			//screeningOfFilm_1.getPurchases().add(customer);
			session.save(customer);
			session.save(screeningOfFilm_1);
			session.save(screeningOfFilm_2);
			session.save(screeningOfFilm_3);
			session.save(screeningOfFilm_4);
			session.save(screeningOfFilm_5);
			session.save(screeningOfFilm_6);
			session.save(screeningOfFilm_7);
			session.save(screeningOfFilm_8);
			session.save(screeningOfFilm_9);
			session.save(screeningOfFilm_10);
			session.save(screeningOfFilm_11);
			session.save(screeningOfFilm_12);

			session.save(hall1);
			session.save(hall2);
			session.save(hall3);
			session.save(hall4);

			session.save(shirWorker);
			session.save(nivWorker);
			session.save(lielWorker);
			session.save(asafWorker);
			session.save(hadarWorker);

			session.save(haifaCinema);
			session.save(telAvivCinema);



			Purchase customer2 = new Purchase("Alon", "Latman1", "shiravneri@gmail.com", "Some details", "123456789",
					new Pair<Boolean, Integer>(true, 20), false, getExacTime(2021, 5, 28, 10, 15), haifaCinema, null, new ArrayList<>(), 10, null,screeningOfFilm_2,false);
			Purchase customer1 = new Purchase("Alon", "Latman2", "shiravneri@gmail.com", "Some details", "123456789",
					new Pair<Boolean, Integer>(false, 0), false, getExacTime(2021, 5, 28, 10, 15), haifaCinema, null, new ArrayList<>(), 10, null,screeningOfFilm_3,false);
			Purchase customer3 = new Purchase("Alon", "Latman3", "shiravneri@gmail.com", "Some details", "123456789",
					new Pair<Boolean, Integer>(false, 0), false, getExacTime(2021, 5, 30, 10, 15), haifaCinema, null, new ArrayList<>(), 10, null,screeningOfFilm_4,false);
			Purchase customer4 = new Purchase("Alon", "Latman4", "shiravneri@gmail.com", "Some details", "123456789",
					new Pair<Boolean, Integer>(false, 0), false, getExacTime(2021, 5, 27, 10, 15), haifaCinema, null, new ArrayList<>(), 10, null,screeningOfFilm_10,false);
			Purchase customer5 = new Purchase("Alon", "Latman5", "shiravneri@gmail.com", "Some details", "123456789",
					new Pair<Boolean, Integer>(false, 0), false, getExacTime(2021, 6, 28, 10, 15), haifaCinema, null, new ArrayList<>(), 10, null,screeningOfFilm_5,false);
			Purchase customer6 = new Purchase("Alon", "Latman6", "shiravneri@gmail.com", "Some details", "123456789",
					new Pair<Boolean, Integer>(false, 0), false, getExacTime(2021, 5, 28, 10, 15), telAvivCinema, null, new ArrayList<>(), 10, null,screeningOfFilm_6,false);
			Purchase customer7 = new Purchase("Alon", "Latman7", "shiravneri@gmail.com", "Some details", "123456789",
					new Pair<Boolean, Integer>(false, 0), false, getExacTime(2021, 6, 28, 10, 15), telAvivCinema, null, new ArrayList<>(), 10, null,screeningOfFilm_7,false);
			Purchase customer8 = new Purchase("Alon", "Latman8", "shiravneri@gmail.com", "Some details", "123456789",
					new Pair<Boolean, Integer>(false, 0), false, getExacTime(2021, 5, 28, 10, 15), telAvivCinema, null, new ArrayList<>(), 10, null,screeningOfFilm_8,false);
			Purchase customer9 = new Purchase("Alon", "Latman9", "shiravneri@gmail.com", "Some details", "123456789",
					new Pair<Boolean, Integer>(false, 0), false, getExacTime(2021, 4, 28, 10, 15), telAvivCinema, null, new ArrayList<>(), 10, null,screeningOfFilm_8,false);
			Purchase customer10 = new Purchase("Alon", "Latman22", "shiravneri@gmail.com", "Some details", "123456789",
					new Pair<Boolean, Integer>(false, 0), false, getExacTime(2021, 5, 27, 10, 15), haifaCinema, null, new ArrayList<>(), 10, null,screeningOfFilm_1,false);
			Purchase customer11 = new Purchase("Alon", "Latman33", "shiravneri@gmail.com", "Some details", "123456789",
					new Pair<Boolean, Integer>(false, 0), false, getExacTime(2021, 5, 27, 10, 15), haifaCinema, null, new ArrayList<>(), 10, null,screeningOfFilm_3,false);
			
			
			
			
			
			//session.save(customer);
			session.save(customer2);
			session.save(customer1);
			session.save(customer3);
			session.save(customer4);
			session.save(customer5);
			session.save(customer6);
			session.save(customer7);
			session.save(customer8);
			session.save(customer9);
			session.save(customer10);
			session.save(customer11);

		    LocalDate date = LocalDate.of(2021, 1, 13);  

			Complaint someComplaint1 = new Complaint("Shir", "Avneri", "I'm very upset", "I want to finish this project", true,date,true,Complaint.getComplaintTypes()[0],haifaCinema);
			Complaint someComplaint2 = new Complaint("Niv", "Sapir", "I want to complain", "I am very upset", true,date,true,Complaint.getComplaintTypes()[1],telAvivCinema);
			Complaint someComplaint3 = new Complaint("Hadar", "Manor", "Some title", "Some details" ,false,date,true,Complaint.getComplaintTypes()[2],haifaCinema);
			//Purchase customer = new Purchase("Hadar", "Manor", "Some title", "Some details" , "12312312",new Pair<Boolean, Integer>(true, 20),false,null,null,null,new ArrayList<>(),10,null);
			//session.save(customer);
			session.save(someComplaint1);
			session.save(someComplaint2);
			session.save(someComplaint3);
			session.save(haifaCinema);
			session.save(telAvivCinema);

			session.flush();

			// System.out.println(ScreeningController.pickChair(1, 1, hall4));

			session.getTransaction().commit();
			session.clear();
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			assert session != null;
			session.close();
		}

	}

	public static void main(String[] args) throws IOException {
		Main server = new Main(3000);
		if (args.length != 1) {
			System.out.println("Required argument: <port>");
		} else {
			server.listen();
			System.out.println("hello server");
		}
		addDataToDB();
	//	Purchase purchase = CustomerController.getID(10);
//		ArrayList<Worker> lol= getAllOfType(Worker.class);
//		for(Worker worker : lol) {
//			if(worker instanceof CustomerService) {
//				worker = (CustomerService)worker;
//				PurpleLimitController.SetPurpleLimit(((CustomerService) worker).getDatesOfPurpleLimit().getKey(), ((CustomerService) worker).getDatesOfPurpleLimit().getValue());
//			}
//		}
//		PurpleLimitController.SetPurpleLimit(getTime(2021,1, 1), getTime(2021,3, 4));
		//saveRowInDB(new Screening(LocalDateTime.now(), new Hall(), new Movie(), new Cinema()));

	}
	public static <T> void saveRowInDB(T objectType) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(objectType);
			session.flush();
			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			e.printStackTrace();
		} finally {
			// assert session != null;
			session.close();
			System.out.println("save the object type: " + objectType.getClass());
		}
	}

	public static void saveScreeningInDB(Screening screening) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			ArrayList<Screening> screenings = getAllOfType(Screening.class);
			screenings.add(screening);
			session.update(screenings);
			session.flush();
			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			e.printStackTrace();
		} finally {
			// assert session != null;
			session.close();
			System.out.println("save screening in database");
		}
	}

	public static <T> void deleteRowInDB(T objectType) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(objectType);
			session.flush();
			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			e.printStackTrace();
		} finally {
			// assert session != null;
			session.close();
			System.out.println("deleteRowInDB");
		}
	}

	public static <T> void updateRowDB(T objectType) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(objectType);
			session.flush();
			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			e.printStackTrace();
		} finally {
			// assert session != null;
			session.close();
			System.out.println("Complaint added to database");
		}
	}



	public static <T> ArrayList<T> getAllOfType(Class<T> objectType) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		ArrayList<T> returnedList = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> query = builder.createQuery(objectType);
			query.from(objectType);
			returnedList = (ArrayList<T>) session.createQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.out.println("catch in getalloftypes");
		} finally {
			session.close();
		}
		return returnedList;
	}

	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		// TODO Auto-generated method stub
		
		System.out.println("Client Disconnected.");
		super.clientDisconnected(client);
	}

	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		System.out.println("Client connected: " + client.getInetAddress());
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		System.out.println("message recieved " +  ((Message)msg).getAction());

		Message currentMsg = ((Message)msg);

		serverMsg = new Message();
				if(currentMsg.getAction().equals("pull movies")) {
					ArrayList<Movie> screeningMoviesArrayList = new ArrayList<>();
					ArrayList<Movie> toReturnArrayList = new ArrayList<>();
					screeningMoviesArrayList = Main.getAllOfType(Movie.class);
					for(Movie movie : screeningMoviesArrayList) {
						if(movie.isDeleted() == false && (movie.isScreening() == true || movie.isSoonInCinema()==true)) {
							toReturnArrayList.add(movie);
						}
					}
					serverMsg.setMovies(toReturnArrayList);
					serverMsg.setAction("got movies");
					try {
						client.sendToClient(serverMsg);
					} catch (IOException e) {
						System.out.println("cant create list of movies");
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

		if(currentMsg.getAction().equals("update movie time")) {

			System.out.println("about to update movie time");
			Screening newScreening = new Screening(currentMsg.getDateMovie(),ScreeningController.getHallById(currentMsg.getHallId()),
					MovieController.getMovieByName(currentMsg.getMovieName()), MovieController.getCinemaByName(currentMsg.getCinemaName()),null);
			
			
			if(currentMsg.getDbAction().equals("removal")) {
				deleteRowInDB(currentMsg.getScreening());
				serverMsg = new Message();
				currentMsg.setAction("updated movie time");
				currentMsg.setScreeningArrayList(getAllOfType(Screening.class));
				try {
					client.sendToClient(currentMsg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			
			  List<Screening> screenings = getAllOfType(Screening.class);
			  
			  
			  
			  for(Screening screening : screenings) { 
				  if(screening.getDate_screen().equals(newScreening.getDate_screen()) && 
						 screening.getCinema().getName().equals(newScreening.getCinema().getName())&&
						 screening.getMovie().getName().equals(newScreening.getMovie().getName())&&
						 screening.getHall().getHallId() == (newScreening.getHall().getHallId())) {
					  System.out.println("screening == newScreening");
					  if(currentMsg.getDbAction().equals("addition"))
					  { 
						  Message serverMsg = new
						  Message(); serverMsg.setAction("update movie time error");
						  serverMsg.setError("screening already exists");
						  try {
							  client.sendToClient(serverMsg); 
							  return; 
						  	  } catch (IOException e){
						  		  // TODOAuto-generated catch block 
						  		  e.printStackTrace(); 
						  		  } 
						  }
					  if(currentMsg.getDbAction().equals("removal")) 
					  { 
						  deleteRowInDB(screening);
						  break;
					  }
				  }
			  }
			  
			System.out.println("about to save newScreening in database");
			saveRowInDB(newScreening);
			serverMsg = new Message();
			currentMsg.setAction("updated movie time");
			currentMsg.setScreeningArrayList(getAllOfType(Screening.class));
			try {
				client.sendToClient(currentMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(currentMsg.getAction().equals("login")) {
			try {
				if(currentMsg.getUsername().equals(null) || ((Message) msg).getPassword().equals(null)) {
					System.out.println("user or password is null");
				}else {
					UserController.getUser((Message) msg);
					serverMsg = (Message) msg;
					serverMsg.setAction("login done");
					client.sendToClient(serverMsg);
				}
			} catch (IOException e) {
				System.out.println("cant login");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(currentMsg.getAction().equals("add a complaint")) {
			serverMsg.setAction("added a complaint");
			System.out.println("about to add a complaint");

			try {
				if(currentMsg.getComplaint() == null) {
					System.out.println("complaint is null in add a complaint");
				}else {
					System.out.println(((Message) msg).getComplaint().getComplaintTitle());
					saveRowInDB(((Message) msg).getComplaint());
					client.sendToClient(serverMsg);
				}
			} catch (IOException e) {
				System.out.println("cant add a complaint");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentMsg.getAction().equals("cinema contained movies")) {
			try {
				serverMsg = currentMsg;
				System.out.println("about to pull cinemas according to movie id");
				System.out.println("movie id is: " + currentMsg.getMovieId()) ;
				serverMsg.setCinemasArrayList((ArrayList<Cinema>) ScreeningController.getCinemas(currentMsg.getMovieId()));
				System.out.println("finished pulling cinemas according to movie id");
				serverMsg.setAction("cinema contained movies done");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant cinema contained movies");
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		if(currentMsg.getAction().equals("screening for movie")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setScreeningArrayList((ArrayList<Screening>) ScreeningController.getAllDateOfMovie(serverMsg.getMovieId(), serverMsg.getCinemaId()));
				serverMsg.setAction("screening for movie done");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant screening for movie");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentMsg.getAction().equals("pull soon movies")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setMovies((ArrayList<Movie>) MovieController.getSoonMovies()); 
				serverMsg.setAction("got soon movies");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant pull soon movies");
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		if(currentMsg.getAction().equals("pull screening movies")) {
			try {
				serverMsg = currentMsg;
				System.out.println("in Main pull screeening movies msg");

				serverMsg.setMovies((ArrayList<Movie>) MovieController.getAllScreeningMovies());
				System.out.println("in the func handleMessageFromClient");
				serverMsg.setAction("got screening movies");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant pull screening movies");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentMsg.getAction().equals("pull soon movies genre")) {
			try {
				System.out.println("in Main genre pull screeening movies msg");
				serverMsg = (Message) msg;
				serverMsg.setMovies((ArrayList<Movie>) MovieController.getGenreTypeMovies(serverMsg.getGenre()));
				System.out.println("in the func handleMessageFromClient");
				serverMsg.setAction("got screening movies");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant pull screening movies");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(currentMsg.getAction().equals("sort movies by genre")) {     //from here *********
			try {
				serverMsg = (Message) msg;
				serverMsg.setMovies((ArrayList<Movie>) MovieController.MoviesByGener(serverMsg.getGenre())); 
				serverMsg.setAction("sorted movies by genre");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant pull screening movies");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentMsg.getAction().equals("sort movies by date")) {
			try {
				serverMsg = (Message) msg;
				serverMsg.setMovies((ArrayList<Movie>) MovieController.MoviesByDate(serverMsg.getDateMovie())); 
				serverMsg.setAction("done to sort by date");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant pull screening movies");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentMsg.getAction().equals("sort movies by popular")) {
			try {
				serverMsg = (Message) msg;
				serverMsg.setMovies((ArrayList<Movie>) MovieController.MoviesByPopularty()); 
				serverMsg.setAction("done to sort by popular");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant pull screening movies");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentMsg.getAction().equals("pull movies from home")) {
			try {
				ArrayList<Movie> movies = ((ArrayList<Movie>) MovieController.WatchingFromHome()); 
				if(currentMsg.getRate() != null) {
					
					Iterator<Movie> iter = movies.iterator();
					
					while(iter.hasNext()) {
						Movie movie = iter.next();
						if(Double.parseDouble(currentMsg.getRate()) != movie.getPopular()) {
							iter.remove();
						}
					}
				}
				
				if(currentMsg.getSearch() != null) {
					
					Iterator<Movie> iter = movies.iterator();
					
					while(iter.hasNext()) {
						Movie movie = iter.next();
						if(!movie.getName().contains(currentMsg.getSearch())) {
							iter.remove();
						}
					}
				}
				
				Message serverMsg = new Message();
				serverMsg.setAction("got movies from home");
				serverMsg.setMovies(movies);
				client.sendToClient(serverMsg);
				
			}
			catch (IOException e) {
				System.out.println("cant add movie");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(currentMsg.getAction().equals("picking chair")) {
			try {
				serverMsg = currentMsg;
				Screening clientScreening = serverMsg.getScreening();
				Screening serverScreening = ScreeningController.getScreening(clientScreening.getId());
				if(ScreeningController.pickChair(clientScreening.getSeats(), serverScreening)) {
					serverMsg.setAction("picking seats success");
					for(int i = 0 ; i < clientScreening.getHall().getRows() ; i++) {
						for(int j = 0 ; j < clientScreening.getHall().getCols() ; j++) {
							if(clientScreening.getSeats()[i][j] == 2) {
								clientScreening.getSeats()[i][j] = 1;
							}
						}
					}
					serverMsg.setScreening(clientScreening);
					updateRowDB(clientScreening);
				}else {
					serverMsg.setAction("picking seats error");
					serverMsg.setError("Seats have already been chosen by another customer, please choose again");
					serverMsg.setScreening(serverScreening);

				}
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant picking chair");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*if(currentMsg.getAction().equals("pull current complaint")) {
			try {
				serverMsg = currentMsg;
				System.out.println("in pull current complaint");
				serverMsg.setComplaints((ArrayList<Complaint>) CustomerController.getAllCurrentComplaints());
				System.out.println("in the func handleMessageFromClient");
				serverMsg.setAction("got complaints");
	            System.out.println(serverMsg.getComplaints().toString());
				/*for (Complaint model : serverMsg.getComplaints()) {
		            System.out.println(model.getFirstName());
		        }
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant pull complaints");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		if(currentMsg.getAction().equals("save customer")) { // save ticket // save customer
			try {

				serverMsg = currentMsg;

				saveRowInDB(serverMsg.getPurchase());
				serverMsg.setAction("save customer done");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant save customer");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("123");
		if(currentMsg.getAction().equals("get purchase by id")) {
			try {
				serverMsg = currentMsg;
				System.out.println("1");
				serverMsg.setPurchase(CustomerController.getID(serverMsg.getId()));
				System.out.println("2");
				if(serverMsg.getPurchase()!=null) {
					serverMsg.setPayment(CustomerController.ReturnOnPurchase(serverMsg.getPurchase(), LocalDateTime.now()));}
				System.out.println("3");
				serverMsg.setAction("got purchase by id");
				if(serverMsg.getPurchase()!= null && serverMsg.getPurchase().isCanceled())
					serverMsg.setPurchase(null);
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant get purchase by id");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("321");
		if(currentMsg.getAction().equals("get purchases")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPurchasesList(getAllOfType(Purchase.class));
				serverMsg.setAction("got purchases");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant get purchase by id");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(currentMsg.getAction().equals("get cinemas and purchases and complaints")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPurchasesList(getAllOfType(Purchase.class));
				serverMsg.setCinemasArrayList(getAllOfType(Cinema.class));
				serverMsg.setComplaints(getAllOfType(Complaint.class));
				serverMsg.setAction("got cinemas and purchases and complaints");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant get purchase by id");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(currentMsg.getAction().equals("get report ticket")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPurchasesList(ReportController.getTicketReportMonthly(serverMsg.getMonth(), serverMsg.getCinema()));
				serverMsg.setAction("got report ticket");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant get report ticket");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(currentMsg.getAction().equals("get report special ticket")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPurchasesList(ReportController.getSpecialTicketReportMonthly(serverMsg.getMonth(), serverMsg.getCinema()));
				serverMsg.setAction("got report special ticket");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant get report special ticket");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentMsg.getAction().equals("get status complaints monthly")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPurchasesList(ReportController.statusComplaintsMonthly(serverMsg.getMonth(), serverMsg.getCinema()));
				serverMsg.setAction("got status complaints monthly");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant get status complaints monthly");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(((Message) msg).getAction().equals("pull genre screening movies")) {
			try {
				System.out.println("in Main pull screeening movies msg");
				serverMsg = (Message) msg;
				serverMsg.genreArray=MovieController.getAllGenreScreeningMovies().toArray(new String[0]); 
				System.out.println("in the func handleMessageFromClient");
				serverMsg.setAction("got genre screening movies");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant pull screening movies");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(currentMsg.getAction().equals("send successful purchase mail")) {
			try {
				serverMsg = currentMsg;
				JavaMailUtil.sendMessage(serverMsg.getCustomerEmail(), "Thank you for your purchase at The Sirtiya!", serverMsg.getEmailMessage());
				serverMsg.setAction("sent successful purchase mail");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant get status complaints monthly");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(currentMsg.getAction().equals("send purchase cancellation mail")) {
			try {
				serverMsg = currentMsg;
				
				JavaMailUtil.sendPurchaseCancellationMessage(serverMsg.getPurchase().getEmailOrder(), serverMsg.getPurchase().getFirstName() + " " + serverMsg.getPurchase().getLastName(), String.valueOf(serverMsg.getPurchase().getId()), serverMsg.getPayment());
				serverMsg.setAction("sent purchase cancellation mail");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant send purchase cancellation mail");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(currentMsg.getAction().equals("get all screenings")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setScreeningArrayList(getAllOfType(Screening.class));
				serverMsg.setAction("got all screenings");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant get status complaints monthly");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentMsg.getAction().equals("add movie")) {
			try {
				serverMsg = currentMsg;
				saveRowInDB(serverMsg.getMovie());
				serverMsg.setAction("added movie");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant add movie");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentMsg.getAction().equals("delete movie")) {
			try {
				serverMsg = currentMsg;
				serverMsg.getMovie().setDeleted(true);
				updateRowDB(serverMsg.getMovie());
				serverMsg.setAction("deleted movie");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant deleted movie");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentMsg.getAction().equals("delete a viewing package")) {
			try {
				serverMsg = currentMsg;
				serverMsg.getMovie().setStreamOnline(false);
				updateRowDB(serverMsg.getMovie());
				serverMsg.setAction("deleted a viewing package");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant deleted movie");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(currentMsg.getAction().equals("set purple limit")) {
			try {
				serverMsg = currentMsg;
				((CustomerService)serverMsg.getWorker()).setPurpleLimit(true);
				updateRowDB(serverMsg.getWorker());
				Worker worker = serverMsg.getWorker();
				PurpleLimitController.SetPurpleLimit(((CustomerService) worker).getDatesOfPurpleLimit().getKey(), ((CustomerService) worker).getDatesOfPurpleLimit().getValue());
				serverMsg.setAction("added movie");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant add movie");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(currentMsg.getAction().equals("search bar update")) {
			try {
				ArrayList<Movie> movies = new ArrayList<Movie>();
				List<Screening> screenings = getAllOfType(Screening.class);
				
				if(currentMsg.getTheater() != null) {
					Iterator<Screening> iters = screenings.iterator();
					while(iters.hasNext()) {
						Screening s = iters.next();
						if(!s.getCinema().getName().equals(currentMsg.getTheater())) {
							System.out.println("removed screening in search bar update");
							iters.remove();
						}
					}
					
					for(Screening screening : screenings) {
						if(!movies.contains(screening.getMovie())){
							movies.add(screening.getMovie());
						}
					}
				}else {
					movies = getAllOfType(Movie.class);
				}
				
				
				if(currentMsg.getRate() != null) {
					
					Iterator<Movie> iter = movies.iterator();
					
					while(iter.hasNext()) {
						Movie movie = iter.next();
						if(Double.parseDouble(currentMsg.getRate()) != movie.getPopular()) {
							iter.remove();
						}
					}
				}
				
				if(currentMsg.getSearch() != null) {
					
					Iterator<Movie> iter = movies.iterator();
					
					while(iter.hasNext()) {
						Movie movie = iter.next();
						if(!movie.getName().contains(currentMsg.getSearch())) {
							iter.remove();
						}
					}
				}
				
				Message serverMsg = new Message();
				serverMsg.setAction("got screening movies");
				serverMsg.setMovies(movies);
				client.sendToClient(serverMsg);
				
			}
			catch (IOException e) {
				System.out.println("cant add movie");
			}
		}
		
		if(currentMsg.getAction().equals("cancellation of purchase")) {
			try {
				serverMsg = currentMsg;
				serverMsg.getPurchase().getCinema().getCancelPurchases().add(serverMsg.getPurchase());
				serverMsg.getPurchase().setCanceled(true);
				updateRowDB(serverMsg.getPurchase());
				updateRowDB(serverMsg.getPurchase().getCinema());
				serverMsg.setAction("got purchase cancelation by id");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant cancellation of purchase");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(currentMsg.getAction().equals("check purple limit")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setStatus(PurpleLimitController.CheckPurpleLimit(serverMsg.getDateMovie()));
				serverMsg.setAction("done check purple limit");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant check purple limit");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(currentMsg.getAction().equals("selection of seats under restrictions")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setSeats(PurpleLimitController.SetSeatsPurpleLimit(serverMsg.getScreening(), serverMsg.getNumOfSeats()));
				serverMsg.setAction("done selection of seats under restrictions");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant selection of seats under restrictions");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void updateMovie(String movieName, String time, String action, ConnectionToClient client) {
		boolean timeChanged = false;
		boolean error = false;
		Message msg = new Message();
		System.out.println("1");
		try {

			ArrayList<Movie> movies = getAllOfType(Movie.class);
			System.out.println("1.5");

			session = sessionFactory.openSession();
			session.beginTransaction();

			System.out.println("1.7");
			// find movie
			for (Movie movie : movies) {
				System.out.println("1.9");

				if (movie.getName().equals(movieName)) {
					if (action.equals("addition")) {
						// if time dosent exist we goochi
						if (movie.getMovieBeginingTime().indexOf(time) == -1) {
							movie.getMovieBeginingTime().add(time);
							timeChanged = true;
							System.out.println("2");
						} else {
							error = true;
							msg.setError("Screening already exists!");
							System.out.println("3");
						}
					} else {
						// if action is removal
						Integer index = movie.getMovieBeginingTime().indexOf(time);
						if (index == -1) {
							// time for removal does not exist
							System.out.println("4");
							error = true;
							msg.setError("Selected time does not exist for this movie");
						} else {
							/*
							 * if(index != -1) { movie.getMovieBeginingTime().remove(index); timeChanged =
							 * true; }else { JOptionPane.showMessageDialog(null, "Incorrect time chosen"); }
							 */
							System.out.println("5");
							for (String mTime : movie.getMovieBeginingTime()) {
								if (time.equals(mTime)) {
									movie.getMovieBeginingTime().remove(time);
									timeChanged = true;
									break;
								}
							}
						}
					}
					if (timeChanged) {
						session.update(movie);
						session.flush();
						session.getTransaction().commit();
						session.clear();
						System.out.println("6");
						msg.setAction("updated movie time");
						client.sendToClient(msg);
						break;
					}
					if (error) {
						System.out.println("7");
						msg.setAction("	 error");
						client.sendToClient(msg);
						break;
					}

				}
			}

		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			assert session != null;
			session.close();
		}

	}


	//	public static <T> T getExacRow(Class<T> objectType , int id) {
	//		T t = null;
	//		try {
	//			session = sessionFactory.openSession();
	//			session.beginTransaction();
	//			
	//			//System.out.println(12);
	//			t = session.load(objectType , id);
	//			System.out.println(((Cinema)t).getName());
	//		}catch (Exception e) {
	//			e.printStackTrace();
	//			if (session != null) {
	//				session.getTransaction().rollback();
	//			}
	//			System.out.println("exan row loader");
	//		}
	//		finally {
	//			session.close();
	//		}
	//		
	//		return t;
	//		
	//	}

}
