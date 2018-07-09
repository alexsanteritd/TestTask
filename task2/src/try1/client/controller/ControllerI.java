package try1.client.controller;

import try1.client.model.ClientUser;

public interface ControllerI {
	public void swapTo(String pageName);
	public ClientUser getClientUser();
	public void setClientUser(ClientUser clientUser);
}
