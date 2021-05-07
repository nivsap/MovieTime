package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.util.ArrayList;
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
			//ArrayList<String> movieTime = new ArrayList<String>();
			Movie nivMovie = new Movie("niv", "1:00", 5, "niv");
			Movie eitanMovie = new Movie("Eitan", "2:00", 5, "Eitan");
			Movie shirMovie = new Movie("Shir", "3:00", 5, "Shir");
			
			session.save(nivMovie);
			session.save(eitanMovie);
			session.save(shirMovie);
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
