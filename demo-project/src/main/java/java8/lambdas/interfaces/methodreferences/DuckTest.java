package java8.lambdas.interfaces.methodreferences;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

public class DuckTest {
	public static void main(String[] args) {
		Comparator<Duck> comparator1 = DuckHelper::compareByName;
		Comparator<Duck> comparator2 = (a, b) -> DuckHelper.compareByName(a, b);

		Duck duck1 = new Duck("duck1", 100);
		Duck duck2 = new Duck("duck2", 200);

		System.out.println(comparator1.compare(duck1, duck2));
		System.out.println(comparator2.compare(duck1, duck2));

		BiPredicate<Duck, String> predicate = (d, str) -> d.getName().startsWith(str);
		Function<Duck, String> function = Duck::getName;
		Supplier<Duck> supplier = Duck::new;

		System.out.println(predicate.test(duck2, "duck2"));
		System.out.println(function.apply(duck1));
		System.out.println(supplier.get());

	}
}
