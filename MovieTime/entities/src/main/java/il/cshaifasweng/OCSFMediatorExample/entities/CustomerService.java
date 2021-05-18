package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CustomerService")
public class CustomerService extends Worker implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	boolean purpleLimit;
	public CustomerService() {}
	public CustomerService(String firstName, String lastName, String userName, String password, boolean purpleLimit) {
		super(firstName, lastName, userName, password);
		this.purpleLimit = purpleLimit;
	}
	public int getId() {
		return id;
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
