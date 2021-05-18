package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
import il.cshaifasweng.OCSFMediatorExample.entities.ContentManager;
import il.cshaifasweng.OCSFMediatorExample.entities.CustomerService;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.NetworkAdministrator;
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
		configuration.addAnnotatedClass(ContentManager.class);
		configuration.addAnnotatedClass(BranchManager.class);
		configuration.addAnnotatedClass(CustomerService.class);
		ServiceRegistry serviceRegistry =
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static LocalDateTime getTime(int year, int month, int day){
		return LocalDate.of(year, month, day).atStartOfDay();
	}
	
	public static void addUsersToDB() {
		try {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Worker shirWorker = new BranchManager("shir", "shir", "shir", "shir");
		Worker lielWorker = new ContentManager("liel", "liel", "liel", "liel");
		Worker asafWorker = new CustomerService("asaf", "asaf", "asaf", "asaf",false);
		Worker hadarWorker = new NetworkAdministrator("hadar", "hadar", "hadar", "hadar");
		
		session.save(shirWorker);
		session.save(lielWorker);
		session.save(asafWorker);
		session.save(hadarWorker);
		session.flush();
		session.getTransaction().commit();
		session.clear();
		}catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			e.printStackTrace();
		} finally 
		{
			assert session != null;
			session.close();
		}
	}

	public static void addMoviesToDB() {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

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
			Movie StarWars = new Movie("Star Wars", "2h 21min", 5.00, "Action   •   Adventure   •   Fantasy", "StarWars.jpg", "StarWars.png", movieStartTimes, true, false, "The surviving members of the Resistance face the First Order once \nagain, and the legendary conflict between the Jedi and the Sith reaches \nits peak, bringing the Skywalker saga to its end.",
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
		addUsersToDB();
		addMoviesToDB();
		

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
			serverMsg.setAction("updated movie time");
			System.out.println("about to update movie time");

			try {
				if(((Message) msg).getMovie() == null) {
					System.out.println("movie is null in update movie");
				}else {
					System.out.println(((Message) msg).getMovie().getName());
					updateMovie(((Message) msg).getMovie());
					client.sendToClient(serverMsg);
				}
			} catch (IOException e) {
				System.out.println("cant update movie time");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	public static void updateMovie(Movie movie) {
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();

			session.update(movie);
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

}