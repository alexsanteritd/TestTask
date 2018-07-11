package try1.client.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3213327774236897707L;
	float billScore;
	Timestamp regDate;

	public CUser(float billScore, Timestamp regDate, String email) {
		super();
		this.billScore = billScore;
		this.regDate = regDate;
		this.email = email;
	}

	public CUser() {
		// TODO Auto-generated constructor stub
	}

	String email;

	public float getBillScore() {
		return billScore;
	}

	public void setBillScore(float billScore) {
		this.billScore = billScore;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
