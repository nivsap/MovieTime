package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Complaints")
public class Complaint implements  Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private int complaintType; 
	private LocalDate complaintDate;
	private LocalTime complaintTime;
	private String complaintTitle;
	private int purchaseType;
	private String purchaseSerial;
	private String complaintDetails;
	private Boolean isOpen;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Purchase purchase;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;
	
	public Complaint() {
		super();
	}
	
	public Complaint(String firstName, String lastName, String email, String phoneNumber, String complaintTitle, String complaintDetails, Purchase purchase, boolean isOpen) {
		super();
		complaintDate = LocalDate.now();
		complaintTime = LocalTime.now();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		
		this.complaintTitle = complaintTitle;
		this.complaintDetails = complaintDetails;
		this.purchase = purchase;
		this.isOpen = isOpen;
		
		if(purchase != null) {
			this.complaintType = purchase.getPurchaseType(); 
			cinema = purchase.getCinema();
		}
		else {
			this.complaintType = 4;
			cinema = null;
		}
			
	}
	
	public int getId() {
		return id;
	}
	
	public LocalDate getComplaintDate() {
		return complaintDate;
	}
	
	public void setComplaintDate() {
		this.complaintDate = LocalDate.now();
	}
	
	public LocalTime getComplaintTime() {
		return complaintTime;
	}

	public void setComplaintTime() {
		this.complaintTime = LocalTime.now();
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getComplaintType() {
		return complaintType;
	}
	
	public String getComplaintTitle() {
		return complaintTitle;
	}
	
	public void setComplaintTitle(String complaintTitle) {
		this.complaintTitle = complaintTitle;
	}
	
	public String getComplaintDetails() {
		return complaintDetails;
	}
	
	public void setComplaintDetails(String complaintDetails) {
		this.complaintDetails = complaintDetails;
	}
	
	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
		if(purchase != null) {
			complaintType = purchase.getPurchaseType();
			cinema = purchase.getCinema();
		}
	}
	
	public Cinema getCinema() {
		return cinema;
	}

	public Boolean isOpen() {
		return isOpen;
	}
	
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complaint other = (Complaint) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (complaintType != other.complaintType) {
			return false;
		}
		if (complaintDate == null) {
			if (other.complaintDate != null)
				return false;
		} else if (!complaintDate.equals(other.complaintDate))
			return false;
		if (complaintTime == null) {
			if (other.complaintTime != null)
				return false;
		} else if (!complaintTime.equals(other.complaintTime))
			return false;
		if (complaintTitle == null) {
			if (other.complaintTitle != null)
				return false;
		} else if (!complaintTitle.equals(other.complaintTitle))
			return false;
		if (complaintDetails == null) {
			if (other.complaintDetails != null)
				return false;
		} else if (!complaintDetails.equals(other.complaintDetails))
			return false;
		if (isOpen == null) {
			if (other.isOpen != null)
				return false;
		} else if (!isOpen.equals(other.isOpen))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Complaint number " + id +": [first name= " + firstName + ", last name = " + lastName + ", email= " + email + ", phone number =" + phoneNumber
				+ ", complaint type= " + complaintType + ", complaint date =" + complaintDate + ", complaint time =" + complaintTime + ", complaint title=" 
				+ complaintTitle + ", complaint details=" + complaintDetails + "is open=" + isOpen + "]";
	}

	public String getPurchaseSerial() {
		return purchaseSerial;
	}

	public void setPurchaseSerial(String purchaseSerial) {
		this.purchaseSerial = purchaseSerial;
	}

	public int getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(int purchaseType) {
		this.purchaseType = purchaseType;
	}

}
