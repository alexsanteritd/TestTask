package try1.server.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="User_Seq")
	@SequenceGenerator(name = "User_Seq", sequenceName = "SEQUENCE_USERS",allocationSize=1,initialValue=2)
    @Column(name="id", nullable=false)
	long id;
	@Column(name = "email", nullable=false)
	String email;
	@Column(name = "passhash", nullable=false)
	String pass;
	@Column(name = "role", nullable=false)
	String role;
	@Column(name = "registrationsdate", nullable=false)
	private Timestamp registationsDate;
	
	@Transient
	private AccountHistory accountHistory;
	
	public AccountHistory getAccountHistory() {
		return accountHistory;
	}

	public void setAccountHistory(AccountHistory accountHistory) {
		this.accountHistory = accountHistory;
	}

	public User() {	
		// TODO Auto-generated constructor stub
	}

	public User(String email, String pass, String role) {
		this.email = email;
		this.pass = pass;
		this.role = role;
		this.registationsDate=new Timestamp(System.currentTimeMillis());
	}

	
	public void setId(long id) {
		this.id = id;
	}


	public Long getId() {
		return id;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", pass=" + pass + ", role=" + role + ", registationsDate="
				+ registationsDate+ "]";
	}




}
