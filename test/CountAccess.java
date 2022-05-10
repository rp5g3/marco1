import java.util.List;

import com.adapit.portal.entidades.SystemCounter;
import com.adapit.portal.services.remote.RemoteServicesUtility;


public class CountAccess {

	
	public static void main(String[] args) {
		List<SystemCounter> list = RemoteServicesUtility.getInstance().retrieveAll(SystemCounter.class);
		for(SystemCounter s: list){
			System.out.println(s.getKey()+" " + s.getValue());
		}
	}

}
