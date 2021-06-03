package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "ContentManager")
public class ContentManager extends Worker implements  Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "worker" )
//	private List<PriceRequest> priceRequests;
	//private ArrayList<Movie> allMoviesInCinema;
	public ContentManager() {}
	public ContentManager(String firstName, String lastName, String userName, String password , Cinema cinema,boolean isLoggedIn 
			) {
		super(firstName, lastName, userName, password,cinema, isLoggedIn);
		//this.priceRequests = new ArrayList<>();
		//this.allMoviesInCinema = new ArrayList<Movie>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



	
	//private static final long serialVersionUID = 1L;

}
