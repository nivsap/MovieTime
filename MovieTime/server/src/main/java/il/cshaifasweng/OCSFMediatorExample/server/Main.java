package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
			
			//create movite 
			ArrayList<String> movieStartTimes = new ArrayList<String>(Arrays.asList("10:00" , "12:00" , "16:00" , "18:00" , "20:00" , "22:00" , "00:00"));
			Movie titanic = new Movie("Titanic","197", 5.00, "Drama", null, movieStartTimes, true, false, "Explains the whole story from departure until the death of Titanic", "Leonardo"
							+ " DiCaprio,Kate Winslet,Billy Zane,Kathy Bates", new java.util.Date(1997 -1900, 10, 16));
			Movie forrestGump = new Movie("Forrest Gump", "142", 4.5, "Drama", null, movieStartTimes, true, false, "The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other "
					+ "historical events unfold from the perspective of  man "
					+ "with an IQ of 75, whose only desire is to be childhood sweetheart.", "Tom Hanks, Robin Wright, Gary Sinise", new Date(1994, 1, 3));
			Movie harryPotter = new Movie("Harry Potter and is friens the Philosopher's Stone", "152", 4.00, "Thriller", null, movieStartTimes, true, false, " Harry makes close friends and a few enemies during his first year at the school, and with the help of his friends,"
					+ " he faces an attempted comeback by the dark wizard Lord Voldemort.", 
					"Daniel Radcliffe,Rupert Grint,Emma Watson", new Date(2001, 11, 4));
			Movie movie1917 = new Movie("1917", "119", 5.00, "Drama", null, movieStartTimes, true, false, "The film takes place after the German retreat to the Hindenburg Line during Operation Alberich, and follows two British soldiers in their mission to deliver an important message to call off a "
					+ "doomed offensive attack." , "George MacKay, Dean-Charles Chapman, Mark Strong",new Date(2019, 9, 22));
			Movie twilight = new Movie("The Twilight Saga", "607", 4.4, "Love", null, movieStartTimes, true, false, "Twilight focuses on the development of a personal relationship between teenager and vampire"
					, "Kristen Stewart,Robert Pattinson,aylor Lautner", new Date(2008, 11,22));
			Movie miracleMovie = new Movie("Miracle in Cell No. 7", "132", 5.00, "Comedy-Drama", null, movieStartTimes, true, false, "The film is about a mentally challenged man wrongfully imprisoned for murder, who builds "
					+ "friendships with the hardened criminals in his cell, who in return help him see his daughter again by smuggling her into the prison.","Aras Bulut İynemli"
							+ "Nisa Aksongur Celile Toyon", new Date(2013, 9, 22));
			session.save(titanic);
			session.save(forrestGump);
			session.save(harryPotter);
			session.save(movie1917);
			session.save(twilight);
			session.save(miracleMovie);
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
		//addMoviesToDB();
		Main server = new Main(3000);
		if (args.length != 1) {
			System.out.println("Required argument: <port>");
		} else {
			server.listen();
			System.out.println("hello server");
			}
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
}
