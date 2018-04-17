package java8;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public class StreamDemo {
	public static void main(String[] args) {
		List<Product> products = new ArrayList<>();
		String name = "A";
		double price = 30;
		Stream<Product> s1 = products.stream().filter((product)-> product.getName().contains(name)).filter((product)-> product.getPrice() < price);
		System.out.println(s1.count());
		
		//Short Circuit Terminal operation
		Optional<Product> opt = s1.findAny();
		opt.get();
		
		
	}
}
