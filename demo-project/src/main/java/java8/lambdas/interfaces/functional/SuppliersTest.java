package java8.lambdas.interfaces.functional;

import java.util.UUID;
import java.util.function.Supplier;

public class SuppliersTest {

	public static void main(String[] args) {
		Supplier<String> supplier1 = UUID.randomUUID()::toString;
		Supplier<String> supplier2 = () -> UUID.randomUUID().toString();

		System.out.println(supplier1.get());
		System.out.println(supplier2.get());
	}

}
