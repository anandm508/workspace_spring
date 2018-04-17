package java8;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MethodReferene {

	public static void main(String[] args) {
		
		//Instance method reference of an object of a particular type.
		Comparator<String> c1 = (a, b) -> {return a.compareTo(b);};	
		Comparator<String> c2 = String::compareTo;
		
		System.out.println(c1.compare("abc","def"));
		System.out.println(c2.compare("abc","def"));
		
		//Static method reference
		Function<String, String> f1 = (a) -> String.format(a);
		Function<String, String> f2 = String::format;
		
		System.out.println(f1.apply("abc"));
		System.out.println(f2.apply("abc"));
		
		//Instance method reference of an existing object
		String test = "ANAND";
		Predicate<String> p1 = a -> test.contains(a);
		Predicate<String> p2 = test::contains;
		
		System.out.println(p1.test("AN"));
		System.out.println(p2.test("AN"));
		
		//Constructor based method reference
		Supplier<ArrayList<String>> s1 = () -> {return new ArrayList<String>();};
		Supplier<ArrayList<String>> s2 = ArrayList::new;	
		
		System.out.println(s1.get());
		System.out.println(s2.get());		
	}	
}
