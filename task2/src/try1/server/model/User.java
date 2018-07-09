package try1.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "email")
	String email;
	@Column(name = "passhash")
	String pass;
	@Column(name = "role")
	String role;
	@Column(name = "registrationsdate")
	private java.sql.Timestamp registationsDate;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true, name = "email")
	private Bill bill;

	public User() {
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public User(String email, String pass, String role, Bill bill) {
		this.email = email;
		this.pass = pass;
		this.role = role;
		this.bill = bill;
		this.registationsDate = new java.sql.Timestamp(System.currentTimeMillis());
	}

	public User(String email, String pass, String role) {
		this.email = email;
		this.pass = pass;
		this.role = role;
		this.registationsDate = new java.sql.Timestamp(System.currentTimeMillis());
	}

	public java.sql.Timestamp getRegistationsDate() {
		return registationsDate;
	}

	public void setRegistationsDate(java.sql.Timestamp registationsDate) {
		this.registationsDate = registationsDate;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String toString() {
		return email + " " + pass + " " + role + " " + registationsDate;
	}
}
