package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javafx.util.Pair;

@Entity
@Table(name = "CustomerService")
public class CustomerService extends Worker implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	boolean purpleLimit;
	Pair<LocalDateTime,LocalDateTime> datesOfPurpleLimit;
	
	public CustomerService() {}
	public CustomerService(String firstName, String lastName, String userName, String password,Cinema cinema, boolean purpleLimit,boolean isLoggedIn,Pair<LocalDateTime,LocalDateTime> datesOfPurpleLimit) {
		super(firstName, lastName, userName, password,cinema, isLoggedIn);
		this.purpleLimit = purpleLimit;
		this.datesOfPurpleLimit = datesOfPurpleLimit;
	}
	public int getId() {
		return id;
	}
	
	public Pair<LocalDateTime, LocalDateTime> getDatesOfPurpleLimit() {
		return datesOfPurpleLimit;
	}
	public void setDatesOfPurpleLimit(Pair<LocalDateTime, LocalDateTime> datesOfPurpleLimit) {
		this.datesOfPurpleLimit = datesOfPurpleLimit;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isPurpleLimit() {
		return purpleLimit;
	}
	public void setPurpleLimit(boolean purpleLimit) {
		this.purpleLimit = purpleLimit;
	}
	
}
