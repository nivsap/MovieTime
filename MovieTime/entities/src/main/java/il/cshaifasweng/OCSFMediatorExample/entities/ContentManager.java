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
	
	public ContentManager() {
		super();
	}
	
	public ContentManager(String firstName, String lastName, Cinema cinema, String userName, String password , boolean isLoggedIn) {
		super(firstName, lastName, cinema, userName, password, isLoggedIn);
	}
	
	public int getId() {
		return id;
	}
}
