import com.adapit.portal.services.local.LocalServicesUtility;


public class CreateTables {

	
	public static void main(String[] args) {
		LocalServicesUtility.getInstance().openSession();
		System.out.println("End CreateTables");
	}

}
