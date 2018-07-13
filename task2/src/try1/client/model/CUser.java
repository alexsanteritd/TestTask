package try1.client.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3213327774236897707L;
	String email;
	long account;
	Timestamp regDate;
	long id;

	public long getId() {
		return id;
	}

	public CUser(long id,long billScore, Timestamp regDate, String email) {
		super();
		this.id=id;
		this.account = billScore;
		this.regDate = regDate;
		this.email = email;
	}

	public CUser() {
		// TODO Auto-generated constructor stub
	}
	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
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
