package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


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
	private String complaintType;
	private LocalDate incidentDate;
	private String complaintTitle;
	private String complaintDetails;
	Boolean isOpen;
	private static String[] complaintTypes = { "Movie screening issues", "Viewing package issues", "Payment issues",
									    	   "Dissatisfaction with customer support", "Other" };
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Purchase purchase;
	private boolean status;
	
	public Complaint() {
		super();
	}
	public Complaint(String firstName, String lastName, String complaintTitle, String complaintDetails, boolean isOpen,Purchase purchase , boolean status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.complaintTitle = complaintTitle;
		this.complaintDetails = complaintDetails;
		this.isOpen = isOpen;
		this.purchase = purchase;
		this.status = status;
	}
	
	public Complaint(String firstName, String lastName, String email, String phoneNumber, String complaintType, LocalDate incidentDate, 
					 String complaintTitle, String complaintDetails, boolean isOpen,Purchase purchase, boolean status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.complaintType = complaintType;
		this.incidentDate = incidentDate;
		this.complaintTitle = complaintTitle;
		this.complaintDetails = complaintDetails;
		this.isOpen = isOpen;
		this.purchase = purchase;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	
	public void setComplaintTitle(String complaintTitle) {
		this.complaintTitle = complaintTitle;
	}
	
	public void setIncidentDate(LocalDate incidentDate) {
		this.incidentDate = incidentDate;
	}
	
	public void setComplaintDetails(String complaintDetails) {
		this.complaintDetails = complaintDetails;
	}
	
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public int getID() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getComplaintType() {
		return complaintType;
	}
	
	public String getComplaintTitle() {
		return complaintTitle;
	}
	
	public LocalDate getIncidentDate() {
		return incidentDate;
	}
	
	public String getComplaintDetails() {
		return complaintDetails;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}
	
	public static String[] getComplaintTypes() {
		return complaintTypes;
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
		if (complaintType == null) {
			if (other.complaintType != null)
				return false;
		} else if (!complaintType.equals(other.complaintType))
			return false;
		if (incidentDate == null) {
			if (other.incidentDate != null)
				return false;
		} else if (!incidentDate.equals(other.incidentDate))
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
				+ ", complaint type= " + complaintType + ", incident date =" + incidentDate + ", complaint title=" 
				+ complaintTitle + ", complaint details=" + complaintDetails + "is open=" + isOpen + "]";
	}

}
