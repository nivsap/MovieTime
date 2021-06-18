package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "SubscriptionCards")
public class SubscriptionCard implements  Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int remaining;
	private String serial;
	private int serialSize =10;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Purchase purchase;
	
	public SubscriptionCard() {
		super();
		if(serial == null || serial.isBlank())
			serial = getAlphaNumericString(serialSize);
		remaining = 20;
	}
	
	public SubscriptionCard(Purchase purchase) {
		if(serial == null || serial.isBlank())
			serial = getAlphaNumericString(serialSize);
		remaining = 20;
		this.purchase = purchase;
	}
	
	public int getId() {
		return id;
	}
	
	public int getRemaining() {
		return remaining;
	}
	
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	
	public Purchase getPurchase() {
		return purchase;
	}
	
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	
	public String getEmail() {
		return purchase.getEmail();
	}
	
	private static String getAlphaNumericString(int n)
    {
  
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }

	public String getSerial() {
		return serial;
	}
}
