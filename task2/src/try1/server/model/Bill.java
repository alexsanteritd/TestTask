package try1.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usersbilling")
public class Bill {
	@Id
	@Column(name = "email")
	private String email;
	@Column(name = "bill")
	private float billScore;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getBillScore() {
		return billScore;
	}

	public void setBillScore(float bill) {
		this.billScore = bill;
	}

	@Override
	public String toString() {
		return "Bill [email=" + email + ", bill=" + billScore + "]";
	}

	public Bill() {
		// TODO Auto-generated constructor stub
	}

	public Bill(String email) {
		this.email = email;
		billScore = 0;
	}

	public Bill(String email, float bill) {
		super();
		this.email = email;
		this.billScore = bill;
	}

}
