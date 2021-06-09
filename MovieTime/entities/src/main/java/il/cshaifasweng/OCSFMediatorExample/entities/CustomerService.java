package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javafx.util.Pair;

@Entity
@Table(name = "CustomerService")
public class CustomerService extends Worker implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public CustomerService() {
		super();
	}
	
	public CustomerService(String firstName, String lastName, String userName, String password, boolean isLoggedIn, Cinema cinema) {
		super(firstName, lastName, userName, password, cinema, isLoggedIn);
	}
}
