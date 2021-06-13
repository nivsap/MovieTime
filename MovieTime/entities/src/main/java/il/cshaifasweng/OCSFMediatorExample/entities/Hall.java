package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Halls")
public class Hall implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int number;
	@Column (name = "hall_rows")
	private int rows;
	@Column (name = "hall_cols")
	private int cols;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
	private Cinema cinema;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "hall")
	private List<Screening> screenings;

	public Hall() {
		super();
	}
	
	public Hall (int rows, int cols, Cinema cinema, ArrayList<Screening> screenings) {
		super();
		this.rows = rows;
		this.cols = cols;
		this.cinema = cinema;
		this.screenings = screenings;
	}
	
	public int getId() {
		return id;
	}
	
	public int getHallId() {
		return number;
	}
	
	public void setHallId(int number) {
		this.number = number;
	}

	public int getRows() {
		return rows;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public void setCols(int cols) {
		this.cols = cols;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	public List<Screening> getScreenings() {
		return screenings;
	}
	
	public void setScreenings(ArrayList<Screening> screenings) {
		this.screenings = screenings;
	}
	
	public void addScreening(Screening screening) {
		if(screenings != null)
			screenings.add(screening);
	}

}
