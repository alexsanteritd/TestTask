package try1.server.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "accounthistory")
public class AccountHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AccountHistory_Seq")
	@SequenceGenerator(name = "AccountHistory_Seq", sequenceName = "SEQUENCE_AccountHistory",allocationSize=1,initialValue=1)
	@Column(name = "id")
	long id;

	@Column(name = "user_id")
	long userId;

	@Column(name = "admin_id")
	long adminId;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	User user;

	@ManyToOne
	@JoinColumn(name = "admin_id", insertable = false, updatable = false)
	User admin;

	@Column(name = "debet")
	long debet;

	@Column(name = "credit")
	long credit;

	@Column(name = "account")
	long amount;

	@Column(name = "date")
	Timestamp date;

	public AccountHistory() {
		credit = 0;
		debet = 0;
		amount = 0;
		date = new Timestamp(System.currentTimeMillis());
	}

	public AccountHistory(long debet, long credit, long account) {

		this.credit = credit;
		this.debet = debet;
		this.amount = account;
		this.date = new Timestamp(System.currentTimeMillis());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getAdmiId() {
		return adminId;
	}

	public void setAdmiId(long adminId) {
		this.adminId = adminId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		setUserId(user.getId());
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
		setAdmiId(admin.getId());
	}

	public long getCredit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}

	public long getDebet() {
		return debet;
	}

	public void setDebet(long debet) {
		this.debet = debet;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long account) {
		this.amount = account;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "AccountHistory [id=" + id + ", user=" + user.getEmail() + ", admin=" + admin.getEmail() + ", debet="
				+ debet + ", credit=" + credit + ", account=" + amount + ", date=" + date + "]";
	}

}
