package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PriceRequests")
public class PriceRequest implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime date;
	private int type; // 0 - ticket, 1 - link, 2 - subscription card
	private String comment;
	private Float oldPrice;
	private Float newPrice;
	private boolean isOpen;
	private String administratorComment;
	
	public PriceRequest() {
		super();
		this.date = LocalDateTime.now();
	}

	public PriceRequest(int type, String comment, Float oldPrice, Float newPrice, boolean isOpen, String administratorComment) {
		super();
		this.date = LocalDateTime.now();
		this.type = type;
		this.comment = comment;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		this.isOpen = isOpen;
		this.setAdministratorComment(administratorComment);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDateTime getRequestDate() {
		return date;
	}
	
	public void setRequestDate(LocalDateTime date) {
		this.date = date;
	}
	
	public boolean isTicket() {
		return (type == 0);
	}
	
	public boolean isLink() {
		return (type == 1);
	}
	
	public boolean isCard() {
		return (type == 2);
	}
	
	public void setType(int type) {
		if(type < 0 || type > 2)
			return;
		this.type = type;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Float getOldPrice() {
		return oldPrice;
	}
	
	public void setOldPrice(Float oldPrice) {
		this.oldPrice = oldPrice;
	}
	
	public Float getNewPrice() {
		return newPrice;
	}
	
	public void setNewPrice(Float newPrice) {
		this.newPrice = newPrice;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getAdministratorComment() {
		return administratorComment;
	}

	public void setAdministratorComment(String administratorComment) {
		this.administratorComment = administratorComment;
	}
}
