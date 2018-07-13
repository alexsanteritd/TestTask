package try1.client.model;

import java.io.Serializable;

public class ClientUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4132447638010459819L;
	long account;
	String accsessLevel;
	long id;

	public long getId() {
		return id;
	}

	public ClientUser(Long id, String accsessLevel, long billScore) {
		this.id=id;
		this.accsessLevel = accsessLevel;
		this.account = billScore;	}

	public ClientUser() {
	}

	public long getAccount() {
		return account;
	}

	public void setAccount(long billScore) {
		this.account = billScore;
	}

	public String getAccsessLevel() {
		return accsessLevel;
	}

	@Override
	public String toString() {
		return "ClientUser [account=" + account + ", accsessLevel=" + accsessLevel + ", id=" + id + "]";
	}
	
}
