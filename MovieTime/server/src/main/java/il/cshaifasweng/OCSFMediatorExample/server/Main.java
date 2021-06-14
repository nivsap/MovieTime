package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


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
import il.cshaifasweng.OCSFMediatorExample.entities.PriceRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Purchase;
import il.cshaifasweng.OCSFMediatorExample.entities.PurpleLimit;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.SubscriptionCard;
import il.cshaifasweng.OCSFMediatorExample.entities.ViewingPackage;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import javafx.util.Pair;

public class Main extends AbstractServer {
	private static SessionFactory sessionFactory = getSessionFactory();
	private static Session session;
	private static ArrayList<String> genres = new ArrayList<String>(Arrays.asList("Action", "Adventure", "Animation", "Comedy", "Crime", "Drama", "Fiction", "Fantasy", "Horror", "Mystery", "Romance", "Science", "Thriller", "Other"));
	private Message serverMsg;
	
	public Main(int port) {
		super(port);
	}

	public static SessionFactory getSessionFactory() throws HibernateException {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Worker.class);
		configuration.addAnnotatedClass(NetworkAdministrator.class);
		configuration.addAnnotatedClass(ContentManager.class);
		configuration.addAnnotatedClass(BranchManager.class);
		configuration.addAnnotatedClass(CustomerService.class);
		configuration.addAnnotatedClass(Cinema.class);
		configuration.addAnnotatedClass(Hall.class);
		configuration.addAnnotatedClass(Screening.class);
		configuration.addAnnotatedClass(Movie.class);
		configuration.addAnnotatedClass(ViewingPackage.class);
		configuration.addAnnotatedClass(SubscriptionCard.class);
		configuration.addAnnotatedClass(Purchase.class);
		configuration.addAnnotatedClass(Complaint.class);
		configuration.addAnnotatedClass(PurpleLimit.class);
		configuration.addAnnotatedClass(PriceRequest.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static LocalDateTime getTime(int year, int month, int day) {
		return LocalDate.of(year, month, day).atStartOfDay();
	}

	private static LocalDateTime getExactTime(int year, int month, int day, int hours, int minutes) {
		return LocalDate.of(year, month, day).atTime(hours, minutes);
	}

	public static void addDataToDB() {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			/* ---------- Setting Workers For Data Base ---------- */
			Worker hadarWorker = new NetworkAdministrator("Hadar", "Manor", null, "hadar", "hadar", false, 40f, 30f, 600f);
			Worker shirWorker = new BranchManager("Shir", "Avneri", null, "shir", "shir", false);
			Worker nivWorker = new BranchManager("Niv", "Sapir", null, "niv", "niv", false);
			Worker lielWorker = new ContentManager("Liel", "Fridman", null, "liel", "liel", false);
			Worker eitanWorker = new CustomerService("Eitan", "Sharabi", null, "asaf", "asaf", false);
			Worker alonWorker = new CustomerService("Alon", "Latman", null, "alon", "alon", false);
			/* ---------- Setting Cinema For Data Base ---------- */
			Cinema haifaCinema = new Cinema("Haifa", "Haifa, Carmel st.", (BranchManager)shirWorker, new ArrayList<Worker>(Arrays.asList(shirWorker, alonWorker)), null, null, null, null, null);
			Cinema telAvivCinema = new Cinema("Tel-Aviv", "Tel-Aviv, Wieztman st.", (BranchManager)nivWorker, new ArrayList<Worker>(Arrays.asList(nivWorker, eitanWorker)), null, null, null, null, null);
			shirWorker.setCinema(haifaCinema); alonWorker.setCinema(haifaCinema); nivWorker.setCinema(telAvivCinema); eitanWorker.setCinema(telAvivCinema);
			/* ---------- Setting Halls For Data Base ---------- */
			Hall hall1 = new Hall(5, 10, haifaCinema, null);
			Hall hall2 = new Hall(7, 10, haifaCinema, null);
			Hall hall3 = new Hall(6, 11, telAvivCinema, null);
			Hall hall4 = new Hall(5, 10, telAvivCinema, null);
			haifaCinema.setHalls(new ArrayList<Hall>(Arrays.asList(hall1, hall2)));
			telAvivCinema.setHalls(new ArrayList<Hall>(Arrays.asList(hall3, hall4)));
			/* ---------- Setting Movies For Data Base ---------- */
			String absPath = Paths.get("").toAbsolutePath().toString();
			String picPath = absPath.substring(0, absPath.length() - 6);
			Movie avengersEndgame = new Movie("Avengers: Endgame", "Action   •   Adventure   •   Drama",
					"After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
					"Anthony Russo, Joe Russo", "Robert Downey Jr., Chris Evans, Mark Ruffalo", LocalDate.of(2019, 4, 26), 3, 1, 5f,
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/AvengersEndgame.jpg",
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/LargeImages/AvengersEndgame.png",
					false, false, null, null);
			Movie sherlockHolmes = new Movie("Sherlock Holmes", "Action   •   Adventure   •   Mystery", 
					"Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England.",
					"Guy Ritchie", "Robert Downey Jr., Jude Law, Rachel McAdams", LocalDate.of(2009, 12, 25), 2, 8, 4.5f,
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/SherlockHolmes.jpg",
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/LargeImages/SherlockHolmes.png",
					false, false,  null, null);
			Movie babyDriver = new Movie("Baby Driver", "Action   •   Crime   •   Drama ",
					"After being coerced into working for a crime boss, a young getaway driver finds himself taking part in a heist doomed to fail.",
					"Edgar Wright", "Ansel Elgort, Jon Bernthal, Jon Hamm", LocalDate.of(2017, 6, 28), 1, 53, 4f,
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/BabyDriver.jpg",
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/LargeImages/BabyDriver.png",
					false, false, null, null);
			Movie wonderWoman1984 = new Movie("Wonder Woman 1984", "Action   •   Adventure   •   Fantasy",
					"Diana must contend with a work colleague and businessman, whose desire for extreme wealth sends the world down a path of destruction, after an ancient artifact that grants wishes goes missing.",
					"Patty Jenkins", "Gal Gadot, Chris Pine, Kristen Wiig", LocalDate.of(2020, 12, 21), 2, 31, 5f,
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/WonderWoman1984.jpg",
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/LargeImages/WonderWoman1984.png",
					false, false, null, null);
			Movie it = new Movie("IT", "Horror",
					"In the summer of 1989, a group of bullied kids band together to destroy a shape-shifting monster, which disguises itself as a clown and preys on the children of Derry, their small Maine town.",
					"Andy Muschietti", "Bill Skarsgard, Jaeden Martell, Finn Wolfhard", LocalDate.of(2017, 9, 8), 2, 15, 5f,
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/It.jpg",
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/LargeImages/It.png",
					false, false, null, null);
			Movie toyStory = new Movie("Toy Story", "Animation   •   Adventure   •   Comedy",
					"When a new toy called 'Forky' joins Woody and the gang, a road trip alongside old and new friends reveals how big the world can be for a toy.",
					"Josh Cooley", "Tom Hanks, Tim Allen, Annie Potts", LocalDate.of(2017, 6, 21), 1, 40, 5f,
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/ToyStory.jpg",
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/LargeImages/ToyStory.png",
					true, false, null, null);
			Movie minions = new Movie("Minions", "Animation   •   Adventure   •   Comedy",
					"Minions Stuart, Kevin, and Bob are recruited by Scarlet Overkill, a supervillain who, alongside her inventor husband Herb, hatches a plot to take over the world.",
					"Kyle Balda, Pierre Coffin", "Sandra Bullock, Jon Hamm, Michael Keaton", LocalDate.of(2015, 7, 10), 1, 31, 4.5f,
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/Minions.jpg",
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/LargeImages/Minions.png",
					true, false, null, null);
			Movie starWars = new Movie("Star Wars", "Action   •   Adventure   •   Fantasy", 
					"The surviving members of the Resistance face the First Order once again, and the legendary conflict between the Jedi and the Sith reaches its peak, bringing the Skywalker saga to its end.",
					"J.J. Abrams", "Daisy Ridley, John Boyega, Oscar Isaac", LocalDate.of(2019, 12, 20), 2, 21, 5f,
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/StarWars.jpg",
					picPath + "client/src/main/resources/il/cshaifasweng/OCSFMediatorExample/client/images/MoviesPosters/LargeImages/StarWars.png",
					true, false, null, null);
			/* ---------- Setting Viewing Packages For Data Base ---------- */
			ViewingPackage viewingPackage1 = new ViewingPackage(wonderWoman1984, getExactTime(2021, 6, 18, 16, 00), "www.sirtiya.co.il/wonderWoman1");
			ViewingPackage viewingPackage2 = new ViewingPackage(wonderWoman1984, getExactTime(2021, 6, 19, 16, 00), "www.sirtiya.co.il/wonderWoman2");
			ViewingPackage viewingPackage3 = new ViewingPackage(wonderWoman1984, getExactTime(2021, 6, 20, 16, 00), "www.sirtiya.co.il/wonderWoman3");
			ViewingPackage viewingPackage4 = new ViewingPackage(babyDriver, getExactTime(2021, 6, 18, 17, 00), "www.sirtiya.co.il/babyDriver1");
			ViewingPackage viewingPackage5 = new ViewingPackage(babyDriver, getExactTime(2021, 6, 19, 17, 00), "www.sirtiya.co.il/babyDriver2");
			ViewingPackage viewingPackage6 = new ViewingPackage(babyDriver, getExactTime(2021, 6, 20, 17, 00), "www.sirtiya.co.il/babyDriver3");
			wonderWoman1984.setViewingPackages(new ArrayList<ViewingPackage>(Arrays.asList(viewingPackage1, viewingPackage2, viewingPackage3)));
			babyDriver.setViewingPackages(new ArrayList<ViewingPackage>(Arrays.asList(viewingPackage4, viewingPackage5, viewingPackage6)));
			/* ---------- Setting Screenings For Data Base ---------- */
			Screening screening1 = new Screening(getExactTime(2021, 6, 17, 20, 00), avengersEndgame, hall1, haifaCinema, null);
			Screening screening2 = new Screening(getExactTime(2021, 6, 18, 20, 00), avengersEndgame, hall1, haifaCinema, null);
			Screening screening3 = new Screening(getExactTime(2021, 6, 17, 20, 00), avengersEndgame, hall3, telAvivCinema, null);
			Screening screening4 = new Screening(getExactTime(2021, 6, 18, 20, 00), avengersEndgame, hall3, telAvivCinema, null);
			Screening screening5 = new Screening(getExactTime(2021, 6, 17, 17, 00), sherlockHolmes, hall2, haifaCinema, null);
			Screening screening6 = new Screening(getExactTime(2021, 6, 18, 17, 00), sherlockHolmes, hall2, haifaCinema, null);
			Screening screening7 = new Screening(getExactTime(2021, 6, 17, 17, 00), sherlockHolmes, hall4, telAvivCinema, null);
			Screening screening8 = new Screening(getExactTime(2021, 6, 18, 17, 00), sherlockHolmes, hall4, telAvivCinema, null);
			Screening screening9 = new Screening(getExactTime(2021, 6, 17, 20, 30), babyDriver, hall2, haifaCinema, null);
			Screening screening10 = new Screening(getExactTime(2021, 6, 18, 20, 30), babyDriver, hall2, haifaCinema, null);
			Screening screening11 = new Screening(getExactTime(2021, 6, 17, 20, 30), babyDriver, hall4, telAvivCinema, null);
			Screening screening12 = new Screening(getExactTime(2021, 6, 18, 20, 30), babyDriver, hall4, telAvivCinema, null);
			Screening screening13 = new Screening(getExactTime(2021, 6, 17, 17, 30), wonderWoman1984, hall1, haifaCinema, null);
			Screening screening14 = new Screening(getExactTime(2021, 6, 18, 17, 30), wonderWoman1984, hall1, haifaCinema, null);
			Screening screening15 = new Screening(getExactTime(2021, 6, 17, 17, 30), wonderWoman1984, hall3, telAvivCinema, null);
			Screening screening16 = new Screening(getExactTime(2021, 6, 18, 17, 30), wonderWoman1984, hall3, telAvivCinema, null);
			Screening screening17 = new Screening(getExactTime(2021, 6, 17, 00, 00), it, hall1, haifaCinema, null);
			Screening screening18 = new Screening(getExactTime(2021, 6, 18, 00, 00), it, hall1, haifaCinema, null);
			Screening screening19 = new Screening(getExactTime(2021, 6, 17, 00, 00), it, hall3, telAvivCinema, null);
			Screening screening20 = new Screening(getExactTime(2021, 6, 18, 00, 00), it, hall3, telAvivCinema, null);
			
			avengersEndgame.setScreenings(new ArrayList<Screening>(Arrays.asList(screening1, screening2, screening3, screening4)));
			sherlockHolmes.setScreenings(new ArrayList<Screening>(Arrays.asList(screening5, screening6, screening7, screening8)));
			babyDriver.setScreenings(new ArrayList<Screening>(Arrays.asList(screening9, screening10, screening11, screening12)));
			wonderWoman1984.setScreenings(new ArrayList<Screening>(Arrays.asList(screening13, screening14, screening15, screening16)));
			it.setScreenings(new ArrayList<Screening>(Arrays.asList(screening17, screening18, screening19, screening20)));
			
			hall1.setScreenings(new ArrayList<Screening>(Arrays.asList(screening1, screening2, screening13, screening14, screening17, screening18)));
			hall2.setScreenings(new ArrayList<Screening>(Arrays.asList(screening5, screening6, screening9, screening10)));
			hall3.setScreenings(new ArrayList<Screening>(Arrays.asList(screening3, screening4, screening15, screening16, screening19, screening20)));
			hall4.setScreenings(new ArrayList<Screening>(Arrays.asList(screening7, screening8, screening11, screening12)));

			haifaCinema.setScreenings(new ArrayList<Screening>(Arrays.asList(screening1, screening2, screening5, screening6, screening9, screening10, screening13, screening14, screening17, screening18)));
			telAvivCinema.setScreenings(new ArrayList<Screening>(Arrays.asList(screening3, screening4, screening7, screening8, screening11, screening12, screening15, screening16, screening19, screening20)));
			/* ---------- Setting Purple Limits For Data Base ---------- */
			PurpleLimit purpleLimit1 = new PurpleLimit(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 3, 1), 40);
			PurpleLimit purpleLimit2 = new PurpleLimit(LocalDate.of(2021, 5, 5), LocalDate.of(2021, 6, 17), 40);
			/* ---------- Setting Subscription Cards For Data Base ---------- */
			SubscriptionCard card1 = new SubscriptionCard(); card1.setRemaining(17);
			SubscriptionCard card2 = new SubscriptionCard(); card2.setRemaining(19);
			SubscriptionCard card3 = new SubscriptionCard(); card3.setRemaining(20);
			/* ---------- Setting Purchases For Data Base ---------- */
			// Tickets Purchases
			Purchase customer1 = new Purchase("Shir", "Avneri", "shiravneri@gmail.com", "street 1, city", "0523456789", 40.0, getTime(2021, 6, 10), screening1, new ArrayList<>(), null);
			Purchase customer2 = new Purchase("Eitan", "Sharabi", "shiravneri@gmail.com", "street 2, city", "0523456789", 40.0, getTime(2021, 6, 10), screening5, new ArrayList<>(), null);
			Purchase customer3 = new Purchase("Niv", "Sapir", "eitansharabi@gmail.com", "street 3, city", "0523456789", 40.0, getTime(2021, 6, 10), screening3, new ArrayList<>(), null);
			Purchase customer4 = new Purchase("Hadar", "Manor", "shiravneri@gmail.com", "street 4, city", "0523456789", 40.0, getTime(2021, 6, 10), screening20, new ArrayList<>(), null);
			
			screening1.setPurchases(new ArrayList<Purchase>(Arrays.asList(customer1))); screening3.setPurchases(new ArrayList<Purchase>(Arrays.asList(customer3))); 
			screening5.setPurchases(new ArrayList<Purchase>(Arrays.asList(customer2))); screening20.setPurchases(new ArrayList<Purchase>(Arrays.asList(customer4)));
			
			haifaCinema.setPurchases(new ArrayList<Purchase>(Arrays.asList(customer1, customer2))); 
			telAvivCinema.setPurchases(new ArrayList<Purchase>(Arrays.asList(customer3, customer4)));
			// Viewing Package Purchases
			Purchase customer5 = new Purchase("Alon", "Latman", "shiravneri@gmail.com", "street 5, city", "0523456789", 30.0, getTime(2021, 6, 9), null, viewingPackage1, null);
			Purchase customer6 = new Purchase("Liel", "Fridman", "shiravneri@gmail.com", "street 6, city", "0523456789", 30.0,  getTime(2021, 6, 9), null, viewingPackage2, null);
			Purchase customer7 = new Purchase("Asaf", "Moshe", "shiravneri@gmail.com", "street 7, city", "0523456789", 30.0, getTime(2021, 6, 10), null, viewingPackage3, null);
			Purchase customer8 = new Purchase("Malki", "Grossman", "shiravneri@gmail.com", "street 8, city", "0523456789", 30.0,  getTime(2021, 6, 10), null, viewingPackage4, null);
			// Subscription Cards Purchases
			Purchase customer9 = new Purchase("Liel", "Fridman", "shiravneri@gmail.com", "street 9, city", "0523456789", 600.0, getTime(2021, 6, 5), null, card1, null);
			Purchase customer10 = new Purchase("Malki", "Grossman", "shiravneri@gmail.com", "street 10, city", "0523456789", 600.0, getTime(2021, 6, 5), null, card2, null);
			Purchase customer11 = new Purchase("Asaf", "Moshe", "shiravneri@gmail.com", "street 11, city", "0523456789", 600.0, getTime(2021, 6, 5), null, card3, null);
			card1.setPurchase(customer9); card2.setPurchase(customer10); card3.setPurchase(customer11); 
			/* ---------- Setting Complaints For Data Base ---------- */
			Complaint complaint1 = new Complaint("Shir", "Avneri", "shiravneri@gmail.com", "0523456789", Complaint.getComplaintTypes()[0], "I'm very upset", "I want to finish this project", customer1, true);
			Complaint complaint2 = new Complaint("Niv", "Sapir", "shiravneri@gmail.com", "0523456789", Complaint.getComplaintTypes()[0], "I want to complain", "I am very upset", customer2, true);
			Complaint complaint3 = new Complaint("Hadar", "Manor", "shiravneri@gmail.com", "0523456789", Complaint.getComplaintTypes()[0], "Some title", "Some details", customer3, false);
			
			haifaCinema.setComplaints(new ArrayList<Complaint>(Arrays.asList(complaint1, complaint2))); 
			telAvivCinema.setComplaints(new ArrayList<Complaint>(Arrays.asList(complaint3)));
			/* ---------- Setting Price Request For Data Base ---------- */
			PriceRequest request1 = new PriceRequest(0, "I think the price is too low.", 40f, 50f, true, null);
			PriceRequest request2 = new PriceRequest(1, "I think the price is too high.", 30f, 20f, true, null);		
			/* ---------- Saving Workers To Data Base ---------- */
			session.save(hadarWorker);
			session.save(shirWorker);
			session.save(nivWorker);
			session.save(lielWorker);
			session.save(eitanWorker);
			session.save(alonWorker);
			/* ---------- Saving Cinema To Data Base ---------- */
			session.save(haifaCinema);
			session.save(telAvivCinema);
			/* ---------- Saving Halls To Data Base ---------- */
			session.save(hall1);
			session.save(hall2);
			session.save(hall3);
			session.save(hall4);
			/* ---------- Saving Movies To Data Base ---------- */
			session.save(avengersEndgame);
			session.save(sherlockHolmes);
			session.save(babyDriver);
			session.save(wonderWoman1984);
			session.save(it);
			session.save(toyStory);
			session.save(minions);
			session.save(starWars);

			/* ---------- Saving Viewing Packages To Data Base ---------- */
			session.save(viewingPackage1);
			session.save(viewingPackage2);
			session.save(viewingPackage3);
			session.save(viewingPackage4);
			session.save(viewingPackage5);
			session.save(viewingPackage6);
			/* ---------- Saving Screenings To Data Base ---------- */
			session.save(screening1);
			session.save(screening2);
			session.save(screening3);
			session.save(screening4);
			session.save(screening5);
			session.save(screening6);
			session.save(screening7);
			session.save(screening8);
			session.save(screening9);
			session.save(screening10);
			session.save(screening11);
			session.save(screening12);
			session.save(screening13);
			session.save(screening14);
			session.save(screening15);
			session.save(screening16);
			session.save(screening17);
			session.save(screening18);
			session.save(screening19);
			session.save(screening20);
			/* ---------- Saving Purple Limits To Data Base ---------- */
			session.save(purpleLimit1);
			session.save(purpleLimit2);
			/* ---------- Saving Subscription Cards To Data Base ---------- */
			session.save(card1);
			session.save(card2);
			session.save(card3);
			/* ---------- Saving Purchases To Data Base ---------- */
			session.save(customer1);
			session.save(customer2);
			session.save(customer3);
			session.save(customer4);
			session.save(customer5);
			session.save(customer6);
			session.save(customer7);
			session.save(customer8);
			session.save(customer9);
			session.save(customer10);
			session.save(customer11);
			/* ---------- Saving Complaints To Data Base ---------- */
			session.save(complaint1);
			session.save(complaint2);
			session.save(complaint3);
			/* ---------- Saving Requests To Data Base ---------- */
			session.save(request1);
			session.save(request2);
			// DONE!
			session.flush();
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
		Thread timerThread = new Thread(() -> {
			synchronized (Purchase.class) {
			while (true) {
				List<Purchase> list = getAllOfType(Purchase.class);
				for (Purchase i : list) {
					if (i.isLink() && i.getViewingPackage().getDateTime().getDayOfYear() == LocalDateTime.now().getDayOfYear()
							&& i.getViewingPackage().getDateTime().getHour() - 1 == LocalDateTime.now().getHour()
							&& i.getViewingPackage().getDateTime().getMinute() == LocalDateTime.now().getMinute()) {
						JavaMailUtil.sendMessage(i.getEmail(), "hadye", "hadye");
					}
				}
				try {
					Thread.sleep(55000); // 55 second
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				List<Complaint> listComplaints = getAllOfType(Complaint.class);
				for (Complaint i : listComplaints) {
					if(i.isOpen() && i.getComplaintDate().getDayOfYear() - 1 == LocalDate.now().getDayOfYear() && i.getComplaintTime().getHour() == LocalTime.now().getHour() && i.getComplaintTime().getMinute()  == LocalTime.now().getMinute()) {
						JavaMailUtil.sendMessage(i.getEmail(), "close complain", "close complain");
						System.out.println("123");
						i.setIsOpen(false);
						updateRowDB(i);
					}
				}
				try {
					Thread.sleep(55000); // 55 second
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}}
		});
		timerThread.start();
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
			session.close();
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
		System.out.println("message recieved " + ((Message) msg).getAction());

		Message currentMsg = ((Message) msg);

		serverMsg = new Message();
		if (currentMsg.getAction().equals("pull movies")) {
			ArrayList<Movie> screeningMoviesArrayList = new ArrayList<>();
			ArrayList<Movie> toReturnArrayList = new ArrayList<>();
			screeningMoviesArrayList = Main.getAllOfType(Movie.class);
			for (Movie movie : screeningMoviesArrayList) {
				if (!movie.isDeleted()) {
					toReturnArrayList.add(movie);
				}
			}
			serverMsg.setMovies(toReturnArrayList);
			serverMsg.setAction("got movies");
			try {
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant create list of movies");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("update movie time")) {

			System.out.println("about to update movie time");
			Screening newScreening = new Screening(currentMsg.getScreeningDate(), MovieController.getMovieByName(currentMsg.getMovieName()),
					ScreeningController.getHallById(currentMsg.getHallId()),
					CinemaController.getCinemaByName(currentMsg.getCinemaName()), null);

			if (currentMsg.getDBAction().equals("removal")) {
				deleteRowInDB(currentMsg.getScreening());
				serverMsg = new Message();
				currentMsg.setAction("updated movie time");
				currentMsg.setScreenings(getAllOfType(Screening.class));
				try {
					client.sendToClient(currentMsg);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}

			List<Screening> screenings = getAllOfType(Screening.class);

			for (Screening screening : screenings) {
				if (screening.getDateAndTime().equals(newScreening.getDateAndTime())
						&& screening.getCinema().getName().equals(newScreening.getCinema().getName())
						&& screening.getMovie().getName().equals(newScreening.getMovie().getName())
						&& screening.getHall().getHallId() == (newScreening.getHall().getHallId())) {
					System.out.println("screening == newScreening");
					if (currentMsg.getDBAction().equals("addition")) {
						Message serverMsg = new Message();
						serverMsg.setAction("update movie time error");
						serverMsg.setError("screening already exists");
						try {
							client.sendToClient(serverMsg);
							return;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (currentMsg.getDBAction().equals("removal")) {
						deleteRowInDB(screening);
						break;
					}
				}
			}

			System.out.println("about to save newScreening in database");
			saveRowInDB(newScreening);
			serverMsg = new Message();
			currentMsg.setAction("updated movie time");
			currentMsg.setScreenings(getAllOfType(Screening.class));
			try {
				client.sendToClient(currentMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("login")) {
			try {
				if (currentMsg.getUsername().equals(null) || ((Message) msg).getPassword().equals(null)) {
					System.out.println("user or password is null");
				} else {
					UserController.getUser((Message) msg);
					serverMsg = (Message) msg;
					serverMsg.setAction("login done");
					client.sendToClient(serverMsg);
				}
			} catch (IOException e) {
				System.out.println("cant login");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("add a complaint")) {
			serverMsg.setAction("added a complaint");
			System.out.println("about to add a complaint");

			try {
				if (currentMsg.getComplaint() == null) {
					System.out.println("complaint is null in add a complaint");
				} else {
					System.out.println(((Message) msg).getComplaint().getComplaintTitle());
					saveRowInDB(((Message) msg).getComplaint());
					client.sendToClient(serverMsg);
				}
			} catch (IOException e) {
				System.out.println("cant add a complaint");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("cinema contained movies")) {
			try {
				serverMsg = currentMsg;
				System.out.println("about to pull cinemas according to movie id");
				System.out.println("movie id is: " + currentMsg.getId());
				serverMsg.setCinemas((ArrayList<Cinema>) ScreeningController.getCinemas(currentMsg.getMovieId()));
				System.out.println("finished pulling cinemas according to movie id");
				serverMsg.setAction("cinema contained movies done");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant cinema contained movies");
				e.printStackTrace();

			}
		}
		if (currentMsg.getAction().equals("screening for movie")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setScreenings((ArrayList<Screening>) ScreeningController.getAllDateOfMovie(serverMsg.getMovieId(), serverMsg.getCinemaId()));
				serverMsg.setAction("screening for movie done");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant screening for movie");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("pull soon movies")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setMovies((ArrayList<Movie>) MovieController.getComingSoonMovies());
				serverMsg.setAction("got soon movies");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant pull soon movies");
				e.printStackTrace();

			}
		}
		if (currentMsg.getAction().equals("pull screening movies")) {
			try {
				serverMsg = currentMsg;
				System.out.println("in Main pull screeening movies msg");

				serverMsg.setMovies((ArrayList<Movie>) MovieController.getCurrentlyScreeningMovies());
				serverMsg.setAction("got screening movies");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant pull screening movies");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("pull soon movies genre")) {
			try {
				System.out.println("in Main genre pull screeening movies msg");
				serverMsg = (Message) msg;
				serverMsg.setMovies((ArrayList<Movie>) MovieController.getGenreTypeMovies(serverMsg.getGenre()));
				serverMsg.setAction("got screening movies");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant pull screening movies");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("sort movies by genre")) { // from here *********
			try {
				serverMsg = (Message) msg;
				serverMsg.setMovies((ArrayList<Movie>) MovieController.MoviesByGener(serverMsg.getGenre()));
				serverMsg.setAction("sorted movies by genre");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant pull screening movies");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("sort movies by date")) {
			try {
				serverMsg = (Message) msg;
				serverMsg.setMovies((ArrayList<Movie>) MovieController.getMoviesByDate(serverMsg.getScreeningDate()));
				serverMsg.setAction("done to sort by date");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("sort movies by popular")) {
			try {
				serverMsg = (Message) msg;
				serverMsg.setMovies((ArrayList<Movie>) MovieController.MoviesByPopularty());
				serverMsg.setAction("done to sort by popular");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant pull screening movies");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("pull movies from home")) {
			try {
				ArrayList<Movie> movies = ((ArrayList<Movie>) ViewingPackageController.getViewingPackageMovies());
				if (currentMsg.getRate() != null) {

					Iterator<Movie> iter = movies.iterator();

					while (iter.hasNext()) {
						Movie movie = iter.next();
						if (Double.parseDouble(currentMsg.getRate()) != movie.getRate()) {
							iter.remove();
						}
					}
				}

				if (currentMsg.getSearch() != null) {

					Iterator<Movie> iter = movies.iterator();

					while (iter.hasNext()) {
						Movie movie = iter.next();
						if (!movie.getName().contains(currentMsg.getSearch())) {
							iter.remove();
						}
					}
				}

				Message serverMsg = new Message();
				serverMsg.setAction("got movies from home");
				serverMsg.setMovies(movies);
				client.sendToClient(serverMsg);

			} catch (IOException e) {
				System.out.println("cant add movie");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("picking chair")) {
			try {
				serverMsg = currentMsg;
				Screening clientScreening = serverMsg.getScreening();
				Screening serverScreening = ScreeningController.getScreening(clientScreening.getId());
				if (ScreeningController.pickChair(clientScreening.getSeats(), serverScreening)) {
					serverMsg.setAction("picking seats success");
					for (int i = 0; i < clientScreening.getHall().getRows(); i++) {
						for (int j = 0; j < clientScreening.getHall().getCols(); j++) {
							if (clientScreening.getSeats()[i][j] == 2) {
								clientScreening.getSeats()[i][j] = 1;
							}
						}
					}
					serverMsg.setScreening(clientScreening);
					updateRowDB(clientScreening);
				} else {
					serverMsg.setAction("picking seats error");
					serverMsg.setError("Seats have already been chosen by another customer, please choose again");
					serverMsg.setScreening(serverScreening);

				}
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant picking chair");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("pull current complaint")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setComplaints((ArrayList<Complaint>) CustomerController.getAllCurrentComplaints());
				serverMsg.setAction("got complaints");
				System.out.println(serverMsg.getComplaints().toString());
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant pull complaints");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("save customer")) { // save ticket // save customer
			try {
				serverMsg = currentMsg;
				Purchase purchase = serverMsg.getPurchase();
				saveRowInDB(purchase);
				if(purchase.isCard()) {
					SubscriptionCard subscriptionCard = new SubscriptionCard(purchase);
					saveRowInDB(subscriptionCard);
					serverMsg.setSubscriptionCard(subscriptionCard);
				}
				JavaMailUtil.sendMessage(serverMsg.getCustomerEmail(), "Customer Of The Sirtiya, Order Number :" , serverMsg.getEmailMessage());
				serverMsg.setPurchase(purchase);
				serverMsg.setAction("save customer done");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("can't save customer");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("get purchase by id")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPurchase(CustomerController.getID(serverMsg.getId()));
				if (serverMsg.getPurchase() != null) {
					serverMsg.setPayment(
							CustomerController.ReturnOnPurchase(serverMsg.getPurchase(), LocalDateTime.now()));
				}
				serverMsg.setAction("got purchase by id");
				if (serverMsg.getPurchase() != null && serverMsg.getPurchase().isCanceled())
					serverMsg.setPurchase(null);
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get purchase by id");
				e.printStackTrace();
			}
		}
		
		if (currentMsg.getAction().equals("get purchase by serial")) {
			try {
				serverMsg = currentMsg;
				System.out.println("a");
				serverMsg.setPurchase(CustomerController.getPurchaseBySerial(currentMsg.getSerial()));
				if (serverMsg.getPurchase() != null) {
					serverMsg.setPayment(
							CustomerController.ReturnOnPurchase(serverMsg.getPurchase(), LocalDateTime.now()));
					System.out.println("b");
				}
				serverMsg.setAction("got purchase by serial");
				if (serverMsg.getPurchase() != null && serverMsg.getPurchase().isCanceled()) {
					System.out.println("c");
					serverMsg.setPurchase(null);
					}
				System.out.println("d");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get purchase by id");
				e.printStackTrace();
			}
		}
		
		if (currentMsg.getAction().equals("get purchases")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPurchases(getAllOfType(Purchase.class));
				serverMsg.setAction("got purchases");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get purchase by id");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("get cinemas and purchases and complaints")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPurchases(getAllOfType(Purchase.class));
				serverMsg.setCinemas(getAllOfType(Cinema.class));
				serverMsg.setComplaints(getAllOfType(Complaint.class));
				serverMsg.setAction("got cinemas and purchases and complaints");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get purchase by id");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("get report ticket")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPurchases(ReportController.getTicketReportMonthly(serverMsg.getCinema()));
				serverMsg.setAction("got report ticket");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get report ticket");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("get report special ticket")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPurchases(ReportController.getSpecialTicketReportMonthly(serverMsg.getCinema()));
				serverMsg.setAction("got report special ticket");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get report special ticket");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("get status complaints monthly")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPurchases(ReportController.statusComplaintsMonthly(serverMsg.getCinema()));
				serverMsg.setAction("got status complaints monthly");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get status complaints monthly");
				e.printStackTrace();
			}
		}
		if (((Message) msg).getAction().equals("pull genre screening movies")) {
			try {
				System.out.println("in Main pull screeening movies msg");
				serverMsg = (Message) msg;
				serverMsg.setGenres((ArrayList<String>) MovieController.getAllGenreScreeningMovies());
				serverMsg.setAction("got genre screening movies");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant pull screening movies");
				e.printStackTrace();
			}
		}


		
		if (currentMsg.getAction().equals("send purchase cancellation mail")) {
			try {
				serverMsg = currentMsg;

				JavaMailUtil.sendPurchaseCancellationMessage(serverMsg.getPurchase().getEmail(),
						serverMsg.getPurchase().getFirstName() + " " + serverMsg.getPurchase().getLastName(),
						String.valueOf(serverMsg.getPurchase().getId()), serverMsg.getPayment());
				serverMsg.setAction("sent purchase cancellation mail");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant send purchase cancellation mail");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("get all screenings")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setScreenings(getAllOfType(Screening.class));
				serverMsg.setAction("got all screenings");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get status complaints monthly");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("add movie")) {
			try {
				serverMsg = currentMsg;
				saveRowInDB(serverMsg.getMovie());
				serverMsg.setAction("added movie");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant add movie");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("delete movie")) {
			try {
				serverMsg = currentMsg;
				serverMsg.getMovie().setIsDeleted(true);
				updateRowDB(serverMsg.getMovie());
				serverMsg.setAction("deleted movie");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant deleted movie");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("delete a viewing package")) {
			try {
				serverMsg = currentMsg;
				updateRowDB(serverMsg.getMovie());
				serverMsg.setAction("deleted a viewing package");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant deleted movie");
				e.printStackTrace();
			}
		}
		
		if (currentMsg.getAction().equals("get active purple limits")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setActivePurpleLimits(PurpleLimitController.getActivePurpleLimits());
				serverMsg.setAction("got active purple limits");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get active purple limits");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("add purple limit")) {
			try {
				serverMsg = currentMsg;
				PurpleLimit newPurpleLimit = serverMsg.getPurpleLimit();
				saveRowInDB(newPurpleLimit);
				PurpleLimitController.cancelPurchases(newPurpleLimit.getFromDate(), newPurpleLimit.getToDate());
				serverMsg.setActivePurpleLimits(PurpleLimitController.getActivePurpleLimits());
				serverMsg.setAction("added purple limit");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("can't add purple limit");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("search bar update")) {
			try {
				ArrayList<Movie> movies = new ArrayList<Movie>();

				if (currentMsg.getActionType().equals("pull screening movies")) {

					List<Screening> screenings = getAllOfType(Screening.class);

					if (currentMsg.getTheater() != null) {
						Iterator<Screening> iters = screenings.iterator();
						while (iters.hasNext()) {
							Screening s = iters.next();
							if (!s.getCinema().getName().equals(currentMsg.getTheater())) {
								iters.remove();
							}
						}

						for (Screening screening : screenings) {
							if (!movies.contains(screening.getMovie())) {
								movies.add(screening.getMovie());
							}
						}
					} else {
						movies = getAllOfType(Movie.class);
						Iterator<Movie> iter = movies.iterator();
						while (iter.hasNext()) {
							Movie movie = iter.next();
							if (movie.isComingSoon()) {
								iter.remove();
							}
						}

						iter = movies.iterator();
						while (iter.hasNext()) {
							Movie movie = iter.next();
							if(!movie.isScreening()) {
								iter.remove();
							}
						}
					}

				} else if (currentMsg.getActionType().equals("pull soon movies")) {

					movies = getAllOfType(Movie.class);
					Iterator<Movie> iter = movies.iterator();
					while (iter.hasNext()) {
						Movie movie = iter.next();

						if (!movie.isComingSoon()) {
							iter.remove();
						}
					}

				} else {

					movies = getAllOfType(Movie.class);
					Iterator<Movie> iter = movies.iterator();
					while (iter.hasNext()) {
						Movie movie = iter.next();
						if (!movie.isViewingPackage()) {
							iter.remove();
						}
					}

				}

				if (currentMsg.getRate() != null) {

					Iterator<Movie> iter = movies.iterator();

					while (iter.hasNext()) {
						Movie movie = iter.next();
						if (Double.parseDouble(currentMsg.getRate()) != movie.getRate()) {
							iter.remove();
						}
					}
				}

				if (currentMsg.getGenre() != null) {

					Iterator<Movie> iter = movies.iterator();

					while (iter.hasNext()) {
						Movie movie = iter.next();
						if (!movie.getGenre().contains(currentMsg.getGenre())) {
							iter.remove();
						}
					}
				}

				if (currentMsg.getSearch() != null) {

					Iterator<Movie> iter = movies.iterator();

					while (iter.hasNext()) {
						Movie movie = iter.next();
						if (!movie.getName().contains(currentMsg.getSearch())) {
							iter.remove();
						}
					}
				}

				Message serverMsg = new Message();
				serverMsg.setAction(currentMsg.getMoviesType());
				serverMsg.setMovies(movies);
				client.sendToClient(serverMsg);

			} catch (IOException e) {
				System.out.println("cant add movie");
			}
		}

		if (currentMsg.getAction().equals("cancellation of purchase")) {
			try {
				serverMsg = new Message();
				System.out.println("1");
				Purchase p = currentMsg.getPurchase();
				if(p.isTicket())
					currentMsg.getPurchase().getScreening().getCinema().getCanceledPurchases().add(p);
				Float refund = CustomerController.ReturnOnPurchase(currentMsg.getPurchase(), LocalDateTime.now());
				currentMsg.getPurchase().setIsCanceled(new Pair<Boolean, Float> (true, refund));
				updateRowDB(currentMsg.getPurchase());
				if(p.isTicket())
					updateRowDB(currentMsg.getPurchase().getScreening().getCinema());
				serverMsg.setAction("got purchase cancelation by id");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant cancellation of purchase");
				e.printStackTrace();
			}
		}
		
		if (currentMsg.getAction().equals("check purple limit")) {
			try {
				serverMsg = currentMsg;
				Pair<Boolean, Integer> tavSagol = PurpleLimitController.checkPurpleLimit(serverMsg.getScreeningDate().toLocalDate());
				serverMsg.setStatus(tavSagol.getKey());
				serverMsg.setTavSagolLimit(tavSagol.getValue());
				serverMsg.setAction("done check purple limit");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant check purple limit");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("selection of seats under restrictions")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setSeats(PurpleLimitController.setSeatsPurpleLimit(serverMsg.getScreening(), serverMsg.getNumOfSeats()));
				serverMsg.setAction("done selection of seats under restrictions");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant selection of seats under restrictions");
				e.printStackTrace();
			}

		}
		if (currentMsg.getAction().equals("log out")) {
			try {
				UserController.logUserOut(currentMsg);
				serverMsg = new Message();
				serverMsg.setAction("logged out");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant selection of seats under restrictions");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("add viewing package")) {
			try {
				serverMsg = currentMsg;
				saveRowInDB(serverMsg.getViewingPackage());
				serverMsg.setAction("added viewing package");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant selection of seats under restrictions");
				e.printStackTrace();
			}
		}

		if (currentMsg.getAction().equals("cancel current order")) {
			try {
				serverMsg = currentMsg;
				Screening screening = serverMsg.getScreening();
				updateRowDB(screening);
				serverMsg.setAction("canceled current order");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant selection of seats under restrictions");
			}
		}

		if (currentMsg.getAction().equals("get all cinemas")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setCinemas(getAllOfType(Cinema.class));
				serverMsg.setAction("got all cinemas");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get all cinemas");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("get all price request")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPriceRequests(getAllOfType(PriceRequest.class));
				serverMsg.setAction("got all price request");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get all price request");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("get all open price requests")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setPriceRequests(PriceRequestController.getAllOpenPriceRequests());
				serverMsg.setAction("got all open price requests");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get all open price requests");
				e.printStackTrace();
			}
		}
		
		if (currentMsg.getAction().equals("approve price request")) {
			try {
				serverMsg = currentMsg;
				PriceRequest request = serverMsg.getPriceRequest();
				NetworkAdministrator administrator = NetworkAdministratorController.getAdministrator();
				if(request.isTicket())
					administrator.setMoviePrice(request.getNewPrice());
				if(request.isLink())
					administrator.setViewingPackagePrice(request.getNewPrice());
				if(request.isCard())
					administrator.setSubscriptionCardPrice(request.getNewPrice());
				updateRowDB(administrator);
				updateRowDB(request);
				serverMsg.setPriceRequests(PriceRequestController.getAllOpenPriceRequests());
				serverMsg.setAction("approved price request");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("can't approve price request");
				e.printStackTrace();
			}
		}
		
		if (currentMsg.getAction().equals("decline price request")) {
			try {
				serverMsg = currentMsg;
				updateRowDB(serverMsg.getPriceRequest());
				serverMsg.setPriceRequests(PriceRequestController.getAllOpenPriceRequests());
				serverMsg.setAction("declined price request");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("can't decline price request");
				e.printStackTrace();
			}
		}
		
		if (currentMsg.getAction().equals("save price request")) {
			try {
				serverMsg = currentMsg;
				saveRowInDB(serverMsg.getPriceRequest());
				serverMsg.setAction("done to save price request");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant save price request");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("close complaint")) {
			try {
				serverMsg = currentMsg;
				updateRowDB(serverMsg.getComplaint());
				serverMsg.setAction("done close complaint");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant close complaint");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("get genres")) {
			try {
				serverMsg = new Message();
				serverMsg.setGenres(genres);
				serverMsg.setAction("got genres");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant close complaint");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("update viewing package")) {
			try {
				serverMsg = currentMsg;
				updateRowDB(serverMsg.getViewingPackage());
				serverMsg.setAction("added viewing package");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant selection of seats under restrictions");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("get all viewing package")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setViewingPackages(getAllOfType(ViewingPackage.class));
				serverMsg.setAction("got all viewing package");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get all viewing package");
				e.printStackTrace();
			}
		}
		if (currentMsg.getAction().equals("get all movies from viewing packages")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setMovies(ViewingPackageController.getViewingPackageMovies());
				serverMsg.setAction("got all movies from viewing packages");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get all movies from viewing packages");
				e.printStackTrace();
			}
		}
		
		
		if (currentMsg.getAction().equals("get viewing packages by movie")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setViewingPackages(ViewingPackageController.getViewingPackagesByMovie(currentMsg.getMovieName()));
				serverMsg.setAction("got viewing packages by movie");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant get viewing packages by movie");
				e.printStackTrace();
			}
		}
		
		if (currentMsg.getAction().equals("get subscription card")) {
			try {
				serverMsg = currentMsg;
				serverMsg.setSubscriptionCard(SubscriptionCardController.getSubscriptionCard(serverMsg.getId()));
				serverMsg.setAction("got subscription card");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("can't get subscription card");
				e.printStackTrace();
			}
		}
		
		if (currentMsg.getAction().equals("get prices")) {
			try {
				NetworkAdministrator n = NetworkAdministratorController.getAdministrator();
				serverMsg = currentMsg;
				serverMsg.setMoviePrice(n.getMoviePrice());
				serverMsg.setViewingPackagePrice(n.getViewingPackagePrice());
				serverMsg.setSubscriptionCardPrice(n.getSubscriptionCardPrice());
				serverMsg.setAction("got prices");
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("can't get prices");
				e.printStackTrace();
			}
		}
		
		
		
	}
	
}
