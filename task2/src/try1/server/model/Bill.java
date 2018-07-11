package try1.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "usersbilling")
public class Bill {
	@Id
	@Column(name = "id")
	private long id;

	@Column(name = "bill")
	private float billScore;

	
public Bill() {
	// TODO Auto-generated constructor stub
}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", billScore=" + billScore + "]";
	}

	public Bill(float billScore, long id) {
		this.billScore = billScore;
		this.id=id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getBillScore() {
		return billScore;
	}

	public void setBillScore(float bill) {
		this.billScore = bill;
	}

}
