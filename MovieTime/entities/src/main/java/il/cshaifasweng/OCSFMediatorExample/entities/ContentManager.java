package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
@Entity

@Table(name = "ContentManager")
public class ContentManager extends Worker implements  Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private ArrayList<Movie> allMoviesInCinema;
	public ContentManager() {}
	public ContentManager(String firstName, String lastName, String userName, String password , Cinema cinema
			) {
		super(firstName, lastName, userName, password,cinema);
		//this.allMoviesInCinema = new ArrayList<Movie>();
	}


	
	//private static final long serialVersionUID = 1L;

}
