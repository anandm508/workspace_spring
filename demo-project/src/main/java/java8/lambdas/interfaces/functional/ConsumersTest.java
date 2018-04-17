package java8.lambdas.interfaces.functional;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumersTest {
	public static void main(String[] args) {
		Consumer<String> consumer1 = System.out::println;
		Consumer<String> consumer2 = (x) -> System.out.println(x);

		consumer1.accept("Anand");
		consumer2.accept("Musaddi");

		Map<String, String> map = new HashMap<>();
		BiConsumer<String, String> biconsumer1 = map::put;
		BiConsumer<String, String> biconsumer2 = (k, v) -> map.put(k, v);
		Consumer<Map<String, String>> biconsumer3 = System.out::println;

		biconsumer1.accept("key1", "value1");
		biconsumer2.accept("key2", "value2");
		
		biconsumer3.accept(map);

	}
}
