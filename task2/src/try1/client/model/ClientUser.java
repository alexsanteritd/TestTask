package try1.client.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ClientUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4132447638010459819L;
	float billScore;
	String accsessLevel;
	Timestamp regDate;
	String email;
	
	public ClientUser(float billScore, String email, Timestamp regDate) {
		super();
		this.billScore = billScore;
		this.accsessLevel = null;
		this.regDate = regDate;
		this.email=email;
	}

	public ClientUser(String accsessLevel, float billScore) {
		this.accsessLevel = accsessLevel;
		this.billScore = billScore;
		regDate = null;
	}

	public ClientUser() {
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public float getBillScore() {
		return billScore;
	}

	public void setBillScore(float billScore) {
		this.billScore = billScore;
	}

	public String getAccsessLevel() {
		return accsessLevel;
	}
	
	@Override
	public String toString() {
		return "ClientUser [billScore=" + billScore + ", accsessLevel=" + accsessLevel + "]";
	}

	public String getEmail() {
		return email;
	}

}
