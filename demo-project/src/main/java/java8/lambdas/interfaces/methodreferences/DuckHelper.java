package java8.lambdas.interfaces.methodreferences;

public class DuckHelper {

	public static int compareByName(Duck duck1, Duck duck2) {
		return duck1.getName().compareTo(duck2.getName());
	}

	public static int compareByPrice(Duck duck1, Duck duck2) {
		return duck1.getPrice().compareTo(duck2.getPrice());
	}
}
