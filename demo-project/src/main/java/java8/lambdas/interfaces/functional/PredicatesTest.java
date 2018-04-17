package java8.lambdas.interfaces.functional;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicatesTest {

	public static void main(String[] args) {
		Predicate<String> predicate1 = String::isEmpty;
		Predicate<String> predicate2 = (s) -> s.isEmpty();

		System.out.println(predicate1.test("ANAND"));
		System.out.println(predicate2.test(""));

		BiPredicate<String, String> bipredicate1 = String::contains;
		BiPredicate<String, String> bipredicate2 = (s1, s2) -> s1.contains(s2);

		System.out.println(bipredicate1.test("ANAND MUSADDI", "ANAND"));
		System.out.println(bipredicate2.test("ANAND MUSADDI", "ANAND"));
	}

}
