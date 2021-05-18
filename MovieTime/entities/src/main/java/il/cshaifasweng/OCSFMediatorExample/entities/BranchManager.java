package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "BranchManager")
public class BranchManager extends Worker implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	//@JoinColumn(name = "cinema_id")
	//private Cinema cinema;
	 public BranchManager() {
	    }
	public BranchManager(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName, password);
	//	this.cinema = cinema;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	
}
