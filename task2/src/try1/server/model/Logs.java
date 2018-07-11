package try1.server.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logs")
public class Logs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	long id;
	
	@Column(name="user_email")
	String userEmail;
	@Column(name="admin_email")
	String adminEmail;
	@Column(name="date")
	Timestamp date;
	@Column(name="bill_change")
	float billChange;
	public Logs() {
		
	}
	public Logs(String userEmail, String adminEmail, float billChange) {
		this.userEmail = userEmail;
		this.adminEmail = adminEmail;
		this.billChange=billChange;
		this.date = new  Timestamp(System.currentTimeMillis());
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public float getBillChange() {
		return billChange;
	}
	public void setBillChange(float billChange) {
		this.billChange = billChange;
	}
	
	
}
