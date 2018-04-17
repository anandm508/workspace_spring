package java8.lambdas.interfaces.functional;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionsTest {

	public static void main(String[] args) {
		// Static method reference
		Function<String, Integer> function1 = String::length;
		// Instance method reference of a particular object
		Function<String, Integer> function2 = (s) -> s.length();

		System.out.println(function1.apply("Welcome to JAVA8"));
		System.out.println(function2.apply("Welcome to JAVA8"));

		// Static method reference
		BiFunction<String, String, String> bifunction1 = String::concat;
		// Instance method reference of a particular object
		BiFunction<String, String, String> bifunction2 = (str1, str2) -> str1.concat(str2);

		System.out.println(function1.apply("Welcome to JAVA8"));
		System.out.println(function2.apply("Welcome to JAVA8"));

		System.out.println(bifunction1.apply("Welcome to ", "JAVA8"));
		System.out.println(bifunction2.apply("Welcome to ", ".NET"));

		Map<String, String> map = new HashMap<>();

		Function<Map<String, String>, Integer> function4 = Map::size;
		System.out.println(function4.apply(map));
	}

}
