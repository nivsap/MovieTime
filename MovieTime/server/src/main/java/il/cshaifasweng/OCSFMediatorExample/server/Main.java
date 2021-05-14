package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import java.util.Date;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
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
        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

	public static void addMoviesToDB() {
		try {
			 SessionFactory sessionFactory = getSessionFactory();
	         session = sessionFactory.openSession();
	         session.beginTransaction();
			
			//create movie 
			ArrayList<String> movieStartTimes = new ArrayList<String>(Arrays.asList("10:00" , "12:00" , "16:00" , "18:00" , "20:00" , "22:00" , "00:00"));
			Movie avengersEndgame = new Movie("Avengers: Endgame","3h 1min", 5.00, "Action   •   Adventure   •   Drama", "AvengersEndgame.jpg",  "AvengersEndgame.png", movieStartTimes, true, false, "After the devastating events of Avengers: Infinity War (2018), the \nuniverse is in ruins. With the help of remaining allies, \nthe Avengers assemble once more in order to reverse Thanos' \nactions and restore balance to the universe.",
									"Robert Downey Jr., Chris Evans, Mark Ruffalo", new java.util.Date(1997 -1900, 10, 16));
			Movie sherlockHolmes = new Movie("Sherlock Holmes", "2h 8min", 4.5, "Action   •   Adventure   •   Mystery", "SherlockHolmes.jpg", "SherlockHolmes.png", movieStartTimes, true, false, "Detective Sherlock Holmes and his stalwart partner Watson engage in \na battle of wits and brawn with a nemesis whose plot\n is a threat to all of England.",
								   "Robert Downey Jr., Jude Law, Rachel McAdams", new Date(1994, 1, 3));
			Movie babyDriver = new Movie("Baby Driver", "1h 53min", 4.00, "Action   •   Crime   •   Drama ", "BabyDriver.jpg", "BabyDriver.png", movieStartTimes, true, false, "After being coerced into working for a crime boss, a young getaway \ndriver finds himself taking part in a heist doomed to fail.",
							   "Ansel Elgort, Jon Bernthal, Jon Hamm", new Date(2001, 11, 4));
			Movie wonderWoman1984  = new Movie("Wonder Woman 1984", "2h 31min", 5.00, "Action   •   Adventure   •   Fantasy", "WonderWoman1984.jpg", "WonderWoman1984.png", movieStartTimes, true, false, "Diana must contend with a work colleague and businessman, whose desire \nfor extreme wealth sends the world down a path of destruction, \nafter an ancient artifact that grants wishes goes missing.",
									 "Gal Gadot, Chris Pine, Kristen Wiig",new Date(2019, 9, 22));
			Movie it  = new Movie("IT", "2h 15min", 5.00, "Horror", "It.jpg", "It.png", movieStartTimes, true, false, "In the summer of 1989, a group of bullied kids band together\n to destroy a shape-shifting monster, which disguises itself \nas a clown and preys on the children of Derry, \ntheir small Maine town.",
					 	"Bill Skarsgard, Jaeden Martell, Finn Wolfhard",new Date(2019, 9, 22));
			Movie toyStory = new Movie("Toy Story", "1h 40min", 5.00, "Animation   •   Adventure   •   Comedy", "ToyStory.jpg", "ToyStory.png", movieStartTimes, true, false, "When a new toy called 'Forky' joins Woody and the gang, \na road trip alongside old and new friends reveals how \nbig the world can be for a toy.",
							 "Tom Hanks, Tim Allen, Annie Potts", new Date(2013, 9, 22));
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
		            session.getSessionFactory().close();
		        }
            
		}

	public static void main(String[] args) throws IOException {
		addMoviesToDB();
		Main server = new Main(3000);
		if (args.length != 1) {
			System.out.println("Required argument: <port>");
		} else {
			server.listen();
			System.out.println("hello server");
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
		
	}
    
	private static <T> ArrayList<T> getAllOfType(Class<T> objectType) {
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
			 SessionFactory sessionFactory = getSessionFactory();
	         session = sessionFactory.openSession();
	         session.beginTransaction();
			
			//create movie 
			System.out.println("in updateMovie function");
			System.out.println(movie.getName());
			Movie currentMovie = movie;
			
		
			session.update(currentMovie);
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
		            session.getSessionFactory().close();
		        }
            
		}
	
}
