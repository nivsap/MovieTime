package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class Main extends AbstractServer{


	private static Session session;
	static SessionFactory sessionFactory = getSessionFactory();
	Message serverMsg;

	//Message serverMsg;  need to create class for msg
	public Main(int port) {

		super(port);
	}

	private static SessionFactory getSessionFactory() throws HibernateException {

		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Movie.class);
		configuration.addAnnotatedClass(Worker.class);
		configuration.addAnnotatedClass(NetworkAdministrator.class);
		configuration.addAnnotatedClass(Cinema.class);
		configuration.addAnnotatedClass(Hall.class);
		configuration.addAnnotatedClass(Screening.class);
		configuration.addAnnotatedClass(ContentManager.class);
		configuration.addAnnotatedClass(BranchManager.class);
		configuration.addAnnotatedClass(CustomerService.class);
		configuration.addAnnotatedClass(Complaint.class);
		ServiceRegistry serviceRegistry =
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static LocalDateTime getTime(int year, int month, int day){
		return LocalDate.of(year, month, day).atStartOfDay();
	}

	public static void addComplaintToDB(Complaint complaint) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(complaint);
			session.flush();
			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			e.printStackTrace();
		} finally
		{
			assert session != null;
			session.close();
			System.out.println("Complaint added to database");
		}
	}


	private static LocalDateTime getExacTime(int year, int month, int day , int hours , int minutes){
		return LocalDate.of(year, month, month).atTime(hours, minutes);
	}


	public static void addDataToDB() {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Worker shirWorker = new BranchManager("shir", "shir", "shir", "shir",null);
			Worker nivWorker = new BranchManager("niv", "niv", "niv", "niv",null);
			Worker lielWorker = new ContentManager("liel", "liel", "liel", "liel",null);
			Worker asafWorker = new CustomerService("asaf", "asaf", "asaf", "asaf",null,false);
			Worker hadarWorker = new NetworkAdministrator("hadar", "hadar", "hadar", "hadar",null);

			//create movie 
			ArrayList<String> movieStartTimes = new ArrayList<String>(Arrays.asList("10:00" , "12:00" , "16:00" , "18:00" , "20:00" , "22:00" , "00:00"));
			Movie avengersEndgame = new Movie("Avengers: Endgame","3h 1min", 5.00, "Action   •   Adventure   •   Drama", "AvengersEndgame.jpg",  "AvengersEndgame.png", movieStartTimes, true, false, "After the devastating events of Avengers: Infinity War (2018), the \nuniverse is in ruins. With the help of remaining allies, \nthe Avengers assemble once more in order to reverse Thanos' \nactions and restore balance to the universe.",
					"Robert Downey Jr., Chris Evans, Mark Ruffalo", getTime(2019, 4, 26));
			Movie sherlockHolmes = new Movie("Sherlock Holmes", "2h 8min", 4.5, "Action   •   Adventure   •   Mystery", "SherlockHolmes.jpg", "SherlockHolmes.png", movieStartTimes, true, false, "Detective Sherlock Holmes and his stalwart partner Watson engage in \na battle of wits and brawn with a nemesis whose plot \nis a threat to all of England.",
					"Robert Downey Jr., Jude Law, Rachel McAdams", getTime(2009, 12, 25));
			Movie babyDriver = new Movie("Baby Driver", "1h 53min", 4.00, "Action   •   Crime   •   Drama ", "BabyDriver.jpg", "BabyDriver.png", movieStartTimes, true, false, "After being coerced into working for a crime boss, a young getaway \ndriver finds himself taking part in a heist doomed to fail.",
					"Ansel Elgort, Jon Bernthal, Jon Hamm", getTime(2017, 6, 28));
			Movie wonderWoman1984  = new Movie("Wonder Woman 1984", "2h 31min", 5.00, "Action   •   Adventure   •   Fantasy", "WonderWoman1984.jpg", "WonderWoman1984.png", movieStartTimes, true, false, "Diana must contend with a work colleague and businessman, whose desire \nfor extreme wealth sends the world down a path of destruction, \nafter an ancient artifact that grants wishes goes missing.",
					"Gal Gadot, Chris Pine, Kristen Wiig", getTime(2020, 12, 21));
			Movie it  = new Movie("IT", "2h 15min", 5.00, "Horror", "It.jpg", "It.png", movieStartTimes, true, false, "In the summer of 1989, a group of bullied kids band together\n to destroy a shape-shifting monster, which disguises itself \nas a clown and preys on the children of Derry, \ntheir small Maine town.",
					"Bill Skarsgard, Jaeden Martell, Finn Wolfhard", getTime(2017, 9, 8));
			Movie toyStory = new Movie("Toy Story", "1h 40min", 5.00, "Animation   •   Adventure   •   Comedy", "ToyStory.jpg", "ToyStory.png", movieStartTimes, true, false, "When a new toy called 'Forky' joins Woody and the gang, \na road trip alongside old and new friends reveals how \nbig the world can be for a toy.",

					"Tom Hanks, Tim Allen, Annie Potts", getTime(2017, 6, 21));
			Movie Minions = new Movie("Minions", "1h 31min", 4.50, "Animation   •   Adventure   •   Comedy", "Minions.jpg", "Minions.png", movieStartTimes, true, false, "Minions Stuart, Kevin, and Bob are recruited by Scarlet Overkill, \na supervillain who, alongside her inventor husband Herb, \nhatches a plot to take over the world.",
					"Sandra Bullock, Jon Hamm, Michael Keaton", getTime(2015, 7, 10));
			Movie StarWars = new Movie("Star Wars", "2h 21min", 5.00, "Action   •   Adventure   •   Fantasy", "StarWars.jpg", "StarWars.png", movieStartTimes, true, true, "The surviving members of the Resistance face the First Order once \nagain, and the legendary conflict between the Jedi and the Sith reaches \nits peak, bringing the Skywalker saga to its end.",
					"Daisy Ridley, John Boyega, Oscar Isaac", getTime(2019, 12, 20));

			avengersEndgame.setMovieBeginingTime(new ArrayList<String>(Arrays.asList("10:00" , "12:00")));
			sherlockHolmes.setMovieBeginingTime(new ArrayList<String>(Arrays.asList( "16:00" , "18:00")));
			babyDriver.setMovieBeginingTime(new ArrayList<String>(Arrays.asList( "20:00" , "22:00")));
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
			Cinema haifaCinema = new Cinema("Haifa", "Haifa,Carmel st", (BranchManager)shirWorker, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			Cinema telAvivCinema = new Cinema("Tel-Aviv", "Tel-Aviv,Wieztman st", (BranchManager)nivWorker, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
			shirWorker.setCinema(haifaCinema);
			nivWorker.setCinema(telAvivCinema);

			boolean[][] planeArray = new boolean[3][3];

			Hall hall1 = new Hall(planeArray, 3, 3, new ArrayList<>(),haifaCinema);
			Hall hall2 = new Hall(planeArray, 3, 3, new ArrayList<>(),haifaCinema);
			Hall hall3 = new Hall(planeArray, 3, 3, new ArrayList<>(),telAvivCinema);
			Hall hall4 = new Hall(planeArray, 3, 3, new ArrayList<>(),telAvivCinema);


			Screening screeningOfFilm_1 = new Screening(getExacTime(2021, 5, 25, 16, 00), hall1, avengersEndgame,haifaCinema);
			Screening screeningOfFilm_2 = new Screening(getExacTime(2021, 5, 25, 20, 00), hall1, sherlockHolmes,haifaCinema);
			Screening screeningOfFilm_3 = new Screening(getExacTime(2021, 5, 26, 20, 00), hall2, sherlockHolmes,haifaCinema);
			Screening screeningOfFilm_4 = new Screening(getExacTime(2021, 5, 27, 20, 00), hall2, babyDriver,haifaCinema);
			Screening screeningOfFilm_9 = new Screening(getExacTime(2021, 5, 27, 20, 30), hall2, sherlockHolmes,haifaCinema);
			Screening screeningOfFilm_10 = new Screening(getExacTime(2021, 5, 27, 20, 30), hall1, wonderWoman1984,haifaCinema);
			Screening screeningOfFilm_5 = new Screening(getExacTime(2021, 5, 26, 20, 00), hall3, wonderWoman1984,telAvivCinema);
			Screening screeningOfFilm_6 = new Screening(getExacTime(2021, 5, 27, 20, 00), hall3, it,telAvivCinema);
			Screening screeningOfFilm_7 = new Screening(getExacTime(2021, 5, 28, 13, 30), hall4, toyStory,telAvivCinema);
			Screening screeningOfFilm_8 = new Screening(getExacTime(2021, 5, 28, 20, 00), hall4, Minions,telAvivCinema);
			Screening screeningOfFilm_11 = new Screening(getExacTime(2021, 5, 28, 10, 15), hall4, Minions,telAvivCinema);
			Screening screeningOfFilm_12 = new Screening(getExacTime(2021, 5, 28, 10, 15), hall3, it,telAvivCinema);

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
			haifaCinema.getHallArray().add(hall1);
			haifaCinema.getHallArray().add(hall2);

			telAvivCinema.getScreeningArray().add(screeningOfFilm_5);
			telAvivCinema.getScreeningArray().add(screeningOfFilm_6);
			telAvivCinema.getScreeningArray().add(screeningOfFilm_7);
			telAvivCinema.getScreeningArray().add(screeningOfFilm_8);
			telAvivCinema.getScreeningArray().add(screeningOfFilm_11);
			telAvivCinema.getScreeningArray().add(screeningOfFilm_12);
			telAvivCinema.getHallArray().add(hall3);
			telAvivCinema.getHallArray().add(hall4);



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
			session.flush();

			//System.out.println(ScreeningController.pickChair(1, 1, hall4));

			session.getTransaction().commit();
			session.clear();
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally 
		{
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

		addComplaintsToDB();

		//addUsersToDB();
		addDataToDB();
		System.out.println(MovieController.getSoonMovies().get(0).getName());
		//for(Screening srScreening : ScreeningController.getAllDateOfMovie(2, 1)) {
		//	System.out.println(srScreening.getMovie().getName());
		//}



	}
	public static void addComplaintsToDB() {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Complaint someComplaint1 = new Complaint("Shir", "Avneri", "I'm very upset", "I want to finish this project", true);
			Complaint someComplaint2 = new Complaint("Niv", "Sapir", "I want to complain", "I am very upset", true);
			Complaint someComplaint3 = new Complaint("Hadar", "Manor", "Some title", "Some details" ,false);

			session.save(someComplaint1);
			session.save(someComplaint2);
			session.save(someComplaint3);
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
			assert session != null;
			session.close();
		}
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
		
		
		serverMsg = new Message();
		if(((Message) msg).getAction().equals("pull movies")) {
			serverMsg.setMovies(getAllOfType(Movie.class));
			serverMsg.setAction("got movies");
			try {
				client.sendToClient(serverMsg);
			} catch (IOException e) {
				System.out.println("cant create list of movies");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(((Message) msg).getAction().equals("update movie time")) {
			System.out.println("about to update movie time");
			updateMovie(((Message) msg).getMovieName(),((Message) msg).getTime(), ((Message) msg).getDbAction(), client);
			
		
		}
		if(((Message) msg).getAction().equals("login")) {
			try {
				if(((Message) msg).getUsername().equals(null) || ((Message) msg).getPassword().equals(null)) {
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

		if(((Message) msg).getAction().equals("add a complaint")) {
			serverMsg.setAction("added a complaint");
			System.out.println("about to add a complaint");

			try {
				if(((Message) msg).getComplaint() == null) {
					System.out.println("complaint is null in add a complaint");
				}else {
					System.out.println(((Message) msg).getComplaint().getComplaintTitle());
					addComplaint(((Message) msg).getComplaint());
					client.sendToClient(serverMsg);
				}
			} catch (IOException e) {
				System.out.println("cant add a complaint");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(((Message) msg).getAction().equals("cinema contained movies")) {
			try {
				serverMsg = (Message) msg;
				serverMsg.setCinemasArrayList((ArrayList<Cinema>) ScreeningController.getCinemas(((Message) msg).getMovieId()));
				serverMsg.setAction("cinema contained movies done");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant cinema contained movies");
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		if(((Message) msg).getAction().equals("screening for movie")) {
			try {
				serverMsg = (Message) msg;
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
		if(((Message) msg).getAction().equals("pull soon movies")) {
			try {
				serverMsg = (Message) msg;
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
		if(((Message) msg).getAction().equals("pull screening movies")) {
			try {
				serverMsg = (Message) msg;
				serverMsg.setMovies((ArrayList<Movie>) MovieController.getAllScreeningMovies()); 
				serverMsg.setAction("got screening movies");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant pull screening movies");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(((Message) msg).getAction().equals("picking chair")) {
			try {
				serverMsg = (Message) msg;
				serverMsg.setStatus(ScreeningController.pickChair(serverMsg.getRow(), serverMsg.getCol(), serverMsg.getHall()));
				serverMsg.setAction("picking chair is done");
				client.sendToClient(serverMsg);
			}
			catch (IOException e) {
				System.out.println("cant picking chair");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		}
		finally {
			session.close();
		}
		return returnedList;
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
			//find movie
			for(Movie movie : movies) {
				System.out.println("1.9");
			
				if(movie.getName().equals(movieName)) {
					if(action.equals("addition")) {
						//if time dosent exist we goochi
						if(movie.getMovieBeginingTime().indexOf(time) == -1) {
							movie.getMovieBeginingTime().add(time);
							timeChanged = true;
							System.out.println("2");
						}else {
							error = true;
							msg.setError("Screening already exists!");
							System.out.println("3");
						}
					}else {
						 // if action is removal
						 Integer index = movie.getMovieBeginingTime().indexOf(time);
						 if(index == -1) {
							 // time for removal does not exist
							 System.out.println("4");
							 error = true;
							 msg.setError("Selected time does not exist for this movie");
						 }else {						
							 /* if(index != -1) { movie.getMovieBeginingTime().remove(index); timeChanged =
						 * true; }else { JOptionPane.showMessageDialog(null, "Incorrect time chosen"); }
						 */
							 System.out.println("5");
							for(String mTime : movie.getMovieBeginingTime()) {
								if(time.equals(mTime)) {
									movie.getMovieBeginingTime().remove(time);
									timeChanged = true;
									break;
								}
							}
						 }
					}
					if(timeChanged) {
						session.update(movie);
						session.flush();
						session.getTransaction().commit();
						session.clear();
						System.out.println("6");
						msg.setAction("updated movie time");
						client.sendToClient(msg);
						break;
					}
					if(error) {
						System.out.println("7");
						msg.setAction("update movie error");
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
		} finally 
		{
			assert session != null;
			session.close();
		}

	}

	public static void addComplaint(Complaint complaint) {
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();

			session.save(complaint);
			System.out.println("finished adding complaint");
			session.flush();
			System.out.println("finished adding complaint flush");
			session.getTransaction().commit();
			System.out.println("finished adding complaint transaction");
			session.clear();
			System.out.println("finished adding complaint clear");
		} catch (Exception exception) {
			if (session != null) {
				System.out.println("trying to rollback from addComplaint");
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally
		{
			assert session != null;
			session.close();
		}

	}
	public static void updateChair(Hall hall) {
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();

			session.update(hall);
			System.out.println("finished movie update");
			session.flush();
			System.out.println("finished movie flush");
			session.getTransaction().commit();
			System.out.println("finished movie transaction");
			session.clear();
			System.out.println("finished movie clear");
		} catch (Exception exception) {
			if (session != null) {
				System.out.println("trying to rollback from movieUpdate");
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally 
		{
			assert session != null;
			session.close();

		}

	}
	//Movie movie = session.load(Movie.class , movie.getId());

}
