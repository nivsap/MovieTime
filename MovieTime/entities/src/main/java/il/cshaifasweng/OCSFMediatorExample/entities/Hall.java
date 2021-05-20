package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "Hall")
public class Hall implements  Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private static final long serialVersionUID = 1L;
	private boolean chair[][];
	@Column (name = "hall_rows")
	private int rows;
	@Column (name = "hall_cols")
	private int cols;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "hall")
	private List<Screening> screeningArray;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
	private Cinema cinema;
	
	public Hall(boolean[][] chair, int rows, int cols, ArrayList<Screening> screeningArray , Cinema cinema) {
		super();
		this.rows = rows;
		this.cols = cols;
		this.screeningArray = new ArrayList<>();
		this.chair = new boolean[rows][cols];
		this.cinema = cinema;
	}
	public Hall() {}
	
	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean[][] getChair() {
		return chair;
	}
	public void setChair(boolean[][] chair) {
		this.chair = chair;
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
	public List<Screening> getScreeningArray() {
		return screeningArray;
	}
	public void setScreeningArray(ArrayList<Screening> screeningArray) {
		this.screeningArray = screeningArray;
	}
	
	

	
/*boolean[][] planeArray = new boolean[][]{{true, true, true},
                                         {true, true, true},
                                         {true, true, true}
                                        };*/
}
