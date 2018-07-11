package try1.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.view.client.Range;

import try1.client.model.CUser;
import try1.client.serverdata.DataService;
import try1.server.config.UserConfiguration;
import try1.server.services.DataServerService;

public class DataControllerImp extends RemoteServiceServlet implements DataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6414105922524292637L;

	ApplicationContext context = new AnnotationConfigApplicationContext(UserConfiguration.class);
	DataServerService dataSer=context.getBean(DataServerService.class);
	
	@Override
	public List<CUser> getData(Range range) {
		return dataSer.getData(range.getStart(), range.getStart()+range.getLength());
	}

	@Override
	public long getUsersCount() {
		// TODO Auto-generated method stub
		return dataSer.getUsersCount();
	}

	@Override
	public float updateScore(float plusValue, String login, String adminslogin) {
		return dataSer.updateScore(plusValue, login,adminslogin);
		
	}

}
