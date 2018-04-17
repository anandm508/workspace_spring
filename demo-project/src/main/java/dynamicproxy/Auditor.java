package dynamicproxy;

public class Auditor {
	public void audit(String service, String extraData) {
		// ... Do the auditing
		System.out.println("Inside auditor....." + service + " " + extraData );
	}
}
