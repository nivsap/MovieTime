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
@Table(name = "NetworkAdministrator")
public class NetworkAdministrator extends Worker implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L;
	//private ArrayList<Cinema> allCinemas;
	
	public NetworkAdministrator() {}

	public NetworkAdministrator(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName, password);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


}
